package com.seepine.esign.v3.auth.org.entity;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
  /** 机构账号ID */
  public String orgId;
  /** 组织机构名称（账号标识） */
  public String orgName;
  /** 组织机构信息 */
  public OrgInfo orgInfo;

  @Getter
  @Setter
  @ToString
  @Builder
  public static class OrgInfo {
    /** 组织机构证件号 */
    public String orgIDCardNum;
    /** 组织机构证件类型 CRED_ORG_USCC-统一社会信用代码 ，CRED_ORG_REGCODE-工商注册号 */
    public String orgIDCardType;
    /** 法定代表人姓名 */
    public String legalRepName;
    /** 法定代表人证件号 */
    public String legalRepIDCardNum;
    /**
     * 法定代表人证件类型
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
    public String legalRepIDCardType;
  }
}
