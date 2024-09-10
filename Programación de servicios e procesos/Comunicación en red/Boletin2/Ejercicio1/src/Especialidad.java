import java.io.Serializable;

public class Especialidad implements Serializable {
    private int id;
    private String nombreEsp;

    public Especialidad() {

    }

    public Especialidad(int id, String nString) {
        super();
        this.id = id;
        this.nombreEsp = nString;
    }

    public int getId() {
        return id;
    }

    public String getNombreEsp() {
        return nombreEsp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreEsp(String nombreEsp) {
        this.nombreEsp = nombreEsp;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
