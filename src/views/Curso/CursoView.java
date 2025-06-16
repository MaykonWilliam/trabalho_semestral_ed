package views.Curso;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.CursoController;
import controllers.ValidationResponse;
import domain.entities.Curso;

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

	private Curso curso;
	private final CursoController controller = new CursoController();

	private static final String TITLE = "Cadastro de Curso";
	private static final String MSG_CODIGO_INVALIDO = "Digite um código válido.";
	private static final String MSG_REGISTRO_NAO_ENCONTRADO = "Registro não encontrado.";
	private static final String MSG_ERRO_CONSULTA = "Erro ao consultar o registro: ";
	private static final String MSG_ERRO_VALIDACAO = "Erro ao validar o formulário: ";
	private static final String MSG_SELECIONE_REGISTRO = "Selecione um registro.";
	private static final String MSG_SUCESSO_SALVAR = "Registro salvo com sucesso.";
	private static final String MSG_CONFIRMA_EXCLUSAO = "Tem certeza que deseja excluir este registro?";
	private static final String MSG_SUCESSO_EXCLUIR = "Registro excluído com sucesso.";

	public CursoView(Curso curso) {
		initializeComponent();
		this.curso = curso;
		if (curso != null) {
			loadData();
		}
	}

	public CursoView() {
		initializeComponent();
	}

	private void initializeComponent() {
		setupFrame();
		createComponents();
		setupEventListeners();
		initializeFormState();
	}

	private void setupFrame() {
		setTitle(TITLE);
		setSize(576, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
	}

	private void createComponents() {
		createLabels();
		createTextFields();
		createButtons();
		createSeparator();
	}

	private void createLabels() {
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 20, 100, 20);
		getContentPane().add(lblCodigo);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 75, 100, 20);
		getContentPane().add(lblNome);

		JLabel lblArea = new JLabel("Área");
		lblArea.setBounds(10, 131, 100, 20);
		getContentPane().add(lblArea);
	}

	private void createTextFields() {
		txtCodigo = new JTextField();
		txtCodigo.setBounds(10, 40, 109, 25);
		getContentPane().add(txtCodigo);

		txtNome = new JTextField();
		txtNome.setBounds(10, 95, 540, 25);
		getContentPane().add(txtNome);

		txtArea = new JTextField();
		txtArea.setBounds(10, 151, 540, 25);
		getContentPane().add(txtArea);
	}

	private void createButtons() {
		btnSearch = createIconButton("/resources/icons/search.png", 120, 39, 27, 27);

		btnNew = createButton("Novo Registro", "/resources/icons/new.png", 10, 361, 100, 69);
		btnEdit = createButton("Editar", "/resources/icons/edit.png", 120, 361, 100, 69);
		btnSave = createButton("Salvar", "/resources/icons/save.png", 230, 361, 100, 69);
		btnDelete = createButton("Excluir", "/resources/icons/delete.png", 340, 361, 100, 69);
		btnCancel = createButton("Cancelar", "/resources/icons/undo.png", 450, 361, 100, 69);
	}

	private JButton createButton(String text, String iconPath, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
		button.setBounds(x, y, width, height);
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		getContentPane().add(button);
		return button;
	}

	private JButton createIconButton(String iconPath, int x, int y, int width, int height) {
		JButton button = new JButton();
		button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
		button.setBounds(x, y, width, height);
		getContentPane().add(button);
		return button;
	}

	private void createSeparator() {
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 348, 540, 2);
		getContentPane().add(separator);
	}

	private void setupEventListeners() {
		txtCodigo.addActionListener(e -> findByCodigo());
		btnSearch.addActionListener(e -> findByCodigo());
		btnNew.addActionListener(e -> newAction());
		btnEdit.addActionListener(e -> editAction());
		btnSave.addActionListener(e -> saveAction());
		btnDelete.addActionListener(e -> deleteAction());
		btnCancel.addActionListener(e -> cancelAction());
	}

	private void initializeFormState() {
		disableForm();
		disableButtons();
	}

	// Métodos de controle do formulário
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

	private void loadData() {
		if (curso != null) {
			txtCodigo.setText(String.valueOf(curso.getCodigo()));
			txtNome.setText(curso.getNome());
			txtArea.setText(curso.getArea());
		}
	}

	// Métodos de ação
	private void findByCodigo() {
		String codigoText = txtCodigo.getText().trim();

		if (codigoText.isEmpty()) {
			return;
		}

		int codigo;
		try {
			codigo = Integer.parseInt(codigoText);
		} catch (NumberFormatException ex) {
			showErrorMessage(MSG_CODIGO_INVALIDO);
			return;
		}

		try {
			Curso record = controller.show(codigo);

			if (record == null) {
				showWarningMessage(MSG_REGISTRO_NAO_ENCONTRADO);
				clearForm();
				return;
			}

			curso = record;
			loadData();

		} catch (Exception e) {
			showErrorMessage(MSG_ERRO_CONSULTA + e.getMessage());
			clearForm();
		}
	}

	private void newAction() {
		clearForm();
		enableForm();
		enableButtons();
		txtNome.requestFocusInWindow();
	}

	private void editAction() {
		if (curso == null) {
			showWarningMessage(MSG_SELECIONE_REGISTRO);
			return;
		}

		loadData();
		enableForm();
		enableButtons();
	}

	private void saveAction() {
		if (!validateForm()) {
			return;
		}

		try {
			if (curso == null) {

				int codigo = controller.getSequencePrimaryKey();
				curso = controller.save(codigo, txtNome.getText().trim(), txtArea.getText().trim());
			} else {

				curso.setNome(txtNome.getText().trim());
				curso.setArea(txtArea.getText().trim());
				controller.update(curso);
			}

			txtCodigo.setText(String.valueOf(curso.getCodigo()));
			showSuccessMessage(MSG_SUCESSO_SALVAR);

			disableButtons();
			disableForm();
			txtCodigo.requestFocusInWindow();

		} catch (Exception e) {
			showErrorMessage("Erro ao salvar o registro: " + e.getMessage());
		}
	}

	private void deleteAction() {
		if (curso == null) {
			showWarningMessage(MSG_SELECIONE_REGISTRO);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, MSG_CONFIRMA_EXCLUSAO, getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		try {
			controller.delete(curso);
			showSuccessMessage(MSG_SUCESSO_EXCLUIR);

			clearForm();
			disableButtons();
			disableForm();
			txtCodigo.requestFocusInWindow();

		} catch (Exception e) {
			showErrorMessage("Erro ao excluir o registro: " + e.getMessage());
		}
	}

	private void cancelAction() {
		clearForm();
		disableForm();
		disableButtons();
		txtCodigo.requestFocusInWindow();
	}

	// Métodos de validação e mensagens
	private boolean validateForm() {
		String nome = txtNome.getText().trim();
		String area = txtArea.getText().trim();

		if (nome.isEmpty() || area.isEmpty()) {
			showWarningMessage("Todos os campos são obrigatórios.");
			return false;
		}

		try {
			ValidationResponse response = controller.validForm(nome, area);
			if (!response.isValid) {
				showWarningMessage(response.message);
			}
			return response.isValid;
		} catch (Exception e) {
			showErrorMessage(MSG_ERRO_VALIDACAO + e.getMessage());
			return false;
		}
	}

	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, getTitle(), JOptionPane.ERROR_MESSAGE);
	}

	private void showWarningMessage(String message) {
		JOptionPane.showMessageDialog(this, message, getTitle(), JOptionPane.WARNING_MESSAGE);
	}

	private void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(this, message, getTitle(), JOptionPane.INFORMATION_MESSAGE);
	}
}