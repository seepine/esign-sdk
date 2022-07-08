package com.seepine.esign.model.sign.account;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryOrganizationReq extends Request {
  String orgId;

  public QueryOrganizationReq(String orgId) {
    this.orgId = orgId;
  }

  @Override
  public void build() {
    super.setUrl("/v1/organizations/" + orgId);
    super.setMethod(Method.GET);
  }
}
