package com.seepine.esign.v2.auth.person.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ContextInfo {
  /** 自定义业务标识 在异步通知时发送回发起方，直接对接客户建议上传相关订单Id或会员Id，渠道分销商建议上传客户唯一Id */
  String contextId;
  /**
   * 发起方接收实名认证状态变更通知的地址，异步通知参考 <a
   * href="https://open.esign.cn/doc/opendoc/identity_service/awa4m6">【请点击】</a>
   */
  String notifyUrl;
  /**
   * 认证发起来源，默认值为 BROWSER
   *
   * <p>BROWSER - 浏览器
   *
   * <p>APP - 移动端APP
   */
  String origin;
  /** 认证结束后页面跳转地址，【点击】查看如何跳转回APP */
  String redirectUrl;
  /**
   * 认证完成是否显示结果页，默认值为 true
   *
   * <p>true - 显示结果，false - 不显示结果
   */
  String showResultPage;
}
