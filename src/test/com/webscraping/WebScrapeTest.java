package com.webscraping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WebScrapeTest {
    private final String player1 = "Luka Donćić";
    private final String player1_test = "2018-19 8.0\n2019-20 9.5\n2020-21 8.2\n";
    private final String player2 = "LeBron James";
    private final String player2_test = "2003-04 2.5\n" +
            "2004-05 3.3\n" +
            "2005-06 4.1\n" +
            "2006-07 3.5\n" +
            "2007-08 4.3\n" +
            "2008-09 4.5\n" +
            "2009-10 4.7\n" +
            "2010-11 3.3\n" +
            "2011-12 2.3\n" +
            "2012-13 3.2\n" +
            "2013-14 3.8\n" +
            "2014-15 4.9\n" +
            "2015-16 3.7\n" +
            "2016-17 4.4\n" +
            "2017-18 4.8\n" +
            "2018-19 6.1\n" +
            "2019-20 6.6\n" +
            "2020-21 6.9\n";

    @Test
    @DisplayName(player1)
    void test1() {
        assertEquals(player1_test, WebScrape.get3PA(player1));
    }

    @Test
    @DisplayName(player2)
    void test2() {
        assertEquals(player2_test, WebScrape.get3PA(player2));
    }

    @Test
    @DisplayName("Player not found")
    void test3() {
        assertEquals("The player is not in our database", WebScrape.get3PA("abcdef"));
    }
}
