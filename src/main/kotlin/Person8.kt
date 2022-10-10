class Person8 {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute (attrName: String, value: String){
        _attributes[attrName] = value
    }

    val name: String by _attributes
}