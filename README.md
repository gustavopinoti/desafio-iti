# Desafio Iti

## Importação dos Dados

Ao iniciar a aplicação, o sistema lê um arquivo .log contendo informações de Transações realizadas e as importa. Na sequencia, é realizada uma requisição HTTP para uma API onde são disponibilizados outras transações realizadas. A aplicação da mesma forma as importa, e as une juntamente com as transações importadas através do arquivo.

Caso uma das fontes não esteja disponivel, ou ocorra algum problema na importação da mesma, a aplicação continuará normalmente apenas com as transações contidas na outra fonte. 

Caso ambas as fontes estejam indisponiveis, a aplicação apresenta uma mensagem informando que não existem aplicações disponiveis e é encerrada.

Após a importação dos dados, um menu é exibido.

## Menu

Assim que o Menu é iniciádo, todos os calculos e tratativas são executadas. Estas serão executadas apenas uma vez, podendo serem exibidas indefinidas vezes.

O menu é exibido, sendo possível a escolha de 8 opções, podendo ser realizadas através de numeros. Sendo elas do 0 ao 7: 

1 - Histórico de movimentações
2 - Gasto por categoria
3 - Categoria com maior gasto
4 - Mês com maior gasto
5 - Dinheiro total gasto
6 - Dinheiro total recebido
7 - Saldo total de movimentações
0 - Sair

Caso uma letra, ou um numero diferente das opções informadas acima seja inserido, o menu será exibido novamente, e outra opção será solicitada.

* Na opção 0, o sistema será encerrado
* Ao ser inserido o numero 1, será exibido um Histórico das movimentações ordenados por data.
* Já no numero 2, será exibidos o total de gastos em cada categoria
* Na opção 3, será informado qual categoria teve o maior gasto
* Ná 4, será exibido qual mês o cliente mais gastou
* Ao escolher a 5, o usuário será informado da quantidade de dinheiro total gastá por ele
* Na opção 6, será a quantidade total de dinheiro recebido
* E por ultimo, na opção 7, o saldo total de movimentações do cliente

## Executando a Aplicação

* é necessário ter o JDK/JRE e o Maven Instalado instalados devidamente em seu computador.

No terminal, no diretorio do projeto digite:

1- mvn clean install
2- cd target
3- java -jar desafio-iti.jar

a aplicação iniciará no terminal

## Executando os testes

* é necessário ter o JDK/JRE e o Maven Instalado instalados devidamente em seu computador.

No terminal, no diretorio do projeto digite:

1- mvn test
