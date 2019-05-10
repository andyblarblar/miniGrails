package fitfanatics

import static org.springframework.http.HttpStatus.UNAUTHORIZED

class UserInterceptor {

    UserInterceptor(){
    }

    boolean before() {//get only needs credentials, all others need the same user(including password)
        try {
            int indexOfColon = request.getHeader("Authorization").indexOf(":")
            String username = request.getHeader("Authorization").substring(6, indexOfColon)
            String password = request.getHeader("Authorization").substring(indexOfColon + 1)

            if (DbAuth.list().find { it.username == username && it.password == password } != null) {//querys all auths for given username and password TODO add encypt
                return true
            }
        }
        catch (Exception e ){
           println("no auth in request at: ${request.requestURL}")
            render status: UNAUTHORIZED
        }
        render status: UNAUTHORIZED
        return false

    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
