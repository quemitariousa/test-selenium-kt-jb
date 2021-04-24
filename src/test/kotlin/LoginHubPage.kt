import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class LoginHubPage(private val driver: WebDriver){
    private val hubUrl = "https://hub.jetbrains.com/auth/login"

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(xpath = "//a[contains (@class, 'auth-button-federated')]")
    lateinit var loginInAsGuest: WebElement

    @FindBy(xpath = "(//img[contains (@class, 'login-page__authmodules__module__icon') and contains (@alt, 'JetBrains Account OAuth 2.0')])[2]")
    lateinit var loginJb: WebElement

    @FindBy(xpath = "(//img[contains (@class, 'login-page__authmodules__module__icon') and contains (@alt, 'Google')])[2]")
    lateinit var loginWithGoogle: WebElement

    @FindBy(xpath = "(//img[contains (@class, 'login-page__authmodules__module__icon') and contains (@alt, 'GitHub')])[2]")
    lateinit var loginWithGithub: WebElement

    @FindBy(xpath = "(//img[contains (@class, 'login-page__authmodules__module__icon') and contains (@alt, 'Bitbucket Cloud')])[2]")
    lateinit var loginWithBitbucket: WebElement

    fun elementsIsDisplayed(): Boolean {
        return loginInAsGuest.isDisplayed and
                loginJb.isDisplayed and
                loginWithBitbucket.isDisplayed and
                loginWithGithub.isDisplayed and
                loginWithGoogle.isDisplayed
    }
}