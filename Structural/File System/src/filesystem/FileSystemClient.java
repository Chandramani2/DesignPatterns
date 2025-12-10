package filesystem;

import filesystem.component.FileSystemComponent;
import filesystem.composite.Directory;
import filesystem.leaf.File;

public class FileSystemClient {
    public static void main(String[] args) {
        // 1. Create Files
        FileSystemComponent file1 = new File("resume.pdf", 2);
        FileSystemComponent file2 = new File("photo.png", 5);
        FileSystemComponent file3 = new File("movie.mp4", 500);

        // 2. Create Directories
        Directory rootDir = new Directory("Root");
        Directory documents = new Directory("Documents");
        Directory media = new Directory("Media");

        // 3. Build Tree
        documents.addComponent(file1);
        media.addComponent(file2);
        media.addComponent(file3);

        rootDir.addComponent(documents);
        rootDir.addComponent(media);

        // 4. Print Tree
        System.out.println("--- Simplified Package Structure ---");
        rootDir.showDetails("");
    }
}