package cooking

// ─────────────────────────────────────────────
// RecipeSearch: Search Engine + User Collection
// ─────────────────────────────────────────────
class RecipeSearch {
    private val _recipes: MutableList<Recipe> = mutableListOf()
    private val _searchHistory: MutableList<String> = mutableListOf()

    fun getRecipes(): List<Recipe> = _recipes.toList()

    // Add a recipe to the engine
    fun addRecipe(recipe: Recipe) {
        _recipes.add(recipe)
        println("✅ Recipe '${recipe.getName()}' added successfully!")
    }

    // Remove a recipe by name
    fun removeRecipe(name: String): Boolean {
        val recipe = _recipes.find { it.getName().equals(name, ignoreCase = true) }
        return if (recipe != null) {
            _recipes.remove(recipe)
            println("🗑️  Recipe '${recipe.getName()}' removed.")
            true
        } else {
            println("❌ Recipe '$name' not found.")
            false
        }
    }

    // Search by ingredient
    fun searchByIngredient(ingredient: String): List<Recipe> {
        _searchHistory.add("ingredient:$ingredient")
        return _recipes.filter { recipe ->
            recipe.getIngredients().any { it.contains(ingredient, ignoreCase = true) }
        }
    }

    // Search by name
    fun searchByName(name: String): List<Recipe> {
        _searchHistory.add("name:$name")
        return _recipes.filter { recipe ->
            recipe.getName().contains(name, ignoreCase = true)
        }
    }

    // Search by recipe type
    fun searchByType(type: String): List<Recipe> {
        return _recipes.filter { recipe ->
            recipe.getRecipeType().contains(type, ignoreCase = true)
        }
    }

    // Bonus: Recommend based on search history + ratings
    fun getRecommendations(): List<Recipe> {
        if (_searchHistory.isEmpty()) {
            return _recipes.sortedByDescending { it.getRating() }.take(3)
        }
        // Extract keywords from history
        val keywords = _searchHistory
            .flatMap { it.removePrefix("ingredient:").removePrefix("name:").split(" ") }
            .filter { it.length > 2 }
            .distinct()

        val scored = _recipes.map { recipe ->
            val nameScore = keywords.count { recipe.getName().contains(it, ignoreCase = true) }
            val ingredientScore = keywords.count { kw ->
                recipe.getIngredients().any { it.contains(kw, ignoreCase = true) }
            }
            val ratingBonus = recipe.getRating()
            recipe to (nameScore + ingredientScore + ratingBonus)
        }
        return scored.sortedByDescending { it.second }.map { it.first }.take(3)
    }

    fun getSearchHistory(): List<String> = _searchHistory.toList()

    fun clearSearchHistory() {
        _searchHistory.clear()
        println("🧹 Search history cleared.")
    }

    // Display all recipes summary
    fun listAll() {
        if (_recipes.isEmpty()) {
            println("📭 No recipes found.")
            return
        }
        println("\n📚 ALL RECIPES (${_recipes.size} total)")
        println("═".repeat(50))
        _recipes.forEachIndexed { i, recipe ->
            val cookingInfo = if (recipe is CookingMethod)
                " | ⏱️ ${recipe.getCookingTime().toInt()} min" else ""
            println("  ${i + 1}. [${recipe.getRecipeType()}] ${recipe.getName()}$cookingInfo")
        }
        println("═".repeat(50))
    }
}