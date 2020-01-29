package dream.mgr.tobeprocess.dto;

import common.bean.BaseDTO;
/**
 * ToBeProcess Page - 공통 DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrToBeProcessCommonDTO extends BaseDTO
{
	/**결재flow Id*/
    private String toBeProcessId    		= "";
	/**Filter To Be Process 명*/
    private String filterToBeProcessDesc    = "";
    
    private String fileName				    = "";
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getToBeProcessId() {
		return toBeProcessId;
	}
	public void setToBeProcessId(String toBeProcessId) {
		this.toBeProcessId = toBeProcessId;
	}
	public String getFilterToBeProcessDesc() {
		return filterToBeProcessDesc;
	}
	public void setFilterToBeProcessDesc(String filterToBeProcessDesc) {
		this.filterToBeProcessDesc = filterToBeProcessDesc;
	}
}
