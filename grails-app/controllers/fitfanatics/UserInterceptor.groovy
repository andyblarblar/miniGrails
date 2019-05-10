package fitfanatics

import com.juniorgang.util.HeaderParser

import static org.springframework.http.HttpStatus.UNAUTHORIZED

class UserInterceptor {

    UserInterceptor(){//for DbAuth controller and User
    }

    boolean before() {//get only needs credentials, all others need the same user(including password)
        try {
            String username = HeaderParser.getUsernameFromAuthHeader(request.getHeader("Authorization"))
            String password = HeaderParser.getPasswordFromAuthHeader(request.getHeader("Authorization"))

            if (DbAuth.list().find { it.username == username && it.password == password } != null) {//querys all auths for given username and password TODO add encypt

                return true
            }
        }
        catch (Exception e ){
           println("no auth in request at: ${request.requestURL}")
            render status: UNAUTHORIZED
            return false
        }

        render status: UNAUTHORIZED
        return false

    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
