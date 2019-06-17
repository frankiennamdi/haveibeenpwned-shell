package com.franklin.samples.haveibeenpwned;


import org.jline.utils.AttributedString;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
@ComponentScan("com.franklin.samples.haveibeenpwned")
public class ApplicationShell {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ApplicationShell.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @Bean
  public PromptProvider pawnedPromptProvider() {
    return () -> new AttributedString("pawned:>");
  }
}