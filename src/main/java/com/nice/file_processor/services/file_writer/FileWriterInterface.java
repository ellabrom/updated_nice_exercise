package com.nice.file_processor.services.file_writer;

import com.nice.file_processor.models.OutputEvent;

import java.util.List;

public interface FileWriterInterface {
    void writeFile( List<OutputEvent> outputEventList);
}
