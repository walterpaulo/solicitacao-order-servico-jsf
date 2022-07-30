package br.edu.faculdadedelta.converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.AlunoDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.AlunoWalterPaulo;


@FacesConverter(value = "alunoConverterWalterPaulo")
public class AlunoConverterWalterPaulo implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, 
			UIComponent arg1, String valor) {
		
		if (valor != null) {
			AlunoDAOWalterPaulo dao = new AlunoDAOWalterPaulo();
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
			return String.valueOf(((AlunoWalterPaulo)valor).getId());
		}
		return null;
	}

}
