package cooking

// ─────────────────────────────────────────────
// Interface: CookingMethod
// Represents recipes that have a measurable cooking time.
// ─────────────────────────────────────────────
interface CookingMethod {
    fun getCookingTime(): Double
}

// ─────────────────────────────────────────────
// Abstract Class: Recipe
// Base class for all recipes, enforcing a recipe type.
// ─────────────────────────────────────────────
abstract class Recipe(
    val name: String,
    val ingredients: List<String>,
    val instructions: String
) {
    abstract fun getRecipeType(): String

    override fun toString(): String {
        return """
╔══════════════════════════════════════════════╗
  $name - ${getRecipeType()}
══════════════════════════════════════════════
  🧂 Ingredients:
${ingredients.joinToString("\n") { "     • $it" }}

  📋 Instructions:
     $instructions
╚══════════════════════════════════════════════╝
""".trimIndent()
    }
}

