package filesystem.composite;

import filesystem.component.FileSystemComponent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children;

    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        children.add(component);
    }

    public List<FileSystemComponent> getChildren() {
        return children;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📂 Directory: " + name);
        for (FileSystemComponent component : children) {
            component.showDetails(indent + "    ");
        }
    }

    // ============================
    // 1. DFS Algorithm (Recursive)
    // ============================
    @Override
    public FileSystemComponent searchDFS(String keyword) {
        // Check self first
        if (this.name.equals(keyword)) {
            return this;
        }

        // Delegate to children (Depth First)
        for (FileSystemComponent component : children) {
            FileSystemComponent result = component.searchDFS(keyword);
            if (result != null) {
                return result; // Found it in the depths
            }
        }
        return null; // Not found in this branch
    }

    // ============================
    // 2. BFS Algorithm (Iterative)
    // ============================
    // Note: This is specific to Directory because it manages the Queue of children
    public FileSystemComponent searchBFS(String keyword) {
        Queue<FileSystemComponent> queue = new LinkedList<>();

        // Start with self
        queue.add(this);

        while (!queue.isEmpty()) {
            FileSystemComponent current = queue.poll();

            // Check if this is what we want
            if (current.getName().equals(keyword)) {
                return current;
            }

            // If it's a Directory, add its children to the queue (Next Level)
            if (current instanceof Directory) {
                queue.addAll(((Directory) current).getChildren());
            }
        }

        return null;
    }
}