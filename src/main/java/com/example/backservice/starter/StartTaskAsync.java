package com.example.backservice.starter;

import com.example.backservice.observable.DirectoryObservable;
import com.example.backservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class StartTaskAsync {

    @Autowired
    private FileService fileService;

    @Autowired
    private DirectoryObservable directoryObservable;

    @Async
    public void run(){
        try {
            fileService.prepareInitialProcessingToExistingsFiles();
            directoryObservable.initObservableInputPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
