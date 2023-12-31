package az.code.filemanagement;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.*;


public class ExcelReader {

    private final List<Student> students = new ArrayList<>();

    public void readExcel(String inputFile, String outputFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();


        for (int i = 1; i <= rows; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                Student student = new Student();
                student.setId((int) row.getCell(0).getNumericCellValue());
                student.setName(row.getCell(1).getStringCellValue());
                student.setSurname(row.getCell(2).getStringCellValue());
                student.setAge((int) row.getCell(3).getNumericCellValue());
                student.setSpeciality(row.getCell(4).getStringCellValue());

                students.add(student);
            }
        }

        workbook.close();

        List<Student> sortedList = students
                .stream()
                .sorted(Comparator.comparing(Student::getName))
                .toList();

        FileWriter fileWriter = new FileWriter(outputFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Student student : sortedList) {
            fileWriter.write(student.toString());
            printWriter.println();
        }

        printWriter.close();
        fileWriter.close();

        System.out.println("Data written to " + outputFile);
    }

    public void sortByAgeAndWriteToFile(String outputFile) throws IOException {
        List<Student> sortedByAgeList = new ArrayList<>(students);

        List<Student> compareAgeList = sortedByAgeList
                .stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .toList();

        FileWriter fileWriter = new FileWriter(outputFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Student student : compareAgeList) {
            fileWriter.write(student.toString());
            printWriter.println();
        }

        printWriter.close();
        fileWriter.close();

        System.out.println("Data written to " + outputFile);
    }

    public void findBySpeciality(String outputFile) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the speciality: ");
        String specialityToFind = scanner.next();
        scanner.close();

        List<Student> matchedStudents = new ArrayList<>();

        for (Student student : students) {
            if (student.getSpeciality().equalsIgnoreCase(specialityToFind)) {
                matchedStudents.add(student);
            }
        }

        FileWriter fileWriter = new FileWriter(outputFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Student student : matchedStudents) {
            fileWriter.write(student.toString());
            printWriter.println();
        }

        printWriter.close();
        fileWriter.close();

        System.out.println("Students with speciality '" + specialityToFind + "' written to " + outputFile);
    }
}
