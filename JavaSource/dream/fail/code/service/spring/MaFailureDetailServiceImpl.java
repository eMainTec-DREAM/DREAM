package dream.fail.code.service.spring;

import common.bean.User;
import dream.fail.code.dao.MaFailureDetailDAO;
import dream.fail.code.dto.MaFailureDetailDTO;
import dream.fail.code.service.MaFailureDetailService;

/**
 * �����ڵ� - �� serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maFailureDetailServiceTarget"
 * @spring.txbn id="maFailureDetailService"
 * @spring.property name="maFailureDetailDAO" ref="maFailureDetailDAO"
 */
public class MaFailureDetailServiceImpl implements MaFailureDetailService
{
    private MaFailureDetailDAO maFailureDetailDAO = null;
    
    public MaFailureDetailDAO getMaFailureDetailDAO() 
    {
		return maFailureDetailDAO;
	}

	public void setMaFailureDetailDAO(MaFailureDetailDAO maFailureDetailDAO) 
	{
		this.maFailureDetailDAO = maFailureDetailDAO;
	}

	public MaFailureDetailDTO findDetail(User user, String failureId)throws Exception
    {
        return maFailureDetailDAO.findDetail(user, failureId);
    }
    
	public int insertDetail(MaFailureDetailDTO maFailureDetailDTO) throws Exception
    {        
		//TALANG Data �Է�
		maFailureDetailDAO.makeLangData(maFailureDetailDTO);
		//�����ڵ� Data �Է�
        return maFailureDetailDAO.insertDetail(maFailureDetailDTO);
    }
	
	public int updateDetail(MaFailureDetailDTO maFailureDetailDTO) throws Exception
    {        
		maFailureDetailDAO.updateLangData(maFailureDetailDTO);
		
        return maFailureDetailDAO.updateDetail(maFailureDetailDTO);
    }
	
	public String validFailureNo(MaFailureDetailDTO maFailureDetailDTO) throws Exception
    {
        return maFailureDetailDAO.validFailureNo(maFailureDetailDTO);
    }
}
