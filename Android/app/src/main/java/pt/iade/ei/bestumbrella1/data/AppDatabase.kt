package pt.iade.ei.bestumbrella1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.iade.ei.bestumbrella1.models.*

@Database(
    entities = [User::class, Pagamento::class, Estacao::class, Aluguer::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pagamentoDao(): PagamentoDao
    abstract fun estacaoDao(): EstacaoDao
    abstract fun aluguerDao(): AluguerDao
}