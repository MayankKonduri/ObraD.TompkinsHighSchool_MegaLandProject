package Project.src;
import java.util.*;
import java.lang.*;

public class TreasureCardDeck {
    public ArrayList<TreasureCard> availableTreasure = new ArrayList<TreasureCard>();
    public ArrayList<TresureCard> trashedTreasure = new ArrayList<TreasureCard>();
    private final int  DECK_COUNT = 46;

    public String toString(){
        return availableTreasure.toString();
    }
        availableTreasure.add(new BuildingCards(14, "Bowling Alley", 3, false, true, bowlingAlley, 4));
        availableTreasure.add(new BuildingCards(15, "Smithy", 3, false, false, smithy, 4));
        availableTreasure.add(new BuildingCards(16, "Fish Vendor", 3, false, false, fishVendor, 4));
        availableTreasure.add(new BuildingCards(17, "Toll Booth", 3, false, false, tollBooth, 4));
        availableTreasure.add(new BuildingCards(18, "Soap Makers", 3, false, false, soapMakers, 4));
        availableTreasure.add(new BuildingCards(19, "Hall of Elders", 3, false, false, hallOfElders, 4));
        availableTreasure.add(new BuildingCards(20, "Lodge", 4, false, true, lodge, 4));
        availableTreasure.add(new BuildingCards(21, "Root Market", 4, false, false, rootMarket, 4));
        availableTreasure.add(new BuildingCards(22, "Endless Mine", 5, false, true, endlessMine, 4));
        availableTreasure.add(new BuildingCards(23, "Arena", 5, false, false, arena, 4));

}
