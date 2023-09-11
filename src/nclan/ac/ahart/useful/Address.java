package nclan.ac.ahart.useful;

/**
 * Simple class to hold address details.
 */
public class Address {
    private int houseNumber;
    private String street;
    private String town;
    private String county;
    private String postcode;

    /**
     * Create a new instance of address. Assumes that every house has a number. No checks are made on postcode to check
     * for length, and good formatting.
     * @param houseNumber house number
     * @param street      street name
     * @param town        town
     * @param county      district or county
     * @param postcode    postcode or zip code. No checks are carried out on the format of supplied String.
     */
    public Address(int houseNumber, String street, String town, String county, String postcode) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.town = town;
        this.county = county;
        this.postcode = postcode;
    }

    /**
     * Return a single string showing the address
     * @return Formatted String showing full address.
     */
    @Override
    public String toString() {
        return houseNumber +" "+ street + ",\n" + town + ",\n" + county + ",\n" + postcode + ".";
    }
}
