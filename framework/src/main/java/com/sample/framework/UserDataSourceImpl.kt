package com.sample.framework

import androidx.room.*
import com.sample.data.UserDataSource
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity
import com.sample.domain.Result
import kotlinx.coroutines.delay
import java.security.MessageDigest
import javax.inject.Inject
import kotlin.random.Random

class UserDataSourceImpl @Inject constructor(private val userDatabase: UserDatabase) :
    UserDataSource {
    override suspend fun login(userName: String, password: String): Result<Credentials> {
        // Fake server delay
        delay(Random.nextInt(from = 1, until = 5) * 1000L)

        if (userName == "admin" && password == "admin") {
            return Result.Success(Credentials("admin", "token"))
        }

        val userDao = userDatabase.userDao()
        val user = userDao.findUser(userName)
        return if (user != null) {
            if (password.sha512() == user.passwordHash) {
                Result.Success(Credentials(user.userName, "token"))
            } else {
                Result.Failure(ErrorEntity.WRONG_CREDENTIALS)
            }
        } else {
            Result.Failure(ErrorEntity.WRONG_CREDENTIALS)
        }
    }

    override suspend fun logout() {
        // Notify the server or something
    }

    override suspend fun register(userName: String, password: String): Result<Unit> {
        val userDao = userDatabase.userDao()
        val storedUser = userDao.findUser(userName)

        // Fake server delay
        delay(250)

        // Check that the username is not in use.
        return if (storedUser == null) {
            val user = User(userName, password.sha512())
            userDao.insert(user)
            Result.Success(Unit)
        } else {
            Result.Failure(ErrorEntity.USERNAME_IN_USE)
        }
    }

    override suspend fun areCredentialsValid(credentials: Credentials): Boolean {
        return true
    }
}

@Entity
data class User(
    @PrimaryKey val userName: String,
    @ColumnInfo val passwordHash: String
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE userName LIKE :userName LIMIT 1")
    suspend fun findUser(userName: String): User?

    @Insert
    suspend fun insert(user: User)
}

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

fun String.sha512(): String {
    return hashString(this, "SHA-512")
}

private fun hashString(input: String, algorithm: String): String {
    return MessageDigest
        .getInstance(algorithm)
        .digest(input.toByteArray())
        .fold("") { str, it -> str + "%02x".format(it) }
}