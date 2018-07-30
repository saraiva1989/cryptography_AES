package com.danielsaraiva1.cryptoeas_ecb.Helper;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Criptografia {

    private final static char[] _hexArray = "0123456789ABCDEF".toCharArray();
    private static String _key = "orhvlwprghtjblqr";

    public static String BytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = _hexArray[v >>> 4];
            hexChars[j * 2 + 1] = _hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] HexStringToByteArray(String texto) {
        byte[] retornaByte = new byte[texto.length() / 2];
        for (int i = 0; i < retornaByte.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(texto.substring(index, index + 2), 16);
            retornaByte[i] = (byte) v;
        }
        return retornaByte;
    }

    public static byte[] Criptografar(String texto) {
        try {
            byte[] encryptionKey = _key.getBytes(StandardCharsets.UTF_8);
            byte[] plainText = texto.getBytes(StandardCharsets.UTF_8);
            AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(
                    encryptionKey);
            byte[] cipherText = advancedEncryptionStandard.encrypt(plainText);

            return cipherText;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error ".getBytes();
    }

    public static String Descriptografar(byte[] texto) {
        try {
            byte[] encryptionKey = _key.getBytes(StandardCharsets.UTF_8);
            AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(
                    encryptionKey);
            byte[] decryptedCipherText = advancedEncryptionStandard.decrypt(texto);

            return new String(decryptedCipherText);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error ";
    }


}

class AdvancedEncryptionStandard {
    private byte[] key;

    private static final String ALGORITHM = "AES/ECB/NOPADDING";

    public AdvancedEncryptionStandard(byte[] key) {
        this.key = key;
    }

    /**
     * Encrypts the given plain text
     *
     * @param plainText The plain text to encrypt
     */
    public byte[] encrypt(byte[] plainText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }

    /**
     * Decrypts the given byte array
     *
     * @param cipherText The data to decrypt
     */
    public byte[] decrypt(byte[] cipherText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }
}
