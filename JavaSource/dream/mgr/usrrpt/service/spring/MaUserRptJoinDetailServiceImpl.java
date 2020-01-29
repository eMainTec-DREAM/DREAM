package dream.mgr.usrrpt.service.spring;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptJoinDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptJoinDetailDTO;
import dream.mgr.usrrpt.service.MaUserRptJoinDetailService;

/**
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptJoinDetailServiceTarget"
 * @spring.txbn id="maUserRptJoinDetailService"
 * @spring.property name="maUserRptJoinDetailDAO" ref="maUserRptJoinDetailDAO"
 */
public class MaUserRptJoinDetailServiceImpl implements MaUserRptJoinDetailService
{
    private MaUserRptJoinDetailDAO maUserRptJoinDetailDAO = null;
    
    public MaUserRptJoinDetailDAO getMaUserRptJoinDetailDAO() 
    {
		return maUserRptJoinDetailDAO;
	}

	public void setMaUserRptJoinDetailDAO(MaUserRptJoinDetailDAO maUserRptJoinDetailDAO) 
	{
		this.maUserRptJoinDetailDAO = maUserRptJoinDetailDAO;
	}

	public MaUserRptJoinDetailDTO findDetail(MaUserRptCommonDTO msPtMstrCommonDTO, User loginUser)throws Exception
    {
        return maUserRptJoinDetailDAO.findDetail(msPtMstrCommonDTO, loginUser);
    }
    
	public int updateDetail(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO, User loginUser) throws Exception
    {        
        return maUserRptJoinDetailDAO.updateDetail(maUserRptJoinDetailDTO, loginUser);
    }
	
	public int insertDetail(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO, User loginUser) throws Exception
    {   
		int stepNum = maUserRptJoinDetailDAO.checkNextNum(maUserRptJoinDetailDTO)+ 1;
		
		maUserRptJoinDetailDTO.setStepNum(stepNum+"");
        return maUserRptJoinDetailDAO.insertDetail( maUserRptJoinDetailDTO, loginUser);
    }
}
