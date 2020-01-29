package dream.part.iss.wo.service;

import common.bean.User;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.iss.wo.dto.PartIssWoItemDetailDTO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;


/**
 * 자재출고확정 - 상세 service
 * 
 * @author hyosung
 * @version $Id:$
 * @since 1.0
 */
public interface PartIssWoItemDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemListDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public PartIssWoItemDetailDTO findDetail(PartIssWoItemListDTO partIssWoItemListDTO, User user)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(PartIssWoItemDetailDTO partIssWoItemDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(PartIssWoItemDetailDTO partIssWoItemDetailDTO, User loginUser) throws Exception;

    /**
     * serial check
     * @author hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailDTO
     * @return
     * @throws Exception
     */
    public PartIssWoItemDetailDTO serialCheck(PartIssWoItemDetailDTO partIssWoItemDetailDTO) throws Exception;
   
}
