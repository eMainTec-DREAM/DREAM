package dream.mgr.rpt.cp.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.rpt.cp.dto.LovMgrRptCpDTO;

/**
 * 출력물 선택 Service
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 */
public interface LovMgrRptCpService
{
    public List findList(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception;
    
    public String findTotalCount(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception;

    public Map makeReport(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception;
}