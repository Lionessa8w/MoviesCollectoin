package model

data class FilmsModel (
    private var id: Int? = null,
    private var localizedName: String? = null,
    private var name: String? = null,
    private var year: Int? = null,
    private var rating: Double? = null,
    private var imageUrl: String? = null,
    private var description: String? = null,
    private var genres: List<String> = arrayListOf()
)