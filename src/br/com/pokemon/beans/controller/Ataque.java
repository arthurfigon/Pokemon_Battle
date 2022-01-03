package br.com.pokemon.beans.controller;

public class Ataque {

    int id;
    String nome;
    int dano;
    Tipo tipo;
    boolean critico = false;

    public Ataque(int id, String nome, int dano, Tipo tipo) {
        this.id = id;
        this.nome = nome;
        this.dano = dano;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }

    public boolean isCritico() {
        return critico;
    }

    public void setCritico(boolean critico) {
        this.critico = critico;
    }
}
