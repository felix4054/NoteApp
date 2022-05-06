package by.kavalchuk.aliaksandr.noteapp.data


//@Database(entities = [TodoItem::class], version = 1, exportShema = false)
//abstract class TodoDatabase: RoomDatabase() {
//
//    abstract fun todoDao(): TodoDatabaseDao
//
//    companion object {
//        private var instanceTodoDatabase: TodoDatabase? = null
//
//        fun getInstance(context: Context): TodoDatabase {
//            synchronized(this) {
//                var instance = instanceTodoDatabase
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicatioContext,
//                        TodoDatabase::class.java,
//                        "todo_list_database"
//                    ).fallbackToDestructiveMigration()
//                        .build()
//
//                    instanceTodoDatabase = instance
//                }
//
//                return instance
//            }
//        }
//    }
//}
//














