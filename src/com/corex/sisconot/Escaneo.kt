package com.corex.sisconot

import java.io.File
import java.io.IOException
import java.util.*

class Imagen(nombre: String, path: String) { val Ubicacion: String = "$path/$nombre" }

class Escaneo(nombreImagen: String, pathImagen: String) {
    private var imagen: Imagen = Imagen(nombreImagen, pathImagen)
    private val Comando: String = "tesseract ${imagen.Ubicacion} $pathImagen/outputbase"

    init {
        println("Lanzar comando")
        try {
            val proceso: Process = Runtime.getRuntime().exec(Comando)
            println("Extrayendo Texto")
            proceso.waitFor()
        } catch (e: IOException) { println("Error en la ejecucion del Tesseract") }
    }
}

class Extraccion(UbicacionFichero: String) {
    val Ubicacion: String = UbicacionFichero
    var Archivo: File = File("$Ubicacion/outputbase.txt")
    var ContenidoArchivo: Scanner = Scanner(Archivo)

    init { LeerContenido() }

    fun LeerContenido() {
        while (ContenidoArchivo.hasNextLine()) {
            println(ContenidoArchivo.nextLine())
        }
        ContenidoArchivo.close()
    }
}

fun main(args: Array<String>) {
    println("Comando de Tesseract")

    //Escaneo("Scan.JPG", "/home/leonardo/Descargas")
    Extraccion("/home/leonardo/Descargas")
}