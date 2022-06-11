package sql

import org.jetbrains.annotations.NotNull
import java.sql.ResultSet

class SQLActions(private var sqlTool: SQLTool) {

    @NotNull
    fun selectAll(table: String): ResultSet {
        return sqlTool.query("SELECT * FROM $table")
    }

    @NotNull
    fun selectAllWhere(table: String, where: String, arg: String): ResultSet {
        return sqlTool.query("SELECT * FROM $table WHERE $where= '$arg'")
    }

    @NotNull
    fun getLineByID(table: String, id: Int): ResultSet {
        return sqlTool.query("SELECT * FROM $table WHERE id = $id")
    }

    @NotNull
    fun selectAllValuesFromColumnName(table: String, columnName: String): ArrayList<String> {
        val rs: ResultSet = sqlTool.query("SELECT $columnName FROM $table")
        val list: ArrayList<String> = ArrayList()
        while (rs.next()) {
            list.add(rs.getString(columnName))
        }
        return list
    }
}