package com.franklin.samples.haveibeenpwned.command;

import com.franklin.samples.haveibeenpwned.support.HttpSupport;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.net.URISyntaxException;

/**
 * command handler for {@link CommandConstants#BREACHES_COMMAND}
 */
@ShellComponent
public final class BreachesCommand {


  private final HttpSupport httpSupport;

  @Autowired
  public BreachesCommand(HttpSupport httpSupport) {
    this.httpSupport = httpSupport;
  }

  @ShellMethod(value = "Find all breaches", prefix = "-")
  public String breaches(@ShellOption(help = "domain to filter", defaultValue = "") String domain) {
    try {
      URIBuilder uriBuilder = new URIBuilder(CommandConstants.BREACHES_SERVICE);
      URIBuilderHelper uriBuilderHelper = new URIBuilderHelper();
      uriBuilderHelper.addStringParameter(uriBuilder, "domain", domain);
      return httpSupport.getResponse(uriBuilder.build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
