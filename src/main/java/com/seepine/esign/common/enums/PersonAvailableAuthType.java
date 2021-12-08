package com.seepine.esign.common.enums;

/**
 * 个人页面显示实名认证方式
 *
 * @author seepine
 */
public enum PersonAvailableAuthType {
  /** 个人银行卡四要素认证 */
  PSN_BANK4_AUTHCODE,
  /** 个人运营商三要素认证 */
  PSN_TELECOM_AUTHCODE,
  /** 个人刷脸认证 */
  PSN_FACEAUTH_BYURL
}
