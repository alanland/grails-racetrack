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

        def runner2 = new Runner(
                firstName: '王',
                lastName: '阳明',
                dateOfBirth: (new Date() - 365 * 3000),
                gender: 'F',
                address: 'xxx',
                city: '上海',
                state: '上海',
                zipCode: '12124',
                email: 'wang.cheng@yi.com'
        ).save()
        def runner3 = new Runner(
                firstName: '丘',
                lastName: '处机',
                dateOfBirth: (new Date() - 365 * 3500),
                gender: 'F',
                address: 'xxx',
                city: '上海',
                state: '上海',
                zipCode: '12124',
                email: 'wang.cheng@yi.com'
        ).save()
        def runner4 = new Runner(
                firstName: '小白',
                lastName: '鼠',
                dateOfBirth: (new Date() - 365 * 3500),
                gender: 'F',
                address: 'xxx',
                city: '上海',
                state: '上海',
                zipCode: '12124',
                email: 'wang.cheng@yi.com'
        ).save()

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
        def race2 = new Race(
                name: '大夏王朝马拉松',
                startDate: (new Date() + 30),
                city: '都城',
                state: 'xxx',
                distance: 500.0,
                cost: 0.0,
                maxRunners: 6000
        ).save()

        //
        def reg = new Registration(paid: false, runner: runner, race: race)
        reg.save()
        if (reg.hasErrors())
            println reg.errors
        def reg1 = new Registration(paid: false, runner: runner, race1: race).save()
        def reg21 = new Registration(paid: false, runner2: runner, race: race).save()
        def reg22 = new Registration(paid: false, runner2: runner, race: race).save()
        def reg31 = new Registration(paid: false, runner3: runner, race: race).save()
        def reg32 = new Registration(paid: false, runner3: runner, race: race).save()

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
