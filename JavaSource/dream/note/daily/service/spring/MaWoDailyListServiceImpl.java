package dream.note.daily.service.spring;

import java.util.List;

import common.bean.User;
import dream.note.daily.dao.MaWoDailyListDAO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.service.MaWoDailyListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maWoDailyListServiceTarget"
 * @spring.txbn id="maWoDailyListService"
 * @spring.property name="maWoDailyListDAO" ref="maWoDailyListDAO"
 */
public class MaWoDailyListServiceImpl implements MaWoDailyListService
{
    private MaWoDailyListDAO maWoDailyListDAO = null;

    public MaWoDailyListDAO getMaWoDailyListDAO() 
    {
        return maWoDailyListDAO;
    }

    public void setMaWoDailyListDAO(MaWoDailyListDAO maWoDailyListDAO) 
    {
        this.maWoDailyListDAO = maWoDailyListDAO;
    }

    public List findList(MaWoDailyCommonDTO maWoDailyCommonDTO, User user)
    {      
        return maWoDailyListDAO.findList(maWoDailyCommonDTO,user);
    }

    public int deleteList(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoDailyListDAO.deleteList(id,compNo);
                result = result + maWoDailyListDAO.deleteActList(id,compNo);
            }
        return result;
    }

	@Override
	public String findTotalCount(MaWoDailyCommonDTO maWoDailyCommonDTO, User user) throws Exception {
		return maWoDailyListDAO.findTotalCount(maWoDailyCommonDTO, user);
	}


}