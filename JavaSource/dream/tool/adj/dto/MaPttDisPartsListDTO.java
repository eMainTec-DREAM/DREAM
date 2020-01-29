package dream.tool.adj.dto;

import common.bean.BaseDTO;

/**
 * DTO
 * @author  kim21017
 * @version $Id: MaPttDisPartsListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPttDisPartsListDTO extends BaseDTO
{
    /** KEY ID */
    private String ptdisuselistId    = "";
    
    
	/** ´äº¯ID */
	private String ptdisuseitemId 	= "";

	
	

    public String getPtdisuselistId() {
		return ptdisuselistId;
	}

	public void setPtdisuselistId(String ptdisuselistId) {
		this.ptdisuselistId = ptdisuselistId;
	}

	public String getPtdisuseitemId() {
		return ptdisuseitemId;
	}

	public void setPtdisuseitemId(String ptdisuseitemId) {
		this.ptdisuseitemId = ptdisuseitemId;
		super.setAuditKey(ptdisuseitemId);
	}

	
	
}