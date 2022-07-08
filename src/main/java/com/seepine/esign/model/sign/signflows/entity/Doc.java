package com.seepine.esign.model.sign.signflows.entity;

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
public class Doc {
  String fileId;
  String fileName;
  String fileUrl;

  public Doc(String fileId) {
    this.fileId = fileId;
  }
}
