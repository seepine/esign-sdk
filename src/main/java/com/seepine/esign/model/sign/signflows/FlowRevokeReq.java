package com.seepine.esign.model.sign.signflows;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 签署服务API-一步发起签署
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowRevokeReq extends Request {
  String flowId;
  String operatorId;
  String revokeReason;

  public FlowRevokeReq(String flowId) {
    this.flowId = flowId;
  }

  public FlowRevokeReq(String flowId, String revokeReason) {
    this.flowId = flowId;
    this.revokeReason = revokeReason;
  }

  @Override
  public void build() {
    setUrl("/v1/signflows/" + flowId + "/revoke");
    setMethod(Method.PUT);
  }
}
