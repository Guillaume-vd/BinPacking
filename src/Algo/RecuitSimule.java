package Algo;

import Function.Objectif;
import Function.Voisinage;
import Type.Bin;
import Type.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecuitSimule {
    private int score;

    public RecuitSimule(Info info){
        info.setBins(new ArrayList<>());
        UnBinParItem unBinParItem = new UnBinParItem(info);
        Objectif o = new Objectif();
        Random random = new Random();
        Voisinage v = new Voisinage();

        double temp = 500.0;
        this.score = o.getObjectif(info.getBins());
        int lastScore = 0;
        int newScore = 0;
        List<Bin> lastBin;

        for(int k = 0; k < 1000; k++){
            lastBin = info.getBins();
            lastScore = o.getObjectif(info.getBins());

            //Choisit aléatoirement l'opération à effectué
            int choice = random.nextInt(2);
            if(choice == 0){
                v.deplace(info);
            } else {
                v.swap(info);
            }

            //On calcul maintenant la
            newScore = o.getObjectif(info.getBins());
            if(score <= newScore){
                score = newScore;
            } else {
                double p = random.nextDouble();
                if(p > Math.exp(-(newScore-lastScore)/temp)){
                    info.setBins(lastBin);
                }
            }
            temp = temp * 0.95;
        }
    }

    public int getScore() {
        return score;
    }
}
