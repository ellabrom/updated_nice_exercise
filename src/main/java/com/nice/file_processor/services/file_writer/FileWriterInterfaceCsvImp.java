package com.nice.file_processor.services.file_writer;

import com.nice.file_processor.models.EmotionEnum;
import com.nice.file_processor.models.EvenOddEnum;
import com.nice.file_processor.models.OutputEvent;
import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileWriterInterfaceCsvImp implements FileWriterInterface {
    @Value("${outputFileName}")
    String outputFileName;
    @Override
    @SneakyThrows
    public void writeFile(List<OutputEvent> outputEventList) {
        File file = new File(outputFileName);
        FileWriter outputfile = new FileWriter(file);
        CSVWriter csvWriter= new CSVWriter(outputfile);
        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[] { "segmentId", "emotion", "speechText", "evenOrOdd" });
        outputEventList
                .forEach(outputEvent->data.add((new String[] { outputEvent.getSegmentId().toString(), outputEvent.getEmotion().toString(),
                        outputEvent.getSpeechText(), outputEvent.getEvenOrOdd().toString() })));
        csvWriter.writeAll(data);
        csvWriter.close();

    }
}
