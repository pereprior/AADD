package T2.ex2

import java.awt.EventQueue
import java.awt.FlowLayout
import java.awt.GridLayout
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import javax.swing.*

class Exercici2 : JFrame() {
    val fieldLabel = JLabel("File:")
    val textField = JTextField(25)
    val openButton = JButton("Open")
    val saveButton = JButton("Save")
    val textArea = JTextArea(10, 50)
    val scrollPane = JScrollPane(textArea)

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        setLayout(GridLayout(2, 1))
        setTitle("Text Editor")

        val mainPanel = JPanel(GridLayout(0, 1))
        val fieldPanel = JPanel(FlowLayout())
        mainPanel.add(fieldPanel)
        fieldPanel.add(fieldLabel)
        fieldPanel.add(textField)

        val areaPanel = JPanel(FlowLayout())
        mainPanel.add(areaPanel)
        areaPanel.add(openButton)
        areaPanel.add(saveButton)
        val emptyPanel = JPanel(GridLayout(0, 1))
        emptyPanel.add(scrollPane)
        textArea.setEditable(true)

        add(mainPanel)
        add(emptyPanel)
        pack()

        // prueba con el fichero prueba.txt
        openButton.addActionListener {
            val file = File(textField.text)
            if (file.isFile) {
                val reader = FileReader(file)

                var sb = StringBuilder()
                var r = reader.read()
                while (r != -1) {
                    sb.append(r.toChar())
                    r = reader.read()
                }

                reader.close()
                textArea.setText(sb.toString())
            } else
                textArea.setText("Sorry, this file doesn't exists")
        }

        saveButton.addActionListener {
            val file = File(textField.text)

            if (file.isFile) {
                val text = textArea.text
                val reader = FileWriter(file)
                for (c in text) {
                    reader.write(c.toString())
                }
                reader.close()
            } else
                textArea.setText("Sorry, this file doesn't exists")
        }
    }
}

private fun createWindow() {

    val frame = Exercici2()
    frame.isVisible = true
}

fun main(args: Array<String>) {
    EventQueue.invokeLater(::createWindow)
}