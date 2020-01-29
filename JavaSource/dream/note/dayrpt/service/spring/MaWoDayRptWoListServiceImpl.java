package dream.note.dayrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.note.dayrpt.dao.MaWoDayRptWoListDAO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;
import dream.note.dayrpt.service.MaWoDayRptWoListService;

/**
 * 작업 목록 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maWoDayRptWoListServiceTarget"
 * @spring.txbn id="maWoDayRptWoListService"
 * @spring.property name="maWoDayRptWoListDAO" ref="maWoDayRptWoListDAO"
 */
public class MaWoDayRptWoListServiceImpl implements MaWoDayRptWoListService
{
    private MaWoDayRptWoListDAO maWoDayRptWoListDAO = null;

    public MaWoDayRptWoListDAO getMaWoDayRptWoListDAO() 
    {
        return maWoDayRptWoListDAO;
    }

    public void setMaWoDayRptWoListDAO(MaWoDayRptWoListDAO maWoDayRptWoListDAO) 
    {
        this.maWoDayRptWoListDAO = maWoDayRptWoListDAO;
    }

    public List findList(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User user)
    {      
        return maWoDayRptWoListDAO.findList(maWoDayRptDetailDTO, user);
    }

}