package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.extension.setBodyFromFileName
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ArticleRepositoryTest {
    private val articleRepository = ArticleRepository()
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                return when{
                    request!!.requestLine == "https://9hqe5z0uw7.execute-api.ap-northeast-1.amazonaws.com/test/articleList?userId=111"
                    -> MockResponse().setResponseCode(200).setBodyFromFileName("articleList.json")
                    request.requestLine == "https://9hqe5z0uw7.execute-api.ap-northeast-1.amazonaws.com/test/articleList?companyId=003&offset=1&userId=1"
                    -> MockResponse().setResponseCode(200).setBodyFromFileName("articleListCompany.json")
                    else
                    -> MockResponse().setResponseCode(400)
                }
            }
        }
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start()
    }

    @Test
    fun getAllCategoryArticles() {
        val categories = articleRepository.getAllCategoryArticles("111")
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .values()[0]
                .categories
        assertThat(categories).isNotEmpty

        articleRepository.getAllCategoryArticles("")
                .test()
                .await()
                .assertNotComplete()
    }

    @Test
    fun getCategoryArticles() {
        val categories = articleRepository.getCategoryArticles("003", "1", "1")
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .values()[0]
        assertThat(categories.articles).isNotEmpty

        articleRepository.getCategoryArticles("003", "1", "")
                .test()
                .await()
                .assertNotComplete()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}