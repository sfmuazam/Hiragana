import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class TestDialog extends JDialog implements ActionListener {
	private JLabel lblKana;
	private JButton btnPass, btnEnd;
	private JTextField answerBox;
	public int correct = 0;
	public int question = 0;
	public int score = 0;
	public static String[] hiragana = {
			"\u3042", "\u3044", "\u3046", "\u3048", "\u304A",
			"\u304B", "\u304D", "\u304F", "\u3051", "\u3053",
			"\u3055", "\u3057", "\u3059", "\u305B", "\u305D",
			"\u305F", "\u3061", "\u3064", "\u3066", "\u3068",
			"\u306A", "\u306B", "\u306C", "\u306D", "\u306E",
			"\u306F", "\u3072", "\u3075", "\u3078", "\u307B",
			"\u307E", "\u307F", "\u3080", "\u3081", "\u3082",
			"\u3084", "\u3086", "\u3088",
			"\u3089", "\u308A", "\u308B", "\u308C", "\u308D",
			"\u308F", "\u3092",
			"\u3093"
	};
	public static String[] romaji = {
			"a", "i", "u", "e", "o",
			"ka", "ki", "ku", "ke", "ko",
			"sa", "shi", "su", "se", "so",
			"ta", "chi", "tsu", "te", "to",
			"na", "ni", "nu", "ne", "no",
			"ha", "hi", "fu", "he", "ho",
			"ma", "mi", "mu", "me", "mo",
			"ya", "yu", "yo",
			"ra", "ri", "ru", "re", "ro",
			"wa", "wo",
			"n"
	};

	public static String shuffle() {
		Random r = new Random();
		int index = r.nextInt(hiragana.length); // mendapatkan random int
		return hiragana[index];
	}

	public static String getRomaji(String c) {
		for (int i = 0; i < romaji.length; i++)
			if (hiragana[i].equals(c))
				return romaji[i];
		throw new IllegalArgumentException("No constant with value " + c + " found");
	}

	public TestDialog(final JFrame parent) {
		super(parent, parent.getTitle()); // (parent, string judul)
		setSize(700, 350);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 700) / 2, (screen.height - 350) / 2);
		setResizable(false);
		setModal(true); // agar parent tidak bisa diakses
		JPanel p1 = new JPanel();
		p1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// label huruf
		lblKana = new JLabel();
		lblKana.setHorizontalAlignment(SwingConstants.CENTER);
		lblKana.setText(shuffle());
		lblKana.setFont(new Font("MS Mincho", Font.BOLD, 120));
		c.gridx = 0; // kolom ke-
		c.gridy = 0; // baris ke-
		c.gridwidth = 3; // span kolom
		p1.add(lblKana, c);

		// kolom jawaban
		answerBox = new JTextField("", 3); // (string, column)
		answerBox.addActionListener(this);
		answerBox.setFont(new Font("MS Mincho", Font.BOLD, 30));
		answerBox.setHorizontalAlignment(JTextField.CENTER); // letak tengah
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		p1.add(answerBox, c);

		// button pass
		btnPass = new JButton("Pass");
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblKana.setText(shuffle());
			}
		});
		c.insets = new Insets(20, 0, 30, 0); // padding top,left,bottom,right
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		p1.add(btnPass, c);

		// button end
		btnEnd = new JButton("End");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (question == 0) {
					score = 0;
				} else {
					score = correct * 100 / question; // hitung nilai
				}
				JOptionPane.showMessageDialog(null,
						"You answered correctly " + correct + " out of " + question + " questions\nScore : " + score); // (parent,
																														// //
																														// objek)
			}
		});
		c.gridx = 2;
		c.gridy = 2;
		p1.add(btnEnd, c);

		this.add(p1);
		setVisible(true); // tampilkan jdialog
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = e.getActionCommand();
		if (input.toLowerCase().equals("")) { // jika jawaban kosong
			lblKana.setText(shuffle());
		} else if (input.toLowerCase().equals(getRomaji(lblKana.getText()))) { // jika jawaban benar
			correct++;
			question++;
			JOptionPane.showMessageDialog(null, "Correct! Very nice!");
		} else { // jika jawaban salah
			question++;
			JOptionPane.showMessageDialog(null, "Wrong! The correct answer is \"" + getRomaji(lblKana.getText()) + "\"");
		}
		answerBox.setText("");
		lblKana.setText(shuffle());
	}
}