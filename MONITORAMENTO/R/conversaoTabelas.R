valorInicial = NULL
tabela = registro

CPU = valorInicial
RAM = valorInicial
DISCO = valorInicial
CONEXAOU = valorInicial
CONEXAOD = valorInicial
TEMP = valorInicial
maiorQtdItens = 0
contadoComSucesso = FALSE

#Começando Looping que irá olhar linha por linha do BD
x = 1
while (x <= nrow(tabela)) {

  #Pegando Linha x do export do BD
  #Os campos que estão sendo pegos são desde o 1 até o N° total de colunas que tiver [1:final]
  linhaAtual = tabela[x, 1:ncol(tabela)]
  
  #Identifica a qual componente a linha esta se referindo
  if(linhaAtual$nomeComponente == "CPU"){
    #Entra no vetor daquele componente e adiciona mais um valor.
    CPU[length(CPU)+1] = linhaAtual$valor
  }
  else if (linhaAtual$nomeComponente == "RAM")
    RAM[length(RAM)+1] = linhaAtual$valor
    
  else if (linhaAtual$nomeComponente == "Disco")
    DISCO[length(DISCO)+1] = linhaAtual$valor
  
  else if (linhaAtual$nomeComponente == "conexaoU")
    CONEXAOU[length(CONEXAOU)+1] = linhaAtual$valor
  
  else if (linhaAtual$nomeComponente == "conexaoD")
    CONEXAOD[length(CONEXAOD)+1] = linhaAtual$valor
  
  else if (linhaAtual$nomeComponente == "temperatura")
    TEMP[length(TEMP)+1] = linhaAtual$valor
  
  #Passando para a próxima linha
  x <- x + 1
}

#Conto quantos de Cada Componente veio do BD
#Isso pois para criar uma nova tabela eu preciso saber quantas linhas ela terá
#Através do n° presente no maiorQtdDeItens dimensionamos a qtd de linhas em nossa tabela

verificarQtdItens <- function(componente){
  maiorItens = maiorQtdItens
  if(length(componente) > maiorItens){
  maiorItens = length(componente)
}
  return(maiorItens)
}

maiorQtdItens = verificarQtdItens(CPU)
maiorQtdItens = verificarQtdItens(RAM)
maiorQtdItens = verificarQtdItens(DISCO)
maiorQtdItens = verificarQtdItens(CONEXAOD)
maiorQtdItens = verificarQtdItens(CONEXAOU)
maiorQtdItens = verificarQtdItens(TEMP)


adicionarVazio <- function(componente) {
  while(length(componente) < maiorQtdItens){
  componente[length(componente) + 1] = 'Vazio'
}
  return(componente)
}

CPU <- adicionarVazio(CPU)
RAM <- adicionarVazio(RAM)
DISCO <- adicionarVazio(DISCO)
CONEXAOD <- adicionarVazio(CONEXAOD)
CONEXAOU <- adicionarVazio(CONEXAOU)
TEMP <- adicionarVazio(TEMP)



#Crio uma nova tabela
novaTabela = data.frame(ID = 1:maiorQtdItens)

adicionarColunas <- function(componente) {
  if(is.null(componente) == FALSE)
    return(componente)
}

#Verifico se eu coletei um determinado componente.
#Se o vetor do componente está vazio, passa reto
#Se não, ele adiciona uma coluna à tabela.
#Coluna esta que terá os mesmos valores do vetor

novaTabela$CPU <- adicionarColunas(CPU)
novaTabela$RAM <- adicionarColunas(RAM)
novaTabela$DISCO <- adicionarColunas(DISCO)
novaTabela$CONEXAOD <- adicionarColunas(CONEXAOD)
novaTabela$CONEXAOU <- adicionarColunas(CONEXAOU)
novaTabela$TEMP <- adicionarColunas(TEMP)

# if(is.null(CPU) == FALSE)
#   novaTabela$CPU <- CPU
# #Mesma verificação, mas escrito diferente
# if(!is.null(RAM))
#   novaTabela$RAM <- RAM
# if(!is.null(DISCO))
#   novaTabela$Disco <- DISCO
# if(!is.null(CONEXAOD))
#   novaTabela$ConexaoD <- CONEXAOD
# if(!is.null(CONEXAOU))
#   novaTabela$ConexaoU <- CONEXAOU
# if(!is.null(TEMP))
#   novaTabela$Temp <- TEMP