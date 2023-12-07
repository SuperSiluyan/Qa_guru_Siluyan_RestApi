package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddBookRequest {
    String userId;
    public List<CollectionInfo> collectionOfIsbns = new ArrayList<>();

    @Data
    public static class CollectionInfo {
        String isbn;
    }
}
