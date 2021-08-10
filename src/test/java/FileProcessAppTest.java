import com.nice.file_processor.models.CallEvent;
import com.nice.file_processor.models.EmotionEnum;
import com.nice.file_processor.models.EvenOddEnum;
import com.nice.file_processor.models.OutputEvent;
import com.nice.file_processor.services.rules_algorithms.InteractionRuleEmotionImp;
import com.nice.file_processor.services.rules_algorithms.InteractionRuleSegmentIdImp;
import org.junit.Assert;
import org.junit.Test;

public class FileProcessAppTest {
    CallEvent callEvent = CallEvent.builder().segmentId(12345L).timeDuration(4444).startTime("2020-10-28T17:13:36.000Z").build();
    OutputEvent outputEvent = new OutputEvent();

    @Test
    public void checkOdd() {
        InteractionRuleSegmentIdImp ruleSegmentIdImp = new InteractionRuleSegmentIdImp();
        ruleSegmentIdImp.applyRule(callEvent, outputEvent);
        Assert.assertEquals(outputEvent.getEvenOrOdd(), EvenOddEnum.ODD);
    }
    @Test
    public void checkEven() {
        InteractionRuleSegmentIdImp ruleSegmentIdImp = new InteractionRuleSegmentIdImp();
        callEvent.setSegmentId(5678L);
        ruleSegmentIdImp.applyRule(callEvent, outputEvent);
        Assert.assertEquals(outputEvent.getEvenOrOdd(), EvenOddEnum.EVEN);
    }
    @Test
    public void checkEmotion() {
        InteractionRuleEmotionImp emotionRule = new InteractionRuleEmotionImp();
        emotionRule.applyRule(callEvent, outputEvent);
        Assert.assertTrue(outputEvent.getEmotion()== EmotionEnum.NEGATIVE || outputEvent.getEmotion()== EmotionEnum.POSITIVE
                            || outputEvent.getEmotion()== EmotionEnum.NEUTRAL);
    }
}
