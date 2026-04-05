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
// Inherits Recipe + implements CookingMethod
// ─────────────────────────────────────────────
class BakingRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String,
    ovenTemp: Float,
    bakeTime: Float
) : Recipe(name, ingredients, instructions), CookingMethod {

    private var _ovenTemp: Float = ovenTemp
    private var _bakeTime: Float = bakeTime

    fun getOvenTemp(): Float = _ovenTemp
    fun setOvenTemp(value: Float) { _ovenTemp = value }

    fun getBakeTime(): Float = _bakeTime
    fun setBakeTime(value: Float) { _bakeTime = value }

    // Polymorphic implementation of CookingMethod
    override fun getCookingTime(): Double = _bakeTime.toDouble()

    override fun getRecipeType(): String = "🍰 Baking"

    override fun toString(): String {
        return super.toString() + "\n  🌡️  Oven: ${_ovenTemp}°C | ⏱️ Bake Time: ${_bakeTime} min"
    }
}

// ─────────────────────────────────────────────
// Bonus Subclass: ItalianRecipe
// ─────────────────────────────────────────────
class ItalianRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String,
    region: String = "Italy"
) : Recipe(name, ingredients, instructions) {

    private var _region: String = region
    fun getRegion(): String = _region
    fun setRegion(value: String) { _region = value }

    override fun getRecipeType(): String = "🍝 Italian (${ _region})"
}

// ─────────────────────────────────────────────
// Bonus Subclass: ChineseRecipe
// ─────────────────────────────────────────────
class ChineseRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String,
    cookingStyle: String = "Stir-fry"
) : Recipe(name, ingredients, instructions), CookingMethod {

    private var _cookingStyle: String = cookingStyle
    fun getCookingStyle(): String = _cookingStyle
    fun setCookingStyle(value: String) { _cookingStyle = value }

    // Polymorphic cooking time for Chinese recipes (typically quick)
    override fun getCookingTime(): Double = 15.0

    override fun getRecipeType(): String = "🥢 Chinese (${_cookingStyle})"
}