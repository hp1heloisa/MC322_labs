# Laboratório 3 - Relacionamentos

## Introdução
Seja bem-vindo a explicação do código do Lab 3!

Após alguns anos de paz, algumas coisas fugiram de controle: Os robôs descobriram que podem morrer agora. Por isso, eles estão desesperados. A fim de contornar tal situação, os engenheiros desonvolveram uma nova ferramenta -- o Piloto Automático. Com essa nova funcionalidade, os robôs minimizam sua chance de morte, porém como ainda está em períodos de testes, ainda ocorrem alguns deslizes. Então, vamos testar essa nova funcionalidade!

OBS: o nosso diagrama é o arquivo relacionamentos.jpg

## Tipos de Robôs

### Robôs Terrestres
Todos os robôs terrestres possuem velocidade máxima de 5km/h por padrão.

#### Robô Limitado
- Movimentação restrita aos quatro pontos cardeais (cima, baixo, direita, esquerda)
- Funcionalidades básicas de movimento

#### Robô Guindaste
- Equipado com guindaste para manipulação de obstáculos
- Pode trocar de posição com obstáculos no caminho
- Mantém o ambiente organizado reposicionando os obstáculos

### Robôs Aéreos
- Capacidade de voo com altitude controlável
- Altitude máxima configurável pelo usuário

#### Robô Destruidor
- Tecnologia avançada para destruição de obstáculos
- Pode eliminar obstáculos em todas as direções


#### Robô Teletransportador
- Capacidade de teletransporte vertical
- Mantém coordenadas X e Y durante o teletransporte
- Ideal para evasão rápida de ameaças aéreas

## Tipos de obstáculos

### Muralha
- Inicial: M
- Possui um grande comprimento e altura.
- Foi desenvolvida para bloquear o percurso do robô

### Lago
- Inicial: L
- Essencial para a melhoria de umidade do ambiente
- Tome cuidado! O robô pode morrer afogado.

### Fogo
- Inicial: F
- Contribui para o aumento de temperatura e prejudicar o funcionamento do robô.
- Pode acabar queimando o robô.

### Vegetação
- Inicial: V
- Possui o maior comprimento de todos os obstáculos
- Fornece um pouco de umidade.

## Sensores 
### Sensor Plano
- Mostra os objetos próximos no mesmo plano em um raio r, definido pelo sensor

### Sensor Altitude
- Ele mostra os objetos próximos em relação até uma altura r, definida pelo sensor.

### Sensor Temperatura
- Mostra a temperatura da coordenada, utilizando a distância eucliadiana entre vários obstáculos e realizando uma média entre todos.

### Sensor Altitude
- Mesma lógica do sensor temperatura.
## Guia de Uso

### Como Executar
1. Execute o programa principal, rodando `./script.sh` no terminal
2. Se quiser controlar os robôs basta escrever a terceira linha  do arquivo script.sh assim("java Main #< input.txt"), se não o programa irá ler a nossa entrada pré-definida
3. Se estiver usando o input.txt, apenas aguarde e veja o resultado final direto, se não siga as instruções interativas no console
4. Selecione o tipo de robô desejado

### Controles Básicos
| Tecla | Ação                | Robôs Compatíveis       |
|-------|---------------------|-------------------------|
| w     | Mover para frente   | Todos                   |
| a     | Mover para esquerda | Todos                   |
| s     | Mover para trás     | Todos                   |
| d     | Mover para direita  | Todos                   |
| q     | Aumentar velocidade | Terrestres              |
| k     | Ativar habilidade   | Todos menos o Limitado
| u     | Subir               | Aéreos                  |
| j     | Descer              | Aéreos                  |
| p     | Scanear área        | Todos                   |
| n     | Novo robô           | Todos                   |
| x     | Sair                | Todos                   |
| c     | Remover/trocar robô | Todos

### Os nossos testes
 O piloto automático é a nossa entrada padrão, o arquivo "input.txt". Se quiserem rodar o `./script.sh`, sem alterações, ele irá funcionar direitinho. Sugiro que se teste copiando algumas partes, pois o teste irá vir diversas linhas de uma vez.
**Primeiro robô:**
#### Robô limitado:
- Nome: Link
- Começamos tentando mover para frente("w" -- linha 6 da entrada), e acabamos chocando com uma Vegetação densa. Por isso, o robô não anda.
- Nos próximos moviemntos, o robô acaba andando aleatoriamente e criamos um novo robô.

**Robô Guindaste:**
1. Vamos criar um novo robô: o guindaste, chamado de Bob.
2. Nós trocamos alguns obstáculos durante o caminho, para testar o seu guindaste
3. Agora, o nosso robô não morreu. Apenas criamos um novo.

**Robô Destruidor:**
1. Robô chamado de Exterminador.
2. Destruímos vários obstáculos durante o percurso
3. Subimos e descemos usando os comandos("u" e "j")

**Robô Teletransportador:**
1. Nome é Spock
2. Movemos alguns movimentos aleatórios e usamos o seu poder de teletransportar para a altitude 5.
3. Acabamos usando o comando c(linha 76), para trocar ou mudar o nosso robô de controle.

**Trocas de robô**
1. Agora usamos "m", que significa mudar o robô e trocamos para o robô Link, e fazemos com que ele morra afogado no lago.
2. E depois trocamos para o Spock e Bob que acabam se movendo sem incidentes.
