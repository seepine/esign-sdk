package com.seepine.esign.model.identity.common;

import lombok.Data;

/**
 * @author seepine
 */
@Data
public class IndivInfo {
  String accountId;
  String name;
  String certNo;
  String certType;
  String nationality;
  String mobileNo;
  String bankCardNo;
  String facePhotoUrl;
  String facePhotoAllUrl;
  String similarity;
  String livingScore;
}
