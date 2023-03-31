package pages.posts;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddPost {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final String PAGE_URL = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add";
	
	
	@FindBy(xpath = "//select[@name='post_category_id']")
	private WebElement category;
	@FindBy(xpath = "//input[@name='title']")
	private WebElement title;
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement description;
	@FindBy(xpath = "//label[.='Yes']")
	private WebElement importantYes;

	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement saveButton;
	
	@FindBy(name="email")
	private WebElement email;
	@FindBy(name="password")
	private WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement signIn;
	
	@FindBy(id="title-error")
	private WebElement titleError;
	@FindBy(id="description-error")
	private WebElement descriptionError;
	@FindBy(id="tag_id[]-error")
	private WebElement tagError;
	@FindBy(xpath="//div[.='The content field is required.']")
	private WebElement contentError;
	
	@FindBy(xpath="//a[.='Home']")
	private WebElement home;
	@FindBy(xpath="//a[.='Post']")
	private WebElement post;
	

	
	public AddPost(WebDriver driver) {
		this.driver = driver;
		this.wait= new WebDriverWait(driver,Duration.ofMillis(80000));
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver,this);
	}
	
	public void login() {
		email.sendKeys("kursqa@cubes.edu.rs");
		password.sendKeys("cubesqa");
		clickOnSignIn();
		
	}
	public void openPage() {
		driver.get(PAGE_URL);
	}
	public void clickOnSignIn() {
		signIn.click();
	}

	public void clickOnSaveButton() {
		
		saveButton.submit();
	}
	public void clickOnCancelLink(){
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Cancel')]")));
		executor.executeScript("arguments[0].click();", element);
		
	
	}
	public void chooseCategory(String categoryName) {
	
		 category.click();
		
		   WebElement categoryChoose= driver.findElement(By.xpath("//option[contains(.,'"+categoryName+"')]"));
			
			  categoryChoose.click();

	}
	
	public void inputStringTitle(String titleName){
		title.clear();
		title.sendKeys(titleName);
	}
	public void inputStringDescription(String descriptionText){
		description.clear();
		description.sendKeys(descriptionText);
	}
	public void clickOnImportantYes() {
		importantYes.click();
	}
	public void inputStringContent(String contentText){
		
		 WebElement iframe = driver.findElement(By.xpath("//iframe[1]"));
	        driver.switchTo().frame(iframe);

	        WebElement inputElement = driver.findElement(By.xpath("//p[1]"));
	        inputElement.sendKeys(contentText);

	        driver.switchTo().defaultContent();
	}
	public String getStringFromTitleError() {
		return titleError.getText();
	}
	public String getStringFromDescriptionError() {
		return descriptionError.getText();
	}
	public String getStringFromTagError() {
		return tagError.getText();
	}
	public String getStringFromContentError() {
		return contentError.getText();
	}
	public void chooseTags(String tagName) {
		
		WebElement tagChoose= driver.findElement(By.xpath("//label[.='"+tagName+"']"));
		tagChoose.click();
		
	}
	public void clickOnHomeLink() {
		home.click();
	}
	public void clickOnPostLink() {
		post.click();
	}
	public void logOut() {
		WebElement profile= driver.findElement(By.xpath("//i[@class='far fa-user']"));
		profile.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Log Out')]")));
		WebElement logout= driver.findElement(By.xpath("//a[contains(.,'Log Out')]"));
		logout.click();
	}
}
