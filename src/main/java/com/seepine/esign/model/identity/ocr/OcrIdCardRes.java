package com.seepine.esign.model.identity.ocr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 身份证OCR
 *
 * @author seepine 2022.1.28
 */
@Getter
@ToString
@AllArgsConstructor
public class OcrIdCardRes {
  /** 业务Id */
  String verifyId;
  /** 姓名 */
  String name;
  /** 身份证号 */
  String idNo;
  /** 性别 */
  String gender;
  /** 出生日期 */
  String birthDay;
  /** 民族 */
  String nation;
  /** 住址 */
  String address;
  /** 有效期限，YYYY.MM.DD-YYYY.MM.DD 或 YYYY.MM.DD-长期 */
  String validityPeriod;
  /** 发证机关 */
  String issuedBy;
}
