package dream.consult.comp.cdsys.service.spring;

import common.bean.User;
import dream.consult.comp.cdsys.dao.MaCdSysCodeDetailDAO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeDetailDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.service.MaCdSysCodeDetailService;

/**
 * 시스템코드 - 분류
 * @author kim2107
 * @version $Id: MaCdSysCodeDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maCdSysCodeDetailServiceTarget"
 * @spring.txbn id="maCdSysCodeDetailService"
 * @spring.property name="maCdSysCodeDetailDAO" ref="maCdSysCodeDetailDAO"
 */
public class MaCdSysCodeDetailServiceImpl implements MaCdSysCodeDetailService
{
    private MaCdSysCodeDetailDAO maCdSysCodeDetailDAO = null;
    
    public MaCdSysCodeDetailDAO getMaCdSysCodeDetailDAO() {
		return maCdSysCodeDetailDAO;
	}

	public void setMaCdSysCodeDetailDAO(MaCdSysCodeDetailDAO maCdSysCodeDetailDAO) {
		this.maCdSysCodeDetailDAO = maCdSysCodeDetailDAO;
	}

	public MaCdSysCodeDetailDTO findDetail(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO, User user)throws Exception
    {
        return maCdSysCodeDetailDAO.findDetail(maCdSysCommonDTO, maCdSysCodeListDTO, user);
    }
    
	public int updateDetail(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user) throws Exception
    {        
		this.insertUpdateLangData(maCdSysCodeDetailDTO, maCdSysCommonDTO, user);
		
		return maCdSysCodeDetailDAO.updateDetail(maCdSysCodeDetailDTO, maCdSysCommonDTO, user);
		        
    }
	
	public int insertDetail(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user) throws Exception
    {        
		this.insertUpdateLangData(maCdSysCodeDetailDTO, maCdSysCommonDTO, user);
		return  maCdSysCodeDetailDAO.insertDetail( maCdSysCodeDetailDTO, maCdSysCommonDTO,user);
    }
	
	public void insertUpdateLangData(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user) throws Exception
	{        
		String isExists = maCdSysCodeDetailDAO.checkLangData(maCdSysCodeDetailDTO, maCdSysCommonDTO, user);
		System.out.println("존재하면 1 존재하지않으면 0 >>> " + isExists);
    	if("0".equals(isExists))
    		maCdSysCodeDetailDAO.insertLangData(maCdSysCodeDetailDTO, maCdSysCommonDTO, user);
    	else
    		maCdSysCodeDetailDAO.updateLangData(maCdSysCodeDetailDTO, maCdSysCommonDTO, user);
	}
}
