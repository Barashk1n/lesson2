import java.nio.file.Path;
import java.util.List;

public class PathInfo {
        Path path;
        long size;
        List<PathInfo> children;
    public PathInfo(Path path, long size, List<PathInfo> children) {
        this.path = path;
        this.size = size;
        this.children = children;
    }
}
