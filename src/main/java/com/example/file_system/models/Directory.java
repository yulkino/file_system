package com.example.file_system.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {

    public FileModel ParentDirectoryPath;
    public List<FileModel> Children = new ArrayList<>();

    public Directory(File directory) {
        ParentDirectoryPath = new FileModel(directory);
        for (File child : directory.listFiles()) {
            Children.add(new FileModel(child));
        }
    }
}
