package dream.work.cal.pmperiod.service;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;

/**
 * �����۾�����(�Ⱓ) - ��� service
 * @author  kim21017
 * @version $Id: MaWoSchedListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoSchedListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoSchedListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoSchedCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findSchedList(MaWoSchedCommonDTO maWoSchedCommonDTO, User user);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoSchedListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteSched(String[] deleteRows, User user) throws Exception;
    

    
    /**
     * ������ ����
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param gridList
     * @param user
     */
    public void updateSchedule(List gridList, User user);
    
    public String findTotalCount(MaWoSchedCommonDTO maWoSchedCommonDTO, User user);
    
}
