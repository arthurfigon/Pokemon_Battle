package br.com.pokemon.beans;

import java.util.Random;

public class Batalha {

    Pokemon[] pokemons = new Pokemon[2];

    public Batalha(Pokemon pokemonTrainer, Pokemon pokemonWild) {
        this.pokemons[0] = pokemonTrainer;
        this.pokemons[1] = pokemonWild;
    }

    public void startBattle() {
        System.out.println("\n\n\n");
        balaoDebatalha("Seu pokemon: "+pokemons[0].getNome()+": "+pokemons[0].getVidaAtual()+
                "/"+pokemons[0].getVidaTotal());
        balaoDebatalha("Oponente: "+pokemons[1].getNome()+": "+pokemons[1].getVidaAtual()+
                "/"+pokemons[1].getVidaTotal());
        System.out.print("\n");
        try { Thread.sleep(4500);
            System.out.println("\n\n\n\n\n");
        } catch (InterruptedException ignored) {}
        while(this.pokemons[0].getVidaAtual() > 0 && this.pokemons[1].getVidaAtual() > 0) {
            mostrarBatalha();
        }
    }

    private void ataquePlayer(int indexAtaque){
        int danoCausado;
        if (this.pokemons[0].getVidaAtual() > 0) {
            danoCausado = rollDano(this.pokemons[1].getAtaques().get(indexAtaque));
            this.pokemons[1].perdeHP(danoCausado);
            if(pokemons[1].getVidaAtual() < 0){ pokemons[1].setVidaAtual(0);}
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║ "+pokemons[1].getNome()+": "+pokemons[1].getVidaAtual()+"/"+pokemons[1].getVidaTotal() + " HP");
            System.out.println("║ ╔═════════════════════════════════════════════════════╗");
            System.out.println("║ ║ - "+danoCausado+" HP");
            System.out.println("║ ╚═════════════════════════════════════════════════════╝");
            System.out.println("║");
            System.out.println("║");
            System.out.println("║");
            System.out.println("║ "+pokemons[0].getNome()+": "+pokemons[0].getVidaAtual()+"/"+pokemons[0].getVidaTotal());
            System.out.println("║ ╔═════════════════════════════════════════════════════╗");
            if(danoCausado > this.pokemons[0].getAtaques().get(0).getDano()){
                System.out.println("║ ║ Usou: "+this.pokemons[0].getAtaques().get(indexAtaque).getNome()+"! (Ataque Crítico)");
            }
            else{
                System.out.println("║ ║ Usou: "+this.pokemons[0].getAtaques().get(indexAtaque).getNome()+"!");
            }
            System.out.println("║ ╚═════════════════════════════════════════════════════╝");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            try { Thread.sleep(4500);
                System.out.println("\n\n\n\n\n");
            } catch (InterruptedException ignored) {}
        }
    }

    private void ataqueOponente(int indexAtaque){
        int danoCausado;
        if (this.pokemons[1].getVidaAtual() > 0) {
            danoCausado = rollDano(this.pokemons[1].getAtaques().get(indexAtaque));
            this.pokemons[0].perdeHP(danoCausado);
            if(pokemons[0].getVidaAtual() < 0){ pokemons[0].setVidaAtual(0);}
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║ "+pokemons[1].getNome()+": "+pokemons[1].getVidaAtual()+"/"+pokemons[1].getVidaTotal() + " HP");
            System.out.println("║ ╔═════════════════════════════════════════════════════╗");
            if(danoCausado > this.pokemons[1].getAtaques().get(0).getDano()){
                System.out.println("║ ║ Usou: "+this.pokemons[1].getAtaques().get(indexAtaque).getNome()+"! (Ataque Crítico)");
            }
            else{
                System.out.println("║ ║ Usou: "+this.pokemons[1].getAtaques().get(indexAtaque).getNome()+"!");
            }
            System.out.println("║ ╚═════════════════════════════════════════════════════╝");
            System.out.println("║");
            System.out.println("║");
            System.out.println("║");
            System.out.println("║ "+pokemons[0].getNome()+": "+pokemons[0].getVidaAtual()+"/"+pokemons[0].getVidaTotal());
            System.out.println("║ ╔═════════════════════════════════════════════════════╗");
            System.out.println("║ ║ - "+danoCausado+" HP");
            System.out.println("║ ╚═════════════════════════════════════════════════════╝");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            try { Thread.sleep(4500);
                System.out.println("\n\n\n\n\n");
            } catch (InterruptedException ignored) {}
        }
    }

    private void mostrarBatalha(){
        ataquePlayer(0);
        ataqueOponente(0);

        if (this.pokemons[0].getVidaAtual() <= 0) {
            balaoDebatalha("Seu pokémon ficou fora de combate...");
            try { Thread.sleep(3000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            } catch (InterruptedException ignored) {}
        }
        if (this.pokemons[1].getVidaAtual() <= 0) {
            balaoDebatalha("O pokemon inimigo está fora de combate!");
            try { Thread.sleep(3000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            } catch (InterruptedException ignored) {}
        }
    }

    private int rollDano(Ataque ataque){
        Random dano = new Random();
        Random critico = new Random();
        if(critico.nextInt(20)+1 >= 18){
            ataque.setCritico(true);
            return (dano.nextInt(ataque.getDano())+1)*2;
        }else{
            ataque.setCritico(false);
            return dano.nextInt(ataque.getDano())+1;
        }
    }

    private void statusLuta(){
        balaoDebatalha("Status da luta:");
        balaoDebatalha(pokemons[0].getNome()+": "+pokemons[0].getVidaAtual()+"/"+pokemons[0].getVidaTotal());
        balaoDebatalha(pokemons[1].getNome()+": "+pokemons[1].getVidaAtual()+"/"+pokemons[1].getVidaTotal());
    }

    private void balaoDebatalha(String texto){
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   "+texto);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
