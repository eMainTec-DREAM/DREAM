package dream.consult.program.table.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.program.table.dto.TableColValLovDTO;
/**
 * Å×ÀÌºí Column LOV - List Form
 * @author kim21017
 * @version $Id: TableColValLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="tableColValLovForm"
 * */

public class TableColValLovForm extends MaFinderAcForm{
	
	private TableColValLovDTO tableColValLovDTO = new TableColValLovDTO();

	public TableColValLovDTO getTableColValLovDTO() {
		return tableColValLovDTO;
	}

	public void setTableColValLovDTO(TableColValLovDTO tableColValLovDTO) {
		this.tableColValLovDTO = tableColValLovDTO;
	}

	
}
