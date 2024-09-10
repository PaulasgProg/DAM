import java.io.Serializable;

public class Profesor implements Serializable {

    private int idProfesor;
    private String nombre;
    private Asignatura[] asignaturas;
    private Especialidad especialidad;

    public Profesor() {

    }

    public Profesor(int id, String nombre, Asignatura[] asignaturas, Especialidad es) {
        super();
        this.asignaturas = asignaturas;
        this.idProfesor = id;
        this.nombre = nombre;
        this.especialidad = es;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAsignaturas(Asignatura[] asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
