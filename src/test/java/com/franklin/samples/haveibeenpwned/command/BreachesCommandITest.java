package com.franklin.samples.haveibeenpwned.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=" + false,
        "spring.main.banner-mode=off"})
public class BreachesCommandITest {

  @Autowired
  private Shell shell;

  @Test
  public void testBreaches_WithDomainSpecified() {
    String command = "breaches -domain adobe.com";
    Object output = shell.evaluate(() -> command);
    assertThat(output, hasJsonPath("$.[0].Name", equalTo("Adobe")));
  }

  @Test
  public void testBreaches_WithNoDomainSpecified() {
    Object output = shell.evaluate(() -> "breaches");
    assertThat(output, hasJsonPath("$.[0].Name"));
    assertThat(output, hasJsonPath("$.[0].Title"));
  }

}
