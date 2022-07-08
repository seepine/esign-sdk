package com.seepine.esign.model.sign.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author seepnie
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetUploadUrlRes {
  private String fileId;
  private String uploadUrl;
}
