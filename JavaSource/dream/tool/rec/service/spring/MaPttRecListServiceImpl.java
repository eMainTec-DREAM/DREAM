package dream.tool.rec.service.spring;

import java.util.List;

import common.bean.User;
import dream.tool.rec.dao.MaPttRecDetailDAO;
import dream.tool.rec.dao.MaPttRecListDAO;
import dream.tool.rec.dto.MaPttRecCommonDTO;
import dream.tool.rec.service.MaPttRecListService;

/**
 * 구매입고 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPttRecListServiceTarget"
 * @spring.txbn id="maPttRecListService"
 * @spring.property name="maPttRecListDAO" ref="maPttRecListDAO"
 * @spring.property name="maPttRecDetailDAO" ref="maPttRecDetailDAO"
 */
public class MaPttRecListServiceImpl implements MaPttRecListService
{
    private MaPttRecListDAO maPttRecListDAO = null;
    private MaPttRecDetailDAO maPttRecDetailDAO = null;

    public MaPttRecDetailDAO getMaPttRecDetailDAO()
    {
        return maPttRecDetailDAO;
    }

    public void setMaPttRecDetailDAO(MaPttRecDetailDAO maPttRecDetailDAO)
    {
        this.maPttRecDetailDAO = maPttRecDetailDAO;
    }

    public MaPttRecListDAO getMaPttRecListDAO() 
    {
        return maPttRecListDAO;
    }

    public void setMaPttRecListDAO(MaPttRecListDAO maPttRecListDAO) 
    {
        this.maPttRecListDAO = maPttRecListDAO;
    }

    public List findList(MaPttRecCommonDTO maPttRecCommonDTO, User user)
    {      
        return maPttRecListDAO.findList(maPttRecCommonDTO,user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        String prRecListId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
            prRecListId = deleteRows[i];
            
            // 상태가 입고완료[C]가 아닌 경우만 Delete 한다. 
            String preRecListStatus = maPttRecDetailDAO.findPrRecListStatus(compNo, prRecListId);
            if(!"C".equals(preRecListStatus))
            {
                result = result + maPttRecListDAO.deleteList(compNo, prRecListId);
            }
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPttRecCommonDTO maPttRecCommonDTO, User user) throws Exception {
		return maPttRecListDAO.findTotalCount(maPttRecCommonDTO,user);
    }
}