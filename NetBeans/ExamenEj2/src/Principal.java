
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Principal extends JFrame {

    JButton crear;
    JFileChooser selector;
    JEditorPane texto;

    public Principal() {

        JPanel p = new JPanel();
        crear = new JButton("Guardar Archivo");
        crear.addActionListener((ActionEvent e) -> {
            selector = new JFileChooser();
            int op = selector.showSaveDialog(Principal.this);
            if (op == JFileChooser.APPROVE_OPTION) {
                try {
                    try (OutputStream archivo = new FileOutputStream(selector.getSelectedFile())) {
                        Document doc = new Document();
                        PdfWriter.getInstance(doc, archivo);
                        doc.open();
                        doc.add(new Paragraph(texto.getText()));
                        doc.close();
                    }
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace(System.out);                    
                } catch (DocumentException | IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace(System.out);
                }
                // TODO Auto-generated catch block
                
            }
        });
        p.add(crear);

        add(p, BorderLayout.NORTH);

        texto = new JEditorPane();
        add(new JScrollPane(texto));

    }

    public static void main(String arg[]) {
        Principal p = new Principal();
        p.setVisible(true);
        p.setBounds(0, 0, 400, 400);
        p.setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLocationRelativeTo(null);
    }

}
