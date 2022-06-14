package xml;

import shape.PaintShape;

import java.util.List;

public interface XMLPictureReader {

    List<PaintShape> readPicture(String filename);
}
