import java.io.Serializable

class Ruta(var nom: String, var desnivell: Int, var desnivellAcumulat: Int, var llistaDePunts: MutableList<PuntGeo>) :
    Serializable {
    companion object {
        private const val serialVersionUID: Long = 1
    }

    fun addPunt(p: PuntGeo) {
        llistaDePunts.add(p)
    }

    fun getPunt(i: Int): PuntGeo {
        return llistaDePunts.get(i)
    }

    fun getPuntNom(i: Int): String {
        return llistaDePunts.get(i).nom
    }

    fun getPuntLatitud(i: Int): Double {
        return llistaDePunts.get(i).coord.latitud
    }

    fun getPuntLongitud(i: Int): Double {
        return llistaDePunts.get(i).coord.longitud
    }

    fun size(): Int {
        return llistaDePunts.size
    }

    fun mostrarRuta() {
        println("Ruta: $nom")
        println("Desnivell: $desnivell")
        println("Desnivell acumulat: $desnivellAcumulat")
        println("Te ${size()} punts")
        for (i in 0 until size()) {
            println("Punt ${i+1}: ${getPuntNom(i)} (${getPuntLatitud(i)} ,${getPuntLongitud(i)})")
        }
    }
}