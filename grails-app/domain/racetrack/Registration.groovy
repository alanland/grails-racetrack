package racetrack

class Registration {

    static constraints = {
        race()
        runner()
        paid()
        dateCreated()
    }

    static mapping = {
//        autoTimestamp false // 关闭 dateCreated lastUpdated 的默认功能
    }

    static belongsTo = [race: Race, runner: Runner]

    Boolean paid
    Date dateCreated

    def beforeInsert = {}
    def beforeUpdate = {}
    def beforeDelete = {}
    def onLoad = {}
}
