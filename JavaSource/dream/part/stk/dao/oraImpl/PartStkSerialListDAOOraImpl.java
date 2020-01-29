package dream.part.stk.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.stk.dao.PartStkSerialListDAO;
import dream.part.stk.dto.PartStkSerialListDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partStkSerialListDAOTarget"
 * @spring.txbn id="partStkSerialListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartStkSerialListDAOOraImpl extends BaseJdbcDaoSupportOra implements PartStkSerialListDAO
{
    /**
     * grid find
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partStkSerialListDTO
     * @return List
     */ 
    public List findSerialList(PartStkSerialListDTO partStkSerialListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        //String compNo = user.getCompNo();
        
        query.append("SELECT                                             ");
        query.append("       ''  isDelCheck                              ");
        query.append("      ,''  seqNo                                   ");
        query.append("      ,a.serial_no          serialNo               ");
        query.append("      ,(SELECT b.description FROM TAEQUIPMENT b WHERE a.p_equip_id=b.equip_id)         description             ");
        query.append("      ,SFACODE_TO_DESC(a.eq_status,'EQ_STATUS','SYS','','ko')    equipStatus   ");
        query.append("      ,b.part_id            partId                 ");
        query.append("      ,(SELECT bb.part_no FROM TAPARTS bb WHERE bb.part_id=b.part_id)  partNo  ");
        query.append("FROM  TAEQUIPMENT a inner join TAEQDEVICE b on a.equip_id=b.equip_id          ");
        query.append("WHERE 1=1          ");
        query.append("AND  a.eq_status !='X'         ");
        query.getAndQuery("b.part_id", partStkSerialListDTO.getPartId());

        return getJdbcTemplate().queryForList(query.toString());
    }
    
  

    
}