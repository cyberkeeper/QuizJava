package nclan.ac.ahart.useful;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class to hold generic person information. Uses a LocalDate type variable to hold the date of birth.
 *
 * @author ahart
 */
public class Person {
    private String firstName;
    private String surname;
    private LocalDate dateOfBirth;
    private Address address;

    /**
     * Basic constructor allows creation of a person using name only. Assumption is that the other
     * information required by the program will have it added using setter methods.
     *
     * @param firstName first name
     * @param surname   surname
     */
    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullname() {
        return firstName + " " + surname;
    }

    /**
     * Allow first name to be updated, in case of typo or name change.
     *
     * @param firstName person's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    /**
     * Allow surname to be updated, in case of marriage or typo
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Calculate and return the age of the person
     *
     * @return int value of the number of years from date of birth to now.
     */
    public int getAge() {

        Period difference = Period.between(dateOfBirth, LocalDate.now());
        return difference.getYears();
    }

    /**
     * Set the date of birth for the person.
     *
     * @param dateOfBirth Expects String in format "yyyy-MM-dd".
     * @throws Exception if the supplied string can not be parsed to a date then an exception is thrown and no date of
     *                   birth is set. If nothing supplied set date of birth to today.
     */
    public void setDateOfBirth(String dateOfBirth) throws Exception {
        //check if a date of birth string was supplied before trying to split it
        if (!dateOfBirth.isEmpty()) {
            String[] data = dateOfBirth.split("-");
            //check that there were three entries
            if (data.length == 3) {
                this.dateOfBirth = LocalDate.of(Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        Integer.parseInt(data[2]));
            } else {
                throw new Exception("Date of birth supplied in wrong format.");
            }
        } else {
            this.dateOfBirth = LocalDate.now();
        }
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Set address using an existing Address class instance.
     *
     * @param address Valid address object, no checks made to ensure it is correct.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Set address using discrete information
     *
     * @param houseNumber house number
     * @param street      street name
     * @param town        town
     * @param county      district or county
     * @param postcode    postcode or zip code. No checks are carried out on the format of supplied String.
     */
    public void setAddress(int houseNumber, String street, String town, String county, String postcode) {
        this.address = new Address(houseNumber, street, town, county, postcode);
    }

    public String toString() {
        return firstName + " " + surname;
    }
}
