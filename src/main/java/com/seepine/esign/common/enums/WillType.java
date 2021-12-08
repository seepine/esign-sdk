package com.seepine.esign.common.enums;
/**
 * 页面指定意愿认证方式
 *
 * @author seepine
 */
public enum WillType {
  /** 短信验证码 */
  CODE_SMS,
  /** 支付宝刷脸 */
  FACE_ZHIMA_XY,
  /** 腾讯云刷脸 */
  FACE_TECENT_CLOUD_H5,
  /** e签宝刷脸 */
  FACE_FACE_LIVENESS_RECOGNITION,
  /** 微信小程序刷脸 */
  FACE_WE_CHAT_FACE,
  /** 智能视频认证 */
  FACE_AUDIO_VIDEO_DUAL
}
