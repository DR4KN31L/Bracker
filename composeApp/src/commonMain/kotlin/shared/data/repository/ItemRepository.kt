package shared.data.repository

import kotlinx.coroutines.Dispatchers
import shared.data.localDb.BrackerDB

class ItemRepository(private val database: BrackerDB) {
    private val dispatcher = Dispatchers.IO

}