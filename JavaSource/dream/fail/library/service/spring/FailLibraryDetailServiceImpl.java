package dream.fail.library.service.spring;

import common.bean.User;
import dream.fail.library.dao.FailLibraryDetailDAO;
import dream.fail.library.dto.FailLibraryDetailDTO;
import dream.fail.library.service.FailLibraryDetailService;

/**
 * 고장library - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="failLibraryDetailServiceTarget"
 * @spring.txbn id="failLibraryDetailService"
 * @spring.property name="failLibraryDetailDAO" ref="failLibraryDetailDAO"
 */
public class FailLibraryDetailServiceImpl implements FailLibraryDetailService
{
    private FailLibraryDetailDAO failLibraryDetailDAO = null;
    
    public FailLibraryDetailDAO getFailLibraryDetailDAO() 
    {
		return failLibraryDetailDAO;
	}

	public void setFailLibraryDetailDAO(FailLibraryDetailDAO failLibraryDetailDAO) 
	{
		this.failLibraryDetailDAO = failLibraryDetailDAO;
	}

	public FailLibraryDetailDTO findDetail(User user, String failureId)throws Exception
    {
        return failLibraryDetailDAO.findDetail(user, failureId);
    }
    
	public int insertDetail(FailLibraryDetailDTO failLibraryDetailDTO) throws Exception
    {        
        return failLibraryDetailDAO.insertDetail(failLibraryDetailDTO);
    }
	
	public int updateDetail(FailLibraryDetailDTO failLibraryDetailDTO) throws Exception
    {        
        return failLibraryDetailDAO.updateDetail(failLibraryDetailDTO);
    }
}
