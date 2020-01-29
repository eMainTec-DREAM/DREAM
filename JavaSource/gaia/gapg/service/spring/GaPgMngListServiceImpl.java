package gaia.gapg.service.spring;

import java.util.List;

import gaia.gapg.dao.GaPgMngListDAO;
import gaia.gapg.dto.GaPgMngCommonDTO;
import gaia.gapg.service.GaPgMngListService;


/**
 * 화면 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: GaPgMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="gaPgMngListServiceTarget"
 * @spring.txbn id="gaPgMngListService"
 * @spring.property name="gaPgMngListDAO" ref="gaPgMngListDAO"
 */
public class GaPgMngListServiceImpl implements GaPgMngListService
{
    private GaPgMngListDAO gaPgMngListDAO = null;

    public GaPgMngListDAO getGaPgMngListDAO() {
		return gaPgMngListDAO;
	}

	public void setGaPgMngListDAO(GaPgMngListDAO gaPgMngListDAO) {
		this.gaPgMngListDAO = gaPgMngListDAO;
	}

	public List findPgList(GaPgMngCommonDTO gaPgMngCommonDTO)
    {      
        return gaPgMngListDAO.findPgList(gaPgMngCommonDTO);
    }
	
	public int deletePage(String[] deleteRows) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + gaPgMngListDAO.deletePage(id);
            }
        return result;
    }
}

