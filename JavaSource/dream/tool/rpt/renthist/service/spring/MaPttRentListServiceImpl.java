package dream.tool.rpt.renthist.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.tool.rpt.renthist.dao.MaPttRentListDAO;
import dream.tool.rpt.renthist.dto.MaPttRentCommonDTO;
import dream.tool.rpt.renthist.form.MaPttRentListForm;
import dream.tool.rpt.renthist.service.MaPttRentListService;

/**
 * 공기구대여내역 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPttRentListServiceTarget"
 * @spring.txbn id="maPttRentListService"
 * @spring.property name="maPttRentListDAO" ref="maPttRentListDAO"
 */
public class MaPttRentListServiceImpl implements MaPttRentListService
{
    private MaPttRentListDAO maPttRentListDAO = null;

    public MaPttRentListDAO getMaPttRentListDAO() 
    {
        return maPttRentListDAO;
    }

    public void setMaPttRentListDAO(MaPttRentListDAO maPttRentListDAO) 
    {
        this.maPttRentListDAO = maPttRentListDAO;
    }

    public List findList(MaPttRentCommonDTO maPttRentCommonDTO)
    {      
        return maPttRentListDAO.findList(maPttRentCommonDTO);
    }
    
    public int reqPtReturn(MaPttRentListForm maPttRentListForm, User loginUser)
	{
		
    	String[] selectRows = maPttRentListForm.getSelectRows();  
    	selectRows = CommonUtil.makeRepRemoval(selectRows);

        int result = 0;

        for(int i = 0; selectRows.length > i ; i++)
        {
            // 구매신청
            result = result + maPttRentListDAO.reqHdrPtRtn(maPttRentListForm,selectRows[i],loginUser);
        }
        
        return result;
    }

}