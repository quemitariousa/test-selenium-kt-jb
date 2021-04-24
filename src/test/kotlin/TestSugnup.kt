import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import io.kotlintest.Description
import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener

class TestSignup : StringSpec(), TestListener {
    private val driver: WebDriver = ChromeDriver()
    private val loginPage = LoginPage(driver)
    private val hubPage = LoginHubPage(driver)


    override fun afterSpec(description: Description, spec: Spec) {
        super<StringSpec>.afterSpec(description, spec)
        driver.quit()
    }

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()


        "Guest can see basic web elements for login"{
            loginPage.run {
                open()
                assert(elementsIsDisplayed())
            }
        }

        "Log in guest into a TeamCity Servers"{
            loginPage.run {
                open()
                loginAsGuest()
                assert(
                    driver.currentUrl.startsWith("https://teamcity.jetbrains.com/overview.html")
                )
            }
        }

        "Guest can see basic web elements for TeamCity Page"{
        loginPage.run {
            open()
            var projectPage = loginAsGuest()
            assert(
                driver.currentUrl.startsWith("https://teamcity.jetbrains.com/overview.html")
            )
            assert(projectPage.elementsIsDisplayedOnProjectPage())
        }
        }

        "Guest can login from Project Page with Hub Login"{
            loginPage.run {
                open()
                var projectPage = loginAsGuest()
                projectPage.buttonLogIn.click()
                assert(
                    driver.currentUrl.startsWith("https://hub.jetbrains.com/auth/login")
                )
                hubPage.elementsIsDisplayed()
                hubPage.loginInAsGuest.click()
                assert(driver.currentUrl.startsWith("https://teamcity.jetbrains.com/overview.html"))
            }
        }
        }
    }


