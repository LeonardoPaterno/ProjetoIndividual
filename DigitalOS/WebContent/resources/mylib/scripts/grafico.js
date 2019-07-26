var trace1 = {
    type: 'bar',
    x: [1, 2, 3, 4],
    y: [1, 2, 3, 4],
    marker: {
        color: '#2e6da4',
        line: {
            width: 1
        }
    }
};

var data = [ trace1 ];

var layout = {
  title: 'Ordens de Serviço',
  font: {size: 12}
};

Plotly.newPlot('myDiv', data, layout, {responsive: true});