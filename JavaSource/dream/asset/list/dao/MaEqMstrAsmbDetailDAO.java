package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비부위 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrAsmbDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqAsmbId
     * @param compNo
     * @return
     */
    public MaEqMstrAsmbDetailDTO findDetail(MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    public int[] updateDetail(final List<MaEqMstrAsmbDetailDTO> list, final User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAsmbDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    /**
     * reset full description
     * @author kim21017
     * @version $Id: MaEqMstrAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param compNo
     * @return
     */
    public int resetEqAsmbFullDesc(String compNo);
    
    //복사하기
    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user);

}