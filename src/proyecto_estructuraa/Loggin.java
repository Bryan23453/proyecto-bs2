/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_estructuraa;

import java.awt.Label;
import java.net.URL;
import javax.swing.JFrame;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author bryan
 */
public class Loggin extends javax.swing.JFrame {
    ConexionDB c;
    String ubicacionAntigua;
    String tipoAntiguo;
    int UPCProductoXAgregar;
    int idTiendaInventario;
    int UPCInventario;
    int idClienteIngresado = 0;
    ArrayList<String> Clientes = new ArrayList<>();
    String CliNom;
    ArrayList<Producto> pro = new ArrayList();
    ArrayList<Vendedor> ven = new ArrayList();
    ArrayList<Producto> pro2 = new ArrayList();
    /**
     * Creates new form Loggin
     */
    public Loggin() {
        initComponents();
        this.setResizable(false);
        this.setSize(1153,680);
        this.setLocationRelativeTo(null);
        Fondo_Login.setVisible(false); 
        Fondo4.setVisible(false);
        Fondo_Cambio.setVisible(false);
        Fondo5.setVisible(false);
        Menu_Admin.setVisible(false);
        Menu_Customer.setVisible(false);
        Label_Incorrecto.setVisible(false);
        Label_Incorrecto1.setVisible(false);
        panel_ingreso.setVisible(false);
        panel_Registro.setVisible(false);
        Menu_Maestro.setVisible(false);
        cambio_ecena_login cm = new cambio_ecena_login(Fondo_Login,Fondo3 ,panel_Registro);
        cm.start();
        
    }
   public void meterClientes(){
        Statement st;
        ResultSet rs;
        
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from vistacliente");
            while(rs.next()){ 
                int id = rs.getInt("idcliente");
                String NumeroCad =  Integer.toString(id);

                String concat = rs.getString("nombreCliente") + " " + NumeroCad;
                Clientes.add(rs.getString("nombreCliente"));
            }
            
            
        }catch(SQLException e){
            System.out.println("Error!!!");
        }
        
    }
    public void mostrarInventario(){
        DefaultTableModel tInventario = new DefaultTableModel();
        tInventario.addColumn("idTienda");
        tInventario.addColumn("nombreTienda");
        tInventario.addColumn("NombreProducto");
        tInventario.addColumn("TamañoProducto");
        tInventario.addColumn("Embalaje");
        tInventario.addColumn("Marca");
        tInventario.addColumn("Precio");
        tInventario.addColumn("CantidadProducto");
        tInventario.addColumn("UbicacionTienda");
        tableReportes.setModel(tInventario);
        
        String []datos = new String[9];
        
        Statement st;
        ResultSet rs;
        
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from 1view_Inv");
            while(rs.next()){
                int id = rs.getInt("idTienda");
                String IntCad =  Integer.toString(id);
                datos[0] = IntCad;
                
                datos[1] = rs.getString("nombreTienda");
                datos[2] = rs.getString("nombreProducto");
                datos[3] = rs.getString("tamanoProducto");
                datos[4] = rs.getString("embalaje");
                datos[5] = rs.getString("marca");
                
                float precio = rs.getFloat("precioProducto");
                String FloatCad1 = Float.toString(precio);
                datos[6] = FloatCad1;
                
                int cantidad = rs.getInt("cantidadProducto");
                String NumeroCad =  Integer.toString(cantidad);
                datos[7] = NumeroCad;
                
                datos[8] = rs.getString("ubicacionTienda");
                
                tInventario.addRow(datos);
            }
            
            tableReportes.setModel(tInventario);
        }catch(SQLException e){
            System.out.println("Error!!!");
        }
        
    }
    
    public void mostrarComprasCliente(){
        //System.out.println(Nom);
        DefaultTableModel tInventario = new DefaultTableModel();
        tInventario.addColumn("NombreCliente");
        tInventario.addColumn("NumeroFactura");
        tInventario.addColumn("FechaFactura");
        tInventario.addColumn("IDProductosComprados");
        tableReportes.setModel(tInventario);
        
        String []datos = new String[4];
        
        Statement st;
        ResultSet rs;
        
        try{
            
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from 2view_CxC");
            String veri = "";
            while(rs.next()){
               
                datos[0] = rs.getString("nombreCliente");
                datos[1] = rs.getString("numeroFactura");

                java.util.Date fecha = rs.getDate("fechaFactura");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = formato.format(fecha);
                datos[2] = fechaFormateada;

                //float total = rs.getString("ProductosComprados");
                String FloatCad = rs.getString("ProductosComprados");
                datos[3] = FloatCad;
                tInventario.addRow(datos);
                
                
            }
            
            tableReportes.setModel(tInventario);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public void mostrarHistorialVentas(){
        DefaultTableModel tInventario = new DefaultTableModel();
        tInventario.addColumn("NombreTienda");
        tInventario.addColumn("NumeroFactura");
        tInventario.addColumn("FechaFactura");
        tInventario.addColumn("Total");
        tableReportes.setModel(tInventario);
        
        String []datos = new String[4];
        
        Statement st;
        ResultSet rs;
        
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from 3view_HistorialVenta");
            while(rs.next()){
                
                datos[0] = rs.getString("nombreTienda");
                datos[1] = rs.getString("numeroFactura");
                
                java.util.Date fecha = rs.getDate("fechaFactura");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = formato.format(fecha);
                datos[2] = fechaFormateada;
                
                float total = rs.getFloat("total");
                String FloatCad = Float.toString(total);
                datos[3] = FloatCad;
                
                
                tInventario.addRow(datos);
            }
            
            tableReportes.setModel(tInventario);
        }catch(SQLException e){
            System.out.println("Error!!!");
        }
        
    }
    public void mostrarTop20Tiendas(){
        DefaultTableModel tInventario = new DefaultTableModel();
        tInventario.addColumn("NombreTienda");
        tInventario.addColumn("NombreProducto");
        tInventario.addColumn("CantidadVendida");
        tableReportes.setModel(tInventario);
        
        String []datos = new String[3];
        
        Statement st;
        ResultSet rs;
        
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from 4view_Top20Tienda");
            while(rs.next()){
                
                datos[0] = rs.getString("nombreTienda");
                datos[1] = rs.getString("nombreProducto");
                
                int compras = rs.getInt("CantidadVendida");
                String NumeroCad1 =  Integer.toString(compras);
                datos[2] = NumeroCad1;
                   
                tInventario.addRow(datos);
            }
            
            tableReportes.setModel(tInventario);
        }catch(SQLException e){
            System.out.println("Error!!!");
        }
        
    }
    
    public void mostrarTop20TiendasUbi(){
        DefaultTableModel tInventario = new DefaultTableModel();
        tInventario.addColumn("UbicacionTienda");
        tInventario.addColumn("NombreProducto");
        tInventario.addColumn("CantidadVendida");
        tableReportes.setModel(tInventario);
        
        String []datos = new String[3];
        
        Statement st;
        ResultSet rs;
        
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from 5view_Top20Ubi");
            while(rs.next()){
                
                datos[0] = rs.getString("ubicacionTienda");
                datos[1] = rs.getString("nombreProducto");
                
                int compras = rs.getInt("CantidadVendida");
                String NumeroCad1 =  Integer.toString(compras);
                datos[2] = NumeroCad1;
                   
                tInventario.addRow(datos);
            }
            
            tableReportes.setModel(tInventario);
        }catch(SQLException e){
            System.out.println("Error!!!");
        }
        
    }
    
    public void mostrarTiendsVentas(){
        DefaultTableModel tInventario = new DefaultTableModel();
        tInventario.addColumn("NombreTienda");
        tInventario.addColumn("VentasAnuales");
        tableReportes.setModel(tInventario);
        
        String []datos = new String[3];
        
        Statement st;
        ResultSet rs;
        
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from 6view_Top5");
            while(rs.next()){
                
                datos[0] = rs.getString("nombreTienda");
                
                
                float total = rs.getFloat("VentasAnuales");
                String FloatCad = Float.toString(total);
                datos[1] = FloatCad;
                   
                tInventario.addRow(datos);
            }
            
            tableReportes.setModel(tInventario);
        }catch(SQLException e){
            System.out.println("Error!!!");
        }
        
    }
    
    // falta 2
    public void mostrarCocaCola(){
        DefaultTableModel tInventario = new DefaultTableModel();
        tInventario.addColumn("NombreTienda");
        tInventario.addColumn("VentasCocaCola");
        tInventario.addColumn("VentasPepsi");
        tableReportes.setModel(tInventario);
        
        String []datos = new String[3];
        
        Statement st;
        ResultSet rs;
        
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from 7view_CocaCola");
            while(rs.next()){
                
                datos[0] = rs.getString("ubicacionTienda");
                
                int compras = rs.getInt("VentasCocaCola");
                String NumeroCad =  Integer.toString(compras);
                datos[1] = NumeroCad;
                
                
                int compras1 = rs.getInt("VentasPepsi");
                String NumeroCad1 =  Integer.toString(compras);
                datos[2] = NumeroCad1;
                   
                tInventario.addRow(datos);
            }
            
            tableReportes.setModel(tInventario);
        }catch(SQLException e){
            System.out.println("Error!!!");
        }
        
    }
    
     public void mostrarAntiLeche(){
        DefaultTableModel tInventario = new DefaultTableModel();
        tInventario.addColumn("TipoProducto");
        tInventario.addColumn("CantidadCompras");
        tableReportes.setModel(tInventario);
        
        String []datos = new String[2];
        
        Statement st;
        ResultSet rs;
        
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("select * from 8view_ExLeche");
            while(rs.next()){
                datos[0] = rs.getString("tipoProducto");
                
                int compras = rs.getInt("CantidadCompras");
                String NumeroCad =  Integer.toString(compras);
                datos[1] = NumeroCad;

                   
                tInventario.addRow(datos);
            }
            
            tableReportes.setModel(tInventario);
        }catch(SQLException e){
            System.out.println("Error!!!");
        }
        
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu_Admin = new javax.swing.JPanel();
        Panel_Informes = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        ComboReportes = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableReportes = new javax.swing.JTable();
        Panel_Financiero = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Panel_Notas = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton14 = new javax.swing.JToggleButton();
        jToggleButton15 = new javax.swing.JToggleButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        Panel_Clases = new javax.swing.JTabbedPane();
        Panel_Vendedorsitos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tf_nombreVendedor = new javax.swing.JTextField();
        tf_idVendedor = new javax.swing.JTextField();
        btn_updateVendedor = new javax.swing.JButton();
        btn_deleteVendedor = new javax.swing.JButton();
        btn_createVendedor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Vendedores = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        cbox_ListaVendedores = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        table_ProductosparaVendedor = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        Panel_Maestros = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        Panel_Alumnos = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tf_embalajeProducto = new javax.swing.JTextField();
        tf_UPC = new javax.swing.JTextField();
        tf_nombreProducto = new javax.swing.JTextField();
        tf_tamanoProducto = new javax.swing.JTextField();
        btn_deleteProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Productos = new javax.swing.JTable();
        btn_createProducto = new javax.swing.JButton();
        btn_updateProducto = new javax.swing.JButton();
        tf_marcaProducto = new javax.swing.JTextField();
        Panel_Carro = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tf_idCliente = new javax.swing.JTextField();
        tf_nombreCliente = new javax.swing.JTextField();
        btn_deleteCliente = new javax.swing.JButton();
        btn_createCliente = new javax.swing.JButton();
        btn_updateCliente = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_Clientes = new javax.swing.JTable();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        Panel_menu_abajo = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        Panel_Menu_Izquierda = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Icono_Clase = new javax.swing.JLabel();
        Icono_Carro = new javax.swing.JLabel();
        Icono_Maestra = new javax.swing.JLabel();
        Icono_Alumno = new javax.swing.JLabel();
        Icono_Notas = new javax.swing.JLabel();
        Icono_Financiero = new javax.swing.JLabel();
        Icono_Informe = new javax.swing.JLabel();
        Fondo5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Menu_Maestro = new javax.swing.JPanel();
        Panel_Bitacora2 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        ta_Bitacora2 = new javax.swing.JTextArea();
        Panel_Menu_Izquierda2 = new javax.swing.JPanel();
        Icono_Tienda2 = new javax.swing.JLabel();
        Icono_Producto2 = new javax.swing.JLabel();
        Icono_Vendedor2 = new javax.swing.JLabel();
        Icono_Cliente2 = new javax.swing.JLabel();
        Panel_Informes2 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        ComboReportes2 = new javax.swing.JComboBox<>();
        jScrollPane26 = new javax.swing.JScrollPane();
        tableReportes2 = new javax.swing.JTable();
        Panel_Cliente2 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        tf_idCliente2 = new javax.swing.JTextField();
        tf_nombreCliente2 = new javax.swing.JTextField();
        tf_correoCliente2 = new javax.swing.JTextField();
        btn_deleteCliente2 = new javax.swing.JButton();
        btn_createCliente2 = new javax.swing.JButton();
        btn_updateCliente2 = new javax.swing.JButton();
        jScrollPane27 = new javax.swing.JScrollPane();
        table_Clientes2 = new javax.swing.JTable();
        Panel_Productos2 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        tf_embalajeProducto2 = new javax.swing.JTextField();
        tf_UPC2 = new javax.swing.JTextField();
        tf_nombreProducto2 = new javax.swing.JTextField();
        tf_tamanoProducto2 = new javax.swing.JTextField();
        btn_deleteProducto2 = new javax.swing.JButton();
        jScrollPane28 = new javax.swing.JScrollPane();
        table_Productos2 = new javax.swing.JTable();
        btn_createProducto2 = new javax.swing.JButton();
        btn_updateProducto2 = new javax.swing.JButton();
        tf_marcaProducto2 = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        tf_tipoProducto2 = new javax.swing.JTextField();
        Panel_vendedores2 = new javax.swing.JTabbedPane();
        Panel_Vendedorsitos2 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        tf_nombreVendedor2 = new javax.swing.JTextField();
        tf_idVendedor2 = new javax.swing.JTextField();
        btn_updateVendedor2 = new javax.swing.JButton();
        btn_deleteVendedor2 = new javax.swing.JButton();
        btn_createVendedor2 = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        table_Vendedores2 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        cbox_ListaVendedores2 = new javax.swing.JComboBox<>();
        jScrollPane30 = new javax.swing.JScrollPane();
        table_ProductosparaVendedor2 = new javax.swing.JTable();
        jScrollPane31 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        Panel_menu_abajo2 = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        Panel_Factura2 = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jScrollPane32 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        Panel_Tienda2 = new javax.swing.JTabbedPane();
        Panel_Tiendita2 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        tf_idTienda2 = new javax.swing.JTextField();
        jLabel143 = new javax.swing.JLabel();
        tf_nombreTienda2 = new javax.swing.JTextField();
        jLabel144 = new javax.swing.JLabel();
        tf_horarioTienda2 = new javax.swing.JTextField();
        jScrollPane33 = new javax.swing.JScrollPane();
        table_Tiendas2 = new javax.swing.JTable();
        btn_modificarTienda2 = new javax.swing.JToggleButton();
        btn_eliminarTienda2 = new javax.swing.JToggleButton();
        btn_crearTienda2 = new javax.swing.JToggleButton();
        jLabel145 = new javax.swing.JLabel();
        tf_ubicacionTienda2 = new javax.swing.JTextField();
        jLabel146 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        cbox_NombreTiendas2 = new javax.swing.JComboBox<>();
        jScrollPane34 = new javax.swing.JScrollPane();
        table_TodosProductos2 = new javax.swing.JTable();
        jScrollPane35 = new javax.swing.JScrollPane();
        table_ProductosXTienda2 = new javax.swing.JTable();
        btn_agregarProductoTienda2 = new javax.swing.JButton();
        tf_precio2 = new javax.swing.JTextField();
        tf_cantidad2 = new javax.swing.JTextField();
        tf_reorden2 = new javax.swing.JTextField();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        btn_eliminarInventario2 = new javax.swing.JToggleButton();
        btn_modifyInventario2 = new javax.swing.JToggleButton();
        Fondo7 = new javax.swing.JLabel();
        Menu_Customer = new javax.swing.JPanel();
        Panel_Bitacora1 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        ta_Bitacora1 = new javax.swing.JTextArea();
        Panel_Menu_Izquierda1 = new javax.swing.JPanel();
        Icono_Tienda1 = new javax.swing.JLabel();
        Icono_Producto1 = new javax.swing.JLabel();
        Icono_Vendedor1 = new javax.swing.JLabel();
        Icono_Factura1 = new javax.swing.JLabel();
        Icono_Cliente1 = new javax.swing.JLabel();
        Panel_Informes1 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        ComboReportes1 = new javax.swing.JComboBox<>();
        jScrollPane15 = new javax.swing.JScrollPane();
        tableReportes1 = new javax.swing.JTable();
        Panel_Cliente1 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        tf_idCliente1 = new javax.swing.JTextField();
        tf_nombreCliente1 = new javax.swing.JTextField();
        tf_correoCliente1 = new javax.swing.JTextField();
        btn_deleteCliente1 = new javax.swing.JButton();
        btn_createCliente1 = new javax.swing.JButton();
        btn_updateCliente1 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        table_Clientes1 = new javax.swing.JTable();
        Panel_Productos1 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        tf_embalajeProducto1 = new javax.swing.JTextField();
        tf_UPC1 = new javax.swing.JTextField();
        tf_nombreProducto1 = new javax.swing.JTextField();
        tf_tamanoProducto1 = new javax.swing.JTextField();
        btn_deleteProducto1 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        table_Productos1 = new javax.swing.JTable();
        btn_createProducto1 = new javax.swing.JButton();
        btn_updateProducto1 = new javax.swing.JButton();
        tf_marcaProducto1 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        tf_tipoProducto1 = new javax.swing.JTextField();
        Panel_vendedores1 = new javax.swing.JTabbedPane();
        Panel_Vendedorsitos1 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        tf_nombreVendedor1 = new javax.swing.JTextField();
        tf_idVendedor1 = new javax.swing.JTextField();
        btn_updateVendedor1 = new javax.swing.JButton();
        btn_deleteVendedor1 = new javax.swing.JButton();
        btn_createVendedor1 = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        table_Vendedores1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        cbox_ListaVendedores1 = new javax.swing.JComboBox<>();
        jScrollPane19 = new javax.swing.JScrollPane();
        table_ProductosparaVendedor1 = new javax.swing.JTable();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        Panel_menu_abajo1 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        Panel_Factura1 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        Panel_Tienda1 = new javax.swing.JTabbedPane();
        Panel_Tiendita1 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        tf_idTienda1 = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        tf_nombreTienda1 = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        tf_horarioTienda1 = new javax.swing.JTextField();
        jScrollPane22 = new javax.swing.JScrollPane();
        table_Tiendas1 = new javax.swing.JTable();
        btn_modificarTienda1 = new javax.swing.JToggleButton();
        btn_eliminarTienda1 = new javax.swing.JToggleButton();
        btn_crearTienda1 = new javax.swing.JToggleButton();
        jLabel111 = new javax.swing.JLabel();
        tf_ubicacionTienda1 = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        cbox_NombreTiendas1 = new javax.swing.JComboBox<>();
        jScrollPane23 = new javax.swing.JScrollPane();
        table_TodosProductos1 = new javax.swing.JTable();
        jScrollPane24 = new javax.swing.JScrollPane();
        table_ProductosXTienda1 = new javax.swing.JTable();
        btn_agregarProductoTienda1 = new javax.swing.JButton();
        tf_precio1 = new javax.swing.JTextField();
        tf_cantidad1 = new javax.swing.JTextField();
        tf_reorden1 = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        btn_eliminarInventario1 = new javax.swing.JToggleButton();
        btn_modifyInventario1 = new javax.swing.JToggleButton();
        Fondo6 = new javax.swing.JLabel();
        panel_Registro = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        Correo = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        Boton_Ingreso3 = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        Label_Incorrecto1 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        contra = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        panel_ingreso = new javax.swing.JPanel();
        Usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Boton_Ingreso = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Label_Incorrecto = new javax.swing.JLabel();
        Contra = new javax.swing.JPasswordField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        Contra1 = new javax.swing.JPasswordField();
        Usuario1 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        Usuario2 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        Fondo4 = new javax.swing.JLabel();
        Fondo3 = new javax.swing.JLabel();
        Fondo_Login = new javax.swing.JLabel();
        Fondo_Cambio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        Menu_Admin.setLayout(null);

        Panel_Informes.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Informes.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Informes.setLayout(null);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(137, 250, 230));
        jLabel30.setText("Informes");
        Panel_Informes.add(jLabel30);
        jLabel30.setBounds(470, 0, 170, 60);

        ComboReportes.setForeground(new java.awt.Color(102, 102, 102));
        ComboReportes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vista 1", "Vista 2", "Vista 3", "Vista 4", "Vista 5", "Vista 6", "Vista 7", "Vista 8" }));
        ComboReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboReportesActionPerformed(evt);
            }
        });
        Panel_Informes.add(ComboReportes);
        ComboReportes.setBounds(470, 60, 170, 40);

        tableReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tableReportes);

        Panel_Informes.add(jScrollPane6);
        jScrollPane6.setBounds(30, 110, 960, 430);

        Menu_Admin.add(Panel_Informes);
        Panel_Informes.setBounds(1220, 0, 1080, 610);

        Panel_Financiero.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Financiero.setLayout(null);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(137, 250, 230));
        jLabel48.setText("Informe Dinero");
        Panel_Financiero.add(jLabel48);
        jLabel48.setBounds(480, 0, 290, 50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable1);

        Panel_Financiero.add(jScrollPane7);
        jScrollPane7.setBounds(60, 100, 970, 440);

        Menu_Admin.add(Panel_Financiero);
        Panel_Financiero.setBounds(1230, 0, 1080, 610);

        Panel_Notas.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Notas.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Notas.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Notas.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(137, 250, 230));
        jLabel21.setText("Notas");
        Panel_Notas.add(jLabel21);
        jLabel21.setBounds(470, 0, 270, 70);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(137, 250, 230));
        jLabel32.setText("Alumno");
        Panel_Notas.add(jLabel32);
        jLabel32.setBounds(30, 100, 140, 30);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(137, 250, 230));
        jLabel35.setText("Nota");
        Panel_Notas.add(jLabel35);
        jLabel35.setBounds(30, 190, 100, 30);

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(137, 250, 230));
        jLabel36.setText("Clase");
        Panel_Notas.add(jLabel36);
        jLabel36.setBounds(30, 20, 100, 30);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTable6);

        Panel_Notas.add(jScrollPane8);
        jScrollPane8.setBounds(280, 90, 710, 460);

        jToggleButton13.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton13.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton13.setText("Agregar");
        Panel_Notas.add(jToggleButton13);
        jToggleButton13.setBounds(50, 380, 140, 50);

        jToggleButton14.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton14.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton14.setText("Eliminar");
        Panel_Notas.add(jToggleButton14);
        jToggleButton14.setBounds(50, 500, 140, 50);

        jToggleButton15.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton15.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton15.setText("Modificar");
        Panel_Notas.add(jToggleButton15);
        jToggleButton15.setBounds(50, 440, 140, 50);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Notas.add(jComboBox2);
        jComboBox2.setBounds(20, 50, 180, 40);

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Notas.add(jComboBox9);
        jComboBox9.setBounds(20, 130, 180, 40);

        jTextField1.setText("jTextField1");
        Panel_Notas.add(jTextField1);
        jTextField1.setBounds(30, 230, 160, 40);

        Menu_Admin.add(Panel_Notas);
        Panel_Notas.setBounds(1230, 0, 1080, 610);

        Panel_Vendedorsitos.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Vendedorsitos.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Vendedorsitos.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(137, 250, 230));
        jLabel5.setText("Clases");
        Panel_Vendedorsitos.add(jLabel5);
        jLabel5.setBounds(400, 0, 270, 70);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(137, 250, 230));
        jLabel15.setText("ID");
        Panel_Vendedorsitos.add(jLabel15);
        jLabel15.setBounds(40, 40, 130, 25);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(137, 250, 230));
        jLabel16.setText("Nombre");
        Panel_Vendedorsitos.add(jLabel16);
        jLabel16.setBounds(40, 120, 130, 25);

        tf_nombreVendedor.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreVendedor.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos.add(tf_nombreVendedor);
        tf_nombreVendedor.setBounds(40, 150, 260, 30);

        tf_idVendedor.setBackground(new java.awt.Color(102, 102, 102));
        tf_idVendedor.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos.add(tf_idVendedor);
        tf_idVendedor.setBounds(40, 70, 260, 30);

        btn_updateVendedor.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateVendedor.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateVendedor.setText("Modificar");
        btn_updateVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateVendedorMouseClicked(evt);
            }
        });
        Panel_Vendedorsitos.add(btn_updateVendedor);
        btn_updateVendedor.setBounds(110, 380, 120, 40);

        btn_deleteVendedor.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteVendedor.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteVendedor.setText("Eliminar");
        btn_deleteVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteVendedorMouseClicked(evt);
            }
        });
        Panel_Vendedorsitos.add(btn_deleteVendedor);
        btn_deleteVendedor.setBounds(110, 450, 120, 40);

        btn_createVendedor.setBackground(new java.awt.Color(204, 204, 204));
        btn_createVendedor.setForeground(new java.awt.Color(0, 0, 0));
        btn_createVendedor.setText("Añadir");
        btn_createVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createVendedorMouseClicked(evt);
            }
        });
        Panel_Vendedorsitos.add(btn_createVendedor);
        btn_createVendedor.setBounds(110, 310, 120, 40);

        table_Vendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Vendedor", "Nombre Vendedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table_Vendedores);

        Panel_Vendedorsitos.add(jScrollPane2);
        jScrollPane2.setBounds(340, 100, 680, 440);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(137, 250, 230));
        jLabel17.setText("Maestro");
        Panel_Vendedorsitos.add(jLabel17);
        jLabel17.setBounds(40, 200, 130, 25);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Vendedorsitos.add(jComboBox1);
        jComboBox1.setBounds(40, 230, 250, 40);

        Panel_Clases.addTab("Clases", Panel_Vendedorsitos);

        jPanel5.setBackground(new java.awt.Color(0,0,0,200));
        jPanel5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(null);

        jLabel55.setBackground(new java.awt.Color(137, 250, 230));
        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(137, 250, 230));
        jLabel55.setText("Clase");
        jPanel5.add(jLabel55);
        jLabel55.setBounds(40, 10, 180, 70);

        cbox_ListaVendedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbox_ListaVendedores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbox_ListaVendedoresItemStateChanged(evt);
            }
        });
        cbox_ListaVendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbox_ListaVendedoresMouseClicked(evt);
            }
        });
        jPanel5.add(cbox_ListaVendedores);
        cbox_ListaVendedores.setBounds(220, 30, 310, 40);

        table_ProductosparaVendedor.setBackground(new java.awt.Color(153, 153, 153));
        table_ProductosparaVendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(table_ProductosparaVendedor);

        jPanel5.add(jScrollPane11);
        jScrollPane11.setBounds(30, 120, 430, 440);

        jTable11.setBackground(new java.awt.Color(153, 153, 153));
        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane12.setViewportView(jTable11);

        jPanel5.add(jScrollPane12);
        jScrollPane12.setBounds(610, 120, 430, 440);

        jButton12.setBackground(new java.awt.Color(204, 204, 204));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 0, 0));
        jButton12.setText("<-");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });
        jPanel5.add(jButton12);
        jButton12.setBounds(500, 340, 80, 50);

        jLabel56.setBackground(new java.awt.Color(137, 250, 230));
        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(137, 250, 230));
        jLabel56.setText("Lista Alumnos");
        jPanel5.add(jLabel56);
        jLabel56.setBounds(190, 90, 130, 20);

        jLabel57.setBackground(new java.awt.Color(137, 250, 230));
        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(137, 250, 230));
        jLabel57.setText("Lista Alumnos En Clase");
        jPanel5.add(jLabel57);
        jLabel57.setBounds(730, 90, 210, 20);

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 0, 0));
        jButton13.setText("->");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jPanel5.add(jButton13);
        jButton13.setBounds(500, 270, 80, 50);

        Panel_Clases.addTab("Alumnos", jPanel5);

        Menu_Admin.add(Panel_Clases);
        Panel_Clases.setBounds(1230, 0, 1080, 610);

        Panel_Maestros.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Maestros.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Maestros.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Maestros.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(137, 250, 230));
        jLabel4.setText("Maestros");
        Panel_Maestros.add(jLabel4);
        jLabel4.setBounds(470, 0, 270, 70);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(137, 250, 230));
        jLabel14.setText("Nivel De Curso");
        Panel_Maestros.add(jLabel14);
        jLabel14.setBounds(30, 90, 140, 30);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(137, 250, 230));
        jLabel18.setText("Correo");
        Panel_Maestros.add(jLabel18);
        jLabel18.setBounds(30, 230, 100, 30);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(137, 250, 230));
        jLabel19.setText("Usuario");
        Panel_Maestros.add(jLabel19);
        jLabel19.setBounds(30, 300, 100, 30);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(137, 250, 230));
        jLabel20.setText("Nombre");
        Panel_Maestros.add(jLabel20);
        jLabel20.setBounds(30, 160, 100, 30);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(137, 250, 230));
        jLabel22.setText("ID");
        Panel_Maestros.add(jLabel22);
        jLabel22.setBounds(30, 20, 100, 30);

        jTextField7.setBackground(new java.awt.Color(102, 102, 102));
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.setText("jTextField7");
        Panel_Maestros.add(jTextField7);
        jTextField7.setBounds(30, 50, 190, 30);

        jFormattedTextField1.setText("jFormattedTextField1");
        Panel_Maestros.add(jFormattedTextField1);
        jFormattedTextField1.setBounds(30, 120, 190, 30);

        jTextField8.setBackground(new java.awt.Color(102, 102, 102));
        jTextField8.setForeground(new java.awt.Color(255, 255, 255));
        jTextField8.setText("jTextField8");
        Panel_Maestros.add(jTextField8);
        jTextField8.setBounds(30, 190, 190, 30);

        jTextField9.setBackground(new java.awt.Color(102, 102, 102));
        jTextField9.setForeground(new java.awt.Color(255, 255, 255));
        jTextField9.setText("jTextField9");
        Panel_Maestros.add(jTextField9);
        jTextField9.setBounds(30, 260, 190, 30);

        jTextField10.setBackground(new java.awt.Color(102, 102, 102));
        jTextField10.setForeground(new java.awt.Color(102, 102, 102));
        jTextField10.setText("jTextField10");
        Panel_Maestros.add(jTextField10);
        jTextField10.setBounds(30, 330, 190, 30);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        Panel_Maestros.add(jScrollPane3);
        jScrollPane3.setBounds(280, 90, 710, 460);

        jToggleButton4.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton4.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton4.setText("Agregar");
        Panel_Maestros.add(jToggleButton4);
        jToggleButton4.setBounds(50, 380, 140, 50);

        jToggleButton5.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton5.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton5.setText("Eliminar");
        Panel_Maestros.add(jToggleButton5);
        jToggleButton5.setBounds(50, 500, 140, 50);

        jToggleButton6.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton6.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton6.setText("Modificar");
        Panel_Maestros.add(jToggleButton6);
        jToggleButton6.setBounds(50, 440, 140, 50);

        Menu_Admin.add(Panel_Maestros);
        Panel_Maestros.setBounds(1230, 0, 1080, 610);

        Panel_Alumnos.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Alumnos.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Alumnos.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Alumnos.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(137, 250, 230));
        jLabel6.setText("Usuarios");
        Panel_Alumnos.add(jLabel6);
        jLabel6.setBounds(500, 10, 270, 70);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(137, 250, 230));
        jLabel9.setText("ID");
        Panel_Alumnos.add(jLabel9);
        jLabel9.setBounds(70, 50, 37, 20);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(137, 250, 230));
        jLabel10.setText("Nombre");
        Panel_Alumnos.add(jLabel10);
        jLabel10.setBounds(70, 100, 80, 20);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(137, 250, 230));
        jLabel11.setText("Correo");
        Panel_Alumnos.add(jLabel11);
        jLabel11.setBounds(70, 150, 80, 20);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(137, 250, 230));
        jLabel12.setText("Usuario");
        Panel_Alumnos.add(jLabel12);
        jLabel12.setBounds(70, 200, 80, 30);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(137, 250, 230));
        jLabel13.setText("Contrase;a");
        Panel_Alumnos.add(jLabel13);
        jLabel13.setBounds(70, 260, 80, 20);

        tf_embalajeProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_embalajeProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Alumnos.add(tf_embalajeProducto);
        tf_embalajeProducto.setBounds(70, 230, 220, 30);

        tf_UPC.setBackground(new java.awt.Color(102, 102, 102));
        tf_UPC.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Alumnos.add(tf_UPC);
        tf_UPC.setBounds(70, 70, 220, 30);

        tf_nombreProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Alumnos.add(tf_nombreProducto);
        tf_nombreProducto.setBounds(70, 120, 220, 30);

        tf_tamanoProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_tamanoProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Alumnos.add(tf_tamanoProducto);
        tf_tamanoProducto.setBounds(70, 170, 220, 30);

        btn_deleteProducto.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteProducto.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteProducto.setText("Eliminar");
        btn_deleteProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteProductoMouseClicked(evt);
            }
        });
        Panel_Alumnos.add(btn_deleteProducto);
        btn_deleteProducto.setBounds(90, 490, 170, 40);

        jScrollPane1.setViewportView(table_Productos);

        Panel_Alumnos.add(jScrollPane1);
        jScrollPane1.setBounds(350, 100, 670, 450);

        btn_createProducto.setBackground(new java.awt.Color(204, 204, 204));
        btn_createProducto.setForeground(new java.awt.Color(0, 0, 0));
        btn_createProducto.setText("Agregar");
        btn_createProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createProductoMouseClicked(evt);
            }
        });
        Panel_Alumnos.add(btn_createProducto);
        btn_createProducto.setBounds(90, 370, 170, 40);

        btn_updateProducto.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateProducto.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateProducto.setText("Editar");
        btn_updateProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateProductoMouseClicked(evt);
            }
        });
        Panel_Alumnos.add(btn_updateProducto);
        btn_updateProducto.setBounds(90, 430, 170, 40);

        tf_marcaProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_marcaProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Alumnos.add(tf_marcaProducto);
        tf_marcaProducto.setBounds(70, 280, 220, 30);

        Menu_Admin.add(Panel_Alumnos);
        Panel_Alumnos.setBounds(1230, 0, 1080, 610);

        Panel_Carro.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Carro.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Carro.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Carro.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(137, 250, 230));
        jLabel3.setText("Carros");
        Panel_Carro.add(jLabel3);
        jLabel3.setBounds(420, 10, 270, 70);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(137, 250, 230));
        jLabel23.setText("Modelo Auto");
        Panel_Carro.add(jLabel23);
        jLabel23.setBounds(50, 130, 130, 30);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(137, 250, 230));
        jLabel24.setText("Tipo De Auto");
        Panel_Carro.add(jLabel24);
        jLabel24.setBounds(50, 210, 130, 30);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(137, 250, 230));
        jLabel25.setText("Matricula");
        Panel_Carro.add(jLabel25);
        jLabel25.setBounds(50, 50, 90, 30);

        tf_idCliente.setBackground(new java.awt.Color(102, 102, 102));
        tf_idCliente.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Carro.add(tf_idCliente);
        tf_idCliente.setBounds(50, 80, 210, 30);

        tf_nombreCliente.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Carro.add(tf_nombreCliente);
        tf_nombreCliente.setBounds(50, 160, 210, 30);

        btn_deleteCliente.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteCliente.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteCliente.setText("Eliminar");
        btn_deleteCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteClienteMouseClicked(evt);
            }
        });
        Panel_Carro.add(btn_deleteCliente);
        btn_deleteCliente.setBounds(60, 550, 160, 40);

        btn_createCliente.setBackground(new java.awt.Color(204, 204, 204));
        btn_createCliente.setForeground(new java.awt.Color(0, 0, 0));
        btn_createCliente.setText("Añadir");
        btn_createCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createClienteMouseClicked(evt);
            }
        });
        Panel_Carro.add(btn_createCliente);
        btn_createCliente.setBounds(60, 410, 160, 40);

        btn_updateCliente.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateCliente.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateCliente.setText("Modificar");
        btn_updateCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateClienteMouseClicked(evt);
            }
        });
        Panel_Carro.add(btn_updateCliente);
        btn_updateCliente.setBounds(60, 480, 160, 40);

        table_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Cliente", "Nombre Cliente", "Correo Cliente"
            }
        ));
        jScrollPane4.setViewportView(table_Clientes);

        Panel_Carro.add(jScrollPane4);
        jScrollPane4.setBounds(330, 90, 670, 450);

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Carro.add(jComboBox7);
        jComboBox7.setBounds(50, 250, 200, 40);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(137, 250, 230));
        jLabel29.setText("Maestro Asignado");
        Panel_Carro.add(jLabel29);
        jLabel29.setBounds(50, 310, 160, 30);

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Carro.add(jComboBox8);
        jComboBox8.setBounds(50, 350, 200, 40);

        Menu_Admin.add(Panel_Carro);
        Panel_Carro.setBounds(1230, 0, 1080, 610);

        Panel_menu_abajo.setBackground(new java.awt.Color(67, 122, 227,200));
        Panel_menu_abajo.setLayout(null);

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/cerrar-sesion (1).png"))); // NOI18N
        jLabel72.setText("Salir");
        jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel72MouseClicked(evt);
            }
        });
        Panel_menu_abajo.add(jLabel72);
        jLabel72.setBounds(960, 0, 70, 32);

        Menu_Admin.add(Panel_menu_abajo);
        Panel_menu_abajo.setBounds(80, 610, 1080, 40);

        Panel_Menu_Izquierda.setBackground(new java.awt.Color(255, 255, 255,240));
        Panel_Menu_Izquierda.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        Panel_Menu_Izquierda.add(jPanel2);
        jPanel2.setBounds(78, 0, 2, 650);

        Icono_Clase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/Icono Clases Estatico.png"))); // NOI18N
        Icono_Clase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_ClaseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icono_ClaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icono_ClaseMouseExited(evt);
            }
        });
        Panel_Menu_Izquierda.add(Icono_Clase);
        Icono_Clase.setBounds(0, 10, 60, 70);

        Icono_Carro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/Icono Carro Estatico.png"))); // NOI18N
        Icono_Carro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_CarroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icono_CarroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icono_CarroMouseExited(evt);
            }
        });
        Panel_Menu_Izquierda.add(Icono_Carro);
        Icono_Carro.setBounds(0, 100, 74, 70);

        Icono_Maestra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/Icono Maestro Estatico.png"))); // NOI18N
        Icono_Maestra.setText("Maestro ico");
        Icono_Maestra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_MaestraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icono_MaestraMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icono_MaestraMouseExited(evt);
            }
        });
        Panel_Menu_Izquierda.add(Icono_Maestra);
        Icono_Maestra.setBounds(0, 190, 60, 72);

        Icono_Alumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/Icono Alumno Estatico.png"))); // NOI18N
        Icono_Alumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_AlumnoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icono_AlumnoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icono_AlumnoMouseExited(evt);
            }
        });
        Panel_Menu_Izquierda.add(Icono_Alumno);
        Icono_Alumno.setBounds(0, 290, 70, 70);

        Icono_Notas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/Icono Notas Estatico.png"))); // NOI18N
        Icono_Notas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_NotasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icono_NotasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icono_NotasMouseExited(evt);
            }
        });
        Panel_Menu_Izquierda.add(Icono_Notas);
        Icono_Notas.setBounds(0, 380, 70, 70);

        Icono_Financiero.setForeground(new java.awt.Color(0, 0, 0));
        Icono_Financiero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/Icono Financiero Estatico.png"))); // NOI18N
        Icono_Financiero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_FinancieroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icono_FinancieroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icono_FinancieroMouseExited(evt);
            }
        });
        Panel_Menu_Izquierda.add(Icono_Financiero);
        Icono_Financiero.setBounds(10, 470, 60, 81);

        Icono_Informe.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        Icono_Informe.setForeground(new java.awt.Color(0, 0, 0));
        Icono_Informe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/Icono Informe Estatico.png"))); // NOI18N
        Icono_Informe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_InformeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icono_InformeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icono_InformeMouseExited(evt);
            }
        });
        Panel_Menu_Izquierda.add(Icono_Informe);
        Icono_Informe.setBounds(10, 560, 60, 80);

        Menu_Admin.add(Panel_Menu_Izquierda);
        Panel_Menu_Izquierda.setBounds(0, 0, 80, 650);

        Fondo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/fondo.gif"))); // NOI18N
        Menu_Admin.add(Fondo5);
        Fondo5.setBounds(70, 0, 1160, 650);
        Menu_Admin.add(jPanel1);
        jPanel1.setBounds(80, 0, 10, 10);

        getContentPane().add(Menu_Admin);
        Menu_Admin.setBounds(0, 0, 2300, 650);

        Menu_Maestro.setLayout(null);

        Panel_Bitacora2.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Bitacora2.setLayout(null);

        jLabel117.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(137, 250, 230));
        jLabel117.setText("Bitacora");
        Panel_Bitacora2.add(jLabel117);
        jLabel117.setBounds(480, 0, 150, 50);

        ta_Bitacora2.setColumns(20);
        ta_Bitacora2.setRows(5);
        jScrollPane25.setViewportView(ta_Bitacora2);

        Panel_Bitacora2.add(jScrollPane25);
        jScrollPane25.setBounds(50, 70, 1000, 510);

        Menu_Maestro.add(Panel_Bitacora2);
        Panel_Bitacora2.setBounds(1230, 0, 1080, 610);

        Panel_Menu_Izquierda2.setBackground(new java.awt.Color(67, 122, 227,170));

        Icono_Tienda2.setText("Usuario Ico");
        Icono_Tienda2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Tienda2MouseClicked(evt);
            }
        });

        Icono_Producto2.setText("Clases Ico");
        Icono_Producto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Producto2MouseClicked(evt);
            }
        });

        Icono_Vendedor2.setText("Examen ico");
        Icono_Vendedor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Vendedor2MouseClicked(evt);
            }
        });

        Icono_Cliente2.setText("Calificacion ico");
        Icono_Cliente2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Cliente2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_Menu_Izquierda2Layout = new javax.swing.GroupLayout(Panel_Menu_Izquierda2);
        Panel_Menu_Izquierda2.setLayout(Panel_Menu_Izquierda2Layout);
        Panel_Menu_Izquierda2Layout.setHorizontalGroup(
            Panel_Menu_Izquierda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Menu_Izquierda2Layout.createSequentialGroup()
                .addGroup(Panel_Menu_Izquierda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_Menu_Izquierda2Layout.createSequentialGroup()
                        .addComponent(Icono_Cliente2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Panel_Menu_Izquierda2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Panel_Menu_Izquierda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_Menu_Izquierda2Layout.createSequentialGroup()
                                .addGroup(Panel_Menu_Izquierda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Icono_Tienda2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Icono_Producto2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Icono_Vendedor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        Panel_Menu_Izquierda2Layout.setVerticalGroup(
            Panel_Menu_Izquierda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Menu_Izquierda2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Icono_Tienda2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Producto2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Vendedor2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Cliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(350, Short.MAX_VALUE))
        );

        Menu_Maestro.add(Panel_Menu_Izquierda2);
        Panel_Menu_Izquierda2.setBounds(0, 0, 80, 650);

        Panel_Informes2.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Informes2.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Informes2.setLayout(null);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(137, 250, 230));
        jLabel41.setText("Informes");
        Panel_Informes2.add(jLabel41);
        jLabel41.setBounds(470, 0, 170, 60);

        ComboReportes2.setForeground(new java.awt.Color(102, 102, 102));
        ComboReportes2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vista 1", "Vista 2", "Vista 3", "Vista 4", "Vista 5", "Vista 6", "Vista 7", "Vista 8" }));
        ComboReportes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboReportes2ActionPerformed(evt);
            }
        });
        Panel_Informes2.add(ComboReportes2);
        ComboReportes2.setBounds(470, 60, 170, 40);

        tableReportes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane26.setViewportView(tableReportes2);

        Panel_Informes2.add(jScrollPane26);
        jScrollPane26.setBounds(30, 110, 960, 430);

        Menu_Maestro.add(Panel_Informes2);
        Panel_Informes2.setBounds(1220, 0, 1080, 610);

        Panel_Cliente2.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Cliente2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente2.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Cliente2.setLayout(null);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(137, 250, 230));
        jLabel46.setText("Clientes");
        Panel_Cliente2.add(jLabel46);
        jLabel46.setBounds(420, 10, 270, 70);

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(137, 250, 230));
        jLabel49.setText("Nombre");
        Panel_Cliente2.add(jLabel49);
        jLabel49.setBounds(50, 130, 110, 30);

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(137, 250, 230));
        jLabel118.setText("Correo");
        Panel_Cliente2.add(jLabel118);
        jLabel118.setBounds(50, 210, 60, 30);

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(137, 250, 230));
        jLabel119.setText("ID");
        Panel_Cliente2.add(jLabel119);
        jLabel119.setBounds(50, 50, 60, 30);

        tf_idCliente2.setBackground(new java.awt.Color(102, 102, 102));
        tf_idCliente2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente2.add(tf_idCliente2);
        tf_idCliente2.setBounds(50, 80, 210, 30);

        tf_nombreCliente2.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreCliente2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente2.add(tf_nombreCliente2);
        tf_nombreCliente2.setBounds(50, 160, 210, 30);

        tf_correoCliente2.setBackground(new java.awt.Color(102, 102, 102));
        tf_correoCliente2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente2.add(tf_correoCliente2);
        tf_correoCliente2.setBounds(50, 240, 210, 30);

        btn_deleteCliente2.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteCliente2.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteCliente2.setText("Eliminar");
        btn_deleteCliente2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteCliente2MouseClicked(evt);
            }
        });
        Panel_Cliente2.add(btn_deleteCliente2);
        btn_deleteCliente2.setBounds(60, 450, 160, 40);

        btn_createCliente2.setBackground(new java.awt.Color(204, 204, 204));
        btn_createCliente2.setForeground(new java.awt.Color(0, 0, 0));
        btn_createCliente2.setText("Añadir");
        btn_createCliente2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createCliente2MouseClicked(evt);
            }
        });
        Panel_Cliente2.add(btn_createCliente2);
        btn_createCliente2.setBounds(60, 310, 160, 40);

        btn_updateCliente2.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateCliente2.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateCliente2.setText("Modificar");
        btn_updateCliente2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateCliente2MouseClicked(evt);
            }
        });
        Panel_Cliente2.add(btn_updateCliente2);
        btn_updateCliente2.setBounds(60, 380, 160, 40);

        table_Clientes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Cliente", "Nombre Cliente", "Correo Cliente"
            }
        ));
        jScrollPane27.setViewportView(table_Clientes2);

        Panel_Cliente2.add(jScrollPane27);
        jScrollPane27.setBounds(330, 90, 670, 450);

        Menu_Maestro.add(Panel_Cliente2);
        Panel_Cliente2.setBounds(1230, 0, 1080, 610);

        Panel_Productos2.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Productos2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos2.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Productos2.setLayout(null);

        jLabel120.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(137, 250, 230));
        jLabel120.setText("Productos");
        Panel_Productos2.add(jLabel120);
        jLabel120.setBounds(500, 10, 270, 70);

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(137, 250, 230));
        jLabel121.setText("UPC");
        Panel_Productos2.add(jLabel121);
        jLabel121.setBounds(70, 50, 37, 20);

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(137, 250, 230));
        jLabel122.setText("Nombre");
        Panel_Productos2.add(jLabel122);
        jLabel122.setBounds(70, 100, 80, 20);

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(137, 250, 230));
        jLabel123.setText("Tamaño");
        Panel_Productos2.add(jLabel123);
        jLabel123.setBounds(70, 150, 80, 20);

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(137, 250, 230));
        jLabel124.setText("Embalaje");
        Panel_Productos2.add(jLabel124);
        jLabel124.setBounds(70, 200, 80, 30);

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(137, 250, 230));
        jLabel125.setText("Marca");
        Panel_Productos2.add(jLabel125);
        jLabel125.setBounds(70, 260, 80, 20);

        tf_embalajeProducto2.setBackground(new java.awt.Color(102, 102, 102));
        tf_embalajeProducto2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos2.add(tf_embalajeProducto2);
        tf_embalajeProducto2.setBounds(70, 230, 220, 30);

        tf_UPC2.setBackground(new java.awt.Color(102, 102, 102));
        tf_UPC2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos2.add(tf_UPC2);
        tf_UPC2.setBounds(70, 70, 220, 30);

        tf_nombreProducto2.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreProducto2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos2.add(tf_nombreProducto2);
        tf_nombreProducto2.setBounds(70, 120, 220, 30);

        tf_tamanoProducto2.setBackground(new java.awt.Color(102, 102, 102));
        tf_tamanoProducto2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos2.add(tf_tamanoProducto2);
        tf_tamanoProducto2.setBounds(70, 170, 220, 30);

        btn_deleteProducto2.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteProducto2.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteProducto2.setText("Eliminar");
        btn_deleteProducto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteProducto2MouseClicked(evt);
            }
        });
        Panel_Productos2.add(btn_deleteProducto2);
        btn_deleteProducto2.setBounds(110, 510, 170, 40);

        table_Productos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "UPC", "Nombre Producto", "Tamaño", "Embalaje", "Marca", "Tipo"
            }
        ));
        jScrollPane28.setViewportView(table_Productos2);

        Panel_Productos2.add(jScrollPane28);
        jScrollPane28.setBounds(350, 100, 670, 450);

        btn_createProducto2.setBackground(new java.awt.Color(204, 204, 204));
        btn_createProducto2.setForeground(new java.awt.Color(0, 0, 0));
        btn_createProducto2.setText("Agregar");
        btn_createProducto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createProducto2MouseClicked(evt);
            }
        });
        Panel_Productos2.add(btn_createProducto2);
        btn_createProducto2.setBounds(110, 390, 170, 40);

        btn_updateProducto2.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateProducto2.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateProducto2.setText("Editar");
        btn_updateProducto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateProducto2MouseClicked(evt);
            }
        });
        Panel_Productos2.add(btn_updateProducto2);
        btn_updateProducto2.setBounds(110, 450, 170, 40);

        tf_marcaProducto2.setBackground(new java.awt.Color(102, 102, 102));
        tf_marcaProducto2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos2.add(tf_marcaProducto2);
        tf_marcaProducto2.setBounds(70, 280, 220, 30);

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(137, 250, 230));
        jLabel126.setText("Tipo Producto");
        Panel_Productos2.add(jLabel126);
        jLabel126.setBounds(70, 310, 120, 30);

        tf_tipoProducto2.setBackground(new java.awt.Color(102, 102, 102));
        tf_tipoProducto2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos2.add(tf_tipoProducto2);
        tf_tipoProducto2.setBounds(70, 340, 220, 30);

        Menu_Maestro.add(Panel_Productos2);
        Panel_Productos2.setBounds(1230, 0, 1080, 610);

        Panel_Vendedorsitos2.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Vendedorsitos2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos2.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Vendedorsitos2.setLayout(null);

        jLabel127.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(137, 250, 230));
        jLabel127.setText("Vendedores");
        Panel_Vendedorsitos2.add(jLabel127);
        jLabel127.setBounds(400, 0, 270, 70);

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(137, 250, 230));
        jLabel128.setText("ID");
        Panel_Vendedorsitos2.add(jLabel128);
        jLabel128.setBounds(40, 120, 130, 25);

        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(137, 250, 230));
        jLabel129.setText("Nombre");
        Panel_Vendedorsitos2.add(jLabel129);
        jLabel129.setBounds(40, 200, 130, 25);

        tf_nombreVendedor2.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreVendedor2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos2.add(tf_nombreVendedor2);
        tf_nombreVendedor2.setBounds(40, 230, 260, 30);

        tf_idVendedor2.setBackground(new java.awt.Color(102, 102, 102));
        tf_idVendedor2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos2.add(tf_idVendedor2);
        tf_idVendedor2.setBounds(40, 150, 260, 30);

        btn_updateVendedor2.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateVendedor2.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateVendedor2.setText("Modificar");
        btn_updateVendedor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateVendedor2MouseClicked(evt);
            }
        });
        Panel_Vendedorsitos2.add(btn_updateVendedor2);
        btn_updateVendedor2.setBounds(110, 380, 120, 40);

        btn_deleteVendedor2.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteVendedor2.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteVendedor2.setText("Eliminar");
        btn_deleteVendedor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteVendedor2MouseClicked(evt);
            }
        });
        Panel_Vendedorsitos2.add(btn_deleteVendedor2);
        btn_deleteVendedor2.setBounds(110, 450, 120, 40);

        btn_createVendedor2.setBackground(new java.awt.Color(204, 204, 204));
        btn_createVendedor2.setForeground(new java.awt.Color(0, 0, 0));
        btn_createVendedor2.setText("Añadir");
        btn_createVendedor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createVendedor2MouseClicked(evt);
            }
        });
        Panel_Vendedorsitos2.add(btn_createVendedor2);
        btn_createVendedor2.setBounds(110, 310, 120, 40);

        table_Vendedores2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Vendedor", "Nombre Vendedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane29.setViewportView(table_Vendedores2);

        Panel_Vendedorsitos2.add(jScrollPane29);
        jScrollPane29.setBounds(340, 100, 680, 440);

        Panel_vendedores2.addTab("Vendedores", Panel_Vendedorsitos2);

        jPanel7.setBackground(new java.awt.Color(0,0,0,200));
        jPanel7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.setLayout(null);

        jLabel130.setBackground(new java.awt.Color(137, 250, 230));
        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(137, 250, 230));
        jLabel130.setText("Vendedor");
        jPanel7.add(jLabel130);
        jLabel130.setBounds(40, 10, 180, 70);

        cbox_ListaVendedores2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbox_ListaVendedores2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbox_ListaVendedores2ItemStateChanged(evt);
            }
        });
        cbox_ListaVendedores2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbox_ListaVendedores2MouseClicked(evt);
            }
        });
        jPanel7.add(cbox_ListaVendedores2);
        cbox_ListaVendedores2.setBounds(220, 30, 310, 40);

        table_ProductosparaVendedor2.setBackground(new java.awt.Color(153, 153, 153));
        table_ProductosparaVendedor2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane30.setViewportView(table_ProductosparaVendedor2);

        jPanel7.add(jScrollPane30);
        jScrollPane30.setBounds(30, 120, 430, 440);

        jTable14.setBackground(new java.awt.Color(153, 153, 153));
        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane31.setViewportView(jTable14);

        jPanel7.add(jScrollPane31);
        jScrollPane31.setBounds(610, 120, 430, 440);

        jButton16.setBackground(new java.awt.Color(204, 204, 204));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(0, 0, 0));
        jButton16.setText("<-");
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
        });
        jPanel7.add(jButton16);
        jButton16.setBounds(500, 340, 80, 50);

        jLabel131.setBackground(new java.awt.Color(137, 250, 230));
        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(137, 250, 230));
        jLabel131.setText("Productos");
        jPanel7.add(jLabel131);
        jLabel131.setBounds(190, 90, 100, 20);

        jLabel132.setBackground(new java.awt.Color(137, 250, 230));
        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(137, 250, 230));
        jLabel132.setText("Productos Que Vende");
        jPanel7.add(jLabel132);
        jLabel132.setBounds(730, 90, 210, 20);

        jButton17.setBackground(new java.awt.Color(204, 204, 204));
        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(0, 0, 0));
        jButton17.setText("->");
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
        });
        jPanel7.add(jButton17);
        jButton17.setBounds(500, 270, 80, 50);

        Panel_vendedores2.addTab("Inventario", jPanel7);

        Menu_Maestro.add(Panel_vendedores2);
        Panel_vendedores2.setBounds(1230, 0, 1080, 610);

        Panel_menu_abajo2.setBackground(new java.awt.Color(67, 122, 227,200));
        Panel_menu_abajo2.setLayout(null);

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel133.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/cerrar-sesion (1).png"))); // NOI18N
        jLabel133.setText("Salir");
        jLabel133.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel133MouseClicked(evt);
            }
        });
        Panel_menu_abajo2.add(jLabel133);
        jLabel133.setBounds(960, 0, 70, 32);

        Menu_Maestro.add(Panel_menu_abajo2);
        Panel_menu_abajo2.setBounds(80, 610, 1080, 40);

        Panel_Factura2.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Factura2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Factura2.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Factura2.setLayout(null);

        jLabel134.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(137, 250, 230));
        jLabel134.setText("Facturas");
        Panel_Factura2.add(jLabel134);
        jLabel134.setBounds(470, 0, 270, 70);

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(137, 250, 230));
        jLabel135.setText("Fecha");
        Panel_Factura2.add(jLabel135);
        jLabel135.setBounds(30, 90, 100, 30);

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(137, 250, 230));
        jLabel136.setText("ID Tienda");
        Panel_Factura2.add(jLabel136);
        jLabel136.setBounds(30, 440, 100, 30);

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(137, 250, 230));
        jLabel137.setText("Subtotal");
        Panel_Factura2.add(jLabel137);
        jLabel137.setBounds(30, 230, 100, 30);

        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel138.setForeground(new java.awt.Color(137, 250, 230));
        jLabel138.setText("Total");
        Panel_Factura2.add(jLabel138);
        jLabel138.setBounds(30, 300, 100, 30);

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel139.setForeground(new java.awt.Color(137, 250, 230));
        jLabel139.setText("ISV");
        Panel_Factura2.add(jLabel139);
        jLabel139.setBounds(30, 160, 100, 30);

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(137, 250, 230));
        jLabel140.setText("ID Cliente");
        Panel_Factura2.add(jLabel140);
        jLabel140.setBounds(30, 370, 100, 30);

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(137, 250, 230));
        jLabel141.setText("Numero");
        Panel_Factura2.add(jLabel141);
        jLabel141.setBounds(30, 20, 100, 30);

        jTextField15.setBackground(new java.awt.Color(102, 102, 102));
        jTextField15.setForeground(new java.awt.Color(255, 255, 255));
        jTextField15.setText("jTextField7");
        Panel_Factura2.add(jTextField15);
        jTextField15.setBounds(30, 50, 190, 30);

        jFormattedTextField3.setText("jFormattedTextField1");
        Panel_Factura2.add(jFormattedTextField3);
        jFormattedTextField3.setBounds(30, 120, 190, 30);

        jTextField16.setBackground(new java.awt.Color(102, 102, 102));
        jTextField16.setForeground(new java.awt.Color(255, 255, 255));
        jTextField16.setText("jTextField8");
        Panel_Factura2.add(jTextField16);
        jTextField16.setBounds(30, 190, 190, 30);

        jTextField17.setBackground(new java.awt.Color(102, 102, 102));
        jTextField17.setForeground(new java.awt.Color(255, 255, 255));
        jTextField17.setText("jTextField9");
        Panel_Factura2.add(jTextField17);
        jTextField17.setBounds(30, 260, 190, 30);

        jTextField18.setBackground(new java.awt.Color(102, 102, 102));
        jTextField18.setForeground(new java.awt.Color(102, 102, 102));
        jTextField18.setText("jTextField10");
        Panel_Factura2.add(jTextField18);
        jTextField18.setBounds(30, 330, 190, 30);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Factura2.add(jComboBox4);
        jComboBox4.setBounds(30, 400, 190, 30);

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Factura2.add(jComboBox6);
        jComboBox6.setBounds(30, 470, 190, 30);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane32.setViewportView(jTable5);

        Panel_Factura2.add(jScrollPane32);
        jScrollPane32.setBounds(280, 90, 710, 460);

        jToggleButton10.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton10.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton10.setText("Agregar");
        Panel_Factura2.add(jToggleButton10);
        jToggleButton10.setBounds(20, 520, 76, 27);

        jToggleButton11.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton11.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton11.setText("Eliminar");
        Panel_Factura2.add(jToggleButton11);
        jToggleButton11.setBounds(20, 560, 77, 27);

        jToggleButton12.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton12.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton12.setText("Modificar");
        Panel_Factura2.add(jToggleButton12);
        jToggleButton12.setBounds(150, 540, 85, 27);

        Menu_Maestro.add(Panel_Factura2);
        Panel_Factura2.setBounds(1230, 0, 1080, 610);

        Panel_Tienda2.setBackground(new java.awt.Color(153, 153, 153));
        Panel_Tienda2.setForeground(new java.awt.Color(0, 0, 0));

        Panel_Tiendita2.setBackground(new java.awt.Color(0, 0, 0,200));
        Panel_Tiendita2.setForeground(new java.awt.Color(0, 0, 0));
        Panel_Tiendita2.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Tiendita2.setLayout(null);

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(137, 250, 230));
        jLabel142.setText("Horario");
        Panel_Tiendita2.add(jLabel142);
        jLabel142.setBounds(110, 200, 100, 30);

        tf_idTienda2.setBackground(new java.awt.Color(102, 102, 102));
        tf_idTienda2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita2.add(tf_idTienda2);
        tf_idTienda2.setBounds(40, 70, 200, 30);

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(137, 250, 230));
        jLabel143.setText("ID");
        Panel_Tiendita2.add(jLabel143);
        jLabel143.setBounds(130, 40, 100, 30);

        tf_nombreTienda2.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreTienda2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita2.add(tf_nombreTienda2);
        tf_nombreTienda2.setBounds(40, 150, 200, 30);

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(137, 250, 230));
        jLabel144.setText("Nombre");
        Panel_Tiendita2.add(jLabel144);
        jLabel144.setBounds(110, 120, 100, 30);

        tf_horarioTienda2.setBackground(new java.awt.Color(102, 102, 102));
        tf_horarioTienda2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita2.add(tf_horarioTienda2);
        tf_horarioTienda2.setBounds(40, 230, 200, 30);

        table_Tiendas2.setBackground(new java.awt.Color(153, 153, 153));
        table_Tiendas2.setForeground(new java.awt.Color(0, 0, 0));
        table_Tiendas2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Tienda", "Nombre Tienda", "Horario", "Ubicacion"
            }
        ));
        table_Tiendas2.setGridColor(new java.awt.Color(153, 153, 153));
        table_Tiendas2.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table_Tiendas2.setShowGrid(false);
        jScrollPane33.setViewportView(table_Tiendas2);

        Panel_Tiendita2.add(jScrollPane33);
        jScrollPane33.setBounds(260, 90, 770, 470);

        btn_modificarTienda2.setBackground(new java.awt.Color(204, 204, 204));
        btn_modificarTienda2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_modificarTienda2.setForeground(new java.awt.Color(0, 0, 0));
        btn_modificarTienda2.setText("Modificar");
        btn_modificarTienda2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modificarTienda2MouseClicked(evt);
            }
        });
        Panel_Tiendita2.add(btn_modificarTienda2);
        btn_modificarTienda2.setBounds(60, 450, 150, 40);

        btn_eliminarTienda2.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminarTienda2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_eliminarTienda2.setForeground(new java.awt.Color(0, 0, 0));
        btn_eliminarTienda2.setText("Eliminar");
        btn_eliminarTienda2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminarTienda2MouseClicked(evt);
            }
        });
        Panel_Tiendita2.add(btn_eliminarTienda2);
        btn_eliminarTienda2.setBounds(60, 520, 150, 40);

        btn_crearTienda2.setBackground(new java.awt.Color(204, 204, 204));
        btn_crearTienda2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_crearTienda2.setForeground(new java.awt.Color(0, 0, 0));
        btn_crearTienda2.setText("Añadir");
        btn_crearTienda2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_crearTienda2MouseClicked(evt);
            }
        });
        Panel_Tiendita2.add(btn_crearTienda2);
        btn_crearTienda2.setBounds(60, 380, 150, 40);

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel145.setForeground(new java.awt.Color(137, 250, 230));
        jLabel145.setText("Tiendas");
        Panel_Tiendita2.add(jLabel145);
        jLabel145.setBounds(550, 10, 180, 64);

        tf_ubicacionTienda2.setBackground(new java.awt.Color(102, 102, 102));
        tf_ubicacionTienda2.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita2.add(tf_ubicacionTienda2);
        tf_ubicacionTienda2.setBounds(40, 310, 200, 30);

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel146.setForeground(new java.awt.Color(137, 250, 230));
        jLabel146.setText("Ubicacion");
        Panel_Tiendita2.add(jLabel146);
        jLabel146.setBounds(100, 280, 100, 30);

        Panel_Tienda2.addTab("Tiendas", Panel_Tiendita2);

        jPanel8.setBackground(new java.awt.Color(0,0,0,200));
        jPanel8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.setLayout(null);

        jLabel147.setBackground(new java.awt.Color(137, 250, 230));
        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(137, 250, 230));
        jLabel147.setText("Tienda");
        jPanel8.add(jLabel147);
        jLabel147.setBounds(40, 10, 130, 70);

        cbox_NombreTiendas2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbox_NombreTiendas2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbox_NombreTiendas2ItemStateChanged(evt);
            }
        });
        jPanel8.add(cbox_NombreTiendas2);
        cbox_NombreTiendas2.setBounds(170, 30, 310, 40);

        table_TodosProductos2.setBackground(new java.awt.Color(153, 153, 153));
        table_TodosProductos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "UPC", "Nombre Producto", "Tamano Producto", "Embalaje", "Marca", "Tipo"
            }
        ));
        jScrollPane34.setViewportView(table_TodosProductos2);

        jPanel8.add(jScrollPane34);
        jScrollPane34.setBounds(50, 100, 400, 300);

        table_ProductosXTienda2.setBackground(new java.awt.Color(153, 153, 153));
        table_ProductosXTienda2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "idTienda", "UPC", "Precio", "Cantidad", "Reorden"
            }
        ));
        jScrollPane35.setViewportView(table_ProductosXTienda2);

        jPanel8.add(jScrollPane35);
        jScrollPane35.setBounds(560, 100, 400, 300);

        btn_agregarProductoTienda2.setBackground(new java.awt.Color(204, 204, 204));
        btn_agregarProductoTienda2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_agregarProductoTienda2.setForeground(new java.awt.Color(0, 0, 0));
        btn_agregarProductoTienda2.setText("->");
        btn_agregarProductoTienda2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_agregarProductoTienda2MouseClicked(evt);
            }
        });
        jPanel8.add(btn_agregarProductoTienda2);
        btn_agregarProductoTienda2.setBounds(480, 230, 50, 31);

        tf_precio2.setBackground(new java.awt.Color(102, 102, 102));
        tf_precio2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.add(tf_precio2);
        tf_precio2.setBounds(40, 440, 220, 40);

        tf_cantidad2.setBackground(new java.awt.Color(102, 102, 102));
        tf_cantidad2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.add(tf_cantidad2);
        tf_cantidad2.setBounds(40, 520, 220, 40);

        tf_reorden2.setBackground(new java.awt.Color(102, 102, 102));
        tf_reorden2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.add(tf_reorden2);
        tf_reorden2.setBounds(280, 480, 220, 40);

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(137, 250, 230));
        jLabel148.setText("Cantidad");
        jPanel8.add(jLabel148);
        jLabel148.setBounds(110, 490, 80, 25);

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(137, 250, 230));
        jLabel149.setText("Reorder");
        jPanel8.add(jLabel149);
        jLabel149.setBounds(350, 450, 90, 25);

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(137, 250, 230));
        jLabel150.setText("Precio");
        jPanel8.add(jLabel150);
        jLabel150.setBounds(120, 410, 60, 20);

        btn_eliminarInventario2.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminarInventario2.setForeground(new java.awt.Color(0, 0, 0));
        btn_eliminarInventario2.setText("Eliminar");
        btn_eliminarInventario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminarInventario2MouseClicked(evt);
            }
        });
        jPanel8.add(btn_eliminarInventario2);
        btn_eliminarInventario2.setBounds(590, 430, 140, 40);

        btn_modifyInventario2.setBackground(new java.awt.Color(204, 204, 204));
        btn_modifyInventario2.setForeground(new java.awt.Color(0, 0, 0));
        btn_modifyInventario2.setText("Modificar");
        btn_modifyInventario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modifyInventario2MouseClicked(evt);
            }
        });
        jPanel8.add(btn_modifyInventario2);
        btn_modifyInventario2.setBounds(590, 490, 140, 40);

        Panel_Tienda2.addTab("Inventario", jPanel8);

        Menu_Maestro.add(Panel_Tienda2);
        Panel_Tienda2.setBounds(1230, 0, 1080, 610);

        Fondo7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/frame menu.png"))); // NOI18N
        Menu_Maestro.add(Fondo7);
        Fondo7.setBounds(0, 0, 1150, 650);

        getContentPane().add(Menu_Maestro);
        Menu_Maestro.setBounds(0, 0, 2300, 650);

        Menu_Customer.setLayout(null);

        Panel_Bitacora1.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Bitacora1.setLayout(null);

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(137, 250, 230));
        jLabel74.setText("Bitacora");
        Panel_Bitacora1.add(jLabel74);
        jLabel74.setBounds(480, 0, 150, 50);

        ta_Bitacora1.setColumns(20);
        ta_Bitacora1.setRows(5);
        jScrollPane14.setViewportView(ta_Bitacora1);

        Panel_Bitacora1.add(jScrollPane14);
        jScrollPane14.setBounds(50, 70, 1000, 510);

        Menu_Customer.add(Panel_Bitacora1);
        Panel_Bitacora1.setBounds(1230, 0, 1080, 610);

        Panel_Menu_Izquierda1.setBackground(new java.awt.Color(67, 122, 227,170));

        Icono_Tienda1.setText("Usuario Ico");
        Icono_Tienda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Tienda1MouseClicked(evt);
            }
        });

        Icono_Producto1.setText("Clases Ico");
        Icono_Producto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Producto1MouseClicked(evt);
            }
        });

        Icono_Vendedor1.setText("Examen ico");
        Icono_Vendedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Vendedor1MouseClicked(evt);
            }
        });

        Icono_Factura1.setText("Financiero ico");
        Icono_Factura1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Factura1MouseClicked(evt);
            }
        });

        Icono_Cliente1.setText("Calificacion ico");
        Icono_Cliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_Cliente1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_Menu_Izquierda1Layout = new javax.swing.GroupLayout(Panel_Menu_Izquierda1);
        Panel_Menu_Izquierda1.setLayout(Panel_Menu_Izquierda1Layout);
        Panel_Menu_Izquierda1Layout.setHorizontalGroup(
            Panel_Menu_Izquierda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Menu_Izquierda1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_Menu_Izquierda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_Menu_Izquierda1Layout.createSequentialGroup()
                        .addComponent(Icono_Vendedor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(Panel_Menu_Izquierda1Layout.createSequentialGroup()
                        .addGroup(Panel_Menu_Izquierda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Icono_Cliente1)
                            .addComponent(Icono_Factura1)
                            .addComponent(Icono_Producto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Icono_Tienda1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        Panel_Menu_Izquierda1Layout.setVerticalGroup(
            Panel_Menu_Izquierda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Menu_Izquierda1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Icono_Tienda1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Icono_Producto1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Vendedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Factura1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Cliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );

        Menu_Customer.add(Panel_Menu_Izquierda1);
        Panel_Menu_Izquierda1.setBounds(0, 0, 80, 650);

        Panel_Informes1.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Informes1.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Informes1.setLayout(null);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(137, 250, 230));
        jLabel40.setText("Informes");
        Panel_Informes1.add(jLabel40);
        jLabel40.setBounds(470, 0, 170, 60);

        ComboReportes1.setForeground(new java.awt.Color(102, 102, 102));
        ComboReportes1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vista 1", "Vista 2", "Vista 3", "Vista 4", "Vista 5", "Vista 6", "Vista 7", "Vista 8" }));
        ComboReportes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboReportes1ActionPerformed(evt);
            }
        });
        Panel_Informes1.add(ComboReportes1);
        ComboReportes1.setBounds(470, 60, 170, 40);

        tableReportes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane15.setViewportView(tableReportes1);

        Panel_Informes1.add(jScrollPane15);
        jScrollPane15.setBounds(30, 110, 960, 430);

        Menu_Customer.add(Panel_Informes1);
        Panel_Informes1.setBounds(1220, 0, 1080, 610);

        Panel_Cliente1.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Cliente1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente1.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Cliente1.setLayout(null);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(137, 250, 230));
        jLabel45.setText("Clientes");
        Panel_Cliente1.add(jLabel45);
        jLabel45.setBounds(420, 10, 270, 70);

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(137, 250, 230));
        jLabel47.setText("Nombre");
        Panel_Cliente1.add(jLabel47);
        jLabel47.setBounds(50, 130, 110, 30);

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(137, 250, 230));
        jLabel77.setText("Correo");
        Panel_Cliente1.add(jLabel77);
        jLabel77.setBounds(50, 210, 60, 30);

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(137, 250, 230));
        jLabel85.setText("ID");
        Panel_Cliente1.add(jLabel85);
        jLabel85.setBounds(50, 50, 60, 30);

        tf_idCliente1.setBackground(new java.awt.Color(102, 102, 102));
        tf_idCliente1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente1.add(tf_idCliente1);
        tf_idCliente1.setBounds(50, 80, 210, 30);

        tf_nombreCliente1.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreCliente1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente1.add(tf_nombreCliente1);
        tf_nombreCliente1.setBounds(50, 160, 210, 30);

        tf_correoCliente1.setBackground(new java.awt.Color(102, 102, 102));
        tf_correoCliente1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente1.add(tf_correoCliente1);
        tf_correoCliente1.setBounds(50, 240, 210, 30);

        btn_deleteCliente1.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteCliente1.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteCliente1.setText("Eliminar");
        btn_deleteCliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteCliente1MouseClicked(evt);
            }
        });
        Panel_Cliente1.add(btn_deleteCliente1);
        btn_deleteCliente1.setBounds(60, 450, 160, 40);

        btn_createCliente1.setBackground(new java.awt.Color(204, 204, 204));
        btn_createCliente1.setForeground(new java.awt.Color(0, 0, 0));
        btn_createCliente1.setText("Añadir");
        btn_createCliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createCliente1MouseClicked(evt);
            }
        });
        Panel_Cliente1.add(btn_createCliente1);
        btn_createCliente1.setBounds(60, 310, 160, 40);

        btn_updateCliente1.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateCliente1.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateCliente1.setText("Modificar");
        btn_updateCliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateCliente1MouseClicked(evt);
            }
        });
        Panel_Cliente1.add(btn_updateCliente1);
        btn_updateCliente1.setBounds(60, 380, 160, 40);

        table_Clientes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Cliente", "Nombre Cliente", "Correo Cliente"
            }
        ));
        jScrollPane16.setViewportView(table_Clientes1);

        Panel_Cliente1.add(jScrollPane16);
        jScrollPane16.setBounds(330, 90, 670, 450);

        Menu_Customer.add(Panel_Cliente1);
        Panel_Cliente1.setBounds(1230, 0, 1080, 610);

        Panel_Productos1.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Productos1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos1.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Productos1.setLayout(null);

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(137, 250, 230));
        jLabel86.setText("Productos");
        Panel_Productos1.add(jLabel86);
        jLabel86.setBounds(500, 10, 270, 70);

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(137, 250, 230));
        jLabel87.setText("UPC");
        Panel_Productos1.add(jLabel87);
        jLabel87.setBounds(70, 50, 37, 20);

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(137, 250, 230));
        jLabel88.setText("Nombre");
        Panel_Productos1.add(jLabel88);
        jLabel88.setBounds(70, 100, 80, 20);

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(137, 250, 230));
        jLabel89.setText("Tamaño");
        Panel_Productos1.add(jLabel89);
        jLabel89.setBounds(70, 150, 80, 20);

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(137, 250, 230));
        jLabel90.setText("Embalaje");
        Panel_Productos1.add(jLabel90);
        jLabel90.setBounds(70, 200, 80, 30);

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(137, 250, 230));
        jLabel91.setText("Marca");
        Panel_Productos1.add(jLabel91);
        jLabel91.setBounds(70, 260, 80, 20);

        tf_embalajeProducto1.setBackground(new java.awt.Color(102, 102, 102));
        tf_embalajeProducto1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos1.add(tf_embalajeProducto1);
        tf_embalajeProducto1.setBounds(70, 230, 220, 30);

        tf_UPC1.setBackground(new java.awt.Color(102, 102, 102));
        tf_UPC1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos1.add(tf_UPC1);
        tf_UPC1.setBounds(70, 70, 220, 30);

        tf_nombreProducto1.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreProducto1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos1.add(tf_nombreProducto1);
        tf_nombreProducto1.setBounds(70, 120, 220, 30);

        tf_tamanoProducto1.setBackground(new java.awt.Color(102, 102, 102));
        tf_tamanoProducto1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos1.add(tf_tamanoProducto1);
        tf_tamanoProducto1.setBounds(70, 170, 220, 30);

        btn_deleteProducto1.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteProducto1.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteProducto1.setText("Eliminar");
        btn_deleteProducto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteProducto1MouseClicked(evt);
            }
        });
        Panel_Productos1.add(btn_deleteProducto1);
        btn_deleteProducto1.setBounds(110, 510, 170, 40);

        table_Productos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "UPC", "Nombre Producto", "Tamaño", "Embalaje", "Marca", "Tipo"
            }
        ));
        jScrollPane17.setViewportView(table_Productos1);

        Panel_Productos1.add(jScrollPane17);
        jScrollPane17.setBounds(350, 100, 670, 450);

        btn_createProducto1.setBackground(new java.awt.Color(204, 204, 204));
        btn_createProducto1.setForeground(new java.awt.Color(0, 0, 0));
        btn_createProducto1.setText("Agregar");
        btn_createProducto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createProducto1MouseClicked(evt);
            }
        });
        Panel_Productos1.add(btn_createProducto1);
        btn_createProducto1.setBounds(110, 390, 170, 40);

        btn_updateProducto1.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateProducto1.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateProducto1.setText("Editar");
        btn_updateProducto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateProducto1MouseClicked(evt);
            }
        });
        Panel_Productos1.add(btn_updateProducto1);
        btn_updateProducto1.setBounds(110, 450, 170, 40);

        tf_marcaProducto1.setBackground(new java.awt.Color(102, 102, 102));
        tf_marcaProducto1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos1.add(tf_marcaProducto1);
        tf_marcaProducto1.setBounds(70, 280, 220, 30);

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(137, 250, 230));
        jLabel92.setText("Tipo Producto");
        Panel_Productos1.add(jLabel92);
        jLabel92.setBounds(70, 310, 120, 30);

        tf_tipoProducto1.setBackground(new java.awt.Color(102, 102, 102));
        tf_tipoProducto1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos1.add(tf_tipoProducto1);
        tf_tipoProducto1.setBounds(70, 340, 220, 30);

        Menu_Customer.add(Panel_Productos1);
        Panel_Productos1.setBounds(1230, 0, 1080, 610);

        Panel_Vendedorsitos1.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Vendedorsitos1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos1.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Vendedorsitos1.setLayout(null);

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(137, 250, 230));
        jLabel93.setText("Vendedores");
        Panel_Vendedorsitos1.add(jLabel93);
        jLabel93.setBounds(400, 0, 270, 70);

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(137, 250, 230));
        jLabel94.setText("ID");
        Panel_Vendedorsitos1.add(jLabel94);
        jLabel94.setBounds(40, 120, 130, 25);

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(137, 250, 230));
        jLabel95.setText("Nombre");
        Panel_Vendedorsitos1.add(jLabel95);
        jLabel95.setBounds(40, 200, 130, 25);

        tf_nombreVendedor1.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreVendedor1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos1.add(tf_nombreVendedor1);
        tf_nombreVendedor1.setBounds(40, 230, 260, 30);

        tf_idVendedor1.setBackground(new java.awt.Color(102, 102, 102));
        tf_idVendedor1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos1.add(tf_idVendedor1);
        tf_idVendedor1.setBounds(40, 150, 260, 30);

        btn_updateVendedor1.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateVendedor1.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateVendedor1.setText("Modificar");
        btn_updateVendedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateVendedor1MouseClicked(evt);
            }
        });
        Panel_Vendedorsitos1.add(btn_updateVendedor1);
        btn_updateVendedor1.setBounds(110, 380, 120, 40);

        btn_deleteVendedor1.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteVendedor1.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteVendedor1.setText("Eliminar");
        btn_deleteVendedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteVendedor1MouseClicked(evt);
            }
        });
        Panel_Vendedorsitos1.add(btn_deleteVendedor1);
        btn_deleteVendedor1.setBounds(110, 450, 120, 40);

        btn_createVendedor1.setBackground(new java.awt.Color(204, 204, 204));
        btn_createVendedor1.setForeground(new java.awt.Color(0, 0, 0));
        btn_createVendedor1.setText("Añadir");
        btn_createVendedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createVendedor1MouseClicked(evt);
            }
        });
        Panel_Vendedorsitos1.add(btn_createVendedor1);
        btn_createVendedor1.setBounds(110, 310, 120, 40);

        table_Vendedores1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Vendedor", "Nombre Vendedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane18.setViewportView(table_Vendedores1);

        Panel_Vendedorsitos1.add(jScrollPane18);
        jScrollPane18.setBounds(340, 100, 680, 440);

        Panel_vendedores1.addTab("Vendedores", Panel_Vendedorsitos1);

        jPanel6.setBackground(new java.awt.Color(0,0,0,200));
        jPanel6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.setLayout(null);

        jLabel96.setBackground(new java.awt.Color(137, 250, 230));
        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(137, 250, 230));
        jLabel96.setText("Vendedor");
        jPanel6.add(jLabel96);
        jLabel96.setBounds(40, 10, 180, 70);

        cbox_ListaVendedores1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbox_ListaVendedores1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbox_ListaVendedores1ItemStateChanged(evt);
            }
        });
        cbox_ListaVendedores1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbox_ListaVendedores1MouseClicked(evt);
            }
        });
        jPanel6.add(cbox_ListaVendedores1);
        cbox_ListaVendedores1.setBounds(220, 30, 310, 40);

        table_ProductosparaVendedor1.setBackground(new java.awt.Color(153, 153, 153));
        table_ProductosparaVendedor1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane19.setViewportView(table_ProductosparaVendedor1);

        jPanel6.add(jScrollPane19);
        jScrollPane19.setBounds(30, 120, 430, 440);

        jTable13.setBackground(new java.awt.Color(153, 153, 153));
        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane20.setViewportView(jTable13);

        jPanel6.add(jScrollPane20);
        jScrollPane20.setBounds(610, 120, 430, 440);

        jButton14.setBackground(new java.awt.Color(204, 204, 204));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(0, 0, 0));
        jButton14.setText("<-");
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });
        jPanel6.add(jButton14);
        jButton14.setBounds(500, 340, 80, 50);

        jLabel97.setBackground(new java.awt.Color(137, 250, 230));
        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(137, 250, 230));
        jLabel97.setText("Productos");
        jPanel6.add(jLabel97);
        jLabel97.setBounds(190, 90, 100, 20);

        jLabel98.setBackground(new java.awt.Color(137, 250, 230));
        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(137, 250, 230));
        jLabel98.setText("Productos Que Vende");
        jPanel6.add(jLabel98);
        jLabel98.setBounds(730, 90, 210, 20);

        jButton15.setBackground(new java.awt.Color(204, 204, 204));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(0, 0, 0));
        jButton15.setText("->");
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });
        jPanel6.add(jButton15);
        jButton15.setBounds(500, 270, 80, 50);

        Panel_vendedores1.addTab("Inventario", jPanel6);

        Menu_Customer.add(Panel_vendedores1);
        Panel_vendedores1.setBounds(1230, 0, 1080, 610);

        Panel_menu_abajo1.setBackground(new java.awt.Color(67, 122, 227,200));
        Panel_menu_abajo1.setLayout(null);

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/cerrar-sesion (1).png"))); // NOI18N
        jLabel99.setText("Salir");
        jLabel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel99MouseClicked(evt);
            }
        });
        Panel_menu_abajo1.add(jLabel99);
        jLabel99.setBounds(960, 0, 70, 32);

        Menu_Customer.add(Panel_menu_abajo1);
        Panel_menu_abajo1.setBounds(80, 610, 1080, 40);

        Panel_Factura1.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Factura1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Factura1.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Factura1.setLayout(null);

        jLabel100.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(137, 250, 230));
        jLabel100.setText("Facturas");
        Panel_Factura1.add(jLabel100);
        jLabel100.setBounds(470, 0, 270, 70);

        jLabel101.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(137, 250, 230));
        jLabel101.setText("Fecha");
        Panel_Factura1.add(jLabel101);
        jLabel101.setBounds(30, 90, 100, 30);

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(137, 250, 230));
        jLabel102.setText("ID Tienda");
        Panel_Factura1.add(jLabel102);
        jLabel102.setBounds(30, 440, 100, 30);

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(137, 250, 230));
        jLabel103.setText("Subtotal");
        Panel_Factura1.add(jLabel103);
        jLabel103.setBounds(30, 230, 100, 30);

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(137, 250, 230));
        jLabel104.setText("Total");
        Panel_Factura1.add(jLabel104);
        jLabel104.setBounds(30, 300, 100, 30);

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(137, 250, 230));
        jLabel105.setText("ISV");
        Panel_Factura1.add(jLabel105);
        jLabel105.setBounds(30, 160, 100, 30);

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(137, 250, 230));
        jLabel106.setText("ID Cliente");
        Panel_Factura1.add(jLabel106);
        jLabel106.setBounds(30, 370, 100, 30);

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(137, 250, 230));
        jLabel107.setText("Numero");
        Panel_Factura1.add(jLabel107);
        jLabel107.setBounds(30, 20, 100, 30);

        jTextField11.setBackground(new java.awt.Color(102, 102, 102));
        jTextField11.setForeground(new java.awt.Color(255, 255, 255));
        jTextField11.setText("jTextField7");
        Panel_Factura1.add(jTextField11);
        jTextField11.setBounds(30, 50, 190, 30);

        jFormattedTextField2.setText("jFormattedTextField1");
        Panel_Factura1.add(jFormattedTextField2);
        jFormattedTextField2.setBounds(30, 120, 190, 30);

        jTextField12.setBackground(new java.awt.Color(102, 102, 102));
        jTextField12.setForeground(new java.awt.Color(255, 255, 255));
        jTextField12.setText("jTextField8");
        Panel_Factura1.add(jTextField12);
        jTextField12.setBounds(30, 190, 190, 30);

        jTextField13.setBackground(new java.awt.Color(102, 102, 102));
        jTextField13.setForeground(new java.awt.Color(255, 255, 255));
        jTextField13.setText("jTextField9");
        Panel_Factura1.add(jTextField13);
        jTextField13.setBounds(30, 260, 190, 30);

        jTextField14.setBackground(new java.awt.Color(102, 102, 102));
        jTextField14.setForeground(new java.awt.Color(102, 102, 102));
        jTextField14.setText("jTextField10");
        Panel_Factura1.add(jTextField14);
        jTextField14.setBounds(30, 330, 190, 30);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Factura1.add(jComboBox3);
        jComboBox3.setBounds(30, 400, 190, 30);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Factura1.add(jComboBox5);
        jComboBox5.setBounds(30, 470, 190, 30);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane21.setViewportView(jTable4);

        Panel_Factura1.add(jScrollPane21);
        jScrollPane21.setBounds(280, 90, 710, 460);

        jToggleButton7.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton7.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton7.setText("Agregar");
        Panel_Factura1.add(jToggleButton7);
        jToggleButton7.setBounds(20, 520, 76, 27);

        jToggleButton8.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton8.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton8.setText("Eliminar");
        Panel_Factura1.add(jToggleButton8);
        jToggleButton8.setBounds(20, 560, 77, 27);

        jToggleButton9.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton9.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton9.setText("Modificar");
        Panel_Factura1.add(jToggleButton9);
        jToggleButton9.setBounds(150, 540, 85, 27);

        Menu_Customer.add(Panel_Factura1);
        Panel_Factura1.setBounds(1230, 0, 1080, 610);

        Panel_Tienda1.setBackground(new java.awt.Color(153, 153, 153));
        Panel_Tienda1.setForeground(new java.awt.Color(0, 0, 0));

        Panel_Tiendita1.setBackground(new java.awt.Color(0, 0, 0,200));
        Panel_Tiendita1.setForeground(new java.awt.Color(0, 0, 0));
        Panel_Tiendita1.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Tiendita1.setLayout(null);

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(137, 250, 230));
        jLabel108.setText("Horario");
        Panel_Tiendita1.add(jLabel108);
        jLabel108.setBounds(110, 200, 100, 30);

        tf_idTienda1.setBackground(new java.awt.Color(102, 102, 102));
        tf_idTienda1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita1.add(tf_idTienda1);
        tf_idTienda1.setBounds(40, 70, 200, 30);

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(137, 250, 230));
        jLabel109.setText("ID");
        Panel_Tiendita1.add(jLabel109);
        jLabel109.setBounds(130, 40, 100, 30);

        tf_nombreTienda1.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreTienda1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita1.add(tf_nombreTienda1);
        tf_nombreTienda1.setBounds(40, 150, 200, 30);

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(137, 250, 230));
        jLabel110.setText("Nombre");
        Panel_Tiendita1.add(jLabel110);
        jLabel110.setBounds(110, 120, 100, 30);

        tf_horarioTienda1.setBackground(new java.awt.Color(102, 102, 102));
        tf_horarioTienda1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita1.add(tf_horarioTienda1);
        tf_horarioTienda1.setBounds(40, 230, 200, 30);

        table_Tiendas1.setBackground(new java.awt.Color(153, 153, 153));
        table_Tiendas1.setForeground(new java.awt.Color(0, 0, 0));
        table_Tiendas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Tienda", "Nombre Tienda", "Horario", "Ubicacion"
            }
        ));
        table_Tiendas1.setGridColor(new java.awt.Color(153, 153, 153));
        table_Tiendas1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table_Tiendas1.setShowGrid(false);
        jScrollPane22.setViewportView(table_Tiendas1);

        Panel_Tiendita1.add(jScrollPane22);
        jScrollPane22.setBounds(260, 90, 770, 470);

        btn_modificarTienda1.setBackground(new java.awt.Color(204, 204, 204));
        btn_modificarTienda1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_modificarTienda1.setForeground(new java.awt.Color(0, 0, 0));
        btn_modificarTienda1.setText("Modificar");
        btn_modificarTienda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modificarTienda1MouseClicked(evt);
            }
        });
        Panel_Tiendita1.add(btn_modificarTienda1);
        btn_modificarTienda1.setBounds(60, 450, 150, 40);

        btn_eliminarTienda1.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminarTienda1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_eliminarTienda1.setForeground(new java.awt.Color(0, 0, 0));
        btn_eliminarTienda1.setText("Eliminar");
        btn_eliminarTienda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminarTienda1MouseClicked(evt);
            }
        });
        Panel_Tiendita1.add(btn_eliminarTienda1);
        btn_eliminarTienda1.setBounds(60, 520, 150, 40);

        btn_crearTienda1.setBackground(new java.awt.Color(204, 204, 204));
        btn_crearTienda1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_crearTienda1.setForeground(new java.awt.Color(0, 0, 0));
        btn_crearTienda1.setText("Añadir");
        btn_crearTienda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_crearTienda1MouseClicked(evt);
            }
        });
        Panel_Tiendita1.add(btn_crearTienda1);
        btn_crearTienda1.setBounds(60, 380, 150, 40);

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(137, 250, 230));
        jLabel111.setText("Tiendas");
        Panel_Tiendita1.add(jLabel111);
        jLabel111.setBounds(550, 10, 180, 64);

        tf_ubicacionTienda1.setBackground(new java.awt.Color(102, 102, 102));
        tf_ubicacionTienda1.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita1.add(tf_ubicacionTienda1);
        tf_ubicacionTienda1.setBounds(40, 310, 200, 30);

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(137, 250, 230));
        jLabel112.setText("Ubicacion");
        Panel_Tiendita1.add(jLabel112);
        jLabel112.setBounds(100, 280, 100, 30);

        Panel_Tienda1.addTab("Tiendas", Panel_Tiendita1);

        jPanel4.setBackground(new java.awt.Color(0,0,0,200));
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(null);

        jLabel113.setBackground(new java.awt.Color(137, 250, 230));
        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(137, 250, 230));
        jLabel113.setText("Tienda");
        jPanel4.add(jLabel113);
        jLabel113.setBounds(40, 10, 130, 70);

        cbox_NombreTiendas1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbox_NombreTiendas1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbox_NombreTiendas1ItemStateChanged(evt);
            }
        });
        jPanel4.add(cbox_NombreTiendas1);
        cbox_NombreTiendas1.setBounds(170, 30, 310, 40);

        table_TodosProductos1.setBackground(new java.awt.Color(153, 153, 153));
        table_TodosProductos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "UPC", "Nombre Producto", "Tamano Producto", "Embalaje", "Marca", "Tipo"
            }
        ));
        jScrollPane23.setViewportView(table_TodosProductos1);

        jPanel4.add(jScrollPane23);
        jScrollPane23.setBounds(50, 100, 400, 300);

        table_ProductosXTienda1.setBackground(new java.awt.Color(153, 153, 153));
        table_ProductosXTienda1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "idTienda", "UPC", "Precio", "Cantidad", "Reorden"
            }
        ));
        jScrollPane24.setViewportView(table_ProductosXTienda1);

        jPanel4.add(jScrollPane24);
        jScrollPane24.setBounds(560, 100, 400, 300);

        btn_agregarProductoTienda1.setBackground(new java.awt.Color(204, 204, 204));
        btn_agregarProductoTienda1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_agregarProductoTienda1.setForeground(new java.awt.Color(0, 0, 0));
        btn_agregarProductoTienda1.setText("->");
        btn_agregarProductoTienda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_agregarProductoTienda1MouseClicked(evt);
            }
        });
        jPanel4.add(btn_agregarProductoTienda1);
        btn_agregarProductoTienda1.setBounds(480, 230, 50, 31);

        tf_precio1.setBackground(new java.awt.Color(102, 102, 102));
        tf_precio1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(tf_precio1);
        tf_precio1.setBounds(40, 440, 220, 40);

        tf_cantidad1.setBackground(new java.awt.Color(102, 102, 102));
        tf_cantidad1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(tf_cantidad1);
        tf_cantidad1.setBounds(40, 520, 220, 40);

        tf_reorden1.setBackground(new java.awt.Color(102, 102, 102));
        tf_reorden1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(tf_reorden1);
        tf_reorden1.setBounds(280, 480, 220, 40);

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(137, 250, 230));
        jLabel114.setText("Cantidad");
        jPanel4.add(jLabel114);
        jLabel114.setBounds(110, 490, 80, 25);

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(137, 250, 230));
        jLabel115.setText("Reorder");
        jPanel4.add(jLabel115);
        jLabel115.setBounds(350, 450, 90, 25);

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(137, 250, 230));
        jLabel116.setText("Precio");
        jPanel4.add(jLabel116);
        jLabel116.setBounds(120, 410, 60, 20);

        btn_eliminarInventario1.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminarInventario1.setForeground(new java.awt.Color(0, 0, 0));
        btn_eliminarInventario1.setText("Eliminar");
        btn_eliminarInventario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminarInventario1MouseClicked(evt);
            }
        });
        jPanel4.add(btn_eliminarInventario1);
        btn_eliminarInventario1.setBounds(590, 430, 140, 40);

        btn_modifyInventario1.setBackground(new java.awt.Color(204, 204, 204));
        btn_modifyInventario1.setForeground(new java.awt.Color(0, 0, 0));
        btn_modifyInventario1.setText("Modificar");
        btn_modifyInventario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modifyInventario1MouseClicked(evt);
            }
        });
        jPanel4.add(btn_modifyInventario1);
        btn_modifyInventario1.setBounds(590, 490, 140, 40);

        Panel_Tienda1.addTab("Inventario", jPanel4);

        Menu_Customer.add(Panel_Tienda1);
        Panel_Tienda1.setBounds(1230, 0, 1080, 610);

        Fondo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/frame menu.png"))); // NOI18N
        Menu_Customer.add(Fondo6);
        Fondo6.setBounds(0, 0, 1150, 650);

        getContentPane().add(Menu_Customer);
        Menu_Customer.setBounds(0, 0, 2300, 650);

        panel_Registro.setBackground(new java.awt.Color(255,255,255,150));
        panel_Registro.setToolTipText("");
        panel_Registro.setLayout(null);

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/engranaje.png"))); // NOI18N
        jLabel78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel78MouseClicked(evt);
            }
        });
        panel_Registro.add(jLabel78);
        jLabel78.setBounds(10, 0, 32, 40);
        panel_Registro.add(Correo);
        Correo.setBounds(60, 340, 320, 40);

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/carro-trafico2_1.gif"))); // NOI18N
        panel_Registro.add(jLabel79);
        jLabel79.setBounds(50, -70, 320, 300);

        Boton_Ingreso3.setText("Ingresar");
        Boton_Ingreso3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Boton_Ingreso3MouseClicked(evt);
            }
        });
        panel_Registro.add(Boton_Ingreso3);
        Boton_Ingreso3.setBounds(150, 560, 120, 50);

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel80.setText("Correo");
        panel_Registro.add(jLabel80);
        jLabel80.setBounds(60, 310, 90, 25);

        Label_Incorrecto1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label_Incorrecto1.setForeground(new java.awt.Color(255, 0, 51));
        Label_Incorrecto1.setText("Ingrese Un Correo Valido");
        panel_Registro.add(Label_Incorrecto1);
        Label_Incorrecto1.setBounds(60, 480, 320, 25);

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/cerrar-sesion (2).png"))); // NOI18N
        jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel83MouseClicked(evt);
            }
        });
        panel_Registro.add(jLabel83);
        jLabel83.setBounds(380, 0, 32, 32);
        panel_Registro.add(contra);
        contra.setBounds(60, 420, 320, 40);

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel84.setText("Contrase;a");
        panel_Registro.add(jLabel84);
        jLabel84.setBounds(60, 390, 90, 25);

        getContentPane().add(panel_Registro);
        panel_Registro.setBounds(380, 0, 420, 650);

        panel_ingreso.setBackground(new java.awt.Color(255,255,255,150));
        panel_ingreso.setLayout(null);
        panel_ingreso.add(Usuario);
        Usuario.setBounds(60, 360, 320, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/carro-trafico1.gif"))); // NOI18N
        panel_ingreso.add(jLabel2);
        jLabel2.setBounds(60, 10, 310, 160);

        Boton_Ingreso.setText("Ingresar");
        Boton_Ingreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Boton_IngresoMouseClicked(evt);
            }
        });
        panel_ingreso.add(Boton_Ingreso);
        Boton_Ingreso.setBounds(150, 580, 120, 50);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Usuario");
        panel_ingreso.add(jLabel7);
        jLabel7.setBounds(60, 330, 90, 25);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Contraseña");
        panel_ingreso.add(jLabel8);
        jLabel8.setBounds(60, 400, 100, 25);

        Label_Incorrecto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label_Incorrecto.setForeground(new java.awt.Color(255, 0, 51));
        Label_Incorrecto.setText("Usuario o Contraseña Incorrectos");
        panel_ingreso.add(Label_Incorrecto);
        Label_Incorrecto.setBounds(60, 540, 320, 25);
        panel_ingreso.add(Contra);
        Contra.setBounds(60, 430, 320, 40);

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/engranaje.png"))); // NOI18N
        jLabel81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel81MouseClicked(evt);
            }
        });
        panel_ingreso.add(jLabel81);
        jLabel81.setBounds(10, 0, 32, 40);

        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/cerrar-sesion (2).png"))); // NOI18N
        jLabel82.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel82MouseClicked(evt);
            }
        });
        panel_ingreso.add(jLabel82);
        jLabel82.setBounds(380, 0, 32, 32);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("ID");
        panel_ingreso.add(jLabel31);
        jLabel31.setBounds(60, 470, 100, 25);
        panel_ingreso.add(Contra1);
        Contra1.setBounds(60, 500, 320, 40);
        panel_ingreso.add(Usuario1);
        Usuario1.setBounds(60, 290, 320, 40);

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel75.setText("Usuario");
        panel_ingreso.add(jLabel75);
        jLabel75.setBounds(60, 260, 90, 25);
        panel_ingreso.add(Usuario2);
        Usuario2.setBounds(60, 210, 320, 40);

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel76.setText("Usuario");
        panel_ingreso.add(jLabel76);
        jLabel76.setBounds(60, 180, 90, 25);

        getContentPane().add(panel_ingreso);
        panel_ingreso.setBounds(380, 0, 420, 650);

        Fondo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/GIF-MEDIO.gif"))); // NOI18N
        getContentPane().add(Fondo4);
        Fondo4.setBounds(-3, -4, 1150, 650);

        Fondo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/GIF-INICIO.gif"))); // NOI18N
        getContentPane().add(Fondo3);
        Fondo3.setBounds(0, -10, 1150, 660);

        Fondo_Login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/fonfo-login.gif"))); // NOI18N
        getContentPane().add(Fondo_Login);
        Fondo_Login.setBounds(0, 0, 1160, 648);

        Fondo_Cambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/GIF-CAMBIOUSU.gif"))); // NOI18N
        getContentPane().add(Fondo_Cambio);
        Fondo_Cambio.setBounds(0, 0, 1150, 650);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    String variableprovisionalusuarioadmin="admin",variableprovisionalcontraadmin="admin";
    private void Boton_IngresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_IngresoMouseClicked
        if (Usuario.getText().equals(variableprovisionalusuarioadmin) && Contra.getText().equals(variableprovisionalcontraadmin)) {
            c = new ConexionDB();
            Fondo_Login.setVisible(false);
            Fondo4.setVisible(true);
            panel_ingreso.setVisible(false);
            Cambio_Usuario cu = new Cambio_Usuario(Fondo4,Fondo5,Fondo_Login ,Menu_Admin);
            cu.start();
        }else{
            Label_Incorrecto.setVisible(true);
        }
        
    }//GEN-LAST:event_Boton_IngresoMouseClicked

    private void Icono_ClaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_ClaseMouseClicked

        Cambio_Menu CM= new Cambio_Menu(Panel_Alumnos,Panel_Maestros,Panel_Carro,Panel_Informes,Icono_Maestra,Panel_Financiero,Panel_Notas,Panel_Clases,false);
        CM.start();


       

    }//GEN-LAST:event_Icono_ClaseMouseClicked

    private void Icono_CarroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_CarroMouseClicked
        if (Icono_Carro.isEnabled()) {
            Cambio_Menu CM= new Cambio_Menu(Panel_Carro,Panel_Maestros,Panel_Alumnos,Panel_Informes,Icono_Maestra,Panel_Financiero,Panel_Notas,Panel_Clases,true);
            CM.start();
            tf_UPC.setText("");
            tf_nombreProducto.setText("");
            tf_tamanoProducto.setText("");
            tf_marcaProducto.setText("");
            tf_embalajeProducto.setText("");
        }
        
    }//GEN-LAST:event_Icono_CarroMouseClicked

    private void Icono_MaestraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_MaestraMouseClicked
        if (Icono_Maestra.isEnabled()) {
            Cambio_Menu CM= new Cambio_Menu(Panel_Maestros,Panel_Alumnos,Panel_Carro,Panel_Informes,Icono_Maestra,Panel_Financiero,Panel_Notas,Panel_Clases,true);
            CM.start();
            tf_idVendedor.setText("");
            tf_nombreVendedor.setText("");
           
            
           
        }
        
    }//GEN-LAST:event_Icono_MaestraMouseClicked

    private void Icono_AlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_AlumnoMouseClicked
        Cambio_Menu CM= new Cambio_Menu(Panel_Alumnos,Panel_Maestros,Panel_Carro,Panel_Informes,Icono_Maestra,Panel_Financiero,Panel_Notas,Panel_Clases,true);
        CM.start();
    }//GEN-LAST:event_Icono_AlumnoMouseClicked

    private void Icono_NotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_NotasMouseClicked
        Cambio_Menu CM= new Cambio_Menu(Panel_Notas ,Panel_Alumnos,Panel_Maestros,Panel_Informes,Icono_Maestra,Panel_Financiero,Panel_Carro,Panel_Clases,true);
        CM.start();
        tf_idCliente.setText("");
        tf_nombreCliente.setText("");
        
       
    }//GEN-LAST:event_Icono_NotasMouseClicked

    private void Icono_FinancieroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_FinancieroMouseClicked
        Cambio_Menu CM= new Cambio_Menu(Panel_Financiero  ,Panel_Carro,Panel_Alumnos,Panel_Maestros,Icono_Maestra,Panel_Notas,Panel_Informes,Panel_Clases,true);
        CM.start();
    }//GEN-LAST:event_Icono_FinancieroMouseClicked

    private void Icono_InformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_InformeMouseClicked
        Cambio_Menu CM= new Cambio_Menu( Panel_Informes ,Panel_Carro,Panel_Alumnos,Panel_Maestros,Icono_Maestra,Panel_Financiero,Panel_Notas,Panel_Clases,true);
        CM.start();
        
        
    }//GEN-LAST:event_Icono_InformeMouseClicked

    private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseClicked
        Menu_Admin.setVisible(false);
        Fondo5.setVisible(false);
        Fondo_Login.setVisible(true);
        panel_ingreso.setVisible(true);
    }//GEN-LAST:event_jLabel72MouseClicked

    private void btn_createVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createVendedorMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idVendedor.getText();
            String nombre = tf_nombreVendedor.getText();
            if(!id.isEmpty() && !nombre.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL createVendedor(?,?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.setString(2, nombre);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Vendedor Creado Exitosamente");
                listarVendedores(table_Vendedores);
                tf_idVendedor.setText("");
                tf_nombreVendedor.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_createVendedorMouseClicked

    private void btn_updateVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateVendedorMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idVendedor.getText();
            String nombre = tf_nombreVendedor.getText();
            if(!id.isEmpty() && !nombre.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL updateVendedor(?,?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.setString(2, nombre);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Vendedor Modificado Exitosamente");
                listarVendedores(table_Vendedores);
                tf_idVendedor.setText("");
                tf_nombreVendedor.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btn_updateVendedorMouseClicked

    private void btn_deleteVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteVendedorMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idVendedor.getText();
            if(!id.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL deleteVendedor(?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Vendedor Eliminado Exitosamente");
                listarVendedores(table_Vendedores);
                tf_idVendedor.setText("");
                tf_nombreVendedor.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "Id de Vendedor no puede estar vacio");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_deleteVendedorMouseClicked

    private void btn_createClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createClienteMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idCliente.getText();
            String nombre = tf_nombreCliente.getText();
            if(!id.isEmpty() && !nombre.isEmpty() ){
                tf_idCliente.setText("");
                tf_nombreCliente.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_createClienteMouseClicked

    private void btn_updateClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateClienteMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idCliente.getText();
            String nombre = tf_nombreCliente.getText();
            
            if(!id.isEmpty() && !nombre.isEmpty() ){
               
                listarClientes(table_Clientes);
                tf_idCliente.setText("");
                tf_nombreCliente.setText("");

            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_updateClienteMouseClicked

    private void btn_deleteClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteClienteMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idCliente.getText();
            if(!id.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL deleteCliente(?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Cliente Eliminado Exitosamente");
                listarClientes(table_Clientes);
                tf_idCliente.setText("");
                tf_nombreCliente.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "Id de Cliente no puede estar vacio");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_deleteClienteMouseClicked

    private void btn_createProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createProductoMouseClicked
        // TODO add your handling code here:
        try{
            String upc = tf_UPC.getText();
            String nombre = tf_nombreProducto.getText();
            String tamano = tf_tamanoProducto.getText();
            String embalaje = tf_embalajeProducto.getText();
            String marca = tf_marcaProducto.getText();
            if(!upc.isEmpty() && !nombre.isEmpty() && !tamano.isEmpty() && !embalaje.isEmpty() && !marca.isEmpty() ){
                
                JOptionPane.showMessageDialog(null, "Alumno Creado Exitosamente");
                listarProductos(table_Productos);
                tf_UPC.setText("");
                tf_nombreProducto.setText("");
                tf_tamanoProducto.setText("");
                tf_embalajeProducto.setText("");
                tf_marcaProducto.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_createProductoMouseClicked

    private void btn_updateProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateProductoMouseClicked
        // TODO add your handling code here:
        try{
            String upc = tf_UPC.getText();
            String nombre = tf_nombreProducto.getText();
            String tamano = tf_tamanoProducto.getText();
            String embalaje = tf_embalajeProducto.getText();
            String marca = tf_marcaProducto.getText();
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_updateProductoMouseClicked

    private void btn_deleteProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteProductoMouseClicked
        // TODO add your handling code here:
        try{
            String upc = tf_UPC.getText();
            if(!upc.isEmpty()){
                tf_UPC.setText("");
                tf_nombreProducto.setText("");
                tf_tamanoProducto.setText("");
                tf_embalajeProducto.setText("");
                tf_marcaProducto.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_deleteProductoMouseClicked

    private void Boton_Ingreso3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_Ingreso3MouseClicked

        
        if (Correo.getText().equals("")) {
        panel_Registro.setVisible(false);
        Fondo_Login.setVisible(false);
        Fondo4.setVisible(true);
        Cambio_Ecena_Menu cmm = new Cambio_Ecena_Menu(Fondo4,Fondo5 ,Menu_Customer);
        cmm.start();
        }else{if (Correo.getText().equals("admin")&& contra.getText().equals("admin")) {
                panel_Registro.setVisible(false);
                Fondo_Login.setVisible(false);
                Fondo4.setVisible(true);
                Cambio_Ecena_Menu cmm = new Cambio_Ecena_Menu(Fondo4,Fondo5 ,Menu_Admin);
                cmm.start();
            }else  {
                Label_Incorrecto1.setVisible(true);
            }   
        }
    }//GEN-LAST:event_Boton_Ingreso3MouseClicked

    private void jLabel78MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MouseClicked
    panel_Registro.setVisible(false);
     Fondo_Login.setVisible(false);
     Fondo_Cambio.setVisible(true);
      Cambio_Usuario cu = new Cambio_Usuario(Fondo4 ,Fondo_Login,Fondo_Cambio,panel_ingreso);
        cu.start();
    
    
    }//GEN-LAST:event_jLabel78MouseClicked

    private void jLabel81MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseClicked
        panel_Registro.setVisible(true);
        panel_ingreso.setVisible(false);
    }//GEN-LAST:event_jLabel81MouseClicked

    private void jLabel82MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MouseClicked
        
        System.exit(0);
    }//GEN-LAST:event_jLabel82MouseClicked

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
        
        System.exit(0);
    }//GEN-LAST:event_jLabel83MouseClicked

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        listarProductosvendedor();
        
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        desenlistarProductosvendedor();
    }//GEN-LAST:event_jButton12MouseClicked

    private void cbox_ListaVendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbox_ListaVendedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_ListaVendedoresMouseClicked

    private void ComboReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboReportesActionPerformed
    
    }//GEN-LAST:event_ComboReportesActionPerformed

    private void cbox_ListaVendedoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_ListaVendedoresItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_ListaVendedoresItemStateChanged

    private void Icono_Tienda1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Tienda1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Tienda1MouseClicked

    private void Icono_Producto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Producto1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Producto1MouseClicked

    private void Icono_Vendedor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Vendedor1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Vendedor1MouseClicked

    private void Icono_Factura1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Factura1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Factura1MouseClicked

    private void Icono_Cliente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Cliente1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Cliente1MouseClicked

    private void ComboReportes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboReportes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboReportes1ActionPerformed

    private void btn_deleteCliente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteCliente1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteCliente1MouseClicked

    private void btn_createCliente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createCliente1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_createCliente1MouseClicked

    private void btn_updateCliente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateCliente1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateCliente1MouseClicked

    private void btn_deleteProducto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteProducto1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteProducto1MouseClicked

    private void btn_createProducto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createProducto1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_createProducto1MouseClicked

    private void btn_updateProducto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateProducto1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateProducto1MouseClicked

    private void btn_updateVendedor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateVendedor1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateVendedor1MouseClicked

    private void btn_deleteVendedor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteVendedor1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteVendedor1MouseClicked

    private void btn_createVendedor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createVendedor1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_createVendedor1MouseClicked

    private void cbox_ListaVendedores1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_ListaVendedores1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_ListaVendedores1ItemStateChanged

    private void cbox_ListaVendedores1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbox_ListaVendedores1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_ListaVendedores1MouseClicked

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14MouseClicked

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15MouseClicked

    private void jLabel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel99MouseClicked

    private void btn_modificarTienda1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificarTienda1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarTienda1MouseClicked

    private void btn_eliminarTienda1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarTienda1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarTienda1MouseClicked

    private void btn_crearTienda1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_crearTienda1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_crearTienda1MouseClicked

    private void cbox_NombreTiendas1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_NombreTiendas1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_NombreTiendas1ItemStateChanged

    private void btn_agregarProductoTienda1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregarProductoTienda1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agregarProductoTienda1MouseClicked

    private void btn_eliminarInventario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarInventario1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarInventario1MouseClicked

    private void btn_modifyInventario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modifyInventario1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modifyInventario1MouseClicked

    private void Icono_Tienda2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Tienda2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Tienda2MouseClicked

    private void Icono_Producto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Producto2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Producto2MouseClicked

    private void Icono_Vendedor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Vendedor2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Vendedor2MouseClicked

    private void Icono_Cliente2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_Cliente2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Icono_Cliente2MouseClicked

    private void ComboReportes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboReportes2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboReportes2ActionPerformed

    private void btn_deleteCliente2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteCliente2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteCliente2MouseClicked

    private void btn_createCliente2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createCliente2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_createCliente2MouseClicked

    private void btn_updateCliente2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateCliente2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateCliente2MouseClicked

    private void btn_deleteProducto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteProducto2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteProducto2MouseClicked

    private void btn_createProducto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createProducto2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_createProducto2MouseClicked

    private void btn_updateProducto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateProducto2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateProducto2MouseClicked

    private void btn_updateVendedor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateVendedor2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateVendedor2MouseClicked

    private void btn_deleteVendedor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteVendedor2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteVendedor2MouseClicked

    private void btn_createVendedor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createVendedor2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_createVendedor2MouseClicked

    private void cbox_ListaVendedores2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_ListaVendedores2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_ListaVendedores2ItemStateChanged

    private void cbox_ListaVendedores2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbox_ListaVendedores2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_ListaVendedores2MouseClicked

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16MouseClicked

    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17MouseClicked

    private void jLabel133MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel133MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel133MouseClicked

    private void btn_modificarTienda2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificarTienda2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarTienda2MouseClicked

    private void btn_eliminarTienda2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarTienda2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarTienda2MouseClicked

    private void btn_crearTienda2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_crearTienda2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_crearTienda2MouseClicked

    private void cbox_NombreTiendas2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_NombreTiendas2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_NombreTiendas2ItemStateChanged

    private void btn_agregarProductoTienda2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregarProductoTienda2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agregarProductoTienda2MouseClicked

    private void btn_eliminarInventario2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarInventario2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarInventario2MouseClicked

    private void btn_modifyInventario2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modifyInventario2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modifyInventario2MouseClicked

    private void Icono_ClaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_ClaseMouseEntered
        String rutaImagen = "/Mediaa/clases-Icono.gif";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Clase.setIcon(icono);
    }//GEN-LAST:event_Icono_ClaseMouseEntered

    private void Icono_ClaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_ClaseMouseExited
        String rutaImagen = "/Mediaa/Icono Clases Estatico.png";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Clase.setIcon(icono);
    }//GEN-LAST:event_Icono_ClaseMouseExited

    private void Icono_CarroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_CarroMouseEntered
        String rutaImagen = "/Mediaa/Icono-carro.gif";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Carro.setIcon(icono);
    }//GEN-LAST:event_Icono_CarroMouseEntered

    private void Icono_CarroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_CarroMouseExited
        String rutaImagen = "/Mediaa/Icono Carro Estatico.png";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Carro.setIcon(icono);
    }//GEN-LAST:event_Icono_CarroMouseExited

    private void Icono_AlumnoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_AlumnoMouseEntered
        String rutaImagen = "/Mediaa/Icono-Alumno.gif";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Alumno.setIcon(icono);
    }//GEN-LAST:event_Icono_AlumnoMouseEntered

    private void Icono_AlumnoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_AlumnoMouseExited
        String rutaImagen = "/Mediaa/Icono Alumno Estatico.png";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Alumno.setIcon(icono);
    }//GEN-LAST:event_Icono_AlumnoMouseExited

    private void Icono_MaestraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_MaestraMouseEntered
        String rutaImagen = "/Mediaa/Icono-Maestro.gif";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Maestra.setIcon(icono);
    }//GEN-LAST:event_Icono_MaestraMouseEntered

    private void Icono_MaestraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_MaestraMouseExited
        String rutaImagen = "/Mediaa/Icono Maestro Estatico.png";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Maestra.setIcon(icono);
    }//GEN-LAST:event_Icono_MaestraMouseExited

    private void Icono_NotasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_NotasMouseEntered
        String rutaImagen = "/Mediaa/Icono-Notas.gif";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Notas.setIcon(icono);
    }//GEN-LAST:event_Icono_NotasMouseEntered

    private void Icono_NotasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_NotasMouseExited
        String rutaImagen = "/Mediaa/Icono Notas Estatico.png";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Notas.setIcon(icono);
    }//GEN-LAST:event_Icono_NotasMouseExited

    private void Icono_InformeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_InformeMouseEntered
        String rutaImagen = "/Mediaa/Icono-Informe.gif";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Informe.setIcon(icono);
    }//GEN-LAST:event_Icono_InformeMouseEntered

    private void Icono_InformeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_InformeMouseExited
        String rutaImagen = "/Mediaa/Icono Informe Estatico.png";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Informe.setIcon(icono);
    }//GEN-LAST:event_Icono_InformeMouseExited

    private void Icono_FinancieroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_FinancieroMouseEntered
        String rutaImagen = "/Mediaa/Icono-Financiero.gif";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Financiero.setIcon(icono);
    }//GEN-LAST:event_Icono_FinancieroMouseEntered

    private void Icono_FinancieroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_FinancieroMouseExited
        String rutaImagen = "/Mediaa/Icono Financiero Estatico.png";
        URL urlImagen = getClass().getResource(rutaImagen);
        ImageIcon icono = new ImageIcon(urlImagen);
        Icono_Financiero.setIcon(icono);
    }//GEN-LAST:event_Icono_FinancieroMouseExited
    private void listarVendedores(JTable tablaVendedores){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from vendedor");
            DefaultTableModel modeloVendedor = (DefaultTableModel)tablaVendedores.getModel();
            modeloVendedor.setRowCount(0);
            while(rs.next()){
                int id = rs.getInt("idVendedor");
                String nombre = rs.getString("nombreVendedor");
                modeloVendedor.addRow(new Object[]{id, nombre});
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }

    private void listarVendedoresarray(){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from vendedor");
             while (rs.next()) {
            // Moverte a la siguiente fila y obtener los valores
            int idVendedor = rs.getInt("idVendedor");
            String nombreVendedor = rs.getString("nombreVendedor");
            
            // Crear una instancia de Vendedor y agregarla a la lista
            ven.add(new Vendedor(idVendedor, nombreVendedor));
        }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    private void listarVendedores2(JComboBox combo){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from vendedor");
             combo.removeAllItems();
            while(rs.next()){
                String nombre = rs.getString("nombreVendedor");
                combo.addItem(nombre);
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    private void listarClientes(JTable tablaClientes){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from cliente");
            DefaultTableModel modeloVendedor = (DefaultTableModel)tablaClientes.getModel();
            modeloVendedor.setRowCount(0);
            while(rs.next()){
                int id = rs.getInt("idCliente");
                String nombre = rs.getString("nombreCliente");
                String correo = rs.getString("correo");
                modeloVendedor.addRow(new Object[]{id, nombre, correo});
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    private void listarProductos(JTable tablaProductos){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from producto");
            DefaultTableModel modeloTienda = (DefaultTableModel)tablaProductos.getModel();
            modeloTienda.setRowCount(0);
            while(rs.next()){
                int upc = rs.getInt("UPC");
                String nombre = rs.getString("nombreProducto");
                String tamano = rs.getString("tamanoProducto");
                String embalaje = rs.getString("embalaje");
                String marca = rs.getString("marca");
                Statement st2 = c.conexion.createStatement();
                ResultSet rs2 = st.executeQuery("Select tipoProducto from tipo_producto where UPC = " + upc);
                while(rs2.next()){
                    String tipo = rs2.getString("tipoProducto");
                    modeloTienda.addRow(new Object[]{upc, nombre,tamano,embalaje,marca,tipo});
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    private void listarProductos2(JComboBox combo){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from producto");
             combo.removeAllItems();
            while(rs.next()){
                String nombre = rs.getString("nombreProducto");
                combo.addItem(nombre);
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    private void listarProductosarray(){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from producto");
            while(rs.next()){
                pro.add(new Producto(rs.getString("UPC"),rs.getString("nombreProducto")));
                Statement st2 = c.conexion.createStatement();
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    private void listarNombreTiendas(JComboBox cboxTienda){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select nombreTienda from tienda");
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while(rs.next()){
                String nombreTienda = rs.getString("nombreTienda");
                modelo.addElement(nombreTienda);
            }
            cboxTienda.setModel(modelo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void listarProductosvendedor(){
            
        try{
            boolean pass=false;
            for (Producto producto : pro2) {
                producto.getUpc().equals(pro.get(table_ProductosparaVendedor.getSelectedRow()).getUpc());
                pass=true;
            }
            if (pass) {
                JOptionPane.showMessageDialog(null, "Vendedor Ya Vende Este Producto");
            }else {
                DefaultTableModel modeloTienda = new DefaultTableModel() ;
                    modeloTienda.addColumn("UPC");
                modeloTienda.addColumn("Nombre");
                pro2.add(pro.get(table_ProductosparaVendedor.getSelectedRow()));
                for (Producto producto : pro2) {
                    modeloTienda.addRow(new Object[]{producto.getUpc(),producto.getNombre()});
                }  
                jTable11.setModel(modeloTienda);
                Vendedor venmientra= new Vendedor();
                for (Vendedor vende : ven) {
                    if (cbox_ListaVendedores.getSelectedItem().equals(vende.getNombre())) {
                        venmientra=vende;
                    }
                }
                CallableStatement llamador = c.conexion.prepareCall("CALL AgregarProductoVendedor(?,?)");
                llamador.setInt(1, venmientra.getId());
                
                 llamador.setInt(2,Integer.parseInt(pro2.get(pro2.size()-1).getUpc()));
                llamador.execute();
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Seleccione Un Producto A Agregar");
        }       
    }
    private void desenlistarProductosvendedor(){
            
        try{
                DefaultTableModel modeloTienda = new DefaultTableModel() ;
                modeloTienda.addColumn("UPC");
                modeloTienda.addColumn("Nombre");
                pro2.remove(jTable11.getSelectedRow());
                for (Producto producto : pro2) {
                    modeloTienda.addRow(new Object[]{producto.getUpc(),producto.getNombre()});
                    
                }  
                jTable11.setModel(modeloTienda);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Seleccione Un Producto A Eliminar");
        }       
    }
    private void listarProductosXTienda(String nombreTienda, JTable tablaInventarioTienda){
        try{
            Statement st,st2;
            ResultSet rs,rs2;
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select idTienda from tienda where nombreTienda = '" + nombreTienda + "'");
            while(rs.next()){    
                int idTienda = rs.getInt("idTienda");
                st2 = c.conexion.createStatement();
                rs2 = st.executeQuery("Select * from inventario where idTienda=" + idTienda);
                DefaultTableModel modeloProductosXTienda = (DefaultTableModel)tablaInventarioTienda.getModel();
                modeloProductosXTienda.setRowCount(0);
                while(rs2.next()){
                    int id = rs2.getInt("idTienda");
                    int upc = rs2.getInt("UPC");
                    float precio = rs2.getFloat("precioProducto");
                    int cantidad = rs2.getInt("cantidadProducto");
                    String reorden = rs2.getString("reordenProducto");
                    modeloProductosXTienda.addRow(new Object[]{id,upc,precio,cantidad,reorden});
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void listarTiendas(JTable tablaTiendas){
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from tienda");
            DefaultTableModel modeloTienda = (DefaultTableModel)tablaTiendas.getModel();
            modeloTienda.setRowCount(0);
            while(rs.next()){
                int id = rs.getInt("idTienda");
                String nombre = rs.getString("nombreTienda");
                String horario = rs.getString("horarioTienda");
                
                Statement st2 = c.conexion.createStatement();
                ResultSet rs2 = st.executeQuery("Select ubicacionTienda from ubicacion_tienda where idTienda = " + id);
                while(rs2.next()){
                    String ubicacion = rs2.getString("ubicacionTienda");
                    modeloTienda.addRow(new Object[]{id, nombre,horario,ubicacion});
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    private void listarTiendas2(JComboBox combo){
        Statement st;
        ResultSet rs;
        try{
            combo.removeAllItems();
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from tienda");
            
            while(rs.next()){
                String nombre = rs.getString("nombreTienda");
                combo.addItem(nombre);
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loggin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_Ingreso;
    private javax.swing.JButton Boton_Ingreso3;
    private javax.swing.JComboBox<String> ComboReportes;
    private javax.swing.JComboBox<String> ComboReportes1;
    private javax.swing.JComboBox<String> ComboReportes2;
    private javax.swing.JPasswordField Contra;
    private javax.swing.JPasswordField Contra1;
    private javax.swing.JTextField Correo;
    private javax.swing.JLabel Fondo3;
    private javax.swing.JLabel Fondo4;
    private javax.swing.JLabel Fondo5;
    private javax.swing.JLabel Fondo6;
    private javax.swing.JLabel Fondo7;
    private javax.swing.JLabel Fondo_Cambio;
    private javax.swing.JLabel Fondo_Login;
    private javax.swing.JLabel Icono_Alumno;
    private javax.swing.JLabel Icono_Carro;
    private javax.swing.JLabel Icono_Clase;
    private javax.swing.JLabel Icono_Cliente1;
    private javax.swing.JLabel Icono_Cliente2;
    private javax.swing.JLabel Icono_Factura1;
    private javax.swing.JLabel Icono_Financiero;
    private javax.swing.JLabel Icono_Informe;
    private javax.swing.JLabel Icono_Maestra;
    private javax.swing.JLabel Icono_Notas;
    private javax.swing.JLabel Icono_Producto1;
    private javax.swing.JLabel Icono_Producto2;
    private javax.swing.JLabel Icono_Tienda1;
    private javax.swing.JLabel Icono_Tienda2;
    private javax.swing.JLabel Icono_Vendedor1;
    private javax.swing.JLabel Icono_Vendedor2;
    private javax.swing.JLabel Label_Incorrecto;
    private javax.swing.JLabel Label_Incorrecto1;
    private javax.swing.JPanel Menu_Admin;
    private javax.swing.JPanel Menu_Customer;
    private javax.swing.JPanel Menu_Maestro;
    private javax.swing.JPanel Panel_Alumnos;
    private javax.swing.JPanel Panel_Bitacora1;
    private javax.swing.JPanel Panel_Bitacora2;
    private javax.swing.JPanel Panel_Carro;
    private javax.swing.JTabbedPane Panel_Clases;
    private javax.swing.JPanel Panel_Cliente1;
    private javax.swing.JPanel Panel_Cliente2;
    private javax.swing.JPanel Panel_Factura1;
    private javax.swing.JPanel Panel_Factura2;
    private javax.swing.JPanel Panel_Financiero;
    private javax.swing.JPanel Panel_Informes;
    private javax.swing.JPanel Panel_Informes1;
    private javax.swing.JPanel Panel_Informes2;
    private javax.swing.JPanel Panel_Maestros;
    private javax.swing.JPanel Panel_Menu_Izquierda;
    private javax.swing.JPanel Panel_Menu_Izquierda1;
    private javax.swing.JPanel Panel_Menu_Izquierda2;
    private javax.swing.JPanel Panel_Notas;
    private javax.swing.JPanel Panel_Productos1;
    private javax.swing.JPanel Panel_Productos2;
    private javax.swing.JTabbedPane Panel_Tienda1;
    private javax.swing.JTabbedPane Panel_Tienda2;
    private javax.swing.JPanel Panel_Tiendita1;
    private javax.swing.JPanel Panel_Tiendita2;
    private javax.swing.JPanel Panel_Vendedorsitos;
    private javax.swing.JPanel Panel_Vendedorsitos1;
    private javax.swing.JPanel Panel_Vendedorsitos2;
    private javax.swing.JPanel Panel_menu_abajo;
    private javax.swing.JPanel Panel_menu_abajo1;
    private javax.swing.JPanel Panel_menu_abajo2;
    private javax.swing.JTabbedPane Panel_vendedores1;
    private javax.swing.JTabbedPane Panel_vendedores2;
    private javax.swing.JTextField Usuario;
    private javax.swing.JTextField Usuario1;
    private javax.swing.JTextField Usuario2;
    private javax.swing.JButton btn_agregarProductoTienda1;
    private javax.swing.JButton btn_agregarProductoTienda2;
    private javax.swing.JToggleButton btn_crearTienda1;
    private javax.swing.JToggleButton btn_crearTienda2;
    private javax.swing.JButton btn_createCliente;
    private javax.swing.JButton btn_createCliente1;
    private javax.swing.JButton btn_createCliente2;
    private javax.swing.JButton btn_createProducto;
    private javax.swing.JButton btn_createProducto1;
    private javax.swing.JButton btn_createProducto2;
    private javax.swing.JButton btn_createVendedor;
    private javax.swing.JButton btn_createVendedor1;
    private javax.swing.JButton btn_createVendedor2;
    private javax.swing.JButton btn_deleteCliente;
    private javax.swing.JButton btn_deleteCliente1;
    private javax.swing.JButton btn_deleteCliente2;
    private javax.swing.JButton btn_deleteProducto;
    private javax.swing.JButton btn_deleteProducto1;
    private javax.swing.JButton btn_deleteProducto2;
    private javax.swing.JButton btn_deleteVendedor;
    private javax.swing.JButton btn_deleteVendedor1;
    private javax.swing.JButton btn_deleteVendedor2;
    private javax.swing.JToggleButton btn_eliminarInventario1;
    private javax.swing.JToggleButton btn_eliminarInventario2;
    private javax.swing.JToggleButton btn_eliminarTienda1;
    private javax.swing.JToggleButton btn_eliminarTienda2;
    private javax.swing.JToggleButton btn_modificarTienda1;
    private javax.swing.JToggleButton btn_modificarTienda2;
    private javax.swing.JToggleButton btn_modifyInventario1;
    private javax.swing.JToggleButton btn_modifyInventario2;
    private javax.swing.JButton btn_updateCliente;
    private javax.swing.JButton btn_updateCliente1;
    private javax.swing.JButton btn_updateCliente2;
    private javax.swing.JButton btn_updateProducto;
    private javax.swing.JButton btn_updateProducto1;
    private javax.swing.JButton btn_updateProducto2;
    private javax.swing.JButton btn_updateVendedor;
    private javax.swing.JButton btn_updateVendedor1;
    private javax.swing.JButton btn_updateVendedor2;
    private javax.swing.JComboBox<String> cbox_ListaVendedores;
    private javax.swing.JComboBox<String> cbox_ListaVendedores1;
    private javax.swing.JComboBox<String> cbox_ListaVendedores2;
    private javax.swing.JComboBox<String> cbox_NombreTiendas1;
    private javax.swing.JComboBox<String> cbox_NombreTiendas2;
    private javax.swing.JTextField contra;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JPanel panel_Registro;
    private javax.swing.JPanel panel_ingreso;
    private javax.swing.JTextArea ta_Bitacora1;
    private javax.swing.JTextArea ta_Bitacora2;
    private javax.swing.JTable tableReportes;
    private javax.swing.JTable tableReportes1;
    private javax.swing.JTable tableReportes2;
    private javax.swing.JTable table_Clientes;
    private javax.swing.JTable table_Clientes1;
    private javax.swing.JTable table_Clientes2;
    private javax.swing.JTable table_Productos;
    private javax.swing.JTable table_Productos1;
    private javax.swing.JTable table_Productos2;
    private javax.swing.JTable table_ProductosXTienda1;
    private javax.swing.JTable table_ProductosXTienda2;
    private javax.swing.JTable table_ProductosparaVendedor;
    private javax.swing.JTable table_ProductosparaVendedor1;
    private javax.swing.JTable table_ProductosparaVendedor2;
    private javax.swing.JTable table_Tiendas1;
    private javax.swing.JTable table_Tiendas2;
    private javax.swing.JTable table_TodosProductos1;
    private javax.swing.JTable table_TodosProductos2;
    private javax.swing.JTable table_Vendedores;
    private javax.swing.JTable table_Vendedores1;
    private javax.swing.JTable table_Vendedores2;
    private javax.swing.JTextField tf_UPC;
    private javax.swing.JTextField tf_UPC1;
    private javax.swing.JTextField tf_UPC2;
    private javax.swing.JTextField tf_cantidad1;
    private javax.swing.JTextField tf_cantidad2;
    private javax.swing.JTextField tf_correoCliente1;
    private javax.swing.JTextField tf_correoCliente2;
    private javax.swing.JTextField tf_embalajeProducto;
    private javax.swing.JTextField tf_embalajeProducto1;
    private javax.swing.JTextField tf_embalajeProducto2;
    private javax.swing.JTextField tf_horarioTienda1;
    private javax.swing.JTextField tf_horarioTienda2;
    private javax.swing.JTextField tf_idCliente;
    private javax.swing.JTextField tf_idCliente1;
    private javax.swing.JTextField tf_idCliente2;
    private javax.swing.JTextField tf_idTienda1;
    private javax.swing.JTextField tf_idTienda2;
    private javax.swing.JTextField tf_idVendedor;
    private javax.swing.JTextField tf_idVendedor1;
    private javax.swing.JTextField tf_idVendedor2;
    private javax.swing.JTextField tf_marcaProducto;
    private javax.swing.JTextField tf_marcaProducto1;
    private javax.swing.JTextField tf_marcaProducto2;
    private javax.swing.JTextField tf_nombreCliente;
    private javax.swing.JTextField tf_nombreCliente1;
    private javax.swing.JTextField tf_nombreCliente2;
    private javax.swing.JTextField tf_nombreProducto;
    private javax.swing.JTextField tf_nombreProducto1;
    private javax.swing.JTextField tf_nombreProducto2;
    private javax.swing.JTextField tf_nombreTienda1;
    private javax.swing.JTextField tf_nombreTienda2;
    private javax.swing.JTextField tf_nombreVendedor;
    private javax.swing.JTextField tf_nombreVendedor1;
    private javax.swing.JTextField tf_nombreVendedor2;
    private javax.swing.JTextField tf_precio1;
    private javax.swing.JTextField tf_precio2;
    private javax.swing.JTextField tf_reorden1;
    private javax.swing.JTextField tf_reorden2;
    private javax.swing.JTextField tf_tamanoProducto;
    private javax.swing.JTextField tf_tamanoProducto1;
    private javax.swing.JTextField tf_tamanoProducto2;
    private javax.swing.JTextField tf_tipoProducto1;
    private javax.swing.JTextField tf_tipoProducto2;
    private javax.swing.JTextField tf_ubicacionTienda1;
    private javax.swing.JTextField tf_ubicacionTienda2;
    // End of variables declaration//GEN-END:variables
}
