package fitfanatics


class UserInterceptor {

    UserInterceptor(){
        match(controller:"UserController").excludes(action:"save")
    }

    boolean before() {
        if(request.ty)// get if GET or PUT. put needs a full onject with credentials, get just needs crednetials

    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
