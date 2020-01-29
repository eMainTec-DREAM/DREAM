package dream.fail.library.service.spring;

import java.util.List;

import common.bean.User;
import dream.fail.library.dao.FailLibraryListDAO;
import dream.fail.library.dto.FailLibraryCommonDTO;
import dream.fail.library.service.FailLibraryListService;

/**
 * 고장library - 목록 serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="failLibraryListServiceTarget"
 * @spring.txbn id="failLibraryListService"
 * @spring.property name="failLibraryListDAO" ref="failLibraryListDAO"
 */
public class FailLibraryListServiceImpl implements FailLibraryListService
{
    private FailLibraryListDAO failLibraryListDAO = null;

    public FailLibraryListDAO getFailLibraryListDAO() 
    {
		return failLibraryListDAO;
	}

	public void setFailLibraryListDAO(FailLibraryListDAO failLibraryListDAO) 
	{
		this.failLibraryListDAO = failLibraryListDAO;
	}

	public List findList(FailLibraryCommonDTO failLibraryCommonDTO, User user)
    {      
        return failLibraryListDAO.findList(failLibraryCommonDTO,user);
    }

	public int deleteList(String compNo, String[] deleteRows) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + failLibraryListDAO.deleteParts(compNo, id);
            }
        
        return result;
    }
}

