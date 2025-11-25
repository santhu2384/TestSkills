package com.example.data.mapper.repository

import com.example.data.mapper.dto.VideoDTO
import com.example.data.mapper.toDomain
import com.example.domain.model.ManifestType


import com.example.domain.model.Video
import repository.HomeRepository

class HomeRespoitoryImpl: HomeRepository
{
    override suspend fun getHomeSections():List<Pair<String,List<Video>>>
    {
        val hls = VideoDTO( id = "sintel-hls", title = "Sintel (HLS)",
            thumbnailurl = "https://bitdash-a.akamaihd.net/content/sintel/poster.png",
            isLive = false,
            manifestUrl = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8",
            manifestType = ManifestType.HLS).toDomain()

        val dash = VideoDTO(
            id = "sintel-dash",
            title = "Sintel (DASH)",
            thumbnailurl = "https://bitmovin-a.akamaihd.net/content/sintel/poster.png",
            isLive = false,
            manifestUrl = "https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd",
            manifestType = ManifestType.DASH).toDomain()

        val bigBuck = VideoDTO(
            id = "bbb-hls",
            title = "Big Buck Bunny (HLS)",
            thumbnailurl = "https://peach.blender.org/wp-content/uploads/title_anouncement.jpg?x11217",
            isLive = false,
            manifestUrl = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
            manifestType = ManifestType.HLS).toDomain()

        return listOf("Live Now" to listOf(hls.copy(isLive = true),dash.copy(isLive = true)),
            "Featured" to listOf(bigBuck,hls,dash),
            "Recommended" to listOf(dash,bigBuck))
    }

    override suspend fun findVideoById(id:String): Video?
    {
        return getHomeSections().flatMap { it.second }.firstOrNull{it.id == id}
    }




}