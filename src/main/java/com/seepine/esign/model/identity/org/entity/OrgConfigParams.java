package com.seepine.esign.model.identity.org.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author seepine
 */
@Getter
@Setter
@ToString
@Builder
public class OrgConfigParams {
  /**
   * 指定页面上不可修改的信息属性。 未指定的信息属性可以修改。
   *
   * <p>name组织机构名称
   *
   * <p>certNo组织机构证件号
   *
   * <p>legalRepName法定代表人姓名
   *
   * <p>legalRepCertNo法定代表人身份证号
   *
   * <p>agentName办理人姓名
   *
   * <p>agentIdNo办理人证件号
   */
  List<String> orgUneditableInfo;
}
