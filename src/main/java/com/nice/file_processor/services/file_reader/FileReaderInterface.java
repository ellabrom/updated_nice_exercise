package com.nice.file_processor.services.file_reader;

import com.nice.file_processor.models.CallEvent;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;

public interface FileReaderInterface {
    BufferedReader openFile();
    List<CallEvent> readNextBulk(BufferedReader bf);
    void closeFile(BufferedReader bufferedReader );
}
