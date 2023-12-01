package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.ResultDAO;
import form.ResultForm;
import omikuji.Omikuji;
/**
 * 最初に入力された誕生日の過去半年の結果をリスト表示させるために
 * beanから/jsp/halfYearAllOmikuji.jspに値を渡す処理をします
 * @author e_kumakiri
 *
 */
public class HalfYearAllOmikujiAction extends Action{

	/**
	 * 実行するメソッドです
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception{

		//resultDAOオブジェクトの生成
		ResultDAO resultDAO = new ResultDAO();

		//birthdayをsessionから取得する準備
		HttpSession session = request.getSession();

		//session(object型)なので、型変換（キーを指定）
		String birthday = (String) session.getAttribute("birthday");

		//resultDaoの呼び出し（戻り値を変数に保存）
		List<Omikuji> result = resultDAO.OmikujiInformation(birthday);

		//formの使用
		ResultForm resultForm = new ResultForm();

		//formにlistの値をsetする
		resultForm.setResult(result);

		//リクエストスコープに値を保存
		request.setAttribute("ResultForm", resultForm);

		return mapping.findForward("success");
	}
}