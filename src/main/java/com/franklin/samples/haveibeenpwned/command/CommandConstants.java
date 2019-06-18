package com.franklin.samples.haveibeenpwned.command;

/**
 * Holds constants for the Commands
 */
final class CommandConstants {

  private static final String SERVICE_BASE = "https://haveibeenpwned.com/api/v2/";

  static final String BREACHED_ACCOUNT_COMMAND = "breachedaccount";
  static final String BREACHES_COMMAND = "breaches";
  static final String PASTE_ACCOUNT_COMMAND = "pasteaccount";
  static final String BREACH_COMMAND = "breach";

  static final String BREACHED_ACCOUNT_SERVICE = SERVICE_BASE + BREACHED_ACCOUNT_COMMAND;
  static final String BREACHES_SERVICE = SERVICE_BASE + BREACHES_COMMAND;
  static final String PASTE_ACCOUNT_SERVICE = SERVICE_BASE + PASTE_ACCOUNT_COMMAND;
  static final String BREACH_SERVICE = SERVICE_BASE + BREACH_COMMAND;
}
