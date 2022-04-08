package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.AccessControlContext

val DATABASE_NAME ="DB"
val Table_name = "Users"
val col_garbenili = "garbenili"
val col_gacuruli = "gacuruli"
val col_kaloria = "kaloria"
val col_id = "id"
class DB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + Table_name +"("+
                col_id +"INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_garbenili+"INTEGER,"+ col_gacuruli+
                "INTEGER,"+ col_kaloria+"INTEGER)";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun insertData(user: user){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(col_garbenili,user.garbenili)
        cv.put(col_gacuruli,user.gacuruli)
        cv.put(col_kaloria,user.kaloria)
        var result = db.insert(Table_name,null,cv)
    }

    fun readDate(): MutableList<user>{
        var list : MutableList<user> = ArrayList()
        val db = this.writableDatabase
        val query = "Selext * from" + Table_name
        val result = db.rawQuery(query,null)
        if (result.moveToFirst(
                do {
                    var user = user()
                    user.id = result.getString(result.getColumnIndex(col_id)).toInt()
                    user.garbenili = result.getString(result.getColumnIndex(col_garbenili).toInt()
                    user.gacuruli = result.getString(result.getColumnIndex(col_gacuruli)).toInt()
                    user.kaloria= result.getString(result.getColumnIndex(col_kaloria)).toInt()
                }while (result.moveToNext())
        ))

        result.close()
        db.close()
        return list
    }

}

