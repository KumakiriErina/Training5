package form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import omikuji.Omikuji;
/**
 * 割合のBeanです
 * @author e_kumakiri
 */
public class RatioForm extends ActionForm {

	/**割合のフィールドです*/
	private List<Omikuji> ratio;

	/**
	 * 割合を取得する
	 * @return 割合
	 */
	public List<Omikuji> getRatio() {
		return ratio;
	}

	/**
	 * 割合を設定する
	 * @param ratio 割合
	 */
	public void setRatio(List<Omikuji> ratio) {
		this.ratio = ratio;
	}
}
