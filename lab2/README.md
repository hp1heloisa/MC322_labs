# Laboratório 2 - Simulação de Robôs Inteligentes

## Introdução
Seja bem-vindo a explicação do código do Lab 2!

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
- Sistema de segurança impede danos a outros robôs

#### Robô Teletransportador
- Capacidade de teletransporte vertical
- Mantém coordenadas X e Y durante o transporte
- Ideal para evasão rápida de ameaças aéreas

## Guia de Uso

### Como Executar
1. Execute o programa principal
2. Siga as instruções interativas no console
3. Selecione o tipo de robô desejado

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

### Sugestões de Teste

**Robô Limitado:**
1. Inicie com velocidade (tecla `q`)
2. Teste movimentos inválidos (`s`/`a` para bordas)
3. Colida com obstáculos para ver comportamento

**Robô Guindaste:**
1. Use `k` para ativar o guindaste
2. Experimente trocar com obstáculos em diferentes posições
3. Teste limites de velocidade

**Robô Destruidor:**
1. Ajuste altitude com `u`(para cima) e `j`(para baixo)
2. Use `p` para scanear obstáculos
3. Destrua obstáculos com `k` em várias direções

**Robô Teletransportador:**
1. Teste teletransporte (tecla `k`) para várias altitudes
2. Experimente altitudes extremas (valores negativos ou acima do máximo)
3. Combine com movimentos horizontais
