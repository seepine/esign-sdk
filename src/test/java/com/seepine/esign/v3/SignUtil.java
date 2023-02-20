package com.seepine.esign.v3;

import com.seepine.esign.SignTemplate;

public class SignUtil {
  private static final SignTemplate SIGN =
      // 替换成自己的id和secret
      new SignTemplate("7438922000", "bfd6bc0e3a4f257b449c6bfc00000000", true);

  static {
    SIGN.logOut(
        (req, res) -> {
          System.out.println(req);
          System.out.println(res);
        });
  }

  public static SignTemplate get() {
    return SIGN;
  }
}
