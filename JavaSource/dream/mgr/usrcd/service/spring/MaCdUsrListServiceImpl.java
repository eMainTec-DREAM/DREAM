package dream.mgr.usrcd.service.spring;

import java.util.Iterator;
import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.dao.MaCdUsrListDAO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.dto.MaCdUsrGridDTO;
import dream.mgr.usrcd.service.MaCdUsrListService;

/**
 * 사용자코드관리
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maCdUsrListServiceTarget"
 * @spring.txbn id="maCdUsrListService"
 * @spring.property name="maCdUsrListDAO" ref="maCdUsrListDAO"
 */
public class MaCdUsrListServiceImpl implements MaCdUsrListService
{
    /** 사용자코드관리 DAO */
    private MaCdUsrListDAO maCdUsrListDAO = null;
    
    public MaCdUsrListDAO getMaCdUsrListDAO()
    {
        return maCdUsrListDAO;
    }
    
    public void setMaCdUsrListDAO(MaCdUsrListDAO maCdUsrListDAO)
    {
        this.maCdUsrListDAO = maCdUsrListDAO;
    }
    
    public List findSheet(MaCdUsrCommonDTO maCdUsrCommonDTO,User user)
    {
        return maCdUsrListDAO.findSheet(maCdUsrCommonDTO,user);
    }

	public int deleteList(String compNo, String[] deleteRows) throws Exception
	{
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                result = result + maCdUsrListDAO.deleteCdUsr(compNo, id);
            }
        }
        return result;
    }

	@Override
	public String findTotalCount(MaCdUsrCommonDTO maCdUsrCommonDTO, User user)
	{
		return maCdUsrListDAO.findTotalCount(maCdUsrCommonDTO, user);
	}

}
