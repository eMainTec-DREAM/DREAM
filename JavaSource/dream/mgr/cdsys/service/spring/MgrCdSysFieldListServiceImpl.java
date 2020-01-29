package dream.mgr.cdsys.service.spring;

import java.util.List;

import dream.mgr.cdsys.dao.MgrCdSysFieldListDAO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.dto.MgrCdSysFieldListDTO;
import dream.mgr.cdsys.service.MgrCdSysFieldListService;

/**
 * 시스템코드 detail-code 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrCdSysFieldListServiceTarget"
 * @spring.txbn id="mgrCdSysFieldListService"
 * @spring.property name="mgrCdSysFieldListDAO" ref="mgrCdSysFieldListDAO"
 */
public class MgrCdSysFieldListServiceImpl implements MgrCdSysFieldListService
{
    private MgrCdSysFieldListDAO mgrCdSysFieldListDAO = null;


	public List findCodeList(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysFieldListDTO mgrCdSysFieldListDTO)
    {      
        return mgrCdSysFieldListDAO.findCodeList(mgrCdSysCommonDTO, mgrCdSysFieldListDTO);
    }

	public MgrCdSysFieldListDAO getMgrCdSysFieldListDAO() {
		return mgrCdSysFieldListDAO;
	}


	public void setMgrCdSysFieldListDAO(MgrCdSysFieldListDAO mgrCdSysFieldListDAO) {
		this.mgrCdSysFieldListDAO = mgrCdSysFieldListDAO;
	}

    @Override
    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO,
            MgrCdSysFieldListDTO mgrCdSysFieldListDTO)
    {
        return mgrCdSysFieldListDAO.findTotalCount(mgrCdSysCommonDTO, mgrCdSysFieldListDTO);
    }
}

