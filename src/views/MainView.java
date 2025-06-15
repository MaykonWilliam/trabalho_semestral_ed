package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainView() {

		setTitle("Sistema Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		setMinimumSize(new Dimension(800, 600));

		// Carrega a imagem como plano de fundo
		JPanel backgroundPanel = new BackgroundPanel("/resources/images/background.png");
		backgroundPanel.setLayout(new BorderLayout());
		setContentPane(backgroundPanel);

		// Menu
		JMenuBar menuBar = new JMenuBar();
		
		// Início Professor
		JMenuItem professorCadastrarMenuItem = new JMenuItem("Cadastrar Novo Professor");
		JMenuItem professorListarMenuItem = new JMenuItem("Listar Professores");
		
		JMenu professorMenu = new JMenu("Professores");
		professorMenu.add(professorCadastrarMenuItem);
		professorMenu.add(professorListarMenuItem);
		
		professorCadastrarMenuItem.addActionListener(e -> {
			ProfessorView professorView = new ProfessorView();
			professorView.setVisible(true);
		});
		
		menuBar.add(professorMenu);
		
		// Fim Professor

		// Cursos
		JMenuItem cursoCadastrarMenuItem = new JMenuItem("Cadastrar Novo Curso");
		JMenuItem cursoListarMenuItem = new JMenuItem("Listar Cursos");

		JMenu cursoMenu = new JMenu("Cursos");
		cursoMenu.add(cursoCadastrarMenuItem);
		cursoMenu.add(cursoListarMenuItem);

		cursoCadastrarMenuItem.addActionListener(e -> {
			CursoView cursoView = new CursoView();
			cursoView.setVisible(true);
		});

		menuBar.add(cursoMenu);
		setJMenuBar(menuBar);
		
		// Início Disciplina
					JMenuItem disciplinaCadastrarMenuItem = new JMenuItem("Cadastrar Nova Disciplina");
					JMenuItem disciplinaListarMenuItem = new JMenuItem("Listar Disciplinas");
					
					JMenu disciplinaMenu = new JMenu("Disciplinas");
					disciplinaMenu.add(disciplinaCadastrarMenuItem);
					disciplinaMenu.add(disciplinaListarMenuItem);
					
					disciplinaCadastrarMenuItem.addActionListener(e -> {
						ProfessorView professorView = new ProfessorView();
						professorView.setVisible(true);
					});
					
					menuBar.add(disciplinaMenu);
					
					// Fim Disciplina
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainView frame = new MainView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
