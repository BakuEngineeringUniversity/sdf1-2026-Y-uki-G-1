package cooking
// ─────────────────────────────────────────────
// Concrete Subclass: VegetarianRecipe
// ─────────────────────────────────────────────
class VegetarianRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String
) : Recipe(name, ingredients, instructions) {
    override fun getRecipeType(): String = "🥦 Vegetarian"
}

// ─────────────────────────────────────────────
// Concrete Subclass: NonVegetarianRecipe
// ─────────────────────────────────────────────
class NonVegetarianRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String
) : Recipe(name, ingredients, instructions) {
    override fun getRecipeType(): String = "🥩 Non-Vegetarian"
}

// ─────────────────────────────────────────────
// Concrete Subclass: BakingRecipe
// Includes oven temperature and bake time.
// ─────────────────────────────────────────────
class BakingRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String,
    val ovenTemp: Float,
    val bakeTime: Float
) : Recipe(name, ingredients, instructions), CookingMethod {

    override fun getCookingTime(): Double = bakeTime.toDouble()
    override fun getRecipeType(): String = "🍰 Baking"

    override fun toString(): String {
        return super.toString() +
            "\n  🌡️  Oven: ${ovenTemp}°C | ⏱️ Bake Time: ${bakeTime} min"
    }
}

