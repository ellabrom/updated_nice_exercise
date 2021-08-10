package com.nice.file_processor.controllers;

import com.nice.file_processor.models.CallEvent;
import com.nice.file_processor.models.OutputEvent;
import com.nice.file_processor.services.file_reader.FileReaderInterface;
import com.nice.file_processor.services.file_writer.FileWriterInterface;
import com.nice.file_processor.services.rules_algorithms.InteractionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FlowControllerImp implements FlowController{
    @Autowired
    FileReaderInterface fileReader;
    @Autowired
    FileWriterInterface fileWriter;
    @Autowired
    List<InteractionRule> interactionRuleList;
    @Override
    public void controlFlow() {
        List<CallEvent> callEvents = fileReader.readFile();
        List<OutputEvent> outputEvents = new ArrayList<>();

        for (CallEvent callEvent : callEvents) {
            OutputEvent outputEvent = new OutputEvent();
            outputEvent.setSegmentId(callEvent.getSegmentId());
            interactionRuleList.forEach(rule->rule.applyRule(callEvent,outputEvent));
            outputEvents.add(outputEvent);
        }
        fileWriter.writeFile(outputEvents);
    }
}
