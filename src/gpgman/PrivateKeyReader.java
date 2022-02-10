package gpgman;

import java.nio.file.*;
import java.security.*;
import java.security.spec.*;

/**
 * Reads PGP private key from .der file
 * source: <br>
 * https://stackoverflow.com/questions/11410770/load-rsa-public-key-from-file
 */
public class PrivateKeyReader {
    public static PrivateKey get(String filename) throws Exception {
        byte[] key_bytes = Files.readAllBytes(Paths.get(filename));

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key_bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        return kf.generatePrivate(spec);
    }
}