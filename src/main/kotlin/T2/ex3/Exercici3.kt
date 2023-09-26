package T2.ex3

import java.awt.BorderLayout
import java.io.File
import javax.swing.*

class Exercici3 : JFrame() {
    private val area = JTextArea()
    private val scrollPane = JScrollPane(area)

    private val mainBar = JMenuBar()
    private val filesBar = JMenu("Files")
    private val helpBar = JMenu("Help")

    private val openFile = JMenuItem("Open")
    private val saveFile = JMenuItem("Save")
    private val saveLikeFile = JMenuItem("Save like ...")
    private val exitFile = JMenuItem("Exit")
    private val aboutHelp = JMenuItem("Abouts...")

    private val fileChooser = JFileChooser()

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = BorderLayout()
        title = "Advanced Files Manager"
        add(scrollPane)
        area.isEditable = true
        setSize(750, 400)
        jMenuBar = mainBar
        mainBar.add(filesBar)
        mainBar.add(helpBar)

        filesBar.add(openFile)
        filesBar.add(saveFile)
        filesBar.add(saveLikeFile)
        filesBar.add(JSeparator())
        filesBar.add(exitFile)

        helpBar.add(aboutHelp)

        openFile.addActionListener { open() }
        saveFile.addActionListener { save() }
        saveLikeFile.addActionListener { saveLike() }
        exitFile.addActionListener { exit() }
        aboutHelp.addActionListener { about() }
    }

    private fun open() {
        val returnVal = fileChooser.showOpenDialog(this)
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            val file = fileChooser.selectedFile
            area.text = File(file.absolutePath).readText()
        }
    }

    private fun save() {
        val file = fileChooser.selectedFile
        File(file.absolutePath).writeText(area.text)
    }

    private fun saveLike() {
        val returnVal = fileChooser.showSaveDialog(this)
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            save()
        }
    }

    private fun exit() {
        System.exit(0)
    }

    private fun about() {
        JOptionPane.showMessageDialog(this, "This is a file manager app developed by Pere Prior")
    }
}

fun main(args: Array<String>) {
    SwingUtilities.invokeLater { Exercici3().isVisible = true }
}