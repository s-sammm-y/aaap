package com.aaap.webapi.Controllers;

import com.aaap.Service.Abstractions.IPhotoService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/gallery/photo")
public class GalleryController {
    private final IPhotoService photoService;

    public GalleryController(IPhotoService photoService){
        this.photoService = photoService;
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<byte[]> getPhoto(
            @PathVariable String filename) {

        try {
            MediaType contentType = getContentType(filename);
            byte[] photo = photoService.getPhoto(filename);

            return ResponseEntity.ok()
                    .contentType(contentType)
                    .cacheControl(CacheControl.noStore())
                    .body(photo);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid photo request"
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Unable to load photo"
            );
        }
    }

    private MediaType getContentType(String filename) {
        String name = filename.toLowerCase();

        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        }
        if (name.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        }
        if (name.endsWith(".webp")) {
            return MediaType.parseMediaType("image/webp");
        }

        throw new IllegalArgumentException("Unsupported image type");
    }
}
