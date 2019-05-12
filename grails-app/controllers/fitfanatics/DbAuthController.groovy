package fitfanatics

import com.juniorgang.util.HeaderParser
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class DbAuthController {

    DbAuthService dbAuthService
    UserService userService
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

/* unneeded
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dbAuthService.list(params), model:[dbAuthCount: dbAuthService.count()]
    }
*/

    /**
     * gets the user associated with the Auth, must GET users/show
     */
    def show() {//be careful recompiling
        try {
            respond userService.get(DbAuth.findWhere(
                    username: HeaderParser.getUsernameFromAuthHeader(request.getHeader("Authorization")),
                    password: HeaderParser.getPasswordFromAuthHeader(request.getHeader("Authorization"))).id)
        }
        catch (Exception e){println("showing a user that deosnt exist,should only happen if in ide")}
    }

    @Transactional
    def save(User user) {
        def auth = new DbAuth(
                username: HeaderParser.getUsernameFromAuthHeader(request.getHeader("Authorization")),
                password: HeaderParser.getPasswordFromAuthHeader(request.getHeader("Authorization")),
                user: user)
        try {
            dbAuthService.save(auth)
        } catch (ValidationException e) {//keep, Dbauth can through if not unique pass and username
            respond auth.errors
            return
        }

        respond auth, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(User user) {//currently 404s
        println("acssesed")//for testing
        if (user == null) {
            render status: NOT_FOUND
            return
        }
        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dbAuth.errors
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors
            return
        }

        respond user, [status: OK, view:"show"]
    }

    @Transactional
    /**
     * deletes the auth and user associated with the HTTP header
     */
    def delete() {
        dbAuthService.delete(DbAuth.findWhere(
                username: HeaderParser.getUsernameFromAuthHeader(request.getHeader("Authorization")),
                password: HeaderParser.getPasswordFromAuthHeader(request.getHeader("Authorization"))).id)

        render status: NO_CONTENT
    }
}
