package com.seepine.esign.model.sign.file;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.http.Method;
import com.seepine.esign.common.enums.HeaderConstant;
import com.seepine.esign.common.exception.DefineException;
import com.seepine.esign.common.http.Request;
import com.seepine.esign.common.util.Encryption;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.ByteArrayOutputStream;

/**
 * 文件管理API-文件上传
 *
 * @author seepine
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetUploadUrlReq extends Request {
  private String contentMd5;
  private String contentType = HeaderConstant.CONTENTTYPE_PDF.value();
  private boolean convert2Pdf = false;
  private String fileName;
  private Integer fileSize;
  private String accountId;

  public GetUploadUrlReq(String fileName, FastByteArrayOutputStream outputStream)
      throws DefineException {
    this.contentMd5 = Encryption.md5AndBase64(outputStream);
    this.fileName = fileName;
    this.fileSize = outputStream.size();
  }

  public GetUploadUrlReq(String fileName, ByteArrayOutputStream outputStream)
      throws DefineException {
    this.contentMd5 = Encryption.md5AndBase64(outputStream);
    this.fileName = fileName;
    this.fileSize = outputStream.size();
  }

  @Override
  public void build() {
    super.setUrl("/v1/files/getUploadUrl");
    super.setMethod(Method.POST);
  }
}
