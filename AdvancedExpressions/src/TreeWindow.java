import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import amharc.expr.BinaryOperator;
import amharc.expr.Constant;
import amharc.expr.Expression;
import amharc.expr.ExpressionVisitor;
import amharc.expr.UnaryOperator;
import amharc.expr.Variable;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.parser.Parser;


public class TreeWindow extends JFrame {
	private static final long serialVersionUID = 7945874150137535990L;
	
	private JTextField expressionField;
	private JTextField argumentField;
	private JLabel evalResult, integrateResult;
	private JButton evalButton, integrateButton, differentiateButton, resetToSimplfiedButton;
	
	private JTextField beginField, endField, stepsField;
	
	private Expression expression;
	private JTree tree;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	
	public TreeWindow() {
		this.setLayout(new BorderLayout());
		this.setTitle(Messages.getString("TreeWindow.WindowTitle")); //$NON-NLS-1$
		
		JPanel top = new JPanel(new BorderLayout());
		
		top.add(new JLabel(Messages.getString("TreeWindow.ExpressionLabelText")), BorderLayout.WEST); //$NON-NLS-1$
		expressionField = new JTextField();
		expressionField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				disableAll();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				disableAll();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				disableAll();
			}
		});
		top.add(expressionField, BorderLayout.CENTER);
		
		JButton parseButton = new JButton(Messages.getString("TreeWindow.ParseButtonText")); //$NON-NLS-1$
		parseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parseExpression();
			}
		});
		top.add(parseButton, BorderLayout.EAST);
		this.add(top, BorderLayout.NORTH);
		
		JPanel out = new JPanel(new GridLayout(1, 2));
		
		JPanel expressionOut = new JPanel(new BorderLayout());
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setEditable(false);
		expressionOut.add(new JScrollPane(tree), BorderLayout.CENTER);
		
		resetToSimplfiedButton = new JButton(Messages.getString("TreeWindow.ResetToSimplifiedButtonText")); //$NON-NLS-1$
		resetToSimplfiedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				expressionField.setText(expression.toString());
				setExpression();
			}
		});
		resetToSimplfiedButton.setEnabled(false);
		expressionOut.add(resetToSimplfiedButton, BorderLayout.SOUTH);
		out.add(expressionOut);
		
		JPanel evalPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		evalPanel.add(new JLabel(Messages.getString("TreeWindow.ArgumentLabelText")), constraints); //$NON-NLS-1$
		constraints.gridx = 1;
		constraints.weightx = 1;
		evalPanel.add(argumentField = new JTextField(), constraints);
		
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.weightx = 0;
		evalPanel.add(new JLabel(Messages.getString("TreeWindow.EvalResultLabelText")), constraints); //$NON-NLS-1$
		constraints.gridx = 1;
		constraints.weightx = 1;
		evalPanel.add(evalResult = new JLabel(), constraints);
		
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		evalButton = new JButton(Messages.getString("TreeWindow.EvaluateLabelText")); //$NON-NLS-1$
		evalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				evaluateExpression();
			}
		});
		evalPanel.add(evalButton, constraints);
		
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		evalPanel.add(new JSeparator(), constraints);
		
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		evalPanel.add(new JLabel(Messages.getString("TreeWindow.IntegrateBeginLabelText")), constraints); //$NON-NLS-1$
		constraints.gridx = 1;
		evalPanel.add(beginField = new JTextField(), constraints);
		
		constraints.gridy = 5;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		evalPanel.add(new JLabel(Messages.getString("TreeWindow.IntegrateEndLabelText")), constraints); //$NON-NLS-1$
		constraints.gridx = 1;
		evalPanel.add(endField = new JTextField(), constraints);
		
		constraints.gridy = 6;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		evalPanel.add(new JLabel(Messages.getString("TreeWindow.IntegrateStepsLabelText")), constraints); //$NON-NLS-1$
		constraints.gridx = 1;
		evalPanel.add(stepsField = new JTextField("100000"), constraints); //$NON-NLS-1$
		
		constraints.gridy = 7;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		evalPanel.add(new JLabel(Messages.getString("TreeWindow.IntegrateResultLabelText")), constraints); //$NON-NLS-1$
		constraints.gridx = 1;
		evalPanel.add(integrateResult = new JLabel(""), constraints); //$NON-NLS-1$
		
		
		constraints.gridy = 8;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		integrateButton = new JButton(Messages.getString("TreeWindow.IntegrateButtonText")); //$NON-NLS-1$
		integrateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				integrateExpression();
			}
		});
		evalPanel.add(integrateButton, constraints);
		
		constraints.gridy = 9;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		evalPanel.add(new JSeparator(), constraints);
		
		constraints.gridy = 10;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		differentiateButton = new JButton(Messages.getString("TreeWindow.DifferentiateButtonText")); //$NON-NLS-1$
		differentiateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				differentiateExpression();
			}
		});
		evalPanel.add(differentiateButton, constraints);
		
		out.add(evalPanel);
		this.add(out, BorderLayout.CENTER);
		
		disableAll();
		tree.setRootVisible(false);
		
		setPreferredSize(new Dimension(500, 300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
	}
	
	protected void differentiateExpression() {
		root.removeAllChildren();
		expression = expression.derivative();
		expressionField.setText(expression.toString());
		setExpression();
	}

	protected void integrateExpression() {
		try {
			Double begin = Double.parseDouble(beginField.getText());
			Double end = Double.parseDouble(endField.getText());
			Integer steps = Integer.parseInt(stepsField.getText());
			integrateResult.setText(Double.toString(expression.integral(begin, end, steps)));
		}
		catch(NumberFormatException ex) {
			evalResult.setText(""); //$NON-NLS-1$
			JOptionPane.showMessageDialog(this, Messages.getString("TreeWindow.IllegalNumber"), Messages.getString("TreeWindow.ParsingError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private void evaluateExpression() {
		try {
			Double d = Double.parseDouble(argumentField.getText());
			evalResult.setText(Double.toString(expression.evaluate(d)));
		}
		catch(NumberFormatException ex) {
			evalResult.setText(""); //$NON-NLS-1$
			JOptionPane.showMessageDialog(this, Messages.getString("TreeWindow.IllegalNumber"), Messages.getString("TreeWindow.ParsingError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	private void setEnabledFields(boolean enabled) {
		tree.setEnabled(enabled);
		argumentField.setEnabled(enabled);
		beginField.setEnabled(enabled);
		endField.setEnabled(enabled);
		stepsField.setEnabled(enabled);
		evalButton.setEnabled(enabled);
		integrateButton.setEnabled(enabled);
		differentiateButton.setEnabled(enabled);
	}
	
	private void enableAll() {
		setEnabledFields(true);
	}
	
	private void disableAll() {
		setEnabledFields(false);
	}

	private class TreeVisitor extends ExpressionVisitor {
		@Override
		public Object visit(Expression e) {
			return new DefaultMutableTreeNode(Messages.getString("TreeWindow.UnrecognizedExpression")); //$NON-NLS-1$
		}
		
		@Override
		public Object visit(BinaryOperator e) {
			DefaultMutableTreeNode cur = new DefaultMutableTreeNode(e.operatorString());
			cur.add((MutableTreeNode) e.getLhs().accept(this));
			cur.add((MutableTreeNode) e.getRhs().accept(this));
			return cur;
		}
		
		@Override
		public Object visit(UnaryOperator e) {
			DefaultMutableTreeNode cur = new DefaultMutableTreeNode(e.operatorString());
			cur.add((MutableTreeNode) e.getContent().accept(this));
			return cur;
		}
		
		@Override
		public Object visit(Variable e) {
			DefaultMutableTreeNode cur = new DefaultMutableTreeNode("x"); //$NON-NLS-1$
			return cur;
		}
		
		@Override
		public Object visit(Constant e) {
			DefaultMutableTreeNode cur = new DefaultMutableTreeNode("" + e.getValue()); //$NON-NLS-1$
			return cur;
		}
	}
	
	protected void parseExpression() {
		disableAll();
		
		root.removeAllChildren();
		model.nodeStructureChanged(root);
		
		try {
			expression = Parser.parse(expressionField.getText());
			setExpression();
		} catch(IllegalExpressionException ex) {
			JOptionPane.showMessageDialog(this, Messages.getString("TreeWindow.IllegalExpression"), Messages.getString("TreeWindow.ParsingError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	private void setExpression() {
		root.removeAllChildren();
		resetToSimplfiedButton.setEnabled(true);
		DefaultMutableTreeNode expressionRoot = (DefaultMutableTreeNode) expression.accept(new TreeVisitor());
		root.add(expressionRoot);
		model.nodeStructureChanged(root);
		enableAll();
	}
	
	public static void main(String[] args) {
		TreeWindow wnd = new TreeWindow();
		wnd.setVisible(true);
	}
}
