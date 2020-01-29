package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrPmListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmListDTO;

/**
 * 설비 점검 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPmListDAOTarget"
 * @spring.txbn id="maEqMstrPmListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPmListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrPmListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrPmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmListDTO
     * @param loginUser
     * @return List
     */
    public List findInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("       ''									AS seqNo				");
        query.append("      ,'' 								AS isDelCheck			");
        query.append("      ,x.pm_id 							AS pmId					");
        query.append("      ,y.pm_point_id 						AS pmPointId			");
        query.append("      ,x.pm_no 							AS pmNo					");
        query.append("      ,x.description 						AS pmDesc				");
        query.append("      ,x.cycle||			");
        query.append("			SFACODE_TO_DESC(x.period_type,'PERIOD_TYPE','SYS','','"+loginUser.getLangId()+"')	AS periodType	");
        query.append("      ,x.dept_id 							AS deptId				");
        query.append("      ,(SELECT a.description 										");
        query.append("      	FROM TADEPT a											");
        query.append("      	WHERE a.comp_no = x.comp_no								");
        query.append("      	AND a.dept_id = x.dept_id ) 	AS deptDesc				");
        query.append("      ,x.emp_id 							AS empId				");
        query.append("      ,(SELECT a.emp_name 										");
        query.append("      	FROM TAEMP a											");
        query.append("      	WHERE a.comp_no = x.comp_no								");
        query.append("      	AND a.emp_id = x.emp_id ) 		AS empDesc				");
        query.append("      ,x.pm_type 							AS pmTypeId				");
        query.append("		,SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+loginUser.getLangId()+"')	AS pmTypeDesc	");
        query.append("      ,y.step_num 						AS stepNum				");
        query.append("      ,y.eqasmb_id 						AS eqAsmbId				");
        query.append("      ,(SELECT a.full_desc 										");
        query.append("      	FROM TAEQASMB a											");
        query.append("      	WHERE a.comp_no = y.comp_no								");
        query.append("      	AND a.is_use = 'Y'										");
        query.append("      	AND a.eqasmb_id = y.eqasmb_id ) AS eqAsmbDesc			");
        query.append("      ,y.check_point 						AS checkPoint			");
        query.append("      ,y.fit_basis 						AS fitBasis				");
        query.append("      ,y.remark	 						AS remark				");
        query.append("      ,(SELECT MAX(a.pminslist_id) 								");
        query.append("          FROM TAPMINSLIST a 										");
        query.append("         WHERE a.comp_no = x.comp_no 								");
        query.append("           AND a.pm_id = x.pm_id)     	AS pminslistId			");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMPOINT y						");
        query.append("ON  x.comp_no = y.comp_no											");
        query.append("AND x.pm_id = y.pm_id												");
        query.append("WHERE 1=1															");
        query.append("AND y.is_active = 'Y'         ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrPmListDTO,loginUser));

        query.getOrderByQuery("x.pm_id , y.step_num,y.pm_point_id", maEqMstrPmListDTO.getOrderBy(), maEqMstrPmListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrPmListDTO.getIsLoadMaxCount(), maEqMstrPmListDTO.getFirstRow()));
    }
    
    public List findRplList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       ''                                 AS seqNo                ");
        query.append("      ,''                                 AS isDelCheck           ");
        query.append("      ,x.pm_id                            AS pmId                 ");
        query.append("      ,x.pm_no                            AS pmNo                 ");
        query.append("      ,x.description                      AS pmDesc               ");
        query.append("      ,x.cycle||          ");
        query.append("          SFACODE_TO_DESC(x.period_type,'PERIOD_TYPE','SYS','','"+loginUser.getLangId()+"')   AS periodType   ");
        query.append("      ,x.dept_id                          AS deptId               ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("          FROM TADEPT a                                           ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("          AND a.dept_id = x.dept_id )     AS deptDesc             ");
        query.append("      ,x.emp_id                           AS empId                ");
        query.append("      ,(SELECT a.emp_name                                         ");
        query.append("          FROM TAEMP a                                            ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("          AND a.emp_id = x.emp_id )       AS empDesc              ");
        query.append("      ,x.pm_type                          AS pmTypeId             ");
        query.append("      ,SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+loginUser.getLangId()+"') AS pmTypeDesc   ");
        query.append("      ,z.part_no                           AS partNo                   ");
        query.append("      ,z.description                       AS partDesc      ");
        query.append("      ,z.pt_size                           AS ptSize        ");
        query.append("      ,z.model                             AS model         ");
        query.append("      ,y.use_qty                           AS useQty        ");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMPART y                                                ");
        query.append("ON  x.comp_no = y.comp_no                 ");
        query.append("AND x.pm_id = y.pm_id                     ");
        query.append("INNER JOIN TAPARTS z                                                ");
        query.append("ON  y.comp_no = z.comp_no                 ");
        query.append("AND y.part_id = z.part_id                     ");
        query.append("WHERE 1=1                                                         ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrPmListDTO,loginUser));

        query.getOrderByQuery("x.pm_no , x.description", maEqMstrPmListDTO.getOrderBy(), maEqMstrPmListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrPmListDTO.getIsLoadMaxCount(), maEqMstrPmListDTO.getFirstRow()));
    }
    
    public String findInsTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SELECT									");
    	query.append("		count(1)							");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMPOINT y		");
        query.append("ON  x.comp_no = y.comp_no					");
        query.append("AND x.pm_id = y.pm_id						");
        query.append("WHERE 1=1									");
        query.append("AND y.is_active = 'Y'         ");
    	query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrPmListDTO, loginUser));

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    public String findRplTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maEqMstrCommonDTO.getCompNo();
        
        query.append("SELECT                                    ");
        query.append("      count(1)                            ");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMPART y        ");
        query.append("ON  x.comp_no = y.comp_no                 ");
        query.append("AND x.pm_id = y.pm_id                     ");
        query.append("INNER JOIN TAPARTS z                                                ");
        query.append("ON  y.comp_no = z.comp_no                 ");
        query.append("AND y.part_id = z.part_id                     ");
        query.append("WHERE 1=1                                 ");

        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrPmListDTO, loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.wo_type", maEqMstrPmListDTO.getWoTypeId());
    	if(!"".equals(maEqMstrPmListDTO.getWoTypeId())) {
        	query.append("  AND  x.pm_type IN (SELECT aa.cdsysd_no FROM TACDSYSD aa   	");
        	query.append("                                WHERE aa.is_use = 'Y'			");
        	query.getAndQuery("aa.list_type", maEqMstrPmListDTO.getWoTypeId()+"_TYPE");
        	query.getInequalityQuery("aa.cdsysd_no", "!=", maEqMstrPmListDTO.getExceptedPmTypeId());
        	query.append("                                )         					");
    	}
    	query.append("AND x.pm_id IN (SELECT a.pm_id						");
    	query.append("					FROM TAPMEQUIP a					");
    	query.append("					WHERE 1=1							");
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
    	query.append("					AND a.equip_id IN (SELECT b.equip_id FROM TAEQUIPMENT b 					");
    	query.append("										WHERE 1=1 												");
    	query.getAndQuery("b.comp_no", loginUser.getCompNo());
    	query.append("										AND b.item_no = (SELECT c.item_no FROM TAEQUIPMENT c 	");
    	query.append("										WHERE 1=1 												");
    	query.getAndQuery("c.comp_no", loginUser.getCompNo());
    	query.getAndQuery("c.equip_id", maEqMstrCommonDTO.getEquipId());
    	query.append("										)														");
    	query.append(" and(b.is_last_version = 'Y' OR revision_status = 'W')"); //재정인 경우 데이터가 보여야 함
//    	query.getAndQuery("b.is_last_version", "Y");
    	query.append("									)					");
    	query.append("AND a.is_deleted = 'N'                                ");
    	query.append("					)									");
		query.append("AND x.is_last_version = 'Y'              			    ");
    	query.append("AND x.is_active = 'Y'     							");
    	query.append("AND x.is_deleted = 'N'     							");
    	query.append("AND y.is_deleted = 'N'     							");
    	
    	return query.toString();
    }
    
}