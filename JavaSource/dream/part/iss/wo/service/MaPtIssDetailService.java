package dream.part.iss.wo.service;

import common.bean.User;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;


/**
 * �������Ȯ�� - �� service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtIssDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public MaPtIssDetailDTO findDetail(MaPtIssCommonDTO maPtIssCommonDTO, User user)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtIssDetailDTO maPtIssDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtIssDetailDTO maPtIssDetailDTO, User loginUser) throws Exception;

    /**
     * �������ó��
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailDTO
     * @param user
     * @return 
     * @throws Exception 
     */
    public String[] confirmIssuePart(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception;

    /**
     * ��� ���
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailDTO
     * @param user
     * @return 
     * @throws Exception 
     */
    public String[] cancelIssuePart(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception;

    public String findStockQty(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception;

    
}
