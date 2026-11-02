
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
import java.util.concurrent.Semaphore;

@Service
public class PhotoService implements IPhotoService {

    private static final int NONCE_LENGTH = 12;
    private static final int TAG_LENGTH_BITS = 128;

    private final SecretKeySpec secretKey;

    // Allow maximum 2 simultaneous decryptions
    private final Semaphore photoSemaphore =
            new Semaphore(2, true);

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

        // Wait until a decryption slot becomes available
        photoSemaphore.acquire();

        try {

            if (filename == null ||
                    !filename.matches(
                            "[A-Za-z0-9_-]+\\.(jpg|jpeg|png|webp)")) {

                throw new IllegalArgumentException(
                        "Invalid photo filename"
                );
            }

            String baseFilename = filename.replaceFirst(
                    "\\.(jpg|jpeg|png|webp)$", ""
            );

            String encryptedFilename = baseFilename + ".enc";

            ClassPathResource resource = new ClassPathResource(
                    "private-photos/" + encryptedFilename
            );

            try (InputStream input = resource.getInputStream()) {

                byte[] encryptedData = input.readAllBytes();

                return decryptPhoto(encryptedData);
            }

        } finally {

            // Always release the slot, even if decryption fails
            photoSemaphore.release();
        }
    }

    private byte[] decryptPhoto(byte[] encryptedData)
            throws Exception {

        if (encryptedData.length < NONCE_LENGTH + 16) {
            throw new IllegalArgumentException(
                    "Invalid encrypted photo"
            );
        }

        byte[] nonce = new byte[NONCE_LENGTH];

        System.arraycopy(
                encryptedData, 0,
                nonce, 0, NONCE_LENGTH
        );

        byte[] ciphertext = new byte[
                encryptedData.length - NONCE_LENGTH
                ];

        System.arraycopy(
                encryptedData, NONCE_LENGTH,
                ciphertext, 0, ciphertext.length
        );

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
