package dream.consult.comp.wrkcal.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetListDTO;

/**
 * 휴무일 설정  - 목록 service
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDaysetListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultCompWrkcalDaysetListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDaysetListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param consultCompWrkcalCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findDaysetList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteWrkcal(String[] deleteRows) throws Exception;
    
    public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO, User user);
}
