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
    public void showDetails(String indent) {
        System.out.println(indent + "📄 File: " + name + " (" + sizeInMb + "MB)");
    }
}