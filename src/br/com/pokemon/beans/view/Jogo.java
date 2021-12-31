package br.com.pokemon.beans.view;

import br.com.pokemon.beans.*;
import util.Utils;

import java.util.ArrayList;
import java.util.Random;

public class Jogo {
    ArrayList<Tipo> tipos = new ArrayList<>();
    ArrayList<Ataque> ataques = new ArrayList<>();
    Treinador treinador;
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    boolean gameOn = true;
    Mapa mapaKanto = new Mapa();


    public Jogo() {
        Utils ajuda = new Utils();
        // Cria tipos e adiciona em this.tipos para tipos
        inicializaTipos();

        // Cria ataques e adiciona à ataques
        inicializaAtaques();

        // Cria dois pokemons com seus respectivos parametros
        inicializaPokemons();


        // Começa o MODO HISTÓRIA
        this.treinador = new Treinador(dialogoComeçoJogo(),25, "Masculino",
                pickInitial(findPokemonByName("Bulbasaur"),
                        findPokemonByName("Charmander"), findPokemonByName("Squirtle")));

        // Criar uma batalha entre o trainer e um pokemon
        firstBattle();

        //Usa Centro Pokémon
        centroPokemon(treinador.getTime());

        while(gameOn) {
            //Inicia Novo Combate
            Pokemon combatente = pickWildPokemon(findPokemonByName("Pidgey"),findPokemonByName("Rattata"));
            Batalha batalhaUm = new Batalha(treinador.pickPokemon(), combatente);
            batalhaUm.startBattle();
            curaPokemon(combatente);

            //Inicia Novo Combate
            combatente = pickWildPokemon(findPokemonByName("Pidgey"),findPokemonByName("Rattata"));
            Batalha batalhaDois = new Batalha(treinador.pickPokemon(), combatente);
            batalhaDois.startBattle();
            curaPokemon(combatente);
            mapaKanto.showMapa();
            mapaKanto.andaMapa(ajuda.LerString("Use W A S D para mover-se: "));
        }
    }

    private Tipo findTipoByName(String nome){
        for(Tipo tipo : tipos){
            if(tipo.getNome().equals(nome)){
                return tipo;
            }
        }
        return null;
    }

    private Pokemon findPokemonByName(String nome){
        for(Pokemon pokemon : pokemons){
            if(pokemon.getNome().equals(nome)){
                return pokemon;
            }
        }
        return null;
    }

    private void firstBattle(){
        Pokemon bulbasaurInicial = getPokemonById(pokemons,1);
        Pokemon charmanderInicial = getPokemonById(pokemons,4);
        Pokemon squirtleInicial = getPokemonById(pokemons,7);
        Utils ajuda = new Utils();

        balaoDebatalha("Seu rival escolheu outro pokemon...");
        try { Thread.sleep(2000);}catch (InterruptedException ex) {}
        balaoDebatalha("E parece que ele quer batalhar...");
        try { Thread.sleep(1000);
            System.out.println("\n");
        }catch (InterruptedException ex) {}

        startBattleAnimation();
        if(treinador.getFirstPokemon().getId() == 1){
            Batalha batalhaInicial = new Batalha(treinador.getFirstPokemon(),charmanderInicial);
            batalhaInicial.startBattle();
        }else if(treinador.getFirstPokemon().getId() == 4){
            Batalha batalhaInicial = new Batalha(treinador.getFirstPokemon(),squirtleInicial);
            batalhaInicial.startBattle();
        }else{
            Batalha batalhaInicial = new Batalha(treinador.getFirstPokemon(),bulbasaurInicial);
            batalhaInicial.startBattle();
        }

    }

    private void startBattleAnimation(){
        for (int i = 0; i < 240 ; i++){
            if(i%60==0){
                System.out.println("");
            }
            System.out.print("#");
            try { Thread.sleep(10);}catch (InterruptedException ex) {}
        }
        System.out.println("\n\n");
    }

    private Pokemon getPokemonById(ArrayList<Pokemon> vetor, int id){
        for(Pokemon pokemon : vetor){
            if(pokemon.getId() == id){
                return pokemon;
            }
        }
        return pokemons.get(0);
    }

    private String dialogoComeçoJogo(){
        util.Utils ajuda = new Utils();
        ajuda.balaoDebatalha("Olá, jovem! Meu nome é Professor Sysor,\n" +
                "e seja bem vindo(a) ao mundo Pokémon!");
        try { Thread.sleep(3000);
            System.out.println("\n\n");
        } catch (InterruptedException ex) {}
        ajuda.balaoDebatalha("Sei que deve estar ansioso(a) para começar sua jornada,\n" +
                "mas antes disso eu preciso que você me diga uma coisa...");
        try { Thread.sleep(3000);
            System.out.println("");
        } catch (InterruptedException ex) {}
        String nomeJogador = ajuda.LerString("Qual o seu nome: ");
        System.out.println("\n\n");
        ajuda.balaoDebatalha("Olá "+nomeJogador+"!\nPara que possamos começar a sua aventura" +
                "\nvocê precisa tomar uma decisão...");
        System.out.println("");
        return nomeJogador;
    }

    private Pokemon pickWildPokemon(Pokemon pokemonUm, Pokemon pokemonDois){
        Random random = new Random();
        int numeroEscolhido = random.nextInt(2);
        Pokemon[] pokemons = new Pokemon[2];
        pokemons[0] = pokemonUm;
        pokemons[1] = pokemonDois;
        return pokemons[numeroEscolhido];

    }

    private Pokemon pickWildPokemon(Pokemon pokemonUm, Pokemon pokemonDois, Pokemon pokemonTres){
        Random random = new Random();
        int numeroEscolhido = random.nextInt(3);

        Pokemon[] pokemons = new Pokemon[3];
        pokemons[0] = pokemonUm;
        pokemons[1] = pokemonDois;
        pokemons[2] = pokemonTres;
        return pokemons[numeroEscolhido];

    }

    private Pokemon pickWildPokemon(Pokemon pokemonUm, Pokemon pokemonDois, Pokemon pokemonTres, Pokemon pokemonQuatro){
        Random random = new Random();
        int numeroEscolhido = random.nextInt(4);
        Pokemon[] pokemons = new Pokemon[4];
        pokemons[0] = pokemonUm;
        pokemons[1] = pokemonDois;
        pokemons[2] = pokemonTres;
        pokemons[3] = pokemonQuatro;
        return pokemons[numeroEscolhido];

    }

    private Pokemon pickInitial(Pokemon a, Pokemon b, Pokemon c){
        util.Utils ajuda = new util.Utils();
        Treinador.printaPokemon(a);
        Treinador.printaPokemon(b);
        Treinador.printaPokemon(c);
        int resposta = ajuda.LerInt("Digite o id do pokemon que você deseja: ");
        if (resposta == c.getId()){
            return c;
        } else if(resposta == b.getId()){
            return b;
        } else{
            return a;
        }

    }

    private void centroPokemon(ArrayList<Pokemon> pokemons){
        for(Pokemon pokemon : pokemons){
            curaPokemon(pokemon);
        }
        balaoDebatalha("Seus pokémons foram curados... Volte sempre!");
        try { Thread.sleep(2500);
            System.out.println("\n\n\n\n");
        } catch (InterruptedException ex) {}
    }

    public void balaoDebatalha(String texto){
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   "+texto);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    private void curaPokemon(Pokemon pokemon){
        pokemon.setVidaAtual(pokemon.getVidaTotal());
    }

    private void addNovoTipo(Tipo tipo){
        tipos.add(tipo);
    }

    private void addNovoAtaque(Ataque ataque){
        ataques.add(ataque);
    }


    private Ataque findAtaqueByName(String nome){
        for(Ataque ataque : ataques){
            if (ataque.getNome().equals(nome)){
                return ataque;
            }
        }
        return findAtaqueByName("Tackle");
    }

    private void inicializaTipos(){
        Tipo fogo = new Tipo("Fogo");
        addNovoTipo(fogo);
        Tipo agua = new Tipo("Agua");
        addNovoTipo(agua);
        Tipo normal = new Tipo("Normal");
        addNovoTipo(normal);
        Tipo voador = new Tipo("Voador");
        addNovoTipo(voador);
        Tipo planta = new Tipo("Planta");
        addNovoTipo(planta);
        Tipo eletrico = new Tipo("Eletrico");
        addNovoTipo(eletrico);
        Tipo gelo = new Tipo("Gelo");
        addNovoTipo(gelo);
        Tipo lutador = new Tipo("Lutador");
        addNovoTipo(lutador);
        Tipo veneno = new Tipo("Veneno");
        addNovoTipo(veneno);
        Tipo terra = new Tipo("Terra");
        addNovoTipo(terra);
        Tipo psiquico = new Tipo("Psiquico");
        addNovoTipo(psiquico);
        Tipo inseto = new Tipo("Inseto");
        addNovoTipo(inseto);
        Tipo pedra = new Tipo("Pedra");
        addNovoTipo(pedra);
        Tipo fantasma = new Tipo("Fantasma");
        addNovoTipo(fantasma);
        Tipo dragao = new Tipo("Dragão");
        addNovoTipo(dragao);
    }

    private void inicializaAtaques(){
        Ataque bubble = new Ataque(02,"Bubble", 10, findTipoByName("Agua"));
        this.addNovoAtaque(bubble);
        Ataque ember = new Ataque(01,"Ember", 10, findTipoByName("Fogo"));
        this.addNovoAtaque(ember);
        Ataque tackle = new Ataque(03,"Tackle", 5, findTipoByName("Normal"));
        this.addNovoAtaque(tackle);
        Ataque peck = new Ataque(04, "Peck", 5, findTipoByName("Voador"));
        this.addNovoAtaque(peck);
        Ataque razorBlade = new Ataque(05, "Razor Blade", 10, findTipoByName("Planta"));
        this.addNovoAtaque(razorBlade);
    }

    private void inicializaPokemons(){
        Pokemon charmander = new Pokemon(04,"Charmander",25,25,
                findTipoByName("Fogo"), findAtaqueByName("Ember"),05,"Masculino",false);
        Pokemon squirtle = new Pokemon(07,"Squirtle",25,25,
                findTipoByName("Agua"), findAtaqueByName("Bubble"),05,"Feminino",false);
        Pokemon bulbasaur = new Pokemon(01, "Bulbasaur", 25, 25,
                findTipoByName("Planta"), findAtaqueByName("Razor Blade"), 05, "Feminino", false);
        Pokemon rattata = new Pokemon(19,"Ratatta",12,12, findTipoByName("Normal"),
                findAtaqueByName("Tackle"),03,"Masculino",false);
        Pokemon pidgey = new Pokemon(16, "Pidgey", 12, 12, findTipoByName("Voador"),
                findAtaqueByName("Peck"), 03, "Masculino", false);
        pokemons.add(charmander);
        pokemons.add(squirtle);
        pokemons.add(bulbasaur);
        pokemons.add(rattata);
        pokemons.add(pidgey);
    }
}
