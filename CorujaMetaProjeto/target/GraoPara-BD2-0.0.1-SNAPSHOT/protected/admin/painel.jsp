<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="webview.worker.PanelWorker"%>
<%@page import="webview.util.WebUtility"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Grão-Pará</title>
		
		<!-- Import dos styles CSS -->
		<link rel="stylesheet" type="text/css" href="/GraoPara/css/principal.css" />
		<link rel="stylesheet" type="text/css" href="/GraoPara/css/controle.css" />
		<link rel="stylesheet" type="text/css" href="/GraoPara/css/tabs.css" />
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css">
		
		<!-- CSS das validacoes -->
		<link rel="stylesheet" type="text/css" media="screen" href="/GraoPara/css/styleValidation.css" />
		
		<!-- Import dos javascripts -->
		<script type="text/javascript" src="/GraoPara/javascript/ajax.js" charset="utf-8"></script>
		<script type="text/javascript" src="/GraoPara/javascript/instrucao.js" charset="utf-8"></script>
		<script src="/GraoPara/javascript/chili-1.7.pack.js" type="text/javascript"></script>
		
		<script src="http://code.jquery.com/jquery-1.8.2.js" type="text/javascript"></script>
		<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js" type="text/javascript"></script>
		
		<!-- Import dos scripts de validacao de formulario -->
		<!-- <script src="/GraoPara/javascript/jquery.js" type="text/javascript" charset="utf-8"></script> -->
		<script src="/GraoPara/javascript/validate.js" type="text/javascript" charset="utf-8"></script>
		<script src="/GraoPara/javascript/validate_pt_br.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="container">
			<div class="header"></div>
	
			<!-- Menu lateral -->
			<div class="sidebar1">
				
				<!-- Area de login -->
				<%@include file="/WEB-INF/templates/loginReception.jsp"%>
	
				<!-- Area de menu -->
				<%@include file="/WEB-INF/templates/adminMenu.jsp"%>
			</div>
	
			<div class="content" id="content">
				<h1>Painel de Controle</h1>	
				
				<!-- Formulario para alterar senha -->
				<form id="signupform" autocomplete="off" action="/GraoPara/doChangesToAccount?action=editPassword" method="post">
					<%@include file="/WEB-INF/templates/newPasswordForm.jsp"%>
				</form><br><br>
	
				<div id="tabsPainel" class="tableForms">
					<ul>
						<li><a href="#tab1"><span Style="font-size:small;">Novos Usuários</span></a></li>
						<li><a href="#tab2"><span Style="font-size:small;">Todos os Usuários</span></a></li>
					</ul>
					
					<div id="tab1">
						<div class="scroll">
							<!-- <form action=""> -->
								<table class="tablePainel">
									
									<!-- Lista de Usuarios Cadastrados  -->
									<%@include file="/WEB-INF/templates/registeredUserDetails.jsp"%>
																		
									<tbody><%PanelWorker.listAllNewUsers(request, out);%></tbody>
								</table>
						</div>
					</div>
					
					<div id="tab2">
						<div class="scroll">
							<table class="tablePainel">
							
								<!-- Lista de Usuarios Cadastrados  -->
								<%@include file="/WEB-INF/templates/registeredUserDetails.jsp"%>
																	
								<tbody><%PanelWorker.listAllUsers(request, out);%></tbody>
							</table>
						</div>
					</div>
				</div><br>
				
			</div>
			
			<!-- Rodape -->
			<%@include file="/WEB-INF/templates/footer.jsp"%>
		</div>
	
		<script>$("#tabsPainel").tabs();</script>
	</body>
</html>