package com.seepine.esign;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.seepine.esign.common.enums.ApiEnum;
import com.seepine.esign.common.enums.HeaderConstant;
import com.seepine.esign.common.exception.DefineException;
import com.seepine.esign.common.http.Request;
import com.seepine.esign.common.http.Response;
import com.seepine.esign.common.util.Encryption;
import com.seepine.esign.interfaces.LogOut;
import com.seepine.json.Json;
import com.seepine.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author seepine
 */
@Slf4j
public class SignTemplate {
  private final String host;
  private final String appId;
  private final String appSecret;
  LogOut logOut;

  public SignTemplate(String appId, String appSecret) {
    this.appId = appId;
    this.appSecret = appSecret;
    this.host = ApiEnum.FORMAL.url;
    Json.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  public SignTemplate(String appId, String appSecret, boolean isSandBox) {
    this.appId = appId;
    this.appSecret = appSecret;
    this.host = isSandBox ? ApiEnum.SANDBOX.url : ApiEnum.FORMAL.url;
    Json.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  public SignTemplate logOut(LogOut logOut) {
    this.logOut = logOut;
    return this;
  }

  public <T> Response<T> execute(Request request, Class<T> tClass) throws DefineException {
    request.build();
    String paramJson =
        ((ObjectNode) Json.parse(Json.toJson(request)))
            .remove(Arrays.asList("url", "method"))
            .toString();
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
    String url = host + request.getUrl();
    HttpRequest httpRequest =
        HttpUtil.createRequest(request.getMethod(), url)
            .headerMap(headers(contentMd5, reqSignature), true)
            .body(paramJson);

    HttpResponse res = httpRequest.execute();
    Response<T> response;
    JsonObject jsonObject = new JsonObject();
    response = new Response<T>() {};
    if (res != null) {
      try {
        jsonObject = Json.parseObj(res.body());
        response.setBody(res.body());
      } catch (Exception ignore) {
      }
    } else {
      response.setBody("");
    }
    response.setCode(jsonObject.getStr("code"));
    response.setMessage(jsonObject.getStr("message"));
    response.setStatus(jsonObject.has("status") ? jsonObject.getInt("status") : -1);
    JsonObject dataObj = jsonObject.getObj("data");
    response.setData(Json.parse(dataObj == null ? "{}" : dataObj.toString(), tClass));
    if (logOut != null) {
      if (res == null) {
        logOut.out(
            new LogOut.Req(url, httpRequest.getMethod(), httpRequest.headers(), paramJson),
            new LogOut.Res(400, new HashMap<>(0), ""));
      } else {
        logOut.out(
            new LogOut.Req(url, httpRequest.getMethod(), httpRequest.headers(), paramJson),
            new LogOut.Res(res.getStatus(), res.headers(), res.body()));
      }
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
    HttpResponse res = request.body(outputStream.toByteArray()).execute();
    if (logOut != null) {
      logOut.out(
          new LogOut.Req(url, request.getMethod(), request.headers(), ""),
          new LogOut.Res(res.getStatus(), res.headers(), res.body()));
    }
    if (res.getStatus() != HttpStatus.HTTP_OK) {
      log.info("esign upload err: {}", res.body());
      return false;
    }
    if (StrUtil.isBlank(res.body())) {
      log.info("esign upload err: {}", res.body());
      return false;
    }
    return res.body().contains("成功");
  }
}
