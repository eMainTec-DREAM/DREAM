package dream.edu.list.service.spring;

import common.bean.User;
import dream.edu.list.dao.EduDetailDAO;
import dream.edu.list.dto.EduDetailDTO;
import dream.edu.list.service.EduDetailService;

/**
 * 자격증분류 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="eduDetailServiceTarget"
 * @spring.txbn id="eduDetailService"
 * @spring.property name="eduDetailDAO" ref="eduDetailDAO"
 */
public class EduDetailServiceImpl implements EduDetailService
{
    private EduDetailDAO eduDetailDAO = null;
    
    public EduDetailDAO getEduDetailDAO() 
    {
		return eduDetailDAO;
	}

	public void setEduDetailDAO(EduDetailDAO eduDetailDAO) 
	{
		this.eduDetailDAO = eduDetailDAO;
	}

	public EduDetailDTO findDetail(User user, String courseListId)throws Exception
    {
        EduDetailDTO eduDetailDTO = eduDetailDAO.findDetail(user, courseListId);
        
        return eduDetailDTO;
    }
	
	public int insertDetail(EduDetailDTO eduDetailDTO) throws Exception
    {   
        return eduDetailDAO.insertDetail(eduDetailDTO);
    }
	
	public int updateDetail(EduDetailDTO eduDetailDTO) throws Exception
    {   
	    int resultCnt = 0;
	    String compNo = eduDetailDTO.getCompNo();
	    String prRecListId = eduDetailDTO.getCourseListId();
	    

	    resultCnt = eduDetailDAO.updateDetail(eduDetailDTO);

	    
        return resultCnt;
    }
	

}
