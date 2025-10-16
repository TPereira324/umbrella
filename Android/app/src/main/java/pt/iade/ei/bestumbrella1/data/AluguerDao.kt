package pt.iade.ei.bestumbrella1.data

import androidx.room.*
import pt.iade.ei.bestumbrella1.models.Aluguer

@Dao
interface AluguerDao {
    @Insert suspend fun insert(aluguer: Aluguer)
    @Query("SELECT * FROM Aluguer") suspend fun getAll(): List<Aluguer>
}