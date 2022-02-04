import java.util.Objects;

public class Main {

    private static final String
            s_pub = System.getProperty("user.dir") + "/keys/tmp/server/public_key.der",
            s_prv = System.getProperty("user.dir") + "/keys/tmp/server/private_key.der",
            c_pub = System.getProperty("user.dir") + "/keys/tmp/client/public_key.der",
            c_prv = System.getProperty("user.dir") + "/keys/tmp/client/private_key.der";

    public static void main(String[] args) throws Exception {
        GPGManager server_manager = new GPGManager(c_pub,s_prv);
        GPGManager client_manager = new GPGManager(s_pub,c_prv);

        String message = "secret message";
        String enc = server_manager.encrypt(message);
        System.out.println(enc);
        String decrypted = client_manager.decrypt(enc);
        System.out.println(decrypted);
    }
}
