package com.seepine.esign.model.sign.signflows;

import cn.hutool.http.Method;
import com.seepine.esign.common.enums.PersonAvailableAuthType;
import com.seepine.esign.common.enums.WillType;
import com.seepine.esign.common.http.Request;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 签署服务API-一步发起签署
 *
 * @author seepine
 */
@Getter
@Setter
@ToString
public class CreateFlowOneStepReq extends Request {
  FlowInfo flowInfo;

  List<Signer> signers;
  List<Doc> docs;

  public CreateFlowOneStepReq() {}

  public CreateFlowOneStepReq(String businessScene, String fileId, OneStepParams... args) {
    // 1.设置签署文件
    this.docs = Collections.singletonList(new Doc(fileId));
    // 2.设置流程信息
    this.flowInfo = new FlowInfo(businessScene);
    // 3.设置签署者信息
    this.signers = new ArrayList<>();
    int order = 1;
    for (OneStepParams arg : args) {
      Signer signer = new Signer();
      // 设置顺序
      signer.setSignOrder(order);
      order++;
      // 设置签署人id
      Signer.SignerAccount signerAccount = new Signer.SignerAccount();
      signerAccount.setSignerAccountId(arg.signerAccountId);
      signerAccount.setAuthorizedAccountId(arg.authorizedAccountId);
      signer.setSignerAccount(signerAccount);
      // 设置签署区域
      Signer.SignField signField = new Signer.SignField(fileId, arg.posPage, arg.posX, arg.posY);
      if (arg.authorizedAccountId != null) {
        signField.setActorIndentityType(2);
      }
      signField.setSealType(arg.getSealType());
      signField.setAutoExecute(arg.getAutoExecute());
      signer.setSignfields(Collections.singletonList(signField));
      signers.add(signer);
    }
  }

  @Override
  public void build() {
    setUrl("/api/v2/signflows/createFlowOneStep");
    setMethod(Method.POST);
  }
}

@Data
@AllArgsConstructor
class Doc {
  String fileId;
}

@Data
class FlowInfo {
  /** 本次签署流程的文件主题名称 */
  String businessScene;
  /** 是否自动归档 */
  Boolean autoArchive = true;
  /** 是否自动开启 */
  Boolean autoInitiate = true;
  /** 本次签署流程的任务信息配置 */
  FlowConfigInfo flowConfigInfo = new FlowConfigInfo();

  private FlowInfo() {}

  FlowInfo(String businessScene) {
    this.businessScene = businessScene;
  }

  @Data
  static class FlowConfigInfo {
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

@Data
class Signer {
  /** 是否平台方自动签署，默认false */
  Boolean platformSign;
  /** 签署顺序，默认1，且不小于1 */
  Integer signOrder;
  /** 签署方账号信息（平台方自动签署时，无需传入该参数） */
  SignerAccount signerAccount;
  /** 签署方的签署区列表数据 */
  List<SignField> signfields;

  @Data
  static class SignerAccount {
    /** 个人id */
    String signerAccountId;
    /** 组织 */
    String authorizedAccountId;
    /** 页面指定意愿认证方式 */
    WillType[] willTypes;
  }

  @Data
  static class SignField {
    /** 签署方式，个人签署时支持多种签署方式，0-手绘签名 ，1-模板印章签名，多种类型时逗号分割，为空不限制 */
    String sealType = "0";
    /** 文件fileId */
    String fileId;
    /** 是否自动执行签署，默认false */
    Boolean autoExecute;
    /**
     * 企业主体签约类型：0-个人盖章，2-机构盖章；默认是0
     *
     * <p>注：
     *
     * <p>1、签署主体是个人时，无需传入该参数，或者传0
     *
     * <p>2、签署主体是企业时，该字段必传，传入2
     */
    Integer actorIndentityType;

    String[] sealIds;
    String sealId;

    PosBean posBean;

    public SignField(String fileId, String posPage, Double posX, Double posY) {
      this.fileId = fileId;
      this.posBean = new PosBean(posPage, posX, posY);
    }

    @Getter
    @AllArgsConstructor
    static class PosBean {
      /** 页码信息，当签署区signType为2时, 页码可以'-'分割指定页码范围, 传all代表全部页码。其他情况只能是数字 */
      String posPage;
      /** x坐标，坐标为印章中心点 */
      Double posX;
      /** y坐标，坐标为印章中心点 */
      Double posY;
    }
  }
}
