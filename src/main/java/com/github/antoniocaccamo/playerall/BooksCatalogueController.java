package com.github.antoniocaccamo.playerall;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;


@Controller("/books")
@Slf4j
public class BooksCatalogueController {

    @Get("/")
    List<Book> index() {

        log.info("incoming requests...");

        Book buildingMicroservices =
                new Book("1491950358", "Building Microservices");
        Book releaseIt =
                new Book("1680502395", "Release It!");
        Book cidelivery =
                new Book("0321601912", "Continuous Delivery:");

        List list = Arrays.asList(buildingMicroservices, releaseIt, cidelivery);

        log.info("books list {}",list);

        return list;
    }

}
