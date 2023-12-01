<%@page import="java.util.ArrayList"%>
<%@page import="form.ResultForm"%>
<%@ page import="java.util.List"%>
<%@ page import="omikuji.Omikuji"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>最初に入力された誕生日の過去半年の結果</title>
</head>
<body>
	<p>
		<font color="#ba55d3">最初に入力された誕生日の過去半年の結果はこちらです</font>
	</p>
	<table border="1">

		<tr>
			<th>占い日付</th>
			<th>運勢</th>
			<th>願い事</th>
			<th>商い</th>
			<th>学問</th>

		</tr>
		<logic:iterate id="data" name="ResultForm" property="result">
			<tr>

				<td><bean:write name="data" property="fortuneDate" /></td>
				<td><bean:write name="data" property="unsei" /></td>
				<td><bean:write name="data" property="negaigoto" /></td>
				<td><bean:write name="data" property="akinai" /></td>
				<td><bean:write name="data" property="gakumon" /></td>

			</tr>
		</logic:iterate>
	</table>
	<!-- 画面遷移 -->
	<html:link action="/ReturnAction">誕生日入力に戻る</html:link>
	
</body>
</html>