package dream.work.pm.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsDetailDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;

/**
 * WorkPmiCIns Page - Detail DAO
 * @author youngjoo38
 * @version $Id: WorkPmiCInsDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmiCInsDetailDAO extends BaseJdbcDaoSupportIntf       
{
    /**
     * FIND DETAIL
     * @param workPmiCInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmiCInsDetailDTO findDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workPmiCInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param workPmiCInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workPmiCInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePmInsDSched(WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception;
    
    
    public String checkList(WorkPmiCInsCommonDTO workPmiCInsCommonDTO);
    
    
// 확정버튼 관련
    public String checkPoint(WorkPmiCInsDetailDTO workPmiCInsDetailDTO,User user);
    
    public int completeDetail(WorkPmiCInsDetailDTO workPmiCInsDetailDTO);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: workPmiCInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiCInsDetailDTO
     * @return
     */
    public int completeSched(WorkPmiCInsDetailDTO workPmiCInsDetailDTO);
    
    /**
     *  SP_PM_UPDATE_LASTCPLT_DATE 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmiCInsDetailDTO
     * @return
     * @throws Exception
     */
    public int executePmUpdate(WorkPmiCInsDetailDTO workPmiCInsDetailDTO) throws Exception;
    
}
