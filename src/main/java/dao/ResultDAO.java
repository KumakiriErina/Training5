package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omikuji.BadLuck;
import omikuji.GoodBlessing;
import omikuji.GreatBlessing;
import omikuji.MiddleBlassing;
import omikuji.Omikuji;
import omikuji.SmallBlessing;
import omikuji.UncertinLuck;
/**
 * resultテーブルです
 * 
 * おみくじの結果をDBに登録
 * 最初に入力された誕生日の過去半年の結果を取得
 * 全体の過去半年と本日の占い結果の割合の取得
 * をします
 * 
 * @author e_kumakiri
 *
 */
public class ResultDAO {

	//データベース接続に使用する情報
	/** ドライバクラス名 */
	private static final String DRIVER = "org.postgresql.Driver";

	/**接続するDBのURL*/
	private final String URL = "jdbc:postgresql://localhost:5432/banana";

	/**接続するためのユーザ名*/
	private final String USER_NAME = "postgres";

	/**接続するためのパスワード*/
	private final String PASSWORD = "kumakiri2005";

	//DBに接続するために宣言
	Connection connection = null;

	//PreparedStatemenrの準備
	PreparedStatement preparedStatement = null;

	//ResultSetの準備
	ResultSet resultSet = null;

	//フォーマットを整えるための準備
	SimpleDateFormat simpleDateFormat = null;

	/**
	 * 2つのDate型を受け取り、おみくじの結果を登録しています
	 * 
	 * @param date 現在のDate型
	 * @param inputBirthday 入力した誕生日のDate型
	 * @param omikujiCode　おみくじコード
	 */
	public void insertResult(Date date, Date inputBirthday, String omikujiCode) {

		try {

			//JDBCドライバクラスをJVMに登録
			Class.forName(DRIVER);

			//データベースへ接続
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			//接続に成功した場合に表示
			if(connection != null) {
				System.out.println("結果テーブルに登録するため、DBと接続しました");
			}

			//結果テーブルにおみくじの内容を登録(resultテーブルにINSERTする)
			String sqlInsertResult = "INSERT INTO result VALUES(?, ?, ?, 'kumakiri', CURRENT_DATE, 'kumakiri', CURRENT_DATE)";

			//ステートメントの作成
			PreparedStatement preparedStatement3 = connection.prepareStatement(sqlInsertResult);

			//占った日をjava.util.Dateから、java.sql.Dateへ変換
			java.sql.Date dateConvertDate = new java.sql.Date(date.getTime());

			//誕生日をjava.util.Dateから、java.sql.Dateへ変換
			java.sql.Date dateConvertInputDate = new java.sql.Date(inputBirthday.getTime());

			//SQL中の各プレースホルダーに入力値をバインド
			preparedStatement3.setDate(1, dateConvertDate); //占った日
			preparedStatement3.setDate(2, dateConvertInputDate); //誕生日
			preparedStatement3.setString(3, omikujiCode);//おみくじコードの取得

			//SQL文を実行(登録の際はUpdate)
			preparedStatement3.executeUpdate();

		}catch(SQLException se) {
			//DB関係でエラーがあった場合
			System.out.println("DB関係でエラーです");
				se.printStackTrace();

		}catch(ClassNotFoundException ce) {
			//ドライバクラスが見つからなかった場合
			System.out.println("ドライバクラスが見つかりません");
			ce.printStackTrace();

		}finally {
			//DBと接続を切断
			disconnect();
		}
	}

	/**
	 * 最初に入力された誕生日の過去半年の結果を取得するメソッドです
	 * @param　birthday フォームに入力した誕生日
	 * @return resultList　最初に入力された誕生日の過去半年の結果
	 */
	public List<Omikuji> OmikujiInformation(String birthday) {

		//おみくじ情報をListに格納
		List<Omikuji> resultList = new ArrayList<>();
		
		//おみくじオブジェクトの宣言
		Omikuji omikuji = null;

		try {

			//JDBCドライバクラスをJVMに登録
			Class.forName(DRIVER);

			//データベースへ接続
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			//接続に成功した場合に表示
			if(connection != null) {
				System.out.println("結果テーブルに登録するため、DBと接続しました");
			}

			//結果、運勢マスタ、おみくじテーブルから最初に入力された誕生日の過去半年の結果を取得(SELECT）
			String result = "SELECT fortune_date, unsei_name, negaigoto, akinai, gakumon FROM Omikuji  LEFT OUTER JOIN result ON Omikuji.omikuji_code = result.omikuji_code LEFT OUTER JOIN mst_unsei ON Omikuji.unsei_code = mst_unsei.unsei_code WHERE fortune_date > now() - interval '6 months' AND birthday = ? ";

			//ステートメント作成（オブジェクト生成）
			PreparedStatement preparedStatement4 = connection.prepareStatement(result);

			//入力した誕生日をDate型に変換する準備（フォーマット）
			simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

			//入力した誕生日をDate型に変換
			Date convertBirthday = simpleDateFormat.parse(birthday);

			//java.util.Dateから、java.sql.Dateへ変換
			java.sql.Date dateConvert = new java.sql.Date(convertBirthday.getTime());

			//値をバインド
			preparedStatement4.setDate(1, dateConvert);

			//SQLを実行(preparedStatementのオブジェクトが代入される)
			ResultSet resultSet = preparedStatement4.executeQuery();

			//resultSetがnullでない場合に実行する
			if(resultSet != null) {

				while (resultSet.next()) {

					//全体の過去半年と本日の占い結果を取得
					String fortuneDate = resultSet.getString("fortune_date");
					String unsei = resultSet.getString("unsei_name");
					String negaigoto = resultSet.getString("negaigoto");
					String akinai = resultSet.getString("akinai");
					String gakumon = resultSet.getString("gakumon");


					//switch文(具象クラスの生成)
					switch (unsei) {
					case "大吉":
						//それぞれのオブジェクトを生成
						omikuji = new GreatBlessing();
						break;

					case "中吉":
						omikuji = new MiddleBlassing();
						break;

					case "小吉":
						omikuji = new SmallBlessing();
						break;

					case "末吉":
						omikuji = new UncertinLuck();
						break;

					case "吉":
						omikuji = new GoodBlessing();
						break;

					case "凶":
						omikuji = new BadLuck();
						break;

					default:
						System.out.println("飛ばします");
						break;
					}

					//おみくじオブジェクトに値をset
					omikuji.setFortuneDate(fortuneDate);
					omikuji.setUnsei();
					omikuji.setNegaigoto(negaigoto);
					omikuji.setAkinai(akinai);
					omikuji.setGakumon(gakumon);

					//resultListにおみくじオブジェクトをset
					resultList.add(omikuji);
				}
			}

		}catch(SQLException se) {
			//DB関係でエラーがあった場合
			System.out.println("DB関係でエラーです");
			se.printStackTrace();

		}catch(ClassNotFoundException ce) {
			//ドライバクラスが見つからなかった場合
			System.out.println("ドライバクラスが見つかりません");
			ce.printStackTrace();

		}catch(ParseException pe) {
			//Date型への変換がうまくいかなかった場合
			System.out.println("変換がうまくいきませんでした");
			pe.printStackTrace();

		}finally {
			//DBと接続を切断
			disconnect();
		} 
		//おみくじ情報を返す
		return resultList;
	}

	/**
	 * 全体の過去半年と本日の占い結果の割合を取得するメソッドです
	 * @return ratio 割合
	 */
	public List<Omikuji> OmikujiRatio() {

		//割合の情報をListに格納
		List<Omikuji> ratio = new ArrayList<>();
		
		//おみくじオブジェクトの宣言
		Omikuji omikuji = null;

		try {

			//JDBCドライバクラスをJVMに登録
			Class.forName(DRIVER);

			//データベースへ接続
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			//接続に成功した場合に表示
			if(connection != null) {
				System.out.println("結果テーブルに登録するため、DBと接続しました");
			}

			//結果、運勢マスタ、おみくじテーブルから全体の過去半年と本日の占い結果を取得(SELECT）
			String resultRatio = "SELECT unsei_name, ROUND(CAST(CAST(COUNT(result.omikuji_code) AS numeric) / (SELECT COUNT(omikuji_code) FROM result) * 100 AS numeric),2) as unsei_name_ratio FROM mst_unsei LEFT JOIN Omikuji  ON mst_unsei.unsei_code = Omikuji.unsei_code LEFT JOIN result ON Omikuji.omikuji_code = result.omikuji_code AND result.fortune_date >= now() - interval '6 months' GROUP BY mst_unsei.unsei_name ORDER BY unsei_name_ratio DESC;";

			//ステートメント（SQL文を受け取って実行）
			Statement statement2 = connection.createStatement();
			
			//SQLを実行(preparedStatementのオブジェクトが代入される)
			ResultSet resultSet = statement2.executeQuery(resultRatio);
			
			while (resultSet.next()) {

				//運勢名と各運勢の割合を取得
				String unsei = resultSet.getString("unsei_name");
				Double unseiNameRatio = resultSet.getDouble("unsei_name_ratio");
				
				//switch文(具象クラスの生成)
				switch (unsei) {
				case "大吉":
					//それぞれのオブジェクトを生成
					omikuji = new GreatBlessing();
					break;

				case "中吉":
					omikuji = new MiddleBlassing();
					break;

				case "小吉":
					omikuji = new SmallBlessing();
					break;

				case "末吉":
					omikuji = new UncertinLuck();
					break;

				case "吉":
					omikuji = new GoodBlessing();
					break;

				case "凶":
					omikuji = new BadLuck();
					break;

				default:
					System.out.println("飛ばします");
					break;
				}

				//おみくじオブジェクトに値をset
				omikuji.setUnsei();
				omikuji.setUnseiNameRatio(unseiNameRatio);

				//resultListにおみくじオブジェクトをset
				ratio.add(omikuji);
			}

		}catch(SQLException se) {
			//DB関係でエラーがあった場合
			System.out.println("DB関係でエラーです");
			se.printStackTrace();

		}catch(ClassNotFoundException ce) {
			//ドライバクラスが見つからなかった場合
			System.out.println("ドライバクラスが見つかりません");
			ce.printStackTrace();

		}finally {
			//DBと接続を切断
			disconnect();
		}

		//割合の情報を返却
		return ratio;
	}

	/**
	 * DBとの接続を切断する処理です
	 */
	private void disconnect() {
		try {
			if(resultSet != null) {
				//nullじゃなかったら閉じる
				resultSet.close();
			}
			if(preparedStatement != null) {
				//nullじゃなかったら閉じる
				preparedStatement.close();
			}
			if(connection != null) {
				//nullじゃなかったら閉じる
				connection.close();
			}

		}catch(SQLException se) {
			//DB関係でエラーがあった場合
			System.out.println("DB関係でエラーです");
			se.printStackTrace();
		}
	}
}
