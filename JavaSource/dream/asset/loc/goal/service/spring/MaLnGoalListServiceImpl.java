package dream.asset.loc.goal.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.loc.goal.dao.MaLnGoalListDAO;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;
import dream.asset.loc.goal.service.MaLnGoalListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maLnGoalListServiceTarget"
 * @spring.txbn id="maLnGoalListService"
 * @spring.property name="maLnGoalListDAO" ref="maLnGoalListDAO"
 */
public class MaLnGoalListServiceImpl implements MaLnGoalListService
{
    private MaLnGoalListDAO maLnGoalListDAO = null;

    public MaLnGoalListDAO getMaLnGoalListDAO() 
    {
		return maLnGoalListDAO;
	}

	public void setMaLnGoalListDAO(MaLnGoalListDAO maLnGoalListDAO) 
	{
		this.maLnGoalListDAO = maLnGoalListDAO;
	}

	public List findList(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser)
    {      
        return maLnGoalListDAO.findList(maLnGoalCommonDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            { 
                result = result + maLnGoalListDAO.deleteHeader(id, loginUser); 
            }
        }
        return result;
    }
}

