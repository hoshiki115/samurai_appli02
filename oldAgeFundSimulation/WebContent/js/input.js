function calc(){
    var amount = document.form1.monthlyCost.value * 12;
    document.form1.yearlyCost.value = amount;
}

document.getElementById("plan").onchange = function(){
    var select = document.getElementById("plan");
    var planCost = '';
    switch(select.value){
        case 'noPlan':
            planCost = '';
            break;
        case 'planA':
            planCost = 12;
            break;
        case 'planB':
            planCost = 60;
            break;
        case 'planC':
            planCost = 120;
            break;
        case 'planF':
            planCost = '';
            break;
    }
    document.getElementById("planCost").value = planCost;
};