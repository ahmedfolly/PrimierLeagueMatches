package com.example.primierleaguematches

import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class UtilsTodayDateTest{

    @Test
    fun getTodayDate_BadFormat(){
        val todayDate = getTodayDate()
        assertEquals(todayDate,"2023/07/05")
    }

    @Test
    fun getTodayDate_NotTodayDate(){
        val todayDate = getTodayDate()
        assertEquals(todayDate,"2023-08-07")
    }

    fun getTodayDate_NotEnglishDate(){
        val todayDate = getTodayDate()

    }
}