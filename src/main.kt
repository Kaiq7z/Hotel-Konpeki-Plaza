fun autenticar(): String? {
    val nome = nomeObrigatorio("Nome do usuário: ")
    for (tentativa in 1..3) {
        if (texto("Senha: ") == "2678") return nome
        println("Senha incorreta. Tentativa $tentativa de 3.")
    }
    println("Sistema bloqueado: limite de tentativas atingido.")
    return null
}

fun menu(nome: String, dados: DadosHotel) {
    while (true) {
        println("\nKonpeki Plaza")
        println("1 - Reservas de Quartos")
        println("2 - Cadastro de Hóspedes")
        println("3 - Eventos")
        println("4 - Ar-Condicionado")
        println("5 - Abastecimento")
        println("6 - Relatórios Operacionais")
        println("7 - Sair")
        when (texto("Opção: ")) {
            "1" -> reservarQuarto(nome, dados)
            "2" -> cadastroHospedes(dados)
            "3" -> reservarEvento(dados)
            "4" -> arCondicionado(nome)
            "5" -> abastecimento(nome)
            "6" -> relatorios(dados)
            "7" -> {
                println("Muito obrigado e até logo, $nome.")
                return
            }
            else -> erro()
        }
    }
}

fun main() {
    println("Bem-vindo ao Konpeki Plaza")
    try {
        val nome = autenticar() ?: return
        println("Bem-vindo ao Hotel Konpeki Plaza, $nome. É um imenso prazer ter você por aqui!")
        menu(nome, DadosHotel())
    } catch (e: EntradaEncerrada) {
        println("\nEntrada encerrada. Sistema finalizado.")
    }
}
