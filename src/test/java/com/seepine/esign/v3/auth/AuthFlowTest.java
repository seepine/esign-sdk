package com.seepine.esign.v3.auth;

import com.seepine.esign.common.exception.DefineException;
import com.seepine.esign.common.http.Response;
import com.seepine.esign.v3.SignUtil;

public class AuthFlowTest {
  public static void main(String[] args) throws DefineException {
    Response<AuthFlowRes> res =
        SignUtil.get().execute(new AuthFlowReq("OF-25c083789b080009"), AuthFlowRes.class);
    System.out.println(res.getBody());
    System.out.println(res.getData());
  }
}
