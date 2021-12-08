package com.seepine.esign.model.sign.account;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** @author seepine */
@Getter
@Setter
@ToString
public class CreateOrganizationReq extends Request {
  private String thirdPartyUserId;
  private String name;
  private String idType;
  private String idNumber;
  private String orgLegalIdNumber;
  private String orgLegalName;

  public CreateOrganizationReq(
      String thirdPartyUserId, String name, String idType, String idNumber) {
    this.thirdPartyUserId = thirdPartyUserId;
    this.name = name;
    this.idType = idType;
    this.idNumber = idNumber;
  }

  @Override
  public void build() {
    super.setUrl("/v1/organizations/createByThirdPartyUserId");
    super.setMethod(Method.POST);
  }
}
