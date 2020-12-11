tabela <- testeWordcloud
names(tabela)[names(tabela) == "V1"] <- "Id"
names(tabela)[names(tabela) == "V2"] <- "avaliacao"
docs <- Corpus(VectorSource(tabela$avaliacao))
docs <- tm_map(docs, tolower)
docs <- tm_map(docs, removePunctuation)
docs <- tm_map(docs, removeWords, stopwords("pt"))
docs <- tm_map(docs, removeWords, c("sistema", "tambem"))
docs <- tm_map(docs, content_transformer(gsub), pattern = "\\b(lerdo|lento|lerda|lenta)\\b", replacement = "lento")
docs <- tm_map(docs, removeNumbers)
docs <- tm_map(docs, stripWhitespace)
dtm <- TermDocumentMatrix(docs)
matrix <- as.matrix(dtm)
words <- sort(rowSums(matrix), decreasing = TRUE)
df <- data.frame(word = names(words), freq = words)
# wordcloud(words = df$word, freq = df$freq, min.freq = 1, max.words = 50, random.order = FALSE, rot.per = 0.35, colors = brewer.pal(8, "Dark2"), scale = c(2,1))

wordcloud2(data = df, size = 0.8, color = 'random-light', backgroundColor = 'black', minRotation = -pi/6, maxRotation = -pi/6, rotateRatio = 0)
