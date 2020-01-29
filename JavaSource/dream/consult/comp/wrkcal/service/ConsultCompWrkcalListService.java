package dream.consult.comp.wrkcal.service;

import java.util.List;

import common.bean.User;

import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;

/**
 * 근무일달력 - 목록 service
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultCompWrkcalListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param consultCompWrkcalCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findWrkcalList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompWrkcalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteWrkcal(String[] deleteRows , String[] deleteRowsExt, User user) throws Exception;
    
    public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user);
}
