package views;

import javax.swing.*;

import adapters.database.csv.CursoCSVRepositoryAdapter;
import domain.entities.Curso;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CursoView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtArea;

	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnSearch;
	private JButton btnCancel;

	private CursoCSVRepositoryAdapter repository;
	private Curso curso;

	public CursoView() {

		repository = new CursoCSVRepositoryAdapter("cursos.csv");

		setTitle("Cadastro de Curso");
		setSize(576, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 20, 100, 20);
		getContentPane().add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(10, 40, 109, 25);
		txtCodigo.addActionListener(e -> findByCodigo());
		getContentPane().add(txtCodigo);

		btnSearch = new JButton();
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findByCodigo();

			}
		});

		btnSearch.setIcon(new ImageIcon(getClass().getResource("/resources/icons/search.png")));
		btnSearch.setBounds(120, 39, 27, 27);
		getContentPane().add(btnSearch);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 75, 100, 20);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(10, 95, 540, 25);
		getContentPane().add(txtNome);

		JLabel lblArea = new JLabel("Área");
		lblArea.setBounds(10, 131, 100, 20);
		getContentPane().add(lblArea);

		txtArea = new JTextField();
		txtArea.setBounds(10, 151, 540, 25);
		getContentPane().add(txtArea);

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
				saveAction();
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
		txtCodigo.setEnabled(true);
		txtNome.setEnabled(false);
		txtArea.setEnabled(false);
	}

	private void enableForm() {
		txtCodigo.setEnabled(false);
		txtNome.setEnabled(true);
		txtArea.setEnabled(true);
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
		curso = null;
		txtCodigo.setText("");
		txtNome.setText("");
		txtArea.setText("");
	}

	private void findByCodigo() {
		try {
			int codigo = Integer.parseInt(txtCodigo.getText().trim());

			Curso record = repository.show(codigo);

			if (record == null) {
				JOptionPane.showMessageDialog(null, "Registro não encontrado.", getTitle(), JOptionPane.WARNING_MESSAGE);
				clearForm();
				return;
			}
			curso = record;
			loadData();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Digite um código válido.", getTitle(), JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void loadData() {
		txtCodigo.setText(Integer.toString(curso.getCodigo()));
		txtNome.setText(curso.getNome());
		txtArea.setText(curso.getArea());
	}

	private boolean validateForm() {

		boolean isValid = true;
		List<String> messages = new ArrayList<String>();

		String nome = txtNome.getText().trim();
		String area = txtArea.getText().trim();

		if (nome.isEmpty()) {
			messages.add("Nome inválido.");
			isValid = false;
		}

		if (area.isEmpty()) {
			messages.add("Nome inválido.");
			isValid = false;
		}

		if (isValid == false) {

			String errorMessage = "Corrija os erros abaixo para continuar:\n\n" + String.join("\n", messages);
			JOptionPane.showMessageDialog(null, errorMessage, getTitle(), JOptionPane.WARNING_MESSAGE);
		}

		return isValid;
	}

	private void newAction() {
		clearForm();
		enableForm();
		enableButtons();
		txtNome.requestFocusInWindow();
	}

	private void editAction() {
		if (curso == null) {
			JOptionPane.showMessageDialog(null, "Selecione um registro.", getTitle(), JOptionPane.WARNING_MESSAGE);
			return;
		}

		loadData();
		enableForm();
		enableButtons();
	}

	private void saveAction() {
		if (validateForm()) {

			if (curso == null) {
				int codigo = repository.getSequencePrimaryKey();
				curso = new Curso(codigo, txtNome.getText(), txtArea.getText());
				repository.save(curso);
			} else {
				curso.setNome(txtNome.getText());
				curso.setArea(txtArea.getText());
				repository.update(curso);
			}
			txtCodigo.setText(Integer.toString(curso.getCodigo()));
			txtCodigo.requestFocusInWindow();

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

		if (curso == null) {
			JOptionPane.showMessageDialog(null, "Selecione um registro.", getTitle(), JOptionPane.WARNING_MESSAGE);
			return;
		}

		repository.delete(curso);

		JOptionPane.showMessageDialog(null, "Registro Excluido com sucesso.", getTitle(), JOptionPane.INFORMATION_MESSAGE);

		clearForm();
		disableButtons();
		disableForm();

		txtCodigo.requestFocusInWindow();

		return;
	}

	private void cancelAction() {
		clearForm();
		disableForm();
		disableButtons();
		txtCodigo.requestFocusInWindow();
	}
}
