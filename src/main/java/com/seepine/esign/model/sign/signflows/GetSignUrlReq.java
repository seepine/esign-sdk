package com.seepine.esign.model.sign.signflows;

import cn.hutool.http.Method;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** 获取签署地址 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetSignUrlReq extends Request {
  String flowId;

  public GetSignUrlReq(String flowId) {
    this.flowId = flowId;
  }

  @Override
  public void build() {
    setUrl("/v1/signflows/" + flowId + "/executeUrl");
    setMethod(Method.GET);
  }
}
