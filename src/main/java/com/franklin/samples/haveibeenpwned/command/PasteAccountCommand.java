package com.franklin.samples.haveibeenpwned.command;

import com.franklin.samples.haveibeenpwned.support.HttpSupport;
import com.google.common.base.Joiner;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.net.URISyntaxException;

/**
 * Command for the {@link CommandConstants#PASTE_ACCOUNT_COMMAND}
 */
@ShellComponent
public final class PasteAccountCommand {

  private final HttpSupport httpSupport;

  @Autowired
  public PasteAccountCommand(HttpSupport httpSupport) {
    this.httpSupport = httpSupport;
  }

  @ShellMethod(value = "Get all pastes for an account.", prefix = "-")
  public String pasteAccount(@ShellOption(help = "Email account to search.") String account) {
    try {
      String baseUri = Joiner.on("/").join(CommandConstants.PASTE_ACCOUNT_SERVICE, account);
      URIBuilder uriBuilder = new URIBuilder(baseUri);
      return httpSupport.getResponse(uriBuilder.build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
