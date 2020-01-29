package dream.tool.rpt.renthist.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���ⱸ�뿩���� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPttRentCommonDTO extends BaseDTO
{	
	/** ����-â��Id */
	private String filterWcodeId        = "";
	private String filterWname      	= "";

	/** ����-ǰ��/�԰� */
	private String filterPartNameSize   = "";
	
	/** ����-���ɺμ� */
	private String filterDeptId      	= "";
	private String filterDeptDesc      	= "";

	/** ����-������ */
	private String filterRecBy      	= "";
	private String filterRecName      	= "";
	 /** �ݳ� ���� �� ������  */
    private String reqIdx = "";
    
	public String getReqIdx() {
		return reqIdx;
	}
	public void setReqIdx(String reqIdx) {
		this.reqIdx = reqIdx;
	}
	public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterWname() {
		return filterWname;
	}
	public void setFilterWname(String filterWname) {
		this.filterWname = filterWname;
	}
	public String getFilterPartNameSize() {
		return filterPartNameSize;
	}
	public void setFilterPartNameSize(String filterPartNameSize) {
		this.filterPartNameSize = filterPartNameSize;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterRecBy() {
		return filterRecBy;
	}
	public void setFilterRecBy(String filterRecBy) {
		this.filterRecBy = filterRecBy;
	}
	public String getFilterRecName() {
		return filterRecName;
	}
	public void setFilterRecName(String filterRecName) {
		this.filterRecName = filterRecName;
	}
	
	
}
