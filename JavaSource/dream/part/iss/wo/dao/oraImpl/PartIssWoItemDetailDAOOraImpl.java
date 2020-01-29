package dream.part.iss.wo.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.part.iss.wo.dao.PartIssWoItemDetailDAO;
import dream.part.iss.wo.dto.PartIssWoItemDetailDTO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;

/**
 * 자재출고확정 - 상세 dao
 * 
 * @author hyosung
 * @version $Id: PartIssWoItemDetailDAO.java,v 1.0 2015/12/02 08:25:47 hyosung Exp $
 * @since 1.0
 * @spring.bean id="partIssWoItemDetailDAOTarget"
 * @spring.txbn id="partIssWoItemDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartIssWoItemDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements PartIssWoItemDetailDAO
{
    /**
     * detail find
     * @author hyosung
     * @version $Id:$
     * @since   1.0
     *  
     * @param partIssWoItemCommonDTO
     * @return
     */
    public PartIssWoItemDetailDTO findDetail(PartIssWoItemListDTO partIssWoItemListDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("          x.ptisslist_serial_id              ptisslistSerialId            ");
        query.append("          ,x.ptisslist_id                    ptisslistId                  ");
        query.append("          ,(SELECT  a.part_id                                             ");
        query.append("                  FROM  TAPARTS a                                         ");
        query.append("                  WHERE a.part_id = y.part_id) partId                     ");
        query.append("          ,(SELECT  a.part_no                                             ");
        query.append("                  FROM  TAPARTS a                                         ");
        query.append("                  WHERE a.part_id = y.part_id) partNo                     ");
        query.append("          ,(SELECT a.description                                          ");
        query.append("                   FROM TAPARTS a                                         ");
        query.append("                   WHERE a.part_id = y.part_id) partDesc                  ");
        query.append("          ,x.serial_no    serialNo                                        ");
        query.append("          ,x.equip_id     equipId                                         ");
        query.append("          ,(SELECT a.eq_status                                            ");
        query.append("            FROM TAEQUIPMENT  a                                           ");
        query.append("            WHERE a.equip_id =x.equip_id)     eqStatus                    ");
        query.append("          ,(SELECT a.remark                                               ");
        query.append("           FROM TAEQUIPMENT a                                             ");
        query.append("           WHERE a.equip_id=x.equip_id)       remark                      ");
        query.append("FROM  TAPTISSLIST_SERIAL x ,TAPTISSLIST y                                 ");
        query.append("WHERE 1=1                                                                 ");
        query.append("      AND y.ptisslist_id=x.ptisslist_id                               ");
        query.getAndQuery("x.ptisslist_serial_id", partIssWoItemListDTO.getPtisslistSerialId());


        PartIssWoItemDetailDTO partIssWoItemDetailDTO = 
        		(PartIssWoItemDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new PartIssWoItemDetailDTO()));
        
        return partIssWoItemDetailDTO;
    }
    
    /**
     * detail insert
     * @author hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailDTO
     * @return
     */
    public int insertDetail(PartIssWoItemDetailDTO partIssWoItemDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
        Object[] objects = null;
        
        query.append("INSERT INTO TAPTISSLIST_SERIAL(             ");
        query.append("    comp_no                                 ");
        query.append("    ,ptisslist_serial_id                    ");
        query.append("    ,ptisslist_id                           ");
        query.append("    ,part_id                                ");
        query.append("    ,serial_no                              ");
        query.append("    ,equip_id                               ");
        query.append(")VALUES(                                    ");
        query.append("     ?                                      ");
        query.append("    ,?                                      ");
        query.append("    ,?                                      ");
        query.append("    ,(SELECT                                ");
        query.append("              x.part_id                     ");
        query.append("      FROM TAPTISSLIST x                    ");  
        query.append("      WHERE x.ptisslist_id= ?)              ");
        query.append("    ,?                                      ");
        query.append("    ,?                                      ");
        query.append(")                                           ");

        
        objects = new Object[] {
                partIssWoItemDetailDTO.getCompNo()
               ,partIssWoItemDetailDTO.getPtisslistSerialId()
               ,partIssWoItemDetailDTO.getPtisslistId()
               ,partIssWoItemDetailDTO.getPtisslistId()
               ,partIssWoItemDetailDTO.getSerialNo()
               ,partIssWoItemDetailDTO.getEquipId()
        };
            
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    public int insertRemark(PartIssWoItemDetailDTO partIssWoItemDetailDTO, User loginUser)
    {
        //해당 순환자재에 equip_id를 통한 remark 설정.
        QueryBuffer query = new QueryBuffer();
        Object[] objects = null;
        
        query.append("      UPDATE TAEQUIPMENT SET                ");
        query.append("             remark      =       ?          ");
        query.append("      WHERE equip_id     =       ?          ");

        objects = new Object[] {
                partIssWoItemDetailDTO.getRemark()
               ,partIssWoItemDetailDTO.getEquipId()
        };
            
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    

    public int updateDetail(PartIssWoItemDetailDTO partIssWoItemDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAPTISSLIST_SERIAL SET                        ");
        query.append("   serial_no                       = ?               ");
        query.append("WHERE ptisslist_serial_id          = ?               ");

        
        Object[] objects = new Object[] {
                    partIssWoItemDetailDTO.getSerialNo()
                    ,partIssWoItemDetailDTO.getPtisslistSerialId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
        
    }

    

    
    
    public PartIssWoItemDetailDTO serialCheck(PartIssWoItemDetailDTO partIssWoItemDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                             ");
        query.append("      x.serial_no         serialNo ");
        query.append("FROM TAPTISSLIST_SERIAL x          ");
        query.append("WHERE 1=1                          ");
        query.getAndQuery("x.ptisslist_id", partIssWoItemDetailDTO.getPtisslistId());
        query.getAndQuery("x.serial_no", partIssWoItemDetailDTO.getSerialNo());

        PartIssWoItemDetailDTO detailDTO = 
                (PartIssWoItemDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new PartIssWoItemDetailDTO()));
        
        
       return detailDTO;
       //QueryBuffer.haveData(getJdbcTemplate().queryForList(query.toString()))
       
    }
    
}