package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gerenciamento.factory.ConnectionFactory;
import br.com.gerenciamento.models.Cliente;

public class ClienteDao {

    public void save(Cliente Cliente){

        String sql = "INSERT INTO clientes(nome, email, cpf, senha) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, Cliente.getNome());
            pstm.setString(2, Cliente.getEmail());
            pstm.setString(3, Cliente.getCpf());
            pstm.setString(4, Cliente.getSenha());

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

    public List<Cliente> getClientes(){
        String sql = "SELECT * FROM clientes";

        List<Cliente> clientes = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rset.getInt("id"));
                cliente.setNome(rset.getString("nome"));
                cliente.setEmail(rset.getString("email"));
                cliente.setCpf(rset.getString("cpf"));
                cliente.setSenha(rset.getString("senha"));
                clientes.add(cliente);
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
        return clientes;
    }

    public void update(Cliente cliente){
        String sql = "UPDATE clientes SET nome = ?, email = ?, cpf = ?, senha = ?"+
        "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getEmail());
            pstm.setString(3, cliente.getCpf());
            pstm.setString(4, cliente.getSenha());
            pstm.setInt(5, cliente.getId());

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

    public Cliente getClienteById(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente = null;
        
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
        
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
        
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setSenha(rs.getString("senha"));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return cliente;
    }
    
    public void deleteById(int id) {
        
        if (!clienteExiste(id)) {
            System.out.println("\n======================");
            System.out.println("ID NÃO ENCONTRADO NO BANCO!");
            System.out.println("======================\n");
            return; 
        }
    
        String sql = "DELETE FROM clientes WHERE id = ?";
    
        Connection conn = null;
        PreparedStatement pstm = null;
    
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            
         
            System.out.println("\n=================");
            System.out.println("CLIENTE DELETADO COM SUCESSO!");
            System.out.println("==================\n");
    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean clienteExiste(int id) {
        String sql = "SELECT 1 FROM clientes WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
