package com.seepine.esign.model.identity.org;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import com.seepine.esign.model.identity.org.entity.OrgConfigParams;
import com.seepine.esign.model.identity.org.entity.OrgContextInfo;
import com.seepine.esign.model.identity.org.entity.OrgEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 发起个人运营商3要素认证
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrgAuthUrlWithoutAccountReq extends Request {
  /**
   * 指定组织机构默认认证类型（打开认证页面所展示的第一个认证类型）
   *
   * <p>【ORG_BANK_TRANSFER】组织机构对公账户打款认证
   *
   * <p>【ORG_ZM_AUTHORIZE】企业芝麻认证【ORG_LEGAL_AUTHORIZE】组织机构法定代表人授权书签署认证
   *
   * <p>【LEGAL_REP_AUTH】法定代表人认证
   */
  String authType;
  /**
   * 指定页面显示组织机构认证方式
   *
   * <p>【ORG_BANK_TRANSFER】组织机构对公账户打款认证
   *
   * <p>【ORG_ZM_AUTHORIZE】企业芝麻认证
   *
   * <p>【ORG_LEGAL_AUTHORIZE】组织机构法定代表人授权书签署认证
   *
   * <p>【LEGAL_REP_AUTH】法定代表人认证
   *
   * <p>注：
   *
   * <p>（1）法定代表人本人认证的方式默认都是在页面存在的，无法去掉。
   *
   * <p>（2）当只希望用户选择【法定代表人认证】一种方式时，此参数仅传入 LEGAL_REP_AUTH 即可。
   */
  List<String> availableAuthTypes;
  /**
   * 指定页面显示办理人认证方式
   *
   * <p>【PSN_TELECOM_AUTHCODE】个人运营商三要素 【PSN_BANK4_AUTHCODE】个人银行四要素 【PSN_FACEAUTH_BYURL】个人刷脸认证
   *
   * <p>【PSN_FACEAUTH_BYURL_ZHIMA】芝麻认证-人脸识别
   *
   * <p>【PSN_FACEAUTH_BYURL_WEIZHONG】微众-人脸识别
   *
   * <p>【PSN_FACEAUTH_BYURL_ESIGN】e签宝-人脸识别
   *
   * <p>【PSN_FACEAUTH_BYURL_WE_CHAT】微信小程序-人脸识别
   */
  List<String> agentAvailableAuthTypes;
  /**
   * 指定办理人在认证过程中，如果通过银行卡认证或运营商认证方式进行实名认证时，可指定是否使用详情版，如指定则核验失败可返回具体不匹配信息，传空默认为普通版。
   *
   * <p>【PSN_BANK4_AUTHCODE】个人银行卡四要素认证
   *
   * <p>【PSN_TELECOM_AUTHCODE】个人运营商三要素认证
   */
  List<String> agentAuthAdvancedEnabled;
  /** 接收实名认证链接短信通知的手机号。传入手机号会发送邀请实名认证短信通知到该用户，并收取一次短信费用，不传入则不发送，不产生短信费用 */
  String receiveUrlMobileNo;

  /** 组织机构基本信息 */
  OrgEntity orgEntity;

  OrgConfigParams configParams;
  /** 业务方交互上下文信息 */
  OrgContextInfo contextInfo;

  public OrgAuthUrlWithoutAccountReq(String busId, String notifyUrl) {
    this.contextInfo = OrgContextInfo.builder().contextId(busId).notifyUrl(notifyUrl).build();
  }

  public OrgAuthUrlWithoutAccountReq(
      String busId, String notifyUrl, String redirectUrl, Boolean showResultPage) {
    this.contextInfo =
        OrgContextInfo.builder()
            .contextId(busId)
            .notifyUrl(notifyUrl)
            .redirectUrl(redirectUrl)
            .showResultPage(showResultPage)
            .build();
  }

  @Override
  public void build() {
    setUrl("/v2/identity/auth/web/orgAuthUrl");
    setMethod(Method.POST);
  }
}
