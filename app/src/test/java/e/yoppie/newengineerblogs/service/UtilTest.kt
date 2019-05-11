package e.yoppie.newengineerblogs.service

import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.assertj.core.api.Assertions.*

@Suppress("DEPRECATION")
@RunWith(RobolectricTestRunner::class)
class UtilTest {

    @Test
    fun loadImage() {
    }

    @Test
    fun clipBoardCopy() {
        val context = RuntimeEnvironment.application
        Util.clipBoardCopy(context, "clip_test")
        val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipText = clipboardManager.text
        assertThat(clipText).isEqualTo("clip_test")
    }

    @Test
    fun isNetConnection() {
        val networkInfo: NetworkInfo = mock(name = "NetworkInfo")
        whenever(networkInfo.isConnected).thenReturn(true)

        val connectivityManager: ConnectivityManager = mock(name = "ConnectivityManager")
        whenever(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)

        val context: Context = mock(name = "Context")
        whenever(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)

        assertThat(Util.isNetConnection(context)).isTrue()
    }
}