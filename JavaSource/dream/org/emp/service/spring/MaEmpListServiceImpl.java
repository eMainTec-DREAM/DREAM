package dream.org.emp.service.spring;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.org.emp.dao.MaEmpListDAO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.service.MaEmpListService;

/**
 * 사원 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maEmpListServiceTarget"
 * @spring.txbn id="maEmpListService"
 * @spring.property name="maEmpListDAO" ref="maEmpListDAO"
 */
public class MaEmpListServiceImpl implements MaEmpListService
{
    private MaEmpListDAO maEmpListDAO = null;

    public MaEmpListDAO getMaEmpListDAO() 
    {
		return maEmpListDAO;
	}

	public void setMaEmpListDAO(MaEmpListDAO maEmpListDAO) 
	{
		this.maEmpListDAO = maEmpListDAO;
	}

	public List findEmpList(MaEmpCommonDTO maEmpCommonDTO, User user) throws IOException
    {      
        return maEmpListDAO.findEmpList(maEmpCommonDTO, user);
    }

    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maEmpListDAO.deleteEmp(compNo, id);
            }
        
        return result;
    }

    @Override
    public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, User user)
    {
        return maEmpListDAO.findTotalCount(maEmpCommonDTO, user);
    }
}

