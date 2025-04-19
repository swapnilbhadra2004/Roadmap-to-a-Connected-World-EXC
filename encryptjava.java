import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.Base64;

public class encryptjava {

    // --- Classical Encryption: Caesar Cipher ---
    public static String caesarEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift) % 26 + base));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String caesarDecrypt(String text, int shift) {
        return caesarEncrypt(text, 26 - shift);
    }

    // --- Classical Encryption: Vigenère Cipher ---
    public static String vigenereEncrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                result.append((char) ((ch + key.charAt(j) - 2 * 'A') % 26 + 'A'));
                j = ++j % key.length();
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String vigenereDecrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                result.append((char) ((ch - key.charAt(j) + 26) % 26 + 'A'));
                j = ++j % key.length();
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    // --- Modern Encryption: AES ---
    public static String aesEncrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    public static String aesDecrypt(String encrypted, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }

    // --- Modern Encryption: RSA ---
    public static KeyPair generateRSAKeys() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static String rsaEncrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    public static String rsaDecrypt(String encrypted, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }

    // --- Main Method ---
    public static void main(String[] args) throws Exception {
        String message = "HELLO CRYPTOGRAPHY";

        System.out.println("Original Message: " + message);

        // Caesar Cipher
        String caesarEncrypted = caesarEncrypt(message, 3);
        String caesarDecrypted = caesarDecrypt(caesarEncrypted, 3);
        System.out.println("\nCaesar Encrypted: " + caesarEncrypted);
        System.out.println("Caesar Decrypted: " + caesarDecrypted);

        // Vigenère Cipher
        String vigenereEncrypted = vigenereEncrypt(message, "KEY");
        String vigenereDecrypted = vigenereDecrypt(vigenereEncrypted, "KEY");
        System.out.println("\nVigenère Encrypted: " + vigenereEncrypted);
        System.out.println("Vigenère Decrypted: " + vigenereDecrypted);

        // AES Encryption
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey aesKey = keyGen.generateKey();
        String aesEncrypted = aesEncrypt(message, aesKey);
        String aesDecrypted = aesDecrypt(aesEncrypted, aesKey);
        System.out.println("\nAES Encrypted: " + aesEncrypted);
        System.out.println("AES Decrypted: " + aesDecrypted);

        // RSA Encryption
        KeyPair rsaKeys = generateRSAKeys();
        String rsaEncrypted = rsaEncrypt(message, rsaKeys.getPublic());
        String rsaDecrypted = rsaDecrypt(rsaEncrypted, rsaKeys.getPrivate());
        System.out.println("\nRSA Encrypted: " + rsaEncrypted);
        System.out.println("RSA Decrypted: " + rsaDecrypted);
    }
}

