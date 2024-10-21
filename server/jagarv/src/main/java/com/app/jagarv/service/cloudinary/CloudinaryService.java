import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.Map;

// Manages the API's image upload to the cloud
@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;
    
    // Uploads a image
    public Map<String, Object> uploadImage(File file, String folder) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", folder));
    }
}