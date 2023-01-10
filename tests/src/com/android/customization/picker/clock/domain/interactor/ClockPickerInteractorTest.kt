package com.android.customization.picker.clock.domain.interactor

import androidx.test.filters.SmallTest
import com.android.customization.picker.clock.data.repository.FakeClockPickerRepository
import com.android.customization.picker.clock.shared.ClockSize
import com.android.wallpaper.testing.collectLastValue
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
@RunWith(JUnit4::class)
class ClockPickerInteractorTest {

    private lateinit var underTest: ClockPickerInteractor

    @Before
    fun setUp() {
        val testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        underTest = ClockPickerInteractor(FakeClockPickerRepository())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun setClockSize() = runTest {
        val observedClockSize = collectLastValue(underTest.selectedClockSize)
        underTest.setClockSize(ClockSize.DYNAMIC)
        Truth.assertThat(observedClockSize()).isEqualTo(ClockSize.DYNAMIC)

        underTest.setClockSize(ClockSize.LARGE)
        Truth.assertThat(observedClockSize()).isEqualTo(ClockSize.LARGE)
    }
}
