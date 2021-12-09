package com.seepine.esign.model.sign.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/** @author seepnie */
@Getter
@ToString
@AllArgsConstructor
public class GetUploadUrlRes {
  private String fileId;
  private String uploadUrl;
}
