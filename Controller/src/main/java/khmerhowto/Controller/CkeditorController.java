package khmerhowto.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static khmerhowto.globalFunction.GlobalFunctionHelper.uploaded;

@Controller
public class CkeditorController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> fileUpload(@RequestParam("file") MultipartFile file){
        Map<String, Object> response = new HashMap<>();
        String fileName = "";
        try {
            fileName = uploaded(file);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", false);
            response.put("message", "Image is not found.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("status", true);
        response.put("message", "Successfully uploaded image!");
        response.put("Data", fileName);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
