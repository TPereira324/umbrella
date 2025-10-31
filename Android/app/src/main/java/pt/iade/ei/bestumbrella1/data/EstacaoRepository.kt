package pt.iade.ei.bestumbrella1.data

import pt.iade.ei.bestumbrella1.models.Estacao

class EstacaoRepository(private val dao: EstacaoDao) {
    suspend fun guardar(estacao: Estacao) = dao.insert(estacao)
    suspend fun listarTodas(): List<Estacao> = dao.getAll()
}