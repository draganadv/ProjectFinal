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
public class TestPostsList {
	private static ChromeDriver driver;
	private static LoginPage loginPage;
	private static PostsList postsListPage;
	
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(6000));
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dragana\\OneDrive\\Desktop\\WebDriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		postsListPage = new PostsList(driver);
		loginPage.loginSuccess();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		postsListPage.logOut();
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		postsListPage.openPage();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc06SearchPostsWithTitle() {
		postsListPage.inputStringTitle("Asperiores dolor rerum.");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'Asperiores dolor rerum.')]")));
		postsListPage.countPostsWithTitle("Asperiores dolor rerum.");
	}
	@Test
	public void tc07SearchPostsWithTitleKeyword() {
		postsListPage.inputStringTitle("dolorum");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'dolorum')]")));
		postsListPage.countPostsWithTitleKeyword("dolorum");
	}
	@Test
	public void tc09SearchPostsWithAuthor() {
		postsListPage.chooseAuthor("Polaznik Kursa");
	  postsListPage.countElementsWithAuthor("Polaznik Kursa");
	}
	@Test
	public void tc11SearchPostsWithCategory() {
		postsListPage.chooseCategory("Clarkstad");
		
	}
	@Test
	public void tc13SearchPostsWithImportant() {
		postsListPage.showEntries();
		postsListPage.chooseImportant("Yes");
		
	}
	@Test
	public void tc15SearchPostsWithStatusOption() {
		postsListPage.showEntries();
		postsListPage.chooseStatus("enabled");
		
	}
	@Test
	public void tc17SearchPostsWithTagOptions() {
		postsListPage.showEntries();
		postsListPage.chooseTagOption("amet");
		
	}
	@Test
	public void tc18SearchPostsWithMultipleTagOptions() {
		postsListPage.showEntries();
		postsListPage.chooseMultipleTagOptions("amet","aliquid");
		
	}
	@Test
	public void tc19SearchPostsWithID() {
		postsListPage.clickOnSearchWithID();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[contains(.,'12')]")));
		assertEquals(true, postsListPage.isElementWithIDPresent());
	}
	@Test
	public void tc20SearchPostsWithTitleAndAuthor() {
		postsListPage.inputStringTitle("Ducimus impedit voluptas.");
		postsListPage.countPostsWithTitle("Ducimus impedit voluptas.");
		postsListPage.chooseAuthor("Vladan Dzulovic");
		postsListPage.noMatchesMessage();
	
	}
	@Test
	public void tc21SearchPostsTitleAndCategory() {
		postsListPage.inputStringTitle("Culpa quae maiores.");
		postsListPage.chooseCategory("Gracietown");
	}
	@Test
	public void tc22SearchPostsWithKeywordAndImportant() {
		postsListPage.inputStringTitle("culpa");
		postsListPage.chooseImportant("Yes");

	}
	@Test
	public void tc23SearchPostsWithTitleAndStatus() {
		postsListPage.inputStringTitle("Animi ad explicabo magnam et.");
		postsListPage.chooseStatus("enabled");
	}
	@Test
	public void tc24SearchPostsWithTitleAuthorCategory() {
		postsListPage.inputStringTitle("Unde dolor fugiat harum.");
		postsListPage.chooseAuthor("Marko Dragonjic");
		postsListPage.chooseCategory("Greenfelderville");
	}
	@Test
	public void tc25SearchPostWithAuthorAndCategory() {
		postsListPage.chooseAuthor("Vladan Dzulovic");
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc26SearchPostWithAuthorAndImportant() {
		postsListPage.chooseAuthor("Marko Dragonjic");
		postsListPage.chooseImportant("no");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc27SearchPostWithAuthorAndStatus() {
		postsListPage.chooseAuthor("Marko Dragonjic");
		postsListPage.chooseStatus("disabled");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc28SearchPostWithAuthorAndTag() {
		postsListPage.chooseAuthor("Vladan Dzulovic");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc29SearchPostWithAuthorCategoryAndImportant() {
		postsListPage.chooseAuthor("Polaznik Kursa");
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseImportant("no");
	}
	@Test
	public void tc30SearchPostWithAuthorCategoryAndStatus() {
		postsListPage.chooseAuthor("Polaznik Kursa");
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseStatus("enabled");
	}
	@Test
	public void tc31SearchPostWithAuthorCategoryAndTag() {
		postsListPage.chooseAuthor("Polaznik Kursa");
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc32SearchPostWithAuthorCategoryImportantAndStatus() {
		postsListPage.chooseAuthor("Polaznik Kursa");
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseImportant("no");
		postsListPage.chooseStatus("enabled");
	}
	@Test
	public void tc33SearchPostWithAuthorCategoryImportantAndTag() {
		postsListPage.chooseAuthor("Polaznik Kursa");
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseImportant("no");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc34SearchPostWithCategoryAndImportant() {
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseImportant("yes");
	}
	@Test
	public void tc35SearchPostWithCategoryAndStatus() {
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseStatus("enabled");
	}
	@Test
	public void tc36SearchPostWithCategoryAndTag() {
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc37SearchPostWithCategoryImportantAndStatus() {
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseImportant("yes");
		postsListPage.chooseStatus("enabled");
	}
	@Test
	public void tc38SearchPostWithCategoryImportantStatusAndTag() {
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseImportant("yes");
		postsListPage.chooseStatus("enabled");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc39SearchPostWithCategoryImportantAndTag() {
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseImportant("yes");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc40SearchPostWithImportantAndStatus() {
		postsListPage.chooseImportant("yes");
		postsListPage.chooseStatus("enabled");
	}
	@Test
	public void tc41SearchPostWithImportantAndTag() {
		postsListPage.chooseImportant("yes");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	@Test
	public void tc42SearchPostWithImportantStatusAndTag() {
		postsListPage.chooseImportant("yes");
		postsListPage.chooseStatus("enabled");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	public void tc43SearchPostWithStatusAndTag() {
		postsListPage.chooseStatus("enabled");
		postsListPage.chooseTagOption("Tag name 95");
		postsListPage.noMatchesMessage();
	}
	
	@Test
	public void tc44SearchPostsWithAllFields() {
		postsListPage.inputStringTitle("Veritatis illum officiis.");
		postsListPage.chooseAuthor("Polaznik Kursa");
		postsListPage.chooseCategory("Clarkstad");
		postsListPage.chooseImportant("Yes");
		postsListPage.chooseStatus("enabled");
		postsListPage.chooseTagOption("ad");
	}
	@Test
	public void tc45AddNewPostLink() {
		postsListPage.clickOnAddNewPostLink();
		assertEquals( "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add", driver.getCurrentUrl());
	}
	@Test
	public void tc46HomeLinkCheck() {
		postsListPage.clickOnHomeLink();
		assertEquals( "https://testblog.kurs-qa.cubes.edu.rs/admin", driver.getCurrentUrl());
	}
	@Test
	public void tc47ListOfPosts() {
		postsListPage.clickOnShowEntries("10");
		postsListPage.clickOnPageLink("Next");
		
	}
	@Test
	public void tc48ShowEntries() {
		postsListPage.clickOnShowEntries("100");
		
	}
	@Test
	public void tc49CancelDisablePost() {
		postsListPage.inputStringTitle("Unde vero.");
		postsListPage.clickOnDisableButton();
		postsListPage.clickOnCancelDialogDisablePost();
		postsListPage.checkDisableEnable();
		
	}
	@Test
	public void tc50DisablePost() {
		postsListPage.inputStringTitle("Unde vero.");
		postsListPage.clickOnDisableButton();
		postsListPage.clickOnDisableDialogPost();
		postsListPage.getMessage();
		
		
	}
	@Test 
	public void tc51CancelEnablePost() {
		postsListPage.inputStringTitle("Unde vero.");
		postsListPage.clickOnEnableButton();
		postsListPage.clickOnCancelDialogEnablePost();
		postsListPage.checkDisableEnable();
	}
	@Test
	public void tc52EnablePost() {
		postsListPage.inputStringTitle("Unde vero.");
		postsListPage.clickOnEnableButton();
		postsListPage.clickOnEnableDialogPost();
		postsListPage.getMessage();
	}
	@Test
	public void tc53CancelDeletePost() {
		postsListPage.inputStringTitle("This is test title for new post");
		postsListPage.clickOnDeleteButton();
		postsListPage.clickOnCancelDialogDelete();
		
	}
	@Test
	public void tc54DeletePost() {
		postsListPage.inputStringTitle("This is test title for new post");
		postsListPage.clickOnDeleteButton();
		postsListPage.clickOnDeleteDialogButton();
		postsListPage.getMessage();
	}
	@Test
	public void tc55CancelSetAsUnimportant() {
		postsListPage.inputStringTitle("Unde vero.");
		postsListPage.clickOnUnimportantButton();
		postsListPage.clickOnCancelDialogUnimportantButton();
	}
	@Test
	public void tc56SetAsUnimportant() {
		postsListPage.inputStringTitle("Unde vero.");
		postsListPage.clickOnUnimportantButton();
		postsListPage.clickOnSetAsUnimportantDialogButton();
		postsListPage.getMessage();
	}
	@Test
	public void tc57CancelSetAsImportant() {
		postsListPage.inputStringTitle("Unde vero.");
		postsListPage.clickOnImportantButton();
		postsListPage.clickOnCancelDialogImportantButton();
	}
	@Test
	public void tc58SetAsImportant() {
		postsListPage.inputStringTitle("Unde vero.");
		postsListPage.clickOnUnimportantButton();
		postsListPage.clickOnSetAsImportantDialogButton();
		postsListPage.getMessage();
	}
	
}
