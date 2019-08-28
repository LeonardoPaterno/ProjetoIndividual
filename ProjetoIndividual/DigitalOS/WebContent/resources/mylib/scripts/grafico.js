var trace1 = {
    type: 'bar',
    x: [1, 2, 3, 4],
    y: [1, 2, 3, 4],
    marker: {
        color: '#fff',
        line: {
            width: 1
        }
    }
};

var data = [ trace1 ];

var layout = {
  title: 'O.S. Abertas',
  font: {size: 12},
  plot_bgcolor: '#c7c9cb',
  paper_bgcolor: '#c7c9cb'
};

Plotly.newPlot('myDiv', data, layout, {responsive: true});