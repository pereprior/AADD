package T3.exercices.ex2

import java.io.*

fun main(args:Array<String>){
    val fileInput = DataInputStream(FileInputStream("Rutes.dat"))
    val fileOutput = ObjectOutputStream(FileOutputStream("Rutes.obj"))

    while (fileInput.available() > 0) {
        val name = fileInput.readUTF()
        val desnivell = fileInput.readInt()
        val desnivellAcumulat = fileInput.readInt()
        var llistaDePunts = mutableListOf<GeoPoint>()

        val pointsNumber = fileInput.readInt()
        for (e in 0 until pointsNumber){
            val coordName = fileInput.readUTF()
            val latitude = fileInput.readDouble()
            val length = fileInput.readDouble()

            val coord = Coordinate(latitude,length)
            val point = GeoPoint(coordName,coord)
            llistaDePunts.add(point)
        }

        val rute = Rute(name,desnivell,desnivellAcumulat,llistaDePunts)
        fileOutput.writeObject(rute)

        println(rute.mostrarRuta())
    }

    fileOutput.close()
    fileInput.close()
}

