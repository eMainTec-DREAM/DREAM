package dream.cert.list.dao;

import java.util.List;

import common.bean.User;

import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpListDTO;

/**
 * 자격증 사원 목록 dao
 * @author  kim21017
 * @version $Id: CertEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface CertEmpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: CertEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param certCommonDTO
     * @param certEmpListDTO
     * @param loginUser
     * @return List
     */
    public List findEmpList(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User loginUser);

    /**
     * delete
     * @author kim21017
     * @version $Id: CertEmpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteEmpList(String id, User loginUser);

    public String findTotalCount(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User user) throws Exception;
}