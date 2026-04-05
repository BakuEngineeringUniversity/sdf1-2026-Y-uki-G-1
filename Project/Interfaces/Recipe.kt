package cooking
 
// ─────────────────────────────────────────────
// Interface: CookingMethod
// ─────────────────────────────────────────────
interface CookingMethod {
    fun getCookingTime(): Double
}
 
// ─────────────────────────────────────────────
// Abstract Class: Recipe
// ─────────────────────────────────────────────
abstract class Recipe(
    name: String,
    ingredients: List<String>,
    instructions: String
) {
    // Encapsulated properties with getters/setters
    private var _name: String = name
    private var _ingredients: List<String> = ingredients
    private var _instructions: String = instructions
    private var _rating: Double = 0.0
    private var _reviewCount: Int = 0
    private val _reviews: MutableList<String> = mutableListOf()
 
    fun getName(): String = _name
    fun setName(value: String) { _name = value }
 
    fun getIngredients(): List<String> = _ingredients.toList()
    fun setIngredients(value: List<String>) { _ingredients = value }
 
    fun getInstructions(): String = _instructions
    fun setInstructions(value: String) { _instructions = value }
 
    fun getRating(): Double = _rating
    fun getReviewCount(): Int = _reviewCount
    fun getReviews(): List<String> = _reviews.toList()
 
    fun addReview(review: String, rating: Double) {
        require(rating in 1.0..5.0) { "Rating must be between 1 and 5" }
        _reviews.add(review)
        _rating = ((_rating * _reviewCount) + rating) / (_reviewCount + 1)
        _reviewCount++
    }
 
    abstract fun getRecipeType(): String
 
    override fun toString(): String {
        val stars = "★".repeat(_rating.toInt()) + "☆".repeat(5 - _rating.toInt())
        return """
╔══════════════════════════════════════════════╗
  [${getRecipeType()}] ${_name.uppercase()}
  Rating: $stars (${"%.1f".format(_rating)}/5, $_reviewCount reviews)
══════════════════════════════════════════════
  🧂 Ingredients:
${_ingredients.joinToString("\n") { "     • $it" }}
 
  📋 Instructions:
     ${_instructions}
╚══════════════════════════════════════════════╝""".trimIndent()
    }
}