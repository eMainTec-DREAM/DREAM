package dream.mgr.cdsys.service.spring;

import java.util.List;

import dream.mgr.cdsys.dao.MgrCdSysCodeListDAO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.service.MgrCdSysCodeListService;

/**
 * 시스템코드 detail-code 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrCdSysCodeListServiceTarget"
 * @spring.txbn id="mgrCdSysCodeListService"
 * @spring.property name="mgrCdSysCodeListDAO" ref="mgrCdSysCodeListDAO"
 */
public class MgrCdSysCodeListServiceImpl implements MgrCdSysCodeListService
{
    private MgrCdSysCodeListDAO mgrCdSysCodeListDAO = null;


	public List findCodeList(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO)
    {      
        return mgrCdSysCodeListDAO.findCodeList(mgrCdSysCommonDTO, mgrCdSysCodeListDTO);
    }

	public MgrCdSysCodeListDAO getMgrCdSysCodeListDAO() {
		return mgrCdSysCodeListDAO;
	}


	public void setMgrCdSysCodeListDAO(MgrCdSysCodeListDAO mgrCdSysCodeListDAO) {
		this.mgrCdSysCodeListDAO = mgrCdSysCodeListDAO;
	}

    @Override
    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO,
            MgrCdSysCodeListDTO mgrCdSysCodeListDTO)
    {
        return mgrCdSysCodeListDAO.findTotalCount(mgrCdSysCommonDTO, mgrCdSysCodeListDTO);
    }
}

