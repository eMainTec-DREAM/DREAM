package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * ǥ���׸��� �˾� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovStdPointListDTO extends BaseDTO
{
    /** �����׸� */
    private String pointDesc   = "";
    /** PM ID */
    private String pmId 		= "";
    
    private String stWrkPointId   ="";

    public String getStWrkPointId() {
		return stWrkPointId;
	}
	public void setStWrkPointId(String stWrkPointId) {
		this.stWrkPointId = stWrkPointId;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPointDesc() {
		return pointDesc;
	}
	public void setPointDesc(String pointDesc) {
		this.pointDesc = pointDesc;
	}
}
