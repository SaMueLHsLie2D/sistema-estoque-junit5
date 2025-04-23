package com.example.danhpaiva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SistemaEstoqueTest {

  private SistemaEstoque estoque;

  @BeforeEach
  void setUp() {
    estoque = new SistemaEstoque();
  }

  @Test
  void testAdicionarProdutoComSucesso() {
    estoque.adicionarProduto("Caneta", 10);
    assertEquals(10, estoque.consultarEstoque("Caneta"));
  }

  @Test
  void testAdicionarProdutoNomeInvalido() {
    assertThrows(IllegalArgumentException.class, () -> estoque.adicionarProduto("", 10));
    assertThrows(IllegalArgumentException.class, () -> estoque.adicionarProduto(null, 10));
  }

  @Test
  void testAdicionarProdutoQuantidadeInvalida() {
    assertThrows(IllegalArgumentException.class, () -> estoque.adicionarProduto("Caneta", 0));
    assertThrows(IllegalArgumentException.class, () -> estoque.adicionarProduto("Caneta", -5));
  }

  @Test
  void testRemoverProdutoComSucesso() {
    estoque.adicionarProduto("Caderno", 10);
    estoque.removerProduto("Caderno", 5);
    assertEquals(5, estoque.consultarEstoque("Caderno"));
  }

  @Test
  void testRemoverProdutoZerandoEstoque() {
    estoque.adicionarProduto("Lápis", 5);
    estoque.removerProduto("Lápis", 5);
    assertEquals(0, estoque.consultarEstoque("Lápis"));
  }

  @Test
  void testRemoverProdutoInvalido() {
    assertThrows(IllegalArgumentException.class, () -> estoque.removerProduto("", 5));
    assertThrows(IllegalArgumentException.class, () -> estoque.removerProduto("Caneta", -1));
    assertThrows(IllegalArgumentException.class, () -> estoque.removerProduto("Caneta", 1));
  }

  @Test
  void testConsultarEstoqueProdutoInexistente() {
    assertEquals(0, estoque.consultarEstoque("Borracha"));
  }

  @Test
  void testConsultarEstoqueNomeInvalido() {
    assertThrows(IllegalArgumentException.class, () -> estoque.consultarEstoque(""));
  }

  @Test
  void testObterHistoricoTransacoes() {
    estoque.adicionarProduto("Livro", 3);
    estoque.removerProduto("Livro", 2);
    List<String> historico = estoque.obterHistoricoTransacoes();
    assertEquals(2, historico.size());
    assertTrue(historico.get(0).contains("Adicionado"));
    assertTrue(historico.get(1).contains("Removido"));
  }

  @Test
  void testVerificarDisponibilidadeComSucesso() {
    estoque.adicionarProduto("Mouse", 10);
    assertTrue(estoque.verificarDisponibilidade("Mouse", 5));
  }

  @Test
  void testVerificarDisponibilidadeInsuficiente() {
    estoque.adicionarProduto("Teclado", 2);
    assertFalse(estoque.verificarDisponibilidade("Teclado", 5));
  }

  @Test
  void testVerificarDisponibilidadeProdutoInvalido() {
    assertThrows(IllegalArgumentException.class, () -> estoque.verificarDisponibilidade("", 5));
    assertThrows(IllegalArgumentException.class, () -> estoque.verificarDisponibilidade("Monitor", 0));
  }
}
