package com.seepine.esign.model.sign.account;

import com.seepine.esign.common.http.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** @author seepine */
@Getter
@Setter
@ToString
public class CreatePersonRes extends Response<CreatePersonRes> {
  private String accountId;
}
