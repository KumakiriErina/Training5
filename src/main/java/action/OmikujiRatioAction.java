package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.ResultDAO;
import form.RatioForm;
import omikuji.Omikuji;
/**
 * 全体の過去半年と本日の占い結果の割合を
 * beanから/jsp/omikujiRatio.jspに渡す処理をします
 * @author e_kumakiri
 *
 */
public class OmikujiRatioAction  extends Action{
	/**
	 * 実行するメソッドです
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

			//resultDAOオブジェクトの生成
			ResultDAO resultDAO = new ResultDAO();

			//resultDaoの呼び出し（戻り値を変数に保存）
			List<Omikuji> ratio = resultDAO.OmikujiRatio();
			
			//formの使用
			RatioForm ratioForm = new RatioForm();

			//formにlistの値をsetする
			ratioForm.setRatio(ratio);

			//リクエストスコープに値を保存
			request.setAttribute("RatioForm", ratioForm);

			return mapping.findForward("success");
	}
	

}
