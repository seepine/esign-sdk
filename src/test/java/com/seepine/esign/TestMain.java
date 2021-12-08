package com.seepine.esign;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import com.seepine.esign.common.enums.WillType;
import com.seepine.esign.common.exception.DefineException;
import com.seepine.esign.common.http.Response;
import com.seepine.esign.model.sign.account.CreateOrganizationReq;
import com.seepine.esign.model.sign.account.CreateOrganizationRes;
import com.seepine.esign.model.sign.account.CreatePersonReq;
import com.seepine.esign.model.sign.account.CreatePersonRes;
import com.seepine.esign.model.sign.file.GetUploadUrlReq;
import com.seepine.esign.model.sign.file.GetUploadUrlRes;
import com.seepine.esign.model.sign.signflows.CreateFlowOneStepReq;
import com.seepine.esign.model.sign.signflows.CreateFlowOneStepRes;
import com.seepine.esign.model.sign.signflows.OneStepParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestMain {
  public static void main(String[] args) throws DefineException, IOException {
    // 替换成自己的id和secret
    SignTemplate signTemplate =
        new SignTemplate("7438845000", "f79fa7649f748edb647752b158a10000", true);

    // -----------------------个人账号信息用于创建个人账号接口传入-----------------------------
    String thirdPartyUserIdPsn = "123232212121531253"; // thirdPartyUserId参数，用户唯一标识，自定义保持唯一即可
    String namePsn = "孙悟空"; // name参数，姓名
    String idTypePsn = "CRED_PSN_CH_IDCARD"; // idType参数，证件类型
    String idNumberPsn = "350212199511195012"; // idNumber参数，证件号
    String mobilePsn = "13685000000"; // mobile参数，手机号
    System.out.println("------------------ 创建个人账号 start -----------------");
    CreatePersonReq createByThirdPartyUserIdReq =
        new CreatePersonReq(thirdPartyUserIdPsn, namePsn, idTypePsn, idNumberPsn, mobilePsn);

    Response<CreatePersonRes> createByThirdPartyUserIdResResponse =
        signTemplate.execute(createByThirdPartyUserIdReq, CreatePersonRes.class);
    String accountId =
        createByThirdPartyUserIdResResponse.getData().getAccountId(); // 生成的个人账号保存好，后续接口调用需要使用
    System.out.println("accountId:" + accountId);
    System.out.println("------------------ 创建个人账号 end -----------------\n\n");

    System.out.println("------------------ 创建企业账号 start -----------------");
    // ------------------------企业账号信息用于创建机构账号接口传入----------------
    String thirdPartyUserIdOrg = "144632423423125235235235"; //
    //     thirdPartyUserId参数，用户唯一标识，自定义保持唯一即可
    String nameOrg = "福建德尔科技有限公司"; // name参数，机构名称
    String idTypeOrg = "CRED_ORG_USCC"; // idType参数，证件类型
    String idNumberOrg = "9135082339984874XF"; // idNumber参数,机构证件号
    CreateOrganizationReq createOrg =
        new CreateOrganizationReq(thirdPartyUserIdOrg, nameOrg, idTypeOrg, idNumberOrg);
    Response<CreateOrganizationRes> createOrganizationResResponse =
        signTemplate.execute(createOrg, CreateOrganizationRes.class);
    String orgId = createOrganizationResResponse.getData().getOrgId();
    System.out.println("orgId:" + orgId);
    System.out.println("------------------ 创建企业账号 end -----------------\n\n");

    System.out.println("------------------ 通过上传方式创建文件 start -----------------");
    // ------------------------企业账号信息用于创建机构账号接口传入----------------
    FileInputStream fileInputStream = new FileInputStream("/Users/seepine/Documents/自由职业者服务协议.pdf");
    FastByteArrayOutputStream outputStream = IoUtil.read(fileInputStream);
    ByteArrayInputStream is = new ByteArrayInputStream(outputStream.toByteArray());
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    IoUtil.copy(is, os, outputStream.size());

    // 预上传
    GetUploadUrlReq getUploadUrlReq = new GetUploadUrlReq("自由职业者服务协议.pdf", os);
    Response<GetUploadUrlRes> getUploadUrlResResponse =
        signTemplate.execute(getUploadUrlReq, GetUploadUrlRes.class);
    String fileId = getUploadUrlResResponse.getData().getFileId();
    String uploadUrl = getUploadUrlResResponse.getData().getUploadUrl();
    // 真正上传
    boolean uploadSuccess = signTemplate.upload(uploadUrl, os);
    System.out.println("------------------ 通过上传方式创建文件 end -----------------\n\n");

    System.out.println("------------------ 一步签署 start -----------------");
    CreateFlowOneStepReq createFlowOneStepReq =
        new CreateFlowOneStepReq(
            "自由职业者服务协议",
            "cd3bddb8e6be435f9a4798e6383e0000",
            OneStepParams.builder()
                .authorizedAccountId("3f794776740546b9959033eac54d0000")
                .autoExecute(true)
                .posPage("3")
                .posX(220.0)
                .posY(168.0)
                .build(),
            OneStepParams.builder()
                .signerAccountId("2772754dbc2a4282b382ed7787620000")
                .willTypes(WillType.FACE_WE_CHAT_FACE)
                .sealType("0")
                .posPage("3")
                .posX(220.0)
                .posY(168.0)
                .build());
    Response<CreateFlowOneStepRes> createFlowOneStepResResponse =
        signTemplate.execute(createFlowOneStepReq, CreateFlowOneStepRes.class);
    System.out.println("------------------ 一步签署 end -----------------\n\n");
  }
}
