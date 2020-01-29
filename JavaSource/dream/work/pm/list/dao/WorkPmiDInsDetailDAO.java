package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;

/**
 * WorkPmiDIns Page - Detail DAO
 * @author youngjoo38
 * @version $Id: WorkPmiDInsDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmiDInsDetailDAO extends BaseJdbcDaoSupportIntf       
{
    /**
     * INSERT DETAIL
     * @param workPmiDInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, WorkPmiDInsDetailDTO workPmiDInsDetailDTO, User user) throws Exception;
    
// 확정버튼 관련
    public String checkPoint(WorkPmiDInsDetailDTO workPmiDInsDetailDTO,User user);
    
    public int completeDetail(WorkPmiDInsDetailDTO workPmiDInsDetailDTO);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: workPmiDInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiDInsDetailDTO
     * @return
     */
    public int completeSched(WorkPmiDInsDetailDTO workPmiDInsDetailDTO);
    
    /**
     *  SP_PM_UPDATE_LASTCPLT_DATE 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmiDInsDetailDTO
     * @return
     * @throws Exception
     */
    public int executePmUpdate(WorkPmiDInsDetailDTO workPmiDInsDetailDTO) throws Exception;
    public int[] update(List<WorkPmiDInsDetailDTO> list, User user) throws Exception;
    
}
