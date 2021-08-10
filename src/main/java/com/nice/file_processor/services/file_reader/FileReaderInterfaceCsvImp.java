package com.nice.file_processor.services.file_reader;

import com.nice.file_processor.models.CallEvent;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileReaderInterfaceCsvImp implements FileReaderInterface {
    @Value("${numberOfRecordsToRead}")
    int numberOfRecordsToRead;
    @Value("${inputFileName}")
    String inputFileName;
    @Value("${delimiter}")
    String delimiter;

    @Override
    @SneakyThrows
    public List<CallEvent> readNextBulk(BufferedReader bufferedReader) {
        String line;
        int counter = 0;
        String[] readArray;
        List<CallEvent> callEventsList = new ArrayList<>();
        while (counter < numberOfRecordsToRead && (line = bufferedReader.readLine()) != null) {
            counter++;
            readArray = line.split(delimiter);
            callEventsList.add(convertStringToObject(readArray));
        }
        return callEventsList;
    }

    private CallEvent convertStringToObject(String[] readArray) {
        return CallEvent.builder().segmentId(Long.valueOf(readArray[0]))
                .timeDuration(Integer.valueOf(readArray[1]))
                .startTime(readArray[2]).build();
    }

    @SneakyThrows
    @Override
    public BufferedReader openFile() {
        File file = new File(inputFileName);
        FileReader fileReader = new FileReader(file);
        return new BufferedReader(fileReader);
    }

    @SneakyThrows
    @Override
    public void closeFile(BufferedReader bufferedReader) {
        if (bufferedReader!= null) {
            bufferedReader.close();
        }
    }
}

