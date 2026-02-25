package de.oks.g52shop.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import de.oks.g52shop.service.interfaces.FileService;
import de.oks.g52shop.service.interfaces.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final AmazonS3 client;
    private final ProductService productService;

    public FileServiceImpl(AmazonS3 client, ProductService productService) {
        this.client = client;
        this.productService = productService;
    }

    @Override
    public String upload(MultipartFile file, String productTitle) {
        try {
            String uniqName = generateUniqueFileName(file);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());

            PutObjectRequest request = new PutObjectRequest(
                    "shop-bucket", uniqName, file.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            client.putObject(request);

            String url = client.getUrl("shop-bucket", uniqName).toString();

            productService.attachImage(url, productTitle);

            return url;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateUniqueFileName(MultipartFile file) {
        // file -> banana.picture.jpeg
        String sourceFileName = file.getOriginalFilename();
        int dotIndex = sourceFileName.lastIndexOf(".");
        // file -> banana.picture
        String fileName = sourceFileName.substring(0, dotIndex);
        // file -> banana.picture.jpeg -> .jpeg
        String extension = sourceFileName.substring(dotIndex);

       // banana.picture-fdsfsf544-dgdgdgg-dgdgfgdg.jpeg
        return String.format("%s-%s%s", fileName, UUID.randomUUID(), extension);
    }
}
