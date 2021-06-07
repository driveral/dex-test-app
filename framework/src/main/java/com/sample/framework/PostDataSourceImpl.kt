package com.sample.framework

import com.sample.data.PostDataSource
import com.sample.domain.Credentials
import com.sample.domain.Post
import com.sample.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class PostDataSourceImpl @Inject constructor() : PostDataSource {
    override suspend fun getLastestPosts(credentials: Credentials): Result<List<Post>> =
        withContext(Dispatchers.IO) {
            val list = mutableListOf<Post>()

            val post1 = Post(
                1,
                0,
                "Interesting",
                "https://via.placeholder.com/150/92c952",
                false
            )
            val post2 = Post(
                2,
                0,
                "Interesting",
                "https://via.placeholder.com/150/771796",
                false
            )
            val post3 = Post(
                3,
                0,
                "Interesting",
                "https://via.placeholder.com/150/d32776",
                false
            )

            for (i in 1..30) {
                list.add(post1)
                list.add(post2)
                list.add(post3)
            }


            // Fake server delay
            delay(Random.Default.nextInt(from = 1, until = 5) * 1000L)

            return@withContext Result.Success(list)
        }
}