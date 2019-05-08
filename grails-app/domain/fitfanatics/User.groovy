package fitfanatics

class User {
    String fname
    String lname
    Credentials usrCredentials

    static constraints = {
    }
}

class Credentials{
    String username
    String password
    static belongsTo = [user:User]
}
