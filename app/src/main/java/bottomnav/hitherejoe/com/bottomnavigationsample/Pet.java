package bottomnav.hitherejoe.com.bottomnavigationsample;

/**
 * Created by zainabiftikharmalhi on 2017-06-17.
 */

public class Pet {

    private String PetID;
    private String Name;
    private String Breed;
    private String Owner;
    private String Sex;
    private String Born;
    private String Weight;
    private String Feeding;

    public Pet()
    {

    }


    public Pet(String petID,String name, String breed, String owner, String sex, String born, String weight, String feeding) {

        PetID = petID;
        Name = name;
        Breed = breed;
        Owner = owner;
        Sex = sex;
        Born = born;
        Weight = weight;
        Feeding = feeding;
    }


    public Pet(String name, String breed, String owner, String sex, String born, String weight, String feeding) {

        Name = name;
        Breed = breed;
        Owner = owner;
        Sex = sex;
        Born = born;
        Weight = weight;
        Feeding = feeding;
    }
    public String getPetID() {
        return PetID;
    }

    public void setPetID(String petID) {
        PetID = petID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getBorn() {
        return Born;
    }

    public void setBorn(String born) {
        Born = born;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getFeeding() {
        return Feeding;
    }

    public void setFeeding(String feeding) {
        Feeding = feeding;
    }

}
