package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gerenciamento.factory.ConnectionFactory;
import br.com.gerenciamento.models.Produto;

public class ProdutoDao {

    public void save(Produto produto){
        String sql = "INSERT INTO produtos(nome, data_criacao, estoque, valor) VALUES (?, ?, ?, ?)";
    
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, produto.getNome());
            pstm.setDate(2, new Date(produto.getDataCadastro().getTime()));
            pstm.setString(3, produto.getEstoque());
            pstm.setString(4, produto.getValor());
            

            pstm.execute();
        }catch (Exception e) {

            e.printStackTrace();

        }finally{

            try {
                if(pstm!=null){
                    pstm.close();
                }

                if(conn!=null){
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
                
            }
    
    }
    }

    public List<Produto> getProdutos(){
        String sql = "SELECT * FROM produtos";

        List<Produto> produtos = new ArrayList<Produto>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Produto produto = new Produto();
                produto.setId(rset.getInt("id"));
                produto.setNome(rset.getString("nome"));
                produto.setDataCadastro(rset.getDate("data_criacao"));
                produto.setEstoque(rset.getString("estoque"));
                produto.setValor(rset.getString("valor"));

                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(rset!=null){
                    rset.close();
                }
                if(pstm!=null){
                    pstm.close();
                }
    
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        return produtos;

    }

    public void update(Produto produto){
        String sql = "UPDATE produtos SET nome = ?, estoque = ?, valor = ?"+
        "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getEstoque());
            pstm.setString(3, produto.getValor());
            pstm.setInt(4, produto.getId());

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Produto getProdutoById(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;
        
        try (
            Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
        
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
        
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setEstoque(rs.getString("estoque"));
                produto.setValor(rs.getString("valor"));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return produto;
    }

    public void deleteById(int id){

        if (!produtoExiste(id)) {
            System.out.println("\n======================");
            System.out.println("ID NÃO ENCONTRADO NO BANCO!");
            System.out.println("======================\n");
            return; 
        }

        String sql = "DELETE FROM produtos WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            System.out.println("\n=================");
            System.out.println("PRODUTO DELETADO COM SUCESSO!");
            System.out.println("==================\n");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
                if(pstm!=null){
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean produtoExiste(int id) {
        String sql = "SELECT 1 FROM produtos WHERE id = ?";
        
        try (
            Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
    
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
    
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
    
            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getString("preco"));
                produto.setEstoque(rs.getString("quantidade"));
                return produto;
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        return null;
    }

}
