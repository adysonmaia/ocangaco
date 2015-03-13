# Classe Player #

Pessoal, vocês colocaram uma classe Player lá. Eu acho que está boa. Mas ainda acho chato essa classe não ter sido sequer discutida entre todo o grupo.
O que seria o tipo que vocês colocaram lá? Seria o time não é? Não tem nenhum comentário no código. Não seria melhor mudar a variável para time?
Seria bom também comentar aquele método toString(). Acho que vocês vão precisar dele, por isso criaram, mas seria bom sempre comentar bem o código e não deixar que os outros infiram isso - principalmente numa classe que será usada por todo o jogo. Lembrem que estamos trabalhando em equipe, e uma equipe bem grande e diversa.. :)

## Atributos ##
  * String nome;
  * int tipo;
  * double latitude;
  * double longitude;


---


# Mensagens #

## Registar no servidor ##

Envia:
  * String: Nome do Usuário

Recebe:
  * int: id do usuário
  * String: time do usuário _// O servidor irá distribuir os pedidos de registro entre os times. Alguém discorda?_
  * String: Estado do jogo _// Para o primeiro sprint, podemos ignorar esta parte, o jogador já entra no mapa direto, só precisa saber o time_
    * Aguardando outros jogadores
    * Aguardando partida encerrar

## Alteração de Posição ##

**Questões:**
  1. Seria interessante que essa mensagem fosse enviada via socket udp? O mini-framework do Danilo suporta?

Envia:
  * int: id do usuário
  * coordenadas _// Como passar? Strings? Doubles?_

Recebe:
> Não recebe nada, para evitar overhead.

## Alteração de Posição de Jogador próximo ##

Estou olhando o lado do cliente, por isso só tem Recebe
**Questões:**
  1. De início, poderia ser uma mensagem para cada usuário que se mexer por perto, poderíamos ver a viabilidade de enviar mais de uma atualização em uma mesma mensagem - isso pode causar lag e acho que o tempo é muito curto para implementar isto.

Recebe:
  * String: nome do usuário
  * int: time do usuário
  * coordenadas: posição atual do usuário