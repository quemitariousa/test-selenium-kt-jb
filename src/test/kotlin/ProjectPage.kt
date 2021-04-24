import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ProjectPage(private val driver: WebDriver){


    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(xpath = "//div[contains(@id, 'mainContent')]")
    lateinit var mainContent: WebElement

    @FindBy(xpath = "//div[contains(@id, 'react-header-container')]")
    lateinit var header: WebElement

    @FindBy(xpath = "//footer[contains(@id, 'footer')]")
    lateinit var footer: WebElement

    @FindBy(xpath = "//a[contains(@href, 'https://teamcity.jetbrains.com/login.html')]")
    lateinit var buttonLogIn: WebElement

    fun elementsIsDisplayedOnProjectPage(): Boolean {
        return header.isDisplayed and
                mainContent.isDisplayed and
                footer.isDisplayed
    }

}