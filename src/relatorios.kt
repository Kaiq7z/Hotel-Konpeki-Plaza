fun linhaRelatorio(descricao: String, valor: String) {
    println(descricao.padEnd(38) + " | " + valor.padStart(18))
}

fun relatorios(dados: DadosHotel) {
    println("\n[Relatórios Operacionais - Konpeki Plaza]")
    var hospedagem = 0.0
    for (reserva in dados.reservas) {
        hospedagem = hospedagem + reserva.total
    }
    var eventos = 0.0
    for (evento in dados.eventos) {
        eventos = eventos + evento.total
    }
    var ocupados = 0
    for (quarto in dados.quartos) {
        if (quarto) {
            ocupados++
        }
    }
    println("-".repeat(59))
    linhaRelatorio("Reservas de quartos confirmadas", dados.reservas.size.toString())
    linhaRelatorio("Taxa de ocupação atual", numero(ocupados / 20.0 * 100) + "%")
    linhaRelatorio("Hóspedes cadastrados", dados.hospedes.size.toString())
    linhaRelatorio("Eventos confirmados", dados.eventos.size.toString())
    linhaRelatorio("Receita de hospedagem", dinheiro(hospedagem))
    linhaRelatorio("Receita de eventos", dinheiro(eventos))
    linhaRelatorio("Receita total", dinheiro(hospedagem + eventos))
    println("-".repeat(59))
}
