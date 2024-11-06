package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGUI {
    private JTextField txtUsuario;
    private JPasswordField pswContrasena;
    private JButton btnIngresar;
    private JButton btnRegistrarse;
    private JPanel LoginGUI;

    public LoginGUI(JFrame frame) {
        // Acción al hacer clic en el botón "Ingresar"
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contrasena = new String(pswContrasena.getPassword());

                // Verificar las credenciales en la base de datos
                try (Connection connection = DatabaseConnection.getConnection()) {
                    if (connection != null) {
                        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, usuario);
                        statement.setString(2, contrasena);

                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            // Si el usuario existe, mostrar un mensaje en un JPanel
                            JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuario + "!");
                            frame.setContentPane(new PrincipalGUI(frame).getPanel());
                            frame.revalidate();
                        } else {
                            // Si las credenciales no son correctas, mostrar un mensaje de error
                            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción al hacer clic en el botón "Registrarse"
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new RegisterGUI(frame).getPanel());
                frame.revalidate();
            }
        });
    }

    // Metodo para obtener el JPanel principal
    public JPanel getPanel() {
        return LoginGUI;
    }
}
