package Project.src;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class BuildingCardDeck extends Project.src.BuildingCards {


    public Project.src.BuildingCards o5;
    public ArrayList<Project.src.BuildingCards> availableBuildings = new ArrayList<BuildingCards>();

    //need 23 times
    public BuildingCardDeck() {


        BufferedImage image5 = null;
        BufferedImage image6 = null;
        BufferedImage image7 = null;
        BufferedImage image8 = null;
        BufferedImage image9 = null;
        BufferedImage image12 = null;
        BufferedImage image13 = null;
        BufferedImage image14 = null;
        BufferedImage image15 = null;
        BufferedImage image17 = null;
        BufferedImage image16 = null;
        BufferedImage image19 = null;
        BufferedImage image20 = null;
        BufferedImage image21 = null;
        BufferedImage image22 = null;
        BufferedImage image23 = null;
        BufferedImage image26 = null;
        BufferedImage image27 = null;
        BufferedImage image28 = null;
        BufferedImage image29 = null;
        BufferedImage image18 = null;
        BufferedImage image24 = null;
        // need to scan BufferedImage image 10 = null;
        try {
            image5 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0005.jpg")));
            image6 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0006.jpg")));
            image7 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0007.jpg")));
            image8 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0008.jpg")));
            image9 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0009.jpg")));
            //need to scan image10 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0009.jpg")));
            image12 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0012.jpg")));
            image13 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0013.jpg")));
            image14 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0014.jpg")));
            image15 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0015.jpg")));
            image16 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0016.jpg")));
            image17 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0017.jpg")));
            image18 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0018.jpg")));
            image19 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0019.jpg")));
            image20 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0020.jpg")));
            image21 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0021.jpg")));
            image22 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0022.jpg")));
            image23 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0023.jpg")));
            image24 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0024.jpg")));
            image26 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0026.jpg")));
            image27 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0028.jpg")));
            image28 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0028.jpg")));
            image29 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0029.jpg")));


        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
    }
}


       /*


       availableBuildings.add((5, "SANDWICH STAND", image5, 1, false));
       availableBuildings.add(6, "CAFE", image6, 2, false);
       availableBuildings.add(7, "ARCADE", image7, 3, false);
       availableBuildings.add(8, "BAZAAR OF ODDITIES", image8, 4, false);
       availableBuildings.add(9, "HOTEL", image9, 5, false);

        */
