package dream.work.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.list.dto.WorkListCinsPlanMstrDetailDTO;

/**
 * WorkListCinsPlanMstr Page - Detail DAO
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkListCinsPlanMstrDetailDAO extends BaseJdbcDaoSupportIntf       
{
    /**
     * FIND DETAIL
     * @param workListCinsPlanMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkListCinsPlanMstrDetailDTO findDetail(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workListCinsPlanMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param workListCinsPlanMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user) throws Exception;
    
    public String checkList(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO);
    
    
// 확정버튼 관련
    public String checkPoint(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO,User user);
    
    public int getPmInsdPointCnt(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO);
    public int inserPmInsdPoint(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO);
    public int completeDetail(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: workListCinsPlanMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCinsPlanMstrDetailDTO
     * @return
     */
    public int completeSched(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO);
    
}
