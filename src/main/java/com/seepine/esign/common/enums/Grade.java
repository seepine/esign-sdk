package com.seepine.esign.common.enums;

/**
 * 指定是否使用银行卡4要素信息比对详情版，如指定则核验失败可返回具体不匹配信息
 *
 * @author seepine
 */
public enum Grade {
  /** 详情版，需要单独购买，具体购买方式请咨询e签宝工作人员 */
  ADVANCED,
  /** 普通版，无需单独购买，信息比对核验失败，不会返回具体的不匹配信息 */
  STANDARD
}
