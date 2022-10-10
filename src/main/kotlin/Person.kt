class Person (
    val firstName: String, val lastName: String
        ): Comparable <Person>{
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, {it.lastName}, Person::firstName)
    }
}