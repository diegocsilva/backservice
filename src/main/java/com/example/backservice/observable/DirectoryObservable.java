package com.example.backservice.observable;

import com.example.backservice.service.FileService;
import de.helmbold.rxfilewatcher.PathObservables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;

import static com.example.backservice.properties.InitialProperties.EXTENSION_FILE_INPUT;
import static com.example.backservice.properties.InitialProperties.INPUT_DIR;

@Component
public class DirectoryObservable {

    @Autowired
    private FileService fileService;

    public void initObservableInputPath() throws IOException {
        PathObservables.watchNonRecursive(Paths.get(INPUT_DIR))
                .filter(event -> StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())
                        && event.context().toString().endsWith(EXTENSION_FILE_INPUT))
                .subscribe(
                        event -> fileService.readFile(event.context().toString()),
                        Throwable::printStackTrace)
                .dispose();
    }
}
