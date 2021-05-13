package Algo;

import Function.Objectif;
import Type.Bin;
import Type.Info;

import java.util.ArrayList;

public class UnBinParItem {
    private int objectif;

    public UnBinParItem(Info info){
        info.setBins(new ArrayList<>());

        for (Integer i:info.getData()) {
            Bin bin = new Bin(info.getSize());
            bin.addData(i);
            info.addBin(bin);
        }

        Objectif o = new Objectif();
        this.objectif = o.getObjectif(info.getBins());
    }

    public int getObjectif() {
        return objectif;
    }
}
