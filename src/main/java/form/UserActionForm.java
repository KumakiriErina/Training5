package form;

import org.apache.struts.validator.ValidatorForm;
/**
 * 入力した誕生日のリクエストデータを保持するBeanです
 * @author e_kumakiri
 */
public class UserActionForm extends ValidatorForm{

	/** 誕生日のフィールドです*/
	private String birthday;

	/**
	 * 誕生日を取得します
	 * @return birthday　誕生日
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 誕生日を設定します
	 * @param birthday 誕生日
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
