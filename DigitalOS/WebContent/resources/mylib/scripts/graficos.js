$(document).ready(
		function() {
			var segundoGrafico = document.getElementById("#primeiroGrafico")

			let chart = new Chart(primeiroGrafico, {
				type : 'line',
				data : {
					labels : [ 'Janeiro', 'Fevereiro', 'Marco', 'Abril',
							'Maio', 'Junho', 'Junho', 'Julho', 'Agosto',
							'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
					datasets : [ {
						label : 'Numero de Ordens de Servico',
						data : [ 25, 34, 42, 30, 40, 50, 60, 70, 56, 60, 50,
								70, 82 ],
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)' ],
						borderColor : [ 'rgba(255, 99, 132, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)',
								'rgba(255, 99, 132, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)' ],
						borderWidth : 1
					} ]
				},

			});

		});

/*
 * $(document).ready( function() { var primeiroGrafico =
 * document.getElementById("primeiroGrafico")
 * 
 * let chart = new Chart(primeiroGrafico, { type : 'bar', data : { labels : [
 * 'Janeiro', 'Fevereiro', 'Marco', 'Abril', 'Maio', 'Junho', 'Junho', 'Julho',
 * 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ], datasets : [ {
 * label : 'Numero de Ordens de Servico', data : [ 25, 34, 42, 30, 40, 50, 60,
 * 70, 56, 60, 50, 70, 82], backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
 * 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192,
 * 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)', 'rgba(255, 99,
 * 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75,
 * 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)' ],
 * borderColor : [ 'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255,
 * 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255,
 * 159, 64, 1)', 'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255,
 * 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255,
 * 159, 64, 1)' ], borderWidth : 1 } ] },
 * 
 * });
 * 
 * });
 */
