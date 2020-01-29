package dream.cert.list.service.spring;

import common.bean.User;

import dream.cert.list.dao.CertEmpDetailDAO;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpDetailDTO;
import dream.cert.list.service.CertEmpDetailService;

/**
 * 작업결과 작업자
 * @author kim2107
 * @version $Id: CertEmpDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 *
 * @spring.bean id="certEmpDetailServiceTarget"
 * @spring.txbn id="certEmpDetailService"
 * @spring.property name="certEmpDetailDAO" ref="certEmpDetailDAO"
 */
public class CertEmpDetailServiceImpl implements CertEmpDetailService
{
    private CertEmpDetailDAO certEmpDetailDAO = null;

   

	public CertEmpDetailDAO getCertEmpDetailDAO() {
		return certEmpDetailDAO;
	}

	public void setCertEmpDetailDAO(CertEmpDetailDAO certEmpDetailDAO) {
		this.certEmpDetailDAO = certEmpDetailDAO;
	}

	public CertEmpDetailDTO findDetail(String certListId, User loginUser)throws Exception
    {
        return certEmpDetailDAO.findDetail(certListId, loginUser);
    }

	public int updateDetail(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser) throws Exception
    {
        return certEmpDetailDAO.updateDetail(certEmpDetailDTO, certCommonDTO, loginUser);
    }
	public int insertDetail(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser) throws Exception
    {
        return certEmpDetailDAO.insertDetail( certEmpDetailDTO, certCommonDTO, loginUser);
    }
	public String validEmp(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser){
		return certEmpDetailDAO.validEmp(certEmpDetailDTO, certCommonDTO, loginUser);
	}
}
