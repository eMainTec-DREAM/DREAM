package dream.note.dayrpt.service.spring;

import common.bean.User;
import dream.note.dayrpt.dao.MaWoDayRptDetailDAO;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;
import dream.note.dayrpt.service.MaWoDayRptDetailService;

/**
 * 업무일지 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoDayRptDetailServiceTarget"
 * @spring.txbn id="maWoDayRptDetailService"
 * @spring.property name="maWoDayRptDetailDAO" ref="maWoDayRptDetailDAO"
 */
public class MaWoDayRptDetailServiceImpl implements MaWoDayRptDetailService
{
    private MaWoDayRptDetailDAO maWoDayRptDetailDAO = null;
    
    public MaWoDayRptDetailDAO getMaWoDayRptDetailDAO() 
    {
		return maWoDayRptDetailDAO;
	}

	public void setMaWoDayRptDetailDAO(MaWoDayRptDetailDAO maWoDayRptDetailDAO) 
	{
		this.maWoDayRptDetailDAO = maWoDayRptDetailDAO;
	}

	public MaWoDayRptDetailDTO findDetail(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User loginUser)throws Exception
    {
        return maWoDayRptDetailDAO.findDetail(maWoDayRptCommonDTO, loginUser);
    }
	
	public int updateDetail(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User user) throws Exception
    {   
	    int resultCnt = 0;
	    resultCnt = maWoDayRptDetailDAO.updateDetail(maWoDayRptDetailDTO, user);
        return resultCnt;
    }
	public int insertDetail(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User user) throws Exception
	{   
		int resultCnt = 0;
		resultCnt = maWoDayRptDetailDAO.insertDetail(maWoDayRptDetailDTO, user);
		return resultCnt;
	}
}
