package e.yoppie.newengineerblogs.service

import android.content.ClipboardManager
import android.content.Context
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
    }
}