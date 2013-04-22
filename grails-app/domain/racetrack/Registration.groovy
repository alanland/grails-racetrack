package racetrack

class Registration {
    String name
    Date startDate
    String city
    String state
    BigDecimal distance
    BigDecimal cost
    BigDecimal maxRunners = 10000

    static constraints = {
    }
}
