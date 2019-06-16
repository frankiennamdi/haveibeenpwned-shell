package com.franklin.samples.haveibeenpwned.command;

/**
 * Hold properties of filters that can be applied to Commands
 */
public interface CommandFilter {

  String filterKey();

  String filterKeyDescription();

  Class<?> filterType();
}
