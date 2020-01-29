package dream.mgr.usrcd.service.spring;

import common.bean.User;
import dream.mgr.usrcd.dao.MaCdUsrDetailDAO;
import dream.mgr.usrcd.dto.MaCdUsrDetailDTO;
import dream.mgr.usrcd.service.MaCdUsrDetailService;

/**
 * 사용자코드관리
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maCdUsrDetailServiceTarget"
 * @spring.txbn id="maCdUsrDetailService"
 * @spring.property name="maCdUsrDetailDAO" ref="maCdUsrDetailDAO"
 */
public class MaCdUsrDetailServiceImpl implements MaCdUsrDetailService
{
    /** 사용자코드관리 DAO */
    private MaCdUsrDetailDAO maCdUsrDetailDAO = null;
    
    public MaCdUsrDetailDAO getMaCdUsrDetailDAO()
    {
        return maCdUsrDetailDAO;
    }
    
    public void setMaCdUsrDetailDAO(MaCdUsrDetailDAO maCdUsrDetailDAO)
    {
        this.maCdUsrDetailDAO = maCdUsrDetailDAO;
    }
    
    public MaCdUsrDetailDTO findDetail(User user, String dirType)
    {
    	return maCdUsrDetailDAO.findDetail(user, dirType);
    	
    }
    
    public int insertDetail(MaCdUsrDetailDTO maCdUsrDetailDTO)
    {
    	return maCdUsrDetailDAO.insertDetail(maCdUsrDetailDTO);
    }
    
    public int updateDetail(MaCdUsrDetailDTO maCdUsrDetailDTO)
    {
    	return maCdUsrDetailDAO.updateDetail(maCdUsrDetailDTO);
    }
    
   	public String validDirType(MaCdUsrDetailDTO maCdUsrDetailDTO) throws Exception
    {
        return maCdUsrDetailDAO.validDirType(maCdUsrDetailDTO);
    }
}
