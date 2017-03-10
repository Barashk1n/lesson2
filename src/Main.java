import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static PathInfo scan(Path p) throws IOException {
        if (!Files.isDirectory(p)) {
            long size = Files.size(p);
            List<PathInfo> children = Collections.emptyList();
            return new PathInfo(p, size, children);
        } else {
            long sum = 0;
            List<PathInfo> children = new ArrayList<>();
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(p)){
                for (Path child : ds) {
                    PathInfo info = scan(child);
                    children.add(info);
                    sum += info.size;
                }
            }
            return new PathInfo(p,sum,children);
        }
    }

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("/Users/Hamster/Documents");
        PathInfo info = scan(dir);
        print(info," ");
    }
    static void print (PathInfo pi,String level){
        System.out.println(level+pi.path.getFileName()+" "+pi.size);
        for (PathInfo child : pi.children){
            print(child,level+" ");
        }
    }
}
