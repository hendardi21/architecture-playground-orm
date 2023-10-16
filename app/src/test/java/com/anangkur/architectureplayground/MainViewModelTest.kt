package com.anangkur.architectureplayground

import com.anangkur.architectureplayground.page.MainViewModel
import org.junit.Assert
import org.junit.Test

class MainViewModelTest {

    private val mainViewModel = MainViewModel()

    @Test
    fun isProvideDataReturnOneMillionData() {
        val actual = mainViewModel.provideData().size
        val expected = 1000000
        Assert.assertEquals(expected, actual)
    }
}