import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GPGManager {

    private final String public_key_path, private_key_path;
    private Cipher encryptCipher, decryptCipher;

    public GPGManager(String public_key_file, String private_key_path) throws Exception {
        this.public_key_path = public_key_file;
        this.private_key_path = private_key_path;
        read_keys();
    }

    /**
     * Reads private/public key data from specified files and loads them into Cipher objects.
     */
    private void read_keys() throws Exception {
        // keys
        PrivateKey privateKey = PrivateKeyReader.get(private_key_path);
        PublicKey publicKey = PublicKeyReader.get(public_key_path);

        encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    }

    public String encrypt(String message) throws IllegalBlockSizeException, BadPaddingException {
        byte[] encrypted_bytes = encryptCipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted_bytes);
    }

    public String decrypt(String message) throws IllegalBlockSizeException, BadPaddingException {
        byte [] bytes = Base64.getDecoder().decode(message);
        return new String(decryptCipher.doFinal(bytes),StandardCharsets.UTF_8);
    }
}
