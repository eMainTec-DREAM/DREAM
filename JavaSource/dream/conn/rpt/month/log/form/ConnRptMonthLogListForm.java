package dream.conn.rpt.month.log.form;

import common.struts.BaseForm;
import dream.conn.rpt.month.log.dto.ConnRptMonthLogListDTO;

/**
 * 월별접속현황 FORM
 * @author  sy.yang
 * @version $Id: ConnRptMonthLogListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="connRptMonthLogListForm"
 */
public class ConnRptMonthLogListForm extends BaseForm
{    
    private ConnRptMonthLogListDTO connRptMonthLogListDTO = new ConnRptMonthLogListDTO();

	public ConnRptMonthLogListDTO getConnRptMonthLogListDTO() {
		return connRptMonthLogListDTO;
	}

	public void setConnRptMonthLogListDTO(ConnRptMonthLogListDTO connRptMonthLogListDTO) {
		this.connRptMonthLogListDTO = connRptMonthLogListDTO;
	}

}
