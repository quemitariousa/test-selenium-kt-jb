import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit
import io.kotlintest.Description
import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener

class TestSignup : StringSpec(), TestListener {
    private val driver: WebDriver = ChromeDriver()
    private val loginPage = LoginPage(driver)
    private val wait = WebDriverWait(driver, 3)
    private val invalidRequestUrl = "https://internship.jetbrains.com/auth/callback/?error=invalid_request"


    override fun afterSpec(description: Description, spec: Spec) {
        super<StringSpec>.afterSpec(description, spec)
        driver.quit()
    }

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()



        "Guest can see basic web elements for login"{
            loginPage.run {
                openPrivateSection()
                assert(wait.until{
                    elementsIsDisplayed()
                })
            }
        }


        "Guest can't log into a prohibited section"{
            loginPage.run {
                openPrivateSection()
                assert(elementsIsDisplayed())
                loginInAsGuest.click()
                assert(driver.currentUrl.startsWith(invalidRequestUrl))
            }
        }

        "Log in guest into a public section"{
            loginPage.run {
                openPublicSection()
                logInPublicSection.click()
                loginInAsGuest.click()
                Thread.sleep(1000)
                assert(wait.until{
                    driver.currentUrl.startsWith(publicSectionUrl)
                })
            }
        }



    }
}