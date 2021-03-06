# rodar esse comando install caso n�o tenha as bibliotecas instaladas 
# install.packages(c("wordcloud", "RColorBrewer", "wordcloud2", "tm", "pdftools"))
library(wordcloud)
library(wordcloud2)
library(RColorBrewer)
library(tm)
#wordCloud � o nome da tabela exportada pelo R
tabela <- wordCloud
names(tabela)[names(tabela) == "idAvaliacao"] <- "Id"
names(tabela)[names(tabela) == "texto"] <- "avaliacao"
docs <- Corpus(VectorSource(tabela$avaliacao))
docs <- tm_map(docs, tolower)
docs <- tm_map(docs, removePunctuation)
docs <- tm_map(docs, removeWords, stopwords("pt"))
# Aqui podemos adicionar palavras que n�o fazem sentido para a wordCloud
docs <- tm_map(docs, removeWords, c("sistema", "tambem", "por�m","pra","desse","ter","algum","diria","assim"))
# Aqui mudamos variasa palavras de significados iguais para apenas uma palavra semelhante
docs <- tm_map(docs, content_transformer(gsub), pattern = "\\b(lerdo|lento|lerda|lenta)\\b", replacement = "lento")
docs <- tm_map(docs, content_transformer(gsub), pattern = "\\b(�timo|�tima|otimo|otima)\\b", replacement = "�timo")
docs <- tm_map(docs, removeNumbers)
docs <- tm_map(docs, stripWhitespace)
dtm <- TermDocumentMatrix(docs)
matrix <- as.matrix(dtm)
words <- sort(rowSums(matrix), decreasing = TRUE)
df <- data.frame(word = names(words), freq = words)
# wordcloud(words = df$word, freq = df$freq, min.freq = 1, max.words = 50, random.order = FALSE, rot.per = 0.35, colors = brewer.pal(8, "Dark2"), scale = c(2,1))

wordcloud2(data = df, size = 0.8, color = 'random-light', backgroundColor = 'black', minRotation = -pi/6, maxRotation = -pi/6, rotateRatio = 0)
