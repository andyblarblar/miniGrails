package fitfanatics


class UserInterceptor {

    UserInterceptor(){
        match(controller:"UserController").excludes(action:"save")
    }

    boolean before() {//get only needs credentials, all others need the same user(including password)
        if(request.get){
            if(User.where {usrCredentials == request.JSON}.exists()){
                return true
            }
            return false
        }
        if(User.where {request.JSON as User == it}.exists()){
            return true
        }
        return false

    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
