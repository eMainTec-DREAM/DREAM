package dream.work.rpt.mabdpoint.service.spring;

import java.util.List;

import org.apache.poi.util.SystemOutLogger;

import common.bean.User;
import dream.work.rpt.mabdpoint.dao.MaBdPointDetailDAO;
import dream.work.rpt.mabdpoint.dao.MaBdPointListDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.service.MaBdPointListService;

/**
 * 이상점검조치 - 목록 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maBdPointListServiceTarget"
 * @spring.txbn id="maBdPointListService"
 * @spring.property name="maBdPointListDAO" ref="maBdPointListDAO"
 * @spring.property name="maBdPointDetailDAO" ref="maBdPointDetailDAO"
 */
public class MaBdPointListServiceImpl implements MaBdPointListService
{
    private MaBdPointListDAO maBdPointListDAO = null;
    private MaBdPointDetailDAO maBdPointDetailDAO = null;

    public MaBdPointDetailDAO getMaBdPointDetailDAO()
    {
        return maBdPointDetailDAO;
    }

    public void setMaBdPointDetailDAO(MaBdPointDetailDAO maBdPointDetailDAO)
    {
        this.maBdPointDetailDAO = maBdPointDetailDAO;
    }

    public MaBdPointListDAO getMaBdPointListDAO() 
    {
        return maBdPointListDAO;
    }

    public void setMaBdPointListDAO(MaBdPointListDAO maBdPointListDAO) 
    {
        this.maBdPointListDAO = maBdPointListDAO;
    }

    public List findList(MaBdPointCommonDTO maBdPointCommonDTO, User user)
    {      
        return maBdPointListDAO.findList(maBdPointCommonDTO, user);
    }
    
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, User user)
    {      
    	return maBdPointListDAO.findTotalCount(maBdPointCommonDTO, user);
    }

}