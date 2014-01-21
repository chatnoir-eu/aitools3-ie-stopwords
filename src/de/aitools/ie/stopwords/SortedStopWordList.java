package de.aitools.ie.stopwords;
/*
 * Copyright (C) 2008 www.webis.de
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Locale;


/**
 * This class provides the functionality to check if a word is within a
 * predefined stop word list.
 * <p>
 * Stop word list are currently available for the following languages:
 * Basque, Catalan, German, Spanish.
 * 
 * 
 * @author maik.anderka@medien.uni-weimar.de
 * @author christof.braeutigam@uni-weimar.de
 * @version aitools 3.0
 * 
 *          Created on 21.01.2014
 * 
 *          $Id$
 */
public class SortedStopWordList {
	
    private static final String STOP_WORD_LIST_FILENAME_SUFFIX
    	= ".wikipedia.sw.top100.txt";
    private final HashSet<String> STOP_WORDS;

    /**
     * Constructs a <tt>StopWordList</tt> for the specified language.
     * 
     * @param language
     *            Language.
     * @throws FileNotFoundException
     *             If no stop word list exists for the specified language.
     * @throws IllegalArgumentException
     *             If <code>language == null</code>.
     */
    public SortedStopWordList(Locale language) {
        this(language, 50);
    }
    
    public SortedStopWordList(Locale language, int topn) {
    	try {
            this.STOP_WORDS = SortedStopWordList.loadStopWordList(language, topn);
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
    public boolean contains(String word) {
        return STOP_WORDS.size() == 0 ? false : 
        	   this.STOP_WORDS.contains(word.toLowerCase());
    }
    
    
    public String[] getStopWordList() {
        String[] stopWords = new String[STOP_WORDS.size()];
        this.STOP_WORDS.toArray(stopWords);
        return stopWords;
    }
    
    
    /* Loads the stop word list for the specified language. */
    private static HashSet<String> loadStopWordList(Locale locale, int n)
    		throws FileNotFoundException {
        File packagePath = new File(
        		SortedStopWordList.class.getPackage().getName().replace('.', '/'),
        		"sortedstopwordlists");
        File stopWordListFile = new File(packagePath,
        		locale.getLanguage() + STOP_WORD_LIST_FILENAME_SUFFIX);
        InputStream inputStream = SortedStopWordList.class.getClassLoader()
        		.getResourceAsStream(stopWordListFile.toString());
        if (inputStream == null) {
        	throw new FileNotFoundException(stopWordListFile.toString());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        
        HashSet<String> stopWords = new HashSet<String>(n);
        try {
        	int i;
        	String line;
        	String[] parts;
            for (line = br.readLine(), i = 0;
                 line != null && i < n;
            	 line = br.readLine(), ++i) {
            	parts = line.split("\t");  // word\tcount
                stopWords.add(parts[0]);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stopWords;
    }
}
