package com.seepine.esign.model.sign.account;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建个人账号
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreatePersonReq extends Request {
  private String thirdPartyUserId;
  private String name;
  private String idType;
  private String idNumber;
  private String mobile;
  private String email;

  private CreatePersonReq() {}

  public CreatePersonReq(String thirdPartyUserId, String name, String idType, String idNumber) {
    this.thirdPartyUserId = thirdPartyUserId;
    this.name = name;
    this.idType = idType;
    this.idNumber = idNumber;
  }

  public CreatePersonReq(
      String thirdPartyUserId, String name, String idType, String idNumber, String mobile) {
    this.thirdPartyUserId = thirdPartyUserId;
    this.name = name;
    this.idType = idType;
    this.idNumber = idNumber;
    this.mobile = mobile;
  }

  @Override
  public void build() {
    super.setUrl("/v1/accounts/createByThirdPartyUserId");
    super.setMethod(Method.POST);
  }
}
