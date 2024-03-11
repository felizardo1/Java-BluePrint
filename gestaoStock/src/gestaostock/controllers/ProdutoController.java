/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaostock.controllers;

import gestaostock.DAO.ProdutoDAO;
import gestaostock.entidades.Produto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felizardojunior
 */
public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void adicionarProduto(String descricao, double precoCompra, double precoVenda) {

        produtoDAO.adiciona(descricao, precoCompra, precoVenda);
    }

    public void removerProduto(int id) {
        produtoDAO.remove(id);
    }

    public void atualizarProduto(int id, String descricao, double precoCompra, double precoVenda) {
        produtoDAO.atualiza(id, descricao, precoCompra, precoVenda);
    }

    public void popularTabelaProduto(JTable tabela) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        // Limpar a tabela antes de popular com novos dados
        model.setRowCount(0);

        // Buscar todos os produtos do banco de dados
        List<Produto> produtos = buscarTodosProdutos();

        // Preencher a tabela com os dados dos produtos
        for (Produto produto : produtos) {
            Object[] rowData = {produto.getId(), produto.getDescricao(), produto.getPrecoCompra(), produto.getPrecoVenda()};
            System.out.println(produto.getId());
            System.out.println(produto.getPrecoVenda());
            model.addRow(rowData);
        }
    }

    // Método para buscar todos os produtos
    public List<Produto> buscarTodosProdutos() {
        return produtoDAO.busca();
    }

}
