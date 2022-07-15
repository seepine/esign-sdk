package com.seepine.esign.model.identity.org.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author seepine
 */
@Getter
@Setter
@ToString
@Builder
public class OrgEntity {
  /** 组织机构名称 */
  String name;

  /** 组织机构证件号 */
  String certNo;
  /**
   * 组织机构类型
   *
   * <p>1 - 企业类；2 - 个体工商户 ；99 - 其他组织。
   *
   * <p>不填为不指定组织类型；
   *
   * <p>若指定组织类型，则页面上不可更换
   */
  String organizationType;

  /**
   * 法人证件类型。不传默认：中国大陆身份证【INDIVIDUAL_CH_IDCARD】中国大陆身份证【INDIVIDUAL_CH_TWCARD】台湾来往大陆通行证
   *
   * <p>【INDIVIDUAL_CH_HONGKONG_MACAO】港澳来往大陆通行证
   *
   * <p>【INDIVIDUAL_PASSPORT】护照
   */
  String legalRepCertType;
  /** 法定代表人身份证号 */
  String legalRepCertNo;
  /** 法定代表人姓名 */
  String legalRepName;
  /** 当前实名认证办理人姓名 */
  String agentName;
  /** 当前实名认证办理人证件号 */
  String agentIdNo;
}
