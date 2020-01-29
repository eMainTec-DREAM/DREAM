package dream.cert.rpt.emplist.service.spring;

import java.util.List;

import common.bean.User;

import dream.cert.rpt.emplist.dao.CertRptEmpListDAO;
import dream.cert.rpt.emplist.dto.CertRptEmpCommonDTO;
import dream.cert.rpt.emplist.service.CertRptEmpListService;

/**
 * 자격증분류 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 *
 * @spring.bean id="certRptEmpListServiceTarget"
 * @spring.txbn id="certRptEmpListService"
 * @spring.property name="certRptEmpListDAO" ref="certRptEmpListDAO"
 */
public class CertRptEmpListServiceImpl implements CertRptEmpListService
{
    private CertRptEmpListDAO certRptEmpListDAO = null;
    
    public CertRptEmpListDAO getCertRptEmpListDAO() {
		return certRptEmpListDAO;
	}

	public void setCertRptEmpListDAO(CertRptEmpListDAO certRptEmpListDAO) {
		this.certRptEmpListDAO = certRptEmpListDAO;
	}


	public List findList(CertRptEmpCommonDTO certRptEmpCommonDTO    ,User user)
    {
        return certRptEmpListDAO.findList(certRptEmpCommonDTO,user);
    }

    

}