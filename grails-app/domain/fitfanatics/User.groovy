package fitfanatics

class User {
    String fname
    String lname
    Credentials usrCredentials

    static constraints = {
        usrCredentials nullable: false
    }
}

class Credentials{
    String username
    String password
    //static belongsTo = [user:User] uneeded

    static constraints = {
    }
}
