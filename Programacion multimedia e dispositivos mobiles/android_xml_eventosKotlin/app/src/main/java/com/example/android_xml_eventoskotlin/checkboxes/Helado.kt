package com.example.android_xml_eventoskotlin.checkboxes

/**
 *  Objeto que representa el helado
 */
object Helado {
    var fresa = false
    var vainilla = false
    var pistacho = false

    override fun toString(): String {
        // Construimos el string
        var stringHelado: String

        // Si están los 3 ingredientes:
        if (fresa && vainilla && pistacho) stringHelado =
            "Tu helado será de jamón, vainilla y pistacho."
        else if (fresa) {
            stringHelado = "Tu helado será de fresa"
            stringHelado += if (vainilla) " y vainilla." else if (pistacho) " y pistacho." else "."
        } else if (vainilla) {
            stringHelado = "Tu helado será de vainilla"
            stringHelado += if (pistacho) " y pistacho." else "."
        } else if (pistacho) {
            stringHelado = "Tu helado será de pistacho."
        } else stringHelado = ""

        return stringHelado
    }
}