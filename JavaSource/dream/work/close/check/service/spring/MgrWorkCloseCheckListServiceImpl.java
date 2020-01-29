package dream.work.close.check.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.close.check.dao.MgrWorkCloseCheckListDAO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.service.MgrWorkCloseCheckListService;

/**
 * MgrWorkCloseCheck Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrWorkCloseCheckListServiceTarget"
 * @spring.txbn id="mgrWorkCloseCheckListService"
 * @spring.property name="mgrWorkCloseCheckListDAO" ref="mgrWorkCloseCheckListDAO"
 */
public class MgrWorkCloseCheckListServiceImpl implements MgrWorkCloseCheckListService
{
    private MgrWorkCloseCheckListDAO mgrWorkCloseCheckListDAO = null;
    
    public List findList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception
    {      
        return mgrWorkCloseCheckListDAO.findList(mgrWorkCloseCheckCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrWorkCloseCheckListDAO.deleteList(id, user);
            }
        return result;
    }

    public MgrWorkCloseCheckListDAO getMgrWorkCloseCheckListDAO() {
        return mgrWorkCloseCheckListDAO;
    }

    public void setMgrWorkCloseCheckListDAO(MgrWorkCloseCheckListDAO mgrWorkCloseCheckListDAO) {
        this.mgrWorkCloseCheckListDAO = mgrWorkCloseCheckListDAO;
    }    
    
    public String findTotalCount(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO,User user)  throws Exception
    {
        return mgrWorkCloseCheckListDAO.findTotalCount(mgrWorkCloseCheckCommonDTO, user);
    }
}
