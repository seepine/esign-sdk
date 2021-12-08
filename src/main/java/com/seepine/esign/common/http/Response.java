package com.seepine.esign.common.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** @author seepine */
@Getter
@Setter
@ToString
public class Response<T> {
  private int status;
  private String code;
  private String message;
  private String body;
  private T data;

  public boolean isSuccess() {
    return "0".equals(code);
  }
}
