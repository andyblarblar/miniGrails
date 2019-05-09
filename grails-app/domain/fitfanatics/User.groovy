package fitfanatics

class User {
    String fname
    String lname
    Credentials usrCredentials// remove and replace with http header based login

    static constraints = {
        usrCredentials nullable: false
    }
}

class Credentials{
    String username
    String password
    //static belongsTo = [user:User] uneeded

    static constraints = {
        username nullable: false
        password nullable: false
    }
}
