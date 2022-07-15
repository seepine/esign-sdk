package com.seepine.esign.model.identity.common;

import lombok.Data;

/**
 * @author huanghs
 */
@Data
public class QueryFlowDetailRes {
  String flowId;
  /**
   * 认证流程状态
   *
   * <p>INIT 已发起，该状态下e签宝不会计费
   *
   * <p>ING 进行中
   *
   * <p>SUCCESS 已成功
   *
   * <p>FAIL 已失败
   */
  String status;
  /**
   * 认证主体类型
   *
   * <p>INDIVIDUAL 个人
   *
   * <p>ORGANIZATION 组织机构
   */
  String objectType;

  Long startTime;
  Long endTime;
  String failReason;
  /**
   * 实名认证所使用的认证类型
   *
   * <p>个人实名认证场景：
   *
   * <p>INDIVIDUAL_TELECOM_3_FACTOR 个人运营商三要素
   *
   * <p>INDIVIDUAL_BANKCARD_4_FACTOR 个人银行卡四要素
   *
   * <p>INDIVIDUAL_ARTIFICIAL 个人人工实名
   *
   * <p>FACEAUTH_ZMXY 个人人脸识别：支付宝刷脸
   *
   * <p>FACEAUTH_TECENT_CLOUD 个人人脸识别：微众刷脸
   *
   * <p>FACEAUTH_ESIGN 个人人脸识别：e签宝刷脸
   *
   * <p>FACEAUTH_WE_CHAT_FACE 个人人脸识别：微信小程序刷脸
   *
   * <p>企业实名认证场景：
   *
   * <p>ORGANIZATION_INFO_AUTH 企业信息比对（企业三要素或四要素）
   *
   * <p>ORGANIZATION_TRANSFER_RANDOM_AMOUNT 随机金额打款校验
   *
   * <p>ORGANIZATION_REVERSE_PAYMENT 企业反向打款
   *
   * <p>ORGANIZATION_ZMXY 企业芝麻信用认证
   *
   * <p>LEGAL_REP_SIGN_AUTHORIZE 企业法定代表人签署授权认证
   *
   * <p>ORGANIZATION_ARTIFICIAL 企业人工实名
   *
   * <p>LEGAL_REP_AUTH 法定代表人认证
   */
  String authType;
  /**
   * 企业实名认证中，经办人所使用的认证类型（只有企业认证流程才会返回）
   *
   * <p>经办人实名认证场景：
   *
   * <p>INDIVIDUAL_TELECOM_3_FACTOR 个人运营商三要素
   *
   * <p>INDIVIDUAL_BANKCARD_4_FACTOR 个人银行卡四要素
   *
   * <p>INDIVIDUAL_ARTIFICIAL 个人人工实名
   *
   * <p>FACEAUTH_ZMXY 个人人脸识别：支付宝刷脸
   *
   * <p>FACEAUTH_TECENT_CLOUD 个人人脸识别：微众刷脸
   *
   * <p>FACEAUTH_ESIGN 个人人脸识别：e签宝刷脸
   *
   * <p>FACEAUTH_WE_CHAT_FACE 个人人脸识别：微信小程序刷脸
   *
   * <p>经办人意愿认证场景：
   *
   * <p>WILL_AUTHCODE 意愿短信验证码
   *
   * <p>WILL_FACEAUTH_ZMXY 意愿人脸识别：支付宝刷脸
   *
   * <p>WILL_FACEAUTH_TECENT_CLOUD 意愿人脸识别：微众刷脸
   *
   * <p>WILL_FACEAUTH_ESIGN 意愿人脸识别：e签宝刷脸
   *
   * <p>WILL_FACEAUTH_WE_CHAT_FACE 意愿人脸识别：微信小程序刷脸
   */
  String agentAuthType;
  /** 认证长链接，链接永久有效 */
  String url;
  /** 认证短链接，有效期30天 */
  String shortlink;

  OrganInfo organInfo;
  IndivInfo indivInfo;
}
