fun escolherCombustivel(posto: String, alcool: Double, gasolina: Double): Combustivel {
    if (alcool <= gasolina * 0.70) {
        return Combustivel(posto, "álcool", alcool)
    } else {
        return Combustivel(posto, "gasolina", gasolina)
    }
}

fun ordenarCombustiveis(opcoes: MutableList<Combustivel>) {
    for (i in 0 until opcoes.size) {
        for (j in i + 1 until opcoes.size) {
            if (opcoes[i].preco > opcoes[j].preco) {
                val auxiliar = opcoes[i]
                opcoes[i] = opcoes[j]
                opcoes[j] = auxiliar
            }
        }
    }
}

fun abastecimento(nome: String) {
    println("\n[Abastecimento]")
    val cenarios = mutableListOf<Combustivel>()
    val ideais = mutableListOf<Combustivel>()
    for (posto in listOf("Wayne Oil", "Stark Petrol")) {
        println(posto)
        val alcool = decimal("Preço do álcool: ", 0.0, Double.MAX_VALUE / 42, false)
        val gasolina = decimal("Preço da gasolina: ", 0.0, Double.MAX_VALUE / 42, false)
        cenarios.add(Combustivel(posto, "álcool", alcool))
        cenarios.add(Combustivel(posto, "gasolina", gasolina))
        ideais.add(escolherCombustivel(posto, alcool, gasolina))
    }
    println("Ranking de custo dos quatro cenários (42 L):")
    ordenarCombustiveis(cenarios)
    for (indice in 0 until cenarios.size) {
        val opcao = cenarios[indice]
        println("${indice + 1}. ${opcao.posto} | ${opcao.tipo} | ${dinheiro(opcao.preco * 42)}")
    }
    println("Opções ideais pela regra dos 70%, em ordem de custo:")
    ordenarCombustiveis(ideais)
    for (opcao in ideais) {
        println("${opcao.posto}: melhor opção = ${opcao.tipo} | Total (42L) = ${dinheiro(opcao.preco * 42)}")
    }
    val melhor = ideais[0]
    println("$nome, é mais barato abastecer com ${melhor.tipo} no posto ${melhor.posto}.")
}
