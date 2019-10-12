$(document).ready(function(){
	listadeos = [];
	$.ajax({
		type: 'POST',
		url: '/DigitalOS/rest/RestPopulaGrafico/NumeroOSMensal',
		success: function(resposta){
			console.log(resposta);
			
			listadeos = resposta;
			
			var html = "<select style='width:100px;' class='btn btn-default' id='atual' onchange='carregaGrafico()'>";
				anos = [];
				for(var i = 0; i < resposta.length; i++){
					if(!anos.includes(listadeos[i].ano)){
						console.log(listadeos);
						anos.push(listadeos[i].ano);
						
						html += "<option value="+listadeos[i].ano+">"
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
		meses[1] = 0;
		meses[2] = 0;
		meses[3] = 0;
		meses[4] = 0;
		meses[5] = 0;
		meses[6] = 0;
		meses[7] = 0;
		meses[8] = 0;
		meses[9] = 0;
		meses[10] = 0;
		meses[11] = 0;
		meses[12] = 0;
		
		for(var i = 0; i < listadeos.length; i++){
			if(listadeos[i].ano == ano){
				meses[listadeos[i].mes] = listadeos[i].valor;
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
            backgroundColor:[
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)'
    		]
        }],
        labels:["Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"]
    },
    options: {
        scales: {
            xAxes: [{
                ticks: {
                	type: 'category',
                }
            }]
        }
    }
	
	
    /*type: 'bar',
    data: {
    	datasets:[{
    			data: meses
    		}],
    	labels:["Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
    	backgroundColor:[
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)'
    		]
    	},
    	datasets:[{
    		label: "Ordens de Servico",
    		data: meses,
    		scale: [{maxBarThickness: 1}],
    		backgroundColor:[
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)',
    			'rgba(255, 99, 132, 2)'
    		]
    	}]   	
    }*/
});

}