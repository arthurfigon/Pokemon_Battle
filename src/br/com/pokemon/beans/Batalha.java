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
                balaoDebatalha(this.pokemons[0].getNome() + " usou: " + this.pokemons[0].getAtaques().get(0).getNome());
                danoCausado = rollDano(this.pokemons[0].getAtaques().get(0));
                balaoDebatalha("Dano: " + danoCausado);
                this.pokemons[1].perdeHP(danoCausado);
                try { Thread.sleep(2500);
                    System.out.println("\n\n\n\n");
                } catch (InterruptedException ex) {}
            }
            if (this.pokemons[1].getVidaAtual() > 0) {
                balaoDebatalha(this.pokemons[1].getNome() + " usou: " + this.pokemons[1].getAtaques().get(0).getNome());
                danoCausado = rollDano(this.pokemons[1].getAtaques().get(0));
                balaoDebatalha("Dano: " + danoCausado);
                this.pokemons[0].perdeHP(danoCausado);
                try { Thread.sleep(2500);
                    System.out.println("\n\n\n\n");
                } catch (InterruptedException ex) {}
            }
            statusLuta();
            if (this.pokemons[0].getVidaAtual() <= 0) {
                balaoDebatalha("Seu pokémon ficou fora de combate...");
            }
            if (this.pokemons[1].getVidaAtual() <= 0) {
                balaoDebatalha("O pokemon inimigo está fora de combate!");
            }
            try { Thread.sleep(2000);
                System.out.println("\n\n\n");
            } catch (InterruptedException ex) {}
        }
    }

    private int rollDano(Ataque ataque){
        Random dano = new Random();
        Random critico = new Random();
        if(critico.nextInt(20)+1 >= 18){
            ataque.setCritico(true);
            balaoDebatalha("Golpe Crítico!");
            return (dano.nextInt(ataque.getDano())+1)*2;
        }else{
            ataque.setCritico(false);
            return dano.nextInt(ataque.getDano())+1;
        }
    }

    private void statusLuta(){
        if(pokemons[0].getVidaAtual() < 0){
            pokemons[0].setVidaAtual(0);
        }
        if(pokemons[1].getVidaAtual() < 0){
            pokemons[1].setVidaAtual(0);
        }
        balaoDebatalha(pokemons[0].getNome()+": "+pokemons[0].getVidaAtual()+"/"+pokemons[0].getVidaTotal());
        balaoDebatalha(pokemons[1].getNome()+": "+pokemons[1].getVidaAtual()+"/"+pokemons[1].getVidaTotal());
    }

    private void balaoDebatalha(String texto){
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   "+texto);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
