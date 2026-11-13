package controller;

import model.estrutura.grafo.GrafoLista;

public class GrafoListaController {
    public GrafoListaController() {
        super();
    }

    public String teste() throws Exception{
        GrafoLista<String> g = new GrafoLista<String>();

        g.add("a","b");
        g.add("a","c");
        g.add("b","d");
        g.add("c","d");
        g.add("d","e");



        return g.MelhorRotaToString("a","e");
    }
}
