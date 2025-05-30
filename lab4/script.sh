#!/bin/bash

SRC_DIR="src"
BIN_DIR="bin"
MAIN_CLASS="simulador.Main" # Ajuste se o pacote/nome da classe Main for diferente

# 1. Limpar e recriar diretório de saída (silenciosamente)
rm -rf "$BIN_DIR"
mkdir "$BIN_DIR" >/dev/null 2>&1

# 2. Compilar (mostrando erros se houver)
find "$SRC_DIR" -name "*.java" > sources.list
javac -encoding UTF-8 -d "$BIN_DIR" -sourcepath "$SRC_DIR" @sources.list
# A linha acima NÃO tem >/dev/null 2>&1, para que os erros do javac apareçam.
COMPILE_STATUS=$?
rm sources.list

# 3. Verificar se a compilação falhou e reportar o erro
if [ $COMPILE_STATUS -ne 0 ]; then
    echo "--------------------------------------" >&2
    echo "ERRO DE COMPILAÇÃO DETECTADO!" >&2
    echo "Verifique as mensagens do compilador acima." >&2
    echo "--------------------------------------" >&2
    exit 1 # Termina o script se houve erro de compilação
fi

# 4. Executar o programa (apenas se a compilação foi bem-sucedida)
java -cp "$BIN_DIR" "$MAIN_CLASS" # Removido "< input.txt" para entrada interativa