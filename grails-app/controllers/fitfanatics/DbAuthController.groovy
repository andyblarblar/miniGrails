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

    /**
     * gets the user associated with the Auth, must GET users/show
     */
    def show() {
        try {
            respond userService.get(DbAuth.findWhere(
                    username: HeaderParser.getUsernameFromAuthHeader(request.getHeader("Authorization")),
                    password: HeaderParser.getPasswordFromAuthHeader(request.getHeader("Authorization"))).user.id)
        }
        catch (Exception e){println("PASSWORD GOT THROUGH INTERCEPTOR")
        }

    }

    @Transactional
    /**
     * Creates a new User and Auth based off the Authorization HTTP header and the included JSON payload
     */
    def save(User user) {
        def auth = new DbAuth(
                username: HeaderParser.getUsernameFromAuthHeader(request.getHeader("Authorization")),
                password: HeaderParser.getPasswordFromAuthHeader(request.getHeader("Authorization")),
                user: user)
        try {
            dbAuthService.save(auth)
        } catch (ValidationException e) {
            respond auth.errors
            return
        }

        respond auth, [status: CREATED, view:"show"]
    }

    @Transactional
    /**
     * Updates a created user using the JSON payload. Request to users/update
     */
    def update() {
        User user = new User(request.JSON) //needed as we dont send the id, so the user is incomplete
        DbAuth dbAuth = DbAuth.findWhere(
                username: HeaderParser.getUsernameFromAuthHeader(request.getHeader("Authorization")),
                password: HeaderParser.getPasswordFromAuthHeader(request.getHeader("Authorization")))
        dbAuth.setUser(user)

        if (user == null) {
            render status: NOT_FOUND
            return
        }
        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors
            return
        }
        try {
           dbAuthService.save(dbAuth)
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
