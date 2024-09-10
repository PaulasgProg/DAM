import java.io.Serializable;

public class Asignatura implements Serializable {

    private int id;
    private String nombreAsig;

    public Asignatura() {

    }

    public Asignatura(int id, String noString) {
        super();
        this.id = id;
        this.nombreAsig = noString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAsig() {
        return nombreAsig;
    }

    public void setNombreAsig(String nombreAsig) {
        this.nombreAsig = nombreAsig;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
