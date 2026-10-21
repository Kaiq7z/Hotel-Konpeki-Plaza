fun subtotalReserva(diaria: Double, dias: Int, fator: Double): Double {
    val subtotal = diaria * dias * fator
    return subtotal
}

fun mapaQuartos(dados: DadosHotel) {
    println("Mapa de quartos: L = livre | O = ocupado")
    for (linha in 0..3) {
        for (coluna in 0..4) {
            val indice = linha * 5 + coluna
            val status = if (dados.quartos[indice]) "O" else "L"
            print("${(indice + 1).toString().padStart(2, '0')}:$status  ")
        }
        println()
    }
}

fun reservarQuarto(nome: String, dados: DadosHotel) {
    println("\n[Reservas]")
    var livres = 0
    for (ocupado in dados.quartos) {
        if (!ocupado) {
            livres++
        }
    }
    if (livres == 0) {
        println("Não há quartos livres.")
        return
    }
    val diaria = texto("Informe o valor da diária: ").replace(',', '.').toDoubleOrNull()
    val dias = texto("Informe a quantidade de diárias (1-30): ").toIntOrNull()
    if (diaria == null || !diaria.isFinite() || diaria <= 0 || dias == null || dias !in 1..30) {
        println("Valor inválido, $nome")
        return
    }
    val hospede = nomeObrigatorio("Informe o nome completo do hóspede: ")
    var tipo: String
    while (true) {
        tipo = texto("Tipo de quarto (S/E/L): ").uppercase()
        if (tipo == "S" || tipo == "E" || tipo == "L") break
        erro()
    }
    var fator = 1.0
    var descricao = "Standard"
    if (tipo == "E") {
        fator = 1.35
        descricao = "Executivo"
    } else if (tipo == "L") {
        fator = 1.65
        descricao = "Luxo"
    }
    var quarto: Int
    while (true) {
        quarto = inteiro("Escolha um quarto (1-20): ", 1, 20)
        if (!dados.quartos[quarto - 1]) break
        println("Quarto já está ocupado")
        print("Quartos livres: ")
        for (numero in 1..20) {
            if (!dados.quartos[numero - 1]) {
                print("$numero ")
            }
        }
        println()
    }
    val subtotal = subtotalReserva(diaria, dias, fator)
    val taxa = subtotal * 0.10
    val total = subtotal + taxa
    if (!total.isFinite()) {
        println("Valor inválido, $nome")
        return
    }
    println("Resumo:\nHóspede: $hospede\nQuarto: $quarto ($descricao)")
    println("Subtotal: ${dinheiro(subtotal)}\nTaxa de serviço (10%): ${dinheiro(taxa)}\nTotal: ${dinheiro(total)}")
    if (confirmar("$nome, confirma a reserva? (S/N): ")) {
        dados.quartos[quarto - 1] = true
        dados.reservas.add(Reserva(hospede, quarto, dias, total))
        println("Reserva efetuada com sucesso.")
    } else println("Reserva não efetuada.")
    mapaQuartos(dados)
}
