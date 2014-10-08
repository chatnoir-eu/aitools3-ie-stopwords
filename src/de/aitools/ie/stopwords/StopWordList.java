package de.aitools.ie.stopwords;
/*
 * Copyright (C) 2008 www.webis.de
 */


import java.io.*;
import java.util.HashSet;
import java.util.Locale;


/**
 * This class provides the functionality to check if a word is within a predefined stop word list.
 * <p>
 * Stop word list are available for the following languages: Dutch, English, Finnish, French, German, Italian,
 * Norwegian, Polish, Portuguese, Spanish, Swedish.
 * 
 * 
 * @author maik.anderka@medien.uni-weimar.de
 * @version aitools 2.0
 * 
 *          Created on 11.08.2008
 * 
 *          $Id$
 */
public class StopWordList
{
    private static final String   STOP_WORD_LIST_FILENAME_SUFFIX   = "StopWordList.txt";
    private static final int      DEFAULT_INITIAL_HASHSET_CAPACITY = 1 << 10;           // 1024.
    private final HashSet<String> STOP_WORDS;

    public StopWordList()
    {
    	STOP_WORDS = new HashSet<>(0);
    }

    /**
     * Constructs a <tt>StopWordList</tt> for the specified language.
     * 
     * @param language
     *            Language.
     *             If no stop word list exists for the specified language.
     * @throws IllegalArgumentException
     *             If <code>language == null</code>.
     */
    public StopWordList(Locale language)
    {
        try {
            this.STOP_WORDS = StopWordList.loadStopWordList(language);
        } catch (FileNotFoundException e) {
            throw new Error(e);
        }
    }



    /**
     * Returns <tt>true</tt> if this stop word list contains the specified word.
     * 
     * @param word
     *            Word whose presence in this stop word list is to be tested.
     * @return <tt>true</tt> if this stop word list contains the specified word.
     */
    public boolean contains(String word)
    {
        return STOP_WORDS.size() != 0 && this.STOP_WORDS.contains(word.toLowerCase());
    }

    /**
     * Returns <tt>true</tt> if one of the words in the given array is a stop word
     * @param words array with words. Might be a n-gram
     * @return <tt>true</tt> if this stop word list contains one of the words in the array
     */
    public boolean contains(String[] words) {
        for(String word : words) {
            if(contains(word)) return true;
        }
        return false;
    }



    public String[] getStopWordList()
    {
        String[] stopWords = new String[STOP_WORDS.size()];
        this.STOP_WORDS.toArray(stopWords);
        return stopWords;
    }



    /* Loads the stop word list for the specified language. */
    private static HashSet<String> loadStopWordList(Locale locale) throws FileNotFoundException
    {
        File packagePath = new File(StopWordList.class.getPackage().getName().replace('.', '/'), "stopwordlists");
        File stopWordListFile = new File(packagePath, locale.getLanguage() + StopWordList.STOP_WORD_LIST_FILENAME_SUFFIX);
        InputStream inputStream = StopWordList.class.getClassLoader().getResourceAsStream(stopWordListFile.toString());
        if (inputStream == null)
            throw new FileNotFoundException(stopWordListFile.toString());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        HashSet<String> stopWords = new HashSet<>(StopWordList.DEFAULT_INITIAL_HASHSET_CAPACITY);
        try {
            for (String word = bufferedReader.readLine(); word != null; word = bufferedReader.readLine()) {
                stopWords.add(word);
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stopWords;
    }
}
