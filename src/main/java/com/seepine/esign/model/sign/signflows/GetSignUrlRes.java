package com.seepine.esign.model.sign.signflows;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author seepine
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetSignUrlRes {
  String url;
  String shortUrl;
}
