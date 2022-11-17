package vn.cuongle.benchmark

import androidx.benchmark.macro.*
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {

    companion object {
        private const val APP_PACKAGE_NAME = "com.ync.basecompose"
    }

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = APP_PACKAGE_NAME,
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }


    @Test
    fun scrollAndNavigate() = benchmarkRule.measureRepeated(
        packageName = APP_PACKAGE_NAME,
        metrics = listOf(FrameTimingMetric()),
        iterations = 2,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
        scrollDownHomeItemList()

    }
}

fun MacrobenchmarkScope.scrollDownHomeItemList() {
    device.wait(Until.hasObject(By.res("item_list")), 5000)
    val list = device.findObject(By.res("item_list"))
    device.waitForIdle()
    repeat(5) {
        list.fling(Direction.DOWN)
        list.fling(Direction.UP)
    }
}
