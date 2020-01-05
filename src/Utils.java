import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public final class Utils {
    /**
     * Calculer degré maximum à parir des composantes données afin de créer un graphe pour le problème IC
     * @return int
     */
    public static int calculerDegreeMax(ArrayList<Composante> composantes){
        //HashMap <Sommet, Nb Aretes possible>
        HashMap<Integer, Integer> tmp = new HashMap<>();
        for(Composante c: composantes){
            c.getSommets().forEach(s -> {tmp.put(s.getValue(), 0);});
        }

        int length = tmp.size()+1;
        int[][] delta_tab = new int[100][100];
        for(int[] arr1 : delta_tab)
            Arrays.fill(arr1, 0);

        for(Composante c: composantes){
            for(Sommet s1: c.getSommets()){
                for(Sommet s2: c.getSommets()){
                    if(delta_tab[s2.getValue()][s1.getValue()] == 0 && s1.getValue()!=s2.getValue()){
                        delta_tab[s1.getValue()][s2.getValue()] = 1;
                    }
                }
            }
        }

        for(int i=1; i<delta_tab.length; i++){
            int total=0;
            for(int j=1; j<delta_tab.length; j++){
                tmp.put(i, total+=delta_tab[i][j]);
                tmp.put(i, total+=delta_tab[j][i]);
            }
        }

        return Collections.max(tmp.values());
    }

    /**
     * Calculer nombre maximum d'arêtes à parir des composantes données afin de créer un graphe pour le problème IC
     * @return int
     */
    public static int calculerNbAretesMax(ArrayList<Composante> composantes){
        int kMax=0;
        for(Composante c: composantes){
            kMax+=c.getSommets().size()-1;
        }
        return kMax;
    }

    /**
     * Afficher les composantes créées
     */
    public static void showComposantes(ArrayList<Composante> composantes){
        System.out.println("Vous venez de créer "+ composantes.size() +" sous-ensembles");
        for (Composante c: composantes){
            System.out.print("[");
            for(Sommet s: c.getSommets()){
                if(c.getSommets().lastIndexOf(s) == c.getSommets().size()-1)
                    System.out.print(s.getValue());
                else System.out.print(s.getValue()+", ");
            }
            System.out.println("]");
        }
    }

    /**
     * Construire des instances aléatoires (des composantes) pour le problème IC
     * @param p Nombre des sous-ensembles
     * @param t Nombre sommet pour chaque sous-ensemble
     * @return ArrayList
     */
    public static ArrayList<Composante> randomGen(int p, int t){
        if(p>100 || p<1 || t<1){
            return null;
        }else{
            ArrayList<Composante> composantes = new ArrayList<>();
            for(int i=0; i<t; i++){
                Composante c = new Composante();
                ArrayList<Sommet> sommets = new ArrayList<>();
                for(int s=1; s<100; s++){
                    sommets.add(new Sommet(s));
                }
                Collections.shuffle(sommets);
                for(int j=0; j<p; j++){
                    c.ajouter_sommet(sommets.remove(j));
                }
                composantes.add(c);
            }
            // Afficher informations ce qu'on vient de créer
            //showComposantes(composantes);
            return composantes;
        }
    }

    /**
     * Vérifier si il y a de solution pour le probleme IC en donnant des instances correspondantes
     * @param k Nombre d'arête
     * @param delta Degré
     * @param composantes des sous-ensemble qui contient des sommets
     */
    public static boolean checkICProblem_Decision(int k, int delta, ArrayList<Composante> composantes){
        int kMin=0, kMax=0, deltaMin=0, deltaMax=0;
        //Check k value - le plus grand k possible
        kMax = Utils.calculerNbAretesMax(composantes);
        //Check delta value - le plus grand delta possible
        deltaMax = Utils.calculerDegreeMax(composantes);

        return (k<=kMax && delta<=deltaMax);
    }
    /**
     * Calculer degré minimum à parir des composantes données afin de créer un graphe pour le problème IC
     * @return int
     */
    /*
    public static int calculerDegreeMin(ArrayList<Composante> composantes){
        //HashMap <Sommet, Nb intersection>
        HashMap<Integer, Integer> tmp = new HashMap<>();
        for(Composante c: composantes){
            c.getSommets().forEach(s -> {tmp.put(s.getValue(), 0);});
        }

        for(Composante c: composantes){
            for(Sommet s: c.getSommets()){
                for (int key: tmp.keySet()){
                    if(s.getValue() == key){
                        tmp.put(key, tmp.get(key)+1);
                    }
                }
            }
        }

        //Intersection so -1
        tmp.forEach((k, v)->{
            tmp.put(k, v-1);
        });

        return Collections.max(tmp.values());
    }
    */
    /**
     * Calculer nombre d'arêtes à parir des composantes données afin de créer un graphe pour le problème IC
     * @return int
     */
    /*
    public static int calculerNbAretesMin(ArrayList<Composante> composantes){
        return Utils.calculerNbAretesMax(composantes) - Utils.calculerDegreeMin(composantes);
    }
    */


}
