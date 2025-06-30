# Laboratório 5 - Modularização e Arquivos

## Introdução
Seja bem-vindo a explicação do código do Lab 4!

Após alguns anos de paz, algumas coisas fugiram de controle: Os robôs descobriram que podem morrer agora. Por isso, eles estão desesperados. A fim de contornar tal situação, os engenheiros desonvolveram uma nova ferramenta -- o Piloto Automático. Com essa nova funcionalidade, os robôs minimizam sua chance de morte, porém como ainda está em períodos de testes, ainda ocorrem alguns deslizes. Então, vamos testar essa nova funcionalidade!

OBS: o nosso diagrama é o arquivo relacionamentos.jpg

## Tipos de Robôs

### Robôs Terrestres
Todos os robôs terrestres possuem velocidade máxima de 5km/h por padrão.

#### Robô Limitado
- Movimentação restrita aos quatro pontos cardeais (frente, trás, direita, esquerda)
- Funcionalidades básicas de movimento
- Apresenta mensagens melancólicas durante a execução

#### Robô Guindaste
- Equipado com guindaste para manipulação de obstáculos
- Pode trocar de posição com obstáculos no caminho
- Mantém o ambiente organizado reposicionando os obstáculos
- Pode emitir frases motivacionais durante o uso

### Robôs Aéreos
- Capacidade de voo com altitude controlável
- Altitude máxima configurável pelo usuário

#### Robô Destruidor
- Tecnologia avançada para destruição de obstáculos
- Pode eliminar obstáculos em todas as direções
- Gera frases ameaçadoras e provocativas automaticamente

#### Robô Teletransportador
- Capacidade de teletransporte vertical
- Mantém coordenadas X e Y durante o teletransporte
- Ideal para evasão rápida de ameaças aéreas
- Apresenta frases curiosas e existenciais sobre o teletransporte

### Robô Recarregável
- Possui energia limitada e precisa se recarregar em oficinas
- Pode verificar seu nível de bateria
- Alerta de nível de enrgia baixo

### Robô Patrulheiro 
- Realiza patrulhas em áreas definidas do ambiente.
- Se movimenta em trajetos fixos e circulares, monitorando obstáculos e atualizando informações constantemente.
- Ideal para manter vigilância e identificar mudanças no ambiente.
- Pode ser usado em missões de monitoramento autônomo.

### Robô Explorador 
- Programado para explorar o ambiente de forma semi-autônoma.
- Escolhe aleatoriamente direções para avançar, desviando de obstáculos conforme necessário.
- Utiliza sensores para analisar o ambiente e reportar informações.
- É útil para reconhecimento territorial e mapeamento de regiões desconhecidas.

### Agente Inteligente
- Robô avançado com capacidade de executar missões de forma autônoma.
- Pode receber diferentes tipos de missão (Explorar, Monitorar, Buscar Ponto) e executá-las sem intervenção manual.
- Analisa o ambiente, toma decisões baseadas nos sensores e ajusta seu comportamento conforme a situação.
- Representa o avanço da robótica com inteligência embarcada e comportamento orientado a objetivos.

## Missões

### Missão Buscar Ponto
- Objetivo: Levar o robô até uma coordenada específica do ambiente.
- O robô traça uma trajetória em direção ao ponto-alvo, desviando de obstáculos se necessário.
- Pode ser usada para posicionar o robô em locais estratégicos ou para alcançar objetivos específicos.

### Missão Explorar
- Objetivo: Realizar uma exploração aleatória do ambiente durante um número determinado de passos.
- O robô se move por diferentes regiões, evitando obstáculos e mapeando a área ao redor.
- Ideal para descoberta de terreno, reconhecimento e levantamento de dados ambientais.

### Missão Monitorar
- Objetivo: Permanecer em uma coordenada e observar o ambiente durante um número fixo de ciclos.
- O robô utiliza seus sensores para registrar mudanças de temperatura, umidade e movimentação.
- Útil para vigilância passiva, estudos ambientais ou espera em pontos estratégicos.

### Missão Patrulhar
- Objetivo: Realizar uma patrulha contínua em uma área definida.
- O robô percorre uma rota determinada ou circular, mantendo o ambiente sob vigilância constante.
- Recomendado para missões de segurança e monitoramento de regiões de interesse.

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

### Oficina
- Inicial: O
- Recarrega robôs com capacidade de energia
- Pode religar robôs desligados

## Sensores 
### Sensor Plano
- Mostra os objetos próximos no mesmo plano em um raio r, definido pelo sensor

### Sensor Altitude
- Ele mostra os objetos próximos em relação até uma altura r, definida pelo sensor.

### Sensor Temperatura
- Mostra a temperatura da coordenada, utilizando a distância eucliadiana entre vários obstáculos e realizando uma média entre todos.

### Sensor Umidade
- Mesma lógica do sensor temperatura, mas com umidade.

## Interfaces

### Entidade
- Interface geral para qualquer elemento posicionado no ambiente. Define coordenadas e representação.

### InterfaceRobo
- Interface principal dos robôs. Estende **Entidade**, **Sensoriavel**, **Comunicavel** e **Recarregavel**.

### Comunicavel
- Define robôs que podem enviar e receber mensagens, dependendo do seu estado (**Ligado**, **Desligado**).

### Recarregavel 
- Define robôs que possuem bateria, podem recarregar e consomem energia.

### Sensoriavel
- Define entidades que possuem sensores e podem acionar sua leitura.

### Emissor
- Interface para envio de mensagens (herdada por **Comunicavel**).

### Receptor
- Interface para recebimento de mensagens (herdada por **Comunicavel**).

### MoverParaOutroPonto
- Define robôs capazes de se locomover até uma coordenada específica no ambiente.
- É usada principalmente em missões como Buscar Ponto, onde o robô precisa atingir um destino definido.
- Robôs que implementam essa interface devem considerar obstáculos, energia e limites do ambiente durante o trajeto.

## Exceções Personalizadas

### ColisaoException
- Lançada quando o robô tenta se mover para uma posição ocupada por outro obstáculo.

### EnergiaInsuficienteException
-  Lançada quando um robô não possui energia suficiente para realizar uma ação.

### ForadosLimitesException
- Lançada quando o robô tenta sair dos limites do ambiente.

### RoboDesligadoException
- Lançada quando uma ação depende do estado “ligado” do robô, mas ele está desligado.

### OperacaoNaoSuportadaException
- Lançada quando um robô tenta realizar uma ação que não é compatível com sua natureza.

### TipoDeRoboInexistenteException
- Lançada quando o tipo de robô informado não está entre os reconhecidos.


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
| c     | Remover/trocar robô | Todos                   |
| ?     | Ouvir ambiente      | Todos                   |
| !     | Enviar mensagem     | Todos                   |  

### Os nossos testes
 O piloto automático é a nossa entrada padrão, o arquivo "input.txt". Se quiserem rodar o `./script.sh`, sem alterações, ele irá funcionar direitinho. Sugiro que se teste copiando algumas partes, pois o teste irá vir diversas linhas de uma vez.
**Primeiro robô:**
#### Robô limitado:
- Nome: Link
- Começamos tentando mover para frente("w" -- linha 6 da entrada), e acabamos chocando com uma Vegetação densa. Por isso, o robô não anda.
- Nos próximos movimentos, o robô acaba andando aleatoriamente e criamos um novo robô.

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
2. Depois entramos no mundo do Spock e fizemos com que ele morresse queimado nas chamas.
3. E depois trocamos para o Bob que acaba se movendo sem incidentes.

## Exemplo de input.txt

```txt
ROBO DESTRUIDOR oi 0 0 0
ROBO GUINDASTE io 0 1 0
MISSAO oi BUSCAR 0 1 6
MISSAO io EXPLORAR
EXECUTAR oi
EXECUTAR io
SAIR
```

## Logs e Execução

O simulador imprime logs no console, incluindo movimentos, mensagens de erro e eventos (como destruição de obstáculos, missões concluídas ou falhas). Todos os robôs são registrados em tempo real. Ao final, cada missão deixa seu rastro no arquivo de log correspondente (ex: `missao_oi.txt`, `missao_io.txt`).
