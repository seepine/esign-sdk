package com.seepine.esign.model.sign.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** @author seepnie */
@Getter
@Setter
@ToString
public class GetUploadUrlRes {
  private String fileId;
  private String uploadUrl;
}
