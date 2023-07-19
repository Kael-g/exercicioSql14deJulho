package controller;

import connection.Conexao;
import model.Rebelde;
import service.Compras;
import service.GerenciadorRebeldes;
import service.Relatorios;
import service.VisualizarInformacoes;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {

    private Scanner in;
    private Conexao conexao;
    private Statement statement;
    private Compras compras;
    private GerenciadorRebeldes gerenciadorRebeldes;
    private Relatorios relatorios;
    private VisualizarInformacoes visualizarInformacoes;

    public Menu() {
        this.in = new Scanner(System.in);
        this.conexao = new Conexao();
        this.compras = new Compras();
        this.gerenciadorRebeldes = new GerenciadorRebeldes();
        this.relatorios = new Relatorios();
        this.visualizarInformacoes = new VisualizarInformacoes();
    }

    public void iniciar(){
        try {
            conexao.startConnection();
            this.statement = conexao.getConnection().createStatement();
            String opcao="";

            do{
                try {
                    mostraMenu();
                    opcao = in.nextLine();
                    switch (opcao){
                        case "1":
                            addRebelde();
                            break;
                        case "2":
                            atualizarLocal();
                            break;
                        case "3":
                            reportarRebelde();
                            break;
                        case "4":
                            verRebeldes();
                            break;
                        case "5":
                            verRelatorios();
                            break;
                        case "6":
                            verInventario();
                            break;
                        case "7":
                            comprar();
                            break;
                        case "0":
                            System.out.println("Finalizando...");
                            break;
                        default:
                            System.out.println("Opção inválida");

                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            } while (!opcao.equals("0"));

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                conexao.closeConnection();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void mostraMenu(){
        System.out.println("1 - Adicionar rebelde");
        System.out.println("2 - Atualizar localização de rebelde");
        System.out.println("3 - Reportar rebelde como traidor");
        System.out.println("4 - Ver registro de rebeldes");
        System.out.println("5 - Ver relatórios");
        System.out.println("6 - Ver inventário de rebelde");
        System.out.println("7 - Loja");
        System.out.println("0 - Sair");
    }

    private void addRebelde(){
        System.out.println("Digite as informações do novo rebelde");
        System.out.println("Nome:");
        String nome = in.nextLine();

        System.out.println("Idade:");
        int idade = Integer.parseInt(in.nextLine());

        System.out.println("Gênero");
        String genero = in.nextLine();

        System.out.println("Localização:");
        String base = in.nextLine();

        if(gerenciadorRebeldes.adicionarRebelde(statement, new Rebelde(nome, idade, genero, base))) {
            System.out.println("Rebelde adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar rebelde");
        }
    }
    private void atualizarLocal(){
        System.out.println("Digite a ID do rebelde que terá sua localização atualizada:");
        int id = Integer.parseInt(in.nextLine());

        System.out.println("Digite a nova localização:");
        String base = in.nextLine();

        if(gerenciadorRebeldes.atualizarLocalizacao(statement, id, base)) {
            System.out.println("Localização atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar a localização");
        }
    }

    private void reportarRebelde(){
        System.out.println("Digite o ID do rebelde que está fazendo a denúncia:");
        int id_delator = Integer.parseInt(in.nextLine());

        if (gerenciadorRebeldes.verificaIdRebeldeAtivo(statement, id_delator)){
            System.out.println("Digite o id do rebelde acusado de traição:");
            int id_traidor = Integer.parseInt(in.nextLine());
            if (gerenciadorRebeldes.verificaDenunciaIdNovo(statement, id_delator, id_traidor)){
                if (gerenciadorRebeldes.reportarRebeldeTraidor(statement, id_delator, id_traidor)){
                    System.out.println("Denúncia registrada com sucesso.");
                } else {
                    System.out.println("Erro ao registrar denúncia");
                }
            } else {
                System.out.println("Essa denúncia já foi registrada anteriormente");
            }
        } else {
            System.out.println("Não foi possível registrar a denúncia, o ID informado não corresponde a um rebelde ativo");
        }
    }

    private void verRebeldes(){
        System.out.println("Deseja ver todos os rebeldes ou um rebelde específico?");
        System.out.println("1 - Ver todos os rebeldes");
        System.out.println("2 - Ver rebelde específico");

        String opcao = in.nextLine();

        switch (opcao){
            case "1":
                verTodosRebeldes();
                break;
            case "2":
                verRebeldePorId();
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    private void verTodosRebeldes(){
        visualizarInformacoes.mostrarTodosOsRebeldes(statement);
        pause();
    }

    private void verRebeldePorId(){
        System.out.println("Digite o ID do rebelde que deseja ver:");
        int id = Integer.parseInt(in.nextLine());
        visualizarInformacoes.verRebelde(statement, id);
        pause();
    }

    private void verRelatorios(){
        if (relatorios.contarPorcentagemRebeldes(statement)>-1){
            System.out.printf("Rebeldes: %.1f %%\n", relatorios.contarPorcentagemRebeldes(statement));
            System.out.printf("Traidores: %.1f %%\n", relatorios.contarPorcentagemTraidores(statement));
        } else {
            System.out.println("Houve um problema ao gerar relatórios");
        }
        pause();
    }

    private void verInventario(){
        System.out.println("Digite o ID do rebelde para ver seu inventário:");
        int id = Integer.parseInt(in.nextLine());

        visualizarInformacoes.verInventario(statement, id);
        pause();
    }

    private void comprar(){
        System.out.println("Digite o ID do rebelde que está realizando a compra:");
        int id = Integer.parseInt(in.nextLine());
        if (gerenciadorRebeldes.verificaIdRebeldeAtivo(statement, id)){
            visualizarInformacoes.verLoja(statement);

            System.out.println("Digite o ID do produto que deseja comprar:");
            int id_produto = Integer.parseInt(in.nextLine());

            System.out.println("Digite a quantidade:");
            int qtd = Integer.parseInt(in.nextLine());

            if (compras.comprar(statement, id, id_produto, qtd, gerenciadorRebeldes.verificaIdRebeldeAtivo(statement, id))){
                System.out.println("Compra realizada com sucesso!");
            } else {
                System.out.println("Não foi possível realizar a compra");
            }
        } else {
            System.out.println("TRAIDORES NÃO PODEM FAZER COMPRAS!");
        }
    }

    private void pause(){
        System.out.println("Pressione Enter para continuar");
        in.nextLine();
    }
}
