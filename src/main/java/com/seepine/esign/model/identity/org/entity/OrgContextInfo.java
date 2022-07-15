package com.seepine.esign.model.identity.org.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author seepine
 */
@Getter
@Setter
@ToString
@Builder
public class OrgContextInfo {
  String contextId;
  String notifyUrl;
  String redirectUrl;
  Boolean showResultPage;
}
