package bottomnav.hitherejoe.com.bottomnavigationsample;

/**
 * Created by zainabiftikharmalhi on 2017-06-17.
 */

public class PetWeight {
    private String petID;
    private String date;
    private String weight;

    public PetWeight(String petID, String date, String weight) {
        this.petID = petID;
        this.date = date;
        this.weight = weight;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
