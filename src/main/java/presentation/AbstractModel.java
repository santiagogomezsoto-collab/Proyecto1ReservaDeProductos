package presentation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Clase base para todos los "Model" de las pantallas.
 * La idea es que el Model avise solo cuando algo cambia (con firePropertyChange),
 * y la View se suscribe como listener para redibujarse. Asi el Controller no
 * tiene que andar llamando metodos de la View a mano.
 */
public abstract class AbstractModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }
}
