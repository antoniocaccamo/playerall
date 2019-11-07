package com.github.antoniocaccamo.playerall;

import com.diffplug.common.swt.Coat;
import com.diffplug.common.swt.Shells;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "playerall", description = "...",
        mixinStandardHelpOptions = true)
public class PlayerallCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(PlayerallCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }

       Shells.builder(SWT.RESIZE | SWT.ICON | SWT.CLOSE, cmp -> {

       })
        .setTitle("title")
        .setSize(400, 300)
        .setLocation(new Point(22, 22))
        .openOnDisplayBlocking();
    }
}
