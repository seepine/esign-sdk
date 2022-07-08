package com.seepine.esign.model.sign.signflows.entity;

import com.seepine.esign.common.enums.PersonAvailableAuthType;
import com.seepine.esign.common.enums.WillType;
import lombok.Data;

/**
 * @author seepine
 */
@Data
public class FlowInfo {
  /** 本次签署流程的文件主题名称 */
  String businessScene;
  /** 是否自动归档 */
  Boolean autoArchive = true;
  /** 是否自动开启 */
  Boolean autoInitiate = true;
  /** 本次签署流程的任务信息配置 */
  FlowConfigInfo flowConfigInfo = new FlowConfigInfo();

  public FlowInfo(String businessScene, String noticeDeveloperUrl) {
    this.businessScene = businessScene;
    this.flowConfigInfo.setNoticeDeveloperUrl(noticeDeveloperUrl);
    this.flowConfigInfo.personAvailableAuthTypes =
        new PersonAvailableAuthType[] {PersonAvailableAuthType.PSN_FACEAUTH_BYURL};
  }

  @Data
  public static class FlowConfigInfo {
    /** 通知开发者地址。（e签宝服务器主动通过POST方式通知开发者指定服务器的页面路径（http/https）） */
    String noticeDeveloperUrl;
    /** 签署通知方式， 默认方式：1。同时支持多种通知方式，用逗号分割。1-短信，2-邮件。 ""则不通知 */
    String noticeType = "";
    /** 签署完成后，重定向跳转地址（http/https） */
    String redirectUrl;
    /** 签署平台，默认值1,2 可选择多种签署平台，逗号分割。1-H5网页版方式进行签署；2-跳转支付宝(移动端)或支付宝扫码进行签署(PC端)。 */
    String signPlatform;
    /** 个人页面显示实名认证方式,默认刷脸 */
    PersonAvailableAuthType[] personAvailableAuthTypes;
    /** 页面指定意愿认证方式,默认微信小程序刷脸 */
    WillType[] willTypes;
  }
}
