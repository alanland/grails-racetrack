package racetrack

class User {

    String login
    String password
    String role = 'user'

    static searchable = true

    static constraints = {
        login(blank: false, nullable: false, unique: true)
        password(blank: false, password: true)
        role(blank: false, inList: ['admin', 'user'])
    }

    static mapping = {
        order('login')
    }

    // 标识不要把 admin 字段保存（该字段有 getter 方法 isAdmin 生成）
    static transients = ['admin']

    boolean isAdmin() {
        return role == 'admin'
    }

    def beforeInsert = {
        password = password.encodeAsSHA()
    }

    def beforeUpdate = beforeInsert

    @Override
    String toString() {
        login
    }
}
