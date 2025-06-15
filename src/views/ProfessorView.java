package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import adapters.database.csv.ProfessorCSVRepositoryAdapter;
import utils.List;
import domain.entities.Professor;

public class ProfessorView extends JFrame {

	private static final long serialVersionUID = 2L;

	private JTextField txtCPF;
	private JTextField txtNomeProfessor;
	private JTextField txtAreaConhecimento;
	private JTextField txtPontuacao;

	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnSearch;
	private JButton btnCancel;

	private ProfessorCSVRepositoryAdapter repository;
	private Professor professor;

	public ProfessorView() {

		repository = new ProfessorCSVRepositoryAdapter("professor.csv");

		setTitle("Cadastro de Professor");
		setSize(576, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setBounds(10, 20, 100, 20);
		getContentPane().add(lblCPF);

		txtCPF = new JTextField();
		txtCPF.setBounds(10, 40, 109, 25);
		txtCPF.addActionListener(e -> {
			try {
				findByCPF();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		getContentPane().add(txtCPF);

		btnSearch = new JButton();
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					findByCPF();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		btnSearch.setIcon(new ImageIcon(getClass().getResource("/resources/icons/search.png")));
		btnSearch.setBounds(120, 39, 27, 27);
		getContentPane().add(btnSearch);

		JLabel lblNomeProfessor = new JLabel("Nome do Professor");
		lblNomeProfessor.setBounds(10, 75, 200, 20);
		getContentPane().add(lblNomeProfessor);

		txtNomeProfessor = new JTextField();
		txtNomeProfessor.setBounds(10, 95, 540, 25);
		getContentPane().add(txtNomeProfessor);

		JLabel lblAreaConhecimento = new JLabel("Área de Conhecimento");
		lblAreaConhecimento.setBounds(10, 131, 200, 20);
		getContentPane().add(lblAreaConhecimento);

		txtAreaConhecimento = new JTextField();
		txtAreaConhecimento.setBounds(10, 151, 540, 25);
		getContentPane().add(txtAreaConhecimento);

		JLabel lblPontuacao = new JLabel("Pontuação");
		lblPontuacao.setBounds(10, 191, 100, 20);
		getContentPane().add(lblPontuacao);

		txtPontuacao = new JTextField();
		txtPontuacao.setBounds(10, 211, 540, 25);
		getContentPane().add(txtPontuacao);

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
		txtCPF.setEnabled(true);
		txtNomeProfessor.setEnabled(false);
		txtAreaConhecimento.setEnabled(false);
		txtPontuacao.setEnabled(false);
	}

	private void enableForm() {
		txtCPF.setEnabled(true);
		txtNomeProfessor.setEnabled(true);
		txtAreaConhecimento.setEnabled(true);
		txtPontuacao.setEnabled(true);
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
		professor = null;
		txtCPF.setText("");
		txtNomeProfessor.setText("");
		txtAreaConhecimento.setText("");
		txtPontuacao.setText("");
	}

	private void findByCPF() throws Exception {
		String cpf = txtCPF.getText().trim();

		Professor record = repository.show(cpf);

		if (record == null) {
			JOptionPane.showMessageDialog(null, "Registro não encontrado.", getTitle(), JOptionPane.WARNING_MESSAGE);
			clearForm();
			return;
		}
		professor = record;
		loadData();
	}

	private void loadData() {
		txtCPF.setText(professor.getCpf());
		txtNomeProfessor.setText(professor.getNomeProfessor());
		txtAreaConhecimento.setText(professor.getAreaConhecimento());
		txtPontuacao.setText(Integer.toString(professor.getPontuacao()));
	}

	private boolean validateForm() throws Exception {

		boolean isValid = true;
		List<String> listaErros = new List<String>();

		String cpf = txtCPF.getText().trim();
		String nome = txtNomeProfessor.getText().trim();
		String area = txtAreaConhecimento.getText().trim();
		String pontuacao = txtPontuacao.getText().trim();

		if (cpf == null || cpf.isEmpty()) {
			listaErros.addLast("CPF inválido.");
			isValid = false;
		}

		if (nome == null || nome.isEmpty()) {
			listaErros.addLast("Nome do professor inválido.");
			isValid = false;
		}

		if (area == null || area.isEmpty()) {
			listaErros.addLast("Área de conhecimento inválida.");
			isValid = false;
		}

		if (pontuacao == null || pontuacao.isEmpty()) {
			listaErros.addLast("Pontuação inválida.");
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
		txtNomeProfessor.requestFocusInWindow();
	}

	private void editAction() {
		if (professor == null) {
			JOptionPane.showMessageDialog(null, "Selecione um registro.", getTitle(), JOptionPane.WARNING_MESSAGE);
			return;
		}

		loadData();
		enableForm();
		enableButtons();
		txtCPF.setEnabled(false);
	}

	private void saveAction() throws Exception {
		if (validateForm()) {

			if (professor == null) {
				professor = new Professor(txtCPF.getText(), txtNomeProfessor.getText(), txtAreaConhecimento.getText(), Integer.parseInt(txtPontuacao.getText().trim()));
				repository.save(professor);
			} else {
				professor.setNomeProfessor(txtNomeProfessor.getText());
				professor.setAreaConhecimento(txtAreaConhecimento.getText());
				repository.update(professor);
			}
			txtCPF.setText(professor.getCpf());
			txtCPF.requestFocusInWindow();

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

		if (professor == null) {
			JOptionPane.showMessageDialog(null, "Selecione um registro.", getTitle(), JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			repository.delete(professor);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Registro Excluido com sucesso.", getTitle(), JOptionPane.INFORMATION_MESSAGE);

		clearForm();
		disableButtons();
		disableForm();

		txtCPF.requestFocusInWindow();

		return;
	}

	private void cancelAction() {
		clearForm();
		disableForm();
		disableButtons();
		txtCPF.requestFocusInWindow();
	}

}
