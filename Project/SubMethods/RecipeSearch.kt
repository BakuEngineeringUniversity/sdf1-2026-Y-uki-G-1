package cooking
// ─────────────────────────────────────────────
// RecipeSearch: Search Engine + User Collection
// ─────────────────────────────────────────────
class RecipeSearch {
    private val recipes: MutableList<Recipe> = mutableListOf()

    fun addRecipe(recipe: Recipe) = recipes.add(recipe)
    fun getRecipes(): List<Recipe> = recipes.toList()

    fun searchByIngredient(ingredient: String): List<Recipe> =
        recipes.filter { r -> r.ingredients.any { it.contains(ingredient, ignoreCase = true) } }

    fun searchByName(name: String): List<Recipe> =
        recipes.filter { r -> r.name.contains(name, ignoreCase = true) }

    fun searchByType(type: String): List<Recipe> =
        recipes.filter { r -> r.getRecipeType().contains(type, ignoreCase = true) }

    fun listAll() {
        if (recipes.isEmpty()) {
            println("📭 No recipes found.")
            return
        }
        println("\n📚 ALL RECIPES (${recipes.size} total)")
        println("═".repeat(50))
        recipes.forEachIndexed { i, r ->
            val cookingInfo = if (r is CookingMethod) " | ⏱️ ${r.getCookingTime().toInt()} min" else ""
            println("  ${i + 1}. [${r.getRecipeType()}] ${r.name}$cookingInfo")
        }
        println("═".repeat(50))
    }
}
