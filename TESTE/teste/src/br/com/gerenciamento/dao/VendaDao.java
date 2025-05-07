package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.gerenciamento.factory.ConnectionFactory;
import br.com.gerenciamento.models.ResultadoVenda;


public class VendaDao {

    public ResultadoVenda registrarVenda(int produtoId, int quantidadeVendida) {
        String updateEstoqueSql = "UPDATE produtos SET estoque = estoque - ? WHERE id = ?";
        String insertVendaSql = "INSERT INTO vendas (produto_id, quantidade, valor_total, data_venda) VALUES (?, ?, ?, ?)";
        String selectProdutoSql = "SELECT valor, estoque FROM produtos WHERE id = ?";
    
        Connection conn = null;
        PreparedStatement pstmSelect = null;
        PreparedStatement pstmUpdate = null;
        PreparedStatement pstmInsert = null;
    
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
    
            
            pstmSelect = conn.prepareStatement(selectProdutoSql);
            pstmSelect.setInt(1, produtoId);
            ResultSet rs = pstmSelect.executeQuery();
    
            if (!rs.next()) {
                
                return new ResultadoVenda(false, "ID DO PRODUTO NAO ENCONTRADO!");
            }
    
            
            float valorProduto = rs.getFloat("valor");
            int estoqueAtual = rs.getInt("estoque");
    
            
            if (quantidadeVendida > estoqueAtual) {
                return new ResultadoVenda(false, "ESTOQUE INSUFICIENTE!");
            }
    
            
            pstmUpdate = conn.prepareStatement(updateEstoqueSql);
            pstmUpdate.setInt(1, quantidadeVendida);
            pstmUpdate.setInt(2, produtoId);
            int linhasAfetadas = pstmUpdate.executeUpdate();
    
            if (linhasAfetadas > 0) {
                
                float valorTotal = valorProduto * quantidadeVendida;
    
                pstmInsert = conn.prepareStatement(insertVendaSql);
                pstmInsert.setInt(1, produtoId);
                pstmInsert.setInt(2, quantidadeVendida);
                pstmInsert.setFloat(3, valorTotal);
                pstmInsert.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
    
                int vendaRegistrada = pstmInsert.executeUpdate();
                if (vendaRegistrada > 0) {
                    return new ResultadoVenda(true, "VENDA REGISTRADA COM SUCESSO!");
                } else {
                    return new ResultadoVenda(false, "ERRO AO REGISTRAR A VENDA!");
                }
            } else {
                return new ResultadoVenda(false, "ERRO AO ATUALIZAR O ESTOQUE!");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoVenda(false, "ERRO INESPERADO: " + e.getMessage());
        } finally {
            try {
                if (pstmSelect != null) pstmSelect.close();
                if (pstmUpdate != null) pstmUpdate.close();
                if (pstmInsert != null) pstmInsert.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    
}
