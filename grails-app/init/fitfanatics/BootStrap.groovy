package fitfanatics

class BootStrap {

    def init = { servletContext ->
        def usr1 = new User(fname: "andy",lname: "ealovega")
        def credentails1 = new Credentials(password: "password",username: "name")
        usr1.usrCredentials == credentails1
        usr1.save()
    }
    def destroy = {
    }
}
