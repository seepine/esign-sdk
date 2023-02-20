package com.seepine.esign.v3.auth;

import com.seepine.esign.v3.auth.org.entity.Organization;
import com.seepine.esign.v3.auth.person.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthFlowRes {
  /** 认证授权流程ID */
  public String authFlowId;
  /** 认证授权主体类型 ORG-机构实名认证，PSN-个人实名认证 */
  public String authType;
  /** 流程中使用的认证类型 realName-实名认证，willingness-意愿认证，none-都没使用 */
  public String realNameOrWillingness;
  /** 认证流程状态 0-未实名，1-已实名 */
  public String realNameStatus;
  /** 认证详情 */
  public AuthInfo authInfo;
  /** 本次授权详情 */
  public List<AuthorizedInfo> authorizedInfo;

  @Getter
  @ToString
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AuthInfo {
    /**
     * 流程中使用的意愿认证方式
     *
     * <p>CODE_SMS - 短信验证码
     *
     * <p>CODE_EMAIL - 邮箱验证码
     *
     * <p>PSN_FACE_ALIPAY - 支付宝刷脸
     *
     * <p>PSN_FACE_TECENT - 腾讯云刷脸
     *
     * <p>PSN_FACE_ESIGN - e签宝刷脸
     *
     * <p>PSN_FACE_WECHAT - 微信小程序刷脸
     */
    public String willingnessAuthModes;
    /**
     * 本次流程中使用的个人/经办人认证方式
     *
     * <p>PSN_BANKCARD4 - 个人银行卡四要素认证
     *
     * <p>PSN_MOBILE3 - 手机运营商三要素认证
     *
     * <p>PSN_BANKCARD4_DETAILS - 个人银行卡四要素认证（详情版）
     *
     * <p>PSN_MOBILE3_DETAILS - 个人运营商三要素认证（详情版）
     *
     * <p>PSN_FACE - 刷脸认证
     */
    public String psnAuthMode;
    /**
     * 本次流程中机构实名认证使用的认证方式
     *
     * <p>ORG_BANK_TRANSFER - 对公账户打款认证
     *
     * <p>ORG_ALIPAY_CREDIT - 企业支付宝认证
     *
     * <p>ORG_LEGALREP_AUTHORIZATION - 授权委托书认证
     *
     * <p>ORG_LEGALREP - 法定代表人本人实名认证
     *
     * <p>ORG_LEGALREP_WILLINGNESS - 法定代表人本人意愿认证
     */
    public String orgAuthMode;
    /** 流程创建时间（Unix时间戳格式，单位：毫秒） */
    public Long authFlowCreateTime;
    /** 流程最后更新时间（Unix时间戳格式，单位：毫秒） */
    public Long authFlowUpdateTime;
    /**
     * 个人以及机构经办人信息
     *
     * <p>个人实名认证场景返回个人信息 机构实名认证场景返回经办人信息
     */
    public Person person;
    /** 实名认证的机构信息 */
    public Organization organization;
  }

  @Getter
  @ToString
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AuthorizedInfo {
    /**
     * 用户授权范围
     *
     * <p>get_psn_identity_info 允许获取个人用户的账号基本信息 psn_initiate_sign 允许代表个人用户发起合同签署 manage_psn_resource
     * 允许获取个人用户的印章等资源的管理权限 get_org_identity_info 允许获取企业/组织用户的账号基本信息 org_initiate_sign
     * 允许代表企业/组织用户发起合同签署 manage_org_resource 允许获取企业/组织用户的印章、组织成员等资源的管理权限 use_org_order
     * 允许获取企业/组织用户套餐订单的使用权限
     */
    public String authorizedScope;
    /** 授权生效时间（unix时间戳格式，单位：毫秒） */
    public Long effectiveTime;
    /** 授权失效时间（unix时间戳格式，单位：毫秒） */
    public Long expireTime;
  }
}
