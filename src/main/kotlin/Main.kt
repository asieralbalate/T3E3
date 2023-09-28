

import java.io.ObjectInputStream
import java.io.FileInputStream
import javax.xml.parsers.DocumentBuilderFactory
import java.io.EOFException
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(args: Array<String>) {
    val f = ObjectInputStream(FileInputStream ("Rutes.obj"))

    val doc = DocumentBuilderFactory.newInstance ().newDocumentBuilder().newDocument()
    val arrel = doc.createElement("rutes")
    doc.appendChild(arrel)

    try {
        while (true) {
            val e = f.readObject () as Ruta
            val rut = doc.createElement ("ruta")

            val nom = doc.createElement ("nom")
            nom.appendChild(doc.createTextNode(e.nom)) // forma llarga: afegim un fill que és un node de text
            rut.appendChild(nom)

            val desn = doc.createElement("desnivell")
            desn.setTextContent(e.desnivell.toString()) // forma curta: amb setTextContent() li posem contingut
            rut.appendChild(desn)

            val desnAc = doc.createElement("desnivellAcumulat")
            desnAc.setTextContent(e.desnivellAcumulat.toString())
            rut.appendChild(desnAc)

            val punts = doc.createElement ("punts")
            val numPunts = e.size()
            for (i in 0 until numPunts) {
                val punt = doc.createElement("punt")
                punt.setAttribute("num", Integer.toString(i+1))
                punts.appendChild(punt)

                val nom = doc.createElement ("nom")
                nom.setTextContent(e.getPuntNom(i))// forma llarga: afegim un fill que és un node de text
                punt.appendChild(nom)

                val latitud = doc.createElement ("latitud")
                nom.setTextContent(e.getPuntLatitud(i).toString())// forma llarga: afegim un fill que és un node de text
                punt.appendChild(latitud)

                val longitud = doc.createElement ("longitud")
                nom.setTextContent(e.getPuntLongitud(i).toString())
                punt.appendChild(longitud)

                punts.appendChild(punt)
            }

            arrel.appendChild(rut)
            arrel.appendChild(punts)
        }

    } catch (eof: EOFException) {
        f.close();
    }
    val trans = TransformerFactory.newInstance().newTransformer()
    trans.setOutputProperty(OutputKeys.INDENT, "yes")
    trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")

    trans.transform(DOMSource(doc), StreamResult("Rutes.xml"))
}



