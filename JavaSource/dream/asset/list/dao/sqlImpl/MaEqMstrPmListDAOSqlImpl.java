package dream.asset.list.dao.sqlImpl;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class MaEqMstrPmListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrPmListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT															");
        query.append("       ''									AS SEQNO				");
        query.append("      ,'' 								AS ISDELCHECK			");
        query.append("      ,x.pm_id 							AS PMID					");
        query.append("      ,y.pm_point_id 						AS PMPOINTID			");
        query.append("      ,x.pm_no 							AS PMNO					");
        query.append("      ,x.description 						AS PMDESC				");
        query.append("      ,convert(char,x.cycle) +			");
        query.append("			dbo.SFACODE_TO_DESC(x.period_type,'PERIOD_TYPE','SYS','','"+loginUser.getLangId()+"')	AS PERIODTYPE	");
        query.append("      ,x.DEPT_ID 							AS DEPTID				");
        query.append("      ,(SELECT a.description 										");
        query.append("      	FROM TADEPT a											");
        query.append("      	WHERE a.comp_no = x.comp_no								");
        query.append("      	AND a.dept_id = x.dept_id ) 	AS DEPTDESC				");
        query.append("      ,x.EMP_ID 							AS EMPID				");
        query.append("      ,(SELECT a.emp_name 										");
        query.append("      	FROM TAEMP a											");
        query.append("      	WHERE a.comp_no = x.comp_no								");
        query.append("      	AND a.emp_id = x.emp_id ) 		AS EMPDESC				");
        query.append("      ,x.pm_type 							AS PMTYPEID				");
        query.append("		,dbo.SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+loginUser.getLangId()+"')	AS PMTYPEDESC	");
        query.append("      ,y.step_num 						AS STEPNUM				");
        query.append("      ,y.eqasmb_id 						AS EQASMBID				");
        query.append("      ,(SELECT a.full_desc 										");
        query.append("      	FROM TAEQASMB a											");
        query.append("      	WHERE a.comp_no = y.comp_no								");
        query.append("      	AND a.is_use = 'Y'										");
        query.append("      	AND a.eqasmb_id = y.eqasmb_id ) AS EQASMBDESC			");
        query.append("      ,y.check_point 						AS 'CHECKPOINT'			");
        query.append("      ,y.fit_basis 						AS FITBASIS				");
        query.append("      ,y.remark	 						AS REMARK				");
        query.append("      ,(SELECT MAX(a.pminslist_id) 								");
        query.append("          FROM TAPMINSLIST a 										");
        query.append("         WHERE a.comp_no = x.comp_no 								");
        query.append("           AND a.pm_id = x.pm_id)     	AS pminslistId			");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMPOINT y								");
        query.append("ON  x.comp_no = y.comp_no											");
        query.append("AND x.pm_id = y.pm_id												");
        query.append("WHERE 1=1															");
        query.append("AND y.is_active = 'Y'         ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrPmListDTO,loginUser));

        query.getOrderByQuery("x.pm_id", "x.pm_id , y.step_num,y.pm_point_id", maEqMstrPmListDTO.getOrderBy(), maEqMstrPmListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrPmListDTO.getIsLoadMaxCount(), maEqMstrPmListDTO.getFirstRow()));
    }
    
    public List findRplList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       ''                                 AS SEQNO                ");
        query.append("      ,''                                 AS ISDELCHECK           ");
        query.append("      ,x.pm_id                            AS PMID                 ");
        query.append("      ,x.pm_no                            AS PMNO                 ");
        query.append("      ,x.description                      AS PMDESC               ");
        query.append("      ,convert(char,x.cycle)+          ");
        query.append("          dbo.SFACODE_TO_DESC(x.period_type,'PERIOD_TYPE','SYS','','"+loginUser.getLangId()+"')   AS PERIODTYPE   ");
        query.append("      ,x.DEPT_ID                          AS DEPTID               ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("          FROM TADEPT a                                           ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("          AND a.dept_id = x.dept_id )     AS DEPTDESC             ");
        query.append("      ,x.EMP_ID                           AS EMPID                ");
        query.append("      ,(SELECT a.emp_name                                         ");
        query.append("          FROM TAEMP a                                            ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("          AND a.emp_id = x.emp_id )       AS EMPDESC              ");
        query.append("      ,x.pm_type                          AS PMTYPEID             ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+loginUser.getLangId()+"') AS PMTYPEDESC   ");
        query.append("      ,z.part_no                           AS PARTNO                   ");
        query.append("      ,z.description                       AS PARTDESC      ");
        query.append("      ,z.pt_size                           AS PTSIZE        ");
        query.append("      ,z.model                             AS MODEL         ");
        query.append("      ,y.use_qty                           AS USEQTY        ");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMPART y                                                ");
        query.append("ON  x.comp_no = y.comp_no                 ");
        query.append("AND x.pm_id = y.pm_id                     ");
        query.append("INNER JOIN TAPARTS z                                                ");
        query.append("ON  y.comp_no = z.comp_no                 ");
        query.append("AND y.part_id = z.part_id                     ");
        query.append("WHERE 1=1                                                         ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrPmListDTO,loginUser));

        query.getOrderByQuery("x.pm_id", "x.pm_no , x.description", maEqMstrPmListDTO.getOrderBy(), maEqMstrPmListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrPmListDTO.getIsLoadMaxCount(), maEqMstrPmListDTO.getFirstRow()));
    }
    
    public String findInsTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
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
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public String findRplTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
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
        return QuerySqlBuffer.listToString(resultList);
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.wo_type", maEqMstrPmListDTO.getWoTypeId());
    	if(!"".equals(maEqMstrPmListDTO.getWoTypeId())) {
            query.append("  AND  x.pm_type IN (SELECT aa.cdsysd_no FROM TACDSYSD aa   	");
            query.append("                                WHERE aa.is_use = 'Y'     	");
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
    	query.getAndQuery("b.is_last_version", "Y");
    	query.append("									)					");
    	query.append("AND a.is_deleted = 'N'                                ");
    	query.append("					)									");
		query.append("AND x.is_last_version = 'Y'              			    ");
        query.append("AND x.is_active = 'Y'     							");
        query.append("AND x.is_deleted = 'N'                                ");
        query.append("AND y.is_deleted = 'N'                                ");
    	
    	return query.toString();
    }
    
}