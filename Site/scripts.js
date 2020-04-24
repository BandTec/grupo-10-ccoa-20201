function onOff(){
    document.querySelector("#modal")
    .classList.toggle("hide")

    document.querySelector("#bg")
    .classList.toggle("hide")

    document.querySelector("body").classList.toggle("hideScroll")
    

    document.querySelector("#modal").classList.toggle("addScroll")

    document.querySelector("#bg").classList.toggle("addScroll")
}

function calcularLucro(){
    var lucroSem = Number(document.getElementById(lucroM.value));
    var maquinas = Number(document.getElementById(qntMaquinas.value));

    var lucroPrimeiro = (lucroSem.value*1.1) - (maquinas.value*160);
    var lucroMes = (lucroSem.value*1.1) - (maquinas.value*30);

    p1.innerHTML = `O seu lucro após o primeiro mês será de R$${lucroPrimeiro}, devido à mão de obra.`;
    p2.innerHTML = `A partir do segundo mês seu lucro será de R$${lucroMes} por mês`;
}