package dream.note.daily.service.spring;

import java.util.List;

import common.bean.User;
import dream.note.daily.dao.MaWoDailyActListDAO;
import dream.note.daily.dto.MaWoDailyActListDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.service.MaWoDailyActListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maWoDailyActListServiceTarget"
 * @spring.txbn id="maWoDailyActListService"
 * @spring.property name="maWoDailyActListDAO" ref="maWoDailyActListDAO"
 */
public class MaWoDailyActListServiceImpl implements MaWoDailyActListService
{
    private MaWoDailyActListDAO maWoDailyActListDAO = null;
    
    public MaWoDailyActListDAO getMaWoDailyActListDAO() {
		return maWoDailyActListDAO;
	}

	public void setMaWoDailyActListDAO(MaWoDailyActListDAO maWoDailyActListDAO) {
		this.maWoDailyActListDAO = maWoDailyActListDAO;
	}

	public List findList(MaWoDailyCommonDTO maWoDailyCommonDTO,MaWoDailyActListDTO maWoDailyActListDTO, User user)
    {      
        return maWoDailyActListDAO.findList(maWoDailyCommonDTO,maWoDailyActListDTO,user);
    }

    public int deleteList(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoDailyActListDAO.deleteList(id,compNo);
            }
        return result;
    }


}