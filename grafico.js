$(document).ready(function(){
	listadeos = [];
	$.ajax({
		type: 'POST',
		url: '/DigitalOS/rest/RestPopulaGrafico/NumeroOSMensal',
		success: function(resposta){
			console.log(resposta);
			
			listadeos = resposta;
			
			var html = "<label style='font-size:18px;'>Seleção Anual</label> " +
						"<select style='width:100px;' class='btn btn-default' id='atual' onchange='carregaGrafico()'>";
				anos = [];
				for(var i = 0; i < resposta.length; i++){
					if(!anos.includes(listadeos[i].ano)){
						console.log(listadeos);
						anos.push(listadeos[i].ano);
						
						html +="<option value="+listadeos[i].ano+">"
									+ listadeos[i].ano
							   +"</option>";
					}
				}
			
			html += "</select>";
			$("#selecionaAno").html(html);
			carregaGrafico();
		},
		error: function(){
			alert("Erro ao carregar grafico");
		}
	});
});

function carregaGrafico(){
	var ano = $("#atual").val();
	
	if(listadeos != undefined && listadeos.length > 0){
	 
		meses = [];
		meses[0] = 0;
		meses[1] = 0;
		meses[2] = 0;
		meses[3] = 0;
		meses[4] = 0;
		meses[5] = 0;
		meses[6] = 0;
		meses[7] = 0;
		meses[8] = 0;
		meses[90] = 0;
		meses[10] = 0;
		meses[11] = 0;
		
		for(var i = 0; i < listadeos.length; i++){
			if(listadeos[i].ano == ano){
				meses[listadeos[i].mes -1] = listadeos[i].valor;
			}
		}
	}

$("#grafico").remove();
$("#conteudo").append('<canvas id="grafico" class="graficobarras"></canvas>');


var myBarChart = new Chart($("#grafico"), {
	type: 'bar',
    data: {
        datasets: [{
            		data: meses,
            		backgroundColor:['#ff849f', '#00ca36', '#c7007f', '#69ddff', '#9762ff', '#007010', '#9dbf00', '#f28d27', '#c30000', '#7b0079', '#74ffec', '#504c7d'],
            		label: false
                   }],
        labels:["Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
    },
    options:{
	    	title:{
	    		display: true,
	    		text: 'Ordens de Serviço Mensal',
	    		fontSize: 16,
	    		fontColor: '#000000'
	    	},
	    	legend:{
	    		display: false
	    	},
	    	scales:{
	    			yAxes:[{ticks:{
	    						suggestedMin: 0 , 
	    						suggestedMax: 10, 
	    						fontColor: '#000000', 
	    						ontSize: 14, 
	    						fontStyle: 'bold'
	    						}
	    			}],
	    			xAxes:[{ticks:{
	    						fontColor: '#000000',
	    						fontSize: 14,
	    						fontStyle: 'bold'
	    						}
	    			}]
	    	},   	
	    	animation:{
	    		onProgress:'linear',
	    		duration: 4800
	    	}
    }
});

}