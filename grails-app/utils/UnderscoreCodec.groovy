/**
 * Created with IntelliJ IDEA.
 * User: journey
 * Date: 4/22/13
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
class UnderscoreCodec {
    static encode = { target ->
        target.replaceAll(' ', '_')
    }

    static decode = { target ->
        target.replaceAll('_', ' ')
    }
}
