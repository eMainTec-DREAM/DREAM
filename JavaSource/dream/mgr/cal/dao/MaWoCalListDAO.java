package dream.mgr.cal.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dto.MaWoCalCommonDTO;

/**
 * Working Calendar - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaWoCalListDAO
{
    /**
     * grid find
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoCalCommonDTO
     * @return List
     */
    public List findList(MaWoCalCommonDTO maWoCalCommonDTO, User user);

    /**
     * �ٹ����� 
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int dayOnCalList(String compNo, String id, User user);
    
    /**
     * �ٹ����� 
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int dayOnLnList(String compNo, String id, User user);

    /**
     * �޹����� 
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int dayOffList(String compNo, String id, User user);
    
    /**
     * �޹����� 
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int popupSave(String compNo, String userNo, String plant, String changeDate);

	public String findTotalCount(MaWoCalCommonDTO maWoCalCommonDTO, User user);
	
}