package dream.consult.comp.cdsys.service.spring;

import java.util.List;

import dream.consult.comp.cdsys.dao.ConsultCdSysFieldListDAO;
import dream.consult.comp.cdsys.dto.ConsultCdSysFieldListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.service.ConsultCdSysFieldListService;

/**
 * 시스템코드 detail-code 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultCdSysFieldListServiceTarget"
 * @spring.txbn id="consultCdSysFieldListService"
 * @spring.property name="consultCdSysFieldListDAO" ref="consultCdSysFieldListDAO"
 */
public class ConsultCdSysFieldListServiceImpl implements ConsultCdSysFieldListService
{
    private ConsultCdSysFieldListDAO consultCdSysFieldListDAO = null;


	public List findCodeList(MaCdSysCommonDTO maCdSysCommonDTO, ConsultCdSysFieldListDTO consultCdSysFieldListDTO)
    {      
        return consultCdSysFieldListDAO.findCodeList(maCdSysCommonDTO, consultCdSysFieldListDTO);
    }

	public ConsultCdSysFieldListDAO getConsultCdSysFieldListDAO() {
		return consultCdSysFieldListDAO;
	}


	public void setConsultCdSysFieldListDAO(ConsultCdSysFieldListDAO consultCdSysFieldListDAO) {
		this.consultCdSysFieldListDAO = consultCdSysFieldListDAO;
	}
}

