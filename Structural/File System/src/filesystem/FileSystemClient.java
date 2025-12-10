package filesystem;

import filesystem.component.FileSystemComponent;
import filesystem.composite.Directory;
import filesystem.leaf.File;

public class FileSystemClient {
    public static void main(String[] args) {
        // Setup Tree
        Directory root = new Directory("Root");
        Directory documents = new Directory("Documents");
        Directory media = new Directory("Media");
        Directory privateDocs = new Directory("Private"); // Deeply nested

        FileSystemComponent file1 = new File("resume.pdf", 2);
        FileSystemComponent file2 = new File("photo.png", 5);
        FileSystemComponent file3 = new File("secret.txt", 1);

        root.addComponent(documents);
        root.addComponent(media);
        documents.addComponent(file1);
        documents.addComponent(privateDocs); // Nested folder
        privateDocs.addComponent(file3);     // Deep file
        media.addComponent(file2);

        System.out.println("--- Tree Structure ---");
        root.showDetails("");

        // --- Test DFS ---
        System.out.println("\n--- Searching DFS (Recursive) ---");
        FileSystemComponent resultDFS = root.searchDFS("secret.txt");
        if(resultDFS != null) System.out.println("Found: " + resultDFS.getName());

        // --- Test BFS ---
        System.out.println("\n--- Searching BFS (Iterative) ---");
        // BFS is specific to the Directory class implementation in this design
        FileSystemComponent resultBFS = root.searchBFS("photo.png");
        if(resultBFS != null) System.out.println("Found: " + resultBFS.getName());
    }
}