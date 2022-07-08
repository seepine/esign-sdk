package com.seepine.esign.model.sign.file;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 查询文件上传状态
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetFileStatusReq extends Request {
  String fileId;

  public GetFileStatusReq(String fileId) {
    this.fileId = fileId;
  }

  @Override
  public void build() {
    super.setUrl("/v1/files/" + fileId + "/status");
    super.setMethod(Method.GET);
  }
}
