package test.commandLineRunner;

import org.springframework.boot.CommandLineRunner;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/10/26 19:31
 * @version: 1.0
 */
public class CommandLineRunnertTest implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("commandLineRunner");
    }
}
