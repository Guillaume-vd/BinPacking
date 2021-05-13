package Algo;

import Function.CompareDecroissant;
import Function.Objectif;
import Type.Bin;
import Type.Info;

import java.util.ArrayList;
import java.util.Collections;

public class FirstFistDecreasingRand {
    private int objectif;

    public FirstFistDecreasingRand(Info info){
        info.setBins(new ArrayList<>());
        Collections.shuffle(info.getData());

        //tant que tout les items ne sont pas affecter
        for(int i = 0; i < info.getData().size(); i++){
            int itemValue = info.getData().get(i);
            boolean ajouter = false;
            //on test toute les boites disponibles
            for (Bin b:info.getBins()) {
                //Si le bin rentre dans la boite
                if (itemValue <= b.getSizeAvailable()){
                    b.addData(itemValue);
                    ajouter = true;
                    break;
                }
            }

            //Si il rentre dans aucune boite
            if(!ajouter){
                Bin bin = new Bin(info.getSize());
                bin.addData(itemValue);
                info.addBin(bin);
            }
        }
        Objectif o = new Objectif();
        this.objectif = o.getObjectif(info.getBins());
    }

    public int getObjectif() {
        return objectif;
    }
}
