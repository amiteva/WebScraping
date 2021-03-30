package com.webscraping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WebScrapeTest {
    @Test
    @DisplayName("Luka Donćić")
    void test1() {
        assertEquals("2018-19 8.0\n2019-20 9.5\n2020-21 8.2\n", WebScrape.get3PA("Luka Donćić"));
    }

    @Test
    @DisplayName("Player not found")
    void test2() {
        assertEquals("The player is not in our database", WebScrape.get3PA("abcdef"));
    }
}
