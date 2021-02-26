import android.widget.ImageView;

import com.example.minipets.Pet;
import com.example.minipets.R;

public class PetFakeDatabase {

    private Pet myPet;
    private String name;    // The name of the pet
    private int happiness;  // Represents how happy the pet is
    private String type;    // The type of pet (eg. cat or dog)
    private int outfit;     // Id representing the outfit the pet's wearing
    private boolean[] likedFoods;   // If the pet likes the food for some food id
    private ImageView reactionImg;  // ImageView which displays the pet's reaction
    private ImageView petImg;   // ImageView which displays the pet

    public final int MAX_FOODS = 3; // How many different types of food there is
    public final int MAX_HAPPINESS = 100;    // The max that the pet's happiness can go

    public void Pet(String newName, String newType, ImageView newReactionImg, ImageView newPetImg)
    {
        name = newName;
        happiness = MAX_HAPPINESS/2;
        type = newType;
        outfit = 0;
        likedFoods = new boolean[MAX_FOODS];
        reactionImg = newReactionImg;
        petImg = newPetImg;

        // Sets the image of the pet
        if(type.equals("Cat"))
            petImg.setImageResource(R.drawable.cat);
    }

    public ImageView seePet()
    {
        return this.petImg;
    }


}
