package com.trian.module.utility

import com.trian.domain.utils.utils.*
import org.joda.time.DateTime
import org.joda.time.Period
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilityTest {

    @Test
    fun `get Next And Previous date from Timestamp`(){
        //1632889813000
        val currentDate = 1632889813000
        assertEquals("2021-09-29 11:30:13",currentDate.formatDate())
        assertEquals("2021-09-28 00:00:00",currentDate.getPreviousDate().formatDate())
        assertEquals("2021-09-30 00:00:00",currentDate.getNextDate().formatDate())
        assertEquals("2021-09-29 11:30:13",currentDate.formatDate())
        assertEquals("September 29, 2021",currentDate.formatReadableDate())
      //  assertEquals(true,currentDate.isDateBeforeToday())
        //15 july 2021
        //assertEquals("a",1626322177000.getWeek(1642738282000))
        //14 januari 2022
        assertEquals("1 Week 0 Day",1642118400000.getAgePregnancy(1642738282000))
    }

    @Test
    fun `calculate hpl and age pregnancy`(){
        //14 januari 2022
        val date = 1642118400000
        val expectedAge = "1 Week 0 Day"
        val expectedHpl = "October 21, 2022"
        //15 july 2021
        val date2 = 1626322177000
        val expectedAge2 = "27 Week 1 Day"
        val expectedHpl2 = "April 22, 2022"

        //21 december 2021
        val date3 = 1640044800000
        val expectedAge3 = "4 Week 3 Day"
        val expectedHpl3 = "September 28, 2022"

        //21 January 2022
        val currentDate = 1642738282000

        assertEquals(expectedAge,date.getAgePregnancy(currentDate))
        assertEquals(expectedHpl,date.getHpl().formatReadableDate())
        assertEquals(expectedAge2,date2.getAgePregnancy(currentDate))
        assertEquals(expectedHpl2,date2.getHpl().formatReadableDate())
        assertEquals(expectedAge3,date3.getAgePregnancy(currentDate))
        assertEquals(expectedHpl3,date3.getHpl().formatReadableDate())
    }

    @Test
    fun `get total minutes of sleep`(){
        val endTime = 1635120994000
        val startTIme = 1635098413000

        val startSleepTime = 1635098413000
        val startSleepTime2 = 1635100535000
        val sleepLen = 2121

        val deepSleepTotal = 108
        val lightSleepTotal = 267

        val start = DateTime(startTIme)
        val end = DateTime(startSleepTime)
        val end2 = DateTime(startSleepTime2)

        val period = Period(start,end)
        val period2 = Period(start,end2)

        assertEquals(6.25,((deepSleepTotal+lightSleepTotal).toDouble()/60),0.2)
        assertEquals(6.25,((deepSleepTotal+lightSleepTotal).toDouble()/60),0.2)
        assertEquals(375,((deepSleepTotal+lightSleepTotal)))

        //get dur sleep
        assertEquals(35,sleepLen/60)
        assertEquals(0,period.minutes)
        assertEquals(35,period2.minutes)

    }

    @Test
    fun `get avg`(){
        val pos = 300f
        val neg = -300f

        val lesNeg = -200f
        val lesPos = 200f

        assertEquals(true,neg < pos)
        assertEquals(false,neg > pos)
        assertEquals(true,lesNeg > neg)

    }

    @Test
    fun `should extract status order`(){

    }
}