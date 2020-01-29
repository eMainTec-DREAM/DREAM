package dream.edu.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.edu.list.dao.EduDetailDAO;
import dream.edu.list.dao.EduListDAO;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.service.EduListService;

/**
 * 자격증분류 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="eduListServiceTarget"
 * @spring.txbn id="eduListService"
 * @spring.property name="eduListDAO" ref="eduListDAO"
 * @spring.property name="eduDetailDAO" ref="eduDetailDAO"
 */
public class EduListServiceImpl implements EduListService
{
    private EduListDAO eduListDAO = null;
    private EduDetailDAO eduDetailDAO = null;

    public EduDetailDAO getEduDetailDAO()
    {
        return eduDetailDAO;
    }

    public void setEduDetailDAO(EduDetailDAO eduDetailDAO)
    {
        this.eduDetailDAO = eduDetailDAO;
    }

    public EduListDAO getEduListDAO() 
    {
        return eduListDAO;
    }

    public void setEduListDAO(EduListDAO eduListDAO) 
    {
        this.eduListDAO = eduListDAO;
    }

    public List findList(EduCommonDTO eduCommonDTO, User user)
    {      
        return eduListDAO.findList(eduCommonDTO,user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        String courseListId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
        	courseListId = deleteRows[i];


                result = result + eduListDAO.deleteList(compNo, courseListId);

        }
        
        return result;
    }

	@Override
	public String findTotalCount(EduCommonDTO eduCommonDTO, User user) 
	{
		return eduListDAO.findTotalCount(eduCommonDTO, user);
	}

}