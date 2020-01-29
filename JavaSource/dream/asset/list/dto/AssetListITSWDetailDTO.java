package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Detail DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class AssetListITSWDetailDTO extends BaseDTO
{ 
	/**Key ��� ID */ 
    private String itEqId			= "";
    /**Key ��ġ SW ID */ 
    private String eqItInstSwId     = "";
    /** S/W���� ID */ 
    private String swCategoryId     = "";
    /** S/W���� DESC */ 
    private String swCategory	    = "";
    /** S/W�� */ 
    private String swName		    = "";
    /** S/W Version */ 
    private String swVer		    = "";
    /** ��ġ���� */ 
    private String installDate		= "";
    /** ��� */ 
    private String remark			= "";

	public String getSwCategory() {
		return swCategory;
	}

	public void setSwCategory(String swCategory) {
		this.swCategory = swCategory;
	}

	public String getSwCategoryId() {
		return swCategoryId;
	}

	public void setSwCategoryId(String swCategoryId) {
		this.swCategoryId = swCategoryId;
	}

	public String getSwName() {
		return swName;
	}

	public void setSwName(String swName) {
		this.swName = swName;
	}

	public String getSwVer() {
		return swVer;
	}

	public void setSwVer(String swVer) {
		this.swVer = swVer;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = CommonUtil.convertDate(installDate);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEqItInstSwId() {
		return eqItInstSwId;
	}

	public String getItEqId() {
		return itEqId;
	}

	public void setItEqId(String itEqId) {
		this.itEqId = itEqId;
	}

	public void setEqItInstSwId(String eqItInstSwId) {
		this.eqItInstSwId = eqItInstSwId;
		super.setAuditKey(eqItInstSwId);
	}
}
