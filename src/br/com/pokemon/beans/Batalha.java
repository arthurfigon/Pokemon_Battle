package br.com.pokemon.beans;

import java.util.Random;

public class Batalha {

    Pokemon[] pokemons = new Pokemon[2];

    public Batalha(Pokemon pokemonTrainer, Pokemon pokemonWild) {
        this.pokemons[0] = pokemonTrainer;
        this.pokemons[1] = pokemonWild;
    }

    public void startBattle() {
        int danoCausado= 0;
        while(this.pokemons[0].getVidaAtual() > 0 && this.pokemons[1].getVidaAtual() > 0) {
            if (this.pokemons[0].getVidaAtual() > 0) {
                System.out.println(this.pokemons[0].getNome() + " usou: " + this.pokemons[0].getAtaques().get(0).getNome());
                danoCausado = rollDano(this.pokemons[0].getAtaques().get(0));
                System.out.println("Dano: " + danoCausado);
                this.pokemons[1].perdeHP(danoCausado);
                try { Thread.sleep(2000); } catch (InterruptedException ex) {}
            }
            if (this.pokemons[1].getVidaAtual() > 0) {
                System.out.println("\n"+this.pokemons[1].getNome() + " usou: " + this.pokemons[1].getAtaques().get(0).getNome());
                danoCausado = rollDano(this.pokemons[1].getAtaques().get(0));
                System.out.println("Dano: " + danoCausado);
                this.pokemons[0].perdeHP(danoCausado);
                try { Thread.sleep(2000); } catch (InterruptedException ex) {}
            }
            statusLuta();
            if (this.pokemons[0].getVidaAtual() <= 0) {
                System.out.println("Seu pokémon ficou fora de combate...");
            }
            if (this.pokemons[1].getVidaAtual() <= 0) {
                System.out.println("O pokemon inimigo está fora de combate!");
            }
            try { Thread.sleep(2000); } catch (InterruptedException ex) {}
        }
    }

    private int rollDano(Ataque ataque){
        Random dano = new Random();
        return dano.nextInt(ataque.getDano())+1;
    }

    private void statusLuta(){
        System.out.println("\n"+pokemons[0].getNome()+": "+pokemons[0].getVidaAtual()+"/"+pokemons[0].getVidaTotal());
        System.out.println(pokemons[1].getNome()+": "+pokemons[1].getVidaAtual()+"/"+pokemons[1].getVidaTotal()+"\n");
    }
}
