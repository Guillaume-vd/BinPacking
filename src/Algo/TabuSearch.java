package Algo;

import Function.Fitness;
import Function.Voisinage;
import Type.Bin;
import Type.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuSearch {
    private int score;

    public TabuSearch(Info info){
        info.setBins(new ArrayList<>()); //Réinitialisation de la liste de Bin

        //Appel des différents
        UnBinParItem unBinParItem = new UnBinParItem(info);
        Fitness o = new Fitness();
        Voisinage v = new Voisinage();

        this.score = o.getFitness(info.getBins());
        int bestScore = this.score;
        int scoreVoisin = 0;
        int newScore = 0;
        List<List<Integer>> tabu = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            scoreVoisin = 0;
            //System.out.println(i + " " + this.score + " " + info.getBins().size());

            for (int j = 0; j < info.getBins().size() - 1; j++) {
                for (int k = 0; k < info.getBins().size() - 1; k++) {
                    for (int l = 0; l < info.getBins().get(j).getSize(); l++) {

                        boolean acceptable = acceptable(info, tabu, j, k);
                        if (!acceptable) {
                            continue;
                        }

                        if (v.deplaceTabu(info, j, l, k)){
                            newScore = o.getFitness(info.getBins());
                            if (scoreVoisin <= newScore) {
                                scoreVoisin = newScore;
                                List<Integer> temp = new ArrayList<>();
                                temp.add(k);
                                temp.add(info.getBins().get(k).getSize());
                                temp.add(j);
                                tabu.add(temp);
                            }
                        }
                        for (int m = 0; m < info.getBins().get(k).getData().size(); m++) {
                            acceptable = acceptable(info, tabu, j, k);
                            if (!acceptable) {
                                continue;
                            }

                            if (v.swapTabu(info, j, l, k, m)) {
                                newScore = o.getFitness(info.getBins());
                                if (scoreVoisin <= newScore) {
                                    scoreVoisin = newScore;
                                    List<Integer> temp = new ArrayList<>();
                                    temp.add(k);
                                    temp.add(info.getBins().get(k).getSize());
                                    temp.add(j);
                                    temp.add(m);
                                    tabu.add(temp);
                                }
                            }
                        }
                    }
                }
            }

            if (scoreVoisin < newScore) {
                tabu.remove(0);
            } else if (scoreVoisin > bestScore) {
                bestScore = scoreVoisin;
            }
        }
        this.score = o.getFitness(info.getBins());
    }

    public boolean acceptable(Info info, List<List<Integer>> tabu, int j, int k) {
        List<Integer> aled = new ArrayList<>();
        aled.add(k);
        aled.add(info.getBins().get(k).getSize());
        aled.add(j);
        aled.add(info.getBins().get(j).getSize());
        for (int i = 0; i < tabu.size(); i++) {
            if (tabu.get(i) == aled) {
                return false;
            }
        }
        return true;
    }

    public int getScore() {
        return this.score;
    }
}
