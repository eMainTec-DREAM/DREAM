package dream.cert.list.service.spring;

import java.util.List;

import common.bean.User;

import dream.cert.list.dao.CertEmpListDAO;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpListDTO;
import dream.cert.list.service.CertEmpListService;

/**
 * 작업결과 작업자 목록
 * @author kim21017
 * @version $Id: CertEmpListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="certEmpListServiceTarget"
 * @spring.txbn id="certEmpListService"
 * @spring.property name="certEmpListDAO" ref="certEmpListDAO"
 */
public class CertEmpListServiceImpl implements CertEmpListService
{
    private CertEmpListDAO certEmpListDAO = null;
    
	public CertEmpListDAO getCertEmpListDAO() {
		return certEmpListDAO;
	}

	public void setCertEmpListDAO(CertEmpListDAO certEmpListDAO) {
		this.certEmpListDAO = certEmpListDAO;
	}

	public List findEmpList(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User loginUser)
    {
        return certEmpListDAO.findEmpList(certCommonDTO, certEmpListDTO, loginUser);
    }

	public int deleteEmpList(String[] deleteRows, User loginUser) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + certEmpListDAO.deleteEmpList(id, loginUser);
            }

        return result;
    }

    @Override
    public String findTotalCount(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User user) throws Exception
    {
        return certEmpListDAO.findTotalCount(certCommonDTO, certEmpListDTO, user);
    }
}

