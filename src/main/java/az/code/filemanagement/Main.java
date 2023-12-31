package az.code.filemanagement;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final ExcelReader excelReader = new ExcelReader();
    private static final JsonReader jsonReader = new JsonReader();
    private static final XmlReader xmlReader = new XmlReader();



    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println("Choose input file format:" +
                    "\n1.json" +
                    "\n2.xml " +
                    "\n3.excel" +
                    "\n4.Exit");
            int inputFileFormat = scan.nextInt();

            switch (inputFileFormat) {
                case 1:
                    System.out.println("What operation do you want to do?" +
                            "\n1.Read json file and sort by name in new xml file" +
                            "\n2.Compare by age" +
                            "\n3.Search by speciality" +
                            "\n4.exit");
                    int chooseJson = scan.nextInt();
                    if (chooseJson == 1) {
                        jsonReader.ReadJsonToXml("student.json", "students.xml");
                        break;
                    } else if (chooseJson == 2) {
                        jsonReader.sortByAgeAndWriteToFile("sort_by_age.xml");
                        break;
                    } else if ((chooseJson == 3)) {
                        jsonReader.findBySpeciality("find_by_speciality.xml");
                    } else if (chooseJson == 4) {
                        break;
                    } else {
                        System.out.println("Wrong choose.Please Choose Correctly");
                    }

                    break;
                case 2:
                    System.out.println("What operation do you want to do?" +
                            "\n1.Read xml file and sort by name in new json file" +
                            "\n2.Compare by age" +
                            "\n3.Search by speciality" +
                            "\n4.exit");
                    int chooseXml = scan.nextInt();
                    if (chooseXml == 1) {
                        xmlReader.convertXmlToJson("student.xml", "students.json");
                        break;
                    } else if (chooseXml == 2) {
                        xmlReader.sortByAgeAndWriteToFile("student.xml", "sort_by_age.json");
                        break;
                    } else if (chooseXml == 3) {
                        xmlReader.findBySpeciality("student.xml", "find_by_speciality.json");
                        break;
                    } else if (chooseXml == 4) {
                        break;
                    } else {
                        System.out.println("Wrong choose.Please Choose Correctly");
                    }
                    break;
                case 3:
                    System.out.println("What operation do you want to do?" +
                            "\n1.Read excel file and sort by name in new file?" +
                            "\n2.Compare by age?" +
                            "\n3.Search by speciality" +
                            "\n4.exit");
                    int choose = scan.nextInt();
                    if (choose == 1) {
                        excelReader.readExcel("students.xlsx", "students.txt");
                    } else if (choose == 2) {
                        excelReader.sortByAgeAndWriteToFile("age.txt");
                    } else if (choose == 3) {
                        excelReader.findBySpeciality("speciality.txt");
                    } else if (choose == 4) {
                        break;
                    } else {
                        System.out.println("Wrong choose.Please Choose Correctly");
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Wrong input file format.");
            }
        }
    }
}