package com.seepine.esign.model.sign.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author seepnie
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetFileStatusRes {
  /**
   * 文件状态
   *
   * <p>0-文件未上传；
   *
   * <p>1-文件上传中 ；
   *
   * <p>2-文件上传已完成；
   *
   * <p>3-文件上传失败 ；
   *
   * <p>4-文件等待转pdf ；
   *
   * <p>5-文件已转换pdf ；
   *
   * <p>6-加水印中；
   *
   * <p>7-加水印完毕；
   *
   * <p>8-文件转换中；
   *
   * <p>9-文件转换失败
   */
  Integer status;
}
