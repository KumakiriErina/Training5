<%@page import="omikuji.Omikuji"%>
<%@page import="dao.OmikujiDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<logic:notPresent name="birthday" scope="session">
	<logic:redirect forward="fail" />
</logic:notPresent>

<%
//リクエストスコープから値を取得
Omikuji omikujiDao = (Omikuji) request.getAttribute("omikuji");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>占いの結果</title>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="2">今日の運勢は<font color="#ffd700"><%=omikujiDao.getUnsei()%></font>です
			</th>
		</tr>
		<tr>
			<td>願い事</td>
			<td><%=omikujiDao.getNegaigoto()%></td>
		</tr>
		<tr>
			<td>商い</td>
			<td><%=omikujiDao.getAkinai()%></td>
		</tr>
		<tr>
			<td>学問</td>
			<td><%=omikujiDao.getGakumon()%></td>
		</tr>
	</table>
	<!-- 画面遷移 -->
	<html:link action="/ReturnAction">誕生日入力に戻る</html:link>

	<html:link action="/OmikujiRatioAction">全体の過去半年と本日の占い結果の割合</html:link>

	<html:link action="/HalfYearAllOmikujiAction">最初に入力された誕生日の過去半年の結果</html:link>

</body>
</html>