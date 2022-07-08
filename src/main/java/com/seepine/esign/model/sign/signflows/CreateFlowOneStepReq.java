package com.seepine.esign.model.sign.signflows;

import cn.hutool.http.Method;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.seepine.esign.common.enums.WillType;
import com.seepine.esign.common.http.Request;
import com.seepine.esign.model.sign.signflows.entity.Doc;
import com.seepine.esign.model.sign.signflows.entity.FlowInfo;
import com.seepine.esign.model.sign.signflows.entity.Signer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 签署服务API-一步发起签署
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateFlowOneStepReq extends Request {
  FlowInfo flowInfo;

  List<Signer> signers;
  List<Doc> docs;

  public CreateFlowOneStepReq() {}

  public CreateFlowOneStepReq(
      String businessScene, String noticeDeveloperUrl, String fileId, OneStepParams... args) {
    init(businessScene, noticeDeveloperUrl, fileId, args);
  }

  public CreateFlowOneStepReq(String businessScene, String fileId, OneStepParams... args) {
    init(businessScene, null, fileId, args);
  }

  private void init(
      String businessScene, String noticeDeveloperUrl, String fileId, OneStepParams... args) {
    // 1.设置签署文件
    this.docs = Collections.singletonList(new Doc(fileId));
    // 2.设置流程信息
    this.flowInfo = new FlowInfo(businessScene, noticeDeveloperUrl);
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
      if (arg.willTypes != null) {
        signerAccount.setWillTypes(new WillType[] {arg.willTypes});
      }
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
