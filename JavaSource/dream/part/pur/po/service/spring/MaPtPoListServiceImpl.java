package dream.part.pur.po.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.pur.po.dao.MaPtPoDetailDAO;
import dream.part.pur.po.dao.MaPtPoListDAO;
import dream.part.pur.po.dto.MaPtPoCommonDTO;
import dream.part.pur.po.form.MaPtPoListForm;
import dream.part.pur.po.service.MaPtPoListService;

/**
 * 발주이력 - 목록 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtPoListServiceTarget"
 * @spring.txbn id="maPtPoListService"
 * @spring.property name="maPtPoListDAO" ref="maPtPoListDAO"
 * @spring.property name="maPtPoDetailDAO" ref="maPtPoDetailDAO"
 */
public class MaPtPoListServiceImpl implements MaPtPoListService
{
    private MaPtPoListDAO   maPtPoListDAO   = null;
    private MaPtPoDetailDAO maPtPoDetailDAO = null;

    public MaPtPoListDAO getMaPtPoListDAO() {
		return maPtPoListDAO;
	}

	public void setMaPtPoListDAO(MaPtPoListDAO maPtPoListDAO) {
		this.maPtPoListDAO = maPtPoListDAO;
	}

	public MaPtPoDetailDAO getMaPtPoDetailDAO() {
		return maPtPoDetailDAO;
	}

	public void setMaPtPoDetailDAO(MaPtPoDetailDAO maPtPoDetailDAO) {
		this.maPtPoDetailDAO = maPtPoDetailDAO;
	}

	public List findList(MaPtPoCommonDTO maPtPoCommonDTO, User user)
    {      
        return maPtPoListDAO.findList(maPtPoCommonDTO, user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        String poListId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
        	poListId = deleteRows[i];
            result = result + maPtPoListDAO.deleteList(compNo, poListId);
        }
        
        return result;
    }
    public int recList(MaPtPoListForm maPtPoListForm, User loginUser) throws Exception
    {
    	String[] selectRows = maPtPoListForm.getSelectRows();
		selectRows = CommonUtil.makeRepRemoval(selectRows);
		MaPtPoCommonDTO maPtPoCommonDTO = maPtPoListForm.getMaPtPoCommonDTO();
        
		int result = 0;
        for(int i = 0; selectRows.length > i ; i++)
        {
//            입고완료 
            result = result + maPtPoListDAO.insertRec(maPtPoCommonDTO,selectRows[i],loginUser);
        }
        
    	return result;
    }

	@Override
	public String findTotalCount(MaPtPoCommonDTO maPtPoCommonDTO, User user) {
		return maPtPoListDAO.findTotalCount(maPtPoCommonDTO, user);
	}

}