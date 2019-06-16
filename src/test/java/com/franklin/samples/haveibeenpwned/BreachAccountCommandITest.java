package com.franklin.samples.haveibeenpwned;

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
public class BreachAccountCommandITest {

  @Autowired
  private Shell shell;


  @Test
  public void testBreach() {
    String command = "breached-account -account test@example.com -domain adobe.com -truncate-response -include-unverified";
    Object output = shell.evaluate(() -> command);
    assertThat(output, hasJsonPath("$.[0].Name", equalTo("Adobe")));
  }
}
