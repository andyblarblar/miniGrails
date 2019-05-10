package fitfanatics

class BootStrap {

    def init = { servletContext ->
        def usr1 = new User(fname: "andy",lname: "ealovega")
        usr1.save(flush: true)
        def auth1 = new DbAuth(username: "username",password: "password")
        auth1.setUser(usr1)
        auth1.save()
    }
    def destroy = {
    }
}
