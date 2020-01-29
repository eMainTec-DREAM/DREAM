package dream.mgr.cdsys.service.spring;

import java.util.List;

import dream.mgr.cdsys.dao.MgrCdSysListDAO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.service.MgrCdSysListService;

/**
 * 시스템코드 - 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrCdSysListServiceTarget"
 * @spring.txbn id="mgrCdSysListService"
 * @spring.property name="mgrCdSysListDAO" ref="mgrCdSysListDAO"
 */
public class MgrCdSysListServiceImpl implements MgrCdSysListService
{
    private MgrCdSysListDAO mgrCdSysListDAO = null;

    public MgrCdSysListDAO getMgrCdSysListDAO() {
		return mgrCdSysListDAO;
	}

	public void setMgrCdSysListDAO(MgrCdSysListDAO mgrCdSysListDAO) {
		this.mgrCdSysListDAO = mgrCdSysListDAO;
	}

	public List findListTypeList(MgrCdSysCommonDTO mgrCdSysCommonDTO)
    {      
        return mgrCdSysListDAO.findListTypeList(mgrCdSysCommonDTO);
    }

    @Override
    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO)
    {
        return mgrCdSysListDAO.findTotalCount(mgrCdSysCommonDTO);
    }
}

