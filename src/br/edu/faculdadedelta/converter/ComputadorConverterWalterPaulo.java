package br.edu.faculdadedelta.converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.ComputadorDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.ComputadorWalterPaulo;


@FacesConverter(value = "computadorConverterWalterPaulo")
public class ComputadorConverterWalterPaulo implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, 
			UIComponent arg1, String valor) {
		
		if (valor != null) {
			ComputadorDAOWalterPaulo dao = new ComputadorDAOWalterPaulo();
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, 
			UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((ComputadorWalterPaulo)valor).getId());
		}
		return null;
	}

}
