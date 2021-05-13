package Type;

import java.util.ArrayList;
import java.util.List;

public class Info {
    private int size;
    private int nbItem;
    private List<Integer> data;
    private int nbBin;
    private List<Bin> bins;

    public Info(){
        this.size = 0;
        this.nbItem = 0;
        this.data = new ArrayList<>();
        this.nbBin = 0;
        this.bins = new ArrayList<>();
    }

    public void addData(Integer data){
        this.data.add(data);
    }

    public void addBin(Bin bin) {
        this.bins.add(bin);
        this.nbBin++;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNbItem() {
        return nbItem;
    }

    public void setNbItem(int nbItem) {
        this.nbItem = nbItem;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public int getNbBin() {
        return nbBin;
    }

    public void setNbBin(int nbBin) {
        this.nbBin = nbBin;
    }

    public List<Bin> getBins() {
        return bins;
    }

    public void setBins(List<Bin> bins) {
        this.bins = bins;
    }
}
