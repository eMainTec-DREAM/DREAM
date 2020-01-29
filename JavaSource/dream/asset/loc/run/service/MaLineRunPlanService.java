package dream.asset.loc.run.service;

import java.util.List;

import common.bean.User;
import dream.asset.loc.run.dto.MaLineRunPlanDTO;

/**
 * 라인가동계획 - 목록 service
 * @author kim21017
 * @version $Id: $
 * @since   1.0
 */
public interface MaLineRunPlanService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: $
     * @param maLineRunPlanDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List<MaLineRunPlanDTO> findList(MaLineRunPlanDTO maLineRunPlanDTO, User user);    
   
    /**
     * delete List
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int[] deleteList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(MaLineRunPlanDTO maLineRunPlanDTO, User user);
    
    public MaLineRunPlanDTO findDetail(MaLineRunPlanDTO maLineRunPlanDTO, User user)throws Exception;

    public int insertDetail(MaLineRunPlanDTO maLineRunPlanDTO, User user) throws Exception;
    
    public int[] insertDetail(List<MaLineRunPlanDTO> list, User user) throws Exception;
    
    public int updateDetail(MaLineRunPlanDTO maLineRunPlanDTO, User user) throws Exception;

    public String validLineRunPlan(MaLineRunPlanDTO maLineRunPlanDTO, User user) throws Exception;

}
