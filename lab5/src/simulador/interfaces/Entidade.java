package simulador.interfaces;

import simulador.ambiente.TipoEntidade;

public interface Entidade {

    int getX();

    int getY();

    int getZ();

    void setX(int x);

    void setY(int y);

    void setZ(int z);

    TipoEntidade getTipo();

    String getDescricao();

    char getRepresentacao();
}
