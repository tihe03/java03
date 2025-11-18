//código usando gui.

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Filme {
    private String titulo;
    private String genero;
    private int ano;

    public Filme(String titulo, String genero, int ano) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "<html><div style='text-align: center;'>🎬 <b>" + titulo + "</b><br>(" + ano + ")<br><i>Gênero: " + genero + "</i></div></html>";
    }
}

class CatalogoFilmes {
    private List<Filme> filmes = new ArrayList<>();
    private int indiceAtual = 0;

    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }

    public Filme filmeAtual() {
        if (filmes.isEmpty()) return null;
        return filmes.get(indiceAtual);
    }

    public Filme proximoFilme() {
        if (filmes.isEmpty()) return null;
        indiceAtual = (indiceAtual + 1) % filmes.size();
        return filmeAtual();
    }

    public Filme filmeAnterior() {
        if (filmes.isEmpty()) return null;
        indiceAtual = (indiceAtual - 1 + filmes.size()) % filmes.size();
        return filmeAtual();
    }
}

public class Main {
    public static void main(String[] args) {
        CatalogoFilmes catalogo = new CatalogoFilmes();
        catalogo.adicionarFilme(new Filme("Interestelar", "Ficção Científica", 2014));
        catalogo.adicionarFilme(new Filme("O Poderoso Chefão", "Drama", 1972));
        catalogo.adicionarFilme(new Filme("Vingadores: Ultimato", "Ação", 2019));

        JFrame frame = new JFrame("🎬 Catálogo de Filmes");
        frame.setSize(550, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(245, 246, 250));

        JLabel tituloApp = new JLabel("🎥 Netflix - Catálogo de Filmes", SwingConstants.CENTER);
        tituloApp.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tituloApp.setBorder(new EmptyBorder(10, 0, 10, 0));
        frame.add(tituloApp, BorderLayout.NORTH);

        JLabel labelFilme = new JLabel("Nenhum filme disponível", SwingConstants.CENTER);
        labelFilme.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelFilme.setOpaque(true);
        labelFilme.setBackground(Color.WHITE);
        labelFilme.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        frame.add(labelFilme, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(245, 246, 250));

        JButton btnAnterior = criarBotao("◀ Anterior");
        JButton btnProximo = criarBotao("Próximo ▶");
        JButton btnAdicionar = criarBotao("➕ Adicionar Filme");

        painelBotoes.add(btnAnterior);
        painelBotoes.add(btnProximo);
        painelBotoes.add(btnAdicionar);
        frame.add(painelBotoes, BorderLayout.SOUTH);

        if (catalogo.filmeAtual() != null) {
            labelFilme.setText(catalogo.filmeAtual().toString());
        }

        btnProximo.addActionListener(e -> {
            Filme filme = catalogo.proximoFilme();
            if (filme != null) labelFilme.setText(filme.toString());
        });

        btnAnterior.addActionListener(e -> {
            Filme filme = catalogo.filmeAnterior();
            if (filme != null) labelFilme.setText(filme.toString());
        });

        btnAdicionar.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog("Título do Filme:");
            String genero = JOptionPane.showInputDialog("Gênero do Filme:");
            String anoStr = JOptionPane.showInputDialog("Ano do Filme:");

            if (anoStr != null && anoStr.matches("\\d+")) {
                int ano = Integer.parseInt(anoStr);
                catalogo.adicionarFilme(new Filme(titulo, genero, ano));
                JOptionPane.showMessageDialog(frame, "✅ Filme adicionado!");
            } else {
                JOptionPane.showMessageDialog(frame, "⚠ Ano inválido! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(new Color(52, 152, 219));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(new LineBorder(new Color(41, 128, 185), 2, true));
        botao.setPreferredSize(new Dimension(150, 40));
        return botao;
    }
}