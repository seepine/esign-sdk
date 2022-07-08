package com.seepine.esign.common.http;

import cn.hutool.http.Method;

/**
 * @author seepine
 */
public abstract class Request {
  private String url;
  private Method method;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Method getMethod() {
    return method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public abstract void build();
}
