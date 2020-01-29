package dream.consult.program.linkedfunc.dto;

import common.bean.BaseDTO;

/**
 * PgmLinkedFunc Page - ���� DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class ConsultPgmLinkedFuncCommonDTO extends BaseDTO
{
    /** Linked Function ID*/
    private String linkedFuncId            = "";
    
    /**Filter Linked Function ��*/
    private String filterLinkedFuncDesc    = "";
    
	public String getLinkedFuncId() {
		return linkedFuncId;
	}
	public void setLinkedFuncId(String linkedFuncId) {
		this.linkedFuncId = linkedFuncId;
	}
	public String getFilterLinkedFuncDesc() {
		return filterLinkedFuncDesc;
	}
	public void setFilterLinkedFuncDesc(String filterLinkedFuncDesc) {
		this.filterLinkedFuncDesc = filterLinkedFuncDesc;
	}    
}
