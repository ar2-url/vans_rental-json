package command;

import car.CarRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class LoadFromFileCommand implements UserCommand {
    private final String fileName;
    private final CarRepository repository;

    @Override
    public void execute() {
        Path path = Paths.get(fileName);
        try {
            Stream<String> lines = Files.lines(path);

//            StringBuilder stringBuilder = new StringBuilder();
            String fileAsString = lines
//                    .peek(stringBuilder::append)
                    .collect(Collectors.joining());

//            System.out.println(stringBuilder.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            MyCars myCars = objectMapper.readValue(fileAsString, MyCars.class);
            


        } catch (IOException e) {
            System.out.println("File does not exit");
        }
        System.out.println();
    }


    @Setter
    @Getter
    public static class MyCars {
        Integer cars;

        @Override
        public String toString() {
            return "MyCars{" +
                    "cars=" + cars +
                    '}';
        }
    }
}
