package tenaya.suomi.tampere.logistiikka.logistiikka;

import java.util.ArrayList;

/**
 * The Shoe class represents a shoe with various sizes
 */
public class Shoe {
    public ArrayList<Shoe> shoeArrayList = new ArrayList<>();
    public String name;
    public int one;
    public int oneH;
    public int two;
    public int twoH;
    public int three;
    public int threeH;
    public int four;
    public int fourH;
    public int five;
    public int fiveH;
    public int six;
    public int sixH;
    public int seven;
    public int sevenH;
    public int eight;
    public int eightH;
    public int nine;
    public int nineH;
    public int ten;
    public int tenH;
    public int eleven;
    public int elevenH;
    public int twelve;
    public int twelveH;
    public int thirteen;
    public int all;

    /**
     * Overrides the default toString method to provide a
     * formatted string representation of the Shoe object.
     *
     * @return A string representation of the Shoe object.
     */
    @Override
    public String toString() {
        return "Shoe{" +
                "name='" + name + '\'' +
                ", one=" + one +
                ", oneH=" + oneH +
                ", two=" + two +
                ", twoH=" + twoH +
                ", three=" + three +
                ", threeH=" + threeH +
                ", four=" + four +
                ", fourH=" + fourH +
                ", five=" + five +
                ", fiveH=" + fiveH +
                ", six=" + six +
                ", sixH=" + sixH +
                ", seven=" + seven +
                ", sevenH=" + sevenH +
                ", eight=" + eight +
                ", eightH=" + eightH +
                ", nine=" + nine +
                ", nineH=" + nineH +
                ", ten=" + ten +
                ", tenH=" + tenH +
                ", eleven=" + eleven +
                ", elevenH=" + elevenH +
                ", twelve=" + twelve +
                ", twelveH=" + twelveH +
                ", thirteen=" + thirteen +
                ", all=" + all +
                '}';
    }

    /**
     * Constructor for the Shoe class.
     *
     * @param name The name of the shoe model.
     */
    public Shoe(String name) {
        this.name = name;
        shoeArrayList.add(this);
    }

}
