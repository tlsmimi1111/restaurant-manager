package axon.tls.restaurant.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.fasterxml.uuid.Generators;

@Service
public class StorageService {
   private static final String JPG_EXTEND_FILE = ".jpg";
   public static String CONTENT_TYPE_IMAGE = "image/jpg";

   public static String IMAGE_URL_PATH_RESOURCE = "medias/images/"; //Path follow path controller -> using constants

   public static CharSequence CONDITION_IS_IMAGE = "image/";

   private AmazonS3 s3client;

   @Value("${amazonProperties.region}")
   private String region;

   @Value("${amazonProperties.s3.bucketName}")
   private String bucketName;

   @PostConstruct
   private void initializeAmazon() {
	   this.bucketName = this.bucketName.trim();
       this.s3client = AmazonS3ClientBuilder
               .standard()
               .withRegion(Regions.fromName(region))
               .build();
   }

   public String saveFile(MultipartFile multipartFile) {
       String fileName = Generators.timeBasedGenerator().generate().toString() + JPG_EXTEND_FILE;
       String filePathS3 = IMAGE_URL_PATH_RESOURCE + fileName;
       try {
           InputStream inputStream = multipartFile.getInputStream();

           byte[] contents = IOUtils.toByteArray(inputStream);
           InputStream stream = new ByteArrayInputStream(contents);

           ObjectMetadata meta = new ObjectMetadata();
           meta.setContentLength(contents.length);
           meta.setContentType(CONTENT_TYPE_IMAGE);

           s3client.putObject(new PutObjectRequest(
                   bucketName, filePathS3, stream, meta)
                   .withCannedAcl(CannedAccessControlList.BucketOwnerFullControl));

           inputStream.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return filePathS3;
   }

   public byte[] getImage(String nameImage) {
       try {
           S3Object s3object = s3client.getObject(bucketName, IMAGE_URL_PATH_RESOURCE + nameImage);
           S3ObjectInputStream inputStream = s3object.getObjectContent();
           return IOUtils.toByteArray(inputStream);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }
}
