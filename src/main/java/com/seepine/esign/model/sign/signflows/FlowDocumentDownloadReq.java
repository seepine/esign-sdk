package com.seepine.esign.model.sign.signflows;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程文档-流程文档下载
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowDocumentDownloadReq extends Request {
  String flowId;

  public FlowDocumentDownloadReq(String flowId) {
    this.flowId = flowId;
  }

  @Override
  public void build() {
    setUrl("/v1/signflows/" + flowId + "/documents");
    setMethod(Method.GET);
  }
}
