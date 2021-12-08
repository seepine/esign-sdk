package com.seepine.esign.common.enums;

/** @author seepine */
public enum ApiEnum {
  /** 正式环境域名 */
  FORMAL("https://openapi.esign.cn"),
  /** 模拟环境（沙箱环境） */
  SANDBOX("https://smlopenapi.esign.cn");

  public String url;

  ApiEnum(String url) {
    this.url = url;
  }
}
