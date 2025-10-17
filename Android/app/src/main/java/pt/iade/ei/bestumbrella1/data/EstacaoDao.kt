package pt.iade.ei.bestumbrella1.data

import androidx.room.*

@Dao
interface EstacaoDao {
    @Insert suspend fun insert(estacao: Estacao)
    @Query("SELECT * FROM Estacao") suspend fun getAll(): List<Estacao>
}