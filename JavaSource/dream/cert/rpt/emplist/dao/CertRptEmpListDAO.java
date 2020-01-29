package dream.cert.rpt.emplist.dao;

import java.util.List;

import common.bean.User;

import dream.cert.rpt.emplist.dto.CertRptEmpCommonDTO;

/**
 * 자격증분류 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface CertRptEmpListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     *
     * @param certRptEmpCommonDTO
     * @return List
     */
    public List findList(CertRptEmpCommonDTO certRptEmpCommonDTO    ,User user);

    
}