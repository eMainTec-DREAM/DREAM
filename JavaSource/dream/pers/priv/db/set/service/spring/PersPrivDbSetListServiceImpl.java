package dream.pers.priv.db.set.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dao.PersPrivDbSetListDAO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.service.PersPrivDbSetListService;

/**
 * Guide Page - List Service implements
 * @author nhkim8548
 * @version $Id: PersPrivDbSetListServiceImpl.java,v 1.0 2018/08/01 15:56:40 nhkim8548 Exp $
 * @since 1.0
 * @spring.bean id="persPrivDbSetListServiceTarget"
 * @spring.txbn id="persPrivDbSetListService"
 * @spring.property name="persPrivDbSetListDAO" ref="persPrivDbSetListDAO"
 */
public class PersPrivDbSetListServiceImpl implements PersPrivDbSetListService
{
	private PersPrivDbSetListDAO persPrivDbSetListDAO = null;

	public List findList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception
    {      
        return persPrivDbSetListDAO.findList(persPrivDbSetCommonDTO,user);
    }

	public int deleteList(String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + persPrivDbSetListDAO.deleteList(id, user);
            }
        return result;
    }

	public PersPrivDbSetListDAO getPersPrivDbSetListDAO() {
		return persPrivDbSetListDAO;
	}

	public void setPersPrivDbSetListDAO(PersPrivDbSetListDAO persPrivDbSetListDAO) {
		this.persPrivDbSetListDAO = persPrivDbSetListDAO;
	}
	public String findTotalCount(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,User user) throws Exception
    {
        return persPrivDbSetListDAO.findTotalCount(persPrivDbSetCommonDTO, user);
    }
}

