package model.estrutura.grafo;
import java.util.*;

public class GrafoLista <T> {

    // Vou escolher para este exemplo uma lista por LABEL

    private Map<T, List<T> > map = new HashMap<>();

    private List<T> melhorCaminho; // adicionado em 13-11-26

    public void add (T source) {
        map.put(source,new LinkedList<T>());

    }

    public void add (T source, T destination)  {
        if (!map.containsKey(source))
            add(source);
        if (!map.containsKey(destination))
            add(destination);

        map.get(source).add(destination);
        map.get(destination).add(source);
    }

    public boolean has (T s) {
        return map.containsKey(s);
    }
    public boolean has(T s, T d) {
        return map.get(s).contains(d);
    }
    public int size() {
        return map.keySet().size();
    }

    // Escrever um output do grafo
    @Override public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString()+ ":");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }
    public String MelhorRotaToString(T origem, T destino){

        melhorCaminho = null;

        buscarRota(
                origem,
                destino,
                new HashSet<>(),
                new ArrayList<>());
// Este foi incluído
        if (melhorCaminho == null) {
            return "Não existe caminho";
        }

        return melhorCaminho.toString();
    }

    // incluído até aqui

    private void buscarRota(
            T atual,
            T destino,
            Set<T> visitados,
            List<T> caminhoAtual){

        visitados.add(atual);
        caminhoAtual.add(atual);

        if(atual.equals(destino)){

            if(melhorCaminho == null
                    || caminhoAtual.size() < melhorCaminho.size()){

                melhorCaminho =
                        new ArrayList<>(caminhoAtual);
            }

        }else{

            for(T vizinho : map.get(atual)){

                if(!visitados.contains(vizinho)){

                    buscarRota(
                            vizinho,
                            destino,
                            visitados,
                            caminhoAtual);
                }
            }
        }

        caminhoAtual.remove(caminhoAtual.size()-1);
        visitados.remove(atual);
    }
}
