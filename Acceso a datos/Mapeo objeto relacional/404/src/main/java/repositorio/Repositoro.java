package repositorio;

import java.util.List;

public interface Repositoro<T> {
	void insertarUno(T t);
	void borrar(T t);
	List<T> encontrarTodos();
	T encontrarUnoPorString(String nombre);
	void actualizar(T t);

}
