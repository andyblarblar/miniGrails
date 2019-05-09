package fitfanatics

class BootStrap {

    def init = { servletContext ->
        def usr1 = new User(fname: "andy",lname: "ealovega","usrCredentials": new Credentials(password: "password",username: "name"))
        usr1.save()
    }
    def destroy = {
    }
}
