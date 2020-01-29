package dream.mgr.cdsys.service.spring;

import common.bean.User;
import dream.mgr.cdsys.dao.MgrCdSysCodeDetailDAO;
import dream.mgr.cdsys.dto.MgrCdSysCodeDetailDTO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.service.MgrCdSysCodeDetailService;

/**
 * 시스템코드 - 분류
 * @author kim2107
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrCdSysCodeDetailServiceTarget"
 * @spring.txbn id="mgrCdSysCodeDetailService"
 * @spring.property name="mgrCdSysCodeDetailDAO" ref="mgrCdSysCodeDetailDAO"
 */
public class MgrCdSysCodeDetailServiceImpl implements MgrCdSysCodeDetailService
{
    private MgrCdSysCodeDetailDAO mgrCdSysCodeDetailDAO = null;
    
    public MgrCdSysCodeDetailDAO getMgrCdSysCodeDetailDAO() {
		return mgrCdSysCodeDetailDAO;
	}

	public void setMgrCdSysCodeDetailDAO(MgrCdSysCodeDetailDAO mgrCdSysCodeDetailDAO) {
		this.mgrCdSysCodeDetailDAO = mgrCdSysCodeDetailDAO;
	}

	public MgrCdSysCodeDetailDTO findDetail(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO, User user)throws Exception
    {
        return mgrCdSysCodeDetailDAO.findDetail(mgrCdSysCommonDTO, mgrCdSysCodeListDTO, user);
    }
    
	public int updateDetail(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user) throws Exception
    {        
		this.insertUpdateLangData(mgrCdSysCodeDetailDTO, mgrCdSysCommonDTO, user);
		
		return mgrCdSysCodeDetailDAO.updateDetail(mgrCdSysCodeDetailDTO, mgrCdSysCommonDTO, user);
		        
    }
	
	public void insertUpdateLangData(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user) throws Exception
	{        
		String isExists = mgrCdSysCodeDetailDAO.checkLangData(mgrCdSysCodeDetailDTO, mgrCdSysCommonDTO, user);
		System.out.println("존재하면 1 존재하지않으면 0 >>> " + isExists);
    	if("0".equals(isExists))
    		mgrCdSysCodeDetailDAO.insertLangData(mgrCdSysCodeDetailDTO, mgrCdSysCommonDTO, user);
    	else
    		mgrCdSysCodeDetailDAO.updateLangData(mgrCdSysCodeDetailDTO, mgrCdSysCommonDTO, user);
	}
}
