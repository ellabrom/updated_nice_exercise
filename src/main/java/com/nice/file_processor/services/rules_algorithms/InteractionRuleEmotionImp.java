package com.nice.file_processor.services.rules_algorithms;

import com.nice.file_processor.models.CallEvent;
import com.nice.file_processor.models.EmotionEnum;
import com.nice.file_processor.models.OutputEvent;
import com.nice.file_processor.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class InteractionRuleEmotionImp implements InteractionRule{
    @Override
    public void applyRule(CallEvent callEvent, OutputEvent outputEvent) {
        EmotionEnum emotionEnum = Utils.randomEnum(EmotionEnum.class);
        outputEvent.setEmotion(emotionEnum);
    }
}
