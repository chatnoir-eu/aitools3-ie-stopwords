package de.aitools.ie.stopwords;
/*
 * Copyright (C) 2008 www.webis.de
 */


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;


/**
 * JUnitTest for <tt>de.aitools2.wordprocessing.stopwords.StopWordListTest</tt>.
 * 
 * 
 * @author maik.anderka@medien.uni-weimar.de
 * @version aitools 2.0
 * 
 *          Created on 12.08.2008
 * 
 *          $Id$
 */
public class StopWordListTest
{

    /**
     * Test method for {@link de.aitools.ie.wordprocessing.stopwords.StopWordList#contains(java.lang.String)}.
     */
    @Test
    public void testContains()
    {
        StopWordList stopWordList = new StopWordList(Locale.ENGLISH);
        assertTrue(stopWordList.contains("the"));
        assertFalse(stopWordList.contains("tree"));
        
        stopWordList = new StopWordList(Locale.GERMAN);
        assertTrue(stopWordList.contains("und"));
        assertFalse(stopWordList.contains("Plagiatserver"));        
    }



    /**
     * Test method for {@link de.aitools.ie.wordprocessing.stopwords.StopWordList#StopWordList(java.util.Locale)} with supported
     * languages: Dutch, English, Finnish, French, German, Italian, Norwegian, Polish, Portuguese, Spanish, Swedish.
     */
    @Test
    public void testStopWordListSupportedLanguages()
    {
        new StopWordList(new Locale("nl"));
        new StopWordList(Locale.ENGLISH);
        new StopWordList(new Locale("fi"));
        new StopWordList(Locale.FRENCH);
        new StopWordList(Locale.GERMAN);
        new StopWordList(Locale.ITALIAN);
        new StopWordList(new Locale("no"));
        new StopWordList(new Locale("pl"));
        new StopWordList(new Locale("pt"));
        new StopWordList(new Locale("es"));
        new StopWordList(new Locale("se"));
    }



    /**
     * Test method for {@link de.aitools.ie.wordprocessing.stopwords.StopWordList#StopWordList(java.util.Locale)} with unsupported
     * language.
     */
    @Test(expected = Error.class)
    public void testStopWordListUnsupportedLanguage()
    {
        new StopWordList(Locale.JAPAN);
    }

}
