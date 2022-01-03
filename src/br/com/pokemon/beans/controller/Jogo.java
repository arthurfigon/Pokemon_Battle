package br.com.pokemon.beans.controller;

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
        // Cria tipos e adiciona em this.tipos para tipos
        inicializaTipos();

        // Cria ataques e adiciona à ataques
        inicializaAtaques();

        // Cria dois pokemons com seus respectivos parametros
        inicializaPokemons();


        // Começa o MODO HISTÓRIA
        this.treinador = new Treinador(dialogoComecoJogo(),25, "Masculino",
                pickInitial(findPokemonByName("Bulbasaur"),
                        findPokemonByName("Charmander"), findPokemonByName("Squirtle")));

        // Criar uma batalha entre o trainer e um pokemon
        firstBattle();


        while(gameOn) {
            if(mapaKanto.isCidade()){
                createCityMenu();
            }else{
                createWildMenu();
            }
        }
    }

    private void createWildMenu(){
        Utils ajuda = new Utils();
        System.out.println(ajuda.balaoGeral("1 - Procurar pokemon selvagem","2 - Usar Item(ainda será implementado)",
                "3 - Andar","4 - Sair do Jogo"));
        switch (ajuda.LerInt("Digite a opção desejada: ")){
            case 1 -> pickMapBattle(mapaKanto.getLocalizacaoLinha(),mapaKanto.getLocalizacaoColuna());
            case 2 -> pickMapBattle(mapaKanto.getLocalizacaoLinha(),mapaKanto.getLocalizacaoColuna());
            case 3 ->{
                mapaKanto.showMapa();
                mapaKanto.andaMapa(ajuda.LerString("Use W A S D para mover-se: "));
            }
            case 4 -> gameOn = false;
            default -> pickMapBattle(mapaKanto.getLocalizacaoLinha(),mapaKanto.getLocalizacaoColuna());
        }
    }

    private void createCityMenu(){
        Utils ajuda = new Utils();
        System.out.println(ajuda.balaoGeral("1 - Ir ao centro Pokemon","2 - Ir ao PokeMart(ainda será implementado)",
                "3 - Andar","4 - Sair do Jogo"));
        switch (ajuda.LerInt("Digite a opção desejada: ")){
            case 1 -> centroPokemon(treinador.getTime());
            case 2 -> centroPokemon(treinador.getTime());
            case 3 ->{
                mapaKanto.showMapa();
                mapaKanto.andaMapa(ajuda.LerString("Informe w(↑) a(←) s(↓) d(→) para mover-se: "));
            }
            case 4 -> gameOn = false;
            default -> centroPokemon(treinador.getTime());
        }
    }

    private void pickMapBattle(int mapX, int mapY){
        Pokemon combatente;
        Random legendary = new Random();
        if(legendary.nextInt(200) == 199){
            combatente = pickWildPokemon(findPokemonByName("Moltres"), findPokemonByName("Zapdos"),
                    findPokemonByName("Articuno"));
            curaPokemon(combatente);
        }else {
            if ((mapX == 10 || mapX == 9) && mapY == 3) {
                combatente = pickWildPokemon(findPokemonByName("Pidgey"), findPokemonByName("Rattata"));
                Batalha batalhaUm = new Batalha(treinador.pickPokemon(), combatente);
                batalhaUm.startBattle();
                curaPokemon(combatente);
            }
            if (mapX == 7 && mapY == 3) {
                combatente = pickWildPokemon(findPokemonByName("Nidoran♂"), findPokemonByName("Nidoran♀"));
                Batalha batalhaUm = new Batalha(treinador.pickPokemon(), combatente);
                batalhaUm.startBattle();
                curaPokemon(combatente);
            }
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
        Utils ajuda = new Utils();
        Pokemon bulbasaurInicial = getPokemonById(pokemons,1);
        Pokemon charmanderInicial = getPokemonById(pokemons,4);
        Pokemon squirtleInicial = getPokemonById(pokemons,7);

        System.out.println(ajuda.balaoPequeno("Seu rival escolheu outro pokemon..."));
        try { Thread.sleep(1500);}catch (InterruptedException ignored) {}
        System.out.println(ajuda.balaoPequeno("E parece que ele quer batalhar..."));
        try { Thread.sleep(1500);
            System.out.println("\n");
        }catch (InterruptedException ignored) {}

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
                System.out.print("\n");
            }
            System.out.print("#");
            try { Thread.sleep(10);}catch (InterruptedException ignored) {}
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

    private String dialogoComecoJogo(){
        util.Utils ajuda = new Utils();
        System.out.println(ajuda.balaoPequeno("Olá, jovem! Meu nome é Professor Sysor!"));
        try { Thread.sleep(1800);
        } catch (InterruptedException ignored) {}
        System.out.println(ajuda.balaoPequeno("Seja bem vindo(a) ao mundo Pokémon!"));
        try { Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        System.out.println(ajuda.balaoPequeno("Você deve estar ansioso(a) para começar..."));
        try { Thread.sleep(2200);
        } catch (InterruptedException ignored) {}
        System.out.println(ajuda.balaoPequeno("Mas antes me diga uma coisa..."));
        try { Thread.sleep(2000);
            System.out.print("\n");
        } catch (InterruptedException ignored) {}
        String nomeJogador = ajuda.LerString("Qual o seu nome: ");
        System.out.println("\n");
        System.out.println(ajuda.balaoGrande("Olá "+nomeJogador+"! Por favor escolha seu pokemon inicial:"));
        try { Thread.sleep(1000);
            System.out.print("\n");
        } catch (InterruptedException ignored) {}
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
        System.out.print("\n");
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
        Utils ajuda = new Utils();
        for(Pokemon pokemon : pokemons){
            curaPokemon(pokemon);
        }
        System.out.println(ajuda.balaoGrande("Seus pokémons foram curados... Volte sempre!"));
        try { Thread.sleep(2300);
            System.out.println("\n\n\n\n");
        } catch (InterruptedException ignored) {}
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
        Ataque bubble = new Ataque(2,"Bubble", 10, findTipoByName("Agua"));
        this.addNovoAtaque(bubble);
        Ataque ember = new Ataque(1,"Ember", 10, findTipoByName("Fogo"));
        this.addNovoAtaque(ember);
        Ataque tackle = new Ataque(3,"Tackle", 5, findTipoByName("Normal"));
        this.addNovoAtaque(tackle);
        Ataque peck = new Ataque(4, "Peck", 5, findTipoByName("Voador"));
        this.addNovoAtaque(peck);
        Ataque razorBlade = new Ataque(5, "Razor Blade", 10, findTipoByName("Planta"));
        this.addNovoAtaque(razorBlade);
    }

    private void inicializaPokemons(){
        Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", 50, 50,
                findTipoByName("Planta"), findAtaqueByName("Razor Blade"), 5,
                "Femea", false);
        Pokemon ivysaur = new Pokemon(2, "Ivysaur", 160, 160,
                findTipoByName("Planta"), findAtaqueByName("Razor Blade"), 16,
                "Macho", false);
        Pokemon venusaur = new Pokemon(3, "Venusaur", 320, 320,
                findTipoByName("Planta"), findAtaqueByName("Razor Blade"), 32,
                "Macho", false);
        Pokemon charmander = new Pokemon(4,"Charmander",50,50,
                findTipoByName("Fogo"), findAtaqueByName("Ember"),5,
                "Macho",false);
        Pokemon charmeleon = new Pokemon(5,"Charmeleon",160,160,
                findTipoByName("Fogo"), findAtaqueByName("Ember"),16,
                "Femea",false);
        Pokemon charizard = new Pokemon(6,"Charizard",320,320,
                findTipoByName("Fogo"), findAtaqueByName("Ember"),32,
                "Macho",false);
        Pokemon squirtle = new Pokemon(7,"Squirtle",50,50,
                findTipoByName("Agua"), findAtaqueByName("Bubble"),5,
                "Macho",false);
        Pokemon wartortle = new Pokemon(8,"Wartortle",160,160,
                findTipoByName("Agua"), findAtaqueByName("Bubble"),16,
                "Macho",false);
        Pokemon blastoise = new Pokemon(9,"Blastoise",360,360,
                findTipoByName("Agua"), findAtaqueByName("Bubble"),36,
                "Macho",false);
        Pokemon caterpie = new Pokemon(10,"Caterpie",60,60,
                findTipoByName("Inseto"), findAtaqueByName("Tackle"),6,
                "Macho",false);
        Pokemon metapod = new Pokemon(11,"Metapod",70,70,
                findTipoByName("Inseto"), findAtaqueByName("Tackle"),7,
                "Femea",false);
        Pokemon butterfree = new Pokemon(12,"Butterfree",100,100,
                findTipoByName("Inseto"), findAtaqueByName("Tackle"),10,
                "Femea",false);
        Pokemon weedle = new Pokemon(13,"Weedle",60,60,
                findTipoByName("Inseto"), findAtaqueByName("Tackle"),6,
                "Macho",false);
        Pokemon kakuna = new Pokemon(14,"Weedle",70,70,
                findTipoByName("Inseto"), findAtaqueByName("Tackle"),7,
                "Macho",false);
        Pokemon beedrill = new Pokemon(15,"Weedle",100,100,
                findTipoByName("Inseto"), findAtaqueByName("Tackle"),10,
                "Macho",false);
        Pokemon pidgey = new Pokemon(16, "Pidgey", 30, 30, findTipoByName("Voador"),
                findAtaqueByName("Peck"), 3, "Femea", false);
        Pokemon pidgeotto = new Pokemon(17, "Pidgeotto", 180, 180, findTipoByName("Voador"),
                findAtaqueByName("Peck"), 18, "Femea", false);
        Pokemon pidgeot = new Pokemon(18, "Pidgeot", 360, 360, findTipoByName("Voador"),
                findAtaqueByName("Peck"), 36, "Macho", false);
        Pokemon rattata = new Pokemon(19,"Rattata",30,30, findTipoByName("Normal"),
                findAtaqueByName("Tackle"),3,"Macho",false);
        Pokemon raticate = new Pokemon(20,"Raticate",200,200, findTipoByName("Normal"),
                findAtaqueByName("Tackle"),20,"Femea",false);
        Pokemon spearow = new Pokemon(21,"Spearow",60,60, findTipoByName("Normal"),
                findTipoByName("Voador"), findAtaqueByName("Tackle"),6,"Macho",false);
        Pokemon fearow = new Pokemon(22,"Fearow",210,210, findTipoByName("Normal"),
                findTipoByName("Voador"), findAtaqueByName("Tackle"),21,"Macho",false);
        Pokemon ekans = new Pokemon(23,"Ekans",120,120, findTipoByName("Veneno"),
                findAtaqueByName("Tackle"),12,"Femea",false);
        Pokemon arbok = new Pokemon(24,"Arbok",220,220, findTipoByName("Veneno"),
                findAtaqueByName("Tackle"),22,"Macho",false);
        Pokemon pikachu = new Pokemon(25,"Pikachu",80,80, findTipoByName("Eletrico"),
                findAtaqueByName("Tackle"),8,"Femea",false);
        Pokemon raichu = new Pokemon(26,"Raichu",250,250, findTipoByName("Eletrico"),
                findAtaqueByName("Tackle"),25,"Macho",false);
        Pokemon sandshrew = new Pokemon(27,"Sandshrew",100,100, findTipoByName("Terra"),
                findAtaqueByName("Tackle"),10,"Femea",false);
        Pokemon sandslash = new Pokemon(28,"Sandslash",220,220, findTipoByName("Terra"),
                findAtaqueByName("Tackle"),6,"Femea",false);
        Pokemon nidoranF = new Pokemon(29,"Nidoran♀",60,60, findTipoByName("Veneno"),
                findAtaqueByName("Tackle"),6,"Femea",false);
        Pokemon nidorina= new Pokemon(30,"Nidorina",160,160, findTipoByName("Veneno"),
                findAtaqueByName("Tackle"),16,"Masculino",false);
        Pokemon nidoqueen= new Pokemon(31,"Nidoqueen",320,320, findTipoByName("Veneno"),
                findTipoByName("Terra"), findAtaqueByName("Tackle"),32,"Femea",false);
        Pokemon nidoranM= new Pokemon(32,"Nidoran♂", 60, 60, findTipoByName("Veneno"),
                findAtaqueByName("Tackle"),6,"Macho",false);
        Pokemon nidorino= new Pokemon(33,"Nidorino",160 ,160 , findTipoByName("Veneno"),
                findAtaqueByName("Tackle"),16,"Macho",false);
        Pokemon nidoking= new Pokemon(34,"Nidoking", 320, 320 , findTipoByName("Veneno"),
                findTipoByName("Terra"),findAtaqueByName("Tackle"),32,"Macho",false);













        Pokemon moltres = new Pokemon(146,"Moltres", 500, 500, findTipoByName("Fogo"),
                findTipoByName("Voador"), findAtaqueByName("Ember"), 50, "Desconhecido",false);
        Pokemon dratini = new Pokemon(147,"Dratini", 60, 60, findTipoByName("Dragao"),
                findAtaqueByName("Tackle"), 6, "Macho",false);
        Pokemon dragonair = new Pokemon(148,"Dragonair", 300, 300, findTipoByName("Dragao"),
                findAtaqueByName("Tackle"), 30, "Macho",false);
        Pokemon dragonite = new Pokemon(149,"Draqgonite", 550, 550, findTipoByName("Dragao"),
                findTipoByName("Voador"), findAtaqueByName("Tackle"), 55, "Macho",false);
        Pokemon mewtwo = new Pokemon(150,"Mewtwo", 500, 500, findTipoByName("Psiquico"),
                findAtaqueByName("Tackle"), 50, "Desconhecido",false);
        Pokemon mew = new Pokemon(151,"Mew", 500, 500, findTipoByName("Psiquico"),
                findAtaqueByName("Tackle"), 50, "Desconhecido",false);

        pokemons.add(bulbasaur); // 001
        pokemons.add(ivysaur); // 002
        pokemons.add(venusaur); // 003
        pokemons.add(charmander); // 004
        pokemons.add(charmeleon); // 005
        pokemons.add(charizard); // 006
        pokemons.add(squirtle); // 007
        pokemons.add(wartortle); // 008
        pokemons.add(blastoise); // 009
        pokemons.add(caterpie); // 010
        pokemons.add(metapod); // 011
        pokemons.add(butterfree); // 012
        pokemons.add(weedle); // 013
        pokemons.add(kakuna); // 014
        pokemons.add(beedrill); // 015
        pokemons.add(pidgey); // 016
        pokemons.add(pidgeotto); // 017
        pokemons.add(pidgeot); // 018
        pokemons.add(rattata); // 019
        pokemons.add(raticate); // 020
        pokemons.add(spearow); // 021
        pokemons.add(fearow); // 022
        pokemons.add(ekans); // 023
        pokemons.add(arbok); // 024
        pokemons.add(pikachu); // 025



        pokemons.add(nidorina); // 030
        pokemons.add(nidoqueen); // 031
        pokemons.add(nidoranM); // 032
        pokemons.add(nidorino); // 033
        pokemons.add(nidoking); // 034


        pokemons.add(moltres); // 146
        pokemons.add(dratini); // 147
        pokemons.add(dragonair); // 148
        pokemons.add(dragonite); // 149
        pokemons.add(mewtwo); // 150
        pokemons.add(mew); // 151
    }
}
