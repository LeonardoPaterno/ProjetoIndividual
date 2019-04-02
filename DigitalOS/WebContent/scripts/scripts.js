function loginOS(){
	var user = $("input[name=usuario]").val();
	var password = $("input[name=senha]").val();
	
	alert(user + " | " + password);
	
	if(user != "" && user != null && password != "" && password != null){
		var passCoded = btoa(password);
		
		$.ajax({
			type: 'POST',
			url: 'servletLoginOs',
			data: 'user=' + user + 'passCoded=' + passCoded,
			
			success:function(resposta){
				
			},
			error:function(resposta){
				
			}
		});
	}else{
		
	}
}

function CadastroUsuario(){
		
	if( $("input[name=tipocliente]").val() == null){
		alert("Campo Tipo Cliente Vazio, seleciona uma opção!");
		return false;
	}
	if($("input[name=nomecliente]").val() == null){
		alert("Campo Nome Completo Vazio, seleciona uma opção!");
		return false;
	}
	if(  $("input[name=cpfcliente]").val() == null){
		alert("Campo CPF Vazio, seleciona uma opção!");
		return false;
	}
	if( $("input[name=rgcliente]").val() == null){
		alert("Campo RG Vazio, seleciona uma opção!");
		return false;
	}
	if( $("input[name=emailcliente]").val() == null){
		alert("Campo E-mail Vazio, seleciona uma opção!");
		return false;
	}
	if(  $("input[name=telefonecliente]").val() == null){
		alert("Campo Telefone Vazio, seleciona uma opção!");
		return false;
	}
	if( $("input[name=enderecocliente]").val() == null){
		alert("Campo Endereço Vazio, seleciona uma opção!");
		return false;
	}
	if( $("input[name=numerocliente]").val() == null){
		alert("Campo Numero Endereço Vazio, seleciona uma opção!");
		return false;
	}
	if( $("select[value=estadocliente]").val() == null){
		alert("Campo Estado Vazio, seleciona uma opção!");
		return false;
	}
	if( $("select[value=cidadecliente]").val() == null){
		alert("Campo Cidade Vazio, informe a cidade!");
		return false;
	}else{
	return true;
	
	$.ajax({
		type: 'POST',
		url: 'servletCadastroCliente',
		data: {formulario: 'formulario'},
		
		success:function(resposta){
			
		},
		error:function(resposta){
			
		}
	});
	}
	
}