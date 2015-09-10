package br.com.waiso.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.waiso.exception.InternacionalizacaoException;
import br.com.waiso.util.GeradorArquivo;

public class Frame extends JFrame {

	private static final long serialVersionUID = 7258005285967244579L;

	private JTextField tfDiretorio;
	private JPanel painel;
	private JPanel painelPrincipal;
	private JPanel painelSelecaoTipoArquivo;
	private JRadioButton rbJson;
	private JRadioButton rbTmx;
	private JButton btnSelecionarDiretorio;
	private JButton btnIniciar;
	private JFileChooser fileChooser;
	private ButtonGroup grupoRadioButton;

	public Frame() {
		initializaComponentes();
		adicionaCompomentes();
	}

	private void initializaComponentes() {
		this.setBounds(100, 100, 600, 120);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	private void adicionaCompomentes() {
		painelSelecaoTipoArquivo = new JPanel();
		this.getContentPane().add(painelSelecaoTipoArquivo, BorderLayout.NORTH);

		rbJson = new JRadioButton("JSON");
		rbJson.setSelected(true);
		painelSelecaoTipoArquivo.add(rbJson);

		rbTmx = new JRadioButton("TMX");
		painelSelecaoTipoArquivo.add(rbTmx);

		grupoRadioButton = new ButtonGroup(); 
		grupoRadioButton.add(rbJson);
		grupoRadioButton.add(rbTmx);

		painel = new JPanel();
		this.getContentPane().add(painel, BorderLayout.CENTER);
		painel.setLayout(new BorderLayout(0, 0));

		painelPrincipal = new JPanel();
		painel.add(painelPrincipal, BorderLayout.CENTER);

		btnSelecionarDiretorio = new JButton("SELECIONAR DIRETÓRIO");
		btnSelecionarDiretorio.setHorizontalAlignment(SwingConstants.LEFT);
		btnSelecionarDiretorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		painelPrincipal.add(btnSelecionarDiretorio);

		tfDiretorio = new JTextField();
		painelPrincipal.add(tfDiretorio);
		tfDiretorio.setEditable(false);
		tfDiretorio.setHorizontalAlignment(SwingConstants.CENTER);
		tfDiretorio.setColumns(30);

		btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciar();
			}
		});
		painel.add(btnIniciar, BorderLayout.SOUTH);

		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

	public void close() {
		this.setVisible(false);
		this.dispose();
	}

	private void abrir() {
		int returnVal = fileChooser.showOpenDialog(Frame.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	tfDiretorio.setText(fileChooser.getSelectedFile().getParent());
        } 
	}

	private void iniciar() {
		try {
			if (tfDiretorio.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Frame.this, "Selecione um diretório!");
			} else {
				String diretorio = fileChooser.getSelectedFile().getParent();
				if(rbJson.isSelected()) {
					GeradorArquivo.getInstance().geraJson(diretorio);
				} else {
					GeradorArquivo.getInstance().geraTmx(diretorio);
				}
				JOptionPane.showMessageDialog(Frame.this, "Arquivo salvo no diretório: " + diretorio, "sucesso", JOptionPane.INFORMATION_MESSAGE);
				close();
			}
		} catch (InternacionalizacaoException e) {
			JOptionPane.showMessageDialog(Frame.this, e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			System.out.println("Erro:" + e.getMessage());
		} 
	}
	
}
