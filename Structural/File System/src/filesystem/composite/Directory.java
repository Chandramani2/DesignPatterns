package filesystem.composite;

import filesystem.component.FileSystemComponent;
import java.util.ArrayList;
import java.util.List;

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

    public void removeComponent(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📂 Directory: " + name);

        for (FileSystemComponent component : children) {
            // Recursive call
            component.showDetails(indent + "    ");
        }
    }
}