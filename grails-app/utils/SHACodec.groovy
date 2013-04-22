import java.security.MessageDigest

/**
 * User:   WangChengyi
 * EMail:  alanland@163.com
 * GitHub: https://github.com/alanland
 * Date:   4/22/13
 * Time:   9:06 PM
 */
class SHACodec {
    static encode = { target ->
        MessageDigest md = MessageDigest.getInstance('SHA')
        md.update(target.getBytes('UTF-8'))
        return new String(md.digest()).encodeAsBase64()
    }
}
