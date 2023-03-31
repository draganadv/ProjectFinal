package test;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.LoginPage;
import pages.posts.*;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAddPost {
	private static ChromeDriver driver;
	private static AddPost addPostPage;
	private static LoginPage loginPage;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(80000));

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Dragana\\OneDrive\\Desktop\\WebDriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		addPostPage = new AddPost(driver);
		loginPage.loginSuccess();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		addPostPage.logOut();
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		
		addPostPage.openPage();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc03AddPostWithAllEmptyFields() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}

	@Test
	public void tc04AddPostWithTitleAndEmptyOtherfields() {
		addPostPage.inputStringTitle(" This is test title for new post");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc05AddPostWithShortTitleAndEmptyOtherFields() {
		addPostPage.inputStringTitle("New");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "Please enter at least 20 characters.");
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc06AddPostWithTitleAndShortDescription () {
		addPostPage.inputStringTitle("This is test title for new post");
	    addPostPage.inputStringDescription("This is test");
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"Please enter at least 50 characters.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc07AddPostWithEmptyContent() {
		addPostPage.inputStringTitle("This is test title for new post");
	    addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.chooseTags("amet");
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc08AddPostWithOnlyContent() {
		
	  addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
	  addPostPage.clickOnSaveButton();

	  assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
	  assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
	  assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		
	}

	@Test
	public void tc09AddPostWithCategoryAndTitle() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
			
	}
	@Test
	public void tc10AddPostWithCategoryAndDescription() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
			
	}
	@Test
	public void tc11AddPostWithCategoryAndImportant() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.clickOnImportantYes();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
			
	}
	@Test
	public void tc12AddPostWithCategoryAndTag() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
			
	}
	@Test
	public void tc14AddPostWithCategoryAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		 assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
			
	}
	@Test
	public void tc15AddPostWithDescriptionAndImportant() {
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		 assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc16AddPostWithDescriptionAndTag() {
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc18AddPostWithDescriptionAndContent() {
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	}
	@Test
	public void tc19AddPostWithImportantAndTag() {
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
	     assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	
	@Test
	public void tc21AddPostWithImportantAndContent() {
		addPostPage.clickOnImportantYes();
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		 assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	     
	}
	@Test
	public void tc23AddPostWithTagAndContent() {
		addPostPage.chooseTags("Tag name 95");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		 assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		
	}
	@Test
	public void tc25AddPostWithCategoryTitleAndDescription() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		 assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		 assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc26AddPostWithCategoryTitleAndImportant() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.clickOnImportantYes();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		 assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		 assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc27AddPostWithCategoryTitleAndTag() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc29AddPostWithCategoryTitleAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	}
	@Test
	public void tc30AddPostWithCategoryDescriptionAndImportant() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc31AddPostWithCategoryDescriptionAndTag() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc33AddPostWithCategoryDescriptionAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	}
	@Test
	public void tc34AddPostWithCategoryImportantAndTag() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc36AddPostWithCategoryImportantAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.clickOnImportantYes();
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	}
	@Test
	public void tc38AddPostWithDescriptionImportantAndTag() {
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc40AddPostWithDescriptionImportantAndContent() {
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	}
	@Test
	public void tc42AddPostWithDescriptionTagAndContent() {
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.chooseTags("Tag name 95");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
	}
	@Test
	public void tc45AddPostWithImportantTagAndContent() {
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
	}
	@Test
	public void tc48AddPostWithCategoryTitleDescriptionAndImportant() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc49AddPostWithCategoryTitleDescriptionAndTag() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc51AddPostWithCategoryTitleDescriptionAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	}
	@Test
	public void tc52AddPostWithCategoryTitleImportantAndTag() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc54AddPostWithCategoryTitleImportantAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.clickOnImportantYes();
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	}
	@Test
	public void tc56AddPostWithCategoryTitleTagAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.chooseTags("Tag name 95");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
	}
	@Test
	public void tc58AddPostWithCategoryDescriptionImportantAndTag() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string content input.",addPostPage.getStringFromContentError(), "The content field is required.");
	}
	@Test
	public void tc60AddPostWithCategoryDescriptionImportantAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string tag input.", addPostPage.getStringFromTagError(), "This field is required.");
	}
	@Test
	public void tc62AddPostWithCategoryDescriptionTagAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.chooseTags("Tag name 95");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
	}
	@Test
	public void tc64AddPostWithDescriptionImportantTagAndContent() {
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
	}
	@Test
	public void tc67AddPostWithCategoryImportantTagAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Error string title input.", addPostPage.getStringFromTitleError(), "This field is required.");
		assertEquals("Error string description input.", addPostPage.getStringFromDescriptionError(),"This field is required.");
	}
	@Test
	public void tc68AddPostWithCategoryTitleDescriptionImportantTagAndContent() {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("Tag name 95");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
			
	}
	@Test
	public void tc73AddPostWithExistingInformations () {
		addPostPage.chooseCategory("Clarkstad");
		addPostPage.inputStringTitle("This is test title for new post");
		addPostPage.inputStringDescription("This is test This is test This is test This is test");
		addPostPage.clickOnImportantYes();
		addPostPage.chooseTags("amet");
		addPostPage.inputStringContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
		addPostPage.clickOnSaveButton();
		assertEquals("Post with this title already exists.", addPostPage.getStringFromTitleError(), "Post with this Title already exists");	
	}
	@Test
	public void tc74CancelAddingPost() {
		addPostPage.clickOnCancelLink();
		assertEquals("https://testblog.kurs-qa.cubes.edu.rs/admin/posts",driver.getCurrentUrl());
	}
	@Test
	public void tc75HomeLinkCheck() {
		addPostPage.clickOnHomeLink();
		assertEquals( "https://testblog.kurs-qa.cubes.edu.rs/admin", driver.getCurrentUrl());
	}
	@Test
	public void tc76PostLinkCheck() {
		addPostPage.clickOnPostLink();
		assertEquals( "https://testblog.kurs-qa.cubes.edu.rs/admin/posts", driver.getCurrentUrl());
	}
}