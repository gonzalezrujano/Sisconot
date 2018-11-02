package com.corex.sisconot

import java.io.File
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

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
        } catch (e: IOException) { println("Error: Durante la ejecucion del Tesseract") }
    }
}

class Extraccion(Ubicacion: String) {
    var Archivo: File = File("$Ubicacion/outputbase.txt")
    var ContenidoArchivo: Scanner = Scanner(Archivo)
    var Busqueda: String = ""
    var LineaActual: String = ""
    var NumeroDeResultados : Int = 0
    var NumeroDeIteraciones : Int = 0
    var ListaResultados : ArrayList<String> = ArrayList()

    fun LeerContenido() {
        while (ContenidoArchivo.hasNextLine()) {
            LineaActual = ContenidoArchivo.nextLine()
            RevisarLinea()
            if (NumeroDeResultados == NumeroDeIteraciones) {
                break
            }
        }
        ContenidoArchivo.close()
    }

    fun RevisarLinea() {
        if (LineaActual.contains(Busqueda)) {
            AumentarResultados()
            GuardarLinea()
        }
    }

    fun AumentarResultados() {
        NumeroDeResultados ++
    }

    fun GuardarLinea() {
        ListaResultados.add(LineaActual)
    }

}

fun main(args: Array<String>) {
    println("Comando de Tesseract")

    val Ubicacion : String = "/home/leonardo/Descargas"
    //Escaneo("Scan.JPG", Ubicacion)
    var extraccion = Extraccion(Ubicacion)
    extraccion.Busqueda = "CASTELLANO"
    extraccion.NumeroDeIteraciones = 3
    extraccion.LeerContenido()
    println(extraccion.ListaResultados.get(1))

}