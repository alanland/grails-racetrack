package racetrack



import grails.test.mixin.*
import org.codehaus.groovy.grails.plugins.codecs.Base64Codec
import SHACodec
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

    @Before
    void setUp() {
        String.metaClass.encodeAsBase64 = {->
            Base64Codec.encode(delegate)
        }
        String.metaClass.encodeAsSHA = {->
            SHACodec.encode(delegate)
        }
    }

    void testSimpleConstraints() {
        mockForConstraintsTests(User)
        def user = new User(
                login: 'wang',
                password: 'cheng'.encodeAsSHA(),
                role: 'some role'
        )
        assertFalse user.validate()
        assertEquals 'inList', user.errors['role']
    }

    void testUniqueConstraint() {
        def wang = new User(login: 'wang')
        def admin = new User(login: 'admin'.encodeAsSHA())
        mockDomain(User, [wang, admin])
//        wang.save()
//        admin.save()

        def badUser = new User(login: 'wang')
        badUser.save()
        assertEquals 2, User.count()
        assertEquals 'unique', badUser.errors['login']

        def goodUser = new User(login: "good", password: "password", role: "user")
        goodUser.save()
        assertEquals 3, User.count()
        assertNotNull User.findByLoginAndPassword('good', 'password')


    }
}
