package com.seepine.esign.v3.auth.person.entity;

public class RedirectConfig {
  /** 认证完成后跳转页面（需符合 https /http 协议地址） */
  String redirectUrl;
  /**
   * 重定向跳转延迟时间，单位为秒。
   *
   * <p>授权模式下（authorizedScopes有具体的参数值）：默认延迟时间为 3秒。
   *
   * <p>传 0 - 不展示授权结果页，认证完成直接跳转重定向地址 传 其他数字 - 展示授权结果页，倒计时 x秒后，自动跳转重定向地址
   * 实名模式下（authorizedScopes不传或者没有具体的参数值）：默认延迟时间为 5秒。
   *
   * <p>传 0 - 不展示实名结果页，认证完成直接跳转重定向地址 传 其他数字 - 展示实名结果页，倒计时5秒后，自动跳转重定向地址（只有5秒，没有其他秒数的控制）
   * 【注】当redirectUrl不传的情况下，该字段无需传入，认证完成结果页不跳转。
   */
  String redirectDelayTime;
}
