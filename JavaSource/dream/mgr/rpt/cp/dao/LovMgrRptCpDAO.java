package dream.mgr.rpt.cp.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.rpt.cp.dto.LovMgrRptCpDTO;
import dream.mgr.rpt.cp.dto.LovMgrRptCpLogDTO;

/**
 * 출력물 선택
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface LovMgrRptCpDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception;
    
    public String findTotalCount(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception;

    public String[][] executeQuery(String query) throws Exception;

    public int log(LovMgrRptCpLogDTO lovMgrRptCpLogDTO, User user) throws Exception;
}