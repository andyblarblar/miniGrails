package fitfanatics

class DbAuth {
    String username
    String password
    static hasOne = [user:User]
    static constraints = {
        username nullable: false
        username unique: true
        password nullable: false
        password unique: true
    }
}
