package com.seepine.esign.model.identity.common;

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
public class QueryFlowDetailReq extends Request {
  String flowId;

  public QueryFlowDetailReq(String flowId) {
    this.flowId = flowId;
  }

  @Override
  public void build() {
    setUrl("/v2/identity/auth/api/common/" + flowId + "/detail");
    setMethod(Method.GET);
  }
}
