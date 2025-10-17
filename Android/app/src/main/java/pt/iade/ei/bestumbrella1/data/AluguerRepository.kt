package pt.iade.ei.bestumbrella1.data

class AluguerRepository(private val dao: AluguerDao) {
    suspend fun guardar(aluguer: Aluguer) = dao.insert(aluguer)
    suspend fun listarTodos(): List<Aluguer> = dao.getAll()
}