package filesystem.component;

public interface FileSystemComponent {
    void showDetails(String indent);
    String getName();

    // Recursive DFS is the natural fit for Composite Pattern
    FileSystemComponent searchDFS(String keyword);
}