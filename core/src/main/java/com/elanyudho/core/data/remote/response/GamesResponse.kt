package com.elanyudho.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("filters")
    val filters: Filters? = Filters(),
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("nofollow")
    val nofollow: Boolean? = false,
    @SerializedName("nofollow_collections")
    val nofollowCollections: List<String?>? = listOf(),
    @SerializedName("noindex")
    val noindex: Boolean? = false,
    @SerializedName("previous")
    val previous: Any? = Any(),
    @SerializedName("results")
    val results: List<Result?>? = listOf(),
    @SerializedName("seo_description")
    val seoDescription: String? = "",
    @SerializedName("seo_h1")
    val seoH1: String? = "",
    @SerializedName("seo_keywords")
    val seoKeywords: String? = "",
    @SerializedName("seo_title")
    val seoTitle: String? = ""
) {
    data class Filters(
        @SerializedName("years")
        val years: List<Year?>? = listOf()
    ) {
        data class Year(
            @SerializedName("count")
            val count: Int? = 0,
            @SerializedName("decade")
            val decade: Int? = 0,
            @SerializedName("filter")
            val filter: String? = "",
            @SerializedName("from")
            val from: Int? = 0,
            @SerializedName("nofollow")
            val nofollow: Boolean? = false,
            @SerializedName("to")
            val to: Int? = 0,
            @SerializedName("years")
            val years: List<Year?>? = listOf()
        ) {
            data class Year(
                @SerializedName("count")
                val count: Int? = 0,
                @SerializedName("nofollow")
                val nofollow: Boolean? = false,
                @SerializedName("year")
                val year: Int? = 0
            )
        }
    }

    data class Result(
        @SerializedName("added")
        val added: Int? = 0,
        @SerializedName("added_by_status")
        val addedByStatus: AddedByStatus? = AddedByStatus(),
        @SerializedName("background_image")
        val backgroundImage: String? = "",
        @SerializedName("clip")
        val clip: Any? = Any(),
        @SerializedName("dominant_color")
        val dominantColor: String? = "",
        @SerializedName("esrb_rating")
        val esrbRating: EsrbRating? = EsrbRating(),
        @SerializedName("genres")
        val genres: List<Genre?>? = listOf(),
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("metacritic")
        val metacritic: Int? = 0,
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("parent_platforms")
        val parentPlatforms: List<ParentPlatform?>? = listOf(),
        @SerializedName("platforms")
        val platforms: List<Platform?>? = listOf(),
        @SerializedName("playtime")
        val playtime: Int? = 0,
        @SerializedName("rating")
        val rating: Double? = 0.0,
        @SerializedName("rating_top")
        val ratingTop: Int? = 0,
        @SerializedName("ratings")
        val ratings: List<Rating?>? = listOf(),
        @SerializedName("ratings_count")
        val ratingsCount: Int? = 0,
        @SerializedName("released")
        val released: String? = "",
        @SerializedName("reviews_count")
        val reviewsCount: Int? = 0,
        @SerializedName("reviews_text_count")
        val reviewsTextCount: Int? = 0,
        @SerializedName("saturated_color")
        val saturatedColor: String? = "",
        @SerializedName("short_screenshots")
        val shortScreenshots: List<ShortScreenshot?>? = listOf(),
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
        @SerializedName("updated")
        val updated: String? = "",
        @SerializedName("user_game")
        val userGame: Any? = Any()
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
            @SerializedName("requirements_en")
            val requirementsEn: RequirementsEn? = RequirementsEn(),
            @SerializedName("requirements_ru")
            val requirementsRu: RequirementsRu? = RequirementsRu()
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
                val yearStart: Int? = 0
            )

            data class RequirementsEn(
                @SerializedName("minimum")
                val minimum: String? = "",
                @SerializedName("recommended")
                val recommended: String? = ""
            )

            data class RequirementsRu(
                @SerializedName("minimum")
                val minimum: String? = "",
                @SerializedName("recommended")
                val recommended: String? = ""
            )
        }

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

        data class ShortScreenshot(
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("image")
            val image: String? = ""
        )

        data class Store(
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("store")
            val store: Store? = Store()
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
}