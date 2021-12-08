package com.seepine.esign.common.exception;

/**
 * description 自定义全局异常
 *
 * @author 宫清 datetime 2019年7月1日上午10:43:24
 */
public class DefineException extends Exception {

  private static final long serialVersionUID = 4359180081622082792L;
  private Exception e;

  public DefineException(String msg) {
    this.e = new Exception(msg);
  }

  public DefineException(String msg, Throwable cause) {
    this.e = new Exception(msg, cause);
  }

  public DefineException() {}

  public Exception getE() {
    return e;
  }

  public void setE(Exception e) {
    this.e = e;
  }
}
