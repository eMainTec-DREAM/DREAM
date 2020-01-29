package dream.pers.priv.db.set.service.spring;

import common.bean.User;
import dream.pers.priv.db.set.dao.PersPrivDbSetContDetailDAO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;
import dream.pers.priv.db.set.service.PersPrivDbSetContDetailService;

/**
 * 대시보드(Contents) - Detail Service implements
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContDetailServiceImpl.java,v 1.0 2018/08/03 11:31:40 nhkim8548 Exp $
 * @since 1.0
 * @spring.bean id="persPrivDbSetContDetailServiceTarget"
 * @spring.txbn id="persPrivDbSetContDetailService"
 * @spring.property name="persPrivDbSetContDetailDAO" ref="persPrivDbSetContDetailDAO"
 */
public class PersPrivDbSetContDetailServiceImpl implements PersPrivDbSetContDetailService
{
    private PersPrivDbSetContDetailDAO persPrivDbSetContDetailDAO = null;
    
    public PersPrivDbSetContDetailDTO findDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception
    {
    	return persPrivDbSetContDetailDAO.findDetail(persPrivDbSetCommonDTO,persPrivDbSetContListDTO, user);
    }
    
    public int insertDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user) throws Exception
    {
     	return persPrivDbSetContDetailDAO.insertDetail(persPrivDbSetCommonDTO,persPrivDbSetContDetailDTO, user);
    }
    
    public int updateDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user) throws Exception
    {
     	return persPrivDbSetContDetailDAO.updateDetail(persPrivDbSetCommonDTO,persPrivDbSetContDetailDTO, user);
    }

	public PersPrivDbSetContDetailDAO getPersPrivDbSetContDetailDAO() {
		return persPrivDbSetContDetailDAO;
	}

	public void setPersPrivDbSetContDetailDAO(PersPrivDbSetContDetailDAO persPrivDbSetContDetailDAO) {
		this.persPrivDbSetContDetailDAO = persPrivDbSetContDetailDAO;
	}
}
