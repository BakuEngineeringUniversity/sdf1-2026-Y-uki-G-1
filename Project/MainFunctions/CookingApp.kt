package cooking

fun printMenu() {
    println("""

      MAIN MENU    
  1. List all recipes
  2. Search by name
  3. Search by ingredient
  4. Search by type
  5. View recipe details
  0. Exit 

Enter choice: """.trimIndent())
}

fun displaySearchResults(results: List<Recipe>, query: String) {
    if (results.isEmpty()) {
        println("🔍 No recipes found matching '$query'")
    } else {
        println("\nFound ${results.size} recipe(s) for '$query':")
        println("─".repeat(50))
        results.forEachIndexed { i, r ->
            val time = if (r is CookingMethod) " ${r.getCookingTime().toInt()} min" else ""
            println("  ${i + 1}. ${r.name} [${r.getRecipeType()}]$time")
        }
    }
}

// ─────────────────────────────────────────────
// Sample  Data
// ─────────────────────────────────────────────
fun loadSampleData(engine: RecipeSearch) {
    engine.addRecipe(VegetarianRecipe(
        "Caprese Salad",
        listOf("fresh mozzarella", "tomatoes", "basil", "olive oil", "balsamic vinegar", "salt"),
        "Slice mozzarella and tomatoes. Arrange alternately. Add basil. Drizzle oil and balsamic. Season."
    ))

    engine.addRecipe(NonVegetarianRecipe(
        "Beef Tacos",
        listOf("ground beef", "taco shells", "onion", "cumin", "chili powder", "lettuce", "tomato", "cheese", "sour cream"),
        "Brown beef with onion. Add spices. Fill taco shells. Top with lettuce, tomato, cheese, sour cream."
    ))

    engine.addRecipe(BakingRecipe(
        "Chocolate Chip Cookies",
        listOf("flour", "butter", "sugar", "brown sugar", "eggs", "vanilla", "baking soda", "salt", "chocolate chips"),
        "Cream butter and sugars. Beat in eggs and vanilla. Mix dry ingredients. Fold in chips. Bake.",
        ovenTemp = 190f,
        bakeTime = 12f
    ))
}

fun main() {
    val engine = RecipeSearch()
    println("Loading sample recipes...")
    loadSampleData(engine)
    println()

    var running = true
    while (running) {
        printMenu()
        when (readLine()?.trim()) {
            "1" -> engine.listAll()
            "2" -> {
                print("\nSearch name: ")
                val q = readLine()?.trim() ?: ""
                displaySearchResults(engine.searchByName(q), q)
            }
            "3" -> {
                print("\nSearch ingredient: ")
                val q = readLine()?.trim() ?: ""
                displaySearchResults(engine.searchByIngredient(q), q)
            }
            "4" -> {
                print("\nSearch type: ")
                val q = readLine()?.trim() ?: ""
                displaySearchResults(engine.searchByType(q), q)
            }
            "5" -> {
                print("\nEnter recipe name: ")
                val q = readLine()?.trim() ?: ""
                val results = engine.searchByName(q)
                if (results.isEmpty()) println("Not found.") else results.forEach { println(it) }
            }
            "0" -> {
                println("\nThanks for using Kotlin Cooking App! Happy cooking!")
                running = false
            }
            else -> println("Invalid option. Please try again.")
        }
        println()
    }
}
