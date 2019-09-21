var jan; var fev; var mar; var abr; var mai; var jun; var jul; var ago; var set; var out; var nov; var dez;
function dadosgrafico(resposta){
	
};
function graficoBuilder(){
	return{
		build : function(resposta){
					let aparelhos=[]
					let respostaArray = JSON.parse(resposta);
					
					for(let i = 0; i <= respostaArray.length; i++){
						if (respostaArray[i] == undefined || respostaArray[i] == null){
							continue;
						}
						console.log(respostaArray[i]);
						aparelhos[i] = new Aparelho(respostaArray[i]);
					}
					console.log(aparelhos)
					return aparelhos;
		}
	}
}
// create data
var data = [
  ["Janeiro", jan],
  ["Fevereiro", fev],
  ["Março", mar],
  ["Abril", abr],
  ["Maio", mai],
  ["Junho", jun],
  ["Julho", jul],
  ["Agosto", ago],
  ["Setembro", set],
  ["Outrubro", out],
  ["Novembro", nov],
  ["Dezembro", dez]
];

// create a chart
chart = anychart.column();

// create a column series and set the data
var series = chart.column(data);

// set the container id
chart.container("container");

// initiate drawing the chart
chart.draw();