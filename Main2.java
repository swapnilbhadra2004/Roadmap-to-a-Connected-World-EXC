import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class Main2 {

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF-8"));
        return java.util.Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = java.util.Base64.getDecoder().decode(cipherText);
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decriptCipher.doFinal(bytes), "UTF-8");
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();

            String originalMessage = "This is a combined RSA Java program";

            String encryptedMessage = encrypt(originalMessage, keyPair.getPublic());
            String decryptedMessage = decrypt(encryptedMessage, keyPair.getPrivate());

            System.out.println("Encrypted Message : " + encryptedMessage);
            System.out.println("Decrypted Message : " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
