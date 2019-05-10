package fitfanatics

class DbAuth {
    String username
    String password
    static hasMany = [users:User]// each account has many users TODO
    static constraints = {
        username nullable: false
        password nullable: false
    }
}
