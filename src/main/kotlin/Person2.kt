class Person2 (val name: String) {
    private var _emails: List<Email>? = null

    val emails: List<Email>
        get(){
            if (_emails == null){
                _emails = loadEmails(this)
            }
            return _emails!!
        }
}