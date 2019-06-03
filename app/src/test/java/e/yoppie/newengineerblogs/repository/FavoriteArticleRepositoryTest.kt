package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.extension.setBodyFromFileName
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FavoriteArticleRepositoryTest {
    private val favoriteArticleRepository = FavoriteArticleRepository()
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                return when {
                    request!!.requestLine == "https://9hqe5z0uw7.execute-api.ap-northeast-1.amazonaws.com/test/favoriteArticle"
                    -> MockResponse().setResponseCode(200).setBodyFromFileName("favoriteArticle.json")
                    else
                    -> MockResponse().setResponseCode(400)
                }
            }
        }
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        val categories = favoriteArticleRepository.getFavoriteArticles("yoppie")
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .values()[0]
                .categories
        assertThat(categories).isNotEmpty

        favoriteArticleRepository.getFavoriteArticles("")
                .test()
                .await()
                .assertNotComplete()
    }

    @Test
    fun getFavoriteArticles() {
        mockWebServer.shutdown()
    }
}