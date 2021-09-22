package com.example.file_system.models;

import java.io.File;
import java.util.Date;

public class FileModel {
    public String FullPath;
    public String ParentPath;
    public String Name;
    public boolean IsFile;
    public long Size;
    public Date DateOfLastModified;

    public FileModel(File file) {
        FullPath = file.getAbsolutePath().replace("\\", "/");
        String parentPath = file.getParent();
        if (parentPath != null) {
            ParentPath = parentPath.replace("\\", "/");
        }
        Name = file.getName();
        IsFile = file.isFile();
        Size = file.length();
        DateOfLastModified = new Date(file.lastModified());
    }
}
