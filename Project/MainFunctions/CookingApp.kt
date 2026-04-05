package cooking

fun printBanner() {
    println("""
╔═══════════════════════════════════════════════════╗
║                                                   ║
║    🍴  KOTLIN COOKING APP  🍴                     ║
║    Your Personal Recipe Manager                   ║
║                                                   ║
╚═══════════════════════════════════════════════════╝
""")
}

fun printMenu() {
    println("""
┌─────────────────────────────────────────┐
│  MAIN MENU                              │
│  1. List all recipes                    │
│  2. Search by name                      │
│  3. Search by ingredient                │
│  4. Search by type                      │
│  5. View recipe details                 │
│  6. Add a new recipe                    │
│  7. Rate & review a recipe              │
│  8. Get recommendations                 │
│  9. View search history                 │
│  0. Exit                                │
└─────────────────────────────────────────┘
Enter choice: """.trimIndent(), end = "")
}

fun String.Companion.print(s: String, end: String = "\n") = kotlin.io.print(s + end)

fun displaySearchResults(results: List<Recipe>, query: String) {
    if (results.isEmpty()) {
        println("🔍 No recipes found matching '$query'")
    } else {
        println("\n🔍 Found ${results.size} recipe(s) for '$query':")
        println("─".repeat(50))
        results.forEachIndexed { i, r ->
            val time = if (r is CookingMethod) " | ⏱️ ${r.getCookingTime().toInt()} min" else ""
            println("  ${i + 1}. ${r.getName()} [${r.getRecipeType()}]$time")
        }
    }
}

fun loadSampleData(engine: RecipeSearch) {
    // Vegetarian
    engine.addRecipe(VegetarianRecipe(
        name = "Caprese Salad",
        ingredients = listOf("fresh mozzarella", "tomatoes", "basil", "olive oil", "balsamic vinegar", "salt"),
        instructions = "Slice mozzarella and tomatoes. Arrange alternately on a plate. Add basil leaves between slices. Drizzle with olive oil and balsamic. Season with salt."
    ).also { it.addReview("Refreshing and simple!", 4.5) })

    engine.addRecipe(VegetarianRecipe(
        name = "Mushroom Risotto",
        ingredients = listOf("arborio rice", "mushrooms", "onion", "garlic", "white wine", "parmesan", "butter", "vegetable broth"),
        instructions = "Sauté onion and garlic. Toast rice 2 min. Add wine and stir. Gradually add warm broth ladle by ladle, stirring constantly. Fold in mushrooms and parmesan. Finish with butter."
    ).also {
        it.addReview("Creamy perfection!", 5.0)
        it.addReview("Takes practice but worth it", 4.0)
    })

    // Non-Vegetarian
    engine.addRecipe(NonVegetarianRecipe(
        name = "Grilled Chicken Tikka",
        ingredients = listOf("chicken breast", "yogurt", "tikka masala paste", "lemon juice", "garlic", "ginger", "coriander"),
        instructions = "Marinate chicken in yogurt, tikka paste, lemon juice, garlic and ginger for 2 hours. Grill on high heat 5-7 min per side until charred. Garnish with coriander and serve with naan."
    ).also { it.addReview("Better than restaurant!", 5.0) })

    engine.addRecipe(NonVegetarianRecipe(
        name = "Beef Tacos",
        ingredients = listOf("ground beef", "taco shells", "onion", "cumin", "chili powder", "lettuce", "tomato", "cheese", "sour cream"),
        instructions = "Brown beef with onion. Add cumin and chili powder. Simmer 10 min. Fill taco shells and top with lettuce, tomato, cheese and sour cream."
    ).also {
        it.addReview("Family favorite!", 4.5)
        it.addReview("Quick weeknight dinner", 4.0)
    })

    // Baking
    engine.addRecipe(BakingRecipe(
        name = "Classic Banana Bread",
        ingredients = listOf("ripe bananas", "flour", "sugar", "butter", "eggs", "baking soda", "vanilla extract", "walnuts"),
        instructions = "Mash bananas. Mix with melted butter, sugar, eggs, vanilla. Fold in flour and baking soda. Add walnuts. Pour into loaf pan and bake.",
        ovenTemp = 175f,
        bakeTime = 60f
    ).also { it.addReview("Moist and delicious!", 5.0) })

    engine.addRecipe(BakingRecipe(
        name = "Chocolate Chip Cookies",
        ingredients = listOf("flour", "butter", "sugar", "brown sugar", "eggs", "vanilla", "baking soda", "salt", "chocolate chips"),
        instructions = "Cream butter and sugars. Beat in eggs and vanilla. Mix in flour, baking soda, and salt. Fold in chocolate chips. Drop on baking sheet and bake.",
        ovenTemp = 190f,
        bakeTime = 12f
    ).also {
        it.addReview("Crispy edges, chewy center!", 5.0)
        it.addReview("Kids absolutely love these", 4.5)
    })

    // Italian
    engine.addRecipe(ItalianRecipe(
        name = "Spaghetti Carbonara",
        ingredients = listOf("spaghetti", "pancetta", "eggs", "parmesan", "black pepper", "salt"),
        instructions = "Cook pasta al dente. Fry pancetta until crispy. Mix eggs with parmesan. Toss hot pasta with pancetta off heat, add egg mixture quickly, toss vigorously. Season with black pepper.",
        region = "Rome"
    ).also { it.addReview("Authentic and heavenly", 5.0) })

    // Chinese
    engine.addRecipe(ChineseRecipe(
        name = "Kung Pao Chicken",
        ingredients = listOf("chicken thigh", "peanuts", "dried chilies", "soy sauce", "rice vinegar", "cornstarch", "garlic", "ginger", "scallions"),
        instructions = "Marinate chicken in soy sauce and cornstarch. Stir-fry chicken until golden. Add dried chilies, garlic, ginger. Add sauce mix. Toss with peanuts and scallions.",
        cookingStyle = "Stir-fry"
    ).also { it.addReview("Spicy and authentic!", 4.5) })
}

fun addUserRecipe(engine: RecipeSearch) {
    println("\n── ADD NEW RECIPE ──────────────────────────")
    print("Recipe name: ")
    val name = readLine()?.trim() ?: return

    print("Type (1=Vegetarian, 2=Non-Vegetarian, 3=Baking, 4=Italian, 5=Chinese): ")
    val typeChoice = readLine()?.trim()

    print("Ingredients (comma-separated): ")
    val ingredientInput = readLine()?.trim() ?: ""
    val ingredients = ingredientInput.split(",").map { it.trim() }.filter { it.isNotEmpty() }

    print("Instructions: ")
    val instructions = readLine()?.trim() ?: ""

    val recipe: Recipe = when (typeChoice) {
        "1" -> VegetarianRecipe(name, ingredients, instructions)
        "2" -> NonVegetarianRecipe(name, ingredients, instructions)
        "3" -> {
            print("Oven temperature (°C): ")
            val temp = readLine()?.toFloatOrNull() ?: 180f
            print("Bake time (minutes): ")
            val time = readLine()?.toFloatOrNull() ?: 30f
            BakingRecipe(name, ingredients, instructions, temp, time)
        }
        "4" -> {
            print("Region (e.g. Rome, Naples): ")
            val region = readLine()?.trim() ?: "Italy"
            ItalianRecipe(name, ingredients, instructions, region)
        }
        "5" -> {
            print("Cooking style (e.g. Stir-fry, Steamed): ")
            val style = readLine()?.trim() ?: "Stir-fry"
            ChineseRecipe(name, ingredients, instructions, style)
        }
        else -> VegetarianRecipe(name, ingredients, instructions)
    }

    engine.addRecipe(recipe)
}

fun rateRecipe(engine: RecipeSearch) {
    print("\nEnter recipe name to rate: ")
    val name = readLine()?.trim() ?: return
    val results = engine.searchByName(name)
    if (results.isEmpty()) {
        println("❌ Recipe not found.")
        return
    }
    val recipe = results.first()
    print("Your rating (1-5): ")
    val rating = readLine()?.toDoubleOrNull()
    if (rating == null || rating !in 1.0..5.0) {
        println("❌ Invalid rating. Must be 1-5.")
        return
    }
    print("Your review: ")
    val review = readLine()?.trim() ?: ""
    recipe.addReview(review, rating)
    println("✅ Review added! New average: ${"%.1f".format(recipe.getRating())}/5")
}

fun main() {
    printBanner()
    val engine = RecipeSearch()
    println("📥 Loading sample recipes...")
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
                println("\nTypes: Vegetarian / Non-Vegetarian / Baking / Italian / Chinese")
                print("Search type: ")
                val q = readLine()?.trim() ?: ""
                displaySearchResults(engine.searchByType(q), q)
            }

            "5" -> {
                print("\nEnter recipe name: ")
                val q = readLine()?.trim() ?: ""
                val results = engine.searchByName(q)
                if (results.isEmpty()) println("❌ Not found.")
                else results.forEach { println(it) }
            }

            "6" -> addUserRecipe(engine)

            "7" -> rateRecipe(engine)

            "8" -> {
                val recs = engine.getRecommendations()
                println("\n⭐ RECOMMENDED FOR YOU:")
                println("─".repeat(50))
                if (recs.isEmpty()) println("  No recommendations yet.")
                else recs.forEach { r ->
                    val time = if (r is CookingMethod) " | ⏱️ ${r.getCookingTime().toInt()} min" else ""
                    println("  • ${r.getName()} [${r.getRecipeType()}]$time | ★ ${"%.1f".format(r.getRating())}")
                }
            }

            "9" -> {
                val history = engine.getSearchHistory()
                println("\n🕘 SEARCH HISTORY (${history.size} searches):")
                if (history.isEmpty()) println("  No searches yet.")
                else history.forEachIndexed { i, h -> println("  ${i + 1}. $h") }
            }

            "0" -> {
                println("\n👋 Thanks for using Kotlin Cooking App! Happy cooking!")
                running = false
            }

            else -> println("❌ Invalid option. Please try again.")
        }
        println()
    }
}