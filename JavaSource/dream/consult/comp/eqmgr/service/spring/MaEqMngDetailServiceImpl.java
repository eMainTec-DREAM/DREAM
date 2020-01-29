package dream.consult.comp.eqmgr.service.spring;

import dream.consult.comp.eqmgr.dao.MaEqMngDetailDAO;
import dream.consult.comp.eqmgr.dto.MaEqMngDetailDTO;
import dream.consult.comp.eqmgr.service.MaEqMngDetailService;

/**
 * 설비담당자변경 - 상세 serviceimpl 
 * @author  jung7126
 * @version $Id: MaEqMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMngDetailServiceTarget"
 * @spring.txbn id="maEqMngDetailService"
 * @spring.property name="maEqMngDetailDAO" ref="maEqMngDetailDAO"
 */
public class MaEqMngDetailServiceImpl implements MaEqMngDetailService
{
    private MaEqMngDetailDAO maEqMngDetailDAO = null;
    
	public MaEqMngDetailDAO getMaEqMngDetailDAO()
    {
        return maEqMngDetailDAO;
    }

    public void setMaEqMngDetailDAO(MaEqMngDetailDAO maEqMngDetailDAO)
    {
        this.maEqMngDetailDAO = maEqMngDetailDAO;
    }

    public int updateEqMng(MaEqMngDetailDTO maEqMngDetailDTO) throws Exception
    {
    	int rtnCnt = 0;
		if(maEqMngDetailDTO.getMainMngId() != "" )
		{
			int rtn = this.maEqMngDetailDAO.updateMainManager(maEqMngDetailDTO);
			rtnCnt = rtnCnt + rtn;
		}
		else if(maEqMngDetailDTO.getSubMngId() != "")
		{
			int rtn = this.maEqMngDetailDAO.updateSubManager(maEqMngDetailDTO);
			rtnCnt = rtnCnt + rtn;
		}
        return rtnCnt;
    }
}
