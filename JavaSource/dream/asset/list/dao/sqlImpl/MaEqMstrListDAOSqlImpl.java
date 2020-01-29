package dream.asset.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.MaEqMstrListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비마스터 - 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrListDAOTarget"
 * @spring.txbn id="maEqMstrListDAO" 
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maEqMstrCommonDTO.getCompNo();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              	");
        query.append("SELECT                                                           	");
        query.append("      ''                                          seqNo          	");
        query.append("      ,''                                         isDelCheck     	");
        query.append("      ,x.equip_id                                 equipId        	");
        query.append("      ,x.item_no                                  itemNo         	");
        query.append("      ,CASE x.eqctg_type WHEN 'MD' THEN '('+CONVERT(VARCHAR,ISNULL(x.old_eq_no,''))+')'+x.description ELSE x.description END equipDesc     ");
        query.append("      ,(SELECT full_desc                                          ");
        query.append("         FROM TAEQLOC                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc      	");
        query.append("      ,(SELECT full_desc                                          ");
        query.append("         FROM TAEQCTG                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc      	");
        query.append("      ,x.maker                                    maker          	");
        query.append("      ,x.model_no                                 modelNo        	");
        query.append("      ,dbo.SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')  plfTypeDesc		");
        query.append("      ,x.eqctg_type                               eqCtgType      	");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqCtgTypeDesc	");
        query.append("      ,x.is_law_eq                                isLawEq        	");
        query.append("      ,(SELECT description                                        ");
        query.append("         FROM TADEPT                                              ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND dept_id = x.dept_id)                deptDesc       	");
        query.append("      ,(SELECT description                                        ");
        query.append("         FROM TAPLANT                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND plant = x.plant)                    plantDesc       ");
        query.append("      ,(SELECT emp_name                                           ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName    	");
        query.append("      ,(SELECT emp_name                                           ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName     	");
        query.append("      ,x.dept_id                                  deptId         	");
        query.append("      ,x.excel_no                                 excelNo        	");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')	eqstatus    	");
        query.append("      ,(SELECT param1 FROM TACDSYSD WHERE list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) param	");
        query.append("      ,x.revision_status						    revisionStatusId	");
        query.append("      ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    ");
        query.append("      ,x.is_last_version                          isLastVersion   ");
        query.append("      ,x.prod_shape                               prodShape    	");
        query.append("      ,x.old_eq_no                                oldEqNo    		");
        
      //예방점검,정기교체 갯수
        query.append("  ,(select top 1 case when count(*) > 0 then 'Y' else 'N' end                 ");
        query.append("    from tapmequip aa inner join tapmlst bb on aa.comp_no = bb.comp_no  ");
        query.append("                            and aa.pm_id = bb.pm_id                     ");
        query.append("                            and bb.is_active = 'Y'                      ");
        query.append("                            and aa.is_active = 'Y'                      ");
        query.append("                            and bb.is_last_version = 'Y'                ");
        query.append("                            and bb.wo_type = 'PMI'                      ");
        query.append("                            and bb.is_deleted = 'N'                     ");
        query.append("                            and aa.is_deleted = 'N'                     ");
        query.append("    where x.comp_no = aa.comp_no                                        ");
        query.append("        and x.equip_id = aa.equip_id                                    ");
        query.append("    ) as inspection                                                     ");
        
        query.append("  ,(select top 1 case when count(*) > 0 then 'Y' else 'N' end                 ");
        query.append("    from tapmequip aa inner join tapmlst bb on aa.comp_no = bb.comp_no  ");
        query.append("                            and aa.pm_id = bb.pm_id                     ");
        query.append("                            and bb.is_active = 'Y'                      ");
        query.append("                            and aa.is_active = 'Y'                      ");
        query.append("                            and bb.is_last_version = 'Y'                ");
        query.append("                            and bb.wo_type = 'PMW'                      ");
        query.append("                            and bb.is_deleted = 'N'                     ");
        query.append("                            and aa.is_deleted = 'N'                     ");
        query.append("    where x.comp_no = aa.comp_no                                        ");
        query.append("        and x.equip_id = aa.equip_id                                    ");
        query.append("    ) as regularReplace                                                  ");
        
        //예방점검,정기교체 갯수
        query.append(" ,(   SELECT                                                      ");
        query.append("        COUNT(1)                                                  ");
        query.append("    FROM TAPMLST q LEFT OUTER JOIN TAPMPOINT y                    ");
        query.append("    ON  q.comp_no = y.comp_no                                     ");
        query.append("    AND q.pm_id = y.pm_id                                         ");
        query.append("    WHERE 1=1                                                     ");
        query.append("    AND y.is_active = 'Y'                                         ");
        query.append("    AND q.pm_id IN (SELECT a.pm_id FROM TAPMEQUIP a               ");
        query.append("                             WHERE 1=1                            ");
        query.append("                             AND a.comp_no = x.comp_no            ");
        query.append("                             AND a.equip_id = x.equip_id          ");
        query.append("                             AND x.is_last_version ='Y' )         ");
        query.append("                    )                             inspection      ");
        query.append("  ,(  SELECT                                                      ");
        query.append("              COUNT(1)                                            ");
        query.append("        FROM TAPMLST q LEFT OUTER JOIN TAPMPART y                 ");
        query.append("        ON  q.comp_no = y.comp_no                                 ");
        query.append("        AND q.pm_id = y.pm_id                                     ");
        query.append("        INNER JOIN TAPARTS z                                      ");
        query.append("        ON  y.comp_no = z.comp_no                                 ");
        query.append("        AND y.part_id = z.part_id                                 ");
        query.append("        WHERE 1=1                                                 ");
        query.append("        AND q.pm_id IN (SELECT a.pm_id                            ");
        query.append("                            FROM TAPMEQUIP a                      ");
        query.append("                            WHERE 1=1                             ");
        query.append("                            AND a.comp_no = x.comp_no             ");
        query.append("                            AND a.equip_id = x.equip_id           ");
        query.append("                            AND x.is_last_version ='Y'  )         ");
        query.append("                        )                          regularReplace ");
        
        //설비등급
        query.append(",x.eq_grade                                        eqGradeDesc    ");
//        query.append(", SFACODE_TO_DESC(x.eq_grade,'EQ_GRADE','SYS','','"+user.getLangId()+"') eqGradeDesc");
        query.append("      ,x.eqstrloc_no       eqStrLocNo       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.setup_date        setupDate        ");
        query.append("      ,x.capacity            capacity       ");
        query.append("      ,x.serial_no           serialNo       ");
        query.append("      ,x.supplier            supplier       ");
        query.append("      ,x.country_maker     countryMaker     ");
        query.append("      ,x.util_capa             utilCapa     ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy   ");
        query.append("      ,x.upd_date            updDate        						");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy   ");
        query.append("      ,x.cre_date            creDate        						");
        query.append("      ,(SELECT y.description FROM TAWKCTR y WHERE x.comp_no = y.comp_no AND x.wkctr_id = y.wkctr_id) wkctrDesc");
        query.append("     	,x.buy_date										buyDate		");
        query.append("      ,x.remark                                       remark      ");
        query.append("      ,x.revisionhist_id						    	revisionHistId	");
        query.append("FROM   TAEQUIPMENT x												");
        query.append(this.getWhere(maEqMstrCommonDTO, user));

        query.getOrderByQuery("x.equip_id", "x.ord_no", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrCommonDTO.getIsLoadMaxCount(), maEqMstrCommonDTO.getFirstRow()));
}
    
    public String getColums(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                ");
        query.append("SELECT                                                           ");
        query.append("      ''                                          seqNo          ");
        query.append("      ,''                                         isDelCheck     ");
        query.append("      ,x.equip_id                                 equipId        ");
        query.append("      ,x.item_no                                  itemNo         ");
        query.append("      ,CASE x.eqctg_type WHEN 'MD' THEN '('+CONVERT(VARCHAR,ISNULL(x.old_eq_no,''))+')'+x.description ELSE x.description END equipDesc     ");
        query.append("      ,(SELECT full_desc                                         ");
        query.append("         FROM TAEQLOC                                            ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc      ");
        query.append("      ,(SELECT full_desc                                         ");
        query.append("         FROM TAEQCTG                                            ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc      ");
        query.append("      ,x.maker                                    maker          ");
        query.append("      ,x.model_no                                 modelNo        ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')  plfTypeDesc");
        query.append("      ,x.eqctg_type                               eqCtgType      ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqCtgTypeDesc");
        query.append("      ,x.is_law_eq                                isLawEq        ");
        query.append("      ,(SELECT description                                       ");
        query.append("         FROM TADEPT                                             ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND dept_id = x.dept_id)                deptDesc       ");
        query.append("      ,(SELECT description                                       ");
        query.append("         FROM TAPLANT                                            ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND plant = x.plant)                    plantDesc      ");
        query.append("      ,(SELECT emp_name                                          ");
        query.append("         FROM TAEMP                                              ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName    ");
        query.append("      ,(SELECT emp_name                                          ");
        query.append("         FROM TAEMP                                              ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName     ");
        query.append("      ,x.dept_id                                  deptId         ");
        query.append("      ,x.excel_no                                 excelNo        ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')	eqstatus    ");
        query.append("      ,(select param1 from tacdsysd where list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) param");
        query.append("      ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    ");
        query.append("      ,x.is_last_version                          isLastVersion  ");
        query.append("      ,x.prod_shape                               prodShape      ");
        query.append("      ,x.old_eq_no                                oldEqNo    	   ");
        query.append("      ,x.eqstrloc_no       						eqStrLocNo     ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.setup_date        						setupDate      ");
        query.append("      ,x.capacity            						capacity       ");
        query.append("      ,x.serial_no           						serialNo       ");
        query.append("      ,x.supplier            						supplier       ");
        query.append("      ,x.country_maker     						countryMaker   ");
        query.append("      ,x.util_capa             					utilCapa       ");
        query.append("		,x.eq_grade            						eqGradeDesc    ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                          ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id              ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy  ");
        query.append("      ,x.upd_date           						updDate        ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                          ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id              ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy  ");
        query.append("      ,x.cre_date           						creDate        ");
        query.append("      ,x.tag_no                                	tagNo    	   ");
        query.append("      ,x.eq_size                                  eqSize		   ");
        query.append("      ,x.as_vendor                                asVendor       ");
        query.append("      ,x.weight                                   weight		   ");
        query.append("      ,x.eq_spec                                  eqSpec		   ");
        query.append("      ,(SELECT y.description FROM TAWKCTR y WHERE x.comp_no = y.comp_no AND x.wkctr_id = y.wkctr_id) wkctrDesc");
        query.append("      ,x.storage                                	storage    	   ");
        query.append("      ,x.guaranty_date        					guarantyDate   ");
        query.append("      ,x.buy_date                             	buyDate		   ");
        query.append("      ,x.remark                                   remark         ");
        
        return query.toString();
    }   
    
    public String getTables(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    
    	query.append("FROM  TAEQUIPMENT x                                              ");
    	
    	return query.toString();
    }
    
    public String getOrderBy(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getOrderByQuery("x.equip_id", "x.ord_no, x.equip_id ASC", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
    	
    	return query.toString();
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqMachMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
        /**생산설비*/
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maEqMstrCommonDTO.getCompNo();

        query.append(getColums(maEqMstrCommonDTO, user));
        query.append(getTables(maEqMstrCommonDTO, user));
        query.append(this.getWhere(maEqMstrCommonDTO, user));
        query.append("AND   x.eqctg_type='MC'                                   	   ");
        query.append(getOrderBy(maEqMstrCommonDTO, user));
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrCommonDTO.getIsLoadMaxCount(), maEqMstrCommonDTO.getFirstRow()));
}
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqUtilMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
        /**생산설비*/
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maEqMstrCommonDTO.getCompNo();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                           ");
        query.append("      ''                                          seqNo          ");
        query.append("      ,''                                         isDelCheck     ");
        query.append("      ,x.equip_id                                 equipId        ");
        query.append("      ,x.item_no                                  itemNo         ");
        query.append("      ,CASE x.eqctg_type WHEN 'MD' THEN '('+CONVERT(VARCHAR,ISNULL(x.old_eq_no,''))+')'+x.description ELSE x.description END	equipDesc     ");
        query.append("      ,(SELECT full_desc                                         ");
        query.append("         FROM TAEQLOC                                            ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc      ");
        query.append("      ,(SELECT full_desc                                         ");
        query.append("         FROM TAEQCTG                                            ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc      ");
        query.append("      ,x.maker                                    maker          ");
        query.append("      ,x.model_no                                 modelNo        ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')  		plfTypeDesc			");
        query.append("      ,x.eqctg_type                               eqCtgType      ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  	eqCtgTypeDesc		");
        query.append("      ,x.is_law_eq                                isLawEq        ");
        query.append("      ,(SELECT description                                       ");
        query.append("         FROM TADEPT                                             ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND dept_id = x.dept_id)                deptDesc       ");
        query.append("      ,(SELECT description                                       ");
        query.append("         FROM TAPLANT                                            ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND plant = x.plant)                    plantDesc      ");
        query.append("      ,(SELECT emp_name                                          ");
        query.append("         FROM TAEMP                                              ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName    ");
        query.append("      ,(SELECT emp_name                                          ");
        query.append("         FROM TAEMP                                              ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName     ");
        query.append("      ,x.dept_id                                  deptId         ");
        query.append("      ,x.excel_no                                 excelNo        ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')				eqstatus    ");
        query.append("      ,(SELECT param1 FROM TACDSYSD WHERE list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) 	param		");
        query.append("      ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    ");
        query.append("      ,x.is_last_version                                  isLastVersion        ");
        query.append("      ,x.prod_shape                               prodShape    ");
        query.append("      ,x.old_eq_no                               oldEqNo    ");
        query.append("      ,x.eqstrloc_no       eqStrLocNo       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.setup_date        setupDate        ");
        query.append("      ,x.capacity            capacity       ");
        query.append("      ,x.serial_no           serialNo       ");
        query.append("      ,x.supplier            supplier       ");
        query.append("      ,x.country_maker     countryMaker     ");
        query.append("      ,x.util_capa             utilCapa     ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy   ");
        query.append("      ,x.upd_date            updDate        						");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy   ");
        query.append("      ,x.cre_date            creDate        						");
        query.append("      ,(SELECT y.description FROM TAWKCTR y WHERE x.comp_no = y.comp_no AND x.wkctr_id = y.wkctr_id) wkctrDesc");
        query.append("      ,x.remark                                  remark          ");
        query.append("FROM  TAEQUIPMENT x                                              ");
        query.append(this.getWhere(maEqMstrCommonDTO, user));
        query.append("AND   x.eqctg_type='UT'                                    	   ");

        query.getOrderByQuery("x.equip_id", "x.ord_no", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrCommonDTO.getIsLoadMaxCount(), maEqMstrCommonDTO.getFirstRow()));
}
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqBuildMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
        /**토지,건물 */    
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maEqMstrCommonDTO.getCompNo();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                             ");
        query.append("       ''                                          seqNo           ");
        query.append("      ,''                                          isDelCheck      ");
        query.append("      ,x.equip_id                                  equipId         ");
        query.append("      ,x.item_no                                   itemNo          ");
        query.append("      ,CASE x.eqctg_type WHEN 'MD' THEN '('+CONVERT(VARCHAR,ISNULL(x.old_eq_no,''))+')'+x.description ELSE x.description END equipDesc     ");
        query.append("      ,(SELECT full_desc                                           ");
        query.append("         FROM TAEQLOC                                              ");
        query.append("        WHERE comp_no = x.comp_no                                  ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc        ");
        query.append("      ,(SELECT full_desc                                           ");
        query.append("         FROM TAEQCTG                                              ");
        query.append("        WHERE comp_no = x.comp_no                                  ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtg           ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')  plfTypeDesc");
        query.append("      ,x.eqctg_type                                eqCtgType       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqCtgTypeDesc");
        query.append("      ,(SELECT description                                         ");
        query.append("         FROM TADEPT                                               ");
        query.append("        WHERE comp_no = x.comp_no                                  ");
        query.append("          AND dept_id = x.dept_id)                deptDesc         ");
        query.append("      ,(SELECT description                                         ");
        query.append("         FROM TAPLANT                                              ");
        query.append("        WHERE comp_no = x.comp_no                                  ");
        query.append("          AND plant = x.plant)                    plantDesc        ");
        query.append("      ,(SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                                ");
        query.append("        WHERE comp_no = x.comp_no                                  ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName      ");
        query.append("      ,(SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                                ");
        query.append("        WHERE comp_no = x.comp_no                                  ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName       ");
        query.append("      ,x.remark                                   remark           ");
        query.append("      ,y.addr                                     buildAddres      ");
        query.append("      ,y.area                                     buildArea        ");
        query.append("      ,y.struct                                   buildStruct      ");
        query.append("      ,y.FLOOR                                    buildFloor       ");
        query.append("      ,y.section                                  buildSection     ");
        query.append("      ,x.dept_id                                   deptId          ");
        query.append("      ,x.excel_no                                  excelNo         ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')  eqstatus    ");
        query.append("      ,(SELECT param1 FROM TACDSYSD WHERE list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) param   ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    ");
        query.append("      ,x.is_last_version                                  isLastVersion        ");
        query.append("      ,x.prod_shape                               prodShape    ");
        query.append("      ,x.old_eq_no                               oldEqNo    ");
        query.append("      ,x.eqstrloc_no       eqStrLocNo       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.setup_date        setupDate        ");
        query.append("      ,x.capacity            capacity       ");
        query.append("      ,x.serial_no           serialNo       ");
        query.append("      ,x.supplier            supplier       ");
        query.append("      ,x.country_maker     countryMaker     ");
        query.append("      ,x.util_capa             utilCapa     ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy   ");
        query.append("      ,x.upd_date            updDate        ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy   ");
        query.append("      ,x.cre_date            creDate        ");
        query.append("      ,(SELECT y.description FROM TAWKCTR y WHERE x.comp_no = y.comp_no AND x.wkctr_id = y.wkctr_id) wkctrDesc");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQBUILDING y                                                                   ");
        query.append("	ON x.comp_no=y.comp_no                                                                         ");
        query.append(" AND x.equip_id=y.equip_id                                                                       ");
        query.append(this.getWhere(maEqMstrCommonDTO, user));
        query.append("       AND x.eqctg_type='BD'                                    	");
        query.getLikeQuery("y.addr", maEqMstrCommonDTO.getEqBuildAddr());
        query.getOrderByQuery("x.equip_id", "x.ord_no", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrCommonDTO.getIsLoadMaxCount(), maEqMstrCommonDTO.getFirstRow()));
}
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqToolMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
        /**Tool */    
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maEqMstrCommonDTO.getCompNo();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                            ");
        query.append("      ''                                          seqNo          	");
        query.append("      ,''                                         isDelCheck     	");
        query.append("      ,x.equip_id                                 equipId        	");
        query.append("      ,x.item_no                                  itemNo         	");
        query.append("      ,CASE x.eqctg_type WHEN 'MD' THEN '('+CONVERT(VARCHAR,ISNULL(x.old_eq_no,''))+')'+x.description ELSE x.description END equipDesc     ");
        query.append("      ,(SELECT full_desc                                          ");
        query.append("         FROM TAEQLOC                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc      	");
        query.append("      ,(SELECT full_desc                                          ");
        query.append("         FROM TAEQCTG                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc     	");
        query.append("      ,x.maker                                    maker         	");
        query.append("      ,y.min_unit_value                            minUnitValue   ");
        query.append("      ,x.model_no                                 modelNo        	");
        query.append("      ,dbo.SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')  plfTypeDesc		");
        query.append("      ,x.eqctg_type                               eqCtgType      	");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqCtg2			");
        query.append("      ,x.is_law_eq                               isLawEq        	");
        query.append("      ,(SELECT description                                        ");
        query.append("         FROM TADEPT                                              ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND dept_id = x.dept_id)                deptDesc      	");
        query.append("      ,(SELECT description                                        ");
        query.append("         FROM TAPLANT                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND plant = x.plant)                    plantDesc       ");
        query.append("      ,(SELECT emp_name                                           ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName    	");
        query.append("      ,(SELECT emp_name                                           ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName    	");
        query.append("      ,x.dept_id                                  deptId        	");
        query.append("      ,x.excel_no                                 excelNo         ");
        query.append("      ,x.p_equip_id                               parentEquipId	");
        query.append("		,(SELECT item_no 											");
        query.append("			FROM TAEQUIPMENT a 										");
        query.append("			WHERE a.comp_no = x.comp_no 							");
        query.append("			AND a.equip_id = x.p_equip_id) parentItemNo			");
        query.append("      ,(SELECT xx.description FROM TAEQUIPMENT xx where xx.equip_id=x.p_equip_id) parentEquipDesc    	");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')eqstatus    		");
        query.append("      ,y.guage_type                               guaTypeId       ");
        query.append("      ,y.measure_unit                             measureunit     ");
        query.append("      ,y.all_range                              	totalRange      ");
        query.append("      ,y.accuracy                              	accuracy        ");
        query.append("      ,y.use_range                              	measureRange    ");
        query.append("      ,y.tolerance                              	allowValue      ");
        query.append("      ,dbo.SFACODE_TO_DESC(y.guage_type,'GUAGE_TYPE','USR','"+compNo+"','ko') guaTypeDesc        			");
        query.append("      ,(SELECT param1 FROM TACDSYSD WHERE list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) param	");
        query.append("      ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    ");
        query.append("      ,x.is_last_version                                  isLastVersion        ");
        query.append("      ,x.prod_shape                               prodShape    ");
        query.append("      ,x.old_eq_no                               oldEqNo    ");
        query.append("      ,x.eqstrloc_no       eqStrLocNo       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.setup_date        setupDate        ");
        query.append("      ,x.capacity            capacity       ");
        query.append("      ,x.serial_no           serialNo       ");
        query.append("      ,x.supplier            supplier       ");
        query.append("      ,x.country_maker     countryMaker     ");
        query.append("      ,x.util_capa             utilCapa     ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy   ");
        query.append("      ,x.upd_date            updDate        						");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy   ");
        query.append("      ,x.cre_date            creDate        						");
        query.append("      ,x.usage_dept                           usageDeptId         ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("         FROM TADEPT a                                            ");
        query.append("        WHERE a.comp_no = x.comp_no                               ");
        query.append("          AND a.dept_id = x.usage_dept)       usageDeptDesc       ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("         FROM TADEPT a                                             ");
        query.append("        WHERE a.comp_no = x.comp_no                               ");
        query.append("          AND a.dept_id =  (SELECT xx.usage_dept FROM TAEQUIPMENT xx WHERE xx.equip_id=x.p_equip_id))       pUsageDeptDesc       ");
        query.append("      ,(SELECT y.description FROM TAWKCTR y WHERE x.comp_no = y.comp_no AND x.wkctr_id = y.wkctr_id) wkctrDesc");
        query.append("      , dbo.SFACODE_TO_DESC(x.eq_grade,'EQ_GRADE','SYS','','"+user.getLangId()+"') TOOLGRADE                    ");
        query.append("		,y.cal_point								CalPoint		");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQTOOL y                        ");
        query.append("  ON 	x.comp_no=y.comp_no                                         ");
        query.append(" AND 	x.equip_id=y.equip_id                                       ");
        query.append(this.getWhere(maEqMstrCommonDTO, user));
        query.append("AND x.eqctg_type='TL'                                    		");

        query.getOrderByQuery("x.equip_id", "x.ord_no", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrCommonDTO.getIsLoadMaxCount(), maEqMstrCommonDTO.getFirstRow()));
}
    
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrMoldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqMoldMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)
    {
        /**금형*/
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maEqMstrCommonDTO.getCompNo();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                            ");
        query.append("      ''                                           seqNo          ");
        query.append("      ,''                                          isDelCheck     ");
        query.append("      ,x.equip_id                                  equipId        ");
        query.append("      ,x.item_no                                   itemNo         ");
        query.append("      ,x.description                               equipDesc      ");
        query.append("      ,(SELECT full_desc                                          ");
        query.append("         FROM TAEQLOC                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqloc_id = x.eqloc_id)               eqLocDesc      ");
        query.append("      ,(SELECT full_desc                                          ");
        query.append("         FROM TAEQCTG                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqctg_id = x.eqctg_id)               eqCtgDesc      ");
        query.append("      ,x.maker                                     maker          ");
        query.append("      ,x.model_no                                  modelNo        ");
        query.append("      ,dbo.SFACODETODESC(x.plf_type,'PLF_TYPE','SYS','')   plfTypeDesc");
        query.append("      ,x.is_law_eq                                isLawEq        	");
        query.append("      ,(SELECT description                                        ");
        query.append("         FROM TADEPT                                              ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND dept_id = x.dept_id)                deptDesc       	");
        query.append("      ,(SELECT description                                        ");
        query.append("         FROM TAPLANT                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND plant = x.plant)                    plantDesc       ");
        query.append("      ,(SELECT emp_name                                           ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName    	");
        query.append("      ,(SELECT emp_name                                           ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName      ");
        query.append("      ,x.old_eq_no                                OLDMOLDNO       ");
        query.append("      ,y.storage                                  STORAGE         ");
        query.append("      ,y.setcnt                                   SETCNT          ");
        query.append("      ,y.cavity                                   CAVITY          ");
        query.append("      ,y.output                                   DAILYOUTPUT     ");
        query.append("      ,y.stepno                                   STEPNO          ");
        query.append("      ,x.setup_date                               SETUPDATE       ");
        query.append("      ,x.buy_amt                                  BUYAMT       ");
        query.append("      ,y.ownership                                OWNERSHIP       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    ");
        query.append("      ,x.is_last_version                                  isLastVersion        ");
        query.append("      ,x.prod_shape                               prodShape    ");
        query.append("      ,x.old_eq_no                               oldEqNo    ");
        query.append("      ,x.eqstrloc_no       eqStrLocNo       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.capacity            capacity       ");
        query.append("      ,x.serial_no           serialNo       ");
        query.append("      ,x.supplier            supplier       ");
        query.append("      ,x.country_maker     countryMaker     ");
        query.append("      ,x.util_capa             utilCapa     ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy   ");
        query.append("      ,x.upd_date            updDate        						");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy   ");
        query.append("      ,x.cre_date            creDate        						");
        query.append("      ,(SELECT a.description FROM TAWKCTR a WHERE x.comp_no = a.comp_no AND x.wkctr_id = a.wkctr_id) wkctrDesc");
        query.append("      ,dbo.SFACODE_TO_DESC(y.eqmold_atype, 'EQMOLD_ATYPE', 'USR', x.comp_no, '"+user.getLangId()+"') eqMoldAtypeDesc       ");
        query.append("      ,dbo.SFACODE_TO_DESC(y.eqmold_btype, 'EQMOLD_BTYPE', 'USR', x.comp_no, '"+user.getLangId()+"') eqMoldBtypeDesc       ");
        query.append("      ,dbo.SFACODE_TO_DESC(y.eqmold_ctype, 'EQMOLD_CTYPE', 'USR', x.comp_no, '"+user.getLangId()+"') eqMoldCtypeDesc       ");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQMOLD y                        ");
        query.append("  ON x.comp_no = y.comp_no                                     	");
        query.append(" AND x.equip_id = y.equip_id                                   	");
        query.append(this.getWhere(maEqMstrCommonDTO,user));
        query.append(" AND x.EQCTG_TYPE = 'MD'                                       	");
        query.getCodeLikeQuery("y.eqmold_atype", "dbo.SFACODE_TO_DESC(y.eqmold_atype, 'EQMOLD_ATYPE', 'USR', '"+user.getCompNo()+"', '"+user.getLangId()+"')", maEqMstrCommonDTO.getFilterEqMoldAtype(), maEqMstrCommonDTO.getFilterEqMoldAtypeDesc());
        query.getCodeLikeQuery("y.eqmold_btype", "dbo.SFACODE_TO_DESC(y.eqmold_btype, 'EQMOLD_BTYPE', 'USR', '"+user.getCompNo()+"', '"+user.getLangId()+"')", maEqMstrCommonDTO.getFilterEqMoldBtype(), maEqMstrCommonDTO.getFilterEqMoldBtypeDesc());
        query.getCodeLikeQuery("y.eqmold_ctype", "dbo.SFACODE_TO_DESC(y.eqmold_ctype, 'EQMOLD_CTYPE', 'USR', '"+user.getCompNo()+"', '"+user.getLangId()+"')", maEqMstrCommonDTO.getFilterEqMoldCtype(), maEqMstrCommonDTO.getFilterEqMoldCtypeDesc());
        query.getOrderByQuery("x.equip_id", "x.ord_no", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqITList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                                            		");
        query.append("        ''                                     seqNo          		        ");
        query.append("      , ''                                     isDelCheck     		        ");
        query.append("      , x.equip_id                             itEqId        		        ");
        query.append("      , x.item_no                              itEqNo         		        ");
        query.append("      , (CASE x.eqctg_type WHEN 'MD' THEN '('+x.old_eq_no+')'+x.description ELSE x.description END ) itEqDesc		");
        query.append("      , x.eqctg_type                           eqCtgType					");
        query.append("      , dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqCtgTypeDesc		");
        query.append("      , x.model_no                             model						");
        query.append("      , x.serial_no                            serialNo   					");
        query.append("      , x.buy_date                             buyDate						");
        query.append("      , x.eq_spec                              Specification 				");
        query.append("      , ( SELECT a.ipaddr FROM TAEQIT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)   ipAddr		");
        query.append("      , (SELECT a.full_desc                                           		");
        query.append("          FROM TAEQLOC  a                                           		");
        query.append("         WHERE a.comp_no = x.comp_no                                 		");
        query.append("           AND a.eqloc_id = x.eqloc_id)        itEqLocDesc    				");
        query.append("      , x.dept_id                              deptId						");
        query.append("      ,  (SELECT a.description                                         	");
        query.append("          FROM TADEPT a                                              		");
        query.append("         WHERE a.comp_no = x.comp_no                                 		");
        query.append("           AND a.dept_id = x.dept_id)          deptDesc					");
        query.append("      , (SELECT description                                               ");
        query.append("          FROM TAPLANT                                                    ");
        query.append("         WHERE comp_no = x.comp_no                                        ");
        query.append("           AND plant = x.plant)                plantDesc                  ");
        query.append("      , x.main_mng_id                          mainMngId					");
        query.append("      , (SELECT a.emp_name                                            		");
        query.append("          FROM TAEMP a                                              		");
        query.append("         WHERE a.comp_no = x.comp_no                                 		");
        query.append("           AND a.emp_id = x.main_mng_id)       mainMngName					");
        query.append("      , x.usage_dept                           usageDeptId					");
        query.append("      ,  (SELECT a.description                                         	");
        query.append("          FROM TADEPT a                                              		");
        query.append("         WHERE a.comp_no = x.comp_no                                 		");
        query.append("           AND a.dept_id = x.usage_dept)       usageDeptDesc				");
        query.append("      , x.usage_emp                            usageEmp					");
        query.append("      , (SELECT a.emp_name                                            		");
        query.append("          FROM TAEMP a                                              		");
        query.append("         WHERE a.comp_no = x.comp_no                                 		");
        query.append("           AND a.emp_id = x.usage_emp)         usageEmpName    			");
        query.append("      , dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    		");
        query.append("      , x.is_last_version                      isLastVersion				");
        query.append("      , (SELECT param1 FROM TACDSYSD WHERE list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) param		");
        query.append("      , y.os_name 								osName		");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy   ");
        query.append("      ,x.upd_date            updDate        ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy   ");
        query.append("      ,x.cre_date            						creDate         ");
        query.append("FROM   TAEQUIPMENT x  LEFT JOIN TAEQIT y							");
        query.append("ON x.comp_no = y.comp_no 											");
        query.append("AND x.equip_id = y.equip_id        								");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.getAndQuery("x.eqctg_type", "IT");
        query.getOrderByQuery("x.equip_id", "x.ord_no", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(maEqMstrCommonDTO.getIsLoadMaxCount(), maEqMstrCommonDTO.getFirstRow()));
    } 
    
    public String  findITTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT					");
    	query.append("		count(1)			");
    	query.append("FROM   TAEQUIPMENT x  LEFT JOIN TAEQIT y		");
        query.append("ON x.comp_no = y.comp_no 		");
        query.append("AND x.equip_id = y.equip_id        		");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.getAndQuery("x.eqctg_type", "IT");
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public List findEqGNList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                                           	");
        query.append("        ''                                    seqNo          		");
        query.append("      , ''                                    isDelCheck     		");
        query.append("      , x.equip_id                            equipId        		");
        query.append("      , x.item_no                             itemNo         		");
        query.append("      , x.description                         equipDesc     		");
        query.append("      , x.eqctg_type                          eqCtgType   		");
        query.append("      , dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqCtgTypeDesc		");
        query.append("      , x.usage_dept                          usageDeptId 		");
        query.append("      , (SELECT aa.description									");
        query.append("         FROM tadept aa											");
        query.append("         WHERE aa.comp_no = x.comp_no								");
        query.append("           AND aa.dept_id = x.usage_dept)		usageDeptDesc   	");
        query.append("  	, x.usage_emp                           usageEmpId  		");
        query.append("      , (SELECT aa.emp_name										");
        query.append("         FROM taemp aa											");
        query.append("         WHERE aa.comp_no = x.comp_no								");
        query.append("           AND aa.emp_id = x.usage_emp)    	usageEmpName      	");
        query.append("      , x.dept_id                             deptId          	");
        query.append("      , (SELECT aa.description									");
        query.append("         FROM tadept aa											");
        query.append("         WHERE aa.comp_no = x.comp_no								");
        query.append("           AND aa.dept_id = x.dept_id)     	deptDesc  			");
        query.append("    	, x.maker								maker				");
        query.append("    	, x.MODEL_no							model				");
        query.append("    	, x.buy_date							buyDate				");
        query.append("    	, x.buy_amt								buyAmt				");
        query.append("      , x.plant                               plantId				");
        query.append("      , (SELECT aa.description									");
        query.append("      	FROM taplant aa											");
        query.append("        	WHERE aa.comp_no = x.comp_no							");
        query.append("         	  AND aa.plant = x.plant)         	plant  				");
        query.append("FROM   TAEQUIPMENT x												");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.getAndQuery("x.eqctg_type", "GN");
        query.getOrderByQuery("x.equip_id", "x.ord_no", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
     
        return getJdbcTemplate().queryForList(query.toString(maEqMstrCommonDTO.getIsLoadMaxCount(), maEqMstrCommonDTO.getFirstRow()));
    } 
    
    public String  findGNTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT									");
    	query.append("		count(1)							");
    	query.append("FROM   TAEQUIPMENT x  LEFT JOIN TAEQIT y	");
        query.append("ON x.comp_no = y.comp_no 					");
        query.append("AND x.equip_id = y.equip_id        		");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.getAndQuery("x.eqctg_type", "GN");
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
    public String  findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT					");
    	query.append("		count(1)			");
    	query.append("FROM   TAEQUIPMENT x		");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int updateDeleteTag(String id, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAEQUIPMENT SET		                     ");
    	query.append("         IS_DELETED = 'Y'                          ");
    	query.append("        ,description = '[Deleted] ' + description  ");
        query.append("        ,DELETE_BY  = ?                            ");
        query.append("        ,DELETE_TIME = ?                           ");
        query.append("WHERE 1 = 1                 						 ");
    	query.append("  AND comp_no	 = ?		                         ");
    	query.append("  AND equip_id = ?		                         ");
    	
    	Object[] objects = new Object[]{
    			loginUser.getEmpId()
    			,DateUtil.getDate()
    			,loginUser.getCompNo()
    			,id
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        
        String startDate = maEqMstrCommonDTO.getFilterStartDate();
        String endDate = maEqMstrCommonDTO.getFilterEndDate();
        
        query.append("WHERE 1 = 1                                                      ");
        
        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maEqMstrCommonDTO.getEquipId()))
        {
            query.getAndQuery("x.equip_id", maEqMstrCommonDTO.getEquipId());
            return query.toString();
        }
        
        // 개정일자
        if (!"".equals(maEqMstrCommonDTO.getFilterStartDate()) || !"".equals(maEqMstrCommonDTO.getFilterEndDate()))
        {
            query.append(" AND EXISTS (SELECT 1                                             ");
            query.append("               FROM TAREVISIONHIST y                              ");
            query.append("              WHERE y.comp_no = '"+ user.getCompNo() +"'          ");
            query.append("                AND y.revision_object_type = 'ASSET'              ");
            query.append(" AND REPLACE(SUBSTRING(y.wrk_date, 1, 10),'-','') > CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2, GETDATE()), 112)    ");
            query.append(" AND REPLACE(SUBSTRING(y.wrk_date, 1, 10),'-','') <= CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2+6, GETDATE()), 112) ");
            query.append(" AND x.revisionhist_id = y.revisionhist_id                        ");
            query.append(" )                                                                ");
        }
        
        if (!"".equals(maEqMstrCommonDTO.getFilterIsPmIns()))
        {
            if("Y".equals(maEqMstrCommonDTO.getFilterIsPmIns()))
                query.append(" AND EXISTS                                                       ");
            else 
                query.append(" AND NOT EXISTS                                                   ");
            
            query.append("                    (SELECT 1         ");
            query.append("                     FROM   TAPMLST a INNER JOIN TAPMEQUIP b ON a.pm_id = b.pm_id   ");
            query.append("                     WHERE b.equip_id = x.equip_id    ");
            query.append("                       AND a.is_last_version = 'Y'  ");
            query.append("                       AND a.is_deleted = 'N'   ");
            query.append("                    )                             ");

        }
        
        query.getAndDateQuery("y.wrk_date", startDate, endDate);
        
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        query.getLikeQuery("x.description", maEqMstrCommonDTO.getFilterEquipDesc());
        query.getLikeQuery("x.is_law_eq", maEqMstrCommonDTO.getFilterIsLawEq());
        query.getLikeQuery("x.maker", maEqMstrCommonDTO.getFilterMaker());
        query.getLikeQuery("x.model_no", maEqMstrCommonDTO.getFilterModelNo());
        query.getLikeQuery("x.old_eq_no", maEqMstrCommonDTO.getFilterOldEqNo());
        query.getLikeQuery("x.prod_shape", maEqMstrCommonDTO.getFilterProdShape());
        
        //상태
        query.getCodeLikeQuery("x.eq_status","dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')", maEqMstrCommonDTO.getEqStatusId(), maEqMstrCommonDTO.getEqStatusDesc());;

        //위치
        query.getEqLocLevelQuery("x.eqloc_id", maEqMstrCommonDTO.getFilterEqLocId(), maEqMstrCommonDTO.getFilterEqLocDesc(), compNo);

        //종류
        query.getEqCtgLevelQuery("x.eqctg_id", maEqMstrCommonDTO.getFilterEqCtgId(), maEqMstrCommonDTO.getFilterEqCtgDesc(), compNo);
        //설비중요도
        query.getSysCdQuery("x.eq_grade", maEqMstrCommonDTO.getFilterEqGradeId(), maEqMstrCommonDTO.getFilterEqGradeDesc(), "EQ_GRADE", compNo,user.getLangId());
        //최신 version 여부
        query.getSysCdQuery("x.is_last_version", maEqMstrCommonDTO.getFilterIsLastVersionId(), maEqMstrCommonDTO.getFilterIsLastVersionDesc(), "IS_USE", compNo,user.getLangId());
        //문서번호
        if(!"".equals(maEqMstrCommonDTO.getFilterDocNo())){
        	query.append("AND x.equip_id IN (SELECT object_id FROM TAREVISIONHIST WHERE 1=1 AND comp_no = '"+user.getCompNo()+"' AND revision_object_type='ASSET' 	");
        	query.getLikeQuery("doc_no", maEqMstrCommonDTO.getFilterDocNo());
        	query.append("					)																														");
        }
        //Revision No
        if(!"".equals(maEqMstrCommonDTO.getFilterRevNo())){
        	query.append("AND x.equip_id IN (SELECT object_id FROM TAREVISIONHIST WHERE 1=1 AND comp_no = '"+user.getCompNo()+"' AND revision_object_type='ASSET' 	");
        	query.getLikeQuery("rev_no", maEqMstrCommonDTO.getFilterRevNo());
        	query.append("					)																														");
        }
        //설비유형
        query.getSysCdQuery("x.eqctg_type", maEqMstrCommonDTO.getFilterEqCtgTypeId(), maEqMstrCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", compNo,user.getLangId());
        //내,외자 구분
        query.getSysCdQuery("x.plf_type", maEqMstrCommonDTO.getFilterPlfTypeId(), maEqMstrCommonDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo,user.getLangId());
        //관리부서
        query.getDeptLevelQuery("x.dept_id", maEqMstrCommonDTO.getFilterDeptId(), maEqMstrCommonDTO.getFilterDeptDesc(), compNo);
        //관리자(정)
        query.getCodeLikeQuery("x.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.main_mng_id AND a.comp_no='"+compNo+"')", 
        		maEqMstrCommonDTO.getFilterMainMngId(), maEqMstrCommonDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("x.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.sub_mng_id AND a.comp_no='"+compNo+"')", 
        		maEqMstrCommonDTO.getFilterSubMngId(), maEqMstrCommonDTO.getFilterSubMngName());
        //회계자산
        if(!"".equals(maEqMstrCommonDTO.getFilterAccAssetId()) || !"".equals(maEqMstrCommonDTO.getFilterAccAssetDesc())){
            query.append("AND x.equip_id IN (SELECT equip_id FROM TAEQASSET WHERE 1=1 AND comp_no = '"+user.getCompNo()+"'  ");
            query.append("                                                            AND asset_id IN (SELECT asset_id FROM TAASSET WHERE 1=1           ");
            query.getCodeLikeQuery("asset_id", "description", maEqMstrCommonDTO.getFilterAccAssetId(), maEqMstrCommonDTO.getFilterAccAssetDesc());
            query.append("                  )                                                           )                                                           ");
        }
        
        //설비번호
        query.getLikeQuery("x.item_no", maEqMstrCommonDTO.getFilterEquipNo());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
        		maEqMstrCommonDTO.getFilterPlantId(), maEqMstrCommonDTO.getFilterPlantDesc());
        
        // Serial 번호
        query.getLikeQuery("x.serial_no", maEqMstrCommonDTO.getFilterSerialNo());

        // 구입일자
        query.getAndDateQuery("x.buy_date", maEqMstrCommonDTO.getFilterStartBuyDate(), maEqMstrCommonDTO.getFilterEndBuyDate());

        // 사양
        query.getLikeQuery("x.eq_spec", maEqMstrCommonDTO.getFilterSpecification());
        
        // 사용자
        query.getCodeLikeQuery("x.usage_emp", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.usage_emp AND a.comp_no='"+user.getCompNo()+"')", 
        		maEqMstrCommonDTO.getFilterUserId(), maEqMstrCommonDTO.getFilterUserName());
        
        // Tag번호
        query.getLikeQuery("x.tag_no", maEqMstrCommonDTO.getFilterTagNo());
        
        // 생성일자        
        if(!"".equals(maEqMstrCommonDTO.getFilterFromCreDate()) && !"".equals(maEqMstrCommonDTO.getFilterToCreDate())){
        	String fromCreDate = maEqMstrCommonDTO.getFilterFromCreDate();
        	String toCreDate = maEqMstrCommonDTO.getFilterToCreDate();
        	query.append(" AND x.cre_date >= '"+fromCreDate+"' ");
        	query.append(" AND x.cre_date < '"+toCreDate+"' ");
        }
        
        // SIA 등급
        query.getSysCdQuery("x.pmi_action_type", maEqMstrCommonDTO.getFilterPmiActionTypeId(), maEqMstrCommonDTO.getFilterPmiActionTypeDesc(), "PMI_ACTION_TYPE", compNo,user.getLangId());
        
        // 사용부서
        query.getDeptLevelQuery("x.usage_dept", maEqMstrCommonDTO.getFilterUsageDeptId(), maEqMstrCommonDTO.getFilterUsageDeptDesc(), compNo);
        
        //상위사용부서
        query.getDeptLevelQuery("(SELECT a.usage_dept FROM TAEQUIPMENT a WHERE a.equip_id =  x.p_equip_id)", maEqMstrCommonDTO.getFilterPUsageDeptId(), maEqMstrCommonDTO.getFilterPUsageDeptDesc(), compNo);
        
        // 상위 설비번호
        if(!"".equals(maEqMstrCommonDTO.getFilterPEquipNo()))
        {
	        query.append("AND x.p_equip_id IN (SELECT a.equip_id FROM taequipment a         	");
	        query.append("                      WHERE a.comp_no = x.comp_no         			");
	        query.getLikeQuery("a.item_no", maEqMstrCommonDTO.getFilterPEquipNo() );
	        query.append("                                )        								");
        }
        
        //상위설비
        query.getCodeLikeQuery("x.p_equip_id", "(SELECT a.description FROM TAEQUIPMENT a where a.equip_id=x.p_equip_id AND a.comp_no='"+compNo+"')",
                maEqMstrCommonDTO.getFilterPEqId(), maEqMstrCommonDTO.getFilterPEqDesc());
       //작업그룹
       query.getWkCtrLevelQuery("x.wkctr_id", maEqMstrCommonDTO.getFilterWkctrId(), maEqMstrCommonDTO.getFilterWkctrDesc(), compNo);
        
       // 구입일자
       query.getAndDateQuery("x.setup_date", maEqMstrCommonDTO.getFilterFromSetupDate(), maEqMstrCommonDTO.getFilterToSetupDate());
       
       // 보증기간
       query.getAndDateQuery("x.guaranty_date", maEqMstrCommonDTO.getFilterFromGuarDate(), maEqMstrCommonDTO.getFilterToGuarDate());
       //부위 
		if(!"".equals(maEqMstrCommonDTO.getFilterEqasmbDesc())) 
		{ 
			query.append("AND x.equip_id IN (SELECT a.equip_id FROM TAEQASMB a WHERE a.comp_no = '"+compNo+"'");
			query.getLikeQuery("a.full_desc", maEqMstrCommonDTO.getFilterEqasmbDesc()); 
			query.append("															)"); 
		} 
       //설비종류(필터아님)
       query.getAndQuery("x.eqctg_id", maEqMstrCommonDTO.getEqCtgId());
       
       return query.toString();
    }

	public int insertQrCode(String id, String fileName, User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query = new QuerySqlBuffer();
    	
		query.append("INSERT INTO TAREPORTPARAM (   ");
        query.append("        comp_no               ");
        query.append("        ,user_id              ");
        query.append("        ,skey_id              ");
        query.append("        ,file_name            ");
        query.append(")                             ");
        query.append("VALUES (                      ");
        query.append("        ?                     ");
        query.append("        ,?                    ");
        query.append("        ,?                    ");
        query.append("        ,?                    ");
        query.append(")                             ");
        
        Object[] objects = new Object[]{
                loginUser.getCompNo(),
                loginUser.getUserId(),
                id,
                fileName
        };
	    
	    int result = this.getJdbcTemplate().update(query.toString(), getObject(objects));

	    return result;
	}
	public int insertListQrCode(MaEqMstrCommonDTO maEqMstrCommonDTO, String fileName, User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAREPORTPARAM (     ");
        query.append("        comp_no                 ");
        query.append("        ,user_id                ");
        query.append("        ,skey_id                ");
        query.append("        ,file_name              ");
        query.append(")                               ");
        query.append("SELECT x.comp_no,?,x.equip_id,? ");
        query.append("FROM   TAEQUIPMENT x            ");
        query.append(this.getWhere(maEqMstrCommonDTO, loginUser));
        
        Object[] objects = new Object[]{
                loginUser.getUserId()
                ,fileName
        };
		
		int result = this.getJdbcTemplate().update(query.toString(), getObject(objects));
		
		return result;
	}

	public int deleteQrCode(User loginUser, String fileName) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAREPORTPARAM    ");
        query.append("WHERE  comp_no   = ?    ");
        query.append("AND    user_id   = ?    ");
        query.append("AND    file_name = ?    ");

        Object[] objects = new Object[]{
                loginUser.getCompNo()
                ,loginUser.getUserId()
                ,fileName
        };

        int result = this.getJdbcTemplate().update(query.toString(), getObject(objects));

        return result;
    }
	
	public String getNextSequence(String seqName)
    {
    	return super.getNextSequence(seqName);
    }
	/**
	 * copy detail insert
	 * @param maEqMstrDetailDTO
	 * @return
	 */
	public int insertCopyDetail(String newId, String oldId, String revisionHistId,String revisionStatus, User user,String isCopy)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQUIPMENT												");
    	query.append("(comp_no		, equip_id	, item_no, description, eqloc_id					");
    	query.append(", eqctg_id, eq_status, dept_id, main_mng_id, sub_mng_id				");
    	query.append(", setup_date, buy_amt, plf_type, maker, model_no						");
    	query.append(", capacity, util_capa, is_law_eq, guaranty_date, ord_no				");
    	query.append(", remark, excel_no, as_vendor, as_name, as_phone						");
    	query.append(", old_eq_no, serial_no, eq_grade, plant, eqctg_type					");
    	query.append(", buy_date, p_equip_id, quantity, revisionhist_id, revision_status	");
    	query.append(", is_last_version, supplier, country_maker,run_date, disused_date		");
    	query.append(", pmi_action_type,prod_shape,eq_spec, eq_size, weight					");
    	query.append(", eqstrloc_no,is_deleted,usage_dept, usage_emp, ctctr_id				");
    	query.append(", cre_date, cre_by		,storage													");
    	query.append("	)																	");
    	query.append("	SELECT																");
    	query.append("	 comp_no,		?,													");
    	if("Y".equals(isCopy)) query.append(""+newId+","); // 설비복사
    	else query.append("item_no, ");	//설비 개정
    		
    	if("Y".equals(isCopy)){// 설비복사
    		query.append("	 '['||(SELECT a.key_name FROM TALANG a WHERE a.key_type='LABEL' AND a.key_no='copied' AND a.lang = '"+user.getLangId()+"')||']'||	");
    	}
    	query.append("	description ");
    	query.append(", eqloc_id															");
    	query.append(", eqctg_id, eq_status, dept_id, main_mng_id, sub_mng_id				");
    	query.append(", setup_date, buy_amt, plf_type, maker, model_no						");
    	query.append(", capacity, util_capa, is_law_eq, guaranty_date, ord_no				");
    	query.append(", remark, excel_no, as_vendor, as_name, as_phone						");
    	query.append(", old_eq_no, serial_no, eq_grade, plant, eqctg_type					");
    	query.append(", buy_date, p_equip_id, quantity, ?, ?								");
    	query.append(", ?, supplier, country_maker,run_date, disused_date					");
    	query.append(", pmi_action_type,prod_shape,eq_spec, eq_size, weight					");
    	query.append(", eqstrloc_no,'N',usage_dept, usage_emp, ctctr_id						");
    	query.append(", ?, ?		,storage												");
    	query.append("	FROM TAEQUIPMENT													");
    	query.append("	WHERE 1=1															");
    	query.append("	AND comp_no = ?														");
    	query.append("	AND equip_id= ?														");
    	
    	Object[] objects = new Object[] {
    			newId,
    			revisionHistId,
    			revisionStatus,
    			"N".equals(MwareConfig.getIsUseAssetRevision())?"Y":"N",
    	    	DateUtil.getDateTime("yyyyMMddHHmmss"),
    	    	user.getUserId(),
    			user.getCompNo(),
    			oldId
    	};
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	/**
	 * copy eqasmb insert
	 * @param maEqMstrDetailDTO
	 * @return
	 */
	public int insertCopyEqAsmb(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQASMB								");
		query.append("	(comp_no,		eqasmb_id,		equip_id,		");
    	query.append("	 eq_ctg_asmb_id,eqctg_id,		is_eqtype_asmb,	");
    	query.append("	 description,	is_use,			ord_no			");
    	query.append("	) SELECT 										");
    	query.append("	 x.comp_no,		NEXT VALUE FOR SQAEQASMB_ID,	?,		");
    	query.append("	 x.eq_ctg_asmb_id,x.eqctg_id,	x.is_eqtype_asmb,");
    	query.append("	 x.description,	x.is_use,			x.ord_no	");
    	query.append("	FROM TAEQASMB x									");
		query.append("	WHERE 1=1										");
		query.append("	AND x.comp_no = ?								");
		query.append("	AND x.equip_id= ?								");
		
		Object[] objects = new Object[] {
				newId
				,user.getCompNo()
				,oldId
		};
		return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	public int insertCopyEqMold(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQMOLD												");
		query.append("	(comp_no, equip_id, eqmold_atype, eqmold_btype, eqstrloc_no		");
    	query.append("	 , cavity, material, method, structure, storage					");
    	query.append("	 , setcnt, output, ownership, is_disuse, disuse_date			");
    	query.append("	 , disuse_amt, disuse_vendor, use_period						");
    	query.append("	) SELECT 														");
    	query.append("	comp_no, ?, eqmold_atype, eqmold_btype, eqstrloc_no				");
    	query.append("	 , cavity, material, method, structure, storage					");
    	query.append("	 , setcnt, output, ownership, is_disuse, disuse_date			");
    	query.append("	 , disuse_amt, disuse_vendor, use_period						");
    	query.append("	FROM TAEQMOLD													");
		query.append("	WHERE 1=1														");
		query.append("	AND comp_no = ?													");
		query.append("	AND equip_id= ?													");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public int insertCopyEqTool(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQTOOL													");
		query.append("	(comp_no, equip_id, measure_unit, guage_type, is_standard_eq		");
    	query.append("	 ,base_equip_id, min_unit_value, min_value, max_value, measure_tool	");
    	query.append("	 ,measure_categ, pmc_type, all_range, accuracy, use_range			");
    	query.append("	 , tolerance, pmcalibstdtp_id										");
    	query.append("	) SELECT 															");
    	query.append("	comp_no, ?, measure_unit, guage_type, is_standard_eq				");
    	query.append("	 ,base_equip_id, min_unit_value, min_value, max_value, measure_tool	");
    	query.append("	 ,measure_categ, pmc_type, all_range, accuracy, use_range			");
    	query.append("	 , tolerance, pmcalibstdtp_id										");
    	query.append("	FROM TAEQTOOL														");
		query.append("	WHERE 1=1															");
		query.append("	AND comp_no = ?														");
		query.append("	AND equip_id= ?														");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public int insertCopyEqBuilding(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQBUILDING						");
		query.append("	(comp_no, equip_id, addr, area, struct		");
    	query.append("	 , floor, section							");
    	query.append("	) SELECT 									");
    	query.append("	comp_no, ?, addr, area, struct				");
    	query.append("	 , floor, section							");
    	query.append("	FROM TAEQBUILDING							");
		query.append("	WHERE 1=1									");
		query.append("	AND comp_no = ?								");
		query.append("	AND equip_id= ?								");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public int insertCopyEqSpec(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQSPEC									");
		query.append("	(comp_no, eqspec_id, equip_id, categ, prompt		");
    	query.append("	 , response, uom, ord_no							");
    	query.append("	) SELECT 											");
    	query.append("	comp_no, next value for sqaeqspec_id, ?, categ, prompt		");
    	query.append("	 , response, uom, ord_no							");
    	query.append("	FROM TAEQSPEC										");
		query.append("	WHERE 1=1											");
		query.append("	AND comp_no = ?										");
		query.append("	AND equip_id= ?										");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public int insertCopyEqPart(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQPART																");
		query.append("	(comp_no, eqpart_id, eqasmb_id, equip_id, part_id								");
		query.append("	 , consist_qty																	");
		query.append("	 , eq_ctg_part_id, eq_ctg_asmb_id												");
    	query.append("	) SELECT 																		");
    	query.append("	comp_no, next value for sqaeqpart_id, eqasmb_id, ?, part_id							");
		query.append("	 , consist_qty																	");
		query.append("	 , eq_ctg_part_id, eq_ctg_asmb_id												");
    	query.append("	FROM TAEQPART																	");
		query.append("	WHERE 1=1																		");
		query.append("	AND comp_no = ?																	");
		query.append("	AND equip_id= ?																	");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public int insertCopyEqAsset(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQASSET									");
		query.append("	(comp_no, eqasset_id, asset_id, equip_id			");
    	query.append("	) SELECT 											");
    	query.append("	comp_no, next value for sqaeqasset_id, asset_id, ?			");
    	query.append("	FROM TAEQASSET										");
		query.append("	WHERE 1=1											");
		query.append("	AND comp_no = ?										");
		query.append("	AND equip_id= ?										");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public int insertCopyEqItems(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQITEMS									");
		query.append("	(comp_no, eqitem_id, equip_id, item_name, item_qty	");
    	query.append("	, remark, ord_no									");
		query.append("	) SELECT 											");
    	query.append("	comp_no, next value for sqaeqitem_id,?,item_name, item_qty	");
    	query.append("	, remark, ord_no									");
    	query.append("	FROM TAEQITEMS										");
		query.append("	WHERE 1=1											");
		query.append("	AND comp_no = ?										");
		query.append("	AND equip_id= ?										");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public int insertCopyEqVendor(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQVENDOR								");
		query.append("	(comp_no, eqvendor_id, equip_id, vendor_id			");
		query.append("	) SELECT 											");
    	query.append("	comp_no, next value for sqaeqvendor_id, ?, vendor_id		");
    	query.append("	FROM TAEQVENDOR										");
		query.append("	WHERE 1=1											");
		query.append("	AND comp_no = ?										");
		query.append("	AND equip_id= ?										");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public int insertCopyEqDevice(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQDEVICE								");
		query.append("	(comp_no, equip_id, part_id, wcode_id				");
		query.append("	) SELECT 											");
    	query.append("	comp_no, ?, part_id, wcode_id						");
    	query.append("	FROM TAEQDEVICE										");
		query.append("	WHERE 1=1											");
		query.append("	AND comp_no = ?										");
		query.append("	AND equip_id= ?										");
		
		Object[] objects = new Object[] {
				newId,
				user.getCompNo(),
				oldId
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}

	/**
	 * copy eqasmb insert
	 * @param maEqMstrDetailDTO
	 * @return
	 */
	public int insertCopyEqHist(String newId, String oldId, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAEQALTHIST								");
    	query.append("	(comp_no,		eqalthist_id,		upd_date,		");
    	query.append("	 upd_by,		equip_id,			item_no,		");
    	query.append("	 description,	eqloc_id,			eqctg_id,		");
    	query.append("	 eq_status,		dept_id,			main_mng_id,	");
    	query.append("	 sub_mng_id,	is_law_eq,			remark			");
    	query.append("	)	SELECT											");
    	query.append("	 x.comp_no,		NEXT VALUE FOR SQAEQALTHIST_ID,CONVERT(VARCHAR, GETDATE(), 112) +' '+ CONVERT(VARCHAR, GETDATE(), 108),		");
    	query.append("	 ?,				x.equip_id,			x.item_no,		");
    	query.append("	 x.description,	x.eqloc_id,			x.eqctg_id,		");
    	query.append("	 x.eq_status,	x.dept_id,			x.main_mng_id,	");
    	query.append("	 x.sub_mng_id,	x.is_law_eq,		x.remark		");
    	query.append("	FROM TAEQUIPMENT x									");
		query.append("	WHERE 1=1											");
		query.append("	AND x.comp_no = ?									");
		query.append("	AND x.equip_id= ?									");
		
		Object[] objects = new Object[] {
				user.getUserId()
				,user.getCompNo()
				,newId
		};
		return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	
	public void SP_IF_UPD_TXEQUIPMENT(String equipId, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("EXEC dbo.SP_IF_UPD_TXEQUIPMENT '"+user.getCompNo()+"', "+equipId+", '"+user.getUserNo()+"';     ");
        
        this.getJdbcTemplate().execute(query.toString());
    }


	@Override
	public List findEqPartMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                ");
        query.append("SELECT                                                            	");
        query.append("       ''                                          seqNo          	");
        query.append("       ,''                                         isDelCheck     	");
        query.append("       ,x.equip_id                                 equipId       		");
        query.append("       ,x.item_no                                  itemNo         	");
        query.append("       ,CASE x.eqctg_type WHEN 'MD' THEN '('+CONVERT(VARCHAR,ISNULL(x.old_eq_no,''))+')'+x.description ELSE x.description END equipDesc	");
        query.append("       ,(SELECT full_desc                                           	");
        query.append("          FROM TAEQLOC                                             	");
        query.append("         WHERE comp_no = x.comp_no                                 	");
        query.append("           AND eqloc_id = x.eqloc_id)              eqLocDesc      	");
        query.append("       ,(SELECT full_desc                                           	");
        query.append("          FROM TAEQCTG                                             	");
        query.append("         WHERE comp_no = x.comp_no                                 	");
        query.append("           AND eqctg_id = x.eqctg_id)              eqCtgDesc      	");
        query.append("       ,x.maker                                    maker         		");
        query.append("       ,x.model_no                                 modelNo        	");
        query.append("       ,dbo.SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')  		plfTypeDesc	");
        query.append("       ,x.eqctg_type                               eqCtgType      	");
        query.append("       ,dbo.SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  	eqCtg2		");
        query.append("       ,x.is_law_eq                                isLawEq        	");
        query.append("       ,(SELECT description                                         	");
        query.append("          FROM TADEPT                                              	");
        query.append("         WHERE comp_no = x.comp_no                                 	");
        query.append("           AND dept_id = x.dept_id)                deptDesc       	");
        query.append("       ,(SELECT description                                           ");
        query.append("          FROM TAPLANT                                                ");
        query.append("         WHERE comp_no = x.comp_no                                    ");
        query.append("           AND plant = x.plant)                    plantDesc          ");
        query.append("       ,(SELECT emp_name                                            	");
        query.append("          FROM TAEMP                                               	");
        query.append("         WHERE comp_no = x.comp_no                                 	");
        query.append("           AND emp_id = x.main_mng_id)             mainMngName    	");
        query.append("       ,(SELECT emp_name                                            	");
        query.append("          FROM TAEMP                                               	");
        query.append("         WHERE comp_no = x.comp_no                                 	");
        query.append("           AND emp_id = x.sub_mng_id)              subMngName     	");
        query.append("       ,x.dept_id                                  deptId         	");
        query.append("       ,x.excel_no                                 excelNo           	");
        query.append("       ,x.p_equip_id                               parentEquipId   	");
        query.append("       ,x.p_equip_id                               parentItemNo    	");
        query.append("       ,(SELECT xx.description FROM TAEQUIPMENT xx where xx.equip_id=x.p_equip_id) 	parentEquipDesc    	");
        query.append("       ,dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')	eqstatus			");
        query.append("       ,(SELECT part_no FROM TAPARTS WHERE comp_no = x.comp_no AND part_id = y.part_id) partNo			");
        query.append("       ,(SELECT description FROM TAPARTS WHERE comp_no = x.comp_no AND part_id = y.part_id) partDesc		");
        query.append("       ,(SELECT a.wcode FROM TAWAREHOUSE a WHERE a.wcode_id = y.wcode_id AND a.comp_no = y.comp_no)  eqdeviceLocNo		");
        query.append("       ,(SELECT a.wname FROM TAWAREHOUSE a WHERE a.wcode_id = y.wcode_id AND a.comp_no = y.comp_no)  eqdeviceLocNoDesc		");
        query.append("      ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    ");
        query.append("      ,x.is_last_version                                  isLastVersion        ");
        query.append("      ,x.prod_shape                               prodShape    ");
        query.append("      ,x.eqstrloc_no       eqStrLocNo       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.setup_date        setupDate        ");
        query.append("      ,x.capacity            capacity       ");
        query.append("      ,x.serial_no           serialNo       ");
        query.append("      ,x.supplier            supplier       ");
        query.append("      ,x.country_maker     countryMaker     ");
        query.append("      ,x.util_capa             utilCapa     ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy   ");
        query.append("      ,x.upd_date            updDate        						");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)+'/'+b.emp_name          ");
        query.append("       FROM TAUSER a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy   ");
        query.append("      ,x.cre_date            creDate        						");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQDEVICE y ON x.equip_id = y.equip_id AND x.comp_no = y.comp_no 	");
        query.append(this.getWhere(maEqMstrCommonDTO, user));
        query.append("  AND  x.eqctg_type='PT'                                    			");
        query.getOrderByQuery("x.equip_id", "x.ord_no", maEqMstrCommonDTO.getOrderBy(), maEqMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrCommonDTO.getIsLoadMaxCount(), maEqMstrCommonDTO.getFirstRow()));
	}



	public String  findPtTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT					");
    	query.append("		count(1)			");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQDEVICE y ON x.equip_id = y.equip_id AND x.comp_no = y.comp_no	");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.append("  AND  x.eqctg_type='PT'                                    			");

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
	
	public String  findMcTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT					");
    	query.append("		count(1)			");
        query.append("FROM   TAEQUIPMENT x                                              ");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.append("       AND   x.eqctg_type='MC'                                    ");

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
	
	public String  findUtTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT					");
    	query.append("		count(1)			");
        query.append("FROM   TAEQUIPMENT x                                              ");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.append("       AND   x.eqctg_type='UT'                                    ");

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
	
	public String  findBdTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT					");
    	query.append("		count(1)			");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQBUILDING y                                                                   ");
        query.append("	ON x.comp_no=y.comp_no                                                                         ");
        query.append(" AND x.equip_id=y.equip_id                                                                       ");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.append("       AND   x.eqctg_type='BD'                                    ");
    	query.getLikeQuery("y.addr", maEqMstrCommonDTO.getEqBuildAddr());

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
	
	public String  findTlTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT					");
    	query.append("		count(1)			");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQTOOL y                                 ");
        query.append("	ON x.comp_no=y.comp_no                                                                         ");
        query.append(" AND x.equip_id=y.equip_id                                                                       ");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.append("       AND   x.eqctg_type='TL'                                    ");

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
	
	public String  findMdTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqMstrCommonDTO.getCompNo();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT					");
    	query.append("		count(1)			");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQMOLD y                                  ");
        query.append("	ON x.comp_no = y.comp_no                                     ");
        query.append(" and x.equip_id = y.equip_id                                   ");
    	query.append(this.getWhere(maEqMstrCommonDTO, user));
    	query.append("    and x.EQCTG_TYPE = 'MD'                                       ");
    	query.getCodeLikeQuery("y.eqmold_atype", "dbo.SFACODE_TO_DESC(y.eqmold_atype, 'EQMOLD_ATYPE', 'USR', '"+user.getCompNo()+"', '"+user.getLangId()+"')", maEqMstrCommonDTO.getFilterEqMoldAtype(), maEqMstrCommonDTO.getFilterEqMoldAtypeDesc());

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
	public List findImgFileList(String oldImageId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                        ");
        query.append("       x.comp_no AS comp_no                   ");
        query.append("       ,x.imgdata_id AS imgdata_id            ");
        query.append("       ,x.image_id AS image_id                ");
        query.append("       ,x.image_url AS image_url              ");
        query.append("       ,x.file_name AS file_name              ");
        query.append("       ,x.file_ext AS file_ext                ");
        query.append("       ,x.file_size AS file_size              ");
        query.append("       ,x.nf_file_name AS nf_file_name        ");
        query.append("       ,x.nf_file_path AS nf_file_path        ");
        query.append("       ,next value for sqaimgdata_id new_imgdata_id  ");
        query.append("FROM   TAIMGDATA x                            ");
        query.append("WHERE 1=1										");
        query.getAndQuery("x.image_id", oldImageId);
        query.getAndQuery("x.comp_no", user.getCompNo());

        return getJdbcTemplate().queryForList(query.toString());
    }
	public List findImgList(String objectId, String objectType, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                        ");
        query.append("       x.comp_no AS comp_no                   ");
        query.append("       ,x.image_id AS image_id                ");
        query.append("       ,x.object_type AS object_type          ");
        query.append("       ,x.object_id AS object_id              ");
        query.append("       ,x.dept_id AS dept_id                  ");
        query.append("       ,x.reg_id AS reg_id                    ");
        query.append("       ,x.reg_date AS reg_date                ");
        query.append("       ,x.description AS description          ");
        query.append("       ,next value for sqaimage_id new_image_id      ");
        query.append("FROM   TAIMAGE  x                             ");
        query.append("WHERE 1=1                                     ");
        query.getAndQuery("x.object_id", objectId);
        query.getAndQuery("x.object_type", objectType);
        query.getAndQuery("x.comp_no", user.getCompNo());

        return getJdbcTemplate().queryForList(query.toString());
    }
	public int insertImage(String newEquipId, Map map, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAIMAGE									");
    	query.append("	(comp_no,		image_id,			object_type,	");
    	query.append("	 object_id,		dept_id,			reg_id,			");
    	query.append("	 reg_date,		description							");
    	query.append("	)	VALUES (										");
    	query.append("	 ?,				?,					?,				");
    	query.append("	 ?,				?,					?,				");
    	query.append("	 ?,				?									");
    	query.append("	 )													");
		
		Object[] objects = new Object[] {
				String.valueOf(map.get("COMP_NO"))
				,String.valueOf(map.get("NEW_IMAGE_ID"))
				,String.valueOf(map.get("OBJECT_TYPE"))
				,newEquipId
				,String.valueOf(map.get("DEPT_ID"))
				,String.valueOf(map.get("REG_ID"))
				,String.valueOf(map.get("REG_DATE"))
				,String.valueOf(map.get("DESCRIPTION"))
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	
	public int insertImageFile(String newImageId, Map map, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAIMGDATA									");
    	query.append("	(comp_no,		imgdata_id,			image_id,		");
    	query.append("	 image_url,		file_name,			file_ext,		");
    	query.append("	 file_size,		nf_file_name,		nf_file_path	");
    	query.append("	)	VALUES (										");
    	query.append("	 ?,				?,					?,				");
    	query.append("	 ?,				?,					?,				");
    	query.append("	 ?,				?,					?				");
    	query.append("	 )													");
		
		Object[] objects = new Object[] {
				String.valueOf(map.get("COMP_NO"))
				,String.valueOf(map.get("NEW_IMGDATA_ID"))
				,newImageId
				,String.valueOf(map.get("IMAGE_URL"))
				,String.valueOf(map.get("FILE_NAME"))
				,String.valueOf(map.get("FILE_EXT"))
				,String.valueOf(map.get("FILE_SIZE"))
				,String.valueOf(map.get("NEW_IMGDATA_ID"))
				,String.valueOf(map.get("NF_FILE_PATH"))
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}

	public List findDocList(String objectId, String objectType, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        	");
        query.append("       x.comp_no 				AS comp_no          ");
        query.append("       ,x.objdoc_id 			AS objdoc_id        ");
        query.append("       ,x.object_type 		AS object_type      ");
        query.append("       ,x.object_id 			AS object_id        ");
        query.append("       ,x.doc_id 				AS doc_id           ");
        query.append("       ,next value for sqaobjdoc_id 	AS new_objdoc_id	");
        query.append("FROM   TAOBJDOC  x                             	");
        query.append("WHERE 1=1                                     	");
        query.getAndQuery("x.object_id", objectId);
        query.getAndQuery("x.object_type", objectType);
        query.getAndQuery("x.comp_no", user.getCompNo());

        return getJdbcTemplate().queryForList(query.toString());
    }
	public int insertDoc(String newObjectId, Map map, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAOBJDOC								");
    	query.append("	( comp_no		,objdoc_id		,object_type	");
    	query.append("	 ,object_id		,doc_id							");
    	query.append("	)	VALUES (									");
    	query.append("	  ?				,?				,?				");
    	query.append("	 ,?				,?								");
    	query.append("	 )												");
		
		Object[] objects = new Object[] {
				String.valueOf(map.get("COMP_NO"))
				,String.valueOf(map.get("NEW_OBJDOC_ID"))
				,String.valueOf(map.get("OBJECT_TYPE"))
				,newObjectId
				,String.valueOf(map.get("DOC_ID"))
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	public List findDocFileList(String oldDocId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        	");
        query.append("       x.comp_no 				AS comp_no          ");
        query.append("       ,x.docdata_id 			AS docdata_id       ");
        query.append("       ,x.doc_id 				AS doc_id           ");
        query.append("       ,x.file_name 			AS file_name        ");
        query.append("       ,x.file_ext 			AS file_ext         ");
        query.append("       ,x.file_size 			AS file_size        ");
        query.append("       ,x.nf_file_name 		AS nf_file_name     ");
        query.append("       ,x.nf_file_path 		AS nf_file_path     ");
        query.append("       ,next value for sqadocdata_no	AS new_docdata_id	");
        query.append("FROM   TADOCDATA x                            	");
        query.append("WHERE 1=1											");
        query.getAndQuery("x.doc_id", oldDocId);
        query.getAndQuery("x.comp_no", user.getCompNo());

        return getJdbcTemplate().queryForList(query.toString());
    }
	public int insertDocFile(String newDocId, Map map, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TADOCDATA								");
    	query.append("	( comp_no		,docdata_id			,doc_id		");
    	query.append("	 ,file_name		,file_ext			,file_size	");
    	query.append("	 ,nf_file_name	,nf_file_path					");
    	query.append("	)	VALUES (									");
    	query.append("	  ?				,?					,?			");
    	query.append("	 ,?				,?					,?			");
    	query.append("	 ,?				,?								");
    	query.append("	 )												");
		
		Object[] objects = new Object[] {
				String.valueOf(map.get("COMP_NO"))
				,String.valueOf(map.get("NEW_DOCDATA_ID"))
				,newDocId
				,String.valueOf(map.get("FILE_NAME"))
				,String.valueOf(map.get("FILE_EXT"))
				,String.valueOf(map.get("FILE_SIZE"))
				,String.valueOf(map.get("NEW_DOCDATA_ID"))
				,String.valueOf(map.get("NF_FILE_PATH"))
		};
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	
	public String getData(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT											");
		query.append("    CASE WHEN (SELECT COUNT(x.exceltab_id) 		");
		query.append("                FROM TAEXCELTAB x					");
		query.append("                WHERE exceltab_no = ?) = 0		");
		query.append("        THEN '0' 									");
		query.append("        ELSE (SELECT 								");
		query.append("                CONVERT(VARCHAR,x.exceltab_id) + ',' + x.description + ',' + x.table_name        		");
		query.append("            FROM TAEXCELTAB x 					");
		query.append("            WHERE x.exceltab_no = ?)				");
		query.append("        END										");
		
        Object[] objects = new Object[] {
        		maEqMstrCommonDTO.getExceltabNo()
        		,maEqMstrCommonDTO.getExceltabNo()
        };

		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}

}