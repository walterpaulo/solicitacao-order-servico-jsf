package br.edu.faculdadedelta.converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.StatusRetornoDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.StatusRetornoWalterPaulo;



@FacesConverter(value = "statusRetornoConverterWalterPaulo")
public class StatusRetornoConverterWalterPaulo implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, 
			UIComponent arg1, String valor) {
		
		if (valor != null) {
			StatusRetornoDAOWalterPaulo dao = new StatusRetornoDAOWalterPaulo();
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
			return String.valueOf(((StatusRetornoWalterPaulo)valor).getId());
		}
		return null;
	}

}
