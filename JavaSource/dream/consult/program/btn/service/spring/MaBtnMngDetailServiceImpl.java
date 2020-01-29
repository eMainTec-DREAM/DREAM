package dream.consult.program.btn.service.spring;

import common.config.service.ConfigService;
import common.util.CommonUtil;
import dream.consult.program.btn.dao.MaBtnMngDetailDAO;
import dream.consult.program.btn.dto.MaBtnMngDetailDTO;
import dream.consult.program.btn.service.MaBtnMngDetailService;

/**
 * 버튼 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaBtnMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBtnMngDetailServiceTarget"
 * @spring.txbn id="maBtnMngDetailService"
 * @spring.property name="maBtnMngDetailDAO" ref="maBtnMngDetailDAO"
 */
public class MaBtnMngDetailServiceImpl implements MaBtnMngDetailService
{
    private MaBtnMngDetailDAO maBtnMngDetailDAO = null;
    
    public MaBtnMngDetailDAO getMaBtnMngDetailDAO() {
		return maBtnMngDetailDAO;
	}

	public void setMaBtnMngDetailDAO(MaBtnMngDetailDAO maBtnMngDetailDAO) {
		this.maBtnMngDetailDAO = maBtnMngDetailDAO;
	}

	public MaBtnMngDetailDTO findDetail(String buttonId)throws Exception
    {
        return maBtnMngDetailDAO.findDetail(buttonId);
    }
    
	public int insertDetail(MaBtnMngDetailDTO maBtnMngDetailDTO) throws Exception
    {        
        int rtn = maBtnMngDetailDAO.insertDetail(maBtnMngDetailDTO);
        ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
        configService.loadPageButtons();
        return rtn;
    }
	
	public int updateDetail(MaBtnMngDetailDTO maBtnMngDetailDTO) throws Exception
    {        
	    int rtn = maBtnMngDetailDAO.updateDetail(maBtnMngDetailDTO);
	    ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
	    configService.loadPageButtons();
	    return rtn;
    }
}
