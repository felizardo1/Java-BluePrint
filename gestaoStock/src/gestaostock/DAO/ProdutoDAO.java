/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaostock.DAO;

import gestaostock.UtilDB;
import gestaostock.entidades.Produto;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author felizardojunior
 */
public class ProdutoDAO {

    public void adiciona(String descricao, double precoCompra, double precoVenda) {
        String query = "INSERT INTO produto (descricao, preco_compra,  preco_venda) VALUES ('" + descricao + "'," + precoCompra + "," + precoVenda + ")";
        try {
            UtilDB.alterarBD(query);
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(int id) {
        try {
            String query = "DELETE FROM pessoa WHERE id = " + id;
            UtilDB.alterarBD(query);
        } catch (SQLException e) {
            System.err.println("Não foi possível remover a pessoa no banco!");
        }
    }

    public void atualiza(int id, String descricao, double precoCompra, double precoVenda) {
        try {
            String query = "UPDATE pessoa SET descricao = '" + descricao + "', precoCompra = '" + precoCompra
                    + "', precoVenda = " + precoVenda + " WHERE pessoa.id = "
                    + id;
            UtilDB.alterarBD(query);
            
            System.out.println("Atualizado");
        } catch (SQLException e) {
            System.err.println("Não foi possível atualizar os dados da pessoa no banco!");
        }
    }

    public List<Produto> busca() {
        try {
            Connection bd = UtilDB.getConexao();
            Statement stm = bd.createStatement();
            String query = "SELECT * FROM produto";

            try ( ResultSet rs = stm.executeQuery(query)) {

                List<Produto> pessoas = new ArrayList<Produto>();

                while (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getString("descricao"), rs.getDouble("preco_compra"),
                            rs.getDouble("preco_venda"));
                    
                    System.out.println(produto.getId());
                    pessoas.add(produto);
                }

                return pessoas;

            } catch (SQLException e) {
                System.err.println("Falha ao tentar obter o conjunto resultado!");
            }

        } catch (SQLException e) {
            System.err.println("Não foi possível buscar os dados do banco!");
        }
        return null;
    }

}
