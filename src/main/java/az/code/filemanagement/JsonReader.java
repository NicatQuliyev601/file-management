package az.code.filemanagement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class JsonReader {

    private static final List<Student> students = new ArrayList<>();

    public void ReadJsonToXml(String jsonFileName, String xmlFileName) throws IOException {
        File jsonFile = new File(jsonFileName);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);

        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                Student student = objectMapper.treeToValue(node, Student.class);
                students.add(student);
            }

            students.sort(Comparator.comparing(Student::getName));

            XmlMapper xmlMapper = new XmlMapper();
            String xmlString = xmlMapper.writeValueAsString(students);

            try (OutputStream outputStream = new FileOutputStream(xmlFileName)) {
                outputStream.write(xmlString.getBytes());
                outputStream.flush();
            }

            System.out.println("Data written to " + xmlFileName);
        }
    }

    public void sortByAgeAndWriteToFile(String outputFile) throws IOException {
        List<Student> sortedByAgeList = new ArrayList<>(students);

        sortedByAgeList.sort(Comparator.comparingInt(Student::getAge));

        XmlMapper xmlMapper = new XmlMapper();
        String xmlString = xmlMapper.writeValueAsString(students);

        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(xmlString.getBytes());
            outputStream.flush();
        }
        System.out.println("Data written to " + outputFile);
    }


    public void findBySpeciality(String outputFile) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the speciality: ");
        String specialityToFind = scanner.next();

        List<Student> matchedStudents = new ArrayList<>();

        for (Student student : students) {
            if (student.getSpeciality().equalsIgnoreCase(specialityToFind)) {
                matchedStudents.add(student);
            }
        }

        XmlMapper xmlMapper = new XmlMapper();
        String xmlString = xmlMapper.writeValueAsString(matchedStudents);

        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(xmlString.getBytes());
            outputStream.flush();
        }

        System.out.println("Students with speciality '" + specialityToFind + "' written to " + outputFile);

    }
}
