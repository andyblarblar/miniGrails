package fitfanatics

class DbAuth {
    String username
    String password
                    //make has many users and link users
    static constraints = {
        username nullable: false
        password nullable: false
    }
}
