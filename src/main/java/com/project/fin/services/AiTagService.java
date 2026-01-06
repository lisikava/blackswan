package com.project.fin.services;

import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AiTagService {
    public List<String> generateTags(MultipartFile image) throws IOException {
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            ByteString imgBytes = ByteString.copyFrom(image.getBytes());
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feature = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();

            AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(img).build();

            BatchAnnotateImagesResponse response = client.batchAnnotateImages(List.of(request));

            return response.getResponses(0)
                    .getLabelAnnotationsList()
                    .stream()
                    .filter(label -> label.getScore() > 0.7)
                    .map(label -> label.getDescription().toLowerCase())
                    .distinct()
                    .toList();
        }
    }
}
