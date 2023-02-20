package com.seepine.esign.v3.auth.person.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 个人实名认证配置项
 *
 * <p>个人账号标识psnAccount与个人账号psnId二者选一项传入即可； 如已实名，则页面需进行意愿认证。
 */
@Getter
@Setter
@ToString
@Builder
public class PsnAuthConfig {
  /** 个人用户账号标识（手机号或邮箱） （若未知用户的psnId/用户未实名，请传此字段） */
  String psnAccount;
  /** 个人账号ID （若已知用户的psnId/用户已实名，请传此字段） */
  String psnId;
  /** 个人身份附加信息 */
  PsnInfo psnInfo;
  /** 个人实名认证页面配置项 */
  PsnAuthPageConfig psnAuthPageConfig;

  @Getter
  @Setter
  @ToString
  @Builder
  public static class PsnInfo {
    /** 姓名 */
    String psnName;
    /** 证件号码 */
    String psnIDCardNum;
    /**
     * 证件类型，可选值如下：
     *
     * <p>CRED_PSN_CH_IDCARD - 中国大陆居民身份证
     *
     * <p>CRED_PSN_CH_HONGKONG - 香港来往大陆通行证
     *
     * <p>CRED_PSN_CH_MACAO - 澳门来往大陆通行证
     *
     * <p>CRED_PSN_CH_TWCARD - 台湾来往大陆通行证
     *
     * <p>CRED_PSN_PASSPORT - 护照
     */
    String psnIDCardType;
    /** 个人手机号（运营商实名登记手机号或银行卡预留手机号，仅用于认证） */
    String psnMobile;
    /** 个人银行卡号 */
    String bankCardNum;
  }

  @Getter
  @Setter
  @ToString
  @Builder
  public static class PsnAuthPageConfig {
    /**
     * 设置页面中默认选择的实名认证方式，可选值如下：
     *
     * <p>PSN_FACE - 人脸识别认证（默认值）
     *
     * <p>PSN_MOBILE3 - 手机运营商三要素认证
     *
     * <p>PSN_BANKCARD4 - 银行卡四要素认证
     */
    String psnDefaultAuthMode;
    /**
     * 设置页面中可选择的个人认证方式范围，若不传此参数，则可选择全部认证方式。
     *
     * <p>PSN_FACE - 人脸识别认证 PSN_MOBILE3 - 手机运营商三要素认证 PSN_BANKCARD4 - 银行卡四要素认证
     */
    List<String> psnAvailableAuthModes;
    /**
     * 设置页面中可编辑的个人信息字段，不传此参数，页面默认不允许编辑个人信息。
     *
     * <p>name-姓名 IDCardNum-证件号码 mobile-个人手机号（仅针对实名认证手机号） bankCardNum-个人银行卡号
     */
    List<String> psnEditableFields;
  }
}
