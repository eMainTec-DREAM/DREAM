package dream.mgr.cdsys.service.spring;

import dream.mgr.cdsys.dao.MgrCdSysDetailDAO;
import dream.mgr.cdsys.dto.MgrCdSysDetailDTO;
import dream.mgr.cdsys.service.MgrCdSysDetailService;

/**
 * 시스템코드 - 상세 serviceimpl 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrCdSysDetailServiceTarget"
 * @spring.txbn id="mgrCdSysDetailService"
 * @spring.property name="mgrCdSysDetailDAO" ref="mgrCdSysDetailDAO"
 */
public class MgrCdSysDetailServiceImpl implements MgrCdSysDetailService
{
    private MgrCdSysDetailDAO mgrCdSysDetailDAO = null;
    
    public MgrCdSysDetailDAO getMgrCdSysDetailDAO() {
		return mgrCdSysDetailDAO;
	}

	public void setMgrCdSysDetailDAO(MgrCdSysDetailDAO mgrCdSysDetailDAO) {
		this.mgrCdSysDetailDAO = mgrCdSysDetailDAO;
	}

	public MgrCdSysDetailDTO findDetail(String cdSysMId, String lang)throws Exception
    {
        return mgrCdSysDetailDAO.findDetail(cdSysMId,lang);
    }
	
	public int updateDetail(MgrCdSysDetailDTO mgrCdSysDetailDTO) throws Exception
    {        
		this.insertUpdateLangData(mgrCdSysDetailDTO);
		return mgrCdSysDetailDAO.updateDetail(mgrCdSysDetailDTO);
    }
	
	public void insertUpdateLangData(MgrCdSysDetailDTO mgrCdSysDetailDTO) throws Exception
	{        
		String isExists = mgrCdSysDetailDAO.checkLangData(mgrCdSysDetailDTO);
		
    	if("0".equals(isExists))
    		mgrCdSysDetailDAO.insertLangData(mgrCdSysDetailDTO);
    	else
    		mgrCdSysDetailDAO.updateLangData(mgrCdSysDetailDTO);
	}
}
