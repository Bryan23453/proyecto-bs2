/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_estructuraa;

import java.sql.*;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.swing.JOptionPane;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;
import static org.neo4j.driver.Values.parameters;
import org.neo4j.driver.TransactionWork;
/**
 *
 * @author diego
 */
public class ConexionDB {
    Driver driver;
    String url = "bolt://localhost:7687";
    String user = "neo4j";
    String password = "admin123";
    public ConexionDB(){
        try{
            driver = GraphDatabase.driver(url, AuthTokens.basic(user, password));
            System.out.print("Conectado Exitosamente");
        } catch (Exception ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconectar(){
        driver.close();
    }
}
