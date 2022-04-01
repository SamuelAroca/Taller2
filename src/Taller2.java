//By Samuel Aroca
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

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
    private JLabel labelTipoPersona;
    private JComboBox comboBox1;

    public Taller2() {
        setTitle("Taller 2 By Samuel Aroca");
        setContentPane(mainPanel);
        //Todos los listeners
            //Fecha
        textFieldFecha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!isValidFormat(textFieldFecha.getText())) {
                    JOptionPane.showMessageDialog(new Frame(),"El formato no es válido");
                }
            }
        });
        //Hora de ingreso
        textFieldHoraIngre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (validarHora(textFieldHoraIngre.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        //Salir del programa
        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        //Nombre
        textFieldNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!validarNombre(textFieldNombre.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        //Placa
        textFieldPlaca.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (Objects.equals(comboBoxTipo.getSelectedItem(), "Carro")) {
                    if (!validarPlacaCarro(textFieldPlaca.getText())) {
                        JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                    }
                } else if (Objects.equals(comboBoxTipo.getSelectedItem(), "Moto")) {
                    if (!validarPlacaMoto(textFieldPlaca.getText())) {
                        JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                    }
                }
            }
        });
        //Boton Calcular la hora de diferencia y otras cosas
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

                if (Objects.equals(comboBox1.getSelectedItem(), "Preferencial")) {
                    double descuento = (total * 10)/100;
                    total -= descuento;
                }
                //Lo que se le muestra al usuario
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
        //Hora salida
        textSalida.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (validarHora(textSalida.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        //Marca
        textFieldMarca.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (validarCosas(textFieldMarca.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        //Modelo
        textFieldModelo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (validarCosas(textFieldModelo.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
        //Tarifa
        textTarifa.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                if (!validarTarifa(textTarifa.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(),"El formato no es valido");
                }
            }
        });
    }
    //Valida formato fecha
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
    //Valida nombre
    public static boolean validarNombre(String nombre) {
        return nombre.matches("^([A-Z]{1}[a-z]+[ ]?){1,2}$");
    }//{}[]()
    //Valida placa para carro
    public static boolean validarPlacaCarro(String placa) {
        return placa.matches("^[A-Za-z]{3}[0-9]{3}$");
    }
    //Valida placa para moto
    public static boolean validarPlacaMoto(String placa) {
        return placa.matches("^[A-Za-z]{3}[0-9]{2}[A-za-z]{1}$");
    }
    //Valida cosas
    public static boolean validarCosas(String marca) {
        return !marca.matches("^([A-Za-z]+[ ]?){1,2}$");
    }
    //valida la tarifa
    public static boolean validarTarifa(String tarifa) {
        return tarifa.matches("^([1-9]{1}[0-9]+[ ]?){1,2}$");
    }
    //Valida la hora
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
