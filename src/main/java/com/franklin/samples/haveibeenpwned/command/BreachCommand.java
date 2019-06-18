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
 * command for the {@link CommandConstants#BREACH_COMMAND}
 */
@ShellComponent
public final class BreachCommand {

  private final HttpSupport httpSupport;

  @Autowired
  public BreachCommand(HttpSupport httpSupport) {
    this.httpSupport = httpSupport;
  }

  @ShellMethod(value = "Get a single breached site.", prefix = "-")
  public String breach(@ShellOption(help = "Name of the breach.") String name) {
    try {
      String baseUri = Joiner.on("/").join(CommandConstants.BREACH_SERVICE, name);
      URIBuilder uriBuilder = new URIBuilder(baseUri);
      return httpSupport.getResponse(uriBuilder.build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
