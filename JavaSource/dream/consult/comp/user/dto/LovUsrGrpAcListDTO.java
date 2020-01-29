package dream.consult.comp.user.dto;

import common.bean.BaseDTO;
/**
 * LOV DTO
 * @author youngjoo38
 * @version $Id: LovUsrGrpAcListDTO.java,v 1.0 2017/09/12  16:08:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class LovUsrGrpAcListDTO extends BaseDTO
{
    /**   사용자 그룹 ID */
    private String usrGrpId			= "";
    /**   사용자 그룹 코드 */
    private String usrGrpNo		    = "";
	/**   그룹명 */
	private String usrGrpDesc			= "";
	
    public String getUsrGrpId()
    {
        return usrGrpId;
    }
    public void setUsrGrpId(String usrGrpId)
    {
        this.usrGrpId = usrGrpId;
    }
    public String getUsrGrpNo()
    {
        return usrGrpNo;
    }
    public void setUsrGrpNo(String usrGrpNo)
    {
        this.usrGrpNo = usrGrpNo;
    }
    public String getUsrGrpDesc()
    {
        return usrGrpDesc;
    }
    public void setUsrGrpDesc(String usrGrpDesc)
    {
        this.usrGrpDesc = usrGrpDesc;
    }
}
