package dream.note.dayrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.note.dayrpt.dao.MaWoDayRptListDAO;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.service.MaWoDayRptListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maWoDayRptListServiceTarget"
 * @spring.txbn id="maWoDayRptListService"
 * @spring.property name="maWoDayRptListDAO" ref="maWoDayRptListDAO"
 */
public class MaWoDayRptListServiceImpl implements MaWoDayRptListService
{
    private MaWoDayRptListDAO maWoDayRptListDAO = null;

    public MaWoDayRptListDAO getMaWoDayRptListDAO() 
    {
        return maWoDayRptListDAO;
    }

    public void setMaWoDayRptListDAO(MaWoDayRptListDAO maWoDayRptListDAO) 
    {
        this.maWoDayRptListDAO = maWoDayRptListDAO;
    }

    public List findList(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User user)
    {      
        return maWoDayRptListDAO.findList(maWoDayRptCommonDTO,user);
    }

    public int deleteList(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoDayRptListDAO.deleteList(id,compNo);
            }
        return result;
    }

    @Override
    public String findTotalCount(MaWoDayRptCommonDTO maWoDayRptCommonDTO,
            User user)
    {
        // TODO Auto-generated method stub
        return maWoDayRptListDAO.findTotalCount(maWoDayRptCommonDTO,user);
    }


}