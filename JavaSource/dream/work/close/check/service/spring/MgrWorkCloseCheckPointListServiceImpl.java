package dream.work.close.check.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.close.check.dao.MgrWorkCloseCheckPointListDAO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;
import dream.work.close.check.service.MgrWorkCloseCheckPointListService;

/**
 * 표준항목 리스트 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="mgrWorkCloseCheckPointListServiceTarget"
 * @spring.txbn id="mgrWorkCloseCheckPointListService"
 * @spring.property name="mgrWorkCloseCheckPointListDAO" ref="mgrWorkCloseCheckPointListDAO"
 */
public class MgrWorkCloseCheckPointListServiceImpl implements MgrWorkCloseCheckPointListService
{
    private MgrWorkCloseCheckPointListDAO mgrWorkCloseCheckPointListDAO = null;

    public MgrWorkCloseCheckPointListDAO getMgrWorkCloseCheckPointListDAO() 
    {
		return mgrWorkCloseCheckPointListDAO;
	}

	public void setMgrWorkCloseCheckPointListDAO(MgrWorkCloseCheckPointListDAO mgrWorkCloseCheckPointListDAO) 
	{
		this.mgrWorkCloseCheckPointListDAO = mgrWorkCloseCheckPointListDAO;
	}

	public List findStdPointList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO,MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser)
    {      
        return mgrWorkCloseCheckPointListDAO.findStdPointList(mgrWorkCloseCheckCommonDTO,mgrWorkCloseCheckPointListDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                result = result + mgrWorkCloseCheckPointListDAO.deleteList(id, loginUser);
            }
        }
        
        return result;
    }
	
	@Override
    public String findTotalCount(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser)
    {
        return mgrWorkCloseCheckPointListDAO.findTotalCount(mgrWorkCloseCheckCommonDTO, mgrWorkCloseCheckPointListDTO, loginUser);
    }
}
