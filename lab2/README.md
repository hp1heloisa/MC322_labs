Seja bem ao código do lab 2! Seguem algumas orientações de explicação do código.
Nós fizemos 2 tipos de robôs: os aéreos e terrestres, com cada categoria possuindo dois representantes.
Começaremos explicando os terrestres primeiro:

Todos os robôs terrestres possuem velocidade máxima de 5km/h, definida por padrão.
    *Robô Limitado: Como o nome diz, ele possui uma movimentação limitada, se restringindo a apenas a movimentação
convencional, nos quadro pontos cardeais, cima, baixo, direita e esquerda.
    * Robô Guindaste: É um robô que possui um Guindaste em sua estrutura. Com esta funcionalidade disponível,
ele tem o poder  de remover de sua trajetória um obstáculo que esteja atrapalhando o seu percurso, mas
como ele é um robô bastante educado, ele guarda o obstáculo na posição que o robô estava ocupando anteriormente, ou
seja, ele troca de posição com o obstáculo.

Chegou a vez dos robôs aéreos. Em relação aos robôs terrestres, os aéreos possuem uma particularidade bastante
importante de ser lembrada: eles podem voar. Em virtude da liberdade de expressão, permitimos que o usuário escolha
qual é a altitude máxima que o robô pode atingir. Por fim, os robôs aéreos são:
    *Robô Destruidor: Um robô desenvolvido com uma moderna tecnologia que permite destruir apenas os obstáculos em todas
as direções. Então, nem perca tempo tentando destruir outros robôs, pois o sistema impedirá tal ação.
    *Robô Teletransportador: Após assistir ao filme Túmulo dos Vagalumes, o engenheiro de nossa empresa ficou desidratado
pelas lágrimas derramadas e se propôs a construir uma solução para os civis conseguirem se proteger de bombardeios aéreos.
Por isso, ele desenvolveu um robô que permite se teletransportar para outra altitude, sem trocar de posição X e Y. Logo, quando estiver vindo um bombardeio, basta que o cidadão se dirija para o céu e espere com que o ataque acabe.

Durante a execução do programa, há explicações o que fazer para execução do programa, porém deixo uma entrada de sugestão, porém deixarei a movimentação a cargo do usuário, pois o campo de obstáculo é gerado de forma aleatória:

1
1
RoboLimitado
Norte
q  /*Se não colocarmos este q, receberá um aviso, pois o robô não consegue andar com velocidade 0/*
/*Movimentação. Sugiro primeiro tentar se movimentar para baixo(tecla s) ou para esquerda(tecla a), pois essa são posições inválidas
Depois, tentar se movimentar por espaços vazios e se chocar com algum obstáculo.*/
n /*Criar um novo robô*/
1
2
RoboGuindaste
Leste
q
/*Movimentação. Sugiro utilizar a tecla k, pois ela é responsável pelo poder do robô guindaste e assim, receberá as instruções necessárias. Também, é interessante, apertar a tecla q mais algumas vezes até ultrapassar o limite de velocidade para ver o que acontece*/
n
2
1
RoboDestruidor
Sul
5
/*Movimentação. Sugiro testar a movimentação em relação a altitude(tecla u--subida e tecla j--descida). OBS: às vezes pode acontecer de ter um obstáculo em cima. Para achar uma posição que o robô possa subir com certeza, basta apertar a tecla p que ela scaneia a área, inclusive as altitudes acima. E também há o poder k de destruir obstáculos. Ao uilizar o poder, o usuário poderá atirar em qualquer direção com as mesmas teclas de movimentação*/
n
2
2
RoboTeletransportador
Oeste
30
/*Movimentação. Para finalizar, de inédito apenas temos o poder de teletransporte que é com a tecla k. Então, teste o teletransporte para várias altitudes, inclusive para altitudes inexistentes, como -20 ou 59*/
