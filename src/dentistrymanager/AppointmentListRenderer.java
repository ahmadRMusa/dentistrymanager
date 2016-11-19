package dentistrymanager;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


@SuppressWarnings("serial")
public class AppointmentListRenderer extends JLabel implements ListCellRenderer<Appointment> {
	
	public AppointmentListRenderer() {
		setOpaque(true);
	}
	
	public Component getListCellRendererComponent(JList<? extends Appointment> list, Appointment appointment, int index, 
																			boolean isSelected, boolean cellHasFocus) {
		try(Connection connection = DBConnect.getConnection(false)){
			Patient patient = Patient.getPatientByID(connection, appointment.getPatientID());
		}
		
		
		setText(patient.getForename() + " " + patient.getSurname() + " " + appointment.getStartTime());
        Color background;
        Color foreground;

        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            background = Color.BLUE;
            foreground = Color.WHITE;

        // check if this cell is selected
        } else if (isSelected) {
            background = Color.RED;
            foreground = Color.WHITE;

        // unselected, and not the DnD drop location
        } else {
            background = Color.WHITE;
            foreground = Color.BLACK;
        };

        setBackground(background);
        setForeground(foreground);
		return this;
	}

}