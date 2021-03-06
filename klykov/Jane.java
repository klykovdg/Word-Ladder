package klykov;

import java.io.*;
import java.net.*;
import org.jsoup.*;
import  org.jsoup.nodes.*;
import java.util.*;
import java.util.regex.*;

/**
 * Class has a package access as its static method performs a particular task needed for the class Main only.
 * The class uses Jsoup library. Its jar file you can find in the folder "resources".
 */
class Jane {
    /**
     * The method looks for the words and returns the Set of them.
     * @param numLetters The number of letters in a word
     * @return Returns the Set of the words with particular number of letters
     */
    static Set<String> getWords(int numLetters) {
        Set<String> rowSet = new HashSet<>();
        String html = getStringHTML("https://github.com/klykovdg/Word-Ladder/blob/master/resources/charlotte_bronte_jane_eyre.txt");
        Document doc = Jsoup.parse(html);
        int line = 1;
        Element e;
        Pattern p = Pattern.compile("[a-zA-Z]{" + numLetters + "}");
        String[] array;

        while ((e = doc.getElementById("LC" + line++)) != null) {
            array = e.text().split("\\W");
            for (String s : array) {
                if (p.matcher(s).matches()) {
                    rowSet.add(s);
                }
            }
        }
        return rowSet;
    }

    private Jane() {}

    /**
     * The method receives the book url and reads the book.
     * @return Returns a string containing the book as html.
     */
    private static String getStringHTML(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            URL urlBook = new URL(url);
            BufferedReader buff = new BufferedReader(new InputStreamReader(urlBook.openStream()));
            String s;
            while ((s = buff.readLine()) != null) {
                sb.append(s).append("\n");
            }
            buff.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
