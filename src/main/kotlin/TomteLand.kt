
class TomteLand {

    val Bladlusen = Tomte("Bladlusen")
    val Myran = Tomte("Myran", listOf(Bladlusen))
    val Gråsuggan = Tomte("Gråsuggan")
    val Räven = Tomte("Räven", listOf(Gråsuggan,Myran))
    val Dammråttan = Tomte("Dammråttan")
    val Skumtomten = Tomte("Skumtomten", listOf(Dammråttan))
    val Trötter = Tomte("Trötter", listOf(Skumtomten))
    val Haren = Tomte("Haren")
    val Nyckelpigan = Tomte("Nyckelpigan")
    val Rådjuret = Tomte("Rådjuret")
    val Butter = Tomte("Butter", listOf(Rådjuret,Nyckelpigan,Haren,Räven))
    val Tröger = Tomte("Tröger")
    val Blyger = Tomte("Blyger")
    val Glader = Tomte("Glader", listOf(Tröger,Trötter,Blyger))
    val Tomten = Tomte("Tomten", listOf(Glader,Butter))


    val Hierarki = mapOf(
        "Tomten" to listOf(Glader,Butter),
        "Glader" to listOf(Tröger,Trötter,Blyger),
        "Butter" to listOf(Rådjuret,Nyckelpigan,Haren,Räven),
        "Trötter" to listOf(Skumtomten),
        "Skumtomten" to listOf(Dammråttan),
        "Räven" to listOf(Gråsuggan,Myran),
        "Myran" to listOf(Bladlusen))


    fun getUnderlings(currentName: String, res: MutableList<String>): MutableList<String> {

        tailrec fun findAllUnderlings(Tomtar: List<Tomte>, res: MutableList<String>):MutableList<String>{
            // namn på tomtarna i listan
            res += Tomtar.map { t -> t.name }
            // lista med tomtarnas tomtar
            Tomtar.forEach { t -> if (Hierarki.containsKey(t.name))
                findAllUnderlings(Hierarki.getValue(t.name), res) }

            return res
        }

        return if (Hierarki.containsKey(currentName))
            findAllUnderlings(Hierarki.getValue(currentName), res) else return res

    }



}
