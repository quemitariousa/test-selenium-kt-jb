import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

class LoginPage(private val driver: WebDriver){

    private val pageUrl = "https://teamcity.jetbrains.com/login.html"
    private val wait = WebDriverWait(driver, 3)


    init {
        PageFactory.initElements(driver, this)
    }


    @FindBy(xpath = "//a[contains(text(), 'Log in as guest')]")
    lateinit var logIsAGuest: WebElement

    @FindBy(xpath = "//input[contains(@id, 'username')]")
    lateinit var usernameInput: WebElement

    @FindBy(xpath = "//input[contains(@id, 'username')]")
    lateinit var passwordInput: WebElement

    fun elementsIsDisplayed(): Boolean {
        return logIsAGuest.isDisplayed and
                usernameInput.isDisplayed and
                passwordInput.isDisplayed
    }

    fun loginAsGuest(): ProjectPage {
        logIsAGuest.click()
        wait.until {
            driver.currentUrl.startsWith("https://teamcity.jetbrains.com/overview.html")
        }
        return ProjectPage(driver)
    }

    fun open() = driver.get(pageUrl)





}