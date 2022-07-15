package com.seepine.esign.model.identity.individual;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发起个人运营商3要素认证
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Telecom3FactorsReq extends Request {
  String name;
  String idNo;
  String mobileNo;
  String contextId;
  String notifyUrl;
  String grade;

  public Telecom3FactorsReq(
      String fullName, String idCard, String mobile, String busId, String notifyUrl) {
    this.name = fullName;
    this.idNo = idCard;
    this.mobileNo = mobile;
    this.contextId = busId;
    this.notifyUrl = notifyUrl;
  }

  @Override
  public void build() {
    setUrl("/v2/identity/auth/api/individual/telecom3Factors");
    setMethod(Method.POST);
  }
}
