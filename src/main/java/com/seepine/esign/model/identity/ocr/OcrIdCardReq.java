package com.seepine.esign.model.identity.ocr;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 身份证OCR
 *
 * @author seepine 2022.1.28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OcrIdCardReq extends Request {
  private static final String prefix = ";base64,";
  /**
   * 信息面
   *
   * <p>身份证信息面图片BASE64字符串。
   *
   * <p>注意不要带图片BASE64前缀“data:image/jpeg;base64,”
   *
   * <p>图片类型支持：jpg，jpeg，png，bmp。
   *
   * <p>图片建议分辨率为1024*768，图片大小控制在3M以内
   */
  String infoImg;
  /** 国徽面 */
  String emblemImg;

  public OcrIdCardReq(String infoImg, String emblemImg) {
    this.infoImg = filterBase64Header(infoImg);
    this.emblemImg = filterBase64Header(emblemImg);
  }

  public String filterBase64Header(String str) {
    if (str == null) {
      return null;
    }
    if (str.startsWith("data:") && str.indexOf(prefix) > 0) {
      return str.substring(str.indexOf(prefix) + prefix.length());
    }
    return str;
  }

  @Override
  public void build() {
    setMethod(Method.POST);
    setUrl("/v2/identity/auth/api/ocr/idcard");
  }
}
