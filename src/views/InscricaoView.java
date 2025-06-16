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

import adapters.database.csv.DisciplinaCSVRepositoryAdapter;
import adapters.database.csv.InscricaoCSVRepositoryAdapter;
import adapters.database.csv.ProfessorCSVRepositoryAdapter;
import domain.entities.Disciplina;
import domain.entities.Inscricao;
import domain.entities.Professor;
import utils.List;

public class InscricaoView extends JFrame {
	private static final long serialVersionUID = 4L;

	private JTextField txtCodInscricao;
	private JComboBox<String> comboProfessor;
	private JComboBox<String> comboDisciplina;
	private JComboBox<String> comboStatus;

	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnSearch;
	private JButton btnCancel;

	private InscricaoCSVRepositoryAdapter repository;
	private ProfessorCSVRepositoryAdapter professorRepository;
	private DisciplinaCSVRepositoryAdapter disciplinaRepository;
	private Inscricao inscricao;
	private List<Disciplina> disciplinas;
	private List<Professor> professores;

	public InscricaoView() {

		repository = new InscricaoCSVRepositoryAdapter("inscricoes.csv");
		disciplinaRepository = new DisciplinaCSVRepositoryAdapter("disciplinas.csv");
		professorRepository = new ProfessorCSVRepositoryAdapter("professor.csv");
		
		try {
			disciplinas = disciplinaRepository.list();
			professores = professorRepository.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Cadastro de Inscrição");
		setSize(576, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblCodInscricao = new JLabel("Código da Inscrição");
		lblCodInscricao.setBounds(10, 20, 200, 20);
		getContentPane().add(lblCodInscricao);

		txtCodInscricao = new JTextField();
		txtCodInscricao.setBounds(10, 40, 109, 25);
		txtCodInscricao.addActionListener(e -> {
			try {
				findByCodigo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		getContentPane().add(txtCodInscricao);

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

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 80, 200, 20);
		getContentPane().add(lblDisciplina);
		
		String[] nomeDisciplina = new String[disciplinas.size()];
		
		for(int i = 0; i < disciplinas.size(); i++) {
			try {
				nomeDisciplina[i] = disciplinas.get(i).getNome();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		comboDisciplina = new JComboBox<String>(nomeDisciplina);
		comboDisciplina.setBounds(10, 100, 540, 25);
		getContentPane().add(comboDisciplina);
		
		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setBounds(10, 140, 200, 20);
		getContentPane().add(lblProfessor);
		
		String[] nomeProfessor = new String[professores.size()];
		
		for(int i = 0; i < professores.size(); i++) {
			try {
				nomeProfessor[i] = professores.get(i).getNomeProfessor();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		comboProfessor = new JComboBox<String>(nomeProfessor);
		comboProfessor.setBounds(10, 160, 540, 25);
		getContentPane().add(comboProfessor);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 200, 200, 20);
		getContentPane().add(lblStatus);
		
		String[] status = {
	            "Ativo", "Inativo"
	        };

		comboStatus = new JComboBox<String>(status);
		comboStatus.setBounds(10, 220, 540, 25);
		getContentPane().add(comboStatus);


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
		txtCodInscricao.setEnabled(true);
		comboProfessor.setEnabled(false);
		comboStatus.setEnabled(false);
		comboDisciplina.setEnabled(false);
	}

	private void enableForm() {
		txtCodInscricao.setEnabled(false);
		comboProfessor.setEnabled(false);
		comboStatus.setEnabled(true);
		comboDisciplina.setEnabled(false);
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
		inscricao = null;
		txtCodInscricao.setText("");
	}

	private void findByCodigo() throws Exception {
		String codigo = txtCodInscricao.getText().trim();

		Inscricao record = repository.show(codigo);

		if (record == null) {
			JOptionPane.showMessageDialog(null, "Registro não encontrado.", getTitle(), JOptionPane.WARNING_MESSAGE);
			clearForm();
			return;
		}
		inscricao = record;
		loadData();
	}

	private void loadData() {
		txtCodInscricao.setText(inscricao.getCodigo());
		comboStatus.setSelectedItem(inscricao.getStatus());
		
		Disciplina disciplina = null;
		Professor professor = null;
		
		try {
			disciplina = disciplinaRepository.show(inscricao.getCodigoDisciplina());
			professor = professorRepository.show(inscricao.getCpfProfessor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		comboDisciplina.setSelectedItem(disciplina.getNome());
		comboProfessor.setSelectedItem(professor.getNomeProfessor());
	}

	private boolean validateForm() throws Exception {

		boolean isValid = true;
		List<String> listaErros = new List<String>();

		String codInscricao = txtCodInscricao.getText().trim();

		if (codInscricao == null || codInscricao.isEmpty()) {
			if(listaErros.isEmpty()) {
				listaErros.addFirst("Código da Inscrição inválido.");
			} else {
				listaErros.addLast("Código da Inscrição inválido.");
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
		txtCodInscricao.setEnabled(true);
		comboProfessor.setEnabled(true);
		comboStatus.setEnabled(true);
		comboDisciplina.setEnabled(true);
		enableButtons();
		txtCodInscricao.requestFocusInWindow();
	}

	private void editAction() {
		if (inscricao == null) {
			JOptionPane.showMessageDialog(null, "Selecione um registro.", getTitle(), JOptionPane.WARNING_MESSAGE);
			return;
		}

		loadData();
		enableForm();
		enableButtons();
		txtCodInscricao.setEnabled(false);
	}

	private void saveAction() throws Exception {
		if (validateForm()) {
			
			String codDisciplina = null;
			String cpfProfessor = null;
			
			for(int i = 0; i < disciplinas.size(); i++) {
				if(disciplinas.get(i).getNome().equalsIgnoreCase((String) comboDisciplina.getSelectedItem())) {
					codDisciplina = disciplinas.get(i).getCodigo();
					break;
				}
			}
			
			for(int i = 0; i < professores.size(); i++) {
				if(professores.get(i).getNomeProfessor().equalsIgnoreCase((String) comboProfessor.getSelectedItem())) {
					cpfProfessor = professores.get(i).getCpf();
					break;
				}
			}

			if (inscricao == null) {
				inscricao = new Inscricao(txtCodInscricao.getText(), codDisciplina, cpfProfessor, (String) comboStatus.getSelectedItem());
				repository.save(inscricao);
			} else {
				inscricao.setStatus((String) comboStatus.getSelectedItem());
				repository.update(inscricao);
			}
			txtCodInscricao.setText(inscricao.getCodigo());
			txtCodInscricao.requestFocusInWindow();

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

		if (inscricao == null) {
			JOptionPane.showMessageDialog(null, "Selecione um registro.", getTitle(), JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			repository.delete(inscricao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Registro Excluido com sucesso.", getTitle(), JOptionPane.INFORMATION_MESSAGE);

		clearForm();
		disableButtons();
		disableForm();

		txtCodInscricao.requestFocusInWindow();

		return;
	}

	private void cancelAction() {
		clearForm();
		disableForm();
		disableButtons();
		txtCodInscricao.requestFocusInWindow();
	}
}
