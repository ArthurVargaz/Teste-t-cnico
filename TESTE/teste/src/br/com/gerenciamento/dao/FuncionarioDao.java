package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gerenciamento.factory.ConnectionFactory;
import br.com.gerenciamento.models.Funcionario;

public class FuncionarioDao {

    public void save(Funcionario funcionario){
        String sql = "INSERT INTO funcionarios(nome, email, senha, data_criacao) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;

         try{
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getEmail());
            pstm.setString(3, funcionario.getSenha());
            pstm.setDate(4, new Date(funcionario.getDataCadastro().getTime()));

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

    public List<Funcionario> getFuncionarios(){
        String sql = "SELECT * FROM funcionarios";

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rset.getInt("id"));
                funcionario.setNome(rset.getString("nome"));
                funcionario.setEmail(rset.getString("email"));
                funcionario.setSenha(rset.getString("senha"));
                funcionario.setDataCadastro(rset.getDate("data_criacao"));

                funcionarios.add(funcionario);
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



        return funcionarios;
    }

    public void update(Funcionario funcionario){
        String sql = "UPDATE funcionarios SET nome = ?, email = ?, senha = ?"+
        "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getEmail());
            pstm.setString(3, funcionario.getSenha());
            pstm.setInt(4, funcionario.getId());

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

    public Funcionario getFuncionarioById(int id) {
        String sql = "SELECT * FROM funcionarios WHERE id = ?";
        Funcionario funcionario = null;
        
        try (
            Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
        
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
        
            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return funcionario;
    }

    public void deleteById(int id){

        if (!funcionarioExiste(id)) {
            System.out.println("\n======================");
            System.out.println("ID NÃO ENCONTRADO NO BANCO!");
            System.out.println("======================\n");
            return; 
        }

        String sql = "DELETE FROM funcionarios WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();

            System.out.println("\n=================");
            System.out.println("FUNCIONARIO DELETADO COM SUCESSO!");
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

    public boolean funcionarioExiste(int id) {
        String sql = "SELECT 1 FROM funcionarios WHERE id = ?";
        
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

}
