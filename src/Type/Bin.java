package Type;

import java.util.ArrayList;
import java.util.List;

public class Bin implements Cloneable{
    private int size;
    private List<Integer> data;
    private int nbBin;
    private int sizeAvailable;

    public Bin(int size){
        this.size = size;
        this.data = new ArrayList<>();
        this.nbBin = 0;
        this.sizeAvailable = size;
    }

    public void addData(int size) {
        this.data.add(size);
        this.nbBin ++;
        this.sizeAvailable -= size;
    }

    public void removeData(int itemNumber) {
        this.nbBin --;
        this.sizeAvailable += this.data.get(itemNumber);
        this.data.remove(itemNumber);
    }

    public int getSize() {
        return size;
    }

    public List<Integer> getData() {
        return data;
    }

    public int getNbBin() {
        return nbBin;
    }

    public int getSizeAvailable() {
        return sizeAvailable;
    }

}
