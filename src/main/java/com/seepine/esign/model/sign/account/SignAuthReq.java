package com.seepine.esign.model.sign.account;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 签署账号服务API - 设置静默签署授权
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SignAuthReq extends Request {
  String accountId;
  String deadline;

  public SignAuthReq(String accountId) {
    this.accountId = accountId;
  }

  public SignAuthReq(String accountId, String deadline) {
    this.accountId = accountId;
    this.deadline = deadline;
  }

  @Override
  public void build() {
    setUrl("/v1/signAuth/" + this.accountId);
    setMethod(Method.POST);
  }
}
