package com.seepine.esign;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.seepine.esign.common.enums.ApiEnum;
import com.seepine.esign.common.enums.HeaderConstant;
import com.seepine.esign.common.exception.DefineException;
import com.seepine.esign.common.http.Request;
import com.seepine.esign.common.http.Response;
import com.seepine.esign.common.util.Encryption;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/** @author seepine */
public class SignTemplate {
  private final String host;
  private final String appId;
  private final String appSecret;
  private boolean isDev = false;

  public SignTemplate(String appId, String appSecret) {
    this.appId = appId;
    this.appSecret = appSecret;
    this.host = ApiEnum.FORMAL.url;
  }

  public SignTemplate(String appId, String appSecret, boolean isSandBox) {
    this.appId = appId;
    this.appSecret = appSecret;
    this.host = isSandBox ? ApiEnum.SANDBOX.url : ApiEnum.FORMAL.url;
    this.isDev = isSandBox;
  }

  public SignTemplate(String appId, String appSecret, boolean isSandBox, boolean isDev) {
    this.appId = appId;
    this.appSecret = appSecret;
    this.host = isSandBox ? ApiEnum.SANDBOX.url : ApiEnum.FORMAL.url;
    this.isDev = isDev;
  }

  public <T> Response<T> execute(Request request, Class<T> tClass) throws DefineException {
    request.build();
    String paramJson = JSON.toJSONString(request);
    // 对body体做md5摘要
    String contentMd5 = Encryption.doContentMd5(paramJson);
    // get和delete方式请求不能携带body体
    if (Method.GET.name().equals(request.getMethod().name())
        || Method.DELETE.name().equals(request.getMethod().name())) {
      paramJson = null;
    }
    // 传入生成的bodyMd5,加上其他请求头部信息拼接成字符串
    String message =
        Encryption.appendSignDataString(
            request.getMethod().name(),
            HeaderConstant.ACCEPT.value(),
            contentMd5,
            HeaderConstant.CONTENTTYPE_JSON.value(),
            HeaderConstant.DATE.value(),
            HeaderConstant.HEADERS.value(),
            request.getUrl());

    // 整体做sha256签名
    String reqSignature = Encryption.doSignatureBase64(message, appSecret);
    HttpRequest httpRequest =
        HttpUtil.createRequest(request.getMethod(), host + request.getUrl())
            .headerMap(headers(contentMd5, reqSignature), true)
            .body(paramJson);

    HttpResponse res = httpRequest.execute();

    if (isDev) {
      System.out.println(httpRequest);
      System.out.println("Response Body: ");
      System.out.println(res.body());
      System.out.println();
    }
    Response<T> response;
    try {
      response = JSON.parseObject(res.body(), new TypeReference<Response<T>>(tClass) {});
      if (response == null) {
        throw new Exception();
      }
    } catch (Exception ignore) {
      JSONObject jsonObject = JSON.parseObject(res.body());
      response = new Response<T>() {};
      response.setBody(res.body());
      response.setCode(jsonObject.containsKey("code") ? jsonObject.getString("code") : null);
      response.setMessage(
          jsonObject.containsKey("message") ? jsonObject.getString("message") : null);
      response.setStatus(jsonObject.containsKey("status") ? jsonObject.getInteger("status") : -1);
      response.setData(
          JSON.parseObject(
              jsonObject.containsKey("data") ? jsonObject.getString("data") : "{}", tClass));
    }
    return response;
  }

  private Map<String, String> headers(String contentMd5, String reqSignature) {
    Map<String, String> header = new HashMap<>(7);
    header.put("X-Tsign-Open-App-Id", appId);
    header.put("X-Tsign-Open-Ca-Timestamp", Encryption.timeStamp());
    header.put("Accept", HeaderConstant.ACCEPT.value());
    header.put("X-Tsign-Open-Ca-Signature", reqSignature);
    header.put("Content-MD5", contentMd5);
    header.put("Content-Type", HeaderConstant.CONTENTTYPE_JSON.value());
    header.put("X-Tsign-Open-Auth-Mode", HeaderConstant.AUTHMODE.value());
    return header;
  }

  public boolean upload(String url, ByteArrayOutputStream outputStream) throws DefineException {
    HttpRequest request =
        new HttpRequest(UrlBuilder.ofHttpWithoutEncode(url))
            .method(Method.PUT)
            .header("Content-MD5", Encryption.md5AndBase64(outputStream))
            .header("Content-Type", HeaderConstant.CONTENTTYPE_PDF.value());
    if (isDev) {
      System.out.println(request);
    }
    HttpResponse res = request.body(outputStream.toByteArray()).execute();
    if (isDev) {
      System.out.println("Response Body: ");
      System.out.println(res.body());
      System.out.println();
    }
    if (res.getStatus() != HttpStatus.HTTP_OK) {
      return false;
    }
    if (StrUtil.isBlank(res.body())) {
      return false;
    }
    return res.body().contains("成功");
  }
}
