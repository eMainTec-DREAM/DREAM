package dream.mgr.usrcd.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usrcd.dao.MaCdUsrCdListDAO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.service.MaCdUsrCdListService;

/**
 * 사용자코드관리 상세 목록 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maCdUsrCdListServiceTarget"
 * @spring.txbn id="maCdUsrCdListService"
 * @spring.property name="maCdUsrCdListDAO" ref="maCdUsrCdListDAO"
 */
public class MaCdUsrCdListServiceImpl implements MaCdUsrCdListService
{
    /** 사용자코드관리 DAO */
    private MaCdUsrCdListDAO maCdUsrCdListDAO = null;
    
    public MaCdUsrCdListDAO getMaCdUsrCdListDAO()
    {
        return maCdUsrCdListDAO;
    }
    
    public void setMaCdUsrCdListDAO(MaCdUsrCdListDAO maCdUsrCdListDAO)
    {
        this.maCdUsrCdListDAO = maCdUsrCdListDAO;
    }
    
    public List findSheet(MaCdUsrCommonDTO maCdUsrCommonDTO, User user, boolean excelExport)
    {
        List list =  maCdUsrCdListDAO.findSheet(maCdUsrCommonDTO, user);
        return CommonUtil.makeTreeList(list, "PCDUSRDID", "CDUSRDID", excelExport);
    }
	public int deleteList(String compNo, String[] deleteRows) throws Exception
	{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows) 
            {
                result = result + maCdUsrCdListDAO.deleteCdUsrCdList(compNo, id);
            }
        return result;
    }

	@Override
	public String findTotalCount(MaCdUsrCommonDTO maCdUsrCommonDTO, User user)
	{
		return maCdUsrCdListDAO.findTotalCount(maCdUsrCommonDTO, user);
	}

}
