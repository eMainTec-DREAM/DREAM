package dream.work.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import dream.work.list.dao.WoPlanListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.service.WoPlanListService;

/**
 * 작업계획목록 - 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="woPlanListServiceTarget"
 * @spring.txbn id="woPlanListService"
 * @spring.property name="woPlanListDAO" ref="woPlanListDAO"
 */
public class WoPlanListServiceImpl implements WoPlanListService
{
    private WoPlanListDAO woPlanListDAO = null;

    public WoPlanListDAO getWoPlanListDAO() {
		return woPlanListDAO;
	}

	public void setWoPlanListDAO(WoPlanListDAO woPlanListDAO) {
		this.woPlanListDAO = woPlanListDAO;
	}

	public List findWoResultMstrList(WoPlanCommonDTO woPlanCommonDTO, User user)
    {      
        return woPlanListDAO.findWoResultMstrList(woPlanCommonDTO,user);
    }
	public int deleteWoResultMstr(String[] deleteRows, User user) throws Exception{
	    int result = 0;
        if(!deleteRows.equals(null) && deleteRows.length>0) {
            List list = new ArrayList();
            WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
            for(String id : deleteRows)
            {
                woPlanDetailDTO.setWkOrId(id);
                list.add(BeanUtils.cloneBean(woPlanDetailDTO));
            }
            result = woPlanListDAO.updateDeleteTagWoPlanMstr(list,user).length;
        }
        return result;
    }
	
    @Override
    public String findTotalCount( WoPlanCommonDTO woPlanCommonDTO, User user, String woType)
    {
        return woPlanListDAO.findTotalCount(woPlanCommonDTO,user,woType);
    }
}

