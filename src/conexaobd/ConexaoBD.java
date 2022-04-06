/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobd;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {
    
    static final String URL = "jdbc:mysql://localhost:3306/locadora";
    static final String USER = "root";
    static final String PASS = "";    
    
    public static void main(String[] args) {
        // TODO code application logic here
        //insereFilme("Exterminador do Futuro 2", 1991, "acao", "Filme em que a maquinas ganham");
//        alterarFilme(2, "The Batman", 2021, "acao", "Filme de vincanga");
        //deletarFilme(1);
        listaFilme(0);
    }
    
    public static void listaFilme(int codigo){
        String QUERY;
        if(codigo == 0){
            QUERY = "SELECT * FROM filme WHERE ativo = 1";
        }else{
            QUERY = "SELECT * FROM filme WHERE codigo = "+ codigo + " AND ativo = 1";
        }
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                System.out.print("Codigo: " + resultSet.getInt("codigo"));
                System.out.print(" Titulo: " + resultSet.getString("titulo"));
                System.out.print(" Ano: " + resultSet.getInt("ano"));
                System.out.print(" Categoria: " + resultSet.getString("categoria"));
                System.out.println(" Sinopse: " + resultSet.getString("sinopse"));                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
    public static void insereFilme(String titulo, int ano, String categoria, String sinopse){
        String QUERY = "INSERT INTO filme (titulo, ano, categoria, sinopse)"
                + " VALUES('" + titulo + "', " + ano + ", '" + categoria + "', '" + sinopse + "')";
        
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate(QUERY);
            
            System.out.println("Inserido com sucesso");           
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void alterarFilme(int codigo, String titulo, int ano, String categoria, String sinopse){
        String QUERY = "UPDATE filme SET titulo = '" + titulo + "', ano = " + ano + ","
                + " categoria = '" + categoria + "', sinopse = '" + sinopse + "'"
                + " WHERE codigo = "+codigo+"";
        
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate(QUERY);
            
            System.out.println("Alterado com sucesso");           
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void deletarFilme(int codigo){
//        String QUERY = "DELETE FROM filme WHERE codigo = "+codigo;
        String QUERY = "UPDATE filme SET ativo = 0 WHERE codigo = "+codigo;
        
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate(QUERY);
            
            System.out.println("Deletado com sucesso");           
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
