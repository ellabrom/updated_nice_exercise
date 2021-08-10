package com.nice.file_processor.services.file_reader;

import com.nice.file_processor.models.CallEvent;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileReaderInterfaceCsvImp implements FileReaderInterface {
    @Value("${numberOfRecordsToRead}")
    int numberOfRecordsToRead;
    @Value("${inputFileName}")
    String inputFileName;

    // static int readRecords;
    @Override
    @SneakyThrows
    public List<CallEvent> readFile() {

        CSVReader reader =
                new CSVReaderBuilder(new FileReader(inputFileName)).
                        withSkipLines(1). // Skiping firstline as it is header
                        build();
        List<CallEvent> callEventsList = reader.readAll().stream().map(data -> {
            CallEvent callEvent = CallEvent.builder().segmentId(Long.valueOf(data[0]))
                    .timeDuration(Integer.valueOf(data[1]))
                    .startTime(data[2]).build();
            return callEvent;
        }).collect(Collectors.toList());
        return callEventsList;
    }
}

