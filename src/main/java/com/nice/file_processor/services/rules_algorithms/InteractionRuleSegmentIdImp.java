package com.nice.file_processor.services.rules_algorithms;

import com.nice.file_processor.models.CallEvent;
import com.nice.file_processor.models.EvenOddEnum;
import com.nice.file_processor.models.OutputEvent;
import org.springframework.stereotype.Service;

@Service
public class InteractionRuleSegmentIdImp implements InteractionRule {
    @Override
    public void applyRule(CallEvent callEvent, OutputEvent outputEvent) {
        if (callEvent.getSegmentId() % 2 == 0) {
            outputEvent.setEvenOrOdd(EvenOddEnum.EVEN);
        } else {
            outputEvent.setEvenOrOdd(EvenOddEnum.ODD);
        }

    }
}
