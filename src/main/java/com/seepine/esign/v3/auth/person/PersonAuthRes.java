package com.seepine.esign.v3.auth.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonAuthRes {
  /** 个人认证授权长链接（地址永久有效） */
  String authUrl;
  /** 个人认证授权短链接 （地址有效期30天） */
  String authShortUrl;
  /**
   * 本次认证授权流程ID（请注意保管流程ID，可用于<a
   * href="https://open.esign.cn/doc/opendoc/auth3/hlrs7s">【查询认证授权流程详情】</a>）
   */
  String authFlowId;
}
