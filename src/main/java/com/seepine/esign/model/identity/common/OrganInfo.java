package com.seepine.esign.model.identity.common;

import lombok.Data;

/**
 * @author seepine
 */
@Data
public class OrganInfo {
  String accountId;
  String name;
  String certNo;
  /**
   * 组织机构证件类型
   *
   * <p>ORGANIZATION_USC_CODE 统一社会信用代码
   *
   * <p>ORGANIZATION_REG_CODE 企业工商注册号
   */
  String certType;
  /** 法定代表人姓名 */
  String legalRepName;
  /**
   * 法定代表人国籍/地区
   *
   * <p>MAINLAND 中国大陆
   *
   * <p>FOREIGN 非中国地区
   *
   * <p>HONGKONG_MACAO 中国香港/澳门地区
   *
   * <p>TAIWAN 中国台湾省地区
   */
  String legalRepNationality;
  /** 法定代表人证件号 */
  String legalRepCertNo;
  /** 法定代表人证件类型 */
  String legalRepCertType;
}
