package dream.consult.program.table.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.consult.program.table.dto.ListTypeCodeDTOList;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * ������ ���̺� detail - code ���
 * @author  kim21017
 * @version $Id: MaTableColListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maTableColListForm"
 */
public class MaTableColListForm extends BaseForm
{    
    //===============================================================
    /** ������ ���̺� ���� */
    private MaTableCommonDTO maTableCommonDTO = new MaTableCommonDTO();
    /** ������ ���̺� detail-code  */
    private MaTableColListDTO maTableColListDTO = new MaTableColListDTO();
    /** �׸��� DTO */
    private List listTypeCodeDTOList = ListUtils.lazyList(new ArrayList(), getDtoFactory(ListTypeCodeDTOList.class));
	
    
    public List getListTypeCodeDTOList() {
		return listTypeCodeDTOList;
	}

	public void setListTypeCodeDTOList(List listTypeCodeDTOList) {
		this.listTypeCodeDTOList = listTypeCodeDTOList;
	}

	public MaTableCommonDTO getMaTableCommonDTO() {
		return maTableCommonDTO;
	}

	public void setMaTableCommonDTO(MaTableCommonDTO maTableCommonDTO) {
		this.maTableCommonDTO = maTableCommonDTO;
	}

	public MaTableColListDTO getMaTableColListDTO() {
		return maTableColListDTO;
	}

	public void setMaTableColListDTO(MaTableColListDTO maTableColListDTO) {
		this.maTableColListDTO = maTableColListDTO;
	}
}
