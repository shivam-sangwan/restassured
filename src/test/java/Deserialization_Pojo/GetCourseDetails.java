package Deserialization_Pojo;

public class GetCourseDetails {
	//creating pojo class for "OathTest" class response..
	//..to extract values from that response using deserializaion
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;  //note: courses contains nested Json..so we need to create...
	private String linkedIn; //...separate pojo class for Courses
	
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	
	//return type is 'Courses' bcoz method is returning ref. variable of courses class
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	
	//to get getter and setter methods: select all above variable and press: alt+shift+s
	

}
