package paquetereloj;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Reloj extends javax.swing.JFrame implements Runnable {
    String hora, minuto, segundo, ampm;
    Calendar calendario;
    Thread h1;

 
    public Reloj() {
        initComponents();
        h1= new Thread(this);
        h1.start();
        
        setLocationRelativeTo(null);
        setTitle("Reloj");
        setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblReloj = new javax.swing.JLabel();
        veintiCuatroH = new javax.swing.JCheckBox();
        ActivarAlarma = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReloj.setText("jLabel1");
        lblReloj.setToolTipText("");

        veintiCuatroH.setText("Modo 24 horas");

        ActivarAlarma.setText("Programar Alarma");
        ActivarAlarma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarAlarmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReloj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(veintiCuatroH)
                .addGap(41, 41, 41)
                .addComponent(ActivarAlarma)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(veintiCuatroH)
                    .addComponent(ActivarAlarma))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ActivarAlarmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarAlarmaActionPerformed
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfigurarAlarma().setVisible(true);
            }
        });
    }//GEN-LAST:event_ActivarAlarmaActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActivarAlarma;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JCheckBox veintiCuatroH;
    // End of variables declaration//GEN-END:variables

/**
 * Implementa el método 'run()' de la interfaz 'Runnable'. Este método se ejecuta en un hilo separado
 * y se encarga de actualizar continuamente la visualización del reloj en la interfaz gráfica.
 */
@Override
public void run() {
    // Obtener la referencia al hilo actual
    Thread ct = Thread.currentThread();
    
    // Mantener la ejecución mientras el hilo actual sea igual al hilo 'h1'
    while(ct==h1){
        // Calcular y actualizar la hora actual
        calcula();
        
        // Actualizar el componente de texto con la hora formateada
        lblReloj.setText(hora + ":" + minuto + ":" + segundo + " " + ampm);
        
        try {
            // Dormir el hilo durante 1000 milisegundos (1 segundo)
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            // Manejar excepciones relacionadas con la interrupción del hilo
            Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/**
 * Calcula la hora actual y actualiza las variables de instancia 'hora', 'minuto', 'segundo' y 'ampm'.
 */
private void calcula() {
    // Obtener una instancia del calendario gregoriano
    Calendar calendario = new GregorianCalendar();
    
    // Obtener la fecha y hora actual
    Date fechahoraAcutual = new Date();
    
    // Configurar el calendario con la fecha y hora actual
    calendario.setTime(fechahoraAcutual);
    
    // Determinar si es AM o PM y asignar el valor correspondiente a la variable 'ampm'
    ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
    
    // Formatear la hora según el formato de 12 horas
    if (ampm.equals("PM")) {
        int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
        hora = h > 9 ? "" + h : "0" + h;
    } else {
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
    }
    
    // Formatear el minuto y el segundo, asegurándose de tener dos dígitos
    minuto = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
    segundo = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND );
}

}
