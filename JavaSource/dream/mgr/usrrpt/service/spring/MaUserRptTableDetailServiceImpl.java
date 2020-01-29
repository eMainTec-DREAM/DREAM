package dream.mgr.usrrpt.service.spring;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptTableDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptTableDetailDTO;
import dream.mgr.usrrpt.service.MaUserRptTableDetailService;

/**
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptTableDetailServiceTarget"
 * @spring.txbn id="maUserRptTableDetailService"
 * @spring.property name="maUserRptTableDetailDAO" ref="maUserRptTableDetailDAO"
 */
public class MaUserRptTableDetailServiceImpl implements MaUserRptTableDetailService
{
    private MaUserRptTableDetailDAO maUserRptTableDetailDAO = null;
    
    public MaUserRptTableDetailDAO getMaUserRptTableDetailDAO() 
    {
		return maUserRptTableDetailDAO;
	}

	public void setMaUserRptTableDetailDAO(MaUserRptTableDetailDAO maUserRptTableDetailDAO) 
	{
		this.maUserRptTableDetailDAO = maUserRptTableDetailDAO;
	}

	public MaUserRptTableDetailDTO findDetail(MaUserRptCommonDTO msPtMstrCommonDTO, User loginUser)throws Exception
    {
        return maUserRptTableDetailDAO.findDetail(msPtMstrCommonDTO, loginUser);
    }
    
	public int updateDetail(MaUserRptTableDetailDTO maUserRptTableDetailDTO, User loginUser) throws Exception
    {        
        return maUserRptTableDetailDAO.updateDetail(maUserRptTableDetailDTO, loginUser);
    }
	
	public int checkNextNum(String listId)
	{
		return maUserRptTableDetailDAO.checkNextNum(listId);
	}
	
	public int insertDetail(MaUserRptTableDetailDTO maUserRptTableDetailDTO, User loginUser) throws Exception
    {   
		int stepNum = maUserRptTableDetailDAO.checkNextNum(maUserRptTableDetailDTO.getUsrrptlistId() )+ 1;
		
		maUserRptTableDetailDTO.setStepNum(stepNum+"");
		maUserRptTableDetailDTO.setMainSubType(stepNum==1?"MAIN":"SUB");
		
        return maUserRptTableDetailDAO.insertDetail( maUserRptTableDetailDTO, loginUser);
    }
}
