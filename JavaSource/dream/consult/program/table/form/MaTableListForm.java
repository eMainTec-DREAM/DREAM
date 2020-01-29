package dream.consult.program.table.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.consult.program.table.dto.MaTableCommonDTO;
import dream.consult.program.table.dto.MaTableDTOList;

/**
 * ������ ���̺� - ��� form
 * @author  kim21017
 * @version $Id: MaTableListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maTableListForm"
 */
public class MaTableListForm extends BaseForm
{    
    //===============================================================
    /** ������ ���̺� ���� */
    private MaTableCommonDTO maTableCommonDTO = new MaTableCommonDTO();
    /** �׸��� DTO */
    private List maTableDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(MaTableDTOList.class));
    
	public MaTableCommonDTO getMaTableCommonDTO() {
		return maTableCommonDTO;
	}

	public void setMaTableCommonDTO(MaTableCommonDTO maTableCommonDTO) {
		this.maTableCommonDTO = maTableCommonDTO;
	}

	public List getMaTableDTOList() {
		return maTableDTOList;
	}

	public void setMaTableDTOList(List maTableDTOList) {
		this.maTableDTOList = maTableDTOList;
	}
	
}
