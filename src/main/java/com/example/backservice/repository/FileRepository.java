package com.example.backservice.repository;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;

@Repository
public class FileRepository {


    public void refreshFiles(File dir, Path temp) {
        File[] listOfFiles = dir.listFiles();
        if (listOfFiles != null) {
            Arrays.stream(listOfFiles).forEach(file -> {
                try {
                    String nameFile = file.getName();
                    Files.move(file.toPath(), Paths.get(temp +"/"+ nameFile), ATOMIC_MOVE);
                    Thread.sleep(2);
                    Files.move(Paths.get(temp +"/"+ nameFile), Paths.get(dir.toPath() +"/"+ nameFile), ATOMIC_MOVE);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void deleteFileIfExists(File file) throws IOException {
        FileUtils.deleteDirectory(file);
    }

    public Path createDirectory(String path) throws IOException {
        if (!Files.exists(Paths.get(path))) {
            return Files.createDirectory(Paths.get(path));
        } else {
            return Paths.get(path);
        }
    }

    public Path createFile(String path) throws IOException {
        File file = new File(path);
        if (file.createNewFile());
        return file.toPath();
    }

    public boolean fileExists(String file){
        return Files.exists(Paths.get(file));
    }

    public String readFile(String fileName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        return new String(bytes);
    }
}
