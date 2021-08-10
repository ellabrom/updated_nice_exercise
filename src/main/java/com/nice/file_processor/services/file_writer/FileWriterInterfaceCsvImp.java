package com.nice.file_processor.services.file_writer;

import com.nice.file_processor.models.OutputEvent;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileWriterInterfaceCsvImp implements FileWriterInterface {
    @Value("${outputFileName}")
    String outputFileNamePrefix;
    @Value("${delimiter}")
    String delimiter;
    @Override
    @SneakyThrows
    public void writeFile(List<OutputEvent> outputEventList, BufferedWriter writer) {
        List<String> outputString = outputEventList.stream().map(event -> event.getSegmentId().toString() + delimiter + event.getEmotion().toString() + delimiter + event.getSpeechText()
                + delimiter + event.getEvenOrOdd().toString()).collect(Collectors.toList() );
        for (String str : outputString) {
            writer.write(str);
            writer.newLine();
        }
        writer.flush();
    }

    @Override
    @SneakyThrows
    public void writeHeader(BufferedWriter writer) {
        writer.write("segmentId,emotion,speechText,evenOrOdd");
        writer.newLine();
        writer.flush();
    }

    @Override
    @SneakyThrows
    public BufferedWriter openFile() {
        String outputFileName = outputFileNamePrefix+System.currentTimeMillis()+".csv";
        return new BufferedWriter(new FileWriter(outputFileName, true));
    }

    @Override
    @SneakyThrows
    public void closeFile(BufferedWriter writer) {
        if (writer!=null) {
            writer.close();
        }
    }
}
