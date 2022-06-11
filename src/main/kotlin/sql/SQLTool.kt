package sql

import org.jetbrains.annotations.NotNull
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

class SQLTool(private val host: String, private val database: String, private val user: String, private val password: String, private val port: Int) {

    var connection: Connection = TODO()
    private val properties: Properties = Properties()

    init {
        properties.setProperty("host", host)
        properties.setProperty("database", database)
        properties.setProperty("user", user)
        properties.setProperty("password", password)
        properties.setProperty("port", port.toString())
    }

    fun getProperties(): Properties {
        return properties
    }

    fun connect() {
        connection = DriverManager.getConnection("jdbc:mysql://$host:$port/$database?autoReconnect=true", user, password)
        println("Connected to $host:$port/$database")
    }

    fun disconnect() {
        connection.close()
        println("Disconnected from $host:$port/$database")
    }

    @JvmName("getSQLConnection")
    fun getConnection(): Connection {
        return connection
    }

    @NotNull
    fun update(qry: String) {
        val stmt: Statement = connection.createStatement()
        stmt.executeUpdate(qry)
        stmt.close()
    }

    @NotNull
    fun query(qry: String): ResultSet {
        val stmt: Statement = connection.createStatement()
        return stmt.executeQuery(qry)
    }

    @NotNull
    fun updateQuery(qry: String): Int {
        val stmt: Statement = connection.createStatement()
        stmt.executeUpdate(qry)
        return 0
    }
}