package com.seepine.esign.v2.auth.person.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class IndivInfo {
  /** 个人手机号 */
  String mobileNo;
  /** 个人姓名 */
  String name;
  /** 个人银行卡号（仅支持银联卡） */
  String bankCardNo;
  /** 个人证件号 */
  String certNo;
  /**
   * 个人证件类型
   *
   * <p>INDIVIDUAL_CH_IDCARD-中国大陆身份证
   *
   * <p>INDIVIDUAL_CH_TWCARD-台湾来往大陆通行证
   *
   * <p>INDIVIDUAL_CH_HONGKONG_MACAO-港澳来往大陆通行证
   *
   * <p>INDIVIDUAL_PASSPORT-护照
   */
  String certType;
}
