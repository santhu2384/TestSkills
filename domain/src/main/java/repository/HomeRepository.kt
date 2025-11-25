package repository

import com.example.domain.model.Video

interface HomeRepository
{
    suspend fun getHomeSections():List<Pair<String,List<Video>>>
    suspend fun findVideoById(id:String): Video?
}