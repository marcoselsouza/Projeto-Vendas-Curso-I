/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.ItemVendas;
import br.com.projeto.model.Produtos;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class ItemVendaDao {
    
     private Connection con;
    
    
    public ItemVendaDao(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    //metodo que cadastra itens;
    public void cadastraItem(ItemVendas obj){
        
        try {

            String sql = "insert into tb_itensvendas (venda_id,produto_id,qtd,subtotal) values(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            stmt.execute();
            stmt.close();

           

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public List<ItemVendas> listarItensporVendas(int venda_id ){
        try {
         List<ItemVendas> lista = new ArrayList<>();
       String sql = "select i.id, p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id =?";
       
       PreparedStatement stmt = con.prepareStatement(sql);
       stmt.setInt(1, venda_id);
       
       
       ResultSet rs = stmt.executeQuery();
       
       while (rs.next()){
           ItemVendas item = new ItemVendas();
           Produtos prod = new Produtos();
           
           item.setId(rs.getInt("i.id"));
           prod.setDescricao(rs.getString("p.descricao"));
          item.setQtd(rs.getInt("i.qtd"));
           prod.setPreco(rs.getDouble("p.preco"));
           item.setSubtotal(rs.getDouble("i.subtotal"));
                     
           item.setProduto(prod);
           lista.add(item);
                      
       }
       return lista;
        } catch(SQLException erro){
            
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
       
       
        
        
        
    }
    
}
