package com.seepine.esign.interfaces;

import cn.hutool.http.Method;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wraptor
 */
public interface LogOut {

  void out(Req req, Res res);

  @Data
  @AllArgsConstructor
  public static class Req {
    String url;
    Method method;
    Map<String, List<String>> headers;
    String body;
  }

  @Data
  @AllArgsConstructor
  public static class Res {
    int status;
    Map<String, List<String>> headers;
    String body;
  }
}
