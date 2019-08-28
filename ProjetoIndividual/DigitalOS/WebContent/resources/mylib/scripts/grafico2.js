var trace1 = {
    type: 'bar',
    x: [1, 2, 3, 4],
    y: [4, 3, 2, 1],
    marker: {
        color: '#fff',
        line: {
            width: 1
        }
    }
};

var data = [ trace1 ];

var layout = {
  title: 'O.S. Fechadas',
  font: {size: 12}, 
  plot_bgcolor: '#c7c9cb',
  paper_bgcolor: '#c7c9cb'
};

Plotly.newPlot('myDiv2', data, layout, {responsive: true});