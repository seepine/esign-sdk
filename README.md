# esign-sdk

## 一、引入依赖

```xml
  <dependency> 
    <groupId>com.seepine</groupId>
    <artifactId>esign-sdk</artifactId>
    <version>0.1.0</version>
  </dependency>
```

## 二、实例化

若是spring项目可以注册为bean

```java
SignTemplate signTemplate=new SignTemplate("7438845000","f79fa7649f748edb647752b158a10000",true);
```

## 三、使用

### 1.创建个人账户

```java
    System.out.println("------------------ 创建个人账号 start -----------------");
    CreatePersonReq createByThirdPartyUserIdReq =
        new CreatePersonReq(thirdPartyUserIdPsn, namePsn, idTypePsn, idNumberPsn, mobilePsn);

    Response<CreatePersonRes> createByThirdPartyUserIdResResponse =
        signTemplate.execute(createByThirdPartyUserIdReq, CreatePersonRes.class);
    String accountId =
        createByThirdPartyUserIdResResponse.getData().getAccountId(); // 生成的个人账号保存好，后续接口调用需要使用
    System.out.println("accountId:" + accountId);
    System.out.println("------------------ 创建个人账号 end -----------------\n\n");
```

### 2.创建企业

```java
    CreateOrganizationReq createOrg =
        new CreateOrganizationReq(thirdPartyUserIdOrg, nameOrg, idTypeOrg, idNumberOrg);
    Response<CreateOrganizationRes> createOrganizationResResponse =
        signTemplate.execute(createOrg, CreateOrganizationRes.class);
```

### 3.上传文件

```java
    // 预上传
    GetUploadUrlReq getUploadUrlReq = new GetUploadUrlReq("自由职业者服务协议.pdf", os);
    Response<GetUploadUrlRes> getUploadUrlResResponse =
        signTemplate.execute(getUploadUrlReq, GetUploadUrlRes.class);
    String fileId = getUploadUrlResResponse.getData().getFileId();
    String uploadUrl = getUploadUrlResResponse.getData().getUploadUrl();
    // 真正上传
    boolean uploadSuccess = signTemplate.upload(uploadUrl, os);
```

### 4.发起一步签署

```java
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
```

## 四、自行对接接口

若有sdk中未对接的接口，可以通过提issue或给项目提交pr。当然也可以在自己项目中扩展实现。

下文以[查询文件上传状态](https://open.esign.cn/doc/detail?id=opendoc%2Fsaas_api%2Fdog8oo&namespace=opendoc%2Fsaas_api)接口为例来演示如何自行对接sdk中未实现的接口。

### 1.构建Request

```java
@Getter
@Setter
@ToString
class FileStatusReq extends Request{
    // 根据文档查看请求参数
    String fileId;
    
    public FileStatusReq(String fileId){
        this.fileId = fileId;
    }
    
    // 必须，对照文档设置url和method
    @Override
    public void build() {
      super.setUrl("/v1/files/"+fileId+"/status");
      super.setMethod(Method.GET);
    }
}
```

### 2.构建Response

```java
@Getter
@Setter
@ToString
class FileStatusRes{
    // 根据相应参数data中的结构来构建
    Integer status;
}
```

### 3.发起请求

```java
FileStatusReq req = new FileStatusReq("文件id");
Response<FileStatusRes> fileStatusResResponse =
        signTemplate.execute(req, FileStatusRes.class);
// fileStatusResResponse.getData().getStatus()
```

