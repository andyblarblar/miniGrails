package fitfanatics

import static org.springframework.http.HttpStatus.UNAUTHORIZED

class UserInterceptor {

    UserInterceptor(){
    }

    boolean before() {//get only needs credentials, all others need the same user(including password)
        try {
            int indexOfColon = decodeBase64(request.getHeader("Authorization")).toString().indexOf(":")
            String username = decodeBase64(request.getHeader("Authorization")).toString().substring(0, indexOfColon)
            String password = decodeBase64(request.getHeader("Authorization")).toString().substring(indexOfColon + 1)

            if (DbAuths.list().find { it.username == username && it.password == password } != null) {//querys all auths for given username and password TODO add encypt
                return true
            }
        }
        catch (Exception e ){
           println("no auth in request at: ${new Date()}")
            render status: UNAUTHORIZED
        }
        return false

    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
