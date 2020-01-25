package Lab2;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

abstract class Contact{

    private int year;
    private int month;
    private int day;

    public Contact(String date){
        this.year = Integer.parseInt(date.substring(0,4));
        this.month = Integer.parseInt(date.substring(5,7));
        this.day = Integer.parseInt(date.substring(8,10));
    }

    public boolean isNewerThan(Contact c){
        if(this.year > c.getYear()){
            return true;
        }else if(this.year == c.getYear()){
            if(this.month > c.getMonth()){
                return true;
            }else if(this.month == c.getMonth()){
                if(this.day > c.getDay()){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public int getYear(){
        return this.year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public String getType(){
        return "";
    }

}

class EmailContact extends Contact{

    private String email;

    public EmailContact(String date,String email) {
        super(date);
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String getType(){
        return "Email";
    }
}

class PhoneContact extends Contact {

    enum Operator { VIP, ONE, TMOBILE }
    private String phone;

    public PhoneContact(String date,String phone) {
        super(date);
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public Operator getOperator(){
        int temp = Integer.parseInt(phone.substring(2,3));
        if (temp == 7 || temp == 8) {
            return Operator.values()[0];
        }else if(temp == 5 || temp == 6){
            return Operator.values()[1];
        }else
            return Operator.values()[2];
    }

    @Override
    public String getType(){
        return "Phone";
    }

}

class Student{

    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;
    private Contact[] listContacts;

    public Student(String firstName,String lastName,String city,int age,long index){
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        listContacts = new Contact[0];
    }

    public void addEmailContact(String date,String email){
        EmailContact emailContact = new EmailContact(date,email);
        Contact[] newList = new Contact[listContacts.length+1];
        for (int i = 0; i < listContacts.length; i++) {
            newList[i] = listContacts[i];
        }
        newList[listContacts.length] = emailContact;
        this.listContacts = newList;
    }

    public void addPhoneContact(String date,String phone){
        PhoneContact phoneContact = new PhoneContact(date,phone);
        Contact[] newList = new Contact[listContacts.length+1];
        for (int i = 0; i < listContacts.length; i++) {
            newList[i] = listContacts[i];
        }
        newList[listContacts.length] = phoneContact;
        this.listContacts = newList;
    }

    public Contact[] getListContacts(){
        return listContacts;
    }

    public Contact[] getEmailContacts(){
        List<Contact> newList = new ArrayList<>();
        for (int i = 0; i < listContacts.length; i++) {
            if(listContacts[i].getType().equals("Email")){
                newList.add(listContacts[i]);
            }
        }
        Contact[] toReturn = new Contact[newList.size()];
        int count = 0;
        for(Contact temp : newList){
            toReturn[count] = temp;
            count++;
        }
        return toReturn;
    }

    public Contact[] getPhoneContacts(){
        List<Contact> newList = new ArrayList<>();
        for (int i = 0; i < listContacts.length; i++) {
            if(listContacts[i].getType().equals("Phone")){
                newList.add(listContacts[i]);
            }
        }
        Contact[] toReturn = new Contact[newList.size()];
        int count = 0;
        for(Contact temp : newList){
            toReturn[count] = temp;
            count++;
        }
        return toReturn;
    }

    public String getCity(){
        return city;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public long getIndex(){
        return index;
    }

    public Contact getLatestContact(){
        Contact temp = listContacts[0];
        for (int i = 1; i < listContacts.length; i++) {
            if(listContacts[i].isNewerThan(temp)){
                temp = listContacts[i];
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("{\"ime\":\"");
        st.append(firstName + "\", \"prezime\":\"");
        st.append(lastName + "\", \"vozrast\":" );
        st.append(age + ", \"grad\":\"");
        st.append(city + "\", \"indeks\":" );
        st.append(index + ", \"telefonskiKontakti\":[");
        Contact[] tellContacts = getPhoneContacts();
        for (int i = 0; i < tellContacts.length; i++) {
            PhoneContact temp = (PhoneContact)tellContacts[i];
            if(i != tellContacts.length -1){
                st.append("\"" + temp.getPhone() + "\", ");
            }else{
                st.append("\"" + temp.getPhone() + "\"");
            }
        }
        st.append("], \"emailKontakti\":[");
        Contact[] emailContacts = getEmailContacts();
        for (int i = 0; i < emailContacts.length; i++) {
            EmailContact temp = (EmailContact)emailContacts[i];
            if(i != emailContacts.length -1){
                st.append("\"" + temp.getEmail() + "\", ");
            }else{
                st.append("\"" + temp.getEmail() + "\"");
            }
        }
        st.append("]}");
        return st.toString();
    }
}

class Faculty{

    private String name;
    private Student[] students;

    public Faculty(String name,Student[] students){
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName){
        int counter = 0;
        for (int i = 0; i < students.length; i++) {
            if(students[i].getCity().equals(cityName)){
                counter++;
            }
        }
        return counter;
    }

    public Student getStudent(long index){
        for (int i = 0; i < students.length; i++) {
            if(students[i].getIndex() == index){
                return students[i];
            }
        }
        return null;
    }

    public double getAverageNumberOfContacts(){
        int sum = 0;
        for (int i = 0; i < students.length; i++) {
            sum += students[i].getListContacts().length;
        }
        return (float)sum / (float)students.length;
    }

    public Student getStudentWithMostContacts(){
        Student toReturn = students[0];
        int max = students[0].getListContacts().length;
        long tmpIndex = students[0].getIndex();
        for (int i = 0; i < students.length; i++) {
            if(students[i].getListContacts().length > max){
                toReturn = students[i];
                max = students[i].getListContacts().length;
            }else if(students[i].getListContacts().length == max){
                if(students[i].getIndex() > tmpIndex){
                    toReturn = students[i];
                    max = students[i].getListContacts().length;
                    tmpIndex = students[i].getIndex();
                }
            }
        }
        return toReturn;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("{\"fakultet\":\"" + name + "\", \"studenti\":[");
        for (int i = 0; i < students.length; i++) {
            if(i != students.length - 1) {
                st.append(students[i] + ", ");
            }else{
                st.append(students[i]);
            }
        }
        st.append("]}");
        return st.toString();
    }
}


public class ContactsTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getType().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getType().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().length > 0
                            && faculty.getStudent(rindex).getPhoneContacts().length > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().length
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().length);

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().length;
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().length;

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts()[posEmail].isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts()[posPhone]));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}

