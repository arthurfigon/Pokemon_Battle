package br.com.pokemon.beans;

import java.util.ArrayList;

public class Tipo {
    String nome;
    ArrayList<String> fraqueza;
    ArrayList<String> forteContra;

    public Tipo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
