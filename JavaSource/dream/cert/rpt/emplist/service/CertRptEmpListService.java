package dream.cert.rpt.emplist.service;

import java.util.List;

import common.bean.User;

import dream.cert.rpt.emplist.dto.CertRptEmpCommonDTO;

/**
 * 자격증분류 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface CertRptEmpListService
{
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param certRptEmpCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findList(CertRptEmpCommonDTO certRptEmpCommonDTO    ,User user);

    

}
