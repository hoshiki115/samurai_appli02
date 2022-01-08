var ctx = document.getElementById('ex_chart');

var data = {
    labels: age,
    datasets: [{
        label: '収入合計',
        data: income,
        borderColor: 'rgba(255, 100, 100, 1)',
        lineTension: 0,
        fill: false,
        borderWidth: 3
    },
    {   label: '支出合計',
        data: cost,
        borderColor: 'rgba(100, 100, 255, 1)',
        lineTension: 0,
        fill: false,
        borderWidth: 3
    },
    {   label: '収支合計',
        data: balance,
        borderColor: 'rgba(100, 255, 100, 1)',
        lineTension: 0,
        fill: false,
        borderWidth: 3
    },
    {   label: '預貯金残高',
        data: saving,
        borderColor: 'rgba(255, 100, 255, 1)',
        lineTension: 0,
        fill: false,
        borderWidth: 3
    }]
};

var options = {
    scales: {
        xAxes: [{
            scaleLabel: {
                display: true,
                labelString: '年齢'
            }
        }],
        yAxes: [{
            ticks: {
            },
            scaleLabel: {
                display: true,
                labelString: '万円'
            }
        }]
    },
    title: {
        display: true,
        text: '老後資金シミュレーション'
    }
};

var ex_chart = new Chart(ctx, {
    type: 'line',
    data: data,
    options: options
}); 