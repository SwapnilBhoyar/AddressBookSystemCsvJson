import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AddressBookIO {
    public static String File = "AddressBook.txt";
    public static String MyCSV = "AddressBook.csv";

    public void writeData(List<ContactDetail> contactsList) {
        StringBuffer buffer = new StringBuffer();
        contactsList.forEach(contact -> {
            String contactData = contact.toString().concat("\n");
            buffer.append(contactData);
        });
        try {
            Files.write(Paths.get(File), buffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        try {
            Files.lines(new File("AddressBook.txt").toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void readContactFromCSV() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(MyCSV));
            CsvToBeanBuilder
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void writeContactToCSV(List<ContactDetail> contactDetailList) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(MyCSV));
            StatefulBeanToCsv<ContactDetail> statefulBeanToCsv = new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            statefulBeanToCsv.write(contactDetailList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readContactFromCsv() {
        try {
            Files.lines(new File("AddressBook.csv").toPath()).forEach(System.out::println);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException{
        AddressBookIO addressBookIO = new AddressBookIO();
        ContactDetail[] arrayOfContact= {
                new ContactDetail("swapnil", "bhoyar", "camp", "pune", "maharashtra", "411001", "1234567890", "swapnil@gmail.com") ,
                new ContactDetail("aditya", "bhosale", "katraj", "pune", "maharashtra", "411111", "0987654321", "aditya@gmail.com")
        };

        addressBookIO.writeData(Arrays.asList(arrayOfContact));
        addressBookIO.writeContactToCSV(Arrays.asList(arrayOfContact));
        addressBookIO.readContactFromCsv();
        addressBookIO.printData();
    }
}
