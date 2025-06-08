package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InscricaoView extends JFrame {
	private static final long serialVersionUID = -3869218712081955518L;
	private JPanel contentPane;
	private JTextField tfInscricaoCPF;
	private JTextField tfInscricaoCodigoDisciplina;
	private JTextField tfInscricaoStatus;

//	Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscricaoView frame = new InscricaoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	Create the frame.
	public InscricaoView() {
		setTitle("INSCRIÇÃO:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInscricaoCPFprofessor = new JLabel("CPF:");
		lblInscricaoCPFprofessor.setBounds(10, 10, 37, 13);
		lblInscricaoCPFprofessor.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblInscricaoCPFprofessor);
		
		tfInscricaoCodigoDisciplina = new JTextField();
		tfInscricaoCodigoDisciplina.setText("");
		tfInscricaoCodigoDisciplina.setBounds(156, 38, 270, 19);
		contentPane.add(tfInscricaoCodigoDisciplina);
		tfInscricaoCodigoDisciplina.setColumns(10);
		
		JLabel lblInscricaoCodigoDisciplina = new JLabel("Código de Disicplina:");
		lblInscricaoCodigoDisciplina.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInscricaoCodigoDisciplina.setBounds(10, 33, 150, 25);
		contentPane.add(lblInscricaoCodigoDisciplina);
		
		JLabel lblInscricaoStatus = new JLabel("Status:");
		lblInscricaoStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInscricaoStatus.setBounds(10, 68, 51, 13);
		contentPane.add(lblInscricaoStatus);
		
		tfInscricaoCPF = new JTextField();
		tfInscricaoCPF.setBounds(51, 9, 375, 19);
		contentPane.add(tfInscricaoCPF);
		tfInscricaoCPF.setColumns(10);
		
		tfInscricaoStatus = new JTextField();
		tfInscricaoStatus.setBounds(64, 67, 362, 19);
		contentPane.add(tfInscricaoStatus);
		tfInscricaoStatus.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(24, 180, 85, 21);
		contentPane.add(btnNewButton);
	}
}
