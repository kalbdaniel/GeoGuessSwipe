package nl.hva.dka.geoguessswipe;

public class GeoImage {
    private String name;
    private int id;

    public GeoImage(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final String[] IMAGE_NAMES = {
            "Y_Denmark",
            "N_Canada",
            "N_Bangladesh",
            "N_Kazachstan",
            "N_Colombia",
            "Y_Poland",
            "Y_Malta",
            "N_Thailand"
    };

    public static final int[] IMAGE_IDS = {
            R.drawable.img1_yes_denmark,
            R.drawable.img2_no_canada,
            R.drawable.img3_no_bangladesh,
            R.drawable.img4_yes_kazachstan,
            R.drawable.img5_no_colombia,
            R.drawable.img6_yes_poland,
            R.drawable.img7_yes_malta,
            R.drawable.img8_no_thailand
    };
}
