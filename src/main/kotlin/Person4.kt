class Person4 (val name: String, age: Int, salary: Int): PropertyChangeAware() {

    var age: Int = age
        set(newValue){
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }

    var salary: Int = salary
        set(newValue){
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }

}