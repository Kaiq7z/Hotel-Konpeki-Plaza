import java.text.NumberFormat
import java.util.Locale

class EntradaEncerrada : RuntimeException()

fun erro() {
    println("Opção inválida. Tente novamente.")
}

fun texto(mensagem: String): String {
    print(mensagem)
    val resposta = readlnOrNull()
    if (resposta == null) {
        throw EntradaEncerrada()
    }
    return resposta.trim()
}

fun nomeObrigatorio(mensagem: String): String {
    while (true) {
        val nome = texto(mensagem)
        if (nome.isNotEmpty()) return nome
        println("Informe um nome.")
    }
}

fun inteiro(mensagem: String, minimo: Int, maximo: Int): Int {
    while (true) {
        val numero = texto(mensagem).toIntOrNull()
        if (numero != null && numero in minimo..maximo) return numero
        println("Informe um número inteiro entre $minimo e $maximo.")
    }
}

fun decimal(mensagem: String, minimo: Double, maximo: Double, aceitaMinimo: Boolean = true): Double {
    while (true) {
        val numero = texto(mensagem).replace(',', '.').toDoubleOrNull()
        if (numero != null && numero.isFinite() && numero <= maximo &&
            (numero > minimo || (aceitaMinimo && numero == minimo))) return numero
        println("Valor inválido.")
    }
}

fun confirmar(mensagem: String): Boolean {
    while (true) {
        when (texto(mensagem).uppercase()) {
            "S" -> return true
            "N" -> return false
            else -> erro()
        }
    }
}

fun dinheiro(valor: Double): String {
    return NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(valor)
}

fun numero(valor: Double): String {
    return String.format(Locale.forLanguageTag("pt-BR"), "%.2f", valor)
}
