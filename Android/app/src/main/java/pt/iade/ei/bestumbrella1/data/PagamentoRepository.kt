package pt.iade.ei.bestumbrella1.data

class PagamentoRepository(private val dao: PagamentoDao) {
    suspend fun guardar(pagamento: Pagamento) = dao.insert(pagamento)
    suspend fun listarTodos(): List<Pagamento> = dao.getAll()
}