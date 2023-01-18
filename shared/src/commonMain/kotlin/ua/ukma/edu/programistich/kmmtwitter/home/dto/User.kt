package ua.ukma.edu.programistich.kmmtwitter.home.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("contributors_enabled")
    val contributorsEnabled: Boolean? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("default_profile")
    val defaultProfile: Boolean? = null,
    @SerialName("default_profile_image")
    val defaultProfileImage: Boolean? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("favourites_count")
    val favouritesCount: Int? = null,
    @SerialName("follow_request_sent")
    val followRequestSent: Boolean? = null,
    @SerialName("followers_count")
    val followersCount: Int? = null,
    @SerialName("following")
    val following: Boolean? = null,
    @SerialName("friends_count")
    val friendsCount: Int? = null,
    @SerialName("geo_enabled")
    val geoEnabled: Boolean? = null,
    @SerialName("has_extended_profile")
    val hasExtendedProfile: Boolean? = null,
    @SerialName("id")
    val id: Long? = null,
    @SerialName("id_str")
    val idStr: String? = null,
    @SerialName("is_translation_enabled")
    val isTranslationEnabled: Boolean? = null,
    @SerialName("is_translator")
    val isTranslator: Boolean? = null,
    @SerialName("listed_count")
    val listedCount: Int? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("notifications")
    val notifications: Boolean? = null,
    @SerialName("profile_background_color")
    val profileBackgroundColor: String? = null,
    @SerialName("profile_background_tile")
    val profileBackgroundTile: Boolean? = null,
    @SerialName("profile_banner_url")
    val profileBannerUrl: String? = null,
    @SerialName("profile_image_url")
    val profileImageUrl: String? = null,
    @SerialName("profile_image_url_https")
    val profileImageUrlHttps: String? = null,
    @SerialName("profile_link_color")
    val profileLinkColor: String? = null,
    @SerialName("profile_sidebar_border_color")
    val profileSidebarBorderColor: String? = null,
    @SerialName("profile_sidebar_fill_color")
    val profileSidebarFillColor: String? = null,
    @SerialName("profile_text_color")
    val profileTextColor: String? = null,
    @SerialName("profile_use_background_image")
    val profileUseBackgroundImage: Boolean? = null,
    @SerialName("screen_name")
    val screenName: String? = null,
    @SerialName("statuses_count")
    val statusesCount: Int? = null,
    @SerialName("translator_type")
    val translatorType: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("verified")
    val verified: Boolean? = null
)