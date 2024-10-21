package com.app.jagarv.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

// Manages the API's image upload to the cloud
@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;
    
    // Uploads a image
    public Map<String, Object> uploadImage(MultipartFile file, String folder) throws IOException {
    return cloudinary.uploader().upload(file.getInputStream(), ObjectUtils.asMap("folder", folder));
}


}