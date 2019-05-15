package fitfanatics

class DbAuth {
    String username
    String password
    //static hasOne = [user:User] removed for updates
    User user //replacement for above
    static constraints = {
        username nullable: false
        username unique: true
        password nullable: false
        password unique: true
        user unique: true
    }
}
