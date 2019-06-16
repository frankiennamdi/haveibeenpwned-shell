package com.franklin.samples.haveibeenpwned;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=" + false,
        "spring.main.banner-mode=off"})
public class PasteAccountCommandITest {

  @Autowired
  private Shell shell;


  @Test
  public void testPasteAccount() {
    String command = "paste-account -account test@example.com";
    Object output = shell.evaluate(() -> command);
    assertThat(output, hasJsonPath("$.[0].Id", notNullValue()));
    assertThat(output, hasJsonPath("$.[0].Source", notNullValue()));
  }

  @Test
  public void testPasteAccount_withNonEmailAccount_AndExpectBadRequestCode() {
    String command = "paste-account -account adobe";
    Object output = shell.evaluate(() -> command);
    assertThat(output, hasJsonPath("$.statusCode", equalTo(HttpStatus.SC_BAD_REQUEST)));
    assertThat(output, hasJsonPath("$.message", containsString("Invalid email address")));
  }
}
