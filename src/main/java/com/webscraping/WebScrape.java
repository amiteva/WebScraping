package com.webscraping;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class WebScrape {

    public static String get3PA(String input) {
        String link = "";
        String output = "";

        try {
            //Get the website that shows the results of the search
            Document doc = Jsoup.connect("https://www.basketball-reference.com/search/search.fcgi").data("search", input).get();
            Elements searchresults = doc.getElementsByClass("search-item");

            //If there are 0 results from the search
            if(searchresults.size() == 0) {
                return "The player is not in our database";
            }

            //Go through all of the displayed players
            for(Element result : searchresults) {
                link = result.getElementsByClass("search-item-url").text();
                break; //we always take the first result
            }

            link = "https://www.basketball-reference.com" + link;

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //Connect to the player's website and get the section "Per 36 Minutes"
            Document doc = Jsoup.connect(link).get();
            Element table = doc.getElementById("all_per_minute");

            //Because of the structure of the website, we cannot directly obtain the element that we need (by class or id),
            //so instead, I create a new document that contains only our wanted table and extract the information from there
            Node child = table.childNode(4);
            String s = child.toString();
            String subs = s.substring(6, s.length()-4);
            Document doc1 = Jsoup.parse(subs);
            Element table1 = doc1.getElementById("per_minute");

            Element t = table1.getElementsByTag("tbody").get(0);
            Elements rows =  t.getElementsByTag("tr");

            //Go through all of the rows of the table and print the wanted data
            for(Element row : rows) {
                Elements seasons = row.getElementsByAttributeValue("data-stat","season");
                Elements points = row.getElementsByAttributeValue("data-stat","fg3a_per_mp");
                output = output.concat(seasons.text() + " " + points.text() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); //take the name of the player from standard input
        System.out.print(get3PA(input));
    }
}
