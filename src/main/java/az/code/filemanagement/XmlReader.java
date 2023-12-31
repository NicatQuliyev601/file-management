package az.code.filemanagement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class XmlReader {

    private static final List<Student> students = new ArrayList<>();


    public void convertXmlToJson(String xmlFileName, String jsonFileName) throws IOException {
        File xmlFile = new File(xmlFileName);

        XmlMapper xmlMapper = new XmlMapper();
        students.addAll(xmlMapper.readValue(xmlFile, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class)));

        students.sort(Comparator.comparing(Student::getName));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFileName), students);

        System.out.println("Data sorted by name and written to " + jsonFileName);
    }

    public void sortByAgeAndWriteToFile(String xmlInputFile, String jsonOutputFile) throws IOException {
        File xmlFile = new File(xmlInputFile);

        XmlMapper xmlMapper = new XmlMapper();
        students.clear();
        students.addAll(xmlMapper.readValue(xmlFile, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class)));

        students.sort(Comparator.comparingInt(Student::getAge));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonOutputFile), students);

        System.out.println("Data sorted by age and written to " + jsonOutputFile);
    }

    public void findBySpeciality(String xmlInputFile, String outputFile) throws IOException {
        File xmlFile = new File(xmlInputFile);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the speciality: ");
        String specialityToFind = scanner.next();

        XmlMapper xmlMapper = new XmlMapper();
        students.clear();
        students.addAll(xmlMapper.readValue(xmlFile, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class)));

        List<Student> matchedStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getSpeciality().equalsIgnoreCase(specialityToFind)) {
                matchedStudents.add(student);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(matchedStudents);

        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(json.getBytes());
            outputStream.flush();
        }

        System.out.println("Students with speciality '" + specialityToFind + "' written to " + outputFile);
    }
}
