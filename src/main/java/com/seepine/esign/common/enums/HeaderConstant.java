package com.seepine.esign.common.enums;
/**
 * 头部信息常量
 *
 * @author 澄泓
 */
public enum HeaderConstant {
  /** 头部信息常量 */
  ACCEPT("*/*"),
  DATE(""),
  HEADERS(""),
  CONTENTTYPE_JSON("application/json; charset=UTF-8"),
  CONTENTTYPE_PDF("application/pdf"),
  CONTENTTYPE_STREAM("application/octet-stream"),
  AUTHMODE("Signature");

  private final String value;

  HeaderConstant(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }
}
