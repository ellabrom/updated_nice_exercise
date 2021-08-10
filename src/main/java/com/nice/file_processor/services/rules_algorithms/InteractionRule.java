package com.nice.file_processor.services.rules_algorithms;

import com.nice.file_processor.models.CallEvent;
import com.nice.file_processor.models.OutputEvent;

public interface InteractionRule {
    void applyRule(CallEvent callEvent, OutputEvent outputEvent);
}
