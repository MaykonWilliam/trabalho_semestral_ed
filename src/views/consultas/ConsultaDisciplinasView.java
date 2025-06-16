package views.consultas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controllers.ConsultaDisciplinasProcessosController;
import domain.entities.Disciplina;
import domain.entities.Inscricao;
import domain.entities.Professor;
import utils.List;
import views.inscricao.InscricaoView;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConsultaDisciplinasView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private ConsultaDisciplinasProcessosController controller = new ConsultaDisciplinasProcessosController();
	private List<Inscricao> list;
	private JTextField textSearch;
	private TableRowSorter<DefaultTableModel> sorter;

	public ConsultaDisciplinasView() {
		initializeComponent();
		createTable();
		loadData();
		addDoubleClickListener();
		setupWindowFocusListener();
	}

	private void setupWindowFocusListener() {
		this.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				sorter.setRowFilter(null);
				loadData();
			}

			public void windowLostFocus(WindowEvent e) {
				
			}
		});
	}

	private void initializeComponent() {
		setTitle("Consulta de Disciplinas com Processos Abertos");
		setBounds(100, 100, 906, 451);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSearch = new JButton("Buscar");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchRecords();
			}
		});
		btnSearch.setIcon(new ImageIcon(ConsultaDisciplinasView.class.getResource("/resources/icons/search.png")));
		btnSearch.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSearch.setEnabled(true);
		btnSearch.setBounds(775, 11, 105, 45);
		contentPane.add(btnSearch);
	}

	private void searchRecords() {
		sorter.setRowFilter(null);
		loadData();

		String textoBusca = textSearch.getText().trim();
		if (!textoBusca.isEmpty()) {
			RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter("(?i)" + textoBusca);
			sorter.setRowFilter(filter);
		}
	}

	private void createTable() {
		String[] columns = { "Código", "Disciplina", "CPF do Professor", "Nome Professor", "Area Professor", "Pontuação", "Status" };

		tableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Todas as células não editáveis
			}
		};

		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 60, 870, 297);
		contentPane.add(scrollPane);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(25);

		textSearch = new JTextField();
		textSearch.setEnabled(true);
		textSearch.setBounds(10, 31, 755, 25);
		contentPane.add(textSearch);

		JLabel lblDigiteOTemo = new JLabel("Digite o termo para realizar a busca");
		lblDigiteOTemo.setBounds(10, 11, 223, 20);
		contentPane.add(lblDigiteOTemo);

		// Configuração das larguras das colunas
		table.getColumnModel().getColumn(0).setPreferredWidth(80); // Codigo
		table.getColumnModel().getColumn(1).setPreferredWidth(150); // Disciplina
		table.getColumnModel().getColumn(2).setPreferredWidth(150); // CPF
		table.getColumnModel().getColumn(3).setPreferredWidth(250); // Nome
		table.getColumnModel().getColumn(4).setPreferredWidth(100); // Area
		table.getColumnModel().getColumn(5).setPreferredWidth(100); // Pontuacao
		table.getColumnModel().getColumn(6).setPreferredWidth(100); // Status

		sorter = new TableRowSorter<>(tableModel);
	}

	private void loadData() {
		sorter.setRowFilter(null);
		table.setRowSorter(null);
		tableModel.setRowCount(0);

		list = controller.getAll();

		for (int i = 0; i < list.size(); i++) {
			try {
				Inscricao item = list.get(i);
				Professor professor = item.getProfessor();
				Disciplina disciplina = item.getDisciplina();

				Object[] row = { item.getCodigo(), disciplina != null ? disciplina.getNome() : "N/A", professor != null ? professor.getCpf() : "N/A", professor != null ? professor.getNome() : "N/A",
						professor != null ? professor.getAreaConhecimento() : "N/A", professor != null ? professor.getPontuacao() : "N/A", item.getStatus() };
				tableModel.addRow(row);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}

		table.setRowSorter(sorter);
	}

	private void addDoubleClickListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						int selectedRow = table.getSelectedRow();

						if (selectedRow >= 0) {
							int modelRowIndex = table.convertRowIndexToModel(selectedRow);

							if (modelRowIndex >= 0 && modelRowIndex < list.size()) {
								Inscricao selectedRecord = list.get(modelRowIndex);

								if (selectedRecord != null) {
									showRecordPage(selectedRecord);
								}
							}
						}
					} catch (Exception err) {
						JOptionPane.showMessageDialog(null, "Erro ao abrir o registro: " + err.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	private void showRecordPage(Inscricao record) {
		try {
			InscricaoView view = new InscricaoView(record);
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao visualizar o registro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}