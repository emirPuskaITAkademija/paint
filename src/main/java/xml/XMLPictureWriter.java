package xml;

import shape.PaintShape;

import java.util.List;

public interface XMLPictureWriter {
    void savePicture(List<PaintShape> paintShapes, String filename);
}
