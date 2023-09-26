package T2.ex1

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ImageFile(inputFile: File) {
    private val extension = "bmp"
    private val headerBuffer = ByteArray(54) //primeros 54 bytes que se tienen que copiar sin modificar
    var baseFile:File = File("")

    init {

        if (inputFile.exists()){

            if (inputFile.extension==extension) {

                baseFile=inputFile

            } else {
                println("Sorry, the program only supports .bpm files")
            }

        } else {
            println("The file doesn't exists")
        }

    }

    fun negativeFormat() {
        val negativeFile = File("Ex1-Images/${baseFile.nameWithoutExtension}_n.$extension")
        val newFileWriter = FileOutputStream(negativeFile)
        val baseContent = FileInputStream(baseFile)

        baseContent.read(headerBuffer)
        newFileWriter.write(headerBuffer)

        val baseFileLength = baseFile.length().toInt()
        val buffer = ByteArray(baseFileLength - headerBuffer.size)
        val bytesRead = baseContent.read(buffer)

        for (i in 0 until  bytesRead) {
            buffer[i] = (255 - buffer[i].toInt()).toByte()
        }

        newFileWriter.write(buffer)

        newFileWriter.close()
    }

    fun darkFormat() {
        val darkImage = File("Ex1-Images/${baseFile.nameWithoutExtension}_o.$extension")
        val newFileWriter = FileOutputStream(darkImage)
        val baseContent = FileInputStream(baseFile)

        baseContent.read(headerBuffer)
        newFileWriter.write(headerBuffer)

        while (true) {
            val red = baseContent.read()
            val green = baseContent.read()
            val blue = baseContent.read()

            if (red == -1 || green == -1 || blue == -1) {
                break
            }

            val newRed = red / 2
            val newGreen = green / 2
            val newBlue = blue / 2

            newFileWriter.write(newRed)
            newFileWriter.write(newGreen)
            newFileWriter.write(newBlue)
        }

        newFileWriter.close()
    }

    fun blackWhiteFormat() {
        val bwImage = File("Ex1-Images/${baseFile.nameWithoutExtension}_bn.$extension")
        val newFileWriter = FileOutputStream(bwImage)
        val baseContent = FileInputStream(baseFile)

        baseContent.read(headerBuffer)
        newFileWriter.write(headerBuffer)

        while (true) {
            val red = baseContent.read()
            val green = baseContent.read()
            val blue = baseContent.read()

            if (red == -1 || green == -1 || blue == -1) {
                break
            }

            val gray = (red + green + blue) / 3

            newFileWriter.write(gray)
            newFileWriter.write(gray)
            newFileWriter.write(gray)
        }

        newFileWriter.close()
    }



}