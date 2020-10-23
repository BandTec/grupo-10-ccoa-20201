
novaTabela
# Criação da variável de id e dando o valor de 1 para 500
id = 1:500
id
#igualando a váriável "n"a 500
n = 500
# pegamos uma variável "cpus" já existente e igualamos para a criação 
# dos números randômicos através do comando "rnorm" dentro dele definimos
#
cpus = round(rnorm(n,1.5,0.2),2)
novaTabela = data.frame(id,cpus)
novaTabela
write.table(df, file = "tabelaTeste.csv", sep = ",", col.names = TRUE, fileEncoding = "UTF-8")
tabelaTeste <- read.csv("~/tabelaTeste.csv")
View(tabelaTeste)
plot(novaTabela$cpus)
modelo = lm(novaTabela$ID~novaTabela$cpus)
hist(novaTabela$cpus)
tabelaServidor = novaTabela$cpus*3
tabelaServidor
novaTabela = data.frame(id,cpus)
summary(novaTabela$cpus)