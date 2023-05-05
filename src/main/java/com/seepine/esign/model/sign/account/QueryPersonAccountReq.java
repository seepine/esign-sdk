package com.seepine.esign.model.sign.account;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryPersonAccountReq extends Request {
  String accountId;

  public QueryPersonAccountReq(String accountId) {
    this.accountId = accountId;
  }

  @Override
  public void build() {
    super.setUrl("/v1/accounts/" + accountId);
    super.setMethod(Method.GET);
  }
}
