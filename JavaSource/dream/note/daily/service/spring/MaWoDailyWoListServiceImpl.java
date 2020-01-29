package dream.note.daily.service.spring;

import java.util.List;

import common.bean.User;
import dream.note.daily.dao.MaWoDailyWoListDAO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.note.daily.service.MaWoDailyWoListService;

/**
 * 작업 목록 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maWoDailyWoListServiceTarget"
 * @spring.txbn id="maWoDailyWoListService"
 * @spring.property name="maWoDailyWoListDAO" ref="maWoDailyWoListDAO"
 */
public class MaWoDailyWoListServiceImpl implements MaWoDailyWoListService
{
    private MaWoDailyWoListDAO maWoDailyWoListDAO = null;

    public MaWoDailyWoListDAO getMaWoDailyWoListDAO() 
    {
        return maWoDailyWoListDAO;
    }

    public void setMaWoDailyWoListDAO(MaWoDailyWoListDAO maWoDailyWoListDAO) 
    {
        this.maWoDailyWoListDAO = maWoDailyWoListDAO;
    }

    public List findList(MaWoDailyDetailDTO maWoDailyDetailDTO, User user)
    {      
        return maWoDailyWoListDAO.findList(maWoDailyDetailDTO, user);
    }

	@Override
	public String findTotalCount(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception {
		return maWoDailyWoListDAO.findTotalCount(maWoDailyDetailDTO, user);
	}

}