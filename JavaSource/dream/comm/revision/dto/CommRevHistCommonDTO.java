package dream.comm.revision.dto;

import common.bean.BaseDTO;

/**
 * 제/개정 변경이력 DTO
 * @author  kim21017
 * @version $Id: CommRevHistListDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class CommRevHistCommonDTO extends BaseDTO
{
	/** objectID */
	private String objectId 		= "";
	/** objectNo */
	private String objectNo 		= "";
	/** revisionID */
	private String revisionhistId 	= "";
	/** revision Object Type */
	private String revisionObjType	= "";
	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	public String getRevisionhistId() {
		return revisionhistId;
	}
	public void setRevisionhistId(String revisionhistId) {
		this.revisionhistId = revisionhistId;
	}
	public String getRevisionObjType() {
		return revisionObjType;
	}
	public void setRevisionObjType(String revisionObjType) {
		this.revisionObjType = revisionObjType;
	}
	
	
}
