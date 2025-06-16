package views.professor;

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

import controllers.ProfessorController;
import domain.entities.Professor;
import utils.List;

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

public class ProfessorListView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable table;
	private DefaultTableModel tableModel;

	ProfessorController controller = new ProfessorController();
	List<Professor> list;

	private JTextField textSearch;
	private TableRowSorter<DefaultTableModel> sorter;

	public ProfessorListView() {

		initializeComponent();

		createTable();

		loadData();

		addDoubleClickListener();

		setupWindowFocusListener();
	}

	private void setupWindowFocusListener() {
		this.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				textSearch.setText("");
				sorter.setRowFilter(null);
				loadData();
			}

			public void windowLostFocus(WindowEvent e) {

			}
		});
	}

	private void initializeComponent() {

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
		btnSearch.setIcon(new ImageIcon(ProfessorListView.class.getResource("/resources/icons/search.png")));
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

	@SuppressWarnings("serial")
	private void createTable() {
		String[] columns = { "CPF", "Nome", "Area de Conhecimento", "Pontuação" };

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

		JLabel lblDigiteOTemo = new JLabel("Digite o temo para realizr a busca");
		lblDigiteOTemo.setBounds(10, 11, 223, 20);
		contentPane.add(lblDigiteOTemo);

		JButton btnNew = new JButton("Adicionar Novo Registro");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRecordPage(null);
			}
		});
		btnNew.setIcon(new ImageIcon(ProfessorListView.class.getResource("/resources/icons/plus.png")));
		btnNew.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNew.setEnabled(true);
		btnNew.setBounds(616, 366, 264, 35);
		contentPane.add(btnNew);

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);

		sorter = new TableRowSorter<>(tableModel);
	}

	private void loadData() {

		sorter.setRowFilter(null);
		table.setRowSorter(null);

		tableModel.setRowCount(0);

		list = controller.getAll();
		for (int i = 0; i < list.size(); i++) {
			Professor item;
			try {
				item = list.get(i);
				Object[] row = { item.getCpf(), item.getNome(), item.getAreaConhecimento(), item.getPontuacao() };
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
								Professor selectedRecord = list.get(modelRowIndex);

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

	private void showRecordPage(Professor record) {
		try {

			ProfessorView view = new ProfessorView(record);
			view.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao visualizar o registro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
