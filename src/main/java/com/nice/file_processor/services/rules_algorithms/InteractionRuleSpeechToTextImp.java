package com.nice.file_processor.services.rules_algorithms;

import com.github.javafaker.Faker;
import com.nice.file_processor.models.CallEvent;
import com.nice.file_processor.models.OutputEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteractionRuleSpeechToTextImp implements InteractionRule{
    @Autowired
    private Faker faker;
    @Override
    public void applyRule(CallEvent callEvent, OutputEvent outputEvent) {
        outputEvent.setSpeechText(faker.backToTheFuture().quote());
    }
}
