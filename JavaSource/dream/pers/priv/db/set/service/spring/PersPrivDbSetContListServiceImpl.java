package dream.pers.priv.db.set.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dao.PersPrivDbSetContDetailDAO;
import dream.pers.priv.db.set.dao.PersPrivDbSetContListDAO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;
import dream.pers.priv.db.set.service.PersPrivDbSetContDetailService;
import dream.pers.priv.db.set.service.PersPrivDbSetContListService;

/**
 * PersPrivDbSetCont Page - List Service implements
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContListServiceImpl.java,v 1.0 2018/08/03 11:26:40 nhkim8548 Exp $
 * @since 1.0
 * @spring.bean id="persPrivDbSetContListServiceTarget"
 * @spring.txbn id="persPrivDbSetContListService"
 * @spring.property name="persPrivDbSetContListDAO" ref="persPrivDbSetContListDAO"
 * @spring.property name="persPrivDbSetContDetailDAO" ref="persPrivDbSetContDetailDAO"
 * @spring.property name="persPrivDbSetContDetailService" ref="persPrivDbSetContDetailService"
 */
public class PersPrivDbSetContListServiceImpl implements PersPrivDbSetContListService
{
    private PersPrivDbSetContListDAO persPrivDbSetContListDAO = null;
    private PersPrivDbSetContDetailDAO persPrivDbSetContDetailDAO = null;

    private PersPrivDbSetContDetailService persPrivDbSetContDetailService = null;
    
    public PersPrivDbSetContDetailDAO getPersPrivDbSetContDetailDAO()
    {
        return persPrivDbSetContDetailDAO;
    }

    public void setPersPrivDbSetContDetailDAO(
            PersPrivDbSetContDetailDAO persPrivDbSetContDetailDAO)
    {
        this.persPrivDbSetContDetailDAO = persPrivDbSetContDetailDAO;
    }

    public PersPrivDbSetContDetailService getPersPrivDbSetContDetailService() {
		return persPrivDbSetContDetailService;
	}

	public void setPersPrivDbSetContDetailService(PersPrivDbSetContDetailService persPrivDbSetContDetailService) {
		this.persPrivDbSetContDetailService = persPrivDbSetContDetailService;
	}

	public List findList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception
    {      
        return persPrivDbSetContListDAO.findList(persPrivDbSetCommonDTO,persPrivDbSetContListDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + persPrivDbSetContListDAO.deleteList(id, user);
            }
        return result;
    }
    public int insertCntsList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user) throws Exception
    {
        int result = 0;
        String[] multiKey = persPrivDbSetCommonDTO.getMultiKey().split("\\+");
        
        for(int i=0; i<multiKey.length; i++) {
            persPrivDbSetContDetailDTO.setUsrDbCntId(multiKey[i]);
            persPrivDbSetContDetailDTO.setUsrDbMenuCntId(persPrivDbSetContDetailDAO.getNextSequence("SQAUSRDBMENUCNTS_ID"));
            persPrivDbSetContDetailDAO.insertDetail(persPrivDbSetCommonDTO, persPrivDbSetContDetailDTO, user);
        }
        return result;
    }

    public PersPrivDbSetContListDAO getPersPrivDbSetContListDAO() {
        return persPrivDbSetContListDAO;
    }

    public void setPersPrivDbSetContListDAO(PersPrivDbSetContListDAO persPrivDbSetContListDAO) {
        this.persPrivDbSetContListDAO = persPrivDbSetContListDAO;
    }    
    
    public String findTotalCount(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetScoreListDTO, User user)  throws Exception
    {
        return persPrivDbSetContListDAO.findTotalCount(persPrivDbSetCommonDTO, persPrivDbSetScoreListDTO, user);
    }
}
