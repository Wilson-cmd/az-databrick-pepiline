// Databricks notebook source
// MAGIC %python
// MAGIC dbutils.fs.ls('/mnt/datas/bronze')

// COMMAND ----------

val path = "dbfs:/mnt/datas/bronze/dados_imoveis/"
val df = spark.read.format("delta").load(path)

// COMMAND ----------

display(df)

// COMMAND ----------

display(df.select("anuncio.*"))

// COMMAND ----------

display(
  df.select("anuncio.*", "anuncio.endereco.*")
)

// COMMAND ----------

val dados_detalhados = df.select("anuncio.*", "anuncio.endereco.*")

// COMMAND ----------

display(dados_detalhados)

// COMMAND ----------

val dataFrameSilver = dados_detalhados.drop("caracteristicas", "endereco")
display(dataFrameSilver)

// COMMAND ----------

val path = "dbfs:/mnt/datas/silver/dataset_imoveis"
dataFrameSilver.write.format("delta").mode("overwrite").save(path)

// COMMAND ----------

dataFrameSilver.columns
