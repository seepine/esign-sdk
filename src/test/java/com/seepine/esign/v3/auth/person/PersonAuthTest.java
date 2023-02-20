package com.seepine.esign.v3.auth.person;

import com.seepine.esign.common.exception.DefineException;
import com.seepine.esign.common.http.Response;
import com.seepine.esign.v3.SignUtil;
import com.seepine.esign.v3.auth.person.entity.AuthorizeConfig;
import com.seepine.esign.v3.auth.person.entity.PsnAuthConfig;

import java.util.Arrays;

public class PersonAuthTest {
  public static void main(String[] args) throws DefineException {
    PersonAuthReq req = new PersonAuthReq();
    req.setPsnAuthConfig(
        PsnAuthConfig.builder()
            .psnAccount("15080560000")
            .psnAuthPageConfig(
                PsnAuthConfig.PsnAuthPageConfig.builder()
                    .psnEditableFields(Arrays.asList("name", "IDCardNum", "mobile", "bankCardNum"))
                    .build())
            .build());
    req.getPsnAuthConfig().setPsnInfo(PsnAuthConfig.PsnInfo.builder().psnName("张三").build());
    req.setAuthorizeConfig(
        AuthorizeConfig.builder()
            .authorizedScopes(
                Arrays.asList("get_psn_identity_info", "psn_initiate_sign", "manage_psn_resource"))
            .build());
    Response<PersonAuthRes> res = SignUtil.get().execute(req, PersonAuthRes.class);
    System.out.println(res.getData());
  }
}
