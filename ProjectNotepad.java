import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet.*;

public class ProjectNotepad extends KeyAdapter implements ActionListener, KeyListener {

    static int active = 0;
    static int fsize = 17;
    JFrame f1;
    JMenuBar mBar;
    JMenu file, edit, format, view;
    JMenuItem newdoc, opendoc, exit, savedoc, copydoc, pastedoc, fontfamily, fontstyle, fontsize, status;
    JTextArea maintext;
    JTextField title;
    Font font1;
    JPanel bottom;
    JLabel details, pastecopydoc;
    JList familylist, stylelist, sizelist;
    JScrollPane sb;

    String familyvalue[] = { "Agency FB", "Antiqua", "Architect", "Arial", "Calibri", "Comic Sans", "Courier",
            "Cursive", "Impact", "Serif" };

    String sizevalue[] = { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70" };
    int[] stylevalue = { Font.PLAIN, Font.BOLD, Font.ITALIC };
    String[] stylevalues = { "PLAIN", "BOLD", "ITALIC" };
    String ffamily, fsizestr, fstylestr;
    int fstyle;
    int cl;
    int linecount;
    String tle;
    String topicstitle = "";
    JScrollPane sp;

    ProjectNotepad() {
        f1 = new JFrame("Notepad");

        font1 = new Font("Arial", Font.PLAIN, 17);

        bottom = new JPanel();
        details = new JLabel();
        pastecopydoc = new JLabel();

        familylist = new JList(familyvalue);
        stylelist = new JList(stylevalues);
        sizelist = new JList(sizevalue);

        familylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sizelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        stylelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        bottom.add(details);

        maintext = new JTextArea();

        sp = new JScrollPane(maintext);
        title = new JTextField(100);

        sb = new JScrollPane(maintext);

        maintext.setMargin(new Insets(5, 5, 5, 5));

        maintext.setFont(font1);
        f1.add(maintext);

        mBar = new JMenuBar();

        file = new JMenu("File");
        edit = new JMenu("Edit");
        format = new JMenu("Format");
        view = new JMenu("View");

        newdoc = new JMenuItem("New Document");
        opendoc = new JMenuItem("Open Document");
        savedoc = new JMenuItem("Save Document");
        exit = new JMenuItem("Exit");

        copydoc = new JMenuItem("Copy Document");
        pastedoc = new JMenuItem("Paste Document");

        fontfamily = new JMenuItem("Set Font Family");
        fontstyle = new JMenuItem("Set Font Style");
        fontsize = new JMenuItem("Set Font Size");
        status = new JMenuItem("Status");

        file.add(newdoc);
        file.add(opendoc);
        file.add(savedoc);
        file.add(exit);

        edit.add(copydoc);
        edit.add(pastedoc);

        format.add(fontfamily);
        format.add(fontstyle);
        format.add(fontsize);

        view.add(status);

        mBar.add(file);
        mBar.add(edit);
        mBar.add(format);
        mBar.add(view);

        f1.setJMenuBar(mBar);
        f1.add(bottom, BorderLayout.SOUTH);

        newdoc.addActionListener(this);
        copydoc.addActionListener(this);
        pastedoc.addActionListener(this);
        status.addActionListener(this);
        savedoc.addActionListener(this);

        fontfamily.addActionListener(this);
        fontsize.addActionListener(this);
        fontstyle.addActionListener(this);

        exit.addActionListener(this);

        maintext.addKeyListener(this);

        f1.setSize(600, 600);

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);
        f1.setVisible(true);
    }

    public void actionPerformed(final ActionEvent ae) {
        if (ae.getSource() == newdoc) {
            f1.setTitle("New Document.txt");
            maintext.setText("");
        } else if (ae.getSource() == copydoc) {
            final String texts = maintext.getText();
            pastecopydoc.setText(texts);
            JOptionPane.showMessageDialog(null, "Copy Text " + texts);
        } else if (ae.getSource() == pastedoc) {
            if (maintext.getText().length() != 0) {
                maintext.setText(maintext.getText());
            } else {
                maintext.setText(pastecopydoc.getText());
            }
        } else if (ae.getSource() == status) {
            try {
                if (active == 0) {
                    final File f = new File(tle + ".txt");
                    details.setText("Size: " + f.length());
                }
            } catch (final Exception e) {

            }
        } else if (ae.getSource() == fontfamily) {

            JOptionPane.showConfirmDialog(null, familylist, "Choose Font Family", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            ffamily = String.valueOf(familylist.getSelectedValue());
            font1 = new Font(ffamily, fstyle, fsize);
            maintext.setFont(font1);
        } else if (ae.getSource() == fontstyle) {

            JOptionPane.showConfirmDialog(null, stylelist, "Choose Font Style", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            fstyle = stylevalue[stylelist.getSelectedIndex()];
            font1 = new Font(ffamily, fstyle, fsize);
            maintext.setFont(font1);
        } else if (ae.getSource() == fontsize) {

            JOptionPane.showConfirmDialog(null, sizelist, "Choose Font Size", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            fsizestr = String.valueOf(sizelist.getSelectedValue());
            fsize = Integer.parseInt(fsizestr);
            font1 = new Font(ffamily, fstyle, fsize);
            maintext.setFont(font1);
        } else if (ae.getSource() == exit) {
            f1.dispose();

        }
    }

    public void keyTyped(final KeyEvent ke) {
        cl = maintext.getText().length();
        linecount = maintext.getLineCount();
        details.setText("Length: " + cl + " Line: " + linecount);
    }

    public static void main(final String[] args) {

        new ProjectNotepad();
    }

}
