package dream.tool.iss.rtn.service.spring;

import java.util.List;

import common.bean.User;
import dream.tool.iss.rtn.dao.MaPttRtnDetailDAO;
import dream.tool.iss.rtn.dao.MaPttRtnListDAO;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;
import dream.tool.iss.rtn.service.MaPttRtnListService;

/**
 * 공기구반납 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPttRtnListServiceTarget"
 * @spring.txbn id="maPttRtnListService"
 * @spring.property name="maPttRtnListDAO" ref="maPttRtnListDAO"
 * @spring.property name="maPttRtnDetailDAO" ref="maPttRtnDetailDAO"
 */
public class MaPttRtnListServiceImpl implements MaPttRtnListService
{
    private MaPttRtnListDAO maPttRtnListDAO = null;
    private MaPttRtnDetailDAO maPttRtnDetailDAO = null;

    public MaPttRtnDetailDAO getMaPttRtnDetailDAO()
    {
        return maPttRtnDetailDAO;
    }

    public void setMaPttRtnDetailDAO(MaPttRtnDetailDAO maPttRtnDetailDAO)
    {
        this.maPttRtnDetailDAO = maPttRtnDetailDAO;
    }

    public MaPttRtnListDAO getMaPttRtnListDAO() 
    {
        return maPttRtnListDAO;
    }

    public void setMaPttRtnListDAO(MaPttRtnListDAO maPttRtnListDAO) 
    {
        this.maPttRtnListDAO = maPttRtnListDAO;
    }

    public List findList(MaPttRtnCommonDTO maPttRtnCommonDTO,User user)
    {      
        return maPttRtnListDAO.findList(maPttRtnCommonDTO, user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        String ptRtnListId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
        	ptRtnListId = deleteRows[i];
            
            // 상태가 반납완료[C]가 아닌 경우만 Delete 한다. 
            String ptIssListStatus = maPttRtnDetailDAO.findPtRtnListStatus(compNo, ptRtnListId);
            if(!"C".equals(ptIssListStatus))
            {
                result = result + maPttRtnListDAO.deleteList(compNo, ptRtnListId);
            }
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPttRtnCommonDTO maPttRtnCommonDTO, User user) {
		return maPttRtnListDAO.findTotalCount(maPttRtnCommonDTO, user);
	}

}