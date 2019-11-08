package com.github.antoniocaccamo.playerall;

import com.diffplug.common.swt.Layouts;
import com.diffplug.common.swt.Shells;
import com.diffplug.common.swt.SwtRx;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import io.reactivex.Observable;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@OpenAPIDefinition(
        info = @Info(
                title = "Books Catalogue",
                version = "1.0",
                description = "Books Catalogue"
        )
)
@Command(name = "playerall", description = "...",
        mixinStandardHelpOptions = true)

public class PlayerallCommand implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(PlayerallCommand.class);

    private static ApplicationContext context;

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        context = Micronaut.run(PlayerallCommand.class, args);
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
            browser.setUrl("http://localhost:8080/books");


//           SwtRx.addListener(cmp,SWT.Move )
//                   .subscribe(event ->  logger.info("event : {} cmp size : {}", event, cmp.getShell().getSize()));


            SwtRx.addListener(cmp, SWT.Resize, SWT.Move)
                    .subscribe(event -> logger.info("event : {} | cmp size : {} location : {}", event, cmp.getSize(), cmp.getLocation()));


            Observable.just(1L, 2L)
                    .map(aLong -> aLong)
                    //.observeOn(Schedulers.computation())
                    //.subscribeOn(SwtExec.swtOnly().getRxExecutor().scheduler() )
                    .subscribe(l -> Shells.builder(SWT.RESIZE , bcmp -> {
                                Layouts.setGrid(bcmp)
                                        .numColumns(1)
                                        .columnsEqualWidth(true)
                                        .horizontalSpacing(0)
                                        .verticalSpacing(0)
                                ;

                                Browser b = new Browser(bcmp, SWT.NONE);
                                Layouts.setGridData(b)
                                        .grabHorizontal()
                                        .grabVertical()
                                ;
                                b.setUrl("http://localhost:8080/books");
                                SwtRx.addListener(bcmp, SWT.Resize, SWT.Move)
                                        .subscribe(event -> logger.info("bcmp[{}] size : {} location : {}", l, bcmp.getSize(), bcmp.getLocation()));
                            }).setTitle(l.toString())
                              .setSize(400, 300)
                              .setLocation(new Point(22 + (400 * l.intValue()), 22))
                              .openOn(cmp.getShell())
                            , Throwable::printStackTrace
                    );
        })
                .setTitle("title")
                .setSize(400, 300)
                .setLocation(new Point(22, 22))
                .openOnDisplayBlocking();

        logger.info("closing...");
        context.stop();
        context.close();

    }
}
