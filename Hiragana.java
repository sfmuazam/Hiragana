import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hiragana extends JFrame implements ActionListener {

  private JButton btnStart;
  private JLabel title1, title2;

  public Hiragana() {
    setTitle("Hiragana"); // set judul
    setSize(700, 350); // set ukuran
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((screen.width - 700) / 2, (screen.height - 350) / 2);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    JPanel p1 = new JPanel();
    p1.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    // judul 1
    title1 = new JLabel("\u3072\u3089\u304C\u306A", JLabel.CENTER);
    title1.setFont(new Font("MS Mincho", Font.BOLD, 90));
    c.gridx = 0; // kolom ke-
    c.gridy = 0; // baris ke-
    c.gridwidth = 3; // span kolom
    p1.add(title1, c);

    // judul 2
    title2 = new JLabel("Hiragana", JLabel.CENTER);
    title2.setFont(new Font("MS Mincho", Font.BOLD, 30));
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 3;
    p1.add(title2, c);

    // button start
    btnStart = new JButton("Start!");
    btnStart.addActionListener(this);
    c.insets = new Insets(40, 0, 30, 0); // padding top,left,bottom,right
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth = 3;
    p1.add(btnStart, c);
    this.add(p1);
    setVisible(true); // tampilkan jframe
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnStart) {
      new TestDialog(this);
    }
  }

  public static void main(String[] args) {
    Hiragana frame = new Hiragana();
  }
}
