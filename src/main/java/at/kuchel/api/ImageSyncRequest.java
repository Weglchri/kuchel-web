package at.kuchel.api;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImageSyncRequest {

    List<String> imageIds = new ArrayList<>();
}


