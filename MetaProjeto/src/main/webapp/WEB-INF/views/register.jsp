<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="snp" tagdir="/WEB-INF/tags/snippets/" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Profetas - Registrar</title>
		
		<!-- Import dos styles CSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value = "/assets/css/main.css"/>" />		
		
	</head>
	<body>
		<div id="global">
			<div id="header">
				<h2>Cadastro de usuários:</h2>
			</div>
			<div id="middle">
				<form:form action="/createAccount" method="post" commandName="newUser">
					<table>
						<tr>
							<td><form:label path="nickname">Apelido:</form:label></td>
							<td><form:input path="nickname" />
						</tr>
						<tr>
							<td><form:label path="username">E-mail:</form:label></td>
							<td><form:input path="username" type="email"/>
						</tr>
						<tr>
							<td><form:label path="password">Senha:</form:label></td>
							<td><form:password path="password"/>
						</tr>
						<tr>
							<td><form:label path="password">Repita a senha:</form:label></td>
							<td><form:password path="password"/>
						</tr>
						<tr>
							<td><form:button type="submit">Registrar</form:button></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="footer">
				<snp:footer />
			</div>
		</div>
	</body>
</html>