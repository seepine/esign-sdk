package com.seepine.esign.model.sign.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** @author seepine */
@Getter
@Setter
@ToString
public class DocTemplatesRes {
  String templateId;
  String templateName;
  String downloadUrl;
  long fileSize;
  String createTime;
  String updateTime;
}
