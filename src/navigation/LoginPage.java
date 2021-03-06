package navigation;

import org.openqa.selenium.WebDriver;

import core.Element;
import core.Page;
import core.Element.SEARCH_TYPE;
import exception.NotFoundElementException;

/** Login Page of SigSaude
 * 
 * @author SOS
 * 
 */
public class LoginPage extends Page{
	
	/** Constructor of LoginPage object
	 * 
	 * @param nDriver Driver needed to connect to the page 
	 */
	public LoginPage(WebDriver nDriver, String url) {
		super(nDriver);
		
		nDriver.get(url);
	
		try {
			this.elements.put("LOGIN", new Element(this.driver,"login-username",SEARCH_TYPE.BY_ID));
			this.elements.put("PASSWORD", new Element(this.driver,"login-password",SEARCH_TYPE.BY_ID));
			this.elements.put("ENTRAR", new Element(this.driver,"btn",SEARCH_TYPE.BY_CLASS));
			//this.elements.put("ESQUECEU_SENHA", new Element(this.driver,"forgot-pass",SEARCH_TYPE.BY_CLASS));
			//this.elements.put("CADASTRA_SE", new Element(this.driver,"signup",SEARCH_TYPE.BY_CLASS));	
			
		}catch(NotFoundElementException e) {
			System.err.println("Not Found element " + e.getName() + " using the type search " + e.getType().name());
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/** Gets the login input text
	 * 
	 * @return A Element representing the login input text
	 */
	public Element getInputLogin() {
		return this.elements.get("LOGIN");
	}
	
	/** Gets the password input text
	 * 
	 * @return A Element representing the password input text
	 */
	public Element getInputPassword() {
		return this.elements.get("PASSWORD");
	}
	
	/** Gets the entrar button
	 * 
	 * @return A Element representing the entrar button
	 */
	public Element getButtonEntrar() {
		return this.elements.get("ENTRAR");
	}
	
	/** Gets the Esqueceu senha link
	 * 
	 * @return A Element representing the Esqueceu senha link
	 */
	public Element getLinkEsqueceuSenha() {
		return this.elements.get("ESQUECEU_SENHA");
	}
	
	/** Gets the Cadastre-se link
	 * 
	 * @return A Element representing the Cadastre-se link
	 */
	public Element getLinkCadastreSe() {
		return this.elements.get("CADASTRA_SE");
	}
	
	/** Execute system authentication 
	 * 
	 * @param login Value of user name
	 * @param senha Value of password
	 * 
	 * @return If there was a login, it will return a IndexPage object, otherwise it will return a LoginPage object
	 */
	public Page executeAuthentication(String login, String senha) {
		
		this.elements.get("LOGIN").sendKeys(login);
		this.elements.get("PASSWORD").sendKeys(senha);
		this.elements.get("ENTRAR").submit();
		
		if(this.getURL().contains("index")) {
			return new IndexPage(driver);
		}else {
			return this;
		}
	}	
}

