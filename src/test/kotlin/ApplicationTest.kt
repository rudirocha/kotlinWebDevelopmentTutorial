import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.Application
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun emptyPath() {
        withTestApplication(Application::mainModule) {
            val call = handleRequest(HttpMethod.Get, "")

            assertEquals(HttpStatusCode.OK, call.response.status())
        }
    }

    @Test
    fun validValue() {
        withTestApplication(Application::mainModule) {
            val call = handleRequest(HttpMethod.Get, "/dobby")
            assertEquals("""
                {
                  "Name" : "dobby"
                }
                """.asJson(),(call.response.content)?.asJson())
        }
    }
}
private fun String.asJson() = ObjectMapper().readTree(this)