package com.seepine.esign.model.identity.individual;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 银行预留手机号验证码校验
 *
 * @author seepine
 */
@Getter
@Setter
@ToString
public class BankCard4FactorsMobileCodeReq extends Request {
  String flowId;
  String authcode;

  /**
   * @param flowId 流程id
   * @param authCode 验证码
   */
  public BankCard4FactorsMobileCodeReq(String flowId, String authCode) {
    this.flowId = flowId;
    this.authcode = authCode;
  }

  @Override
  public void build() {
    setUrl("/v2/identity/auth/pub/individual/" + this.flowId + "/bankCard4Factors");
    setMethod(Method.PUT);
  }
}
