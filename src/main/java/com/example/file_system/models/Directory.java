package com.example.file_system.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {

    public FileModel ParentDirectory;
    public List<FileModel> Children = new ArrayList<>();

    public Directory(File directory) {
        ParentDirectory = new FileModel(directory);
        for (File child : directory.listFiles()) {
            Children.add(new FileModel(child));
        }
    }
}
