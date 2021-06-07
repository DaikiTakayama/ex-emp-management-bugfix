package jp.co.sample.emp_management.form;

import javax.validation.constraints.NotBlank;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author daiki.takayama
 * 
 */
public class InsertAdministratorForm {
	@NotBlank(message="名前を入力してください")
	/** 名前 */
	private String name;
	@NotBlank(message="メールアドレスを入力してください")
	/** メールアドレス */
	private String mailAddress;
	@NotBlank(message ="パスワードを入力してください")
	/** パスワード */
	private String password;
	@NotBlank(message ="確認用パスワードを入力してください")
	/** 確認用パスワード */
	private String checkPassword;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", checkPassword=" + checkPassword + "]";
	}
	


	
}
