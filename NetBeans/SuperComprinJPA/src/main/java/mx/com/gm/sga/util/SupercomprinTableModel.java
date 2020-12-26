/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.util;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import mx.com.gm.sga.domain.Compra;
import mx.com.gm.sga.domain.Producto;
import mx.com.gm.sga.domain.Wallet;

/**
 *
 * @author peixe
 */
public class SupercomprinTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }    

    public SupercomprinTableModel setModelWallets(List<Wallet> wallets) {
        
        Object[] tags = new Object[]{"ID", "NOMBRE", "APELLIDOS", "DNI", "FECHA NACIMIENTO", "E-MAIL", "PUNTOS", "SALDO", "EDAD"};
        Object[][] data = new Object[wallets.size()][tags.length];
        for (int i = 0; i < wallets.size(); i++) {
            Wallet aux = wallets.get(i);
            data[i][0] = aux.getIdwallet();
            data[i][1] = aux.getNombre();
            data[i][2] = aux.getApellidos();
            data[i][3] = aux.getDni();
            data[i][4] = aux.getFechanacimiento();
            data[i][5] = aux.getEmail();
            data[i][6] = aux.getPuntos();
            data[i][7] = aux.getSaldo();
            data[i][8] = aux.getEdad();
        }
        this.setDataVector(data, tags);
        return this;
    }

    public SupercomprinTableModel setModelProductos(List<Producto> productos) {
        Object[] tags = new Object[]{"ID", "NOMBRE","PUNTOS", "PRECIO"};
        Object[][] data = new Object[productos.size()][tags.length];
        for (int i = 0; i < productos.size(); i++) {
            Producto aux = productos.get(i);
            data[i][0] = aux.getIdproducto();
            data[i][1] = aux.getNombre();
            data[i][2] = aux.getPuntos();
            data[i][3] = aux.getPrecio();
        }
        this.setDataVector(data, tags);
        return this;
    }

    public SupercomprinTableModel setModelCompras(List<Compra> compras) {
        Object[] tags = new Object[]{"ID", "IDPRODUCTO", "IDWALLET", "FECHA"};
        Object[][] data = new Object[compras.size()][tags.length];
        for (int i = 0; i < compras.size(); i++) {
            Compra aux = compras.get(i);
            data[i][0] = aux.getIdCompra();
            data[i][1] = aux.getIdProducto();
            data[i][2] = aux.getIdWallet();
            data[i][3] = aux.getFecha();
        }
        this.setDataVector(data, tags);
        return this;
    }

}
