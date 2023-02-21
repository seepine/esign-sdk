package com.seepine.esign.model.sign.signflows;

import cn.hutool.http.Method;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** 获取签署地址 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetSignUrlReq extends Request {
  @JsonIgnore String flowId;
  @JsonIgnore String accountId;
  @JsonIgnore String organizeId;

  public GetSignUrlReq(String flowId, String accountId, String organizeId) {
    this.flowId = flowId;
    this.accountId = accountId;
    this.organizeId = organizeId;
  }

  @Override
  public void build() {
    String url = "/v1/signflows/" + flowId + "/executeUrl?accountId=" + accountId;
    if (organizeId != null) {
      url += "&organizeId=" + organizeId;
    }
    setUrl(url);
    setMethod(Method.GET);
  }
}
