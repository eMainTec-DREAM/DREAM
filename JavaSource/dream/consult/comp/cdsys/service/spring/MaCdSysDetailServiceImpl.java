package dream.consult.comp.cdsys.service.spring;

import dream.consult.comp.cdsys.dao.MaCdSysDetailDAO;
import dream.consult.comp.cdsys.dto.MaCdSysDetailDTO;
import dream.consult.comp.cdsys.service.MaCdSysDetailService;

/**
 * 시스템코드 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaCdSysDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maCdSysDetailServiceTarget"
 * @spring.txbn id="maCdSysDetailService"
 * @spring.property name="maCdSysDetailDAO" ref="maCdSysDetailDAO"
 */
public class MaCdSysDetailServiceImpl implements MaCdSysDetailService
{
    private MaCdSysDetailDAO maCdSysDetailDAO = null;
    
    public MaCdSysDetailDAO getMaCdSysDetailDAO() {
		return maCdSysDetailDAO;
	}

	public void setMaCdSysDetailDAO(MaCdSysDetailDAO maCdSysDetailDAO) {
		this.maCdSysDetailDAO = maCdSysDetailDAO;
	}

	public MaCdSysDetailDTO findDetail(String cdSysMId, String lang)throws Exception
    {
        return maCdSysDetailDAO.findDetail(cdSysMId,lang);
    }
	
	public int updateDetail(MaCdSysDetailDTO maCdSysDetailDTO) throws Exception
    {        
		this.insertUpdateLangData(maCdSysDetailDTO);
		return maCdSysDetailDAO.updateDetail(maCdSysDetailDTO);
    }
	public int insertDetail(MaCdSysDetailDTO maCdSysDetailDTO) throws Exception
    {        
		this.insertUpdateLangData(maCdSysDetailDTO);
		return maCdSysDetailDAO.insertDetail(maCdSysDetailDTO);
    }
	public void insertUpdateLangData(MaCdSysDetailDTO maCdSysDetailDTO) throws Exception
	{        
		String isExists = maCdSysDetailDAO.checkLangData(maCdSysDetailDTO);
		
    	if("0".equals(isExists))
    		maCdSysDetailDAO.insertLangData(maCdSysDetailDTO);
    	else
    		maCdSysDetailDAO.updateLangData(maCdSysDetailDTO);
	}
}
