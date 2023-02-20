package com.seepine.esign.v3.auth.person.entity;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
  /** 个人账号ID */
  public String psnId;
  /** 个人账号标识（手机号/邮箱） */
  public PsnAccount psnAccount;
  /** 个人身份信息 */
  public PsnInfo psnInfo;
  /** 人脸识别信息 */
  public FaceRecognitionInfo faceRecognitionInfo;

  @Getter
  @Setter
  @ToString
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PsnAccount {
    /** 手机号（个人账号标识） */
    public String accountMobile;
    /** 邮箱（个人账号标识） */
    public String accountEmail;
  }

  @Getter
  @Setter
  @ToString
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PsnInfo {
    /** 个人姓名 */
    public String psnName;
    /** 个人用户已认证的国籍/地区（默认不返回值） */
    public String psnNationality;
    /** 个人用户已认证的证件号 */
    public String psnIDCardNum;
    /**
     * 个人证件类型
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
    public String psnIDCardType;
    /** 个人用户已认证的银行卡号 */
    public String bankCardNum;
    /** 个人用户已认证的运营商实名登记手机号或银行卡预留手机号 */
    public String psnMobile;
  }

  @Getter
  @Setter
  @ToString
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class FaceRecognitionInfo {
    /**
     * 刷脸认证时刷脸照片（base64编码照片图片数据）有效期默认1个小时
     *
     * <p>补充说明：
     *
     * <p>默认不返回值，如需要请联系交付顾问开通； 认证方式仅当选择腾讯云人脸识别和e签宝人脸识别时，才会返回该字段
     */
    String facePhotoUrl;
    /**
     * 刷脸照片相似度得分
     *
     * <p>补充说明：
     *
     * <p>认证方式仅当选择腾讯云人脸识别和e签宝人脸识别时，才会返回该字段
     */
    String similarityScore;
    /**
     * 刷脸活体检测得分
     *
     * <p>补充说明：
     *
     * <p>认证方式仅当选择腾讯云人脸识别和e签宝人脸识别时，才会返回该字段
     */
    String livingScore;
  }
}
