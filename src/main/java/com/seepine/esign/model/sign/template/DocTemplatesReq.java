package com.seepine.esign.model.sign.template;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** @author seepine */
@Getter
@Setter
@ToString
public class DocTemplatesReq extends Request {
  String templateId;

  public DocTemplatesReq(String templateId) {
    this.templateId = templateId;
  }

  @Override
  public void build() {
    this.setUrl("/v1/docTemplates/" + this.templateId);
    this.setMethod(Method.GET);
  }
}
