package com.seepine.esign.model.sign.notify;

import com.seepine.esign.common.enums.NotifyActionEnum;
import lombok.Data;

/** @author seepine */
@Data
public class NotifyRes {
  String accountId;
  String businessScence;
  String statusDescription;
  String endTime;
  String createTime;
  Integer signResult;
  String signTime;
  String readTime;
  String thirdPartyUserId;
  String authorizedAccountId;
  NotifyActionEnum action;
  String resultDescription;
  String flowId;
  /** 2-已完成: 所有签署人完成签署； 3-已撤销: 发起方撤销签署任务； 5-已过期: 签署截止日到期后触发； 7-已拒签 */
  String flowStatus;

  Integer order;
  Long timestamp;

  /**
   * 是否签约流程成功，表示签约流程结束且签约完成
   *
   * @return boolean
   */
  public boolean isSignFlowSuccess() {
    return NotifyActionEnum.SIGN_FLOW_FINISH.equals(action) && "2".equals(flowStatus);
  }
}
