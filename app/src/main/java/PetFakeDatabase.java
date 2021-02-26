import android.widget.ImageView;

import com.example.minipets.Pet;

public class PetFakeDatabase {



    public Pet newPet(String newName, String newType, ImageView newReactionImg, ImageView newPetImg)
    {
       return  new Pet("Jackie", "Cat", newReactionImg, newPetImg);
    }

    //public Pet getPet()
    {
        ///
    }


}
