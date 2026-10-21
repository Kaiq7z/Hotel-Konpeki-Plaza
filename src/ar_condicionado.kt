fun calcularOrcamento(valor: Double, quantidade: Int, percentual: Double, minimo: Int, deslocamento: Double): Double {
    val bruto = valor * quantidade
    var desconto = 0.0
    if (quantidade >= minimo) {
        desconto = bruto * percentual / 100
    }
    return bruto - desconto + deslocamento
}

fun mostrarOrcamento(orcamento: Orcamento) {
    println("O serviço de ${orcamento.empresa} custará ${dinheiro(orcamento.total)}")
}

fun arCondicionado(nome: String) {
    println("\n[Ar-Condicionado]")
    val orcamentos = mutableListOf<Orcamento>()
    while (true) {
        val empresa = nomeObrigatorio("Empresa: ")
        val valor = decimal("Valor por aparelho: ", 0.0, Double.MAX_VALUE)
        val quantidade = inteiro("Quantidade de aparelhos: ", 1, Int.MAX_VALUE)
        val percentual = decimal("Desconto (%): ", 0.0, 100.0)
        val minimo = inteiro("Quantidade mínima para desconto: ", 0, Int.MAX_VALUE)
        val deslocamento = decimal("Deslocamento: ", 0.0, Double.MAX_VALUE)
        val total = calcularOrcamento(valor, quantidade, percentual, minimo, deslocamento)
        if (!total.isFinite()) {
            println("Valor inválido. Informe novamente os dados da empresa.")
            continue
        }
        val orcamento = Orcamento(empresa, total)
        orcamentos.add(orcamento)
        mostrarOrcamento(orcamento)
        if (orcamentos.size < 2) {
            println("Informe pelo menos duas empresas para comparar.")
            continue
        }
        if (!confirmar("Deseja informar novos dados, $nome? (S/N): ")) break
    }
    var melhor = orcamentos[0]
    var pior = orcamentos[0]
    for (orcamento in orcamentos) {
        if (orcamento.total < melhor.total) {
            melhor = orcamento
        }
        if (orcamento.total > pior.total) {
            pior = orcamento
        }
    }
    println("O orçamento de menor valor é o de ${melhor.empresa} por ${dinheiro(melhor.total)}")
    println("Maior orçamento: ${pior.empresa} por ${dinheiro(pior.total)}")
    if (melhor.total == 0.0 && pior.total > 0.0) {
        println("Diferença percentual: não calculável, pois o menor orçamento é zero.")
    } else {
        val diferenca = if (melhor.total == 0.0) 0.0 else (pior.total - melhor.total) / melhor.total * 100
        println("Diferença percentual em relação ao menor: ${numero(diferenca)}%")
    }
}
