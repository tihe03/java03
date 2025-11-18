import java.util.*;

class Filme {
    private String titulo;
    private String genero;
    private int ano;

    public Filme(String titulo, String genero, int ano) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "🎬 " + titulo + " (" + ano + ") - Gênero: " + genero;
    }
}

class CatalogoFilmes {
    private List<Filme> filmes;
    private int indiceAtual; // Controla o "carrossel"

    public CatalogoFilmes() {
        filmes = new ArrayList<>();
        indiceAtual = 0;
    }

    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
        System.out.println("✅ Filme adicionado: " + filme.getTitulo());
    }

    public void exibirFilmeAtual() {
        if (filmes.isEmpty()) {
            System.out.println("⚠ Nenhum filme no catálogo ainda.");
        } else {
            System.out.println("\n📍 Filme atual do carrossel:");
            System.out.println(filmes.get(indiceAtual));
        }
    }

    public void proximoFilme() {
        if (!filmes.isEmpty()) {
            indiceAtual = (indiceAtual + 1) % filmes.size();
            exibirFilmeAtual();
        }
    }

    public void filmeAnterior() {
        if (!filmes.isEmpty()) {
            indiceAtual = (indiceAtual - 1 + filmes.size()) % filmes.size();
            exibirFilmeAtual();
        }
    }

    public void buscarFilme(String titulo) {
        for (Filme filme : filmes) {
            if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("🔎 Encontrado: " + filme);
                return;
            }
        }
        System.out.println("❌ Filme não encontrado!");
    }

    public void listarTodos() {
        if (filmes.isEmpty()) {
            System.out.println("⚠ Nenhum filme no catálogo.");
        } else {
            System.out.println("\n🎞 Lista completa de filmes:");
            for (Filme filme : filmes) {
                System.out.println(filme);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CatalogoFilmes catalogo = new CatalogoFilmes();

        // Filmes iniciais no estilo Netflix
        catalogo.adicionarFilme(new Filme("Interestelar", "Ficção Científica", 2014));
        catalogo.adicionarFilme(new Filme("O Poderoso Chefão", "Drama", 1972));
        catalogo.adicionarFilme(new Filme("Vingadores: Ultimato", "Ação", 2019));

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n====== 🎬 CATÁLOGO DE FILMES (NETFLIX STYLE) ======");
            System.out.println("1 - Ver filme atual do carrossel");
            System.out.println("2 - Próximo filme ▶");
            System.out.println("3 - Filme anterior ◀");
            System.out.println("4 - Buscar filme pelo título");
            System.out.println("5 - Adicionar novo filme");
            System.out.println("6 - Listar todos os filmes");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> catalogo.exibirFilmeAtual();
                case 2 -> catalogo.proximoFilme();
                case 3 -> catalogo.filmeAnterior();
                case 4 -> {
                    System.out.print("Digite o título do filme: ");
                    String titulo = scanner.nextLine();
                    catalogo.buscarFilme(titulo);
                }
                case 5 -> {
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    catalogo.adicionarFilme(new Filme(titulo, genero, ano));
                }
                case 6 -> catalogo.listarTodos();
                case 0 -> System.out.println("👋 Saindo... até logo!");
                default -> System.out.println("❌ Opção inválida!");
            }
        }
    }
}