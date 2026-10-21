data class Reserva(val hospede: String, val quarto: Int, val diarias: Int, val total: Double)
data class Hospede(var nome: String, val cadastro: String)
data class Evento(val empresa: String, val auditorio: String, val dia: String,
                  val inicio: Int, val duracao: Int, val total: Double)
data class Orcamento(val empresa: String, val total: Double)
data class Combustivel(val posto: String, val tipo: String, val preco: Double)

class DadosHotel {
    val quartos = BooleanArray(20)
    val reservas = mutableListOf<Reserva>()
    val hospedes = mutableListOf<Hospede>()
    val eventos = mutableListOf<Evento>()
}
