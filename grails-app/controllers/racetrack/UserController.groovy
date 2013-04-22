package racetrack

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    def beforeInterceptor = [action: this.&debug]
    def beforeInterceptor = [action: this.&auth, except: ['login', 'logout', 'authenticate']]

    def auth() {
        if (!session.user) {
            redirect(controller: 'user', action: 'login')
            return false
        }

        if (!session.user.admin) {
            flash.message = "Tsk tsk-admin only!"
            redirect(controller: 'race', action: 'list')
            return false
        }
    }

    def debug() {
        println "debug: ${actionUri} called"
        println "debug: ${params}"
    }

    def login = {}
    def logout = {
        flash.message = "Goodbye ${session.user.login}"
        session.user = null
        redirect(aciton: 'login')
    }
    def authenticate = {
        def user = User.findByLoginAndPassword(params.login, params.password.encodeAsSHA())
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
