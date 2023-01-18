package ua.ukma.edu.programistich.kmmtwitter.home.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("favorite_count")
    val favoriteCount: Int? = null,
    @SerialName("favorited")
    val favorited: Boolean? = null,
    @SerialName("id")
    val id: Long? = null,
    @SerialName("id_str")
    val idStr: String? = null,
    @SerialName("in_reply_to_screen_name")
    val inReplyToScreenName: String? = null,
    @SerialName("in_reply_to_status_id")
    val inReplyToStatusId: Long? = null,
    @SerialName("in_reply_to_status_id_str")
    val inReplyToStatusIdStr: String? = null,
    @SerialName("in_reply_to_user_id")
    val inReplyToUserId: Long? = null,
    @SerialName("in_reply_to_user_id_str")
    val inReplyToUserIdStr: String? = null,
    @SerialName("is_quote_status")
    val isQuoteStatus: Boolean? = null,
    @SerialName("lang")
    val lang: String? = null,
    @SerialName("retweet_count")
    val retweetCount: Int? = null,
    @SerialName("retweeted")
    val retweeted: Boolean? = null,
    @SerialName("source")
    val source: String? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("truncated")
    val truncated: Boolean? = null,
    @SerialName("user")
    val user: User? = null
)