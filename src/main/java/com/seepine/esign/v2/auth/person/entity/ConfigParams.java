package com.seepine.esign.v2.auth.person.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class ConfigParams {
  /**
   * 设置个人认证页面上不可修改的基本信息。传空表示可以修改
   *
   * <p>name - 姓名 certNo - 证件号 mobileNo - 手机号 bankCardNo - 银行卡号
   * 【注】创建e签宝个人账号时传入了姓名和证件号，则页面上姓名和证件号固定不可修改；
   *
   * <p>创建e签宝个人账号时未传入姓名和证件号，则可通过此参数指定页面上姓名和证件号是否可修改。
   */
  List<String> indivUneditableInfo;
}
