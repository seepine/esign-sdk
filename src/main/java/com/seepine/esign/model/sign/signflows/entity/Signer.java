package com.seepine.esign.model.sign.signflows.entity;

import com.seepine.esign.common.enums.WillType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/** @author seepine */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Signer {
  /** 是否平台方自动签署，默认false */
  Boolean platformSign;
  /** 签署顺序，默认1，且不小于1 */
  Integer signOrder;
  /** 签署方账号信息（平台方自动签署时，无需传入该参数） */
  SignerAccount signerAccount;
  /** 签署方的签署区列表数据 */
  List<SignField> signfields;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SignerAccount {
    /** 个人id */
    String signerAccountId;
    /** 组织 */
    String authorizedAccountId;
    /** 页面指定意愿认证方式 */
    WillType[] willTypes;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SignField {
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
    public static class PosBean {
      /** 页码信息，当签署区signType为2时, 页码可以'-'分割指定页码范围, 传all代表全部页码。其他情况只能是数字 */
      String posPage;
      /** x坐标，坐标为印章中心点 */
      Double posX;
      /** y坐标，坐标为印章中心点 */
      Double posY;
    }
  }
}
