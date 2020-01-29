package dream.work.pmi.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;

/**
 * 점검작업 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: WorkPmiDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface WorkPmiDetailDAO extends BaseJdbcDaoSupportIntf
{
    public int[] update(final List<WorkPmiDetailDTO> list, final User user);
    
    public int completeDetail(WorkPmiDetailDTO workPmiDetailDTO, User user);
    
    public int completeCancelDetail(WorkPmiDetailDTO workPmiDetailDTO, User user);
    
    public String checkPoint(WorkPmiDetailDTO workPmiDetailDTO,User user);
    
    public int completeSched(WorkPmiDetailDTO workPmiDetailDTO, User user);
    
    public int completeCancelSched(WorkPmiDetailDTO workPmiDetailDTO, User user);
    
    public int executePmUpdate(WorkPmiDetailDTO workPmiDetailDTO, User user) throws Exception;
    
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);
}