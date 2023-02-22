package com.seepine.esign.v2.auth.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WebAuthRes {
  /** 认证流程ID */
  String flowId;
  /** 个人实名认证短链接（链接有效期30天） */
  String shortLink;
  /** 个人实名认证长链接（链接永久有效） */
  String url;
}
