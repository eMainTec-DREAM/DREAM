package dream.pers.priv.db.set.service.spring;

import common.bean.User;
import dream.pers.priv.db.set.dao.PersPrivDbSetDetailDAO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;
import dream.pers.priv.db.set.service.PersPrivDbSetDetailService;

/**
 * Guide Page - Detail Service implements
 * @author kim21017
 * @version $Id: PersPrivDbSetDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="persPrivDbSetDetailServiceTarget"
 * @spring.txbn id="persPrivDbSetDetailService"
 * @spring.property name="persPrivDbSetDetailDAO" ref="persPrivDbSetDetailDAO"
 */
public class PersPrivDbSetDetailServiceImpl implements PersPrivDbSetDetailService
{
    private PersPrivDbSetDetailDAO persPrivDbSetDetailDAO = null;
    
    public PersPrivDbSetDetailDTO findDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception
    {
        return persPrivDbSetDetailDAO.findDetail(persPrivDbSetCommonDTO, user);
    }
    
    public int insertDetail(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception
    {
        return persPrivDbSetDetailDAO.insertDetail(persPrivDbSetDetailDTO, user);
    }
    
    public int updateDetail(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception
    {
         this.fineDbMenu(persPrivDbSetDetailDTO, user);
         return persPrivDbSetDetailDAO.updateDetail(persPrivDbSetDetailDTO, user);
    }

    public PersPrivDbSetDetailDAO getPersPrivDbSetDetailDAO() {
        return persPrivDbSetDetailDAO;
    }

    public void setPersPrivDbSetDetailDAO(PersPrivDbSetDetailDAO persPrivDbSetDetailDAO) {
        this.persPrivDbSetDetailDAO = persPrivDbSetDetailDAO;
    }
    
    public int insertDbMenu(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception
    {
        return persPrivDbSetDetailDAO.insertDbMenu(persPrivDbSetDetailDTO, user);
    }
    
    public void fineDbMenu(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO,User user) throws Exception
    {
        String chkDbMenu = "";
        chkDbMenu = this.validDb(persPrivDbSetDetailDTO, user);
        
        persPrivDbSetDetailDTO.setUsrDbMenuKeyType("MENU");
        if("".equals(chkDbMenu))
        {
            persPrivDbSetDetailDTO.setUsrDbMenuKeyNo("TAUSRDBMENU_"+ persPrivDbSetDetailDAO.getNextSequence("SQALANG_ID"));
            
            this.insertDbMenu(persPrivDbSetDetailDTO, user);
        } else { 
            persPrivDbSetDetailDTO.setUsrDbMenuKeyNo(chkDbMenu);
        }
    }
    
    public String validDb(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception {
        
        return persPrivDbSetDetailDAO.validDbMenu(persPrivDbSetDetailDTO, user);
    }
}
