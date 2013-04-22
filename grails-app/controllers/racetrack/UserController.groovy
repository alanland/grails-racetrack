package racetrack

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def login = {}
    def logout = {
        flash.message = "Goodbye ${session.user.login}"
        session.user = null
        redirect(aciton: 'login')
    }
    def authenticate = {
        def user = User.findByLoginAndPassword(params.login, params.password)
        if (user) {
            session.user = user
            flash.message = "Hello ${user.login}"
            redirect(controller: 'race', action: 'list')
        } else {
            flash.message = "Sorry,${params.login}. Please try again."
            redirect(action: 'login')
        }
    }

    def scaffold = true
}
