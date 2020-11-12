/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenej2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import view.Frame;

/**
 *
 * @author axegas
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        //Frame f = new Frame();
        //f.setVisible(true);
        JFileChooser JFCO = new JFileChooser();
        File f2 = new File(System.getProperty("user.dir"), "FICHERACO.pdf");

        JFCO.setCurrentDirectory(new File(System.getProperty("user.dir")));

        JFCO.setSelectedFile(f2);

        JFCO.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnVal = JFCO.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            InputStream in;
            try {
                File fC = new File(JFCO.getSelectedFile().getAbsolutePath());
                in = new FileInputStream(f2);
                try (OutputStream out = new FileOutputStream(fC)) {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }   in.close();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        f2.delete();
    }

}
