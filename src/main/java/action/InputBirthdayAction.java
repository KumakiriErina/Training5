package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import dao.OmikujiDAO;
import dao.ResultDAO;
import form.UserActionForm;
import omikuji.Omikuji;
/**
 * おみくじの結果を/jsp/result.jspに渡す処理をします
 * @author e_kumakiri
 *
 */
public final class InputBirthdayAction extends Action {

	/**
	 * 実行するメソッドです
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		//userActionFormから誕生日を取得
		UserActionForm userActionForm = (UserActionForm) form;
		String birthday = userActionForm.getBirthday();

		//日付入力のフォーマットの宣言
		SimpleDateFormat simpleDateFormat = null;

		//現在の日付を準備
		Date date = null;

		//ランダムクラスの準備
		Random rand = null;

		//おみくじの宣言
		Omikuji omikuji = null;

		try {

			//日付入力のフォーマット（yyyyMMdd）の生成
			simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

			//入力された値が正しい（存在している）かチェック
			simpleDateFormat.setLenient(false);

			//フォームに入力した日付をDate型に変換
			Date inputBirthday = simpleDateFormat.parse(birthday);

			//入力した日付をフォーマット
			birthday = simpleDateFormat.format(inputBirthday);

			//Date型（現在）の生成
			date = new Date();

			//Date型(現在)をString型に変換
			String now = new SimpleDateFormat("yyyyMMdd").format(date);

			//ランダムオブジェクトを生成する（本日と入力した値のString型をInteger型に変換）
			rand = new Random(Integer.parseInt(now) + Integer.parseInt(birthday));

			//OmikujiDAOのオブジェクト生成
			OmikujiDAO omikujiDao = new OmikujiDAO();

			//ランダムオブジェクトをOmikujiDAOに渡す
			omikuji = omikujiDao.findOmikuji(rand);

			//ResultDAOのオブジェクト生成
			ResultDAO resultDao= new ResultDAO();

			//今日と、入力した日付型と、おみくじオブジェクトからおみくじコードを渡す
			resultDao.insertResult(date, inputBirthday, omikuji.getOmikujiCode());

			//birthdayをsessionから取得する準備
			HttpSession session = request.getSession();

			//セッションスコープに入力した値（誕生日）を保存
			session.setAttribute("birthday", birthday);

		}catch(ParseException pe) {
			//Date型への変換がうまくいかなかった場合
			ActionErrors errors = new ActionErrors();

			//messageを格納するリストの作成して、キー値でpropertiesを検索している
			ActionMessage msg = new ActionMessage("errors.msg.key1");
			errors.add(ActionErrors.GLOBAL_MESSAGE, msg);

			//親クラスのメソッドを呼び出す
			addErrors(request, errors);	

			//index.jspに遷移する
			return mapping.findForward("fail");
		}

		//おみくじの結果を/jsp/result.jspに渡す
		request.setAttribute("omikuji", omikuji);

		return mapping.findForward("success");
	}
}
