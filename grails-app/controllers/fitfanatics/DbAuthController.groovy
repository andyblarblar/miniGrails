package fitfanatics

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

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dbAuthService.list(params), model:[dbAuthCount: dbAuthService.count()]
    }

    def show(Long id) {
        respond dbAuthService.get(id)
    }

    @Transactional
    def save(DbAuth dbAuth) {
        if (dbAuth == null) {
            render status: NOT_FOUND
            return
        }
        if (dbAuth.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dbAuth.errors
            return
        }

        try {
            dbAuthService.save(dbAuth)
        } catch (ValidationException e) {
            respond dbAuth.errors
            return
        }

        respond dbAuth, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(DbAuth dbAuth) {
        if (dbAuth == null) {
            render status: NOT_FOUND
            return
        }
        if (dbAuth.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dbAuth.errors
            return
        }

        try {
            dbAuthService.save(dbAuth)
        } catch (ValidationException e) {
            respond dbAuth.errors
            return
        }

        respond dbAuth, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        dbAuthService.delete(id)

        render status: NO_CONTENT
    }
}
