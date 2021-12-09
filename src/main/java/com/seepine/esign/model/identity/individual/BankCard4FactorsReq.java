package com.seepine.esign.model.identity.individual;

import cn.hutool.http.Method;
import com.seepine.esign.common.enums.CertType;
import com.seepine.esign.common.enums.Grade;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发起银行4要素认证
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BankCard4FactorsReq extends Request {
  /** 实名认证 */
  String name;

  CertType certType = CertType.INDIVIDUAL_CH_IDCARD;
  String idNo;
  String mobileNo;
  String bankCardNo;

  /** 核身认证 */
  String accountId;

  Boolean repetition;

  /** 共有参数，对接方业务上下文id，将在异步通知及跳转时携带返回对接方 */
  String contextId;
  /** 共有参数，认证结束后异步通知地址,具体见"异步通知"章节说明 */
  String notifyUrl;
  /** 共有参数，银行卡4要素信息比对版本 */
  Grade grade = Grade.STANDARD;

  public BankCard4FactorsReq(String name, String idNo, String mobileNo, String bankCardNo) {
    this.name = name;
    this.idNo = idNo;
    this.mobileNo = mobileNo;
    this.bankCardNo = bankCardNo;
  }

  public BankCard4FactorsReq(String accountId, String mobileNo, String bankCardNo) {
    this.accountId = accountId;
    this.mobileNo = mobileNo;
    this.bankCardNo = bankCardNo;
  }

  @Override
  public void build() {
    setUrl(
        accountId == null
            ? "/v2/identity/auth/api/individual/bankCard4Factors"
            : "/v2/identity/auth/api/individual/" + accountId + "/bankCard4Factors");
    setMethod(Method.POST);
  }
}
