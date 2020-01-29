package dream.cert.list.dao;

import common.bean.User;

import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpDetailDTO;

/**
 * 자격증 사원 상세 dao
 * @author  kim21017
 * @version $Id: CertEmpDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface CertEmpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: CertEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param wkOrId
     * @param certEmpId
     * @param compNo
     * @return
     */
    public CertEmpDetailDTO findDetail(String certListId, User loginUser);

    /**
     * detail update
     * @author kim21017
     * @version $Id: CertEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param certEmpDetailDTO
     * @param certCommonDTO
     * @return
     */
    public int updateDetail(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: CertEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param certEmpDetailDTO
     * @param certCommonDTO
     * @return
     */
    public int insertDetail(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser);

    /**
     * 사원확인..
     */
    public String validEmp(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser);
}