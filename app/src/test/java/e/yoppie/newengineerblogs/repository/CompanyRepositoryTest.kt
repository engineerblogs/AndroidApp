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
class CompanyRepositoryTest {
    private val companyRepository = CompanyRepository()
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                return when {
                    request!!.requestLine == "https://9hqe5z0uw7.execute-api.ap-northeast-1.amazonaws.com/test/companyList"
                    -> MockResponse().setResponseCode(200).setBodyFromFileName("companyList.json")
                    else
                    -> MockResponse().setResponseCode(400)
                }
            }
        }
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start()
    }

    @Test
    fun getCompanyList() {
        val companies = companyRepository.getCompanyList()
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .values()[0]
                .companies
        assertThat(companies).isNotEmpty
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}