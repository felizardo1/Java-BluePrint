/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaostock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author felizardojunior
 */
public class UtilDB {

    private static Connection conexao;

    private static void abrirConexao() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection("jdbc:sqlite:banco.sqlite");
            System.out.println("Conexao aberta");
        } catch (SQLException e1) {
            System.err.println("Não foi possível abrir a conexão com o banco!");
        } catch (ClassNotFoundException e2) {
            System.err.println("Ocorreu uma falha ao utilizar o driver!");
        }
    }

    public static void fecharConexao() {
        if (conexao == null) {
            return;
        }
        try {
            if (!conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível fechar a conexão com o banco!");
        }
    }

    public static Connection getConexao() {
        try {
            if (conexao == null) {
                abrirConexao();
            }
            if (conexao.isClosed()) {
                abrirConexao();
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível abrir a conexão com o banco!!");
        }
        return conexao;
    }

    public static void initBD() {
        try {
            conexao = getConexao();
            Statement stm = conexao.createStatement();
            stm.executeUpdate("DROP TABLE IF EXISTS produtos");

  stm.execute("CREATE TABLE IF NOT EXISTS produto (" +
                        "id INTEGER PRIMARY KEY," +
                        "descricao TEXT," +
                        "preco_compra REAL," +
                        "preco_venda REAL)");

            System.out.println("Base de dados criada ");
            stm.close();
        } catch (SQLException e) {
            System.err.println("Não foi possível criar o banco!" + e);
        }
    }

    public static void alterarBD(String query) throws SQLException {
        Connection bd = UtilDB.getConexao();
        Statement stm = bd.createStatement();
        stm.executeUpdate(query);
        stm.close();
    }
}
