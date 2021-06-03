package Algo;

import Function.Fitness;
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
        new UnBinParItem(info);
        Fitness o = new Fitness();
        Random random = new Random();
        Voisinage v = new Voisinage();

        double temp = info.getTempRecuit();
        this.score = o.getFitness(info.getBins());
        int lastScore = this.score;
        int newScore = 0;
        List<Bin> lastBin;

        for (int i = 0; i < 6000; i++){ //tant que pas sur optimal
            lastBin = info.getBins();
            lastScore = o.getFitness(info.getBins());

            //Choisit aléatoirement l'opération à effectué
            int choice = random.nextInt(2);
            if(choice == 0){
                v.deplaceRecuit(info);
            } else {
                v.swapRecuit(info);
            }

            //On calcul maintenant la fitness
            newScore = o.getFitness(info.getBins());
            if(score < newScore){ //Si le score est améliorer
                this.score = newScore;
            } else {
                //Sinon on garde une chance de conserver la liste, mais de plus en plus faible
                double p = random.nextDouble();
                if(p > Math.exp(-(newScore-lastScore)/info.getTempRecuit())){
                    info.setBins(lastBin);
                }
            }
        }

        //Mise à jour de la température
        info.setTempRecuit(info.getTempRecuit() * 0.95);
    }


    public int getScore() {
        return score;
    }
}
