public class Client {
    String name;
    String surname;
    String adress;
    String postalCode;
    String city;

    Client (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Client(String name, String surname, String adress, String postalCode, String city) {
        this(name, surname);
        this.surname = surname;
        this.adress = adress;
        this.postalCode = postalCode;
        this.city = city;
    }

    public void printUserData(String name, String surname, String adress, String postalCode, String city) {
        System.out.println("Dane użytkownika : " + name + ", " +
                surname + ", " + adress + ", " + postalCode + ", " + city);
    }
    public void printFullName() {
        System.out.println("Imie i nazwisko : " + name + " " + surname);
    }

}
