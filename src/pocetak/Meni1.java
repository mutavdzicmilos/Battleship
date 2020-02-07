package pocetak;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Meni1 {

    static public JButton[] nizDugmica;
    public JTextField[] nizTekstFieldova;
    protected int brojTekstFieldova;
    protected int brojDugmica;
    public JPanel meniPanel;
    protected GridBagConstraints gbc;
    
    public Meni1(String[] imenaDugmica, String[] imenaTextFieldova) {
       Start1.glavniEkran.getContentPane().setLayout(new BorderLayout());//deli na pet delova
        JLabel nazivIgre = new JLabel("Potapanje brodova");
        nazivIgre.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        brojDugmica = imenaDugmica.length;
        nizDugmica = new JButton[brojDugmica];

       brojTekstFieldova = imenaTextFieldova.length;
       if (brojTekstFieldova > 0)
            nizTekstFieldova = new JTextField[brojTekstFieldova];

        meniPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        //pomeramo sve
        Start1.glavniEkran.getContentPane().add(meniPanel,BorderLayout.NORTH);
        gbc.gridx = 0;
        gbc.gridy = 0;
        meniPanel.add(nazivIgre, gbc);
        //zbog labele  pravimo razmak
        gbc.gridy++;

        gbc.fill = GridBagConstraints.HORIZONTAL;//kada je komponenta manja od njene display area
       
        
        gbc.insets = new Insets(0, 0, 20, 0);//razmak odozdo
        //unosimo sve textfielde koje imamo redom sa razmakom 20
        for (int i = 0; i < brojTekstFieldova; i++) {
            nizTekstFieldova[i] = new JTextField(imenaTextFieldova[i]);
            nizTekstFieldova[i].setFont(new Font("Times New Roman", Font.PLAIN, 20));
            meniPanel.add(nizTekstFieldova[i], gbc);
            gbc.gridy++;
        }
        //razmak na 5 odozdo za dugmice
        gbc.insets = new Insets(0, 0, 5, 0);
        //unosimo sve dugmice redom kolkogod ih ima
        for (int i = 0; i < brojDugmica; i++) {
            nizDugmica[i] = new JButton(imenaDugmica[i]);
            nizDugmica[i].setFont(new Font("Times New Roman", Font.PLAIN, 20));
            meniPanel.add(nizDugmica[i], gbc);
            gbc.gridy++;
        }

    }

    abstract protected class EventHandler implements ActionListener {

        abstract public void actionPerformed(ActionEvent event);

    }

}
