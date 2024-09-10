import java.io.Serializable;

public class Numeros implements Serializable {
    private int numero;
    private long cuadrado;
    private long cubo;

    Numeros() {

    }

    Numeros(int numero, long c, long cubo) {
        this.cuadrado = c;
        this.numero = numero;
        this.cubo = cubo;

    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public int getNumero() {
        return numero;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
