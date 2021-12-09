package com.seepine.esign.common.enums;

/** @author seepine */
public enum NotifyActionEnum {
  /** 签署人签署完成 */
  SIGN_FLOW_UPDATE,
  /** 流程结束 */
  SIGN_FLOW_FINISH,
  /** 流程文件过期前提醒 */
  SIGN_DOC_EXPIRE_REMIND,
  /** 流程文件过期 */
  SIGN_DOC_EXPIRE,
  /** 文件添加数字水印完成 */
  BATCH_ADD_WATERMARK_REMIND,
  /** 签署人申请修改身份信息 */
  FEEDBACK_SIGNERINFO,
  /** 经办人转交签署任务 */
  PROCESS_HANDOVER,
  /** 意愿认证完成 */
  WILL_FINISH,
  /** 签署人已读 */
  PARTICIPANT_MARKREAD;

  @Override
  public String toString() {
    return super.name();
  }
}
