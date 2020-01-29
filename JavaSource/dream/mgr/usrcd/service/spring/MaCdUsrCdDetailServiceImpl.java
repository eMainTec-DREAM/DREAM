package dream.mgr.usrcd.service.spring;

import common.bean.User;
import dream.mgr.usrcd.dao.MaCdUsrCdDetailDAO;
import dream.mgr.usrcd.dto.MaCdUsrCdDetailDTO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.service.MaCdUsrCdDetailService;

/**
 * 유형별 세부코드 
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maCdUsrCdDetailServiceTarget"
 * @spring.txbn id="maCdUsrCdDetailService"
 * @spring.property name="maCdUsrCdDetailDAO" ref="maCdUsrCdDetailDAO"
 */
public class MaCdUsrCdDetailServiceImpl implements MaCdUsrCdDetailService
{
    private MaCdUsrCdDetailDAO maCdUsrCdDetailDAO = null;
    
    public MaCdUsrCdDetailDAO getMaCdUsrCdDetailDAO() {
        return maCdUsrCdDetailDAO;
    }

    public void setMaCdUsrCdDetailDAO(MaCdUsrCdDetailDAO maCdUsrCdDetailDAO) {
        this.maCdUsrCdDetailDAO = maCdUsrCdDetailDAO;
    }

    public MaCdUsrCdDetailDTO findDetail(MaCdUsrCommonDTO maCdUsrCommonDTO, User user)throws Exception
    {
        return maCdUsrCdDetailDAO.findDetail(maCdUsrCommonDTO, user);
    }
    
    public int insertDetail(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user) throws Exception
    {        
    	int result = 0;
    	result =  maCdUsrCdDetailDAO.insertDetail(maCdUsrCdDetailDTO, user);
    	result =  maCdUsrCdDetailDAO.updateFullDesc(maCdUsrCdDetailDTO, user);
    	result =  maCdUsrCdDetailDAO.updateDirType(maCdUsrCdDetailDTO, user);
    	
    	return result;
    }
    
    public int updateDetail(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user) throws Exception
    {        
    	int result = 0;
    	result =  maCdUsrCdDetailDAO.updateDetail(maCdUsrCdDetailDTO, user);
    	result =  maCdUsrCdDetailDAO.updateFullDesc(maCdUsrCdDetailDTO, user);
    	result =  maCdUsrCdDetailDAO.updateDirType(maCdUsrCdDetailDTO, user);
    	
    	return result;
    }
    
    public String validCode(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user) throws Exception
    {
        return maCdUsrCdDetailDAO.validCode(maCdUsrCdDetailDTO, user);
    }
}
