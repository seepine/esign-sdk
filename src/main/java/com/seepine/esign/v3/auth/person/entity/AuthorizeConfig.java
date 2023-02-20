package com.seepine.esign.v3.auth.person.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 个人授权配置项
 *
 * <p>授权有效期默认180天，联系e签宝对接人员最长可修改为365天，授权过期需重新授权； 不传此参数默认页面仅实名认证，不需要用户授权。
 */
@Getter
@Setter
@ToString
@Builder
public class AuthorizeConfig {
  /**
   * 设置页面中可选择的授权范围
   *
   * <p>get_psn_identity_info - 授权允许获取个人用户的账号基本信息 psn_initiate_sign - 授权允许代表个人用户发起合同签署
   * manage_psn_resource - 授权允许获取个人用户的印章等资源的管理权限
   */
  List<String> authorizedScopes;
}
