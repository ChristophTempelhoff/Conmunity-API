package at.conmunity.API.Service;

import org.jetbrains.annotations.NotNull;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CryptographyService {

    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 1024;
    private static final int PBKDF2_ITERATIONS = 1000;

    @org.jetbrains.annotations.NotNull
    public static String CreateHash(String password){
        byte[] salt = new byte[SALT_BYTE_SIZE];

        byte[] hash = PBKDF2(password, salt);

        return PBKDF2_ITERATIONS + ":" +
                Base64.getEncoder().encodeToString(salt) + ":" +
                Base64.getEncoder().encodeToString(hash);
    }

    private static byte[] PBKDF2(@NotNull String password, byte[] salt){
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
