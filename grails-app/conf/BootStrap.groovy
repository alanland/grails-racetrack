import grails.util.Environment
import racetrack.Race
import racetrack.Registration
import racetrack.Runner
import racetrack.User
import racetrack.login.Authority
import racetrack.login.Person
import racetrack.login.PersonAuthority

class BootStrap {

    def init = { servletContext ->

        switch (Environment.getCurrent()) {
            case Environment.DEVELOPMENT:
                initData()
                break
            case Environment.PRODUCTION:
                break
        }
    }

    private initData() {
        //
        def runner = new Runner(
                firstName: '王',
                lastName: '成义',
                dateOfBirth: (new Date() - 365 * 30),
                gender: 'F',
                address: '中江路123',
                city: '上海',
                state: '上海',
                zipCode: '12124',
                email: 'wang.cheng@yi.com'
        )
        runner.save()
        if (runner.hasErrors()) {
            println runner.errors
        }

        //
        def race = new Race(
                name: '新世纪马拉松比赛',
                startDate: (new Date() + 30),
                city: '上海',
                state: '上海',
                distance: 5.0,
                cost: 20.0,
                maxRunners: 300
        )
        race.save()
        if (race.hasErrors())
            println race.errors

        //
        def reg = new Registration(
                paid: false,
                runner: runner,
                race: race
        )
        reg.save()
        if (reg.hasErrors())
            println reg.errors

        //
        def admin = new User(
                login: 'admin',
                password: 'admin123',
                role: 'admin'
        )
        admin.save()
        if (admin.hasErrors())
            println admin.errors

        //
        def user = new User(
                login: 'user',
                password: 'user123',
                role: 'user'
        )
        user.save()
        if (user.hasErrors())
            println user.errors

        // spring security
        def userRole = Authority.findByAuthority('ROLE_USER') ?: new Authority(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Authority.findByAuthority('ROLE_ADMIN') ?: new Authority(authority: 'ROLE_ADMIN').save(failOnError: true)
        def userUser = new Person(username: 'wang', enabled: true, password: 'wang')
        userUser.save(flush: true)
        PersonAuthority.create userUser, userRole, true
        def adminUser = new Person(username: 'admin', enabled: true, password: 'admin')
        adminUser.save(flush: true)
        PersonAuthority.create adminUser, adminRole, true

    }

    def destroy = {
    }
}
