package com.seepine.esign.v2.auth.person;

import cn.hutool.http.Method;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.http.Request;
import com.seepine.esign.v2.auth.person.entity.ConfigParams;
import com.seepine.esign.v2.auth.person.entity.ContextInfo;
import com.seepine.esign.v2.auth.person.entity.IndivInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebAuthReq extends Request {
  @JsonIgnore String accountId;
  /**
   * 指定默认认证类型（打开认证页面所展示的第一个认证类型）
   *
   * <p>PSN_BANK4_AUTHCODE-个人银行卡四要素认证，PSN_TELECOM_AUTHCODE-个人运营商三要素认证，PSN_FACEAUTH_BYURL-个人刷脸认证
   */
  String authType;
  /**
   * 指定页面显示认证方式
   *
   * <p>PSN_BANK4_AUTHCODE-个人银行卡四要素认证，PSN_TELECOM_AUTHCODE-个人运营商三要素认证，PSN_FACEAUTH_BYURL-个人刷脸认证
   */
  List<String> availableAuthTypes;
  /**
   * 指定通过银行卡认证或运营商认证方式时，是否使用详情版，如指定则核验失败可返回具体不匹配信息，传空默认为普通版。
   *
   * <p>PSN_BANK4_AUTHCODE-个人银行卡四要素认证 PSN_TELECOM_AUTHCODE-个人运营商三要素认证
   */
  String authAdvancedEnabled;
  /** 接收实名认证链接短信通知的手机号。传入手机号会发送邀请实名认证短信通知到该用户，并收取一次短信费用，不传入则不发送，不产生短信费用 */
  String receiveUrlMobileNo;
  /** 是否允许重复实名，默认允许 */
  Boolean repeatIdentity;
  /** 个人实名认证的基本信息 */
  IndivInfo indivInfo;
  /** 认证配置信息 */
  ConfigParams configParams;
  /** 业务方交互上下文信息，有统计需求或者分账需求必填部分参数 */
  ContextInfo contextInfo;

  public WebAuthReq(String accountId) {
    this.accountId = accountId;
  }

  @Override
  public void build() {
    super.setUrl("/v2/identity/auth/web/" + accountId + "/indivIdentityUrl");
    super.setMethod(Method.POST);
  }
}
