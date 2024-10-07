package com.example.vridapp.mvvm.model.dataresponse

data class Links(
    val about: List<About>,
    val author: List<Author>,
    val collection: List<Collection>,
    val curies: List<Cury>,
    val predecessor_version: List<PredecessorVersion>,
    val replies: List<Reply>,
    val self: List<Self>,
    val version_history: List<VersionHistory>,
    val wp: List<WpAttachment>,
    val wp1:List<WpFeaturedmedia>,
    val wp2:List<WpTerm>
)