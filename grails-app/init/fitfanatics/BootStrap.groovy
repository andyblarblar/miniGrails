package fitfanatics

class BootStrap {

    def init = { servletContext ->
        def usr1 = new User(fname: "andy",lname: "ealovega")
        usr1.save()
        def auth1 = new DbAuth(username: "username",password: "password").save()
    }
    def destroy = {
    }
}
