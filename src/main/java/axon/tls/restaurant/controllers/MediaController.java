package axon.tls.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import axon.tls.restaurant.config.ApiConfig;
import axon.tls.restaurant.services.StorageService;

@RestController
@RequestMapping(ApiConfig.PREFIX)
@CrossOrigin
public class MediaController {
   @Autowired
   private StorageService storageService;

   @PostMapping("/upload-image")
   public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) {
       return ResponseEntity.ok(storageService.saveFile(file));
   }

   @GetMapping("/images/{name_image}")
   public ResponseEntity<byte[]> getImage(@PathVariable("name_image") String nameImage) {
       return ResponseEntity.ok()
               .contentType(MediaType.valueOf(StorageService.CONTENT_TYPE_IMAGE))
               .body(storageService.getImage(nameImage));
   }
   
}