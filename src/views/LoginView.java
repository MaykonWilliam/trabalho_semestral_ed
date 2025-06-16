package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JLabel lblFeedback;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // Desativa redimensionamento (remove maximizar)
		setUndecorated(false);
		setBounds(100, 100, 419, 451);

		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUser = new JTextField();
		txtUser.setText((String) null);
		txtUser.setEnabled(true);
		txtUser.setBounds(10, 209, 383, 25);
		contentPane.add(txtUser);

		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsurio.setBounds(10, 189, 383, 20);
		contentPane.add(lblUsurio);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/resources/images/logo-login.png")));
		lblNewLabel.setBounds(26, 0, 293, 183);
		contentPane.add(lblNewLabel);

		JLabel lblUsurio_1 = new JLabel("Senha:");
		lblUsurio_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsurio_1.setBounds(10, 245, 383, 20);
		contentPane.add(lblUsurio_1);

		txtPassword = new JPasswordField();
		txtPassword.setText((String) null);
		txtPassword.setEnabled(true);
		txtPassword.setBounds(10, 265, 383, 25);
		contentPane.add(txtPassword);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEntrar.setIcon(new ImageIcon(LoginView.class.getResource("/resources/icons/login.png")));
		btnEntrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEntrar.setBounds(10, 320, 383, 69);
		contentPane.add(btnEntrar);

		lblFeedback = new JLabel("");
		lblFeedback.setVisible(false);
		lblFeedback.setForeground(new Color(128, 0, 0));
		lblFeedback.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFeedback.setBounds(10, 301, 383, 20);
		contentPane.add(lblFeedback);
		
		JLabel lblAcessoRestrito = new JLabel("Acesso Restrito");
		lblAcessoRestrito.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcessoRestrito.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAcessoRestrito.setBounds(10, 151, 383, 20);
		contentPane.add(lblAcessoRestrito);
	}

	private void login() {

		lblFeedback.setVisible(false);
		String user = txtUser.getText().trim();
		char[] cryptPassword = txtPassword.getPassword();
		String password = new String(cryptPassword);

		if (user.equals("admin") && password.equals("admin")) {
			new MainView().setVisible(true);
			this.dispose();
		} else {
			lblFeedback.setText("Usuário ou senha incorretos");
			lblFeedback.setVisible(true);
		}

	}
}
