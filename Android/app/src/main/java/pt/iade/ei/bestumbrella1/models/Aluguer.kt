package pt.iade.ei.bestumbrella1.models

data class Aluguer(
    val id: Int,
    val codigoGuardaChuva: String,
    val estacao: String,
    val duracaoEstimada: String,
    val preco: Double
)