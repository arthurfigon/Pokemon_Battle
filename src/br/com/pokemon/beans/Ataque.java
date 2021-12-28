package br.com.pokemon.beans;

public class Ataque {

    int id;
    String nome;
    int dano;

    public Ataque(int id, String nome, int dano) {
        this.id = id;
        this.nome = nome;
        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }
}
