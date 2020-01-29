package dream.part.iss.wo.dao;

import java.util.List;

import common.bean.User;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 */
public interface PartIssWoItemListDAO
{
    /**
     * grid find
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemListDTO
     * @return List
     */
    public List findPtIssList(PartIssWoItemListDTO partIssWoItemListDTO, User user);
    
    /**
     * delete
     * @author hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePtIss(String compNo, String wcodeId, String partId, String partGrade);
    
    public int deletePtIss(String ptisslistId);

   
}