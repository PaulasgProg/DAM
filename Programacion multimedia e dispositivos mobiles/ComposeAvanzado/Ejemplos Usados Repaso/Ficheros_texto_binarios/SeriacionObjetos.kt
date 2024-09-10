

import java.io.*

data class PersonaFich(val nombre: String, val edad: Int) : Serializable

fun serializarObjeto(objeto: Any, archivo: File) {
    ObjectOutputStream(FileOutputStream(archivo)).use { it.writeObject(objeto) }
}

fun deserializarObjeto(archivo: File): Any? {
    return ObjectInputStream(FileInputStream(archivo)).use { it.readObject() }
}

fun main() {
    val persona = PersonaFich("Juan", 25)
    val archivo = File("data${File.separator}persona.dat")

    // Serializar objeto
    serializarObjeto(persona, archivo)

    // Deserializar objeto
    val objetoDeserializado = deserializarObjeto(archivo)
    if (objetoDeserializado is PersonaFich) {
        println("Nombre: ${objetoDeserializado.nombre}")
        println("Edad: ${objetoDeserializado.edad}")
    }
}