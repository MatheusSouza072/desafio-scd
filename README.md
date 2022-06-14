# Serviço da Receita

Garante que tenha instalado as seguintes tecnologias.

* Java 11
* Maven

Entre na raiz do projeto e rode:
```bash
mvn clean package install
```

Esse comando fará com que os testes e todo o build da aplicação seja rodado<br>
Estando na raiz do projeto, execute o seguinte comando:

```bash
java -jar .\target\dbc-0.0.1-SNAPSHOT.jar .\input.csv
```
O diretório do arquivo é opcional, podendo ser alterado para qualquer local.

```bash
ex: java -jar .\target\dbc-0.0.1-SNAPSHOT.jar C:\usuario\input.csv
```
