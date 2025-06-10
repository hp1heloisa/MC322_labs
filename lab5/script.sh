SRC_DIR="src"
BIN_DIR="bin"
MAIN_CLASS="simulador.Main"


rm -rf "$BIN_DIR"
mkdir "$BIN_DIR" >/dev/null 2>&1


find "$SRC_DIR" -name "*.java" > sources.list
javac -encoding UTF-8 -d "$BIN_DIR" -sourcepath "$SRC_DIR" @sources.list

COMPILE_STATUS=$?
rm sources.list


if [ $COMPILE_STATUS -ne 0 ]; then
    echo "--------------------------------------" >&2
    echo "ERRO DE COMPILAÇÃO DETECTADO!" >&2
    echo "Verifique as mensagens do compilador acima." >&2
    echo "--------------------------------------" >&2
    exit 1 
fi

java -cp "$BIN_DIR" "$MAIN_CLASS" 