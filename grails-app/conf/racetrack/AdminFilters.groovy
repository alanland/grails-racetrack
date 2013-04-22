package racetrack

class AdminFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
        adminOnly(controller: '*', action: "(create|edit|update|delete|save)") {
            before = {
                if (!session?.user?.admin) {
                    flash.message = "Sorry, Admin only."
                    redirect(controller: 'race', action: 'list')
                    return false
                }
            }
        }
    }
}
