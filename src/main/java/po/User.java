package po;
/**
* @author zuoxinlei
* @version 创建时间：2020年6月16日 下午2:04:31
* 这是一个user的实体类
*/
public class User {
	private int userId;
	private String userName;
	private String passWord;
	private String telephone;
	private String email;
	private String role;
	private String question;
	private String answer;
	private String createTime;
	private String updateTime;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public User(int userId, String userName, String passWord, String telephone, String email, String role,
			String question, String answer, String createTime, String updateTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.telephone = telephone;
		this.email = email;
		this.role = role;
		this.question = question;
		this.answer = answer;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", telephone="
				+ telephone + ", email=" + email + ", role=" + role + ", question=" + question + ", answer=" + answer
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
}
