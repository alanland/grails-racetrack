package racetrack

class FooterTagLib {

    def thisYear = {
        out << new Date().format('yyyy')
    }

    def copyRight = { attrs, body ->
        out << "&copy;${attrs.startYear} - ${thisYear()} ${body()}"
    }

}
