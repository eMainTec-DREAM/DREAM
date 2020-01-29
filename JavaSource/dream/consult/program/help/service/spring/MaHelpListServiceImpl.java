package dream.consult.program.help.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.help.dao.MaHelpListDAO;
import dream.consult.program.help.dto.MaHelpCommonDTO;
import dream.consult.program.help.service.MaHelpListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maHelpListServiceTarget"
 * @spring.txbn id="maHelpListService"
 * @spring.property name="maHelpListDAO" ref="maHelpListDAO"
 */
public class MaHelpListServiceImpl implements MaHelpListService
{
    private MaHelpListDAO maHelpListDAO = null;

    public MaHelpListDAO getMaHelpListDAO() 
    {
		return maHelpListDAO;
	}

	public void setMaHelpListDAO(MaHelpListDAO maHelpListDAO) 
	{
		this.maHelpListDAO = maHelpListDAO;
	}

	public List findHelpList(MaHelpCommonDTO maHelpCommonDTO, User user)
    {      
        return maHelpListDAO.findHelpList(maHelpCommonDTO, user);
    }

    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maHelpListDAO.deleteHelp(compNo, id);
            }
        
        return result;
    }

	public String findTotalCount(MaHelpCommonDTO maHelpCommonDTO, User user) throws Exception 
	{
		return maHelpListDAO.findTotalCount(maHelpCommonDTO, user);
	}
}

