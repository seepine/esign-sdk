package com.seepine.esign.model.sign.account;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryPersonReq extends Request {
  String thirdPartyUserId;

  public QueryPersonReq(String thirdPartyUserId) {
    this.thirdPartyUserId = thirdPartyUserId;
  }

  @Override
  public void build() {
    super.setUrl("/v1/accounts/getByThirdId?thirdPartyUserId=" + thirdPartyUserId);
    super.setMethod(Method.GET);
  }
}
