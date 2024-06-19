package com.mbw.squarerepos.data.model.square

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mbw.squarerepos.data.dataconvertor.TopicsConverter

/**
 * Data class for hold  SquareRepos data.
 */
@Entity(tableName = "square_repos_table")
data class SquareReposEntity(
    @PrimaryKey val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val isPrivate: Boolean,
    @Embedded val owner: Owner,
    val html_url: String,
    val description: String?,
    val fork: Boolean,
    val url: String,
    val created_at: String,
    val updated_at: String,
    val pushed_at: String,
    val git_url: String,
    val ssh_url: String,
    val clone_url: String,
    val svn_url: String,
    val homepage: String?,
    val size: Int,
    val stargazers_count: Int=0,
    val watchers_count: Int,
    val language: String?,
    val has_issues: Boolean,
    val has_projects: Boolean,
    val has_downloads: Boolean,
    val has_wiki: Boolean,
    val has_pages: Boolean,
    val has_discussions: Boolean,
    val forks_count: Int,
    val mirror_url: String?,
    val archived: Boolean,
    val disabled: Boolean,
    val open_issues_count: Int,
    var is_bookmarked: Boolean,
    @Embedded
    val license: License?,
    val allow_forking: Boolean,
    val is_template: Boolean,
    val web_commit_signoff_required: Boolean,
    @TypeConverters(TopicsConverter::class)
    val topics: List<String>?,
    val visibility: String,
    val forks: Int,
    val open_issues: Int,
    val watchers: Int,
    val default_branch: String,
    @Embedded val permissions: Permissions
)

data class Owner(
    val login: String,
    @ColumnInfo(name = "owner_id")
    val id: Int,
    @ColumnInfo(name = "owner_node_id")
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String?,
    @ColumnInfo(name = "owner_url")
    val url: String,
    @ColumnInfo(name = "owner_html_url")
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean
)

data class Permissions(
    val admin: Boolean,
    val maintain: Boolean,
    val push: Boolean,
    val triage: Boolean,
    val pull: Boolean
)

data class License(

    val key: String?,
    @ColumnInfo("license_name")
    val name: String?,
    @ColumnInfo("license_nodeid")
    val nodeId: String?,

    val spdxId: String?,
    @ColumnInfo("license_url")
    val url: String?
)