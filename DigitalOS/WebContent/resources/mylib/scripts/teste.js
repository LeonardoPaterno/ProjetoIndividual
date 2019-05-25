function estrutura(){
var html = 
   "<div class='input-group input-group-barra-pesquisa'>" +
		"<input type='text' class='form-control' placeholder='Pesquisar...' id='pesquisaUsuarioInput' name='pesquisaUsuarioInput'>"+
		"<span class='btn input-group-addon icone-pesquisar glyphicon glyphicon-search' onclick='SENAI.usuario.pesquisarUsuario()'></span>"+
	   "<div class='btn-group filtroAtivo'>"+
		   "<select class='btn-default form-control' id='filtroUsuario' onchange='SENAI.usuario.filtroUsuario()'>"+
			"<option value='2'selected>Todos</option>"+
			"<option value='1'selected>Ativos</option>"+
			"<option value='0'selected>Inativos</option>"+
		   "</select>"+
	   "</div>" +
	   "<div class='teble-reponsive2'>" +
	   "<table class='table table-striped table-condensed table-bordered'>"+
	   "<div id='tabelaUser' onchange='SENAI.usuario.tabelaUsuario()'></div>";
	tabelaUsuario();

}

function tabelaUsuario(){
var html ="<table class='table table-striped table-condensed table-bordered'>"+
			"<tr>" +
				"<th>Nome</th> <th>Sobrenome</th> <th>Email</th> <th>Senha</th> <th>Confirmar Senha</th> <th>Status</th> <th>Editar</th>" +
			"</tr>";
			if(listaDeUsuarios != undefined && listaDeUsuarios.length > 0 && listaDeUsuarios[0].id != undefined){
				for(var i = 0; i < listaDeUsuarios.length; i++){

					html +="<tr>" +
								"<td>" + listaDeUsuarios[i].nomeusr + "</td>" +
								"<td>" + listaDeUsuarios[i].sobrenomeusr + "</td>" +
								"<td>" + listaDeUsuarios[i].emailusr + "</td>" +
								"<td>" + listaDeUsuarios[i].senhausr + "</td>" +
								"<td>" + listaDeUsuarios[i].confsenhausr + "</td>" +
								"<td>" + listaDeUsuarios[i].statususr + "</td>"+
							"</tr>";
				};
			}
	html+=	"</table>"+
				"</div>"+
					"</table>";			
}
				