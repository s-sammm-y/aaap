package com.aaap.Service.Services;

import com.aaap.Service.Abstractions.IPhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.util.Base64;

@Service
public class PhotoService implements IPhotoService {

    private static final int NONCE_LENGTH = 12;
    private static final int TAG_LENGTH_BITS = 128;

    private final SecretKeySpec secretKey;

    public PhotoService(
            @Value("${photo.encryption.key}") String encodedKey) {

        byte[] keyBytes =
                Base64.getDecoder().decode(encodedKey.trim());

        if (keyBytes.length != 32) {
            throw new IllegalArgumentException(
                    "AES-256 key must be 32 bytes"
            );
        }

        this.secretKey = new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public byte[] getPhoto(String filename) throws Exception {

        // Accept image filenames such as memory1.jpg or memory2.png
        if (filename == null ||
                !filename.matches(
                        "[A-Za-z0-9_-]+\\.(jpg|jpeg|png|webp)")) {

            throw new IllegalArgumentException(
                    "Invalid photo filename"
            );
        }

        // memory1.jpg -> memory1.enc
        // memory2.png -> memory2.enc
        String baseFilename = filename.replaceFirst(
                "\\.(jpg|jpeg|png|webp)$", ""
        );

        String encryptedFilename = baseFilename + ".enc";

        // Read the encrypted file from resources/private-photos
        ClassPathResource resource = new ClassPathResource(
                "private-photos/" + encryptedFilename
        );

        try (InputStream input = resource.getInputStream()) {

            byte[] encryptedData = input.readAllBytes();

            // Decrypt the image and return its original bytes
            return decryptPhoto(encryptedData);
        }
    }

    private byte[] decryptPhoto(byte[] encryptedData)
            throws Exception {

        // 12-byte nonce + at least 16-byte authentication tag
        if (encryptedData.length < NONCE_LENGTH + 16) {
            throw new IllegalArgumentException(
                    "Invalid encrypted photo"
            );
        }

        // Extract the nonce from the first 12 bytes
        byte[] nonce = new byte[NONCE_LENGTH];

        System.arraycopy(
                encryptedData, 0,
                nonce, 0, NONCE_LENGTH
        );

        // Remaining bytes contain ciphertext and GCM tag
        byte[] ciphertext = new byte[
                encryptedData.length - NONCE_LENGTH
                ];

        System.arraycopy(
                encryptedData, NONCE_LENGTH,
                ciphertext, 0, ciphertext.length
        );

        // Decrypt using AES-256-GCM
        Cipher cipher =
                Cipher.getInstance("AES/GCM/NoPadding");

        cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey,
                new GCMParameterSpec(TAG_LENGTH_BITS, nonce)
        );

        return cipher.doFinal(ciphertext);
    }
}