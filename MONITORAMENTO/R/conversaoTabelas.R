valorInicial = NULL
tabela = exportBD

CPU = valorInicial
RAM = valorInicial
DISCO = valorInicial
CONEXAOU = valorInicial
CONEXAOD = valorInicial
TEMP = valorInicial
DATAS = valorInicial

dataAtual = ''
maiorQtdItens = 0

#Pegando quais componentes vieram do BD
componentes = c()
componenteAtual = ''
for (linha in 1:nrow(tabela)) {
  componenteAtual <- tabela[linha,]$nomeComponente
  if(!is.element(componenteAtual, componentes)){
    componentes <- c(componentes, componenteAtual)
  }
}

#Matriz sera um esboco do dataset que iremos criar no final
matriz <- matrix(ncol = length(componentes)+1)
colnames(matriz) <- append(componentes, "dataHora")

linhaMatriz = 1

for (linhaTabela in 1:nrow(tabela)) {
  #Pegando Linha x do export do BD
  #Os campos que estao sendo pegos sao desde o 1 ate o N° total de colunas que tiver [1:final]
  linhaAtual = tabela[linhaTabela,]
  
  dataAtual = linhaAtual$dataHora
  if(is.na(matriz[linhaMatriz, "dataHora"])){
    #Setando a data caso nao tenha sido feito ainda
    matriz[linhaMatriz, "dataHora"] = dataAtual
  }
  else{
    #Se a data nao e null, e diferente da atual, add linha
    if(matriz[linhaMatriz, "dataHora"] != dataAtual){
      print('Linha adicionada')
      matriz <- rbind(matriz, rep(NA, length(componentes)+1))
      linhaMatriz = linhaMatriz + 1
    }
  }
  
  #Valor sendo adicionado na matriz
  matriz[linhaMatriz, linhaAtual$nomeComponente] <- linhaAtual$valor
  
  x = x+ 1
}

adicionarColunas <- function(componente) {
  if(all(is.na(componente)) == FALSE)
    return(componente)
}

#Crio uma nova tabela
novaTabela = data.frame(ID = 1:nrow(matriz))
DATAS <- matriz[1:nrow(matriz) ,"dataHora"]

novaTabela$CPU <- adicionarColunas( matriz[1:nrow(matriz) ,"CPU"])
novaTabela$RAM <- adicionarColunas( matriz[1:nrow(matriz) ,"RAM"])
novaTabela$Disco <- adicionarColunas( matriz[1:nrow(matriz) ,"Disco"])
novaTabela$ConexaoD <- adicionarColunas( matriz[1:nrow(matriz) ,"conexaoD"])
novaTabela$ConexaoU <- adicionarColunas( matriz[1:nrow(matriz) ,"conexaoU"])
novaTabela$Temperatura <- adicionarColunas( matriz[1:nrow(matriz) ,"temperatura"])
novaTabela$Data <- DATAS

View(novaTabela)