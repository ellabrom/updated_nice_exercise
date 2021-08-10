package com.nice.file_processor.controllers;

import com.nice.file_processor.models.CallEvent;
import com.nice.file_processor.models.OutputEvent;
import com.nice.file_processor.services.file_reader.FileReaderInterface;
import com.nice.file_processor.services.file_writer.FileWriterInterface;
import com.nice.file_processor.services.rules_algorithms.InteractionRule;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlowControllerImp implements FlowController {
    @Autowired
    FileReaderInterface fileReader;
    @Autowired
    FileWriterInterface fileWriter;
    @Autowired
    List<InteractionRule> interactionRuleList;
    @Value("${numberOfRecordsToRead}")
    int numberOfRecordsToRead;

    @Override
    @SneakyThrows
    public void controlFlow() {
        BufferedReader bufferedReader = fileReader.openFile();
        if (bufferedReader.readLine() != null) {
            BufferedWriter bufferedWriter = fileWriter.openFile();
            fileWriter.writeHeader(bufferedWriter);
            proceedData(bufferedReader, bufferedWriter);
            fileWriter.closeFile(bufferedWriter);
        }
        fileReader.closeFile(bufferedReader);
    }

    private void proceedData(BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        int lastReadArraySize = numberOfRecordsToRead;
        while (lastReadArraySize >= numberOfRecordsToRead) {
            List<CallEvent> callEvents = fileReader.readNextBulk(bufferedReader);
            if (callEvents != null && callEvents.size() > 0) {
                lastReadArraySize = callEvents.size();
                List<OutputEvent> outputEvents = new ArrayList<>();
                callEvents.forEach(callEvent -> {OutputEvent outputEvent = new OutputEvent();
                    outputEvent.setSegmentId(callEvent.getSegmentId());
                    interactionRuleList.forEach(rule -> rule.applyRule(callEvent, outputEvent));
                    outputEvents.add(outputEvent);
                });
                fileWriter.writeFile(outputEvents, bufferedWriter);
            }
            else {
                lastReadArraySize = 0;
            }
        }
    }
}
