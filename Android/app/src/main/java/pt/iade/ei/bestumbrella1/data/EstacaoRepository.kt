package pt.iade.ei.bestumbrella1.data

class EstacaoRepository(private val dao: EstacaoDao) {
    suspend fun guardar(estacao: Estacao) = dao.insert(estacao)
    suspend fun listarTodas(): List<Estacao> = dao.getAll()
}