x = 1

CPU = valorInicial
RAM = valorInicial
DISCO = valorInicial
CONEXAOU = valorInicial
CONEXAOD = valorInicial
TEMP = valorInicial

while (x <= nrow(exportMaisOuMenos)) {
  linhaAtual = exportMaisOuMenos[x, 1:4]
  
  if(linhaAtual$nomeComponente == "CPU"){
    CPU[length(CPU)+1] = linhaAtual$valor
  }
  else if (linhaAtual$nomeComponente == "RAM")
    RAM[length(RAM)+1] = linhaAtual$valor
    
  else if (linhaAtual$nomeComponente == "Disco")
    DISCO[length(DISCO)+1] = linhaAtual$valor
  
  else if (linhaAtual$nomeComponente == "RAM")
    CONEXAOU[length(CONEXAOU)+1] = linhaAtual$valor
  
  else if (linhaAtual$nomeComponente == "RAM")
    CONEXAOD[length(CONEXAOD)+1] = linhaAtual$valor
  
  else if (linhaAtual$nomeComponente == "RAM")
    TEMP[length(TEMP)+1] = linhaAtual$valor
  
  else
    print("Componente não catalogado :(")
  
  x = x + 1
}

valorFinal = rep(0, exportMaisOuMenos)

novaTabela = data.frame(id = 1:n, 
                        CPU[2:n], 
                        RAM[2:n], 
                        DISCO[2:n], 
                        CONEXAOD[2:n], 
                        CONEXAOU[2:n], 
                        TEMP[2:n])