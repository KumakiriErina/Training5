package plugIn;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import dao.OmikujiDAO;
/**
 * 独自のplugInです
 * @author e_kumakiri
 *
 */
public class InitPlugIn implements PlugIn{
	/**
	 * 初期化をするメソッドです
	 */
	public void init(ActionServlet servlet, ModuleConfig config)
            throws ServletException {

		//OmikujiDAOのオブジェクト生成
		OmikujiDAO omikujiDao = new OmikujiDAO();

		//csvファイルを読み込んで、おみくじテーブルに値を入れる処理
		omikujiDao.insertOmikuji();
    }
	/**
	 * 後処理をするメソッドです
	 */
	public void destroy() {
		//DB接続はOmikujiDAOで切っているので、何もしない
    }
}
