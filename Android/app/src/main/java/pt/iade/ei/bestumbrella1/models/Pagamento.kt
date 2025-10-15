package pt.iade.ei.bestumbrella1.models

data class Pagamento(
    val id: Int,
    val descricao: String,
    val valor: Double,
    val data: String
)