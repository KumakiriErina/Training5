<%--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    (the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at
   
         http://www.apache.org/licenses/LICENSE-2.0
   
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
--%>
<%@page language = "java" contentType = "text/html; charset = UTF-8"
	pageEncoding = "UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<!DOCTYPE html>
<html>
	<head>
	<title>誕生日入力画面</title>
	</head>
	<font color="red"><html:errors/></font>
		<body>
			<html:form action = "/InputBirthdayAction" method = "post">
				誕生日を入力してください（例:20150809）：
				<html:text styleClass = "birthday" property = "birthday" />
				<html:submit styleClass = "success" property = "submit" value = "送信" />
			</html:form>
		</body>
</html>

<%--

Redirect default requests to Welcome global ActionForward.
By using a redirect, the user-agent will change address to match the path of our Welcome ActionForward. 

--%>
