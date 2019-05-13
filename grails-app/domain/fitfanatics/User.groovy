package fitfanatics

class User {
    String fname
    String lname
    static belongsTo = [dbauth:DbAuth]
    static constraints = {
    }

}


