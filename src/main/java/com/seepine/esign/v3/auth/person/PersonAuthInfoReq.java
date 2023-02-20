package com.seepine.esign.v3.auth.person;

import cn.hutool.http.Method;
import com.seepine.esign.common.http.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询文件上传状态
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonAuthInfoReq extends Request {

  public PersonAuthInfoReq(String psnAccount) {
    setUrl("/v3/persons/identity-info?psnAccount=" + psnAccount);
  }

  @Override
  public void build() {
    super.setMethod(Method.POST);
  }
}
