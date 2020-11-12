package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.conexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

import alumno.alumno;
import alumno.listaalumnos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main extends JFrame {

	private JPanel contentPane;
	public int contador;
	private JTable TablaDatos;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCrudApplication = new JLabel("CRUD APPLICATION");
		lblCrudApplication.setBounds(264, 26, 173, 21);
		lblCrudApplication.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(lblCrudApplication);

		JPanel panel = new JPanel();
		panel.setBounds(12, 68, 680, 138);
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(12, 56, 70, 20);
		panel.add(lblNombre);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEdad.setBounds(12, 89, 70, 20);
		panel.add(lblEdad);

		txtNombre = new JTextField();
		txtNombre.setBounds(101, 56, 269, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtEdad = new JTextField();
		txtEdad.setBounds(102, 89, 56, 22);
		panel.add(txtEdad);
		txtEdad.setColumns(10);

		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(12, 23, 70, 20);
		panel.add(lblId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(101, 23, 56, 22);
		panel.add(txtId);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 234, 680, 138);
		panel_1.setBorder(new TitledBorder(null, "Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		


	

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(12, 402, 680, 273);
		contentPane.add(scrollPane);

		TablaDatos = new JTable();
		TablaDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = TablaDatos.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(null, "USuario no selecionado");
				} else {
					int id = Integer.parseInt((String) TablaDatos.getValueAt(fila, 0).toString());
					String nombre = (String) TablaDatos.getValueAt(fila, 1);
					int edad = (int) TablaDatos.getValueAt(fila, 2);
					txtId.setText("" + id);
					txtNombre.setText(nombre);
					txtEdad.setText("" + edad);
				}

			}
		});
		TablaDatos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Edad" }));
		TablaDatos.getColumnModel().getColumn(1).setMinWidth(29);
		scrollPane.setViewportView(TablaDatos);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				agregar();
				listar();
				nuevo();
			}
		});
		btnAgregar.setBounds(31, 49, 103, 42);
		panel_1.add(btnAgregar);
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				listar();
				nuevo();
			}
		});
		btnModificar.setBounds(184, 49, 103, 42);
		panel_1.add(btnModificar);
		

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				listar();
				nuevo();
			}
		});
		btnEliminar.setBounds(342, 49, 103, 42);
		panel_1.add(btnEliminar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		btnNuevo.setBounds(508, 49, 103, 42);
		panel_1.add(btnNuevo);
		
		listar();

	}

	public void listar() {
		conexion database = new conexion();
		Connection con = null;
		Statement statement = null;
		ResultSet rs1 = null;
		DefaultTableModel modelo;

		String sql = "select * from registro";

		try {

			con = database.getConexion();
			statement = con.createStatement();
			rs1 = statement.executeQuery(sql);

			Object[] persona = new Object[3];
			modelo = (DefaultTableModel) TablaDatos.getModel();

			while (rs1.next()) {
				persona[0] = rs1.getInt("id");
				persona[1] = rs1.getString("nombre");
				persona[2] = rs1.getInt("edad");
				modelo.addRow(persona);

			}

			TablaDatos.setModel(modelo);

		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	public void agregar() {

		String nombre = txtNombre.getText();
		int edad = Integer.parseInt(txtEdad.getText());

		conexion database = new conexion();
		Connection con = null;
		PreparedStatement statement = null;

		int insertado = 0;
		String sql = "INSERT INTO registro(nombre, edad) VALUES ('" + nombre + "', '" + edad + "')";
		try {

			con = database.getConexion();
			statement = con.prepareStatement(sql);
			insertado = statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Agregado");
			limpiar();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void limpiar() {
		DefaultTableModel modelo;
		modelo = (DefaultTableModel) TablaDatos.getModel();

		for (int i = 0; i <= TablaDatos.getRowCount(); i++) {
			modelo.removeRow(i);
			i = i - 1;
		}
	}
	
	
	


	public void modificar() {
		int id= Integer.parseInt(txtId.getText());
		
		String nombre=txtNombre.getText();
		int edad= Integer.parseInt(txtEdad.getText());
		
		String sql="UPDATE registro SET nombre='"+nombre+"', edad='"+edad+"' WHERE id="+id;
	
		
		conexion database = new conexion();
		Connection con = null;
		PreparedStatement statement = null;

		int insertado = 0;
		try {

			con = database.getConexion();
			statement = con.prepareStatement(sql);
			insertado = statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Modificado");
			
			limpiar();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void eliminar() {
		
		int filaselecionado=TablaDatos.getSelectedRow();
		if(filaselecionado==-1) {
			JOptionPane.showMessageDialog(null, "Selecione");
		}else {
			int id= Integer.parseInt(txtId.getText());
			
		
			
			String sql="delete from registro where id="+id;
		
			
			conexion database = new conexion();
			Connection con = null;
			PreparedStatement statement = null;

			int insertado = 0;
			try {

				con = database.getConexion();
				statement = con.prepareStatement(sql);
				insertado = statement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Eliminado");
				
				limpiar();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}


	public void nuevo() {
		txtId.setText("");
		txtNombre.setText("");
		txtEdad.setText("");
		txtNombre.requestFocus();
	}
	
}
