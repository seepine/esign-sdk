package com.seepine.esign.model.sign.account;

import lombok.Data;

@Data
public class QueryPersonRes {
  String accountId;
  String name;
  String idType;
  String idNumber;
  String thirdPartyUserId;
  String mobile;
  String email;
}
