import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun nomeCadastrado(hospedes: List<Hospede>, nome: String): Boolean {
    for (hospede in hospedes) {
        if (hospede.nome == nome) {
            return true
        }
    }
    return false
}

fun listarHospedes(hospedes: List<Hospede>): List<Hospede> {
    val ordenados = hospedes.toMutableList()
    // Compara os nomes de dois em dois e troca quando estão fora de ordem.
    for (i in 0 until ordenados.size) {
        for (j in i + 1 until ordenados.size) {
            if (ordenados[i].nome.lowercase() > ordenados[j].nome.lowercase()) {
                val auxiliar = ordenados[i]
                ordenados[i] = ordenados[j]
                ordenados[j] = auxiliar
            }
        }
    }
    if (ordenados.isEmpty()) println("Nenhum hóspede cadastrado.")
    for (indice in 0 until ordenados.size) {
        val hospede = ordenados[indice]
        println("[${indice + 1}] ${hospede.nome} | ${hospede.cadastro}")
    }
    return ordenados
}

fun cadastroHospedes(dados: DadosHotel) {
    val hospedes = dados.hospedes
    while (true) {
        println("\n[Cadastro de Hóspedes]")
        println("1-Cadastrar 2-Pesquisar exato 3-Pesquisar prefixo 4-Listar 5-Atualizar 6-Remover 7-Voltar")
        val opcao = texto("Opção: ")
        when (opcao) {
            "1" -> {
                if (hospedes.size >= 15) {
                    println("Máximo de cadastros atingido")
                    continue
                }
                val nome = nomeObrigatorio("Nome do hóspede: ")
                if (nomeCadastrado(hospedes, nome)) println("Hóspede já cadastrado")
                else {
                    val data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                    hospedes.add(Hospede(nome, data))
                    println("Operação realizada com sucesso")
                }
            }
            "2" -> {
                val nome = nomeObrigatorio("Nome exato: ")
                if (nomeCadastrado(hospedes, nome)) println("Hóspede $nome foi encontrado")
                else println("Hóspede não encontrado")
            }
            "3" -> {
                val prefixo = nomeObrigatorio("Prefixo: ")
                val encontrados = mutableListOf<Hospede>()
                for (hospede in hospedes) {
                    if (hospede.nome.startsWith(prefixo)) {
                        encontrados.add(hospede)
                    }
                }
                if (encontrados.isEmpty()) println("Hóspede não encontrado")
                else listarHospedes(encontrados)
            }
            "4" -> listarHospedes(hospedes)
            "5", "6" -> {
                editarHospede(hospedes, opcao == "6")
            }
            "7" -> return
            else -> erro()
        }
    }
}

fun editarHospede(hospedes: MutableList<Hospede>, remover: Boolean) {
    val ordenados = listarHospedes(hospedes)
    if (ordenados.isEmpty()) return
    val indice = inteiro("Índice do cadastro: ", 1, ordenados.size)
    val hospede = ordenados[indice - 1]
    if (remover) {
        hospedes.remove(hospede)
    } else {
        val nome = nomeObrigatorio("Novo nome: ")
        if (nome != hospede.nome && nomeCadastrado(hospedes, nome)) {
            println("Hóspede já cadastrado")
            return
        }
        hospede.nome = nome
    }
    println("Operação realizada com sucesso")
}
