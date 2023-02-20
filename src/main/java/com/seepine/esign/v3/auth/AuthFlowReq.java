package com.seepine.esign.v3.auth;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthFlowReq extends Request {
  String authFlowId;

  public AuthFlowReq(String authFlowId) {
    this.authFlowId = authFlowId;
  }

  @Override
  public void build() {
    super.setUrl("/v3/auth-flow/" + authFlowId);
    super.setMethod(Method.GET);
  }
}
