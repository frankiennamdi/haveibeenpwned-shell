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
 * handler for the {@link CommandConstants#BREACHED_ACCOUNT_COMMAND}
 */
@ShellComponent
public final class BreachedAccountCommand {

  private final HttpSupport httpSupport;

  @Autowired
  public BreachedAccountCommand(HttpSupport httpSupport) {
    this.httpSupport = httpSupport;
  }

  @ShellMethod(value = "Return a list of all breaches a particular account has been involved in", prefix = "-")
  public String breachedAccount(
          @ShellOption(help = "name of the account") String account,
          @ShellOption(help = "Returns only the name of the breach") boolean truncateResponse,
          @ShellOption(help = "Filters the result set to only breaches against the domain specified", defaultValue = "") String domain,
          @ShellOption(help = "Returns breaches that have been flagged as \"unverified\"") boolean includeUnverified) {
    try {
      String baseUri = Joiner.on("/").join(CommandConstants.BREACHED_ACCOUNT_SERVICE, account);

      URIBuilder uriBuilder = new URIBuilder(baseUri);
      URIBuilderHelper uriBuilderHelper = new URIBuilderHelper();

      uriBuilderHelper.addBooleanParameter(uriBuilder, "truncateResponse", truncateResponse);
      uriBuilderHelper.addStringParameter(uriBuilder, "domain", domain);
      uriBuilderHelper.addBooleanParameter(uriBuilder, "includeUnverified", includeUnverified);

      return httpSupport.getResponse(uriBuilder.build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }


}
