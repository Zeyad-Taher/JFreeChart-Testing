package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimePeriodFormatException;
import org.jfree.data.time.Year;
import org.junit.Test;

public class QuarterClassTest {
    Quarter quarter;

    private void arrange(Integer quart, Integer year) {
        quarter = new Quarter(quart, year);
    }

    private void arrange() {
        quarter = new Quarter();
    }

    private void arrange(Date date) {
        quarter = new Quarter(date);
    }

    private void arrange(Date date, TimeZone timeZone) {
        quarter = new Quarter(date, timeZone);
    }

    private void arrange(Integer quart, Year year) {
        quarter = new Quarter(quart, year);
    }

    // Constructors Tests

    @Test
    public void testQuarterDefaultCtor() {
        arrange();
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testQuarterDateCtor() {
        Date time = new Date(1681509600000L); // Date: 15/4/2023

        // create a Quarter object using the constructor
        arrange(time);

        // assert that the quarter has the correct year and quarter number
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testQuarterDateCtorBelow1900() {
        Date time = new Date(-2209169109000L); // Date: 30/12/1899

        // create a Quarter object using the constructor
        arrange(time);

        // assert that the quarter has the correct year and quarter number
        assertEquals(1899, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    @Test
    public void testQuarterDateAndTimeZoneCtor() {
        Date date = new Date(1664697600000L); // Date: 1/10/2022

        // create a time zone object for GMT
        TimeZone timeZone = TimeZone.getTimeZone("GMT");

        // create a Quarter object using the constructor
        arrange(date, timeZone);

        // assert that the quarter has the correct year and quarter number
        assertEquals(2022, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testQuarterDateAndTimeZoneCtorBelow1900() {
        Date date = new Date(-2209169109000L); // Date: 30/12/1899

        // create a time zone object for GMT
        TimeZone timeZone = TimeZone.getTimeZone("GMT");

        // create a Quarter object using the constructor
        arrange(date, timeZone);

        // assert that the quarter has the correct year and quarter number
        assertEquals(1899, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor4() {
        arrange(1, 2023);
        assertEquals(1, quarter.getQuarter());
        assertEquals(2023, quarter.getYear().getYear());
    }

    @Test
    public void testQuarterCtor4LessThanOne() {
        arrange(0, 1900);
        assertNotEquals(0, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor4MoreThanFour() {
        arrange(5, 1900);
        assertNotEquals(5, quarter.getQuarter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtor4LessThan1999() {
        arrange(2, 1899);
        assertNotEquals(1899, quarter.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtor4MoreThan9999() {
        arrange(3, 10000);
        assertNotEquals(10000, quarter.getYear().getYear());
    }

    @Test
    public void testQuarterCtor5() {
        Year year = new Year();
        arrange(1, year);
        assertEquals(1, quarter.getQuarter());
        assertEquals(year.getYear(), quarter.getYear().getYear());
    }

    @Test
    public void testQuarterCtor5LessThanOne() {
        Year year = new Year();
        arrange(0, year);
        assertEquals(0, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor5MoreThanFour() {
        Year year = new Year();
        arrange(5, year);
        assertEquals(5, quarter.getQuarter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtor5LessThan1999() {
        Year year = new Year(1899);
        arrange(2, year);
        assertEquals(1899, quarter.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtor5MoreThan9999() {
        Year year = new Year(10000);
        arrange(3, year);
        assertEquals(10000, quarter.getYear().getYear());
    }

    // Methods Tests

    @Test
    public void testQuarterCompareToLessThanEqualYears() { // quarter 1 < quarter 2
        // create a Quarter object for the second quarter of 2022
        Quarter q1 = new Quarter(2, 2022);

        // create a Quarter object for the third quarter of 2022
        Quarter q2 = new Quarter(3, 2022);

        // assert that q1 is less than q2
        assertTrue(q1.compareTo(q2) < 0);
    }

    @Test
    public void testQuarterCompareToLessThanDifferentYears() { // greater year with greater quarter
        // create a Quarter object for the second quarter of 2022
        Quarter q1 = new Quarter(2, 2022);

        // create a Quarter object for the third quarter of 2023
        Quarter q2 = new Quarter(3, 2023);

        // assert that q1 is less than q2
        assertTrue(q1.compareTo(q2) < 0);
    }

    @Test
    public void testQuarterCompareToLessThanDifferentYears2() { // greater year with less quarter
        // create a Quarter object for the second quarter of 2022
        Quarter q1 = new Quarter(2, 2022);

        // create a Quarter object for the first quarter of 2023
        Quarter q2 = new Quarter(1, 2023);

        // assert that q1 is less than q2
        assertTrue(q1.compareTo(q2) < 0);
    }

    @Test
    public void testQuarterCompareToGreaterThanEqualYears() { // quarter 1 < quarter 2
        // create a Quarter object for the second quarter of 2022
        Quarter q1 = new Quarter(2, 2022);

        // create a Quarter object for the third quarter of 2022
        Quarter q2 = new Quarter(3, 2022);

        // assert that q1 is less than q2
        assertTrue(q2.compareTo(q1) > 0);
    }

    @Test
    public void testQuarterCompareToGreaterThanDifferentYears1() { // greater year with greater quarter
        // create a Quarter object for the second quarter of 2022
        Quarter q1 = new Quarter(2, 2022);

        // create a Quarter object for the third quarter of 2023
        Quarter q2 = new Quarter(3, 2023);

        // assert that q1 is less than q2
        assertTrue(q2.compareTo(q1) > 0);
    }

    @Test
    public void testQuarterCompareToGreaterThanDifferentYears2() { // greater year with less quarter
        // create a Quarter object for the second quarter of 2022
        Quarter q1 = new Quarter(2, 2022);

        // create a Quarter object for the first quarter of 2023
        Quarter q2 = new Quarter(1, 2023);

        // assert that q1 is less than q2
        assertTrue(q2.compareTo(q1) > 0);
    }

    @Test
    public void testQuarterCompareToEqualToEqualYears() {
        // create a Quarter object for the second quarter of 2022
        Quarter q1 = new Quarter(2, 2022);

        // create another Quarter object for the second quarter of 2022
        Quarter q2 = new Quarter(2, 2022);

        // assert that q1 is equal to q2
        assertEquals(0, q1.compareTo(q2));
    }

    @Test
    public void testQuarterCompareToEqualToDifferentYears() {
        // create a Quarter object for the second quarter of 2022
        Quarter q1 = new Quarter(2, 2022);

        // create another Quarter object for the second quarter of 2023
        Quarter q2 = new Quarter(2, 2023);

        // assert that q1 is equal to q2
        assertNotEquals(0, q1.compareTo(q2));
    }

    @Test
    public void testQuarterEqualsSame() { // two equal quarters
        // create a Quarter object for the second quarter of 2023
        Quarter q1 = new Quarter(2, 2023);

        // create another Quarter object for the second quarter of 2023
        Quarter q2 = new Quarter(2, 2023);

        // assert that q1 is equal to q2
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testQuarterEqualsDifferent() { // two different quarters
        // create a Quarter object for the second quarter of 2023
        Quarter q1 = new Quarter(2, 2023);

        // create another Quarter object for the second quarter of 2024
        Quarter q2 = new Quarter(4, 2024);

        // assert that q1 is not equal to q2
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testQuarterEqualsSameQDiffObj() { // two equal quarters and different objects
        // create a Quarter object for the second quarter of 2023
        Quarter q1 = new Quarter(2, 2023);

        // create another Quarter object for the second quarter of 2023
        Quarter q2 = new Quarter(2, 2023);

        // assert that q1 is not equal to q2
        assertTrue(q1.equals(q2));

        // assert that q1 is not equal to null
        assertFalse(q1.equals(null));
    }

    @Test
    public void testQuarterEqualsDiffQDiffObj() { // two differnt quarters and different objects
        // create a Quarter object for the second quarter of 2023
        Quarter q1 = new Quarter(2, 2023);

        // create another Quarter object for the second quarter of 2024
        Quarter q2 = new Quarter(4, 2024);

        // assert that q1 is not equal to q2
        assertFalse(q1.equals(q2));

        // assert that q1 is not equal to a String object
        assertFalse(q1.equals("2, 2023"));
    }

    @Test
    public void testGetFirstMillisecondYear() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the first millisecond of the quarter
        calendar.setTimeInMillis(quarter.getFirstMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(2023, calendar.get(Calendar.YEAR));
    }

    @Test
    public void testGetFirstMillisecondMonth() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the first millisecond of the quarter
        calendar.setTimeInMillis(quarter.getFirstMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(Calendar.APRIL, calendar.get(Calendar.MONTH));
    }

    @Test
    public void testGetFirstMillisecondDay() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the first millisecond of the quarter
        calendar.setTimeInMillis(quarter.getFirstMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetFirstMillisecondHour() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the first millisecond of the quarter
        calendar.setTimeInMillis(quarter.getFirstMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testGetFirstMillisecondMinutes() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the first millisecond of the quarter
        calendar.setTimeInMillis(quarter.getFirstMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(0, calendar.get(Calendar.MINUTE));
    }

    @Test
    public void testGetFirstMillisecondSecond() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the first millisecond of the quarter
        calendar.setTimeInMillis(quarter.getFirstMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(0, calendar.get(Calendar.SECOND));
    }

    @Test
    public void testGetFirstMillisecond() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the first millisecond of the quarter
        calendar.setTimeInMillis(quarter.getFirstMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(0, calendar.get(Calendar.MILLISECOND));
    }

    @Test
    public void testGetLastMillisecondYear() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the last millisecond of the quarter
        calendar.setTimeInMillis(quarter.getLastMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(2023, calendar.get(Calendar.YEAR));
    }

    @Test
    public void testGetLastMillisecondMonth() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the last millisecond of the quarter
        calendar.setTimeInMillis(quarter.getLastMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(Calendar.JUNE, calendar.get(Calendar.MONTH));
    }

    @Test
    public void testGetLastMillisecondDay() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the last millisecond of the quarter
        calendar.setTimeInMillis(quarter.getLastMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(30, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetLastMillisecondHour() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the last millisecond of the quarter
        calendar.setTimeInMillis(quarter.getLastMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testGetLastMillisecondMinute() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the last millisecond of the quarter
        calendar.setTimeInMillis(quarter.getLastMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(59, calendar.get(Calendar.MINUTE));
    }

    @Test
    public void testGetLastMillisecondSecond() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the last millisecond of the quarter
        calendar.setTimeInMillis(quarter.getLastMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(59, calendar.get(Calendar.SECOND));
    }

    @Test
    public void testGetLastMillisecond() {
        // Create a Quarter object representing Quarter 2 2023
        arrange(2, 2023);

        // Create a Calendar object set to the time zone "GMT"
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Set the calendar to the last millisecond of the quarter
        calendar.setTimeInMillis(quarter.getLastMillisecond(calendar));

        // Check that the calendar is set to April 1st, 2023 at 00:00:00.000 in the
        // "GMT" time zone
        assertEquals(999, calendar.get(Calendar.MILLISECOND));
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testGetQuarterBelow1900() {
        arrange(new Date(-2209169109000L)); // Date: 30/12/1899

        // test that the quarter is 4 (since Dec is in Q4)
        assertEquals(4, quarter.getQuarter());
    }

    @Test
    public void testGetQuarterFirst() {
        // create a Quarter object with the date "January 1, 2023"
        arrange(new Date(1640995200000L)); // 1/1/2023

        // test that the quarter is 1 (since January is in Q1)
        assertEquals(1, quarter.getQuarter());
    }

    @Test
    public void testGetQuarterSecond() {
        // create a Quarter object with the date "April 15, 2023"
        arrange(new Date(1681509600000L)); // 15/4/2023

        // test that the quarter is 2 (since April is in Q2)
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testGetQuarterThird() {
        // create a Quarter object with the date "August 1, 2023"
        arrange(new Date(1690837200000L)); // 1/8/2023

        // test that the quarter is 3 (since August is in Q3)
        assertEquals(3, quarter.getQuarter());
    }

    @Test
    public void testGetQuarterForth() {
        // create a Quarter object with the date "October 31, 2023"
        arrange(new Date(1667232000000L)); // 31/10/2023

        // test that the quarter is 4 (since October is in Q4)
        assertEquals(4, quarter.getQuarter());
    }

    @Test
    public void testGetSerialIndexQ1() {
        // create a Quarter object for Q1 2023
        arrange(1, 2023);

        // check that the serial index is correct for Q1 2023
        assertEquals(2023L * 4L + 1L, quarter.getSerialIndex());
    }

    @Test
    public void testGetSerialIndexQ2() {
        // create a Quarter object for Q2 2023
        arrange(2, 2023);

        // check that the serial index is correct for Q2 2023
        assertEquals(2023L * 4L + 2L, quarter.getSerialIndex());
    }

    @Test
    public void testGetSerialIndexQ3() {
        // create a Quarter object for Q3 2023
        arrange(3, 2023);

        // check that the serial index is correct for Q3 2023
        assertEquals(2023L * 4L + 3L, quarter.getSerialIndex());
    }

    @Test
    public void testGetSerialIndexQ4() {
        // create a Quarter object for Q4 2023
        arrange(4, 2023);

        // check that the serial index is correct for Q4 2023
        assertEquals(2023L * 4L + 4L, quarter.getSerialIndex());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testGetSerialIndexBelowMinValue() {
        // create a Quarter object for the earliest supported Quarter
        arrange(1, 1899);

        // check that the serial index is correct for the earliest supported Quarter
        assertEquals(1899L * 4L + 1L, quarter.getSerialIndex());
    }

    @Test
    public void testGetSerialIndexMinValue() {
        // create a Quarter object for the earliest supported Quarter
        arrange(1, 1900);

        // check that the serial index is correct for the earliest supported Quarter
        assertEquals(1900L * 4L + 1L, quarter.getSerialIndex());
    }

    @Test
    public void testGetSerialIndexMaxValue() {
        // create a Quarter object for the latest supported Quarter
        arrange(4, 9999);

        // check that the serial index is correct for the latest supported Quarter
        assertEquals(9999L * 4L + 4L, quarter.getSerialIndex());
    }

    @Test
    public void testGetYear() {
        Year year = new Year(2023);
        arrange(3, year);
        assertEquals(year, quarter.getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetYearLessThan1999() {
        Year year = new Year(1899);
        arrange(2, year);
        assertEquals(year, quarter.getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetYearMoreThan9999() {
        Year year = new Year(10000);
        arrange(3, year);
        assertEquals(year, quarter.getYear());
    }

    @Test
    public void testHashCode() {
        arrange(1, 2023);
        int firstHash = quarter.hashCode();

        arrange(1, 2023);
        int secondHash = quarter.hashCode();

        assertEquals(firstHash, secondHash);
    }

    @Test
    public void testHashCodeDifferentQuarters() {
        arrange(1, 2023);
        int firstHash = quarter.hashCode();

        arrange(2, 2023);
        int secondHash = quarter.hashCode();

        assertNotEquals(firstHash, secondHash);
    }

    @Test
    public void testHashCodeDifferentYears() {
        arrange(1, 2000);
        int firstHash = quarter.hashCode();

        arrange(1, 2023);
        int secondHash = quarter.hashCode();

        assertNotEquals(firstHash, secondHash);
    }

    @Test
    public void testNext() {
        arrange(1, 2023);
        RegularTimePeriod next = quarter.next();
        arrange(2, 2023);
        assertEquals(next, quarter);
    }

    @Test
    public void testNextInFourthQuarter() {
        arrange(4, 2023);
        RegularTimePeriod next = quarter.next();
        arrange(1, 2024);
        assertEquals(next, quarter);
    }

    @Test
    public void testNextInFourthQuarterMaxYear() {
        arrange(4, 9999);
        RegularTimePeriod next = quarter.next();
        assertNull(next);
    }

    @Test
    public void testParseQuarterFormat1WithDash() {
        String quart = "2023-Q1";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        arrange(1, 2023);
        assertEquals(parsedQuarter, quarter);
    }

    @Test
    public void testParseQuarterFormat2WithDash() {
        String quart = "Q2-2023";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        arrange(2, 2023);
        assertEquals(parsedQuarter, quarter);
    }

    @Test
    public void testParseQuarterFormat1WithSlash() {
        String quart = "2023/Q1";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        arrange(1, 2023);
        assertEquals(parsedQuarter, quarter);
    }

    @Test
    public void testParseQuarterFormat2WithSlash() {
        String quart = "Q2/2023";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        arrange(2, 2023);
        assertEquals(parsedQuarter, quarter);
    }

    @Test
    public void testParseQuarterFormat1WithComma() {
        String quart = "2023,Q1";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        arrange(1, 2023);
        assertEquals(parsedQuarter, quarter);
    }

    @Test
    public void testParseQuarterFormat2WithComma() {
        String quart = "Q2,2023";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        arrange(2, 2023);
        assertEquals(parsedQuarter, quarter);
    }

    @Test
    public void testParseQuarterFormat1WithSpace() {
        String quart = "2023 Q1";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        arrange(1, 2023);
        assertEquals(parsedQuarter, quarter);
    }

    @Test
    public void testParseQuarterFormat2WithSpace() {
        String quart = "Q2 2023";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        arrange(2, 2023);
        assertEquals(parsedQuarter, quarter);
    }

    @Test(expected = TimePeriodFormatException.class)
    public void testParseQuarterWithoutQ() {
        String quart = "2-2023";
        Quarter.parseQuarter(quart);
    }

    @Test(expected = java.lang.Exception.class)
    public void testParseQuarterNoNumbers() {
        String quart = "QN-YYYY";
        Quarter.parseQuarter(quart);
    }

    @Test(expected = TimePeriodFormatException.class)
    public void testParseQuarterYearLessThan1900() {
        String quart = "Q2-1899";
        Quarter.parseQuarter(quart);
        ;
    }

    @Test(expected = TimePeriodFormatException.class)
    public void testParseQuarterYearMoreThan9999() {
        String quart = "Q2-10000";
        Quarter.parseQuarter(quart);
    }

    @Test()
    public void testParseQuarterQuarterLessThanOne() {
        String quart = "Q0-2023";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        assertEquals(0, parsedQuarter.getQuarter());
    }

    @Test()
    public void testParseQuarterQuarterMoreThanFour() {
        String quart = "Q5-2023";
        Quarter parsedQuarter = Quarter.parseQuarter(quart);
        assertEquals(5, parsedQuarter.getQuarter());
    }

    @Test
    public void testPrevious() {
        arrange(2, 2023);
        RegularTimePeriod previous = quarter.previous();
        arrange(1, 2023);
        assertEquals(previous, quarter);
    }

    @Test
    public void testPreviousInFirstQuarter() {
        arrange(1, 2024);
        RegularTimePeriod previous = quarter.previous();
        arrange(4, 2023);
        assertEquals(previous, quarter);
    }

    @Test
    public void testPreviousInFirstQuarterMinYear() {
        arrange(1, 1900);
        RegularTimePeriod previous = quarter.previous();
        assertNull(previous);
    }

    @Test
    public void testToString() {
        arrange(3, 2023);
        String expected = "Q3/2023";
        assertEquals(expected, quarter.toString());
    }

}
