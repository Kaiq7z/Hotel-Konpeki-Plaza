fun quantidadeGarcons(convidados: Int, duracao: Int): Int {
    var base = convidados / 12
    if (convidados % 12 != 0) {
        base++
    }
    val reforco = duracao / 2
    return base + reforco
}

fun custoBuffet(convidados: Int): Double {
    return convidados * 0.2 * 0.80 + convidados * 0.5 * 0.40 + convidados * 7 * 34.0 / 100
}

fun reservarEvento(dados: DadosHotel) {
    println("\n[Eventos]")
    val convidados = texto("Convidados: ").toIntOrNull()
    if (convidados == null || convidados < 0 || convidados > 350) {
        println("Número de convidados inválido" + if (convidados != null && convidados > 350) ": capacidade excedida." else "")
        return
    }
    val auditorio = if (convidados <= 220) "Laranja" else "Colorado"
    val cadeiras = if (auditorio == "Laranja") maxOf(0, convidados - 150) else 0
    println("Auditório selecionado: $auditorio ($cadeiras cadeiras adicionais)")
    val dia = texto("Dia da semana (sem acentos e em minúsculas, exemplo: segunda): ")
    val fechamento = when (dia) {
        "segunda", "terca", "quarta", "quinta", "sexta" -> 23
        "sabado", "domingo" -> 15
        else -> { erro(); return }
    }
    val inicio = inteiro("Hora inicial: ", 0, 23)
    val duracao = inteiro("Duração (1-12 horas): ", 1, 12)
    val fim = inicio + duracao
    if (inicio < 7 || fim > fechamento) {
        println("Auditório indisponível nesse horário.")
        return
    }
    val empresa = nomeObrigatorio("Empresa: ")
    val garcons = quantidadeGarcons(convidados, duracao)
    val custoGarcons = garcons * duracao * 10.50
    val buffet = custoBuffet(convidados)
    val total = custoGarcons + buffet
    println("\nRelatório do evento")
    println("Auditório: $auditorio | Empresa: $empresa")
    println("Dia da semana: $dia | Início: ${inicio}h | Fim: ${fim}h")
    println("Convidados: $convidados | Garçons: $garcons | Duração: ${duracao}h")
    println("Café: ${numero(convidados * 0.2)} L")
    println("Água: ${numero(convidados * 0.5)} L")
    println("Salgados: ${convidados * 7} un")
    println("Custo com garçons: ${dinheiro(custoGarcons)}")
    println("Custo buffet: ${dinheiro(buffet)}\nTotal geral: ${dinheiro(total)}")
    if (confirmar("Confirmar reserva? (S/N): ")) {
        dados.eventos.add(Evento(empresa, auditorio, dia, inicio, duracao, total))
        println("Auditório reservado para $empresa: $dia às ${inicio}hs")
        println("Reserva efetuada com sucesso.")
    } else println("Reserva não efetuada.")
}
