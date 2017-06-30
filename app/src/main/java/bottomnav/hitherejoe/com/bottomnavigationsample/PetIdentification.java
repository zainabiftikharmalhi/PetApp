package bottomnav.hitherejoe.com.bottomnavigationsample;

/**
 * Created by zainabiftikharmalhi on 2017-06-17.
 */

public class PetIdentification {
    private String petID;
    private String provider;
    private String description;

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PetIdentification(String petID, String provider, String description) {

        this.petID = petID;
        this.provider = provider;
        this.description = description;
    }
}
