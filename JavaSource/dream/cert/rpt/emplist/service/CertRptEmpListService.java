package dream.cert.rpt.emplist.service;

import java.util.List;

import common.bean.User;

import dream.cert.rpt.emplist.dto.CertRptEmpCommonDTO;

/**
 * �ڰ����з� - ��� service
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
     * @return ��ȸ ���
     * @throws Exception
     */
    public List findList(CertRptEmpCommonDTO certRptEmpCommonDTO    ,User user);

    

}
