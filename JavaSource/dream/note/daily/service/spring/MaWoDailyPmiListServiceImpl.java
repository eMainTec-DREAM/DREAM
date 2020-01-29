package dream.note.daily.service.spring;

import java.util.List;

import common.bean.User;
import dream.note.daily.dao.MaWoDailyPmiListDAO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.note.daily.service.MaWoDailyPmiListService;

/**
 * 작업 목록 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maWoDailyPmiListServiceTarget"
 * @spring.txbn id="maWoDailyPmiListService"
 * @spring.property name="maWoDailyPmiListDAO" ref="maWoDailyPmiListDAO"
 */
public class MaWoDailyPmiListServiceImpl implements MaWoDailyPmiListService
{
    private MaWoDailyPmiListDAO maWoDailyPmiListDAO = null;

    public MaWoDailyPmiListDAO getMaWoDailyPmiListDAO() 
    {
        return maWoDailyPmiListDAO;
    }

    public void setMaWoDailyPmiListDAO(MaWoDailyPmiListDAO maWoDailyPmiListDAO) 
    {
        this.maWoDailyPmiListDAO = maWoDailyPmiListDAO;
    }

    public List findList(MaWoDailyDetailDTO maWoDailyDetailDTO, User user)
    {      
        return maWoDailyPmiListDAO.findList(maWoDailyDetailDTO, user);
    }

	@Override
	public String findTotalCount(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception {
		return maWoDailyPmiListDAO.findTotalCount(maWoDailyDetailDTO, user);
	}

}