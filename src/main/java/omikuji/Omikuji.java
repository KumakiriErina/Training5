package omikuji;

/**
 * おみくじ抽象クラス
 * @author e_kumakiri
 */
public abstract class Omikuji implements Fortune {

	/**おみくじコードを表す*/
	protected String omikujiCode;

	/** 運勢を表す */
	protected String unsei;

	/**占い日を表す*/
	protected String fortuneDate;

	/**割合を表す*/
	protected double unseiNameRatio;

	/** 願い事を表す */
	protected String negaigoto;

	/** 商いを表す */
	protected String akinai;

	/** 学問を表す */
	protected String gakumon;

	/** 各運勢クラスで運勢をsetする */
	public abstract void setUnsei();

	/**
	 * おみくじコードをgetするためのメソッドです。
	 * @return おみくじコード
	 */
	public String getOmikujiCode() {
		return omikujiCode;
	}

	/**
	 * おみくじコードをsetするためのメソッドです。
	 * @param omikujiCode　おみくじコード
	 */
	public void setOmikujiCode(String omikujiCode) {
		this.omikujiCode = omikujiCode;
	}

	/**
	 * 占い日をgetするためのメソッドです。
	 * @return 占い日
	 */
	public String getFortuneDate() {
		return fortuneDate;
	}

	/**
	 * 占い日をsetするためのメソッドです。
	 * @param fortuneDate 占い日
	 */
	public void setFortuneDate(String fortuneDate) {
		this.fortuneDate = fortuneDate;
	}

	/**
	 * 割合をgetするためのメソッドです。
	 * @return 割合
	 */
	public double getUnseiNameRatio() {
		return unseiNameRatio;
	}

	/**
	 * 割合をsetするためのメソッドです。
	 * @param unseiNameRatio 割合
	 */
	public void setUnseiNameRatio(double unseiNameRatio) {
		this.unseiNameRatio = unseiNameRatio;
	}

	/**
	 * 願い事をsetするためのメソッドです。
	 * @param negaigoto  願い事
	 */
	public void setNegaigoto(String negaigoto) {
		this.negaigoto = negaigoto;
	}

	/**
	 * 商いをsetするためのメソッドです。
	 * @param akinai 商い
	 */
	public void setAkinai(String akinai) {
		this.akinai = akinai;
	}

	/**
	 * 学問をsetするためのメソッドです。
	 * @param gakumon 学問
	 */
	public void setGakumon(String gakumon) {
		this.gakumon = gakumon;
	}

	/**
	 * 運勢を取得するためのメソッドです。
	 * @return 運勢
	 */
	public String getUnsei() {
		return this.unsei;
	}

	/**
	 * 願い事を取得するためのメソッドです。
	 * @return 願い事
	 */
	public String getNegaigoto() {
		return this.negaigoto;
	}

	/**
	 * 商いを取得するためのメソッドです。
	 * @return 商い
	 */
	public String getAkinai() {
		return this.akinai;
	}

	/**
	 * 学問を取得するためのメソッドです。
	 * @return 学問
	 */
	public String getGakumon() {
		return this.gakumon;
	}

	/**
	 * 運勢を表示するためのメソッドです。
	 * @return 運勢の表示
	 */
	public String disp() {
		String str = String.format(DISP_STR, getUnsei()) + System.lineSeparator() +"願い事:" +getNegaigoto() + System.lineSeparator() + "商い:" + getAkinai() + System.lineSeparator() + "学問:" + getGakumon();
		return str;
	}
}
