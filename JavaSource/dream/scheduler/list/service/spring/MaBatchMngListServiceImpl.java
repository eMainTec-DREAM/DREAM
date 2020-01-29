package dream.scheduler.list.service.spring;

import java.lang.reflect.Method;
import java.util.List;

import common.bean.User;
import dream.scheduler.autoschedule.service.MaBatchService;
import dream.scheduler.list.dao.MaBatchMngListDAO;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;
import dream.scheduler.list.service.MaBatchMngListService;

/**
 * 버튼 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaBatchMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maBatchMngListServiceTarget"
 * @spring.txbn id="maBatchMngListService"
 * @spring.property name="maBatchMngListDAO" ref="maBatchMngListDAO"
 * @spring.property name="maBatchService" ref="maBatchService"
 */
public class MaBatchMngListServiceImpl implements MaBatchMngListService
{
    private MaBatchMngListDAO maBatchMngListDAO = null;

    private MaBatchService maBatchService = null;
    

    public MaBatchService getMaBatchService()
    {
        return maBatchService;
    }

    public void setMaBatchService(MaBatchService maBatchService)
    {
        this.maBatchService = maBatchService;
    }

    public MaBatchMngListDAO getMaBatchMngListDAO() {
		return maBatchMngListDAO;
	}

	public void setMaBatchMngListDAO(MaBatchMngListDAO maBatchMngListDAO) {
		this.maBatchMngListDAO = maBatchMngListDAO;
	}

	public List findBatchList(MaBatchMngCommonDTO maBatchMngCommonDTO)
    {      
        return maBatchMngListDAO.findBatchList(maBatchMngCommonDTO);
    }
	
	public int deleteBatch(String[] deleteRows, User user) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maBatchMngListDAO.deleteBatch(id,user);
            }
        return result;
    }
	public int execBatch(String[] selectRows, User user) throws Exception{
        int result = 0;
        Method method = null;
        String methodName = "";
        
        if(!selectRows.equals(null))
            for(String id : selectRows)
            {
                methodName =  maBatchMngListDAO.execBatch(id,user);
                if(!"".equals(methodName))
                {
                    method = maBatchService.getClass().getMethod(methodName);
                    method.invoke(maBatchService);
                }
                result++;
            }
        return result;
    }

	@Override
	public String findTotalCount(MaBatchMngCommonDTO maBatchMngCommonDTO, User user)
	{
		return maBatchMngListDAO.findTotalCount(maBatchMngCommonDTO, user);
	}
}

