package com.github.antoniocaccamo.playerall;

import com.diffplug.common.rx.RxBox;
import com.diffplug.common.swt.Layouts;
import com.diffplug.common.swt.Shells;
import com.diffplug.common.swt.SwtMisc;
import com.diffplug.common.swt.SwtRx;
import com.diffplug.common.swt.jface.ViewerMisc;
import io.micronaut.configuration.picocli.PicocliRunner;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "playerall", description = "...",
        mixinStandardHelpOptions = true)

public class PlayerallCommand implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(PlayerallCommand.class);

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
            Layouts.setGrid(cmp)
                .numColumns(1)
                .columnsEqualWidth(true)
                .horizontalSpacing(0)
                .verticalSpacing(0)
            ;

           Browser browser = new Browser(cmp, SWT.NONE);
           Layouts.setGridData(browser)
                   .grabHorizontal()
                   .grabVertical()
           ;
           browser.setUrl("https://www.google.it");




//           SwtRx.addListener(cmp,SWT.Move )
//                   .subscribe(event ->  logger.info("event : {} cmp size : {}", event, cmp.getShell().getSize()));


           SwtRx.addListener(cmp,SWT.Resize, SWT.Move )
                   .subscribe(event ->  logger.info("event : {} | cmp size : {} location : {}", event, cmp.getSize(),  cmp.getLocation()));



            
       })
        .setTitle("title")
        .setSize(400, 300)
        .setLocation(new Point(22, 22))
        .openOnDisplayBlocking();
    }
}
