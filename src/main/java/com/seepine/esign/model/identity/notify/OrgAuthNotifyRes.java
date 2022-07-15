package com.seepine.esign.model.identity.notify;

import lombok.Data;

/**
 * @author huanghs
 */
@Data
public class OrgAuthNotifyRes {
  String flowId;
  String accountId;
  String agentFlowId;
  String agentAccountId;
  Boolean success;
  String contextId;
  String verifycode;
}
