package fitfanatics

import com.juniorgang.util.HeaderParser

import static org.springframework.http.HttpStatus.UNAUTHORIZED

class UserInterceptor {

    UserInterceptor(){
        matchAll()//exclude errors TODO
    }

    boolean before() {//lets you through if the authoization header has a username and password that exists
        try {
            String username = HeaderParser.getUsernameFromAuthHeader(request.getHeader("Authorization"))
            String password = HeaderParser.getPasswordFromAuthHeader(request.getHeader("Authorization"))

            if (DbAuth.list().find { it.username == username && it.password == password } != null) {//querys all auths for given username and password TODO add encypt (change to findWhere?)

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
