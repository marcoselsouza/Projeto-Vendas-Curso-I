
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;

import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class FornecedoresDao {
    private Connection con;
    
    
    public FornecedoresDao(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    
    public void cadastrarFornecedores(Fornecedores obj){
        try {
            //1 passo - criar o comando sql
            String sql = "insert into tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            //conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            
            stmt.setString(2, obj.getcnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            
            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro" + e);
            
        }
}
 
    public void alterarFornecedores(Fornecedores obj){
        try {
            //1 passo - criar o comando sql
            String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
            //conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getcnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            stmt.setInt(13, obj.getId());
            
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro" + e);
            
        }
    
}

    public void excluirFornecedores(Fornecedores obj){
        try {
            //1 passo - criar o comando sql
            String sql = "delete from tb_fornecedores where id = ?";
            //conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            
            
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro" + e);
            
        }
    
    
}
    
    public List<Fornecedores> listarFornecedores(){
        try{
        //1 passo criar lista
        List<Fornecedores> lista = new ArrayList<>();
        
        //2passo - criar o sql, organizar e executar
        String sql = "select * from tb_fornecedores";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            Fornecedores obj = new Fornecedores();
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            
            obj.setCnpj(rs.getString("cnpj"));
            obj.setEmail(rs.getString("email"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setCelular(rs.getString("celular"));
            obj.setCep(rs.getString("cep"));
            obj.setEndereco(rs.getString("endereco"));
            obj.setNumero(rs.getInt("numero"));
            obj.setComplemento(rs.getString("complemento"));
            obj.setBairro(rs.getString("bairro"));
            obj.setCidade(rs.getString("cidade"));
            obj.setUf(rs.getString("estado"));
            lista.add(obj);
                     
            
        }
        return lista;
            
        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erro" + e);
              return null;
        }
        
        
    }
    
    //metodo consultaClientePorNome
    
    public Fornecedores consultaPorNome(String nome){
        try {
            //1 passo - criar o sql, organizar e executar.
             String sql = "select * from tb_fornecedores where nome=?";
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1, nome);
             
             ResultSet rs = stmt.executeQuery();
             
             Fornecedores obj = new Fornecedores();
             if(rs.next()){
            
            
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
           
            obj.setCnpj(rs.getString("cpf"));
            obj.setEmail(rs.getString("email"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setCelular(rs.getString("celular"));
            obj.setCep(rs.getString("cep"));
            obj.setEndereco(rs.getString("endereco"));
            obj.setNumero(rs.getInt("numero"));
            obj.setComplemento(rs.getString("complemento"));
            obj.setBairro(rs.getString("bairro"));
            obj.setCidade(rs.getString("cidade"));
            obj.setUf(rs.getString("estado"));
             }
             return obj;
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado");
            return null;
        }
        
    }
    
    //metodo buscar cliente por nome
    public List<Fornecedores> buscaFornecedoresPorNome(String nome){
        try{
        //1 passo criar lista
        List<Fornecedores> lista = new ArrayList<>();
        
        //2passo - criar o sql, organizar e executar
        String sql = "select * from tb_fornecedores where nome like ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            Fornecedores obj = new Fornecedores();
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            
            obj.setCnpj(rs.getString("cpf"));
            obj.setEmail(rs.getString("email"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setCelular(rs.getString("celular"));
            obj.setCep(rs.getString("cep"));
            obj.setEndereco(rs.getString("endereco"));
            obj.setNumero(rs.getInt("numero"));
            obj.setComplemento(rs.getString("complemento"));
            obj.setBairro(rs.getString("bairro"));
            obj.setCidade(rs.getString("cidade"));
            obj.setUf(rs.getString("estado"));
            lista.add(obj);
                     
            
        }
        return lista;
            
        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erro" + e);
              return null;
        }
        
        
    }
        
    //busca cep
    	  public Fornecedores buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Fornecedores obj = new Fornecedores();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
        
}
    
