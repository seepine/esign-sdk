package com.seepine.esign.v3.auth.person;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import com.seepine.esign.v3.auth.person.entity.AuthorizeConfig;
import com.seepine.esign.v3.auth.person.entity.PsnAuthConfig;
import com.seepine.esign.v3.auth.person.entity.RedirectConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询文件上传状态
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonAuthReq extends Request {
  /** 个人实名认证配置项，个人账号标识psnAccount与个人账号psnId二者选一项传入即可； 如已实名，则页面需进行意愿认证。 */
  PsnAuthConfig psnAuthConfig;
  /** 个人授权配置项，授权有效期默认180天，联系e签宝对接人员最长可修改为365天，授权过期需重新授权； 不传此参数默认页面仅实名认证，不需要用户授权。 */
  AuthorizeConfig authorizeConfig;
  /** 认证完成重定向配置项 */
  RedirectConfig redirectConfig;
  /** 接收回调通知的Web地址，通知开发者用户认证和授权的完成以及变更情况， */
  String notifyUrl;
  /** 指定客户端类型，默认值 ALL（注意参数值全部为英文大写）,ALL-自动适配移动端或PC端；H5-移动端适配；PC-PC端适配 */
  String clientType;
  /** AppScheme，用于支付宝人脸认证重定向时唤起指定App。示例值：esign://demo/realBack */
  String appScheme;

  /**
   * @param psnId 个人账号ID
   */
  public PersonAuthReq(String psnId) {
    psnAuthConfig = PsnAuthConfig.builder().psnId(psnId).build();
  }

  public PersonAuthReq() {}

  @Override
  public void build() {
    super.setUrl("/v3/psn-auth-url");
    super.setMethod(Method.POST);
  }
}
