package com.seepine.esign.model.sign.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author seepine
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DocTemplatesRes {
  String templateId;
  String templateName;
  String downloadUrl;
  long fileSize;
  String createTime;
  String updateTime;
}
