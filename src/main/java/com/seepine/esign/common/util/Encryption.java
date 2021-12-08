package com.seepine.esign.common.util;

import cn.hutool.core.io.FastByteArrayOutputStream;
import com.seepine.esign.common.exception.DefineException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 请求数据通用处理类
 *
 * @author 澄泓
 */
public class Encryption {

  /** 不允许外部创建实例 */
  private Encryption() {}

  /** 拼接待签名字符串 */
  public static String appendSignDataString(
      String method,
      String accept,
      String contentMd5,
      String contentType,
      String date,
      String headers,
      String url) {
    StringBuilder sb = new StringBuilder();
    sb.append(method)
        .append("\n")
        .append(accept)
        .append("\n")
        .append(contentMd5)
        .append("\n")
        .append(contentType)
        .append("\n")
        .append(date)
        .append("\n");
    if ("".equals(headers)) {
      sb.append(headers).append(url);
    } else {
      sb.append(headers).append("\n").append(url);
    }
    return new String(sb);
  }

  /***
   *  Content-MD5的计算方法
   * @param str 待计算的消息
   * @return MD5计算后摘要值的Base64编码(ContentMD5)
   * @throws DefineException 加密过程中的异常信息
   */
  public static String doContentMd5(String str) throws DefineException {
    byte[] md5Bytes;
    MessageDigest md5;
    String contentMd5;
    try {
      md5 = MessageDigest.getInstance("MD5");
      // 计算md5函数
      md5.update(str.getBytes(StandardCharsets.UTF_8));
      // 获取文件MD5的二进制数组（128位）
      md5Bytes = md5.digest();
      // 把MD5摘要后的二进制数组md5Bytes使用Base64进行编码（而不是对32位的16进制字符串进行编码）
      contentMd5 = new String(Base64.getEncoder().encode(md5Bytes));
      return contentMd5;
    } catch (NoSuchAlgorithmException e) {
      DefineException ex = new DefineException("不支持此算法", e);
      ex.initCause(e);
      throw ex;
    }
  }

  public static String md5AndBase64(FastByteArrayOutputStream os) throws DefineException {
    return new String(Base64.getEncoder().encode(getFileMd5Bytes128(os)));
  }

  public static String md5AndBase64(ByteArrayOutputStream os) throws DefineException {
    return new String(Base64.getEncoder().encode(getFileMd5Bytes128(os)));
  }

  private static byte[] getFileMd5Bytes128(FastByteArrayOutputStream os) throws DefineException {
    int maxLen = 1024;
    try (InputStream fis = new ByteArrayInputStream(os.toByteArray())) {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      byte[] buffer = new byte[fis.available()];
      int len;
      while ((len = fis.read(buffer, 0, maxLen)) != -1) {
        md5.update(buffer, 0, len);
      }
      return md5.digest();
    } catch (Exception e) {
      throw new DefineException("获取文件md5二进制数组失败");
    }
  }

  private static byte[] getFileMd5Bytes128(ByteArrayOutputStream os) throws DefineException {
    int maxLen = 1024;
    try (InputStream fis = new ByteArrayInputStream(os.toByteArray())) {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      byte[] buffer = new byte[fis.available()];
      int len;
      while ((len = fis.read(buffer, 0, maxLen)) != -1) {
        md5.update(buffer, 0, len);
      }
      return md5.digest();
    } catch (Exception e) {
      throw new DefineException("获取文件md5二进制数组失败");
    }
  }
  /***
   * 计算请求签名值-HmacSHA256摘要
   * @param message 待签名字符串
   * @param secret  密钥APP KEY
   * @return HmacSHA256计算后摘要值的Base64编码
   * @throws DefineException 加密过程中的异常信息
   */
  public static String doSignatureBase64(String message, String secret) throws DefineException {
    String algorithm = "HmacSHA256";
    Mac hmacSha256;
    String digestBase64;
    try {
      hmacSha256 = Mac.getInstance(algorithm);
      byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
      byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
      hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm));
      // 使用HmacSHA256对二进制数据消息Bytes计算摘要
      byte[] digestBytes = hmacSha256.doFinal(messageBytes);
      // 把摘要后的结果digestBytes使用Base64进行编码
      digestBase64 = new String(Base64.getEncoder().encode(digestBytes));
    } catch (NoSuchAlgorithmException e) {
      DefineException ex = new DefineException("不支持此算法", e);
      ex.initCause(e);
      throw ex;
    } catch (InvalidKeyException e) {
      DefineException ex = new DefineException("无效的密钥规范", e);
      ex.initCause(e);
      throw ex;
    }
    return digestBase64;
  }

  /** 获取时间戳 */
  public static String timeStamp() {
    long timeStamp = System.currentTimeMillis();
    return String.valueOf(timeStamp);
  }
}
