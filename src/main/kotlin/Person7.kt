class Person7 {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute (attrName: String, value: String){
        _attributes[attrName] = value
    }

    val name: String
        get() = _attributes["name"]!!
}