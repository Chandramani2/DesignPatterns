package filesystem.leaf;

import filesystem.component.FileSystemComponent;

public class File implements FileSystemComponent {
    private String name;
    private int sizeInMb;

    public File(String name, int sizeInMb) {
        this.name = name;
        this.sizeInMb = sizeInMb;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📄 File: " + name);
    }

    @Override
    public FileSystemComponent searchDFS(String keyword) {
        // Base case: If I am the file you are looking for, return me.
        if (this.name.equals(keyword)) {
            return this;
        }
        return null;
    }
}