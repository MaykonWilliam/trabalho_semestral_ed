package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import adapters.database.csv.CursoCSVRepositoryAdapter;
import adapters.database.csv.DisciplinaCSVRepositoryAdapter;
import domain.entities.Curso;
import domain.entities.Disciplina;
import utils.List;

public class DisciplinaView extends JFrame {

	private static final long serialVersionUID = 3L;

	private JTextField txtCodDisciplina;
	private JTextField txtNomeDisciplina;
	private JComboBox<String> comboDia;
	private JTextField txtHoraInicial;
	private JTextField txtHoraDiaria;
	private JComboBox<String> comboCursos;

	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnSearch;
	private JButton btnCancel;

	private DisciplinaCSVRepositoryAdapter repository;
	private CursoCSVRepositoryAdapter repositoryCursos;
	private Disciplina disciplina;
	private List<Curso> cursos;

	public DisciplinaView() {

		repository = new DisciplinaCSVRepositoryAdapter("disciplinas.csv");
		repositoryCursos = new CursoCSVRepositoryAdapter("cursos.csv");
		
		try {
			cursos = repositoryCursos.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Cadastro de Disciplina");
		setSize(576, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblCodDisciplina = new JLabel("Código da Disciplina");
		lblCodDisciplina.setBounds(10, 20, 200, 20);
		getContentPane().add(lblCodDisciplina);

		txtCodDisciplina = new JTextField();
		txtCodDisciplina.setBounds(10, 40, 109, 25);
		txtCodDisciplina.addActionListener(e -> {
			try {
				findByCodigo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		getContentPane().add(txtCodDisciplina);

		btnSearch = new JButton();
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					findByCodigo();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		btnSearch.setIcon(new ImageIcon(getClass().getResource("/resources/icons/search.png")));
		btnSearch.setBounds(120, 39, 27, 27);
		getContentPane().add(btnSearch);

		JLabel lblNomeDisciplina = new JLabel("Nome da Disciplina");
		lblNomeDisciplina.setBounds(10, 75, 200, 20);
		getContentPane().add(lblNomeDisciplina);

		txtNomeDisciplina = new JTextField();
		txtNomeDisciplina.setBounds(10, 95, 540, 25);
		getContentPane().add(txtNomeDisciplina);

		JLabel lbldiaSemana = new JLabel("Dia da semana");
		lbldiaSemana.setBounds(10, 130, 200, 20);
		getContentPane().add(lbldiaSemana);
		
		String[] dias = {
	            "Domingo", "Segunda-feira", "Terça-feira", 
	            "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"
	        };

		comboDia = new JComboBox<String>(dias);
		comboDia.setBounds(10, 150, 540, 25);
		getContentPane().add(comboDia);

		JLabel lblHoraInicial = new JLabel("Hora Inicial");
		lblHoraInicial.setBounds(10, 190, 100, 20);
		getContentPane().add(lblHoraInicial);

		txtHoraInicial = new JTextField();
		txtHoraInicial.setBounds(10, 211, 540, 25);
		getContentPane().add(txtHoraInicial);
		
		JLabel lblHoradiaria = new JLabel("Horas Diárias");
		lblHoradiaria.setBounds(10, 245, 100, 20);
		getContentPane().add(lblHoradiaria);

		txtHoraDiaria = new JTextField();
		txtHoraDiaria.setBounds(10, 265, 540, 25);
		getContentPane().add(txtHoraDiaria);
		
		JLabel lblCurso = new JLabel("Curso da Disciplina");
		lblCurso.setBounds(10, 295, 200, 20);
		getContentPane().add(lblCurso);
		
		String[] nomeCurso = new String[cursos.size()];
		
		for(int i = 0; i < cursos.size(); i++) {
			try {
				nomeCurso[i] = cursos.get(i).getNome();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		comboCursos = new JComboBox<String>(nomeCurso);
		comboCursos.setBounds(10, 314, 540, 25);
		getContentPane().add(comboCursos);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 348, 540, 2);
		getContentPane().add(separator);

		btnNew = new JButton("Novo Registro");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAction();
			}
		});

		btnNew.setIcon(new ImageIcon(getClass().getResource("/resources/icons/new.png")));
		btnNew.setBounds(10, 361, 100, 69);
		btnNew.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNew.setHorizontalTextPosition(SwingConstants.CENTER);
		getContentPane().add(btnNew);

		btnEdit = new JButton("Editar");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editAction();
			}
		});

		btnEdit.setIcon(new ImageIcon(getClass().getResource("/resources/icons/edit.png")));
		btnEdit.setBounds(120, 361, 100, 69);
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		getContentPane().add(btnEdit);

		btnSave = new JButton("Salvar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveAction();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setIcon(new ImageIcon(getClass().getResource("/resources/icons/save.png")));
		btnSave.setBounds(230, 361, 100, 69);
		btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		getContentPane().add(btnSave);

		btnDelete = new JButton("Excluir");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAction();
			}
		});
		btnDelete.setIcon(new ImageIcon(getClass().getResource("/resources/icons/delete.png")));
		btnDelete.setBounds(340, 361, 100, 69);
		btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		getContentPane().add(btnDelete);

		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelAction();
			}
		});
		btnCancel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/undo.png")));
		btnCancel.setBounds(450, 361, 100, 69);
		btnCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		getContentPane().add(btnCancel);

		// Inicialmente desativa os campos e botões
		disableForm();
		disableButtons();
	}

	private void disableForm() {
		txtCodDisciplina.setEnabled(true);
		txtNomeDisciplina.setEnabled(false);
		comboDia.setEnabled(false);
		txtHoraInicial.setEnabled(false);
		txtHoraDiaria.setEnabled(false);
		comboCursos.setEnabled(false);
	}

	private void enableForm() {
		txtCodDisciplina.setEnabled(true);
		txtNomeDisciplina.setEnabled(true);
		comboDia.setEnabled(true);
		txtHoraInicial.setEnabled(true);
		txtHoraDiaria.setEnabled(true);
		comboCursos.setEnabled(true);
	}

	private void disableButtons() {
		btnNew.setEnabled(true);
		btnEdit.setEnabled(true);
		btnSave.setEnabled(false);
		btnDelete.setEnabled(false);
		btnCancel.setEnabled(false);
	}

	private void enableButtons() {
		btnNew.setEnabled(false);
		btnEdit.setEnabled(false);
		btnSave.setEnabled(true);
		btnDelete.setEnabled(true);
		btnCancel.setEnabled(true);
	}

	private void clearForm() {
		disciplina = null;
		txtCodDisciplina.setText("");
		txtNomeDisciplina.setText("");
		txtHoraInicial.setText("");
		txtHoraDiaria.setText("");
	}

	private void findByCodigo() throws Exception {
		String codigo = txtCodDisciplina.getText().trim();

		Disciplina record = repository.show(codigo);

		if (record == null) {
			JOptionPane.showMessageDialog(null, "Registro não encontrado.", getTitle(), JOptionPane.WARNING_MESSAGE);
			clearForm();
			return;
		}
		disciplina = record;
		loadData();
	}

	private void loadData() {
		txtCodDisciplina.setText(disciplina.getCodigo());
		txtNomeDisciplina.setText(disciplina.getNome());
		txtHoraInicial.setText(disciplina.getHorarioInicial());
		comboDia.setSelectedItem(disciplina.getDiaSemana());
		txtHoraDiaria.setText(disciplina.getHorasDiarias());
		
		Curso curso = null;
		try {
			curso = repositoryCursos.show(disciplina.getCodigoCurso());
		} catch (Exception e) {
			e.printStackTrace();
		}
		comboCursos.setSelectedItem(curso.getNome());
	}

	private boolean validateForm() throws Exception {

		boolean isValid = true;
		List<String> listaErros = new List<String>();

		String codDisciplina = txtCodDisciplina.getText().trim();
		String nomeDisciplina = txtNomeDisciplina.getText().trim();
		String horaInicial = txtHoraInicial.getText().trim();
		String horaDiaria = txtHoraDiaria.getText().trim();

		if (codDisciplina == null || codDisciplina.isEmpty()) {
			if(listaErros.isEmpty()) {
				listaErros.addFirst("Código da Disciplina inválido.");
			} else {
				listaErros.addLast("Código da Disciplina inválido.");
			}
			isValid = false;
		}

		if (nomeDisciplina == null || nomeDisciplina.isEmpty()) {
			if(listaErros.isEmpty()) {
				listaErros.addFirst("Nome da Disciplina inválido.");
			} else {
				listaErros.addLast("Nome da Disciplina inválido.");
			}
			isValid = false;
		}

		if (horaInicial == null || horaInicial.isEmpty()) {
			if(listaErros.isEmpty()) {
				listaErros.addFirst("Hora inicial inválida.");
			} else {
				listaErros.addLast("Hora inicial inválida.");
			}
			isValid = false;
		}

		if (horaDiaria == null || horaDiaria.isEmpty()) {
			if(listaErros.isEmpty()) {
				listaErros.addFirst("Horas Diárias inválidas.");
			} else {
				listaErros.addLast("Horas Diárias inválidas.");
			}
			isValid = false;
		}

		StringBuffer messages = new StringBuffer();

		for (int i = 0; i < listaErros.size(); i++) {
			messages.append(listaErros.get(i));
			messages.append("\n");
		}

		if (isValid == false) {

			String errorMessage = "Corrija os erros abaixo para continuar:\n\n" + messages.toString();
			JOptionPane.showMessageDialog(null, errorMessage, getTitle(), JOptionPane.WARNING_MESSAGE);
		}

		return isValid;
	}

	private void newAction() {
		clearForm();
		enableForm();
		enableButtons();
		txtNomeDisciplina.requestFocusInWindow();
	}

	private void editAction() {
		if (disciplina == null) {
			JOptionPane.showMessageDialog(null, "Selecione um registro.", getTitle(), JOptionPane.WARNING_MESSAGE);
			return;
		}

		loadData();
		enableForm();
		enableButtons();
		txtCodDisciplina.setEnabled(false);
	}

	private void saveAction() throws Exception {
		if (validateForm()) {
			
			int codCurso = 0;
			
			for(int i = 0; i < cursos.size(); i++) {
				if(cursos.get(i).getNome().equalsIgnoreCase((String) comboCursos.getSelectedItem())) {
					codCurso = cursos.get(i).getCodigo();
					break;
				}
			}

			if (disciplina == null) {
				disciplina = new Disciplina(txtCodDisciplina.getText(), txtNomeDisciplina.getText(), (String) comboDia.getSelectedItem(), txtHoraInicial.getText(), txtHoraDiaria.getText(), codCurso);
				repository.save(disciplina);
			} else {
				disciplina.setNome(txtNomeDisciplina.getText());
				disciplina.setDiaSemana((String) comboDia.getSelectedItem());
				disciplina.setHorarioInicial(txtHoraInicial.getText());
				disciplina.setHorasDiarias(txtHoraDiaria.getText());
				disciplina.setCodigoCurso(comboCursos.getSelectedItem());
				repository.update(disciplina);
			}
			txtCodDisciplina.setText(disciplina.getCodigo());
			txtCodDisciplina.requestFocusInWindow();

			JOptionPane.showMessageDialog(null, "Registro salvo com sucesso.", getTitle(), JOptionPane.INFORMATION_MESSAGE);

			disableButtons();
			disableForm();

			return;
		}
		return;
	}

	private void deleteAction() {

		int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este registro?", getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirm == JOptionPane.NO_OPTION) {
			return;
		}

		if (disciplina == null) {
			JOptionPane.showMessageDialog(null, "Selecione um registro.", getTitle(), JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			repository.delete(disciplina);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Registro Excluido com sucesso.", getTitle(), JOptionPane.INFORMATION_MESSAGE);

		clearForm();
		disableButtons();
		disableForm();

		txtCodDisciplina.requestFocusInWindow();

		return;
	}

	private void cancelAction() {
		clearForm();
		disableForm();
		disableButtons();
		txtCodDisciplina.requestFocusInWindow();
	}
}
