package com.seepine.esign.model.sign.signflows;

import com.seepine.esign.common.enums.WillType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** @author seepine */
@Getter
@Setter
@ToString
@Builder
public class OneStepParams {
  /** 个人id */
  String signerAccountId;
  /** 组织 */
  String authorizedAccountId;
  /** 签署方式，个人签署时支持多种签署方式，0-手绘签名 ，1-模板印章签名，多种类型时逗号分割，为空不限制 */
  String sealType;
  /** 页码信息，当签署区signType为2时, 页码可以'-'分割指定页码范围, 传all代表全部页码。其他情况只能是数字 */
  String posPage;
  /** x坐标，坐标为印章中心点 */
  Double posX;
  /** y坐标，坐标为印章中心点 */
  Double posY;
  /** 是否自动执行签署，默认false */
  Boolean autoExecute;
  /** 页面指定意愿认证方式 */
  WillType willTypes;
}
