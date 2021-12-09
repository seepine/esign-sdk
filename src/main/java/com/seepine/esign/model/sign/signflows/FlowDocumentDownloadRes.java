package com.seepine.esign.model.sign.signflows;

import com.seepine.esign.model.sign.signflows.entity.Doc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/** @author seepine */
@Getter
@ToString
@AllArgsConstructor
public class FlowDocumentDownloadRes {
  List<Doc> docs;
}
