package gpgman;

import java.nio.file.*;
import java.security.*;
import java.security.spec.*;

/**
 * Reads PGP public key from .der file
 * source: <br>
 * https://stackoverflow.com/questions/11410770/load-rsa-public-key-from-file
 */
public class PublicKeyReader {
    public static PublicKey get(String filename) throws Exception {

        byte[] key_bytes = Files.readAllBytes(Paths.get(filename));

        X509EncodedKeySpec spec = new X509EncodedKeySpec(key_bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}