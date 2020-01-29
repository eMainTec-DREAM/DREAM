package dream.tool.adj.service.spring;

import java.util.List;

import common.bean.User;
import dream.tool.adj.dao.MaPttDisListDAO;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.service.MaPttDisListService;

/**
 * ��� serviceimpl
 * @author kim21017
 * @version $Id: MaPttDisListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPttDisListServiceTarget"
 * @spring.txbn id="maPttDisListService"
 * @spring.property name="maPttDisListDAO" ref="maPttDisListDAO"
 */
public class MaPttDisListServiceImpl implements MaPttDisListService
{
    private MaPttDisListDAO maPttDisListDAO = null;

    public MaPttDisListDAO getMaPttDisListDAO() {
		return maPttDisListDAO;
	}

	public void setMaPttDisListDAO(MaPttDisListDAO maPttDisListDAO) {
		this.maPttDisListDAO = maPttDisListDAO;
	}

	public List findDisList(MaPttDisCommonDTO maPttDisCommonDTO, User user)
    {      
        return maPttDisListDAO.findDisList(maPttDisCommonDTO,user);
    }
	public int deleteDis(String compNo, String[] deleteRows) throws Exception{
		int result = 0;
        String ptdisuselistId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
        	ptdisuselistId = deleteRows[i];
            
            // ���°� �ݳ��Ϸ�[C]�� �ƴ� ��츸 Delete �Ѵ�. 
            String ptIssListStatus = maPttDisListDAO.findPtDisListStatus(compNo, ptdisuselistId);
            if(!"C".equals(ptIssListStatus))
            {
                result = result + maPttDisListDAO.deleteDis(compNo, ptdisuselistId);
            }
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPttDisCommonDTO maPttDisCommonDTO, User user) {
		return maPttDisListDAO.findTotalCount(maPttDisCommonDTO,user);
	}
}

