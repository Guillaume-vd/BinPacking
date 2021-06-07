package Algo;

import Function.Fitness;
import Function.Voisinage;
import Type.Info;

import java.util.ArrayList;
import java.util.List;

public class TabuSearch {
    private int score;

    public TabuSearch(Info info){
        info.setBins(new ArrayList<>()); //Réinitialisation de la liste de bin

        //Appel des différents
        new UnBinParItem(info);
        Fitness o = new Fitness();
        Voisinage v = new Voisinage();

        this.score = o.getFitness(info.getBins());
        int scoreVoisin;
        int newScore;
        List<List<Integer>> tabu = new ArrayList<>();

        //On boucle n fois l'algorithme
        for (int i = 0; i < info.getTabouIteration(); i++){
            scoreVoisin = 0;
            //System.out.println(i + " " + this.score + " " + info.getBins().size());

            for (int j = 0; j < info.getBins().size() - 1; j++) { //Pour chaque bin source
                for (int k = 0; k < info.getBins().size() - 1; k++) { //Pour chaque bin destination
                    for (int l = 0; l < info.getBins().get(j).getSize(); l++) { //Pour chaque item source

                        //Vérification si les bins sont présents dans la liste tabou
                        boolean dans = dansTabou(info, tabu, j, k);
                        if (!dans) {
                            continue;
                        }

                        if (v.deplaceTabu(info, j, l, k)){ //Si le déplacement est réalisable
                            newScore = o.getFitness(info.getBins());
                            if (scoreVoisin <= newScore) { //Si le score est moins bon on met les éléments dans la liste tabou
                                scoreVoisin = newScore;
                                List<Integer> temp = new ArrayList<>();
                                temp.add(k);
                                temp.add(info.getBins().get(k).getSize());
                                temp.add(j);
                                tabu.add(temp);

                                //Si on atteint la taille max de la liste tabou
                                if (tabu.size() > info.getTabuMax())
                                    tabu.remove(0);
                            }
                        }

                        //Utile dans la boucle???
                        //Vérification si les bins sont présents dans la liste tabou, après le déplacement d'un item
                        dans = dansTabou(info, tabu, j, k);
                        if (!dans) {
                            continue;
                        }

                        for (int m = 0; m < info.getBins().get(k).getData().size(); m++) { //Pour chaque item destination

                            if (v.swapTabu(info, j, l, k, m)) { //Si le déplacement est réalisable
                                newScore = o.getFitness(info.getBins());
                                if (scoreVoisin <= newScore) { //Si le score est moins bon on le met dans la liste tabou
                                    scoreVoisin = newScore;
                                    List<Integer> temp = new ArrayList<>();
                                    temp.add(k);
                                    temp.add(info.getBins().get(k).getSize());
                                    temp.add(j);
                                    temp.add(m);
                                    tabu.add(temp);

                                    //Si on atteint la taille max de la liste tabou
                                    if (tabu.size() > info.getTabuMax())
                                        tabu.remove(0);
                                }
                            }
                        }

                    }
                }
            }
        }
        this.score = o.getFitness(info.getBins());
    }

    /**
    * Vérifie si la/les bins ne sont pas dans la liste tabou
     * On ne déplace pas un item d'une taille X avec un autre d'une même taille X
     */
    public boolean dansTabou(Info info, List<List<Integer>> tabu, int j, int k) {
        List<Integer> tabou = new ArrayList<>();
        tabou.add(k);
        tabou.add(info.getBins().get(k).getSize());
        tabou.add(j);
        tabou.add(info.getBins().get(j).getSize());

        if(tabu.contains(tabou)){
            return false;
        } else {
            return true;
        }
    }

    public int getScore() {
        return this.score;
    }
}
