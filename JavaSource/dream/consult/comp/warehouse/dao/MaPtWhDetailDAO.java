package dream.consult.comp.warehouse.dao;

import common.bean.User;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;
import dream.consult.comp.warehouse.dto.MaPtWhDetailDTO;

/**
 * ��ǰâ�� - �� dao
 * 
 * @author ssong
 * @version $Id: MaPtWhDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 */
public interface MaPtWhDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhCommonDTO
     * @return
     */
    public MaPtWhDetailDTO findDetail(MaPtWhCommonDTO maPtWhCommonDTO, User user);
    
    public int insertDetail(MaPtWhDetailDTO maPtWhDetailDTO);
    
    /**
     * detail stock
     * stock_qty�� ������Ʈ ���� �ʴ´�. �԰�Ȯ��,��ҽ� ���ν��� ���� �����. 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhDetailDTO
     * @return
     */
    public int updateDetail(MaPtWhDetailDTO maPtWhDetailDTO);
}