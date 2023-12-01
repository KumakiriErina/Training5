<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="form.RatioForm"%>
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
<title>全体の過去半年と本日の占い結果の割合の結果</title>
</head>
<body>
	<p>
		<font color="#4169e1">全体の過去半年と本日の占い結果の割合はこちらです</font>
	</p>
	<table>

		<tr>
			<th>運勢名</th>
			<th>割合</th>

		</tr>
		<logic:iterate id="data" name="RatioForm" property="ratio">
			<tr>

				<td><bean:write name="data" property="unsei" /></td>
				<td><bean:write name="data" property="unseiNameRatio" /></td>

			</tr>
		</logic:iterate>
	</table>
	<!-- 画面遷移 -->
	<html:link action="/ReturnAction">誕生日入力に戻る</html:link>
</body>
</html>