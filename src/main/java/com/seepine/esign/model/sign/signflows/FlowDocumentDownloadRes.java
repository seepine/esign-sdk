package com.seepine.esign.model.sign.signflows;

import com.seepine.esign.model.sign.signflows.entity.Doc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author seepine
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FlowDocumentDownloadRes {
  List<Doc> docs;
}
