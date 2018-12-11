package com.zyh.learn.zyhlearn.commandLineRunnerTest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/10/26 19:31
 * @version: 1.0
 */
@Component
public class CommandLineRunnertTest implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    System.out.println("commandLineRunner");
  }
}
