package Deserialization_Pojo;

import java.util.List;

public class Courses {
	//separate pojo class for courses variable of 'GetCourseDetails' pojo class
	
	//below 3 elements also contains nested json..so we need to create...
	//separate pojo class for them also
	private List<Webautomation> webAutomation;  //Webautomation contains multiple items, so.. 
	private List<Api> api;           //..we used ArrayList(stores dynamic elements)
	private List<Mobile> mobile;
	
	
	public List<Webautomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<Webautomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}

	
	
	
	
	
	
	
	
}
