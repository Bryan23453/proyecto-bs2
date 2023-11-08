/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_estructuraa;

import java.awt.Label;
import javax.swing.JFrame;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
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
        Panel_Factura_Cliente.setVisible(false);
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
        panel_Registro = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        Correo = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        Boton_Ingreso3 = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        Label_Incorrecto1 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        Correo1 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        Fondo4 = new javax.swing.JLabel();
        Fondo3 = new javax.swing.JLabel();
        Menu_Admin = new javax.swing.JPanel();
        Panel_vendedores = new javax.swing.JTabbedPane();
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
        Panel_Cliente = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tf_idCliente = new javax.swing.JTextField();
        tf_nombreCliente = new javax.swing.JTextField();
        tf_correoCliente = new javax.swing.JTextField();
        btn_deleteCliente = new javax.swing.JButton();
        btn_createCliente = new javax.swing.JButton();
        btn_updateCliente = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_Clientes = new javax.swing.JTable();
        Panel_Productos = new javax.swing.JPanel();
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
        jLabel73 = new javax.swing.JLabel();
        tf_tipoProducto = new javax.swing.JTextField();
        Panel_Tienda = new javax.swing.JTabbedPane();
        Panel_Tiendita = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        tf_idTienda = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tf_nombreTienda = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tf_horarioTienda = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_Tiendas = new javax.swing.JTable();
        btn_modificarTienda = new javax.swing.JToggleButton();
        btn_eliminarTienda = new javax.swing.JToggleButton();
        btn_crearTienda = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        tf_ubicacionTienda = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        cbox_NombreTiendas = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        table_TodosProductos = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        table_ProductosXTienda = new javax.swing.JTable();
        btn_agregarProductoTienda = new javax.swing.JButton();
        tf_precio = new javax.swing.JTextField();
        tf_cantidad = new javax.swing.JTextField();
        tf_reorden = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        btn_eliminarInventario = new javax.swing.JToggleButton();
        btn_modifyInventario = new javax.swing.JToggleButton();
        Panel_Factura = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        Panel_Bitacora = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        ta_Bitacora = new javax.swing.JTextArea();
        Panel_Informes = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        ComboReportes = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableReportes = new javax.swing.JTable();
        Panel_menu_abajo = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        Panel_Menu_Izquierda = new javax.swing.JPanel();
        Icono_Tienda = new javax.swing.JLabel();
        Icono_Producto = new javax.swing.JLabel();
        Icono_Vendedor = new javax.swing.JLabel();
        Icono_Factura = new javax.swing.JLabel();
        Icono_Cliente = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbl_Bitacora = new javax.swing.JLabel();
        Fondo5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Menu_Customer = new javax.swing.JPanel();
        Panel_Factura_Cliente = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jSpinner1 = new javax.swing.JSpinner();
        Comprar = new javax.swing.JLabel();
        Eliminar_Producto = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        Label_IVA = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        Label_Total = new javax.swing.JLabel();
        Label_Precio_CU = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        Label_Subtotal = new javax.swing.JLabel();
        Label_Importe = new javax.swing.JLabel();
        Agregar_Producto = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        Fondo6 = new javax.swing.JLabel();
        Fondo_Login = new javax.swing.JLabel();
        Fondo_Cambio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        panel_ingreso.setBackground(new java.awt.Color(0,0,0,150));
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
        panel_Registro.add(Correo1);
        Correo1.setBounds(60, 420, 320, 40);

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel84.setText("Correo");
        panel_Registro.add(jLabel84);
        jLabel84.setBounds(60, 390, 90, 25);

        getContentPane().add(panel_Registro);
        panel_Registro.setBounds(380, 0, 420, 650);

        Fondo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/GIF-MEDIO.gif"))); // NOI18N
        getContentPane().add(Fondo4);
        Fondo4.setBounds(-3, -4, 1150, 650);

        Fondo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mediaa/GIF-INICIO.gif"))); // NOI18N
        getContentPane().add(Fondo3);
        Fondo3.setBounds(0, -10, 1150, 660);

        Menu_Admin.setLayout(null);

        Panel_Vendedorsitos.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Vendedorsitos.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Vendedorsitos.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(137, 250, 230));
        jLabel5.setText("Vendedores");
        Panel_Vendedorsitos.add(jLabel5);
        jLabel5.setBounds(400, 0, 270, 70);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(137, 250, 230));
        jLabel15.setText("ID");
        Panel_Vendedorsitos.add(jLabel15);
        jLabel15.setBounds(40, 120, 130, 25);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(137, 250, 230));
        jLabel16.setText("Nombre");
        Panel_Vendedorsitos.add(jLabel16);
        jLabel16.setBounds(40, 200, 130, 25);

        tf_nombreVendedor.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreVendedor.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos.add(tf_nombreVendedor);
        tf_nombreVendedor.setBounds(40, 230, 260, 30);

        tf_idVendedor.setBackground(new java.awt.Color(102, 102, 102));
        tf_idVendedor.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Vendedorsitos.add(tf_idVendedor);
        tf_idVendedor.setBounds(40, 150, 260, 30);

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

        Panel_vendedores.addTab("Vendedores", Panel_Vendedorsitos);

        jPanel5.setBackground(new java.awt.Color(0,0,0,200));
        jPanel5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(null);

        jLabel55.setBackground(new java.awt.Color(137, 250, 230));
        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(137, 250, 230));
        jLabel55.setText("Vendedor");
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
        jLabel56.setText("Productos");
        jPanel5.add(jLabel56);
        jLabel56.setBounds(190, 90, 100, 20);

        jLabel57.setBackground(new java.awt.Color(137, 250, 230));
        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(137, 250, 230));
        jLabel57.setText("Productos Que Vende");
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

        Panel_vendedores.addTab("Inventario", jPanel5);

        Menu_Admin.add(Panel_vendedores);
        Panel_vendedores.setBounds(1230, 0, 1080, 610);

        Panel_Cliente.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Cliente.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Cliente.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(137, 250, 230));
        jLabel3.setText("Clientes");
        Panel_Cliente.add(jLabel3);
        jLabel3.setBounds(420, 10, 270, 70);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(137, 250, 230));
        jLabel23.setText("Nombre");
        Panel_Cliente.add(jLabel23);
        jLabel23.setBounds(50, 130, 110, 30);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(137, 250, 230));
        jLabel24.setText("Correo");
        Panel_Cliente.add(jLabel24);
        jLabel24.setBounds(50, 210, 60, 30);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(137, 250, 230));
        jLabel25.setText("ID");
        Panel_Cliente.add(jLabel25);
        jLabel25.setBounds(50, 50, 60, 30);

        tf_idCliente.setBackground(new java.awt.Color(102, 102, 102));
        tf_idCliente.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente.add(tf_idCliente);
        tf_idCliente.setBounds(50, 80, 210, 30);

        tf_nombreCliente.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente.add(tf_nombreCliente);
        tf_nombreCliente.setBounds(50, 160, 210, 30);

        tf_correoCliente.setBackground(new java.awt.Color(102, 102, 102));
        tf_correoCliente.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Cliente.add(tf_correoCliente);
        tf_correoCliente.setBounds(50, 240, 210, 30);

        btn_deleteCliente.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteCliente.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteCliente.setText("Eliminar");
        btn_deleteCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteClienteMouseClicked(evt);
            }
        });
        Panel_Cliente.add(btn_deleteCliente);
        btn_deleteCliente.setBounds(60, 450, 160, 40);

        btn_createCliente.setBackground(new java.awt.Color(204, 204, 204));
        btn_createCliente.setForeground(new java.awt.Color(0, 0, 0));
        btn_createCliente.setText("Añadir");
        btn_createCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createClienteMouseClicked(evt);
            }
        });
        Panel_Cliente.add(btn_createCliente);
        btn_createCliente.setBounds(60, 310, 160, 40);

        btn_updateCliente.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateCliente.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateCliente.setText("Modificar");
        btn_updateCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateClienteMouseClicked(evt);
            }
        });
        Panel_Cliente.add(btn_updateCliente);
        btn_updateCliente.setBounds(60, 380, 160, 40);

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

        Panel_Cliente.add(jScrollPane4);
        jScrollPane4.setBounds(330, 90, 670, 450);

        Menu_Admin.add(Panel_Cliente);
        Panel_Cliente.setBounds(1230, 0, 1080, 610);

        Panel_Productos.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Productos.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Productos.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(137, 250, 230));
        jLabel6.setText("Productos");
        Panel_Productos.add(jLabel6);
        jLabel6.setBounds(500, 10, 270, 70);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(137, 250, 230));
        jLabel9.setText("UPC");
        Panel_Productos.add(jLabel9);
        jLabel9.setBounds(70, 50, 37, 20);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(137, 250, 230));
        jLabel10.setText("Nombre");
        Panel_Productos.add(jLabel10);
        jLabel10.setBounds(70, 100, 80, 20);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(137, 250, 230));
        jLabel11.setText("Tamaño");
        Panel_Productos.add(jLabel11);
        jLabel11.setBounds(70, 150, 80, 20);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(137, 250, 230));
        jLabel12.setText("Embalaje");
        Panel_Productos.add(jLabel12);
        jLabel12.setBounds(70, 200, 80, 30);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(137, 250, 230));
        jLabel13.setText("Marca");
        Panel_Productos.add(jLabel13);
        jLabel13.setBounds(70, 260, 80, 20);

        tf_embalajeProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_embalajeProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos.add(tf_embalajeProducto);
        tf_embalajeProducto.setBounds(70, 230, 220, 30);

        tf_UPC.setBackground(new java.awt.Color(102, 102, 102));
        tf_UPC.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos.add(tf_UPC);
        tf_UPC.setBounds(70, 70, 220, 30);

        tf_nombreProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos.add(tf_nombreProducto);
        tf_nombreProducto.setBounds(70, 120, 220, 30);

        tf_tamanoProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_tamanoProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos.add(tf_tamanoProducto);
        tf_tamanoProducto.setBounds(70, 170, 220, 30);

        btn_deleteProducto.setBackground(new java.awt.Color(204, 204, 204));
        btn_deleteProducto.setForeground(new java.awt.Color(0, 0, 0));
        btn_deleteProducto.setText("Eliminar");
        btn_deleteProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteProductoMouseClicked(evt);
            }
        });
        Panel_Productos.add(btn_deleteProducto);
        btn_deleteProducto.setBounds(110, 510, 170, 40);

        table_Productos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_Productos);

        Panel_Productos.add(jScrollPane1);
        jScrollPane1.setBounds(350, 100, 670, 450);

        btn_createProducto.setBackground(new java.awt.Color(204, 204, 204));
        btn_createProducto.setForeground(new java.awt.Color(0, 0, 0));
        btn_createProducto.setText("Agregar");
        btn_createProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createProductoMouseClicked(evt);
            }
        });
        Panel_Productos.add(btn_createProducto);
        btn_createProducto.setBounds(110, 390, 170, 40);

        btn_updateProducto.setBackground(new java.awt.Color(204, 204, 204));
        btn_updateProducto.setForeground(new java.awt.Color(0, 0, 0));
        btn_updateProducto.setText("Editar");
        btn_updateProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateProductoMouseClicked(evt);
            }
        });
        Panel_Productos.add(btn_updateProducto);
        btn_updateProducto.setBounds(110, 450, 170, 40);

        tf_marcaProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_marcaProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos.add(tf_marcaProducto);
        tf_marcaProducto.setBounds(70, 280, 220, 30);

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(137, 250, 230));
        jLabel73.setText("Tipo Producto");
        Panel_Productos.add(jLabel73);
        jLabel73.setBounds(70, 310, 120, 30);

        tf_tipoProducto.setBackground(new java.awt.Color(102, 102, 102));
        tf_tipoProducto.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Productos.add(tf_tipoProducto);
        tf_tipoProducto.setBounds(70, 340, 220, 30);

        Menu_Admin.add(Panel_Productos);
        Panel_Productos.setBounds(1230, 0, 1080, 610);

        Panel_Tienda.setBackground(new java.awt.Color(153, 153, 153));
        Panel_Tienda.setForeground(new java.awt.Color(0, 0, 0));

        Panel_Tiendita.setBackground(new java.awt.Color(0, 0, 0,200));
        Panel_Tiendita.setForeground(new java.awt.Color(0, 0, 0));
        Panel_Tiendita.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Tiendita.setLayout(null);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(137, 250, 230));
        jLabel26.setText("Horario");
        Panel_Tiendita.add(jLabel26);
        jLabel26.setBounds(110, 200, 100, 30);

        tf_idTienda.setBackground(new java.awt.Color(102, 102, 102));
        tf_idTienda.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita.add(tf_idTienda);
        tf_idTienda.setBounds(40, 70, 200, 30);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(137, 250, 230));
        jLabel27.setText("ID");
        Panel_Tiendita.add(jLabel27);
        jLabel27.setBounds(130, 40, 100, 30);

        tf_nombreTienda.setBackground(new java.awt.Color(102, 102, 102));
        tf_nombreTienda.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita.add(tf_nombreTienda);
        tf_nombreTienda.setBounds(40, 150, 200, 30);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(137, 250, 230));
        jLabel28.setText("Nombre");
        Panel_Tiendita.add(jLabel28);
        jLabel28.setBounds(110, 120, 100, 30);

        tf_horarioTienda.setBackground(new java.awt.Color(102, 102, 102));
        tf_horarioTienda.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita.add(tf_horarioTienda);
        tf_horarioTienda.setBounds(40, 230, 200, 30);

        table_Tiendas.setBackground(new java.awt.Color(153, 153, 153));
        table_Tiendas.setForeground(new java.awt.Color(0, 0, 0));
        table_Tiendas.setModel(new javax.swing.table.DefaultTableModel(
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
        table_Tiendas.setGridColor(new java.awt.Color(153, 153, 153));
        table_Tiendas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table_Tiendas.setShowGrid(false);
        jScrollPane5.setViewportView(table_Tiendas);

        Panel_Tiendita.add(jScrollPane5);
        jScrollPane5.setBounds(260, 90, 770, 470);

        btn_modificarTienda.setBackground(new java.awt.Color(204, 204, 204));
        btn_modificarTienda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_modificarTienda.setForeground(new java.awt.Color(0, 0, 0));
        btn_modificarTienda.setText("Modificar");
        btn_modificarTienda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modificarTiendaMouseClicked(evt);
            }
        });
        Panel_Tiendita.add(btn_modificarTienda);
        btn_modificarTienda.setBounds(60, 450, 150, 40);

        btn_eliminarTienda.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminarTienda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_eliminarTienda.setForeground(new java.awt.Color(0, 0, 0));
        btn_eliminarTienda.setText("Eliminar");
        btn_eliminarTienda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminarTiendaMouseClicked(evt);
            }
        });
        Panel_Tiendita.add(btn_eliminarTienda);
        btn_eliminarTienda.setBounds(60, 520, 150, 40);

        btn_crearTienda.setBackground(new java.awt.Color(204, 204, 204));
        btn_crearTienda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_crearTienda.setForeground(new java.awt.Color(0, 0, 0));
        btn_crearTienda.setText("Añadir");
        btn_crearTienda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_crearTiendaMouseClicked(evt);
            }
        });
        Panel_Tiendita.add(btn_crearTienda);
        btn_crearTienda.setBounds(60, 380, 150, 40);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(137, 250, 230));
        jLabel1.setText("Tiendas");
        Panel_Tiendita.add(jLabel1);
        jLabel1.setBounds(550, 10, 180, 64);

        tf_ubicacionTienda.setBackground(new java.awt.Color(102, 102, 102));
        tf_ubicacionTienda.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Tiendita.add(tf_ubicacionTienda);
        tf_ubicacionTienda.setBounds(40, 310, 200, 30);

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(137, 250, 230));
        jLabel50.setText("Ubicacion");
        Panel_Tiendita.add(jLabel50);
        jLabel50.setBounds(100, 280, 100, 30);

        Panel_Tienda.addTab("Tiendas", Panel_Tiendita);

        jPanel3.setBackground(new java.awt.Color(0,0,0,200));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(null);

        jLabel51.setBackground(new java.awt.Color(137, 250, 230));
        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(137, 250, 230));
        jLabel51.setText("Tienda");
        jPanel3.add(jLabel51);
        jLabel51.setBounds(40, 10, 130, 70);

        cbox_NombreTiendas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbox_NombreTiendas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbox_NombreTiendasItemStateChanged(evt);
            }
        });
        jPanel3.add(cbox_NombreTiendas);
        cbox_NombreTiendas.setBounds(170, 30, 310, 40);

        table_TodosProductos.setBackground(new java.awt.Color(153, 153, 153));
        table_TodosProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(table_TodosProductos);

        jPanel3.add(jScrollPane9);
        jScrollPane9.setBounds(50, 100, 400, 300);

        table_ProductosXTienda.setBackground(new java.awt.Color(153, 153, 153));
        table_ProductosXTienda.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(table_ProductosXTienda);

        jPanel3.add(jScrollPane10);
        jScrollPane10.setBounds(560, 100, 400, 300);

        btn_agregarProductoTienda.setBackground(new java.awt.Color(204, 204, 204));
        btn_agregarProductoTienda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_agregarProductoTienda.setForeground(new java.awt.Color(0, 0, 0));
        btn_agregarProductoTienda.setText("->");
        btn_agregarProductoTienda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_agregarProductoTiendaMouseClicked(evt);
            }
        });
        jPanel3.add(btn_agregarProductoTienda);
        btn_agregarProductoTienda.setBounds(480, 230, 50, 31);

        tf_precio.setBackground(new java.awt.Color(102, 102, 102));
        tf_precio.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(tf_precio);
        tf_precio.setBounds(40, 440, 220, 40);

        tf_cantidad.setBackground(new java.awt.Color(102, 102, 102));
        tf_cantidad.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(tf_cantidad);
        tf_cantidad.setBounds(40, 520, 220, 40);

        tf_reorden.setBackground(new java.awt.Color(102, 102, 102));
        tf_reorden.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(tf_reorden);
        tf_reorden.setBounds(280, 480, 220, 40);

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(137, 250, 230));
        jLabel52.setText("Cantidad");
        jPanel3.add(jLabel52);
        jLabel52.setBounds(110, 490, 80, 25);

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(137, 250, 230));
        jLabel53.setText("Reorder");
        jPanel3.add(jLabel53);
        jLabel53.setBounds(350, 450, 90, 25);

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(137, 250, 230));
        jLabel54.setText("Precio");
        jPanel3.add(jLabel54);
        jLabel54.setBounds(120, 410, 60, 20);

        btn_eliminarInventario.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminarInventario.setForeground(new java.awt.Color(0, 0, 0));
        btn_eliminarInventario.setText("Eliminar");
        btn_eliminarInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminarInventarioMouseClicked(evt);
            }
        });
        jPanel3.add(btn_eliminarInventario);
        btn_eliminarInventario.setBounds(590, 430, 140, 40);

        btn_modifyInventario.setBackground(new java.awt.Color(204, 204, 204));
        btn_modifyInventario.setForeground(new java.awt.Color(0, 0, 0));
        btn_modifyInventario.setText("Modificar");
        btn_modifyInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modifyInventarioMouseClicked(evt);
            }
        });
        jPanel3.add(btn_modifyInventario);
        btn_modifyInventario.setBounds(590, 490, 140, 40);

        Panel_Tienda.addTab("Inventario", jPanel3);

        Menu_Admin.add(Panel_Tienda);
        Panel_Tienda.setBounds(1230, 0, 1080, 610);

        Panel_Factura.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Factura.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Factura.setPreferredSize(new java.awt.Dimension(1070, 610));
        Panel_Factura.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(137, 250, 230));
        jLabel4.setText("Facturas");
        Panel_Factura.add(jLabel4);
        jLabel4.setBounds(470, 0, 270, 70);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(137, 250, 230));
        jLabel14.setText("Fecha");
        Panel_Factura.add(jLabel14);
        jLabel14.setBounds(30, 90, 100, 30);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(137, 250, 230));
        jLabel17.setText("ID Tienda");
        Panel_Factura.add(jLabel17);
        jLabel17.setBounds(30, 440, 100, 30);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(137, 250, 230));
        jLabel18.setText("Subtotal");
        Panel_Factura.add(jLabel18);
        jLabel18.setBounds(30, 230, 100, 30);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(137, 250, 230));
        jLabel19.setText("Total");
        Panel_Factura.add(jLabel19);
        jLabel19.setBounds(30, 300, 100, 30);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(137, 250, 230));
        jLabel20.setText("ISV");
        Panel_Factura.add(jLabel20);
        jLabel20.setBounds(30, 160, 100, 30);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(137, 250, 230));
        jLabel21.setText("ID Cliente");
        Panel_Factura.add(jLabel21);
        jLabel21.setBounds(30, 370, 100, 30);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(137, 250, 230));
        jLabel22.setText("Numero");
        Panel_Factura.add(jLabel22);
        jLabel22.setBounds(30, 20, 100, 30);

        jTextField7.setBackground(new java.awt.Color(102, 102, 102));
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.setText("jTextField7");
        Panel_Factura.add(jTextField7);
        jTextField7.setBounds(30, 50, 190, 30);

        jFormattedTextField1.setText("jFormattedTextField1");
        Panel_Factura.add(jFormattedTextField1);
        jFormattedTextField1.setBounds(30, 120, 190, 30);

        jTextField8.setBackground(new java.awt.Color(102, 102, 102));
        jTextField8.setForeground(new java.awt.Color(255, 255, 255));
        jTextField8.setText("jTextField8");
        Panel_Factura.add(jTextField8);
        jTextField8.setBounds(30, 190, 190, 30);

        jTextField9.setBackground(new java.awt.Color(102, 102, 102));
        jTextField9.setForeground(new java.awt.Color(255, 255, 255));
        jTextField9.setText("jTextField9");
        Panel_Factura.add(jTextField9);
        jTextField9.setBounds(30, 260, 190, 30);

        jTextField10.setBackground(new java.awt.Color(102, 102, 102));
        jTextField10.setForeground(new java.awt.Color(102, 102, 102));
        jTextField10.setText("jTextField10");
        Panel_Factura.add(jTextField10);
        jTextField10.setBounds(30, 330, 190, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Factura.add(jComboBox1);
        jComboBox1.setBounds(30, 400, 190, 30);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Panel_Factura.add(jComboBox2);
        jComboBox2.setBounds(30, 470, 190, 30);

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

        Panel_Factura.add(jScrollPane3);
        jScrollPane3.setBounds(280, 90, 710, 460);

        jToggleButton4.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton4.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton4.setText("Agregar");
        Panel_Factura.add(jToggleButton4);
        jToggleButton4.setBounds(20, 520, 76, 27);

        jToggleButton5.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton5.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton5.setText("Eliminar");
        Panel_Factura.add(jToggleButton5);
        jToggleButton5.setBounds(20, 560, 77, 27);

        jToggleButton6.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton6.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton6.setText("Modificar");
        Panel_Factura.add(jToggleButton6);
        jToggleButton6.setBounds(150, 540, 85, 27);

        Menu_Admin.add(Panel_Factura);
        Panel_Factura.setBounds(1230, 0, 1080, 610);

        Panel_Bitacora.setBackground(new java.awt.Color(0, 0, 0,150));
        Panel_Bitacora.setLayout(null);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(137, 250, 230));
        jLabel48.setText("Bitacora");
        Panel_Bitacora.add(jLabel48);
        jLabel48.setBounds(480, 0, 150, 50);

        ta_Bitacora.setColumns(20);
        ta_Bitacora.setRows(5);
        jScrollPane8.setViewportView(ta_Bitacora);

        Panel_Bitacora.add(jScrollPane8);
        jScrollPane8.setBounds(50, 70, 1000, 510);

        Menu_Admin.add(Panel_Bitacora);
        Panel_Bitacora.setBounds(1230, 0, 1080, 610);

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

        Panel_Menu_Izquierda.setBackground(new java.awt.Color(67, 122, 227,170));

        Icono_Tienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Tienda.png"))); // NOI18N
        Icono_Tienda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_TiendaMouseClicked(evt);
            }
        });

        Icono_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Productos 2.png"))); // NOI18N
        Icono_Producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_ProductoMouseClicked(evt);
            }
        });

        Icono_Vendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Vendedor.png"))); // NOI18N
        Icono_Vendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_VendedorMouseClicked(evt);
            }
        });

        Icono_Factura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Factura_1.png"))); // NOI18N
        Icono_Factura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_FacturaMouseClicked(evt);
            }
        });

        Icono_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Cliente_1.png"))); // NOI18N
        Icono_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Icono_ClienteMouseClicked(evt);
            }
        });

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Informe_1.png"))); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });

        lbl_Bitacora.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbl_Bitacora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Bitacora.png"))); // NOI18N
        lbl_Bitacora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_BitacoraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_Menu_IzquierdaLayout = new javax.swing.GroupLayout(Panel_Menu_Izquierda);
        Panel_Menu_Izquierda.setLayout(Panel_Menu_IzquierdaLayout);
        Panel_Menu_IzquierdaLayout.setHorizontalGroup(
            Panel_Menu_IzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Menu_IzquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_Menu_IzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Icono_Vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Bitacora)
                    .addComponent(jLabel29)
                    .addComponent(Icono_Cliente)
                    .addComponent(Icono_Factura)
                    .addComponent(Icono_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Icono_Tienda))
                .addContainerGap())
        );
        Panel_Menu_IzquierdaLayout.setVerticalGroup(
            Panel_Menu_IzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Menu_IzquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Icono_Tienda)
                .addGap(18, 18, 18)
                .addComponent(Icono_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Factura, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_Bitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        Menu_Admin.add(Panel_Menu_Izquierda);
        Panel_Menu_Izquierda.setBounds(0, 0, 80, 650);

        Fondo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/frame menu.png"))); // NOI18N
        Menu_Admin.add(Fondo5);
        Fondo5.setBounds(0, 0, 1160, 650);
        Menu_Admin.add(jPanel1);
        jPanel1.setBounds(80, 0, 10, 10);

        getContentPane().add(Menu_Admin);
        Menu_Admin.setBounds(0, 0, 2300, 650);

        Menu_Customer.setLayout(null);

        Panel_Factura_Cliente.setBackground(new java.awt.Color(255, 255, 255,150));

        jLabel58.setBackground(new java.awt.Color(0, 0, 0));
        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Factura_1.png"))); // NOI18N

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel59.setText("Factura A Nombre De:");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel60.setText("Nombre Usuario");

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(jTable12);

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel61.setText("Total");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel62.setText("Subtotal");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel63.setText("IVA");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel64.setText("Lps.");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel65.setText("Lps.");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel66.setText("Lps.");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel67.setText("0");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel68.setText("0");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel69.setText("0");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 0, 51));
        jLabel70.setText("X");
        jLabel70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel70MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_Factura_ClienteLayout = new javax.swing.GroupLayout(Panel_Factura_Cliente);
        Panel_Factura_Cliente.setLayout(Panel_Factura_ClienteLayout);
        Panel_Factura_ClienteLayout.setHorizontalGroup(
            Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Factura_ClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63)
                    .addComponent(jLabel61))
                .addGap(18, 18, 18)
                .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_Factura_ClienteLayout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel68))
                    .addGroup(Panel_Factura_ClienteLayout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel67))
                    .addGroup(Panel_Factura_ClienteLayout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel69)))
                .addGap(31, 31, 31))
            .addGroup(Panel_Factura_ClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_Factura_ClienteLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addGap(141, 141, 141)
                        .addComponent(jLabel58)
                        .addContainerGap(202, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Factura_ClienteLayout.createSequentialGroup()
                        .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(Panel_Factura_ClienteLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel60))))
                        .addGap(14, 14, 14))))
        );
        Panel_Factura_ClienteLayout.setVerticalGroup(
            Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Factura_ClienteLayout.createSequentialGroup()
                .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_Factura_ClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel58))
                    .addComponent(jLabel70))
                .addGap(51, 51, 51)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jLabel64)
                    .addComponent(jLabel69))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_Factura_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel65)
                    .addComponent(jLabel68))
                .addGap(21, 21, 21))
        );

        Menu_Customer.add(Panel_Factura_Cliente);
        Panel_Factura_Cliente.setBounds(370, 10, 440, 610);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255,150));
        jPanel2.setLayout(null);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setText("Producto:");
        jPanel2.add(jLabel32);
        jLabel32.setBounds(40, 30, 90, 30);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox4);
        jComboBox4.setBounds(140, 30, 280, 40);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setText("Cantidad:");
        jPanel2.add(jLabel33);
        jLabel33.setBounds(40, 110, 90, 25);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setText("Precio C/U:");
        jPanel2.add(jLabel34);
        jLabel34.setBounds(750, 20, 100, 25);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setText("Importe:");
        jPanel2.add(jLabel35);
        jLabel35.setBounds(750, 70, 90, 25);

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel36.setText("Lps.");
        jPanel2.add(jLabel36);
        jLabel36.setBounds(870, 20, 33, 25);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setText("Lps.");
        jPanel2.add(jLabel37);
        jLabel37.setBounds(840, 70, 40, 25);

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTable7);

        jPanel2.add(jScrollPane7);
        jScrollPane7.setBounds(20, 150, 1110, 340);
        jPanel2.add(jSpinner1);
        jSpinner1.setBounds(150, 110, 68, 26);

        Comprar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Comprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/verificar (3).png"))); // NOI18N
        Comprar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComprarMouseClicked(evt);
            }
        });
        jPanel2.add(Comprar);
        Comprar.setBounds(60, 500, 160, 140);

        Eliminar_Producto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Eliminar_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/eliminar.png"))); // NOI18N
        jPanel2.add(Eliminar_Producto);
        Eliminar_Producto.setBounds(480, 70, 70, 70);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setText("Subtotal");
        jPanel2.add(jLabel41);
        jLabel41.setBounds(780, 520, 90, 25);

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel42.setText("IVA");
        jPanel2.add(jLabel42);
        jLabel42.setBounds(780, 560, 80, 25);

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel43.setText("Total");
        jPanel2.add(jLabel43);
        jLabel43.setBounds(780, 600, 80, 25);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setText("Lps.");
        jPanel2.add(jLabel38);
        jLabel38.setBounds(970, 560, 50, 25);

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setText("Lps.");
        jPanel2.add(jLabel44);
        jLabel44.setBounds(970, 600, 50, 25);

        Label_IVA.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label_IVA.setText("0");
        jPanel2.add(Label_IVA);
        Label_IVA.setBounds(1010, 560, 40, 25);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setText("Lps.");
        jPanel2.add(jLabel46);
        jLabel46.setBounds(970, 520, 50, 25);

        Label_Total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label_Total.setText("0");
        jPanel2.add(Label_Total);
        Label_Total.setBounds(1010, 600, 40, 25);

        Label_Precio_CU.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label_Precio_CU.setText("0");
        jPanel2.add(Label_Precio_CU);
        Label_Precio_CU.setBounds(910, 20, 40, 25);

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel49.setText("Lps.");
        jPanel2.add(jLabel49);
        jLabel49.setBounds(970, 520, 50, 25);

        Label_Subtotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label_Subtotal.setText("0");
        jPanel2.add(Label_Subtotal);
        Label_Subtotal.setBounds(1010, 520, 40, 25);

        Label_Importe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label_Importe.setText("0");
        jPanel2.add(Label_Importe);
        Label_Importe.setBounds(880, 70, 40, 25);

        Agregar_Producto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Agregar_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/agregar.png"))); // NOI18N
        jPanel2.add(Agregar_Producto);
        Agregar_Producto.setBounds(480, 0, 70, 70);

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/cerrar-sesion.png"))); // NOI18N
        jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel71MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel71);
        jLabel71.setBounds(1000, 20, 70, 70);

        Menu_Customer.add(jPanel2);
        jPanel2.setBounds(0, 0, 1150, 650);

        Fondo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/frame menu.png"))); // NOI18N
        Menu_Customer.add(Fondo6);
        Fondo6.setBounds(0, 0, 1150, 650);

        getContentPane().add(Menu_Customer);
        Menu_Customer.setBounds(0, 0, 2300, 650);

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
            Cambio_Ecena_Menu cmm = new Cambio_Ecena_Menu(Fondo4,Fondo5 ,Menu_Admin);
            cmm.start();
        }else{
            Label_Incorrecto.setVisible(true);
        }
        
    }//GEN-LAST:event_Boton_IngresoMouseClicked

    private void Icono_TiendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_TiendaMouseClicked

        Cambio_Menu CM= new Cambio_Menu(Panel_Productos,Panel_Factura,Panel_Cliente,Panel_Informes,Icono_Vendedor,Panel_Bitacora,Panel_Tienda,Panel_vendedores,false);
        CM.start();
        tf_idTienda.setText("");
        tf_nombreTienda.setText("");
        tf_horarioTienda.setText("");
        tf_ubicacionTienda.setText("");
        listarTiendas(table_Tiendas);
        listarProductos(table_TodosProductos);
        listarNombreTiendas(cbox_NombreTiendas);
        ListSelectionModel selectionModel = table_Tiendas.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = table_Tiendas.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        tf_idTienda.setText(table_Tiendas.getValueAt(filaSeleccionada, 0).toString());
                        tf_nombreTienda.setText(table_Tiendas.getValueAt(filaSeleccionada, 1).toString());
                        tf_horarioTienda.setText(table_Tiendas.getValueAt(filaSeleccionada, 2).toString());
                        tf_ubicacionTienda.setText(table_Tiendas.getValueAt(filaSeleccionada, 3).toString());
                        ubicacionAntigua = table_Tiendas.getValueAt(filaSeleccionada, 3).toString();
                    }
                }
            }
        });
        ListSelectionModel selectionModel2 = table_TodosProductos.getSelectionModel();
        selectionModel2.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = table_TodosProductos.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        UPCProductoXAgregar = Integer.parseInt(table_TodosProductos.getValueAt(filaSeleccionada, 0).toString());
                    }
                }
            }
        });
        ListSelectionModel selectionModel3 = table_ProductosXTienda.getSelectionModel();
        selectionModel3.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = table_ProductosXTienda.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        idTiendaInventario = Integer.parseInt(table_ProductosXTienda.getValueAt(filaSeleccionada, 0).toString());
                        UPCInventario = Integer.parseInt(table_ProductosXTienda.getValueAt(filaSeleccionada, 1).toString());
                        tf_precio.setText(table_ProductosXTienda.getValueAt(filaSeleccionada, 2).toString());
                        tf_cantidad.setText(table_ProductosXTienda.getValueAt(filaSeleccionada, 3).toString());
                        tf_reorden.setText(table_ProductosXTienda.getValueAt(filaSeleccionada, 4).toString());
                    }
                }
            }
        });

    }//GEN-LAST:event_Icono_TiendaMouseClicked

    private void Icono_ProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_ProductoMouseClicked
        if (Icono_Producto.isEnabled()) {
            Cambio_Menu CM= new Cambio_Menu(Panel_Productos,Panel_Factura,Panel_Cliente,Panel_Informes,Icono_Vendedor,Panel_Bitacora,Panel_Tienda,Panel_vendedores,true);
            CM.start();
            tf_UPC.setText("");
            tf_nombreProducto.setText("");
            tf_tamanoProducto.setText("");
            tf_marcaProducto.setText("");
            tf_embalajeProducto.setText("");
            tf_tipoProducto.setText("");
            listarProductos(table_Productos);
            ListSelectionModel selectionModel = table_Productos.getSelectionModel();
            selectionModel.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int filaSeleccionada = table_Productos.getSelectedRow();
                        if (filaSeleccionada >= 0) {
                            tf_UPC.setText(table_Productos.getValueAt(filaSeleccionada, 0).toString());
                            tf_nombreProducto.setText(table_Productos.getValueAt(filaSeleccionada, 1).toString());
                            tf_tamanoProducto.setText(table_Productos.getValueAt(filaSeleccionada, 2).toString());
                            tf_embalajeProducto.setText(table_Productos.getValueAt(filaSeleccionada, 3).toString());
                            tf_marcaProducto.setText(table_Productos.getValueAt(filaSeleccionada, 4).toString());
                            tf_tipoProducto.setText(table_Productos.getValueAt(filaSeleccionada, 5).toString());
                            tipoAntiguo = table_Productos.getValueAt(filaSeleccionada, 5).toString();
                        }
                    }
                }
            });
        }
        
    }//GEN-LAST:event_Icono_ProductoMouseClicked

    private void Icono_VendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_VendedorMouseClicked
        if (Icono_Vendedor.isEnabled()) {
            Cambio_Menu CM= new Cambio_Menu(Panel_Productos,Panel_Factura,Panel_Cliente,Panel_Informes,Icono_Vendedor,Panel_Bitacora,Panel_vendedores,Panel_Tienda,false);
            CM.start();
            tf_idVendedor.setText("");
            tf_nombreVendedor.setText("");
            listarProductosarray();
            listarVendedoresarray();
            listarVendedores2(cbox_ListaVendedores);
            listarProductos(table_ProductosparaVendedor);
            DefaultTableModel modeloTienda = new DefaultTableModel() ;
            modeloTienda.addColumn("Nombre");
            modeloTienda.addColumn("Tamaño");
            modeloTienda.addColumn("Embalaje");
            modeloTienda.addColumn("Marca");
            jTable11.setModel(modeloTienda);
            listarVendedores(table_Vendedores);
            
            ListSelectionModel selectionModel = table_Vendedores.getSelectionModel();
            selectionModel.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int filaSeleccionada = table_Vendedores.getSelectedRow();
                        if (filaSeleccionada >= 0) {
                            tf_idVendedor.setText(table_Vendedores.getValueAt(filaSeleccionada, 0).toString());
                            tf_nombreVendedor.setText(table_Vendedores.getValueAt(filaSeleccionada, 1).toString());
                        }
                    }
                }
            });
        }
        
    }//GEN-LAST:event_Icono_VendedorMouseClicked

    private void Icono_FacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_FacturaMouseClicked
        Cambio_Menu CM= new Cambio_Menu(Panel_Factura,Panel_Cliente,Panel_Productos,Panel_Informes,Icono_Vendedor,Panel_Bitacora,Panel_Tienda,Panel_vendedores,true);
        CM.start();
    }//GEN-LAST:event_Icono_FacturaMouseClicked

    private void Icono_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Icono_ClienteMouseClicked
        Cambio_Menu CM= new Cambio_Menu(Panel_Cliente,Panel_Productos,Panel_Factura,Panel_Informes,Icono_Vendedor,Panel_Bitacora,Panel_Tienda,Panel_vendedores,true);
        CM.start();
        tf_idCliente.setText("");
        tf_nombreCliente.setText("");
        tf_correoCliente.setText("");
        listarClientes(table_Clientes);
        ListSelectionModel selectionModel = table_Clientes.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = table_Clientes.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        tf_idCliente.setText(table_Clientes.getValueAt(filaSeleccionada, 0).toString());
                        tf_nombreCliente.setText(table_Clientes.getValueAt(filaSeleccionada, 1).toString());
                        tf_correoCliente.setText(table_Clientes.getValueAt(filaSeleccionada, 2).toString());
                    }
                }
            }
        });
    }//GEN-LAST:event_Icono_ClienteMouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        Cambio_Menu CM= new Cambio_Menu(Panel_Informes,Panel_Cliente,Panel_Productos,Panel_Factura,Icono_Vendedor,Panel_Bitacora,Panel_Tienda,Panel_vendedores,true);
        CM.start();
    }//GEN-LAST:event_jLabel29MouseClicked

    private void btn_crearTiendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_crearTiendaMouseClicked
        try{
            String id = tf_idTienda.getText();
            String nombre = tf_nombreTienda.getText();
            String horario = tf_horarioTienda.getText();
            String ubicacion = tf_ubicacionTienda.getText();
            if(!id.isEmpty() && !nombre.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL createTienda(?,?,?,?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.setString(2, nombre);
                llamador.setString(3, horario);
                llamador.setString(4, ubicacion);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Tienda Creada Exitosamente");
                listarTiendas(table_Tiendas);
                tf_idTienda.setText("");
                tf_nombreTienda.setText("");
                tf_horarioTienda.setText("");
                tf_ubicacionTienda.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_crearTiendaMouseClicked

    private void lbl_BitacoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_BitacoraMouseClicked
        Cambio_Menu CM= new Cambio_Menu(Panel_Bitacora,Panel_Cliente,Panel_Productos,Panel_Factura,Icono_Vendedor,Panel_Informes,Panel_Tienda,Panel_vendedores,true);
        CM.start();
        Statement st;
        ResultSet rs;
        try{
            st = c.conexion.createStatement();
            rs = st.executeQuery("Select * from bitacora");
            StringBuilder result = new StringBuilder();
            while(rs.next()){
                String tablaAfectada = rs.getString("tabla_Afectada");
                String operacion = rs.getString("operacion");
                String fechaYHora = rs.getString("fecha");
                String usuario = rs.getString("usuario");
                result.append("En tabla ").append(tablaAfectada).append(", se realizo ").append(operacion).append(" el ").append(fechaYHora).append(" por ").append(usuario).append(".").append("\n");
            }
            ta_Bitacora.setText("");
            ta_Bitacora.setText(result.toString());
        }catch(Exception e){
            e.printStackTrace();
        } 
        
    }//GEN-LAST:event_lbl_BitacoraMouseClicked

    private void jLabel70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel70MouseClicked
        Panel_Factura_Cliente.setVisible(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_jLabel70MouseClicked

    private void ComprarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComprarMouseClicked
        Panel_Factura_Cliente.setVisible(true);
        jPanel2.setVisible(false);
    }//GEN-LAST:event_ComprarMouseClicked

    private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseClicked
        Menu_Customer.setVisible(false);
        Fondo_Login.setVisible(true);
        panel_ingreso.setVisible(true);
        
    }//GEN-LAST:event_jLabel71MouseClicked

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

    private void btn_modificarTiendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificarTiendaMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idTienda.getText();
            String nombre = tf_nombreTienda.getText();
            String horario = tf_horarioTienda.getText();
            String ubicacion = tf_ubicacionTienda.getText();
            if(!id.isEmpty() && !nombre.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL updateTienda(?,?,?,?,?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.setString(2, nombre);
                llamador.setString(3, horario);
                llamador.setString(4, ubicacion);
                llamador.setString(5, ubicacionAntigua);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Tienda Modificada Exitosamente");
                listarTiendas(table_Tiendas);
                tf_idTienda.setText("");
                tf_nombreTienda.setText("");
                tf_horarioTienda.setText("");
                tf_ubicacionTienda.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_modificarTiendaMouseClicked

    private void btn_eliminarTiendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarTiendaMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idTienda.getText();
            String nombre = tf_nombreTienda.getText();
            String horario = tf_horarioTienda.getText();
            String ubicacion = tf_ubicacionTienda.getText();
            if(!id.isEmpty() && !nombre.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL deleteTienda(?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Tienda Eliminada Exitosamente");
                listarTiendas(table_Tiendas);
                tf_idTienda.setText("");
                tf_nombreTienda.setText("");
                tf_horarioTienda.setText("");
                tf_ubicacionTienda.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_eliminarTiendaMouseClicked

    private void btn_createClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createClienteMouseClicked
        // TODO add your handling code here:
        try{
            String id = tf_idCliente.getText();
            String nombre = tf_nombreCliente.getText();
            String correo = tf_correoCliente.getText();
            if(!id.isEmpty() && !nombre.isEmpty() && !correo.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL createCliente(?,?,?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.setString(2, nombre);
                llamador.setString(3, correo);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Cliente Creado Exitosamente");
                listarClientes(table_Clientes);
                tf_idCliente.setText("");
                tf_nombreCliente.setText("");
                tf_correoCliente.setText("");
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
            String correo = tf_correoCliente.getText();
            if(!id.isEmpty() && !nombre.isEmpty() && !correo.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL updateCliente(?,?,?)");
                llamador.setInt(1,Integer.parseInt(id));
                llamador.setString(2, nombre);
                llamador.setString(3, correo);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Cliente Modificado Exitosamente");
                listarClientes(table_Clientes);
                tf_idCliente.setText("");
                tf_nombreCliente.setText("");
                tf_correoCliente.setText("");
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
                tf_correoCliente.setText("");
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
            String tipo = tf_tipoProducto.getText();
            if(!upc.isEmpty() && !nombre.isEmpty() && !tamano.isEmpty() && !embalaje.isEmpty() && !marca.isEmpty() && !tipo.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL createProducto(?,?,?,?,?,?)");
                llamador.setInt(1,Integer.parseInt(upc));
                llamador.setString(2, nombre);
                llamador.setString(3, tamano);
                llamador.setString(4, embalaje);
                llamador.setString(5, marca);
                llamador.setString(6, tipo);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Producto Creado Exitosamente");
                listarProductos(table_Productos);
                tf_UPC.setText("");
                tf_nombreProducto.setText("");
                tf_tamanoProducto.setText("");
                tf_embalajeProducto.setText("");
                tf_marcaProducto.setText("");
                tf_tipoProducto.setText("");
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
            String tipo = tf_tipoProducto.getText();
            if(!upc.isEmpty() && !nombre.isEmpty() && !tamano.isEmpty() && !embalaje.isEmpty() && !marca.isEmpty() && !tipo.isEmpty()){
                CallableStatement llamador = c.conexion.prepareCall("CALL updateProducto(?,?,?,?,?,?,?)");
                llamador.setInt(1,Integer.parseInt(upc));
                llamador.setString(2, nombre);
                llamador.setString(3, tamano);
                llamador.setString(4, embalaje);
                llamador.setString(5, marca);
                llamador.setString(6, tipo);
                llamador.setString(7, tipoAntiguo);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Producto Modificado Exitosamente");
                listarProductos(table_Productos);
                tf_UPC.setText("");
                tf_nombreProducto.setText("");
                tf_tamanoProducto.setText("");
                tf_embalajeProducto.setText("");
                tf_marcaProducto.setText("");
                tf_tipoProducto.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
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
                CallableStatement llamador = c.conexion.prepareCall("CALL deleteProducto(?)");
                llamador.setInt(1,Integer.parseInt(upc));
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Producto Eliminado Exitosamente");
                listarProductos(table_Productos);
                tf_UPC.setText("");
                tf_nombreProducto.setText("");
                tf_tamanoProducto.setText("");
                tf_embalajeProducto.setText("");
                tf_marcaProducto.setText("");
                tf_tipoProducto.setText("");
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
        }else{
            Label_Incorrecto1.setVisible(true);
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
        if(c != null){
            c.desconectar();
        }
        System.exit(0);
    }//GEN-LAST:event_jLabel82MouseClicked

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
        if(c != null){
            c.desconectar();
        }
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
        // TODO add your handling code here:
        String selectdValue = ComboReportes.getSelectedItem().toString();
        if(selectdValue == "Vista 1"){
            mostrarInventario();
        }else if(selectdValue == "Vista 2"){
            //ComboReportesFiltro.enable(true);
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String elemento : Clientes) {
                comboBoxModel.addElement(elemento);
            }
            
            mostrarComprasCliente();

        }else if(selectdValue == "Vista 3"){
            // ComboReportesFiltro.removeAllItems();
            mostrarHistorialVentas();
        }else if(selectdValue == "Vista 4"){
            mostrarTop20Tiendas();
        }else if(selectdValue == "Vista 5"){
            mostrarTop20TiendasUbi();
        }else if(selectdValue == "Vista 6"){
            mostrarTiendsVentas();
        }else if(selectdValue == "Vista 7"){
            mostrarCocaCola();
        }else{ // vista 8
            mostrarAntiLeche();
        }
    }//GEN-LAST:event_ComboReportesActionPerformed

    private void cbox_ListaVendedoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_ListaVendedoresItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_ListaVendedoresItemStateChanged

    private void cbox_NombreTiendasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_NombreTiendasItemStateChanged
        // TODO add your handling code here:
        String nombreTienda = (String)cbox_NombreTiendas.getSelectedItem();
        listarProductosXTienda(nombreTienda, table_ProductosXTienda);
    }//GEN-LAST:event_cbox_NombreTiendasItemStateChanged

    private void btn_agregarProductoTiendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregarProductoTiendaMouseClicked
        // TODO add your handling code here:
        try{
            int idTienda=0;
            String nombreTienda = (String)cbox_NombreTiendas.getSelectedItem();
            float precio = Float.parseFloat(tf_precio.getText());
            int cantidad = Integer.parseInt(tf_cantidad.getText());
            String reorden = tf_reorden.getText();
            Statement st;
            ResultSet rs;
            try{
                st = c.conexion.createStatement();
                rs = st.executeQuery("Select idTienda from tienda where nombreTienda = '"+nombreTienda+"'");
                while(rs.next()){
                    idTienda = rs.getInt("idTienda");
                }
            }catch(Exception e){
                e.printStackTrace();
            } 
            if(idTienda != 0){
                CallableStatement llamador = c.conexion.prepareCall("CALL insertarInventario(?,?,?,?,?)");
                llamador.setInt(1,idTienda);
                llamador.setInt(2, UPCProductoXAgregar);
                llamador.setFloat(3, precio);
                llamador.setInt(4, cantidad);
                llamador.setString(5, reorden);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Producto Agregado a Inventario Exitosamente");
                listarProductosXTienda(nombreTienda, table_ProductosXTienda);
                tf_precio.setText("");
                tf_cantidad.setText("");
                tf_reorden.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_agregarProductoTiendaMouseClicked

    private void btn_modifyInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modifyInventarioMouseClicked
        // TODO add your handling code here:
        try{
            String nombreTienda = (String)cbox_NombreTiendas.getSelectedItem();
            float precio = Float.parseFloat(tf_precio.getText());
            int cantidad = Integer.parseInt(tf_cantidad.getText());
            String reorden = tf_reorden.getText();
            Statement st;
            ResultSet rs;
            if(idTiendaInventario != 0){
                CallableStatement llamador = c.conexion.prepareCall("CALL updateInventario(?,?,?,?,?)");
                llamador.setInt(1,idTiendaInventario);
                llamador.setInt(2, UPCInventario);
                llamador.setFloat(3, precio);
                llamador.setInt(4, cantidad);
                llamador.setString(5, reorden);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Producto del Inventario Modificado Exitosamente");
                listarProductosXTienda(nombreTienda, table_ProductosXTienda);
                tf_precio.setText("");
                tf_cantidad.setText("");
                tf_reorden.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_modifyInventarioMouseClicked

    private void btn_eliminarInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarInventarioMouseClicked
        // TODO add your handling code here:
        try{
            String nombreTienda = (String)cbox_NombreTiendas.getSelectedItem();
            Statement st;
            ResultSet rs;
            if(idTiendaInventario != 0){
                CallableStatement llamador = c.conexion.prepareCall("CALL deleteInventario(?,?)");
                llamador.setInt(1,idTiendaInventario);
                llamador.setInt(2, UPCInventario);
                llamador.execute();
                JOptionPane.showMessageDialog(null, "Producto del Inventario Eliminado Exitosamente");
                listarProductosXTienda(nombreTienda, table_ProductosXTienda);
                tf_precio.setText("");
                tf_cantidad.setText("");
                tf_reorden.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "No deben de haber campos vacios");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valores no cumplen las restricciones");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_eliminarInventarioMouseClicked
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
    private javax.swing.JLabel Agregar_Producto;
    private javax.swing.JButton Boton_Ingreso;
    private javax.swing.JButton Boton_Ingreso3;
    private javax.swing.JComboBox<String> ComboReportes;
    private javax.swing.JLabel Comprar;
    private javax.swing.JPasswordField Contra;
    private javax.swing.JPasswordField Contra1;
    private javax.swing.JTextField Correo;
    private javax.swing.JTextField Correo1;
    private javax.swing.JLabel Eliminar_Producto;
    private javax.swing.JLabel Fondo3;
    private javax.swing.JLabel Fondo4;
    private javax.swing.JLabel Fondo5;
    private javax.swing.JLabel Fondo6;
    private javax.swing.JLabel Fondo_Cambio;
    private javax.swing.JLabel Fondo_Login;
    private javax.swing.JLabel Icono_Cliente;
    private javax.swing.JLabel Icono_Factura;
    private javax.swing.JLabel Icono_Producto;
    private javax.swing.JLabel Icono_Tienda;
    private javax.swing.JLabel Icono_Vendedor;
    private javax.swing.JLabel Label_IVA;
    private javax.swing.JLabel Label_Importe;
    private javax.swing.JLabel Label_Incorrecto;
    private javax.swing.JLabel Label_Incorrecto1;
    private javax.swing.JLabel Label_Precio_CU;
    private javax.swing.JLabel Label_Subtotal;
    private javax.swing.JLabel Label_Total;
    private javax.swing.JPanel Menu_Admin;
    private javax.swing.JPanel Menu_Customer;
    private javax.swing.JPanel Panel_Bitacora;
    private javax.swing.JPanel Panel_Cliente;
    private javax.swing.JPanel Panel_Factura;
    private javax.swing.JPanel Panel_Factura_Cliente;
    private javax.swing.JPanel Panel_Informes;
    private javax.swing.JPanel Panel_Menu_Izquierda;
    private javax.swing.JPanel Panel_Productos;
    private javax.swing.JTabbedPane Panel_Tienda;
    private javax.swing.JPanel Panel_Tiendita;
    private javax.swing.JPanel Panel_Vendedorsitos;
    private javax.swing.JPanel Panel_menu_abajo;
    private javax.swing.JTabbedPane Panel_vendedores;
    private javax.swing.JTextField Usuario;
    private javax.swing.JTextField Usuario1;
    private javax.swing.JTextField Usuario2;
    private javax.swing.JButton btn_agregarProductoTienda;
    private javax.swing.JToggleButton btn_crearTienda;
    private javax.swing.JButton btn_createCliente;
    private javax.swing.JButton btn_createProducto;
    private javax.swing.JButton btn_createVendedor;
    private javax.swing.JButton btn_deleteCliente;
    private javax.swing.JButton btn_deleteProducto;
    private javax.swing.JButton btn_deleteVendedor;
    private javax.swing.JToggleButton btn_eliminarInventario;
    private javax.swing.JToggleButton btn_eliminarTienda;
    private javax.swing.JToggleButton btn_modificarTienda;
    private javax.swing.JToggleButton btn_modifyInventario;
    private javax.swing.JButton btn_updateCliente;
    private javax.swing.JButton btn_updateProducto;
    private javax.swing.JButton btn_updateVendedor;
    private javax.swing.JComboBox<String> cbox_ListaVendedores;
    private javax.swing.JComboBox<String> cbox_NombreTiendas;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JLabel lbl_Bitacora;
    private javax.swing.JPanel panel_Registro;
    private javax.swing.JPanel panel_ingreso;
    private javax.swing.JTextArea ta_Bitacora;
    private javax.swing.JTable tableReportes;
    private javax.swing.JTable table_Clientes;
    private javax.swing.JTable table_Productos;
    private javax.swing.JTable table_ProductosXTienda;
    private javax.swing.JTable table_ProductosparaVendedor;
    private javax.swing.JTable table_Tiendas;
    private javax.swing.JTable table_TodosProductos;
    private javax.swing.JTable table_Vendedores;
    private javax.swing.JTextField tf_UPC;
    private javax.swing.JTextField tf_cantidad;
    private javax.swing.JTextField tf_correoCliente;
    private javax.swing.JTextField tf_embalajeProducto;
    private javax.swing.JTextField tf_horarioTienda;
    private javax.swing.JTextField tf_idCliente;
    private javax.swing.JTextField tf_idTienda;
    private javax.swing.JTextField tf_idVendedor;
    private javax.swing.JTextField tf_marcaProducto;
    private javax.swing.JTextField tf_nombreCliente;
    private javax.swing.JTextField tf_nombreProducto;
    private javax.swing.JTextField tf_nombreTienda;
    private javax.swing.JTextField tf_nombreVendedor;
    private javax.swing.JTextField tf_precio;
    private javax.swing.JTextField tf_reorden;
    private javax.swing.JTextField tf_tamanoProducto;
    private javax.swing.JTextField tf_tipoProducto;
    private javax.swing.JTextField tf_ubicacionTienda;
    // End of variables declaration//GEN-END:variables
}
