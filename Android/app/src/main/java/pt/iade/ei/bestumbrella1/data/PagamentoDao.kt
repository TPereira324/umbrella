package pt.iade.ei.bestumbrella1.data

import androidx.room.*
import pt.iade.ei.bestumbrella1.models.Pagamento

@Dao
interface PagamentoDao {
    @Insert suspend fun insert(pagamento: Pagamento)
    @Query("SELECT * FROM Pagamento") suspend fun getAll(): List<Pagamento>
}