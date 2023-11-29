package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder regex = new StringBuilder("[");
        Iterator<String> iterator = delimiters.iterator();
        regex.append(iterator.next());

        while(iterator.hasNext()) {
            //regex.append("|");
            regex.append(iterator.next());
        }

        regex.append("]+");
        while (source.substring(0, 1).matches(regex.toString())){
            source = source.substring(1);
        }

        String[] splitString = source.split(regex.toString());


        return new ArrayList<>(Arrays.asList(splitString));
    }
}
