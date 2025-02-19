package Deserialization_Pojo;

public class Api {
	//separate pojo class for Api variable of 'Course' pojo class
	
			private String courseTitle;
			private String price;
			
			
			public String getCourseTitle() {
				return courseTitle;
			}
			public void setCourseTitle(String courseTitle) {
				this.courseTitle = courseTitle;
			}
			public String getPrice() {
				return price;
			}
			public void setPrice(String price) {
				this.price = price;
			}
}
