package dream.tool.iss.rent.service.spring;

import java.util.List;

import common.bean.User;
import dream.tool.iss.rent.dao.MaPttIssDetailDAO;
import dream.tool.iss.rent.dao.MaPttIssListDAO;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;
import dream.tool.iss.rent.service.MaPttIssListService;

/**
 * 구매입고 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPttIssListServiceTarget"
 * @spring.txbn id="maPttIssListService"
 * @spring.property name="maPttIssListDAO" ref="maPttIssListDAO"
 * @spring.property name="maPttIssDetailDAO" ref="maPttIssDetailDAO"
 */
public class MaPttIssListServiceImpl implements MaPttIssListService
{
    private MaPttIssListDAO maPttIssListDAO = null;
    private MaPttIssDetailDAO maPttIssDetailDAO = null;

    public MaPttIssDetailDAO getMaPttIssDetailDAO()
    {
        return maPttIssDetailDAO;
    }

    public void setMaPttIssDetailDAO(MaPttIssDetailDAO maPttIssDetailDAO)
    {
        this.maPttIssDetailDAO = maPttIssDetailDAO;
    }

    public MaPttIssListDAO getMaPttIssListDAO() 
    {
        return maPttIssListDAO;
    }

    public void setMaPttIssListDAO(MaPttIssListDAO maPttIssListDAO) 
    {
        this.maPttIssListDAO = maPttIssListDAO;
    }

    public List findList(MaPttIssCommonDTO maPttIssCommonDTO, User user)
    {      
        return maPttIssListDAO.findList(maPttIssCommonDTO,user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        String ptIssListId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
        	ptIssListId = deleteRows[i];
            
            // 상태가 입고완료[C]가 아닌 경우만 Delete 한다. 
            String ptIssListStatus = maPttIssDetailDAO.findPtIssListStatus(compNo, ptIssListId);
            if(!"C".equals(ptIssListStatus))
            {
                result = result + maPttIssListDAO.deleteList(compNo, ptIssListId);
            }
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPttIssCommonDTO maPttIssCommonDTO, User user) {
		return maPttIssListDAO.findTotalCount(maPttIssCommonDTO,user);
	}

}