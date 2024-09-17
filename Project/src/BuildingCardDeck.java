package Project.src;
import java.lang.*;
import java.util.*;

public class BuildingCardDeck {

    public ArrayList<BuildingCards> buildingDeck = new ArrayList<BuildingCards>();

    public BuildingCardDeck(){
        buildingDeck.add(new BuildingCards(5, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(6, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(7, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(8, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(9, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(10, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(11, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(12, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(13, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(14, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(15, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(16, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(17, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(18, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(19, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(20, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(21, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(22, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(23, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(24, "Sandwich Stand", 1, true, false));
        buildingDeck.add(new BuildingCards(25, "Sandwich Stand", 1, true, false));
    }


    public String toString(){
        return buildingDeck.toString();
    }
}


