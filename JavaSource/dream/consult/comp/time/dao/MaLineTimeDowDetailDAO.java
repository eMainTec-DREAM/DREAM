package dream.consult.comp.time.dao;

import common.bean.User;
import dream.consult.comp.time.dto.MaLineTimeDowDetailDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;

/**
 * 요일별 설정 상세 dao
 * @author  kim21017
 * @version $Id: MaLineTimeDowDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaLineTimeDowDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @param user 
     * @param maLineTimeDowListDTO 
     * @since   1.0
     * 
     * @return
     */
    public MaLineTimeDowDetailDTO findDetail(MaLineTimeDowListDTO maLineTimeDowListDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDowDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDowDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user);

    /**
     * 요일 중복 검사
     */
    public String validDay(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user);
    public int deleteWrkTime(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user);
    public int execRunTime(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user);
}