package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import views.consultas.ConsultaDisciplinasView;
import views.consultas.ConsultaInscritosView;
import views.curso.CursoListView;
import views.curso.CursoView;
import views.disciplina.DisciplinaListView;
import views.disciplina.DisciplinaView;
import views.inscricao.InscricaoListView;
import views.inscricao.InscricaoView;
import views.professor.ProfessorListView;
import views.professor.ProfessorView;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainView() {

		setTitle("Sistema Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setResizable(true);
		setMinimumSize(new Dimension(800, 600));

		JPanel backgroundPanel = new BackgroundPanel("/resources/images/background.png");
		backgroundPanel.setLayout(new BorderLayout());
		setContentPane(backgroundPanel);

		JMenuBar menuBar = new JMenuBar();

		JMenuItem professorCadastrarMenuItem = new JMenuItem("Cadastrar Novo Professor");
		JMenuItem professorListarMenuItem = new JMenuItem("Listar Professores");

		JMenu professorMenu = new JMenu("Professores");
		professorMenu.add(professorCadastrarMenuItem);
		professorMenu.add(professorListarMenuItem);

		professorCadastrarMenuItem.addActionListener(e -> {
			ProfessorView professorView = new ProfessorView();
			professorView.setVisible(true);
		});

		professorListarMenuItem.addActionListener(e -> {
			ProfessorListView professorListView = new ProfessorListView();
			professorListView.setVisible(true);
		});

		menuBar.add(professorMenu);

		JMenuItem cursoCadastrarMenuItem = new JMenuItem("Cadastrar Novo Curso");
		JMenuItem cursoListarMenuItem = new JMenuItem("Listar Cursos");

		JMenu cursoMenu = new JMenu("Cursos");
		cursoMenu.add(cursoCadastrarMenuItem);
		cursoMenu.add(cursoListarMenuItem);

		cursoCadastrarMenuItem.addActionListener(e -> {
			CursoView cursoView = new CursoView();
			cursoView.setVisible(true);
		});

		cursoListarMenuItem.addActionListener(e -> {
			CursoListView cursoListView = new CursoListView();
			cursoListView.setVisible(true);
		});

		menuBar.add(cursoMenu);

		JMenuItem disciplinaCadastrarMenuItem = new JMenuItem("Cadastrar Nova Disciplina");
		JMenuItem disciplinaListarMenuItem = new JMenuItem("Listar Disciplinas");

		JMenu disciplinaMenu = new JMenu("Disciplinas");
		disciplinaMenu.add(disciplinaCadastrarMenuItem);
		disciplinaMenu.add(disciplinaListarMenuItem);

		disciplinaCadastrarMenuItem.addActionListener(e -> {
			DisciplinaView disciplinaView = new DisciplinaView();
			disciplinaView.setVisible(true);
		});

		disciplinaListarMenuItem.addActionListener(e -> {
			DisciplinaListView disciplinaListView = new DisciplinaListView();
			disciplinaListView.setVisible(true);
		});

		menuBar.add(disciplinaMenu);

		JMenuItem incricaoCadastrarMenuItem = new JMenuItem("Cadastrar Nova Inscrição");
		JMenuItem incricaoListarMenuItem = new JMenuItem("Listar Inscrição");

		JMenu inscricaoMenu = new JMenu("Inscrições");
		inscricaoMenu.add(incricaoCadastrarMenuItem);
		inscricaoMenu.add(incricaoListarMenuItem);

		incricaoCadastrarMenuItem.addActionListener(e -> {
			InscricaoView inscricaoView = new InscricaoView();
			inscricaoView.setVisible(true);
		});

		incricaoListarMenuItem.addActionListener(e -> {
			InscricaoListView inscricaoListView = new InscricaoListView();
			inscricaoListView.setVisible(true);
		});

		menuBar.add(inscricaoMenu);

		JMenuItem consultarInscritosMenuItem = new JMenuItem("Consultar Inscritos");
		JMenuItem consultarDisciplinasProcessosMenuItem = new JMenuItem("Consultar Inscrições por Disciplina");

		JMenu consultaMenu = new JMenu("Consultas");
		consultaMenu.add(consultarInscritosMenuItem);
		consultaMenu.add(consultarDisciplinasProcessosMenuItem);

		consultarInscritosMenuItem.addActionListener(e -> {
			ConsultaInscritosView consultaInscritosView = new ConsultaInscritosView();
			consultaInscritosView.setVisible(true);
		});

		consultarDisciplinasProcessosMenuItem.addActionListener(e -> {
			ConsultaDisciplinasView consultaDisciplinasView = new ConsultaDisciplinasView();
			consultaDisciplinasView.setVisible(true);
		});

		menuBar.add(consultaMenu);

		setJMenuBar(menuBar);
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
