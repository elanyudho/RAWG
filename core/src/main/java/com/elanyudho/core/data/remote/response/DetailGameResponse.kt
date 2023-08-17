package com.elanyudho.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class DetailGameResponse(
    @SerializedName("achievements_count")
    val achievementsCount: Int? = 0,
    @SerializedName("added")
    val added: Int? = 0,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus? = AddedByStatus(),
    @SerializedName("additions_count")
    val additionsCount: Int? = 0,
    @SerializedName("alternative_names")
    val alternativeNames: List<Any?>? = listOf(),
    @SerializedName("background_image")
    val backgroundImage: String? = "",
    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String? = "",
    @SerializedName("clip")
    val clip: Any? = Any(),
    @SerializedName("creators_count")
    val creatorsCount: Int? = 0,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("description_raw")
    val descriptionRaw: String? = "",
    @SerializedName("developers")
    val developers: List<Developer?>? = listOf(),
    @SerializedName("dominant_color")
    val dominantColor: String? = "",
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating? = EsrbRating(),
    @SerializedName("game_series_count")
    val gameSeriesCount: Int? = 0,
    @SerializedName("genres")
    val genres: List<Genre?>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("metacritic")
    val metacritic: Int? = 0,
    @SerializedName("metacritic_platforms")
    val metacriticPlatforms: List<Any?>? = listOf(),
    @SerializedName("metacritic_url")
    val metacriticUrl: String? = "",
    @SerializedName("movies_count")
    val moviesCount: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("name_original")
    val nameOriginal: String? = "",
    @SerializedName("parent_achievements_count")
    val parentAchievementsCount: Int? = 0,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform?>? = listOf(),
    @SerializedName("parents_count")
    val parentsCount: Int? = 0,
    @SerializedName("platforms")
    val platforms: List<Platform?>? = listOf(),
    @SerializedName("playtime")
    val playtime: Int? = 0,
    @SerializedName("publishers")
    val publishers: List<Publisher?>? = listOf(),
    @SerializedName("rating")
    val rating: Double? = 0.0,
    @SerializedName("rating_top")
    val ratingTop: Int? = 0,
    @SerializedName("ratings")
    val ratings: List<Rating?>? = listOf(),
    @SerializedName("ratings_count")
    val ratingsCount: Int? = 0,
    @SerializedName("reactions")
    val reactions: Reactions? = Reactions(),
    @SerializedName("reddit_count")
    val redditCount: Int? = 0,
    @SerializedName("reddit_description")
    val redditDescription: String? = "",
    @SerializedName("reddit_logo")
    val redditLogo: String? = "",
    @SerializedName("reddit_name")
    val redditName: String? = "",
    @SerializedName("reddit_url")
    val redditUrl: String? = "",
    @SerializedName("released")
    val released: String? = "",
    @SerializedName("reviews_count")
    val reviewsCount: Int? = 0,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int? = 0,
    @SerializedName("saturated_color")
    val saturatedColor: String? = "",
    @SerializedName("screenshots_count")
    val screenshotsCount: Int? = 0,
    @SerializedName("slug")
    val slug: String? = "",
    @SerializedName("stores")
    val stores: List<Store?>? = listOf(),
    @SerializedName("suggestions_count")
    val suggestionsCount: Int? = 0,
    @SerializedName("tags")
    val tags: List<Tag?>? = listOf(),
    @SerializedName("tba")
    val tba: Boolean? = false,
    @SerializedName("twitch_count")
    val twitchCount: Int? = 0,
    @SerializedName("updated")
    val updated: String? = "",
    @SerializedName("user_game")
    val userGame: Any? = Any(),
    @SerializedName("website")
    val website: String? = "",
    @SerializedName("youtube_count")
    val youtubeCount: Int? = 0
) {
    data class AddedByStatus(
        @SerializedName("beaten")
        val beaten: Int? = 0,
        @SerializedName("dropped")
        val dropped: Int? = 0,
        @SerializedName("owned")
        val owned: Int? = 0,
        @SerializedName("playing")
        val playing: Int? = 0,
        @SerializedName("toplay")
        val toplay: Int? = 0,
        @SerializedName("yet")
        val yet: Int? = 0
    )

    data class Developer(
        @SerializedName("games_count")
        val gamesCount: Int? = 0,
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("image_background")
        val imageBackground: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("slug")
        val slug: String? = ""
    )

    data class EsrbRating(
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("slug")
        val slug: String? = ""
    )

    data class Genre(
        @SerializedName("games_count")
        val gamesCount: Int? = 0,
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("image_background")
        val imageBackground: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("slug")
        val slug: String? = ""
    )

    data class ParentPlatform(
        @SerializedName("platform")
        val platform: Platform? = Platform()
    ) {
        data class Platform(
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("slug")
            val slug: String? = ""
        )
    }

    data class Platform(
        @SerializedName("platform")
        val platform: Platform? = Platform(),
        @SerializedName("released_at")
        val releasedAt: String? = "",
        @SerializedName("requirements")
        val requirements: Requirements? = Requirements()
    ) {
        data class Platform(
            @SerializedName("games_count")
            val gamesCount: Int? = 0,
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("image")
            val image: Any? = Any(),
            @SerializedName("image_background")
            val imageBackground: String? = "",
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("slug")
            val slug: String? = "",
            @SerializedName("year_end")
            val yearEnd: Any? = Any(),
            @SerializedName("year_start")
            val yearStart: Any? = Any()
        )

        data class Requirements(
            @SerializedName("minimum")
            val minimum: String? = "",
            @SerializedName("recommended")
            val recommended: String? = ""
        )
    }

    data class Publisher(
        @SerializedName("games_count")
        val gamesCount: Int? = 0,
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("image_background")
        val imageBackground: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("slug")
        val slug: String? = ""
    )

    data class Rating(
        @SerializedName("count")
        val count: Int? = 0,
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("percent")
        val percent: Double? = 0.0,
        @SerializedName("title")
        val title: String? = ""
    )

    data class Reactions(
        @SerializedName("1")
        val x1: Int? = 0,
        @SerializedName("11")
        val x11: Int? = 0,
        @SerializedName("12")
        val x12: Int? = 0,
        @SerializedName("16")
        val x16: Int? = 0,
        @SerializedName("20")
        val x20: Int? = 0,
        @SerializedName("3")
        val x3: Int? = 0,
        @SerializedName("4")
        val x4: Int? = 0,
        @SerializedName("5")
        val x5: Int? = 0,
        @SerializedName("6")
        val x6: Int? = 0,
        @SerializedName("7")
        val x7: Int? = 0,
        @SerializedName("8")
        val x8: Int? = 0,
        @SerializedName("9")
        val x9: Int? = 0
    )

    data class Store(
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("store")
        val store: Store? = Store(),
        @SerializedName("url")
        val url: String? = ""
    ) {
        data class Store(
            @SerializedName("domain")
            val domain: String? = "",
            @SerializedName("games_count")
            val gamesCount: Int? = 0,
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("image_background")
            val imageBackground: String? = "",
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("slug")
            val slug: String? = ""
        )
    }

    data class Tag(
        @SerializedName("games_count")
        val gamesCount: Int? = 0,
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("image_background")
        val imageBackground: String? = "",
        @SerializedName("language")
        val language: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("slug")
        val slug: String? = ""
    )
}