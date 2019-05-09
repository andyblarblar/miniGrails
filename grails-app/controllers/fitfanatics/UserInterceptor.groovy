package fitfanatics

import grails.converters.JSON


class UserInterceptor {

    UserInterceptor(){
        match(controller:"UserController").excludes(action:"save")
    }

    boolean before() {//get only needs credentials, all others need the same user(including password)
        if(request.get){
            if(User.where {usrCredentials == new Credentials(JSON.parse(request.JSON.toString()))}.exists()){
                return true
            }
            return false
        }
        if(User.find(new User(JSON.parse(request.JSON.toString())))){
            return true
        }
        return false

    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
