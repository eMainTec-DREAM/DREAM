package dream.part.iss.wo.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.iss.wo.dao.PartIssWoItemListDAO;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partIssWoItemListDAOTarget"
 * @spring.txbn id="partIssWoItemListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartIssWoItemListDAOOraImpl extends BaseJdbcDaoSupportOra implements PartIssWoItemListDAO
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
    public List findPtIssList(PartIssWoItemListDTO partIssWoItemListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        
        query.append("SELECT                                                          ");
        query.append("       ''      seqNO                                            ");
        query.append("      ,''     isDelCheck                                        ");
        query.append("      ,x.ptisslist_serial_id  PTISSLISTSERIALID                 ");
        query.append("      ,x.ptisslist_id         PTISSLISTID                       ");
        query.append("      ,(SELECT  a.part_id                                       ");
        query.append("             FROM  TAPARTS a                                    ");
        query.append("             WHERE a.part_id = y.part_id) partId                ");
        query.append("          ,(SELECT  a.part_no                                   ");
        query.append("                  FROM  TAPARTS a                               ");
        query.append("                  WHERE a.part_id = y.part_id) partNo           ");
        query.append("      ,(SELECT a.description                                    ");
        query.append("              FROM TAPARTS a                                    ");
        query.append("              WHERE a.part_id = y.part_id) partDesc             ");
        query.append("      ,x.serial_no    serialNo                                  ");
        query.append("      ,x.equip_id     EQUIPID                                   ");
        query.append("          ,(SELECT a.remark                                     ");
        query.append("           FROM TAEQUIPMENT a                                   ");
        query.append("           WHERE a.equip_id=x.equip_id)       remark            ");
        query.append("FROM  TAPTISSLIST_SERIAL x ,TAPTISSLIST y                       ");
        query.append("WHERE 1=1                                                       ");
        query.append("AND x.ptisslist_id=y.ptisslist_id              ");
        query.getAndQuery("y.ptisslist_id", partIssWoItemListDTO.getPtisslistId());
        query.getAndQuery("x.ptisslist_serial_id", partIssWoItemListDTO.getPtisslistSerialId());
        query.getAndQuery("x.part_id",partIssWoItemListDTO.getPartId());
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public int deletePtIss(String compNo, String wcodeId, String partId, String partGrade)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTSTOCK			              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  wcode_id      = '"+wcodeId+"'		  ");
    	query.append("  AND  part_id       = '"+partId+"'		  ");
    	query.append("  AND  part_grade    = '"+partGrade+"'	  ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtIssCommonDTO maPtIssCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
      
        if (!"".equals(maPtIssCommonDTO.getPtisslistId()))
        {
            query.getAndQuery("y.ptisslist_id", maPtIssCommonDTO.getPtisslistId());
            return query.toString();
        }
        
        if(!"".equals(maPtIssCommonDTO.getEquipId()) || !"".equals(maPtIssCommonDTO.getEquipDesc()))
        {
            query.append("  AND y.wkor_id IN (SELECT a.wkor_id              ");
            query.append("                      FROM TAWOEQUIP a, TAEQUIPMENT b                      ");
            query.append("                     WHERE a.equip_id = b.equip_id                        ");
            query.append("                       AND a.wkor_id = y.wkor_id        ");
            query.getCodeLikeQuery("b.equip_id", "b.description", maPtIssCommonDTO.getEquipId(), maPtIssCommonDTO.getEquipDesc());
            query.append("                    )        ");

        }
        
        query.getCodeLikeQuery("y.part_id", "(SELECT a.description FROM TAPARTS a WHERE a.part_id = y.part_id)", maPtIssCommonDTO.getPartId(), maPtIssCommonDTO.getPartDesc());
        query.getLikeQuery("z.wo_no", maPtIssCommonDTO.getWoNo());
        query.getLikeQuery("z.description", maPtIssCommonDTO.getWoDesc());
        query.getCodeLikeQuery("z.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = z.emp_id)", maPtIssCommonDTO.getEmpId(), maPtIssCommonDTO.getEmpDesc());
        query.getAndDateQuery("y.issue_date", maPtIssCommonDTO.getIssDateFrom(), maPtIssCommonDTO.getIssDateTo());
        query.getCodeLikeQuery("y.ptiss_status", "SFACODE_TO_DESC(y.ptiss_status,'PTISS_STATUS','SYS','','"+user.getLangId()+"')", maPtIssCommonDTO.getIssStatus(), maPtIssCommonDTO.getIssStatusDesc());
        
        return query.toString();
    }

    public int deletePtIss(String ptisslistSerialId)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAPTISSLIST_SERIAL                ");
        query.append("WHERE ptisslist_serial_id = '"+ptisslistSerialId+"'  ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    
}