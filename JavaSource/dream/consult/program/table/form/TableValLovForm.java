package dream.consult.program.table.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.program.table.dto.TableValLovDTO;
/**
 * Å×ÀÌºí LOV - List Form
 * @author kim21017
 * @version $Id: TableValLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="tableValLovForm"
 * */

public class TableValLovForm extends MaFinderAcForm{
	
	private TableValLovDTO tableValLovDTO = new TableValLovDTO();

	public TableValLovDTO getTableValLovDTO() {
		return tableValLovDTO;
	}

	public void setTableValLovDTO(TableValLovDTO tableValLovDTO) {
		this.tableValLovDTO = tableValLovDTO;
	}

	
}
