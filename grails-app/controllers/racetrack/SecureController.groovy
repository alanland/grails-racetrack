package racetrack

import org.springframework.security.access.annotation.Secured


class SecureController {

    @Secured(['ROLE_ADMIN'])
    def index() {
        render 'Secure access only'
    }

//    def scaffold = true
}
