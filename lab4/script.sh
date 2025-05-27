#!/bin/bash

SRC_DIR="src"
BIN_DIR="bin"
MAIN_CLASS="simulador.Main" # Ajuste se o pacote/nome da classe Main for diferente

rm -rf "$BIN_DIR"
mkdir "$BIN_DIR" >/dev/null 2>&1

find "$SRC_DIR" -name "*.java" > sources.list
javac -encoding UTF-8 -d "$BIN_DIR" -sourcepath "$SRC_DIR" @sources.list >/dev/null 2>&1
COMPILE_STATUS=$?
rm sources.list

if [ $COMPILE_STATUS -ne 0 ]; then
    find "$SRC_DIR" -name "*.java" > sources.list # Recria para mostrar erros
    javac -encoding UTF-8 -d "$BIN_DIR" -sourcepath "$SRC_DIR" @sources.list
    rm sources.list
    echo "ERRO DE COMPILAÇÃO!" >&2
    exit 1
fi

# Executa o programa esperando entrada interativa do terminal
java -cp "$BIN_DIR" "$MAIN_CLASS"