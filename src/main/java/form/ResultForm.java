package form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import omikuji.Omikuji;
/**
 * おみくじ結果のBeanです
 * @author e_kumakiri
 */
public class ResultForm extends ActionForm {

	/**おみくじの結果のフィールドです*/
	protected List<Omikuji> result;

	/**
	 * おみくじの結果を取得します
	 * @return result おみくじの結果
	 */
	public List<Omikuji> getResult() {
		return result;
	}

	/**
	 * おみくじの結果を設定します
	 * @param result　おみくじの結果
	 */
	public void setResult(List<Omikuji> result) {
		this.result = result;
	}
}
