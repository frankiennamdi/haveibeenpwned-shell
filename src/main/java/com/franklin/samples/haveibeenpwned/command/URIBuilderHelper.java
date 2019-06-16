package com.franklin.samples.haveibeenpwned.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

class URIBuilderHelper {

  void addBooleanParameter(URIBuilder uriBuilder, String parameterName, boolean value) {
    if (value) {
      uriBuilder.addParameter(parameterName, Boolean.toString(value));
    }
  }

  void addStringParameter(URIBuilder uriBuilder, String parameterName, String value) {
    if (StringUtils.isNotBlank(value)) {
      uriBuilder.addParameter(parameterName, value);
    }
  }
}
