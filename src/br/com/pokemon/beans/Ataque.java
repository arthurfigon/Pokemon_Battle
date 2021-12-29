package br.com.pokemon.beans;

public class Ataque {

    int id;
    String nome;
    int dano;
    boolean critico = false;

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

    public boolean isCritico() {
        return critico;
    }

    public void setCritico(boolean critico) {
        this.critico = critico;
    }
}
