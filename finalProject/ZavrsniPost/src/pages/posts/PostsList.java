package pages.posts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;


public class PostsList {
	private  WebDriver driver;
	private WebDriverWait wait;
	private static final String PAGE_URL = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts";
	@FindBy(xpath = "//input[@name='title']")
	private WebElement title;
	@FindBy(css=("[title='--Choose Author --']"))
	private WebElement author;
	@FindBy(css=("[title='--Choose Category --'] "))
	private WebElement category;
	@FindBy(xpath = "//select[@name='important']")
	private WebElement important;
	@FindBy(xpath = "//select[@name='status']")
	private WebElement status;
	@FindBy(xpath = "//ul[@class='select2-selection__rendered']")
	private WebElement withTag;
	@FindBy(xpath = "//input[@class='form-control form-control-sm']")
	private WebElement search;
	@FindBy(xpath="//select[@name='entities-list-table_length']")
	private WebElement showEntries;
	@FindBy(xpath="//a[.='Next']")
	private WebElement pageChange;
	@FindBy(xpath="//a[.='Next']")
	private WebElement next;
	@FindBy(xpath="//a[.='Home']")
	private WebElement home;
	@FindBy(xpath="//a[contains(.,'Add new Post')]")
	private WebElement addNewPost;
	@FindBy(name="email")
	private WebElement email;
	@FindBy(name="password")
	private WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement signIn;
	@FindBy(css=(".even > td:nth-of-type(5)"))
	private WebElement titleColumn;
	
	@FindBy(xpath="//tdtd[@class='sorting_1']")
	private WebElement authorColumn;
	
	@FindBy(xpath="//option[.='yes']")
	private WebElement importantYes;
	@FindBy(xpath="//option[.='100']")
	private WebElement showAllEntries;
	@FindBy(xpath="//option[.='enabled']")
	private WebElement statusEnabled;
	
	@FindBy(xpath="//td[@class='dataTables_empty']")
	private WebElement noPostsFound;
	public PostsList(WebDriver driver) {
		this.driver = driver;
		this.wait= new WebDriverWait(driver,Duration.ofMillis(8000));
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver,this);
	}
	
	public void login() {
		email.sendKeys("kursqa@cubes.edu.rs");
		password.sendKeys("cubesqa");
		signIn.click();
		
	}
	
	public void clickOnSignIn() {
		signIn.click();
	}

	public void openPage() {
		driver.get(PAGE_URL);
	}
	public void inputStringTitle(String titleName) {
		title.clear();
		title.sendKeys(titleName);
		
	}
	
	public boolean isTitlePostInTheList(String titleName) {
		return !driver.findElements(By.xpath("//td[contains(.,'"+titleName+"')]")).isEmpty();

	}
	public void countPostsWithTitle(String titleName) {
		List<WebElement> elements = driver.findElements(By.xpath("//td[contains(.,'"+titleName+"')]"));
   
	       System.out.println("Number of posts with title "+titleName+" : " + elements.size());
	       
	}
   public void countPostsWithTitleKeyword(String keyword) {
	   
	   List<WebElement> elements = driver.findElements(By.xpath("//td[contains(.,'"+keyword+"')]"));

       System.out.println("Number of posts containing "+keyword+" keyword : " + elements.size());
       
   }
   public String chooseAuthor(String authorName) {
	   author.click();
		  WebElement authorChoose= driver.findElement(By.xpath("//option[.='"+authorName+"']"));
		
		  authorChoose.click();
		  return authorName;
	 
   }
  
   public void countElementsWithAuthor(String authorName) {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='"+authorName+"']")));
	   List<WebElement> elements = driver.findElements(By.xpath("//td[.='"+authorName+"']"));
	   
       System.out.println("Number of posts of author "+authorName+" : " + elements.size());
     
	}
   public void chooseCategory(String categoryName) {
	   category.click();
	
	   WebElement categoryChoose= driver.findElement(By.xpath("//option[.='"+categoryName+"']"));
		
		  categoryChoose.click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'"+categoryName+"')]")));
 
	   
	   List<WebElement> elements = driver.findElements(By.xpath("//td[contains(.,'"+categoryName+"')]"));

       System.out.println("Number of posts with Category "+categoryName+" : " + elements.size());

   }
   public void chooseImportant(String isImportant) {
	   Select ci = new Select(driver.findElement(By.xpath("//select[@name='important']")));
	
	ci.selectByVisibleText(isImportant);

	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'"+isImportant.substring(0, 0).toUpperCase()+"')]")));
		 List<WebElement> elements = driver.findElements(By.xpath("//td[contains(.,'"+isImportant.substring(0, 0).toUpperCase()+"')]"));
		 
	       System.out.println("Number of posts with Important state "+isImportant+" : " +  elements.size());
	}
	public void showEntries() {
		showEntries.click();
		showAllEntries.click();
	}
	public void chooseStatus(String statusName) {
		  Select cs = new Select(driver.findElement(By.xpath("//select[@name='status']")));
			
			cs.selectByVisibleText(statusName);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'"+statusName+"')]")));

		List<WebElement> elements = driver.findElements(By.xpath("//td[contains(.,'"+statusName+"')]"));
		 
	       System.out.println("Number of posts with Status  "+statusName+" : " +  elements.size());
	}
	public void chooseTagOption(String tagName) {
		withTag.click();
		WebElement tagChoose= driver.findElement(By.xpath("//option[.='"+tagName+"']"));
		tagChoose.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'"+tagName+"')]")));
	
		List<WebElement> elements = driver.findElements(By.xpath("//td[contains(.,'"+tagName+"')]"));
		
	       System.out.println("Number of posts with Tag "+tagName+" : " +  elements.size());
	}
	public void chooseMultipleTagOptions(String tag1, String tag2) {
		withTag.click();
		WebElement tagOption1= driver.findElement(By.xpath("//option[.='"+tag1+"']"));
		tagOption1.click();
		withTag.click();
		WebElement tagOption2= driver.findElement(By.xpath("//option[.='"+tag2+"']"));
		tagOption2.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[contains(text(), '"+tag1+"') or contains(text(), '"+tag2+"')]")));
		List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(), '"+tag1+"') or contains(text(), '"+tag2+"')]"));

	       System.out.println("Number of web elements with choosen tag options: " + elements.size());
	}
	public void clickOnSearchWithID() {
		search.click();
		search.sendKeys("12");
	}
	public boolean isElementWithIDPresent() {
		return !driver.findElements(By.xpath("//td[contains(.,'12')]")).isEmpty();
	}
	public String noPostsFoundMessage() {
	return	noPostsFound.getText();
	}
	public void clickOnAddNewPostLink() {
		addNewPost.click();
	}
	public void clickOnHomeLink() {
		home.click();
	}
	public void clickOnPageLink(String page) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(.,'"+page+"')]")));
		executor.executeScript("arguments[0].click();", element);
		
		String classValue = element.getAttribute("class");
		if(classValue.contains("active")){
		    System.out.println("Element is active");
		} else if(classValue.contains("disabled")){
		    System.out.println("Element is disabled");
		} else {
		    System.out.println("Element is neither active nor disabled");
		}
		    
	}
	public void clickOnShowEntries(String entries) {
		showEntries.click();
		WebElement entriesChoose= driver.findElement(By.xpath("//option[.='"+entries+"']"));
		entriesChoose.click();
		
		String textValue = entriesChoose.getText();
		if(textValue.equals("10") || textValue.equals("25") || textValue.equals("50") || textValue.equals("100")){
		    System.out.println("It is displayed up to "+textValue+" posts.");
		} else {
		    System.out.println("Element value is not 10 or 25 or 50 or 100");
		}
	}
	public void clickOnDisableButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]//div[2]/button[1]")));
		WebElement disableButton= driver.findElement(By.xpath("//tbody[1]/tr[1]//div[2]/button[1]"));
		disableButton.click();
	}
	public void clickOnCancelDialogDisablePost() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='disable-modal']//button[@class='btn btn-default']")));
		WebElement cancelDisableButton= driver.findElement(By.xpath("//form[@id='disable-modal']//button[@class='btn btn-default']"));
		cancelDisableButton.click();
	}
	public void clickOnDisableDialogPost() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Disable')]")));
		WebElement disableDialogButton= driver.findElement(By.xpath("//button[contains(.,'Disable')]"));
	    disableDialogButton.click();
	}
	public void checkDisableEnable() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td:nth-of-type(4)")));
		WebElement element =driver.findElement(By.cssSelector("td:nth-of-type(4)"));
		String text = element.getText();
		if(text.contains("enabled")){
		    System.out.println("Element is enabled");
		} else if(text.contains("disabled")){
		    System.out.println("Element is disabled");
		} else {
		    System.out.println("Element is neither enabled nor disabled");
		}
        

	}
	public void clickOnEnableButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[12]/div[2]/button[1]")));
		WebElement enableButton= driver.findElement(By.xpath("//td[12]/div[2]/button[1]"));
		enableButton.click();
	}
	public void clickOnCancelDialogEnablePost() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='enable-modal']//button[@class='btn btn-default']")));
		WebElement cancelEnableButton= driver.findElement(By.xpath("//form[@id='enable-modal']//button[@class='btn btn-default']"));
		cancelEnableButton.click();
	}
	public void clickOnEnableDialogPost() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Enable')]")));
		WebElement enableDialogButton= driver.findElement(By.xpath("//button[contains(.,'Enable')]"));
	 enableDialogButton.click();
	}
	
	
	public void clickOnUnimportantButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[12]//button[2]")));
		WebElement unimportantButton= driver.findElement(By.xpath("//td[12]//button[2]"));
		unimportantButton.click();
	}
	public void clickOnCancelDialogUnimportantButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='unimportant-modal']//button[@class='btn btn-default']")));
		WebElement cancelUnimportantButton= driver.findElement(By.xpath("//form[@id='unimportant-modal']//button[@class='btn btn-default']"));
		cancelUnimportantButton.click();
	}
	public void clickOnSetAsUnimportantDialogButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Set as Unimportant')]")));
		WebElement unimportantDialogButton= driver.findElement(By.xpath("//button[contains(.,'Set as Unimportant')]"));
	 unimportantDialogButton.click();
	}
	public void clickOnImportantButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[12]//button[2]")));
		WebElement ImportantButton= driver.findElement(By.xpath("//td[12]//button[2]"));
		ImportantButton.click();
	}
	public void clickOnCancelDialogImportantButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='important-modal']//button[@class='btn btn-default']")));
		WebElement cancelUnimportantButton= driver.findElement(By.xpath("//form[@id='important-modal']//button[@class='btn btn-default']"));
		cancelUnimportantButton.click();
	}
	public void clickOnSetAsImportantDialogButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Set as Important')]")));
		WebElement importantDialogButton= driver.findElement(By.xpath("//button[contains(.,'Set as Important')]"));
	 importantDialogButton.click();
	}
	
	public void clickOnDeleteButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[12]/div[1]/button[@class='btn btn-info']")));
		WebElement deleteButton= driver.findElement(By.xpath("//td[12]/div[1]/button[@class='btn btn-info']"));
		deleteButton.click();
	}
	public void clickOnCancelDialogDelete() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='delete-modal']//button[@class='btn btn-default']")));
		WebElement cancelButton= driver.findElement(By.xpath("//form[@id='delete-modal']//button[@class='btn btn-default']"));
		cancelButton.click();
	}
	public void clickOnDeleteDialogButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.='Delete']")));
		WebElement cancelButton= driver.findElement(By.xpath("//button[.='Delete']"));
		cancelButton.click();
	}
	public void getMessage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-message']")));
				
		WebElement message= driver.findElement(By.xpath("//div[@class='toast-message']"));
		message.getText();
		System.out.println(message.getText());
	}
	public void noMatchesMessage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='dataTables_empty']")));
		WebElement noMatches= driver.findElement(By.xpath("//td[@class='dataTables_empty']"));
		noMatches.getText();
		System.out.println(noMatches.getText());
	}
	public void logOut() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='toast-message']")));
		
		WebElement profile= driver.findElement(By.xpath("//i[@class='far fa-user']"));
		profile.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Log Out')]")));
		WebElement logout= driver.findElement(By.xpath("//a[contains(.,'Log Out')]"));
		logout.click();
	}
}
