package com.softuni.json_exercise.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public enum Utils {
    ;
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void writeJsonIntoFile(List<?> objects, Path filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath.toFile());

        GSON.toJson(objects, fileWriter);

        fileWriter.flush();

        fileWriter.close();
    }

    public static <T> void writeXmlIntoFile(T data, Path filePath) throws IOException, JAXBException {

        final File file = filePath.toFile();

        final JAXBContext context = JAXBContext.newInstance(data.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(data, file);

    }

    public static void writeJsonIntoFile(Object object, Path filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath.toFile());

        GSON.toJson(object, fileWriter);

        fileWriter.flush();

        fileWriter.close();
    }
}
