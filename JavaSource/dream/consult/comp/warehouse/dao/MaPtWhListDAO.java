package dream.consult.comp.warehouse.dao;

import java.util.List;

import common.bean.User;

import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;

/**
 * 부품창고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtWhListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhCommonDTO
     * @return List
     */
    public List findPtWhList(MaPtWhCommonDTO maPtWhCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePtWh(String wcodeId, String compNo, User user);
    
    public String findTotalCount(MaPtWhCommonDTO maPtWhCommonDTO, User user);
}