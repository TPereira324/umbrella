package pt.iade.ei.bestumbrella1.data

import pt.iade.ei.bestumbrella1.models.Aluguer

class AluguerRepository(private val dao: AluguerDao) {
    suspend fun guardar(aluguer: Aluguer) = dao.insert(aluguer)
    suspend fun listarTodos(): List<Aluguer> = dao.getAll()
}