package Analizador;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.regex.*;
import java.util.Hashtable;
import java.util.ArrayList;
/**
 *
 * @author aisark ultima actualizacion 06-12-2016 01:16 am
 */
public class Interfaz extends javax.swing.JFrame {
    
    DefaultTableModel modelo;
    DefaultTableModel error;
    DefaultTableModel variden;
    DefaultTableModel varnum;
    
    int num_token = 1,num_var=1,num_numer=1,iter;

    String [] tipos_datos         = {"int","char","boolean","varchar","nchar","bigint","smallint","bit","decimal","numeric","money","smallmoney","tiyint",
                                     "float","real","date","datetime","timestam","time","text","nvarchar","ntext"};
    String [] carac_especial      = {";",",","{","}","*","(",")","*/","/*","//"};
    String [] opera_mat           = {"+","-","/","*","%"};
    String [] opera_relac         = {"=","<>","<",">","<=",">="};
    String [] opera_logico        = {"all","and","or","between","any","exist","in","like","not","null"};
    String [] palabras_reservadas = {"select","create","insert","delete","where","all","distinct","values","group","by","having","from","order","table","program","into"};
    String [] lineas_text;
    
    String xr_string="^\".*\"$",
           xr_literal="^[a-zA-Z]\\w*$",
            xr_fecha = "\\d{1,2}/\\d{1,2}/\\d{4}",
            xr_fecha2 = "\\d{1,2}/(?i)(ene|feb|mar|abr|may|jun|jul|ago|sep|oct|nov|dic)/\\d{4}";
    
    String au_s="",au_c="",au_comen="",lin_com="",auxau="",auxlin="",auxnt="";
    
    boolean au_b=false,crClau=false,insClau=false,selClau=false,delClau=false,comClau=false,comClau2=false;
    
    Pattern expresion_regular;
    Matcher mat;
    
    Hashtable<String, Tabla> tablas = new Hashtable<>();
    String nTable = "";
    ArrayList<String> colTable = new ArrayList<>();
    boolean aTable = false;
    boolean allInd = false;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Interfaz() {
        initComponents();
        setLocationRelativeTo(null);
        tablasAux();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLexico = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaEditor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTabla_sintax = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblError = new javax.swing.JTable();
        lblTablaError = new javax.swing.JLabel();
        lblTablaSimb1 = new javax.swing.JLabel();
        lblEditor = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPalabrasReserv = new javax.swing.JTable();
        lblPalabraReserv = new javax.swing.JLabel();
        lblTipoDatos = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTiposDatos = new javax.swing.JTable();
        lblCaracEspe = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCaracEsp = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblOperMat = new javax.swing.JTable();
        lblOperLogico = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblOperLog = new javax.swing.JTable();
        lblVarIdent = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblVarIden = new javax.swing.JTable();
        lblOperLogico2 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblVarNum = new javax.swing.JTable();
        tblOpeRela = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblOperRela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLexico.setText("Analizador Lexico,Sintactico y Semantico");
        btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicoActionPerformed(evt);
            }
        });

        txaEditor.setColumns(20);
        txaEditor.setRows(5);
        txaEditor.setText("program{\ncreate table alumno(nombre char,LastName char, Semestre int, NumCon int);\nDelete from hijo where nombre=\"jesus\";\ninsert into alumno values (\"Jesus\",\"Torres\",5,15231018);\nselect * from alumno order by nombre;\n}");
        jScrollPane1.setViewportView(txaEditor);

        tblTabla_sintax.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Tipo", "Ln", "No. Token", "Direccion"
            }
        ));
        jScrollPane2.setViewportView(tblTabla_sintax);

        tblError.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Tipo", "Ln", "No. Token"
            }
        ));
        jScrollPane3.setViewportView(tblError);

        lblTablaError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTablaError.setText("Tabla de Errores");

        lblTablaSimb1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTablaSimb1.setText("Tabla de Simbolos");

        lblEditor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEditor.setText("Editor de texto");

        tblPalabrasReserv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblPalabrasReserv);

        lblPalabraReserv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPalabraReserv.setText("Palabras Reservadas");

        lblTipoDatos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTipoDatos.setText("Tipo de Datos");

        tblTiposDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblTiposDatos);

        lblCaracEspe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCaracEspe.setText("Caracteres Especiales");

        tblCaracEsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblCaracEsp);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Operadores Matematicos");

        tblOperMat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblOperMat);

        lblOperLogico.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOperLogico.setText("Operadores Logico");

        tblOperLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblOperLog);

        lblVarIdent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVarIdent.setText("Variables/Identificadores");

        tblVarIden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tblVarIden);

        lblOperLogico2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOperLogico2.setText("Var. Numerica");

        tblVarNum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tblVarNum);

        tblOpeRela.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblOpeRela.setText("Operador Relacional");

        tblOperRela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblOperRela);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                            .addComponent(lblTablaError, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLexico)
                                .addGap(11, 11, 11))
                            .addComponent(lblTablaSimb1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblPalabraReserv, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoDatos)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCaracEspe)
                            .addComponent(tblOpeRela, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(lblOperLogico, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblVarIdent, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOperLogico2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLexico, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPalabraReserv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTipoDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCaracEspe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOperLogico, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblTablaSimb1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTablaError, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tblOpeRela, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOperLogico2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblVarIdent, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
/*---------------------------------------------------------------------------------------------------------------------------------------------------------    
            ANALIZADOR LEXICO
-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public boolean isPalabraReserv(String token){
        boolean flag=false;
        
        for(String palabra : palabras_reservadas){
            if(token.equalsIgnoreCase(palabra)){
                flag=true;
                break;
            }
        }
        
        return flag;
    }
    public boolean isCaracEsp(String token){
        boolean flag=false;
        
        for(String car:carac_especial){
            if(token.equalsIgnoreCase(car)){
                flag=true;
                break;
            }
        }
        return flag;
    }
    public boolean isNumeric(String token){
        try{
            Integer.parseInt(token);
            return true;
        }catch(NumberFormatException w){
            return false;
        }
    }          
    public boolean isLiteral(String token){
        boolean result=false;
        expresion_regular= Pattern.compile(xr_literal);
        mat = expresion_regular.matcher(token);
        result = mat.matches()&&!isPalabraReserv(token)&&!isOperLogic(token)&&!isOperRela(token);
        return result;
    }    
    public boolean isOperLogic(String token){
        boolean flag = false;
        
        for(String car:opera_logico){
            if(token.equalsIgnoreCase(car)){
                flag=true;
                break;
            }
        }
        
        return flag;
    }
    public boolean isOperRela(String token){
        boolean flag = false;
        
        for(String car:opera_relac){
            if(token.equalsIgnoreCase(car)){
                flag=true;
                break;
            }
        }
        
        return flag;
    }
    public boolean isOperMat(String token){
        boolean flag =  false;
        
        for(String car:opera_mat){
            if(token.equalsIgnoreCase(car)){
                flag=true;
                break;
            }
        }
        
        return flag;
    }
    public boolean isCadena(String token){
        expresion_regular= Pattern.compile(xr_string);
        mat = expresion_regular.matcher(token);
        return mat.matches();
    }
    public boolean istipoDato(String token){
        boolean flag=false;
       
        for (String tipos_dato : tipos_datos) {
            if (token.equalsIgnoreCase(tipos_dato)) {
                flag=true;
                break;
            }
        }
        
        return flag;
    }
    public boolean isDate(String token){
        expresion_regular= Pattern.compile(xr_fecha);
        mat = expresion_regular.matcher(token);
        return mat.matches();
    }
    public boolean isDate2(String token){
        expresion_regular= Pattern.compile(xr_fecha2);
        mat = expresion_regular.matcher(token);
        return mat.matches();
    }

    
    public int getDirTipoDato(String token){
        int dir=0;
        
        for (int i =0;i<tipos_datos.length;i++){
            
            if(token.equalsIgnoreCase(tipos_datos[i])){
                dir=(i+1);
            }
        }
        
        return dir;
    }
    public int getDirPalaReserv(String token){
        int dir=0;
        for (int i =0;i<palabras_reservadas.length;i++){
            
            if(token.equalsIgnoreCase(palabras_reservadas[i])){
                dir=(i+1);
            }
        }
        return dir;
    }
    public int getDirCarcEspec(String token){
        int dir=0;
        for (int i =0;i<carac_especial.length;i++){
            
            if(token.equalsIgnoreCase(carac_especial[i])){
                dir=(i+1);
            }
        }
        return dir;
    }
    public int getDirOperRelac(String token){
        int dir=0;
        for (int i =0;i<opera_relac.length;i++){
            
            if(token.equalsIgnoreCase(opera_relac[i])){
                dir=(i+1);
            }
        }
        return dir;
    }
    public int getDirOperaArit(String token){
        int dir=0;
        for (int i =0;i<opera_mat.length;i++){
            
            if(token.equalsIgnoreCase(opera_mat[i])){
                dir=(i+1);
            }
        }
        return dir;
    }
    public int getDirOperaLog(String token){
        int dir=0;
        for (int i =0;i<opera_logico.length;i++){
            
            if(token.equalsIgnoreCase(opera_logico[i])){
                dir=(i+1);
            }
        }
        return dir;
    }
        
    public void setCadena(String token,DefaultTableModel modelo,DefaultTableModel error,DefaultTableModel tblVarIden, DefaultTableModel varnum){
        if(isCadena(token)){
            analiLexico(token, modelo, error, tblVarIden, varnum);
        }else{
            if(!au_b){
                au_s+=token;
                au_b=true;
            }else{
                au_s+=" "+token;
                if(au_s.endsWith("\"")){
                    au_b=false;
                }
            }
        } 
    }
    public void setTokenAnali(String token,DefaultTableModel modelo,DefaultTableModel error,DefaultTableModel tblVarIden, DefaultTableModel varnum){
        if(!isOperRela(token)){
            if(!au_c.equals("")){
                analiLexico(au_c, modelo, error, tblVarIden, varnum);
                au_c="";
            }
            analiLexico(token, modelo, error, tblVarIden, varnum);
        }else{
            if(au_c.equals("")){
                if(!token.equals("=")){
                    au_c+=token;
                }else{
                    analiLexico(token, modelo, error, tblVarIden, varnum);
                } 
            }else if(!au_c.equals("")){
                if(!token.equals("<")){
                    au_c+=token;
                    analiLexico(au_c, modelo, error, tblVarIden, varnum);
                    au_c="";
                }else{
                    analiLexico(au_c, modelo, error, tblVarIden, varnum);
                    au_c=token;
                }
            }
        }
    }
    
    public void clearModel(DefaultTableModel modelo,DefaultTableModel error,DefaultTableModel tblVarIden, DefaultTableModel varnum){
        if(modelo.getRowCount()>0){
                do{
                    modelo.removeRow(modelo.getRowCount()-1);
                }while(modelo.getRowCount()!=0);
            }
        if(error.getRowCount()>0){
            do{
                error.removeRow(error.getRowCount()-1);
            }while(error.getRowCount()!=0);
        }
        
        if(tblVarIden.getRowCount()>0){
            do{
               tblVarIden.removeRow(tblVarIden.getRowCount()-1);
            }while(tblVarIden.getRowCount()!=0);
        }
        if(varnum.getRowCount()>0){
            do{
                varnum.removeRow(varnum.getRowCount()-1);
            }while(varnum.getRowCount()!=0);
        }
    }
    public void newTexto(){
        lineas_text=txaEditor.getText().split("\n");
        String [] texto = new String [lineas_text.length];
        int size=0;
        char [] arreglo = txaEditor.getText().toCharArray();

        for (int i = 0; i < arreglo.length; i++) {
            if(texto[size]==null){
                texto[size]="";     
             }
            if(!isCaracEsp(String.valueOf(arreglo[i]))&&!isOperMat(String.valueOf(arreglo[i]))&&
                !isOperRela(String.valueOf(arreglo[i]))&&!String.valueOf(arreglo[i]).equals("\n")){ 
                
                texto[size]+=arreglo[i];
            }else if(isCaracEsp(String.valueOf(arreglo[i]))||isOperMat(String.valueOf(arreglo[i]))||
                    isOperRela(String.valueOf(arreglo[i]))&&!String.valueOf(arreglo[i]).equals("\n")){  
                texto[size]+=" "+arreglo[i]+" ";
            }else if(String.valueOf(arreglo[i]).equals("\n")){
                size++;
            }

        }
        lineas_text=texto;
            
    }
    public void tokenizador(DefaultTableModel modelo,DefaultTableModel error,DefaultTableModel tblVarIden, DefaultTableModel varnum){
        
        for (iter=0;iter<lineas_text.length;iter++) {
            
            String[] tokens = lineas_text[iter].split(" ");
            for (String token : tokens) {
                
                if(!token.equals("")&&!token.startsWith("\"")&&!au_b){
                    
                    if(!au_s.equals("")){
                        analiLexico(au_s, modelo, error, tblVarIden, varnum);
                        
                        au_s="";
                    }
                    if((token.equals("*")||token.equals("/"))&&(au_comen.equals(""))){
                        au_comen=token;
                    }else if(!token.equals("*")&&!token.equals("/")&&!au_comen.equals("")){
                        setTokenAnali(au_comen, modelo, error, tblVarIden, varnum);
                        setTokenAnali(token, modelo, error, tblVarIden, varnum);
                        au_comen="";
                    }else if((token.equals("*")||token.equals("/"))&&!au_comen.equals("")){
                        if(token.equals("*")&&au_comen.equals("*")){
                            setTokenAnali(au_comen, modelo, error, tblVarIden, varnum);
                            setTokenAnali(token, modelo, error, tblVarIden, varnum); 
                            au_comen="";
                        }else{
                            au_comen+=token;
                            setTokenAnali(au_comen, modelo, error, tblVarIden, varnum);
                            au_comen="";
                        }
                    }else{
                        setTokenAnali(token, modelo, error, tblVarIden, varnum);
                    }
                    
                    
                }else if(!token.equals("")){
                    setCadena(token, modelo, error, tblVarIden, varnum);
                }
            }
            
            if(!au_s.equals("")){
                analiLexico(au_s, modelo, error, tblVarIden, varnum);
                au_s="";
            }
            if(!au_c.equals("")){
                analiLexico(au_c, modelo, error, tblVarIden, varnum);
                au_c="";
            }
            
            
        }
    }
    public void analiLexico(String token,DefaultTableModel modelo,DefaultTableModel error,DefaultTableModel tblVarIden, DefaultTableModel varnum){
        
        if(istipoDato(token)){
            modelo.addRow(new Object[]{token.toUpperCase(),"Tipo de Datos",(iter+1),num_token,"TD "+getDirTipoDato(token)});
            num_token++;
        }else if(isOperLogic(token)){
            modelo.addRow(new Object[]{token.toUpperCase(),"Operador Logico",(iter+1),num_token,"OL "+getDirOperaLog(token)});
            num_token++;
        }else if(isPalabraReserv(token)){
            modelo.addRow(new Object[]{token.toUpperCase(),"Palabra reservada",(iter+1),num_token,"PR "+getDirPalaReserv(token)});
            num_token++;
        }else if(isCaracEsp(token)){
            modelo.addRow(new Object[]{token.toUpperCase(),"Caracter Especial",(iter+1),num_token,"CE "+getDirCarcEspec(token)});
            num_token++;
        }else if (isNumeric(token)){
            modelo.addRow(new Object[]{token,"Es un valor numerico",(iter+1),num_token,"VN "+num_numer});
            varnum.addRow(new Object[]{num_numer,token});
            num_numer++;
            num_token++;
        }else if(!istipoDato(token)&&!isPalabraReserv(token)&&!isOperLogic(token)&&isLiteral(token)){
            modelo.addRow(new Object[]{token.toUpperCase(),"Es una Literal",(iter+1),num_token,"VI "+num_var});
            tblVarIden.addRow(new Object[]{num_var,token.toUpperCase()});
            num_token++;
            num_var++;
        }else if(!istipoDato(token)&&!isPalabraReserv(token)&&!isLiteral(token)&&!isOperLogic(token)&&isCadena(token)){
            modelo.addRow(new Object[]{token.toUpperCase(),"Cadena de Caracteres",(iter+1),num_token,"VI "+num_var});
            tblVarIden.addRow(new Object[]{num_var,token.toUpperCase()});
            num_var++;
            num_token++;
        }else if(isOperRela(token)){
            modelo.addRow(new Object[]{token,"Operador Relacional",(iter+1),num_token,"OR "+getDirOperRelac(token)});
            num_token++;
        }else if(isOperMat(token)){
            modelo.addRow(new Object[]{token,"Operador Matematico",(iter+1),num_token,"OM "+getDirOperaArit(token)});
            num_token++;
        }else{
            modelo.addRow(new Object[]{token,"Error Lexico",(iter+1),num_token,"Tabla de Errores"});
            error.addRow(new Object[]{token,"Error Lexico",(iter+1),num_token});
            num_token++;
        }


    }
    public void tablasAux(){
        DefaultTableModel pala_reserv = (DefaultTableModel)tblPalabrasReserv.getModel();
        DefaultTableModel tipo_dat    = (DefaultTableModel)tblTiposDatos.getModel();
        DefaultTableModel car_spec    = (DefaultTableModel)tblCaracEsp.getModel();
        DefaultTableModel ope_arit    = (DefaultTableModel)tblOperMat.getModel();
        DefaultTableModel ope_log     = (DefaultTableModel)tblOperLog.getModel();
        DefaultTableModel ope_rela    = (DefaultTableModel)tblOperRela.getModel();
        
        for(int i=0;i<palabras_reservadas.length;i++){
            pala_reserv.addRow(new Object[]{(i+1),palabras_reservadas[i].toUpperCase()});
        }
        
        for (int i = 0; i < tipos_datos.length; i++) {
            tipo_dat.addRow(new Object[]{(i+1),tipos_datos[i].toUpperCase()});
        }
        
        for (int i = 0; i < carac_especial.length; i++) {
            car_spec.addRow(new Object[]{(i+1),carac_especial[i].toUpperCase()});
        }
        
        for (int i = 0; i < opera_mat.length; i++) {
            ope_arit.addRow(new Object[]{(i+1),opera_mat[i].toUpperCase()});
        }
        
        for (int i = 0; i < opera_logico.length; i++) {
            ope_log.addRow(new Object[]{(i+1),opera_logico[i].toUpperCase()});
        }
        
        for (int i = 0; i < opera_relac.length; i++) {
            ope_rela.addRow(new Object[]{(i+1),opera_relac[i].toUpperCase()});
        }
    }
/*---------------------------------------------------------------------------------------------------------------------------------------------------------    
            ANALIZADOR SINTACTICO
-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void superAnliSin(DefaultTableModel error){
        String token0,token1,linea,numtoken,au_token;
        for (int i = 0; i < tblTabla_sintax.getRowCount(); i++) {
            
            if(i!=(tblTabla_sintax.getRowCount()-1)){
                if(i!=0){
                    au_token=String.valueOf(tblTabla_sintax.getValueAt(i-1, 0));
                }else{
                    au_token="";
                }
                token0=String.valueOf(tblTabla_sintax.getValueAt(i, 0));
                token1=String.valueOf(tblTabla_sintax.getValueAt(i+1, 0));
                linea=String.valueOf(tblTabla_sintax.getValueAt(i+1, 2));
                numtoken=String.valueOf(tblTabla_sintax.getValueAt(i+1, 3));
                
                analiSintac(token0, token1, linea,numtoken,au_token, error); 
                
            }else{
                au_token=String.valueOf(tblTabla_sintax.getValueAt(i-1, 0));
                token0=String.valueOf(tblTabla_sintax.getValueAt(i, 0));
                token1="";
                linea=String.valueOf(tblTabla_sintax.getValueAt(i, 2));
                numtoken=String.valueOf(tblTabla_sintax.getValueAt(i, 3));
                analiSintac(token0, token1, linea,numtoken,au_token, error);
                au_token=token0;
                token0="";
                analiSintac(token0, token1, linea, numtoken, au_token, error);
            }
            
        }
        
        
    }
    public void analiSintac(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        
        if(token0.equalsIgnoreCase("program")&&!token1.equals("{")&&!comClau){
            error.addRow(new Object[]{token1,"Se esperaba {",linea,numtoken});
        }else if(token0.equals("{")&&!comClau){
            if(!token1.equalsIgnoreCase("create")&&!token1.equalsIgnoreCase("insert")&&!token1.equalsIgnoreCase("select")&&!token1.equalsIgnoreCase("delete")){
                error.addRow(new Object[]{token0,"Se esperaba CREATE|INSERT|SELECT|DELETE",linea,numtoken});
            }if(!au_token.equalsIgnoreCase("program")){
                error.addRow(new Object[]{au_token,"Se esperaba PROGRAM",Integer.valueOf(linea)-1,Integer.valueOf(numtoken)-2});
            }            
        }else if(token0.equals("}")&&!comClau){
            if(!token1.equals("")&&!token1.equalsIgnoreCase("program")){
                error.addRow(new Object[]{au_token,"Se esperaba PROGRAM",linea,numtoken});
            }
        }else if((token0.equalsIgnoreCase("create")||crClau)&&!selClau&&!delClau&&!insClau&&!comClau){
            if(!au_token.equals("{")&&!au_token.equals(";")&&token0.equalsIgnoreCase("create")){
                error.addRow(new Object[]{au_token,"Se esperaba { | ;",linea,Integer.valueOf(numtoken)-1});
            }else{
                createSent(token0, token1,linea,numtoken,au_token, error);
            }
        }else if((token0.equalsIgnoreCase("insert")||insClau)&&!selClau&&!delClau&&!crClau&&!comClau){
            if(!au_token.equals("{")&&!au_token.equals(";")&&token0.equalsIgnoreCase("insert")){
                error.addRow(new Object[]{au_token,"Se esperaba { | ;",linea,Integer.valueOf(numtoken)-1});
            }else{
                insertSent(token0, token1,linea,numtoken,au_token, error);
            }
        }else if((token0.equalsIgnoreCase("select")||selClau)&&!insClau&&!delClau&&!crClau&&!comClau){
            if(!au_token.equals("{")&&!au_token.equals(";")&&token0.equalsIgnoreCase("select")){
                error.addRow(new Object[]{au_token,"Se esperaba { | ;",linea,Integer.valueOf(numtoken)-1});
            }else{
                selectSent(token0, token1,linea,numtoken,au_token, error);
            }
        }else if((token0.equalsIgnoreCase("delete")||delClau)&&!insClau&&!selClau&&!crClau&&!comClau){
            if(!au_token.equals("{")&&!au_token.equals(";")&&token0.equalsIgnoreCase("delete")){
                error.addRow(new Object[]{au_token,"Se esperaba { | ;",linea,Integer.valueOf(numtoken)-1});
            }else{
                deleteSent(token0, token1,linea,numtoken,au_token, error);
            }
        }else if(token0.equals("")&&!au_token.equals("}")){
            error.addRow(new Object[]{au_token,"Se esperaba }",linea,Integer.valueOf(numtoken)-1});
        } 
        
    }
    public void finSent(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        if(token0.equals(";")&&!token1.equals("}")&&!token1.equalsIgnoreCase("delete")&&!token1.equalsIgnoreCase("create")&&!token1.equalsIgnoreCase("insert")&&!token1.equalsIgnoreCase("select")){
            error.addRow(new Object[]{token1,"Se esperaba }",linea,numtoken});
        }
    }
    public void createSent(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        if(token0.equalsIgnoreCase("create")){
            crClau=true;
            createClause(token0,token1,linea,numtoken,au_token,error);
        }else if(token0.equalsIgnoreCase(";")){
            finSent(token0, token1, linea, numtoken, au_token, error);
            crClau=false;
            nTable = "";
        }else{
            createClause(token0,token1,linea,numtoken,au_token,error);
        }
    }
    public void createClause(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        
        if(token0.equalsIgnoreCase("create")&&!token1.equalsIgnoreCase("table")){
            error.addRow(new Object[]{token1,"Se esperaba TABLE",linea,numtoken});
        }else if(token0.equalsIgnoreCase("table")&&!isLiteral(token1)){
            error.addRow(new Object[]{token1,"Se esperaba LITERAL",linea,numtoken});
        }else if(isLiteral(token0)){
            if(au_token.equalsIgnoreCase("table")){
                if(!token1.equals("(")&&!token1.equals(";")){
                   error.addRow(new Object[]{token1,"Se esperaba ( | ;",linea,numtoken}); 
                }else{
                    tablas.put(token0, new Tabla());// A.S.
                    nTable = token0;
                }
                
            }else if((au_token.equals("(")||au_token.equals(","))){
                if(!istipoDato(token1)){
                    error.addRow(new Object[]{token1,"Se esperaba TIPO DE DATO",linea,numtoken});
                }else{ //A.S.
                    if(!tablas.get(nTable).existKey(token0)){
                        tablas.get(nTable).setColum(token0, token1);
                    }else{
                        error.addRow(new Object[]{token0,"Ya existe col. "+token0,linea,Integer.valueOf(numtoken)-1});
                    }
                }
            }
 
        }else if(token0.equals("(")&&!isLiteral(token1)){
            
            error.addRow(new Object[]{token1,"Se esperaba LITERAL",linea,numtoken});
            
        }else if(istipoDato(token0)){
            if(!isLiteral(au_token)){
                error.addRow(new Object[]{au_token,"Se esperaba LITERAL",linea,Integer.valueOf(numtoken)-1});
            }
            if(!token1.equals(")")&&!token1.equals(",")){
               error.addRow(new Object[]{token1,"Se esperaba )|,",linea,numtoken});
            }
            
        }else if(token0.equals(",")&&!isLiteral(token1)){
            error.addRow(new Object[]{token1,"Se esperaba LITERAL",linea,numtoken}); 
        }else if(token0.equals(")")&&!token1.equals(";")){
            error.addRow(new Object[]{token1,"Se esperaba ;",linea,numtoken});
        }

    }
    public void insertSent(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        if(token0.equalsIgnoreCase("insert")){
            insClau=true;
            insertClause(token0,token1,linea,numtoken,au_token,error);
        }else if(token0.equalsIgnoreCase(";")){
             finSent(token0, token1, linea, numtoken, au_token, error);
            insClau=false;
            clTable();
        }else{
            insertClause(token0,token1,linea,numtoken,au_token,error);
        }
    }
    public void insertClause(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        if(token0.equalsIgnoreCase("insert")&&!token1.equalsIgnoreCase("into")){
            error.addRow(new Object[]{token1,"Se esperaba INTO",linea,numtoken});
        }else if(token0.equalsIgnoreCase("into")&&!isLiteral(token1)){
            error.addRow(new Object[]{token1,"Se esperaba LITERAL",linea,numtoken});
        }else if(isLiteral(token0)){
            if(!token1.equalsIgnoreCase("values")){
                error.addRow(new Object[]{token1,"Se esperaba VALUES",linea,numtoken});
            }else{ // A.Se.
                if(tablas.containsKey(token0)){
                    nTable = token0;
                }else{
                    error.addRow(new Object[]{token0,"E.Se.: No existe la tabla "+token0,linea,Integer.valueOf(numtoken)-1});
                }
            }
            
        }else if(token0.equals("(")&&!isNumeric(token1)&&!isCadena(token1)){
            error.addRow(new Object[]{token1,"Se esperaba VALOR",linea,numtoken});
        }else if(token0.equals(",")&&!isNumeric(token1)&&!isCadena(token1)){
            error.addRow(new Object[]{token1,"Se esperaba VALOR",linea,numtoken}); 
        }else if(token0.equals(")")){
            if(!token1.equals(";")){
                error.addRow(new Object[]{token1,"Se esperaba ;",linea,numtoken}); 
            }else{
                if(!nTable.equals("")){
                    try {
                        if((tablas.get(nTable).index)<tablas.get(nTable).gsType()){
                            error.addRow(new Object[]{au_token,"E.Se.: Faltan datos en la insercción ",linea,Integer.valueOf(numtoken)-1});
                        }
                    } catch (IndexOutOfBoundsException e) {
                        error.addRow(new Object[]{au_token,"E.Se.: Desbordamiento de parametros",linea,Integer.valueOf(numtoken)-2});
                    }
                    
                }
                
            }
            
        }else if(token0.equalsIgnoreCase("values")&&!token1.equals("(")){
            error.addRow(new Object[]{token1,"Se esperaba (",linea,numtoken});
        }else if((isNumeric(token0)||isCadena(token0))){
            if ((au_token.equals("(")||au_token.equals(","))&&!token1.equals(")")&&!token1.equals(",")){
                error.addRow(new Object[]{token1,"Se esperaba   ) | ,",linea,numtoken});
            }else{
                try {
                    if(!nTable.equals("")){
                        String extd = tablas.get(nTable).gType(tablas.get(nTable).index);
                        if(isCorrecType(token0, extd)){
                            tablas.get(nTable).upind();
                        }else{
                            tablas.get(nTable).upind();
                            error.addRow(new Object[]{token0,"E.Se: Se esperaba un dato de tipo "+extd,linea,Integer.valueOf(numtoken)-1});
                        }
                    }
                } catch (Exception e) {
                    error.addRow(new Object[]{token0,"E.Se.: Desbordamiento de parametros",linea,Integer.valueOf(numtoken)-1});
                }
            }
        }
    }
    public void selectSent(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
       if(token0.equalsIgnoreCase("select")){
            selClau=true;
            selectClause(token0,token1,linea,numtoken,au_token,error);
        }else if(token0.equalsIgnoreCase(";")){
            finSent(token0, token1, linea, numtoken, au_token, error);
            clTable();
            selClau=false;
        }else{
            selectClause(token0,token1,linea,numtoken,au_token,error);
        } 
    }
    public void selectClause(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        
        if(token0.equalsIgnoreCase("select")){
            if(!token1.equals("*")&&!isLiteral(token1)&&!token1.equalsIgnoreCase("all")&&!token1.equalsIgnoreCase("distinct")){
                error.addRow(new Object[]{token1,"Se esperaba IDENTIFICADOR",linea,numtoken});
            }
            aTable = isLiteral(token1);
            allInd = token1.equals("*");
        }else if(token0.equals("*")&&!token1.equalsIgnoreCase("from")&&!token1.equals("/*")&&!token1.equals("//")){
            error.addRow(new Object[]{token1,"Se esperaba FROM",linea,numtoken});
        }else if(token0.equalsIgnoreCase("distinct")&&!isLiteral(token1)){
            error.addRow(new Object[]{token1,"Se esperaba LITERAL",linea,numtoken});
        }else if(isLiteral(token0)){
            if(au_token.equalsIgnoreCase("select")){
                if(!token1.equalsIgnoreCase("from")&&!token1.equals(",")){
                    error.addRow(new Object[]{token1,"Se esperaba  FROM | ,",linea,numtoken});
                }
                
            }else if((au_token.equalsIgnoreCase("where")||isOperLogic(au_token))){
                if(!isOperRela(token1)){
                    error.addRow(new Object[]{token1,"Se esperaba  OPERADOR RELACIONAL",linea,numtoken}); 
                }else{ // A.Se.
                    existColum(token0, linea, error);
                }
                
            }else if(au_token.equalsIgnoreCase("from")){
                if(!token1.equals(";")&&!token1.equalsIgnoreCase("order")&&!token1.equalsIgnoreCase("where")&&!token1.equalsIgnoreCase("group")){
                    error.addRow(new Object[]{token1,"Se esperaba  ;",linea,numtoken});
                }else{ //A.Se.
                    if(tablas.containsKey(token0)){
                        nTable = token0;
                        if(!allInd){
                            System.out.println(colTable);
                            for (int i = 0; i < colTable.size(); i++) {
                                if(!tablas.get(nTable).existKey(colTable.get(i))){
                                    error.addRow(new Object[]{colTable.get(i),"E.Se.: No existe la columna "+colTable.get(i)+" en la tabla "+nTable,linea,""});
                                }
                            }
                        }
                        
                    }else{
                        error.addRow(new Object[]{token0,"E.Se.: No existe la tabla "+token0,linea,Integer.valueOf(numtoken)-1});
                    }
                }
                
            }else if(au_token.equalsIgnoreCase("having")){
                if(!token1.equals(",")&&!token1.equals(";")){
                    error.addRow(new Object[]{token1,"Se esperaba  ;",linea,numtoken});
                }else{
                    existColum(token0, linea, error);
                }
                
            }else if(au_token.equalsIgnoreCase("by")){
                if(String.valueOf(tblTabla_sintax.getValueAt(Integer.valueOf(numtoken)-4, 0)).equalsIgnoreCase("group")){
                    if(!token1.equals(";")&&!token1.equalsIgnoreCase("having")){
                        error.addRow(new Object[]{token1,"Se esperaba  ;",linea,numtoken});
                    }else{
                        existColum(token0, linea, error);
                    }
                    
                }else if(String.valueOf(tblTabla_sintax.getValueAt(Integer.valueOf(numtoken)-4, 0)).equalsIgnoreCase("order")){
                    if(!token1.equals(";")&&!token1.equalsIgnoreCase(",")){
                        error.addRow(new Object[]{token1,"Se esperaba  ;",linea,numtoken});
                    }else{
                        existColum(token0, linea, error);
                    }
                    
                }
            }
            if(aTable){
                colTable.add(token0);
            }
            
        }else if((token0.equals(",")||token0.equalsIgnoreCase("from")||token0.equalsIgnoreCase("having")||token0.equalsIgnoreCase("by")||token0.equalsIgnoreCase("where"))){            
            if(!isLiteral(token1)){
                error.addRow(new Object[]{token1,"Se esperaba  LITERAL",linea,numtoken});
            }else{
                aTable = !token0.equalsIgnoreCase("from");
            }
            
        }else if((token0.equalsIgnoreCase("group")||token0.equalsIgnoreCase("order"))&&!token1.equalsIgnoreCase("by")){
            error.addRow(new Object[]{token1,"Se esperaba  BY",linea,numtoken});
        }else if(isLiteral(au_token)&&isOperRela(token0)){//++++++++++++++++++++++++++++++++++++++++ aqui me quede
            if((token0.equals("<>")||token0.equals("="))&&!isCadena(token1)&&!isNumeric(token1)){
                error.addRow(new Object[]{token1,"Se esperaba un VALOR",linea,numtoken});
            }else if((token0.equals("<=")||token0.equals(">=")||token0.equals(">")||token0.equals(">"))&&!isNumeric(token1)){
                error.addRow(new Object[]{token1,"Se esperaba un VALOR NUMERICO",linea,numtoken});
            }
            if(!nTable.equals("")){
                if(tablas.get(nTable).existKey(au_token)){// A.Se
                    if(!isCorrecType(token1, tablas.get(nTable).gDataType(au_token))){
                        error.addRow(new Object[]{token1,"E.Se: Tipo de dato incompatible con "+tablas.get(nTable).gDataType(au_token),linea,numtoken});
                    }
                }
            }
        }else if(isNumeric(token0)||isCadena(token0)){
            if(!token1.equals(";")&&!isOperLogic(token1)){
               error.addRow(new Object[]{token1,"Se esperaba ; | OPERADOR LOGICO",linea,numtoken}); 
            }            
        }else if(isOperLogic(token0)&&!isLiteral(token1)){
            error.addRow(new Object[]{token1,"Se esperaba  LITERAL",linea,numtoken});
        }
    }
    public void deleteSent(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        if(token0.equalsIgnoreCase("delete")){
            delClau=true;
            deleteClause(token0,token1,linea,numtoken,au_token,error);
        }else if(token0.equalsIgnoreCase(";")){
            finSent(token0, token1, linea, numtoken, au_token, error);
            clTable();
            delClau=false;
        }else{
            deleteClause(token0,token1,linea,numtoken,au_token,error);
        }
    }
    public void deleteClause(String token0,String token1,String linea,String numtoken,String au_token,DefaultTableModel error){
        if(token0.equalsIgnoreCase("delete")&&!token1.equalsIgnoreCase("from")){
            error.addRow(new Object[]{token1,"Se esperaba FROM",linea,numtoken});
        }else if(token0.equalsIgnoreCase("from")&&!isLiteral(token1)){
            error.addRow(new Object[]{token1,"Se esperaba LITERAL",linea,numtoken});
        }else if(isLiteral(token0)){
            if(au_token.equalsIgnoreCase("from")){
                if(!token1.equals(";")&&!token1.equalsIgnoreCase("where")){
                    error.addRow(new Object[]{token1,"Se esperaba  ; | where",linea,numtoken}); 
                }else{
                    if(tablas.containsKey(token0)){
                        nTable = token0;
                    }else{
                        error.addRow(new Object[]{token0,"E.Se.: No existe la tabla "+token0,linea,Integer.valueOf(numtoken)-1});
                    }
                }
                
            }else if((au_token.equalsIgnoreCase("where")||isOperLogic(au_token))){
                if(!isOperRela(token1)){
                    error.addRow(new Object[]{token1,"Se esperaba  OPERADOR RELACIONAL",linea,numtoken});
                }else{
                    existColum(token0, linea, error);
                }
            }
            
        }else if(token0.equalsIgnoreCase("where")&&!isLiteral(token1)){
            error.addRow(new Object[]{token1,"Se esperaba  LITERAL",linea,numtoken});
        }else if(isLiteral(au_token)&&isOperRela(token0)){
            if((token0.equals("<>")||token0.equals("="))&&!isCadena(token1)&&!isNumeric(token1)){
                error.addRow(new Object[]{token1,"Se esperaba un VALOR",linea,numtoken});
            }else if((token0.equals("<=")||token0.equals(">=")||token0.equals(">")||token0.equals(">"))&&!isNumeric(token1)){
                error.addRow(new Object[]{token1,"Se esperaba un VALOR NUMERICO",linea,numtoken});
            }
            if(!nTable.equals("")){
                if(tablas.get(nTable).existKey(au_token)){// A.Se
                    if(!isCorrecType(token1, tablas.get(nTable).gDataType(au_token))){
                        error.addRow(new Object[]{token1,"E.Se: Tipo de dato incompatible con "+tablas.get(nTable).gDataType(au_token),linea,numtoken});
                    }
                }
            }
        }else if(isNumeric(token0)||isCadena(token0)){
            if(!token1.equals(";")&&!isOperLogic(token1)&&!token1.equals("/*")&&!token1.equals("//")){
               error.addRow(new Object[]{token1,"Se esperaba ; | OPERADOR LOGICO",linea,numtoken}); 
            }            
        }else if(isOperLogic(token0)&&!isLiteral(token1)){
            error.addRow(new Object[]{token1,"Se esperaba  LITERAL",linea,numtoken});
        }
    }
/*---------------------------------------------------------------------------------------------------------------------------------------------------------    
            ANALIZADOR SEMANTICO
-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public boolean isCorrecType(String value, String type){
        boolean flag = false;
        String numer[] = {"int","bigint","smallint","decimal","numeric","money","smallmoney","tiyint","float","real"};
        String text[] = {"char","varchar","nchar","text","nvarchar","ntext"};
        String dates [] = {"date","datetime","timestam","time"};
        if(getTipo(numer, type)){
            flag = isNumeric(value);
        }else if (getTipo(text, type)){
            flag = isCadena(value);
        }else if(type.equalsIgnoreCase("boolean")){
            if(value.equalsIgnoreCase("false")||value.equalsIgnoreCase("true")){
                flag =true;
            }
        }else if(type.equalsIgnoreCase("bit")){
            try {
                byte bite = Byte.valueOf(value);
                flag = true;
            } catch (NumberFormatException e) {
                flag =false;
            }
        } else if(getTipo(dates, type)){
            flag = isDate(value)||isDate2(value);
        }
        return flag;
    }
    public boolean getTipo(String tipos[],String tipo){
        boolean flag = false;
        for (int i = 0; i < tipos.length; i++) {
            flag = tipos[i].equalsIgnoreCase(tipo);
            if(flag) break;
        }
        return flag;
    }
    public void clTable(){
        if(!tablas.isEmpty()&&!nTable.equals(""))tablas.get(nTable).index = 0;
        nTable = "";
        aTable = false;
        allInd = false;
        if(!colTable.isEmpty())colTable.clear();
    }
    public void existColum(String token0,String linea,DefaultTableModel error){
        if(!nTable.equals("")){
            if(!tablas.get(nTable).existKey(token0)){
                error.addRow(new Object[]{token0,"E.Se.: No existe la columna "+token0+" en la tabla "+nTable,linea,""});
            }
        }
    }
    
    
    private void btnLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLexicoActionPerformed
        // TODO add your handling code here:
        modelo      = (DefaultTableModel)tblTabla_sintax.getModel();
        error       = (DefaultTableModel)tblError.getModel();
        variden     = (DefaultTableModel)tblVarIden.getModel();
        varnum      = (DefaultTableModel)tblVarNum.getModel();
        
        
        
        if(!txaEditor.getText().isEmpty()){
            tablas.clear();
            clTable();
            au_b=false;au_s="";au_c="";
            num_token = 1;num_var=1;num_numer=1;
            clearModel(modelo, error,variden,varnum);
            newTexto();
            tokenizador(modelo, error,variden,varnum);
            superAnliSin(error);
        }else{
            JOptionPane.showMessageDialog(rootPane,"Campos Vacios","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLexicoActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLexico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblCaracEspe;
    private javax.swing.JLabel lblEditor;
    private javax.swing.JLabel lblOperLogico;
    private javax.swing.JLabel lblOperLogico2;
    private javax.swing.JLabel lblPalabraReserv;
    private javax.swing.JLabel lblTablaError;
    private javax.swing.JLabel lblTablaSimb1;
    private javax.swing.JLabel lblTipoDatos;
    private javax.swing.JLabel lblVarIdent;
    private javax.swing.JTable tblCaracEsp;
    private javax.swing.JTable tblError;
    private javax.swing.JLabel tblOpeRela;
    private javax.swing.JTable tblOperLog;
    private javax.swing.JTable tblOperMat;
    private javax.swing.JTable tblOperRela;
    private javax.swing.JTable tblPalabrasReserv;
    private javax.swing.JTable tblTabla_sintax;
    private javax.swing.JTable tblTiposDatos;
    private javax.swing.JTable tblVarIden;
    private javax.swing.JTable tblVarNum;
    private javax.swing.JTextArea txaEditor;
    // End of variables declaration//GEN-END:variables
}
