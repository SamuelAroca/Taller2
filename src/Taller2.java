import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Taller2 extends JFrame{
    private JPanel mainPanel;
    private JLabel labelTitulo;
    private JLabel labelNombre;
    private JTextField textFieldNombre;
    private JLabel labelTipo;
    private JComboBox comboBoxTipo;
    private JLabel labelMarca;
    private JLabel labelModelo;
    private JLabel labelPlaca;
    private JLabel labelFecha;
    private JLabel labelHoraIngreso;
    private JLabel labelSalida;
    private JLabel labelTarifa;
    private JTextField textFieldMarca;
    private JTextField textFieldModelo;
    private JTextField textFieldPlaca;
    private JTextField textFieldFecha;
    private JTextField textFieldHoraIngre;
    private JTextField textSalida;
    private JTextField textTarifa;
    private JButton buttonCalcular;
    private JButton buttonSalir;

    public Taller2() {
        setContentPane(mainPanel);

        textFieldFecha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!isValidFormat(textFieldFecha.getText())) {
                    JOptionPane.showMessageDialog(new Frame(),"El formato no es válido");
                }
            }
        });
        textFieldHoraIngre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (validarHora(textFieldHoraIngre.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        textFieldNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!validarNombre(textFieldNombre.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        textFieldPlaca.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!validarPlaca(textFieldPlaca.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        buttonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double tiempotranscurrido = 0;
                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");

                Date firstHour = null;
                try {
                    firstHour = format2.parse(textFieldHoraIngre.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                Date secondHour = null;
                try {
                    secondHour = format2.parse(textSalida.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                assert firstHour != null;
                assert secondHour != null;
                if (firstHour.getTime() < secondHour.getTime()) {
                    int tempresta = (int) (secondHour.getTime() - firstHour.getTime());
                    tiempotranscurrido = Math.rint(((double) tempresta / 3600000) * 100) / 100;
                }
                double tarifa = Double.parseDouble(textTarifa.getText());

                double total = tiempotranscurrido*tarifa;

                if (!textFieldNombre.getText().isEmpty() && !textFieldMarca.getText().isEmpty()
                        && !textFieldModelo.getText().isEmpty() && !textFieldPlaca.getText().isEmpty()
                        && !textFieldFecha.getText().isEmpty() && !textFieldHoraIngre.getText().isEmpty()
                        && !textSalida.getText().isEmpty() && !textTarifa.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(),"Señor/a " + textFieldNombre.getText() + "\nCon vehiculo tipo: " + comboBoxTipo.getSelectedItem()
                            + "\nMarca: " + textFieldMarca.getText() + "\nModelo: " + textFieldModelo.getText() + "\nNumero de Placa: " + textFieldPlaca.getText()
                            + "\nIngreso el dia " + textFieldFecha.getText() + " a la hora " + textFieldHoraIngre.getText() + "\ny salio a la hora: " + textSalida.getText()
                            + "\nPor ende el valor total a pagar teniendo en cuenta la tarifa de " + textTarifa.getText() + " pesos es de: " + total + " COP");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),"Porfavor rellene todos los espacios");
                }
            }
        });
        textSalida.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (validarHora(textSalida.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        textFieldMarca.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!validarCosas(textFieldMarca.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        textFieldModelo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!validarCosas(textFieldModelo.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        textTarifa.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!validarTarifa(textTarifa.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
    }

    private static boolean isValidFormat(String date) {
        Date myDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            myDate = format.parse(date);
            if (!date.equals(format.format(myDate))) {
                myDate = null;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage());
        }
        if (myDate == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validarNombre(String nombre) {
        return nombre.matches("^([A-Z]{1}[a-z]+[ ]?){1,2}$");
    }//{}[]()

    public static boolean validarPlaca(String placa) {
        return placa.matches("^[A-Za-z]{3}[0-9]{3}$");
    }
    public static boolean validarCosas(String marca) {
        return marca.matches("^([A-Za-z]+[ ]?){1,2}$");
    }
    public static boolean validarTarifa(String tarifa) {
        return tarifa.matches("^([1-9]{1}[0-9]+[ ]?){1,2}$");
    }

    public static boolean validarHora(String hora) {
        Date myTime = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            myTime = format.parse(hora);
            if (!hora.equals(format.format(myTime))) {
                myTime = null;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage());
        }
        return myTime == null;
    }
}
