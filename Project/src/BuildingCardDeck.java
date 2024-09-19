package Project.src;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.*;
import java.util.*;

public class BuildingCardDeck {

    public static ArrayList<BuildingCards> buildingDeck = new ArrayList<BuildingCards>();

    private BufferedImage personalCard, heartCard, starCardBackground,
            sandwichStand, cafe, arcade, bazaarOfOddities, hotel, templeOfZoz,
            buildingCardBackground, reptileStable, herbHut, ostrichRanch, gym, hospital, laboratory, fishingPond, bowlingAlley, smithy, fishVendor, tollBooth,
            soapMakers, hallOfElders, lodge, rootMarket, endlessMine, arena,
            backOfLevelCard, levelCard31, levelCard32, levelCard33, levelCard34, levelCard35, levelCard36, levelCard37, levelCard38, levelCard39, levelCard40,
            treasureCardBackground, gear, cube, egg, carrot, mineral, fish,
            coin1, coin5, coin10, firstPlayerToken, heart, jump, indianWoman, gandalf, cat, frog, white, playerLevelCard;

    public BuildingCardDeck(){

        try {
            personalCard = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0002.jpg")));
            heartCard = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0003.jpg")));
            starCardBackground = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0004.jpg")));
            sandwichStand = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0005.jpg")));
            cafe = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0006.jpg")));
            arcade = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0007.jpg")));
            bazaarOfOddities = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0008.jpg")));
            hotel = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0009.jpg")));
            templeOfZoz = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0010.png")));
            buildingCardBackground = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0011.jpg")));
            reptileStable = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0012.jpg")));
            herbHut = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0013.jpg")));
            ostrichRanch = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0014.jpg")));
            gym = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0015.jpg")));
            hospital = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0016.jpg")));
            laboratory = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0017.jpg")));
            fishingPond = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0018.jpg")));
            bowlingAlley = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0019.jpg")));
            smithy = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0020.jpg")));
            fishVendor = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0021.jpg")));
            tollBooth = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0022.jpg")));
            soapMakers = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0023.jpg")));
            hallOfElders = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0024.jpg")));
            //skip 25
            lodge = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0026.jpg")));
            rootMarket = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0027.jpg")));
            endlessMine = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0028.jpg")));
            arena = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0029.jpg")));
            backOfLevelCard = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0030.jpg")));
            levelCard31 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0031.jpg")));
            levelCard32 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0032.jpg")));
            levelCard33 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0033.jpg")));
            levelCard34 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0034.jpg")));
            levelCard35 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0035.jpg")));
            levelCard36 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0036.jpg")));
            levelCard37 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0037.jpg")));
            levelCard38 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0038.jpg")));
            levelCard39 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0039.jpg")));
            levelCard40 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0040.jpg")));

            treasureCardBackground = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0041.jpg")));
            gear = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0042.jpg")));
            cube = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0043.jpg")));
            egg = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0044.jpg")));
            carrot = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0045.jpg")));
            mineral = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0046.jpg")));
            fish = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0047.jpg")));

            coin1 = ImageIO.read((new File("Project\\src\\Images\\MegaLand_1_Coin.png")));
            coin5 = ImageIO.read((new File("Project\\src\\Images\\MegaLand_5_Coin.png")));
            coin10 = ImageIO.read((new File("Project\\src\\Images\\MegaLand_10_Coin.png")));
            firstPlayerToken = ImageIO.read((new File("Project\\src\\Images\\MegaLand_1stPlayerToken.png")));
            heart = ImageIO.read((new File("Project\\src\\Images\\MegaLand_HeartToken.png")));
            jump = ImageIO.read((new File("Project\\src\\Images\\MegaLand_JumpToken.png")));

            indianWoman = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player1.png")));
            gandalf = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player2.png")));
            cat = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player3.png")));
            frog = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player4.png")));
            white = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player5.png")));

            playerLevelCard = ImageIO.read((new File("Project\\src\\Images\\MegaLand_PlayerCard.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        buildingDeck.add(new BuildingCards(1, "Sandwich Stand", 1, true, false, sandwichStand, 4));
        buildingDeck.add(new BuildingCards(2, "Cafe", 2, true, false, cafe, 4));
        buildingDeck.add(new BuildingCards(3, "Arcade", 3, true, false, arcade, 4));
        buildingDeck.add(new BuildingCards(4, "Bazaar of Oddities", 4, true, false, bazaarOfOddities, 4));
        buildingDeck.add(new BuildingCards(5, "Hotel", 5, true, false, hotel, 4));
        buildingDeck.add(new BuildingCards(6, "Temple of Zoz", 6, true, false, templeOfZoz, 4));
        buildingDeck.add(new BuildingCards(7, "Reptile Stable", 1, true, false, reptileStable, 4));
        buildingDeck.add(new BuildingCards(8, "Herb Hut", 1, false, false, herbHut, 4));
        buildingDeck.add(new BuildingCards(9, "Ostrich Ranch", 2, false, false, ostrichRanch, 4));
        buildingDeck.add(new BuildingCards(10, "Gym", 2, false, false, gym, 4));
        buildingDeck.add(new BuildingCards(11, "Hospital", 2, false, false, hospital, 4));
        buildingDeck.add(new BuildingCards(12, "Laboratory", 3, false, false, laboratory, 4));
        buildingDeck.add(new BuildingCards(13, "Fishing Pond", 3, false, true, fishingPond, 4));
        buildingDeck.add(new BuildingCards(14, "Bowling Alley", 3, false, true, bowlingAlley, 4));
        buildingDeck.add(new BuildingCards(15, "Smithy", 3, false, false, smithy, 4));
        buildingDeck.add(new BuildingCards(16, "Fish Vendor", 3, false, false, fishVendor, 4));
        buildingDeck.add(new BuildingCards(17, "Toll Booth", 3, false, false, tollBooth, 4));
        buildingDeck.add(new BuildingCards(18, "Soap Makers", 3, false, false, soapMakers, 4));
        buildingDeck.add(new BuildingCards(19, "Hall of Elders", 3, false, false, hallOfElders, 4));
        buildingDeck.add(new BuildingCards(20, "Lodge", 4, false, true, lodge, 4));
        buildingDeck.add(new BuildingCards(21, "Root Market", 4, false, false, rootMarket, 4));
        buildingDeck.add(new BuildingCards(22, "Endless Mine", 5, false, true, endlessMine, 4));
        buildingDeck.add(new BuildingCards(23, "Arena", 5, false, false, arena, 4));



    }

    public String toString(){
        return buildingDeck.toString();
    }
    public static ArrayList<BuildingCards> getBuildingDeck() {
        return buildingDeck;
    }
}


