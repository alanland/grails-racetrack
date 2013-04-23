package racetrack

class Race {
    String name
    Date startDate
    String city
    String state
    BigDecimal distance
    BigDecimal cost
    Integer maxRunners = 10000

    BigDecimal inMiles() {
        return distance * 0.6214
    }

    static searchable = true

    static constraints = {
        name(blank: false, maxSize: 50)
        startDate(validator: { return (it >= new Date()) })
        city()
        state(inList: ['上海', '江苏', '浙江', '福建'])
        distance(min: 0.0)
        cost(min: 0.0, max: 100.0)
        maxRunners(min: 0, max: 100000)
    }

    static mapping = {
        sort 'startDate' // Race 列表的排序
    }

    static hasMany = [registration: Registration]

    String toString(){
        return "$name, ${startDate.format('MM/dd/yyyy')}"
    }
}
