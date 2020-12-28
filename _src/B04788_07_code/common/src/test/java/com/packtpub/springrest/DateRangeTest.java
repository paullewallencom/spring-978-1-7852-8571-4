package com.packtpub.springrest;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateRangeTest {

    @Test
    public void testNewDateRange() {
        try {
            new DateRange(null, null);
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new DateRange(new Date(), null);
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new DateRange(null, new Date());
            fail();
        } catch (IllegalArgumentException e) { }
        // from must be after until
        try {
            new DateRange(new Date(System.currentTimeMillis() + 5*1000), new Date());
            fail();
        } catch (IllegalArgumentException e) { }

    }

    @Test
    public void testIsBeforeNow() throws Exception {
        assertTrue(new DateRange(new Date(System.currentTimeMillis() - 25 * 60 * 60 * 1000), new Date(System.currentTimeMillis() - 10 * 60 * 1000)).isBeforeNow());
        assertFalse(new DateRange(new Date(), new Date()).isBeforeNow());
    }
}