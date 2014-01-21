package de.aitools.ie.stopwords;
/*
 * Copyright (C) 2008 www.webis.de
 */


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;


/**
 * JUnitTest for <tt>de.aitools2.wordprocessing.stopwords.SortedStopWordList</tt>
 * 
 * @author christof.braeutigam@uni-weimar.de
 * @version aitools 3.0
 * 
 *          Created on 21.01.2014
 * 
 *          $Id$
 */
public class SortedStopWordListTest {
	
    /**
     * Test method for
     * {@link de.aitools.ie.wordprocessing.stopwords.StopWordList#contains(java.lang.String)}.
     */
    @Test
    public void testContains() {
    	
        SortedStopWordList swl = new SortedStopWordList(Locale.GERMAN);
        assertTrue(swl.contains("und"));
        assertFalse(swl.contains("plagiatserver"));
        
        swl = new SortedStopWordList(new Locale("es"));
        assertTrue(swl.contains("los"));
        assertFalse(swl.contains("coche"));

        swl = new SortedStopWordList(new Locale("ca"));
        assertTrue(swl.contains("dels"));
        assertFalse(swl.contains("recorregut"));
        
        swl = new SortedStopWordList(new Locale("eu"));
        assertTrue(swl.contains("eta"));
        assertFalse(swl.contains("jaiotzez"));
    }
    
    
    /**
     * Test method for
     * {@link de.aitools.ie.wordprocessing.stopwords.SortedStopWordList#SortedStopWordList(java.util.Locale)} 
     * with supported languages: Basque, Catalan, German, and Spanish.
     */
    @Test
    public void testSortedStopWordListSupportedLanguages() {
    	new SortedStopWordList(new Locale("eu"));
    	new SortedStopWordList(new Locale("ca"));
        new SortedStopWordList(Locale.GERMAN);
        new SortedStopWordList(new Locale("es"));
    }
    
    /**
     * Test method for
     * {@link de.aitools.ie.wordprocessing.stopwords.SortedStopWordList#SortedStopWordList(java.util.Locale, int)} 
     * with supported languages: Basque, Catalan, German, and Spanish.
     */
    @Test
    public void testSortedStopWordListTopN() {
    	SortedStopWordList swl = new SortedStopWordList(Locale.GERMAN, 10);
    	assertTrue(swl.contains("der"));
    	assertTrue(swl.contains("die"));
    	assertTrue(swl.contains("und"));
    	assertTrue(swl.contains("in"));
    	assertTrue(swl.contains("von"));
    	assertTrue(swl.contains("im"));
    	assertTrue(swl.contains("den"));
    	assertTrue(swl.contains("des"));
    	assertTrue(swl.contains("das"));
    	assertTrue(swl.contains("mit"));
    	assertFalse(swl.contains("ist"));
    }
    
    
    /**
     * Test method for
     * {@link de.aitools.ie.wordprocessing.stopwords.SortedStopWordList#SortedStopWordList(java.util.Locale)}
     * with unsupported language.
     */
    @Test(expected = Error.class)
    public void testSortedStopWordListUnsupportedLanguage() {
        new SortedStopWordList(Locale.JAPAN);
    }

}
