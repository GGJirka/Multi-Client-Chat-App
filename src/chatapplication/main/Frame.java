/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package chatapplication.main;

import chatapplication.connection.Client;
import chatapplication.connection.Server;
import chatapplication.database_connection.DatabaseManager;
import chatapplication.frames.ChatFrame;
import chatapplication.frames.RegisterFrame;
import chatapplication.frames.LoginFrame;
import chatapplication.frames.ProfileFrame;
import chatapplication.user.User;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;

/**
 *
 * @author Adminn
*/

public class Frame extends javax.swing.JFrame {

    private LoginFrame login;
    private ChatFrame chat;
    private ProfileFrame profileFrame;
    private RegisterFrame register;
    private DatabaseManager database;
    private JLabel logged;
    public boolean isLogged = false;
    private Server server;
    
    /**
     * Creates new form Frame
     */
    public Frame(){
        initComponents();       
        logged = new JLabel();
        setTitle("Stalk 1.0");
        topMenu.remove(menuChat);
        topMenu.remove(menuLogout);
        topMenu.remove(menuProfile);
        try {
            database = new DatabaseManager("team_speak","root","");            
        } catch (SQLException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        register = new RegisterFrame(database);
        login = new LoginFrame(database, this);
        login.setVisible(false);
        register.setVisible(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    login.onExit();
                } catch (SQLException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });      
        
    }

    public void isLogged(String username){
        try {
            logged.setText("Logged as: "+username);
            initUser(username);
            topMenu.add(menuProfile);
            topMenu.add(menuChat);
            topMenu.add(menuLogout);
            isLogged = true;
            topMenu.remove(menuLogin);
            topMenu.remove(menuRegister);               
            topMenu.add(Box.createGlue());
            topMenu.add(logged);          
            chat = new ChatFrame(username, database);
            profileFrame = new ProfileFrame(database);            
            chat.setVisible(false);
            profileFrame.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void initUser(String username) throws SQLException{
        PreparedStatement sel = database.Select(null, "users","username = "+"'"+username+"'");
        ResultSet user =  sel.executeQuery();
        user.next();
        database.user = new User(0, user.getString("username"),user.getString("password"),user.getString("mail"));  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        desktop = new javax.swing.JDesktopPane();
        topMenu = new javax.swing.JMenuBar();
        menuRegister = new javax.swing.JMenu();
        menuLogin = new javax.swing.JMenu();
        menuChat = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();
        menuProfile = new javax.swing.JMenu();

        jLabel1.setText("jLabel1");

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );

        menuRegister.setText("Register");
        menuRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRegisterMouseClicked(evt);
            }
        });
        menuRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegisterActionPerformed(evt);
            }
        });
        topMenu.add(menuRegister);

        menuLogin.setText("Login");
        menuLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLoginMouseClicked(evt);
            }
        });
        topMenu.add(menuLogin);

        menuChat.setText("Chat");
        menuChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuChatMouseClicked(evt);
            }
        });
        topMenu.add(menuChat);

        menuLogout.setText("Logout");
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
        });
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });
        topMenu.add(menuLogout);

        menuProfile.setText("Profile");
        menuProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuProfileMouseClicked(evt);
            }
        });
        topMenu.add(menuProfile);

        setJMenuBar(topMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuChatMouseClicked
        if(!chat.isVisible()){       
            this.desktop.add(chat);
            chat.setVisible(true);
            try {
                chat.friendListConf();
            } catch (SQLException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_menuChatMouseClicked

    private void menuLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLoginMouseClicked
        if(!login.isVisible()){     
            this.desktop.add(login);
            login.setVisible(true);  
        }
        
    }//GEN-LAST:event_menuLoginMouseClicked

    private void menuRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegisterActionPerformed

    }//GEN-LAST:event_menuRegisterActionPerformed

    private void menuRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRegisterMouseClicked
       if(!register.isVisible()){  
           this.desktop.add(register);
            register.setVisible(true);
       }
    }//GEN-LAST:event_menuRegisterMouseClicked

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        
    }//GEN-LAST:event_menuLogoutActionPerformed

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        
        try {
            PreparedStatement update = database.Update("users", "session", "0", "username = "+"'"+database.user.getUsername()+"'");
            if(update.executeUpdate() == 1){
                topMenu.removeAll();
                database.user.logout();
                isLogged = false;               
                topMenu.add(menuRegister);
                topMenu.add(menuLogin);
                chat.setVisible(false);
                refreshDesktop();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuLogoutMouseClicked

    private void menuProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuProfileMouseClicked
        if(!profileFrame.isVisible()){
            this.desktop.add(profileFrame);
            profileFrame.setVisible(true);
        }
    }//GEN-LAST:event_menuProfileMouseClicked
    public void refreshDesktop(){
        this.setVisible(false);
        this.setVisible(true);
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
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }
    public DatabaseManager Database(){
        return this.database;
    }
    public LoginFrame getLoginFrame(){
        return this.login;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu menuChat;
    private javax.swing.JMenu menuLogin;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuProfile;
    private javax.swing.JMenu menuRegister;
    private javax.swing.JMenuBar topMenu;
    // End of variables declaration//GEN-END:variables
}
