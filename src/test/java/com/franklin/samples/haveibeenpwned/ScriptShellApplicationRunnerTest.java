package com.franklin.samples.haveibeenpwned;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.shell.Shell;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class ScriptShellApplicationRunnerTest {

  @Test
  public void testRun() throws IOException {
    Shell mockShell = mock(Shell.class);
    ConfigurableEnvironment environment = mock(ConfigurableEnvironment.class);
    when(environment.getPropertySources()).thenReturn(mock(MutablePropertySources.class));
    ScriptShellApplicationRunner scriptShellApplicationRunner = new ScriptShellApplicationRunner(mockShell, environment);
    ArgumentCaptor<ScriptShellApplicationRunner.StringInputProvider> stringInputProvider =
            ArgumentCaptor.forClass(ScriptShellApplicationRunner.StringInputProvider.class);
    String command = "breach -name adobe";
    scriptShellApplicationRunner.run("-c", command);
    verify(mockShell).run(stringInputProvider.capture());
    assertThat(stringInputProvider.getValue().getCommand(), is(command));
  }
}
